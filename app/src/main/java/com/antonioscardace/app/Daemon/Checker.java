package com.antonioscardace.app.Daemon;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.TimeZone;

import javafx.util.Pair;

import javax.mail.MessagingException;

import com.antonioscardace.app.Config;
import com.antonioscardace.app.Database.MySql;
import com.antonioscardace.app.Request.IRequest;
import com.antonioscardace.app.Request.RequestCreator;

public final class Checker {
    private final Integer CHECK_TIME;
    private Set<Pair<String, String>> notified;

    public Checker() {
        this.CHECK_TIME = Config.getInstance().getInt("checkTime");
        this.notified = new HashSet<>();
    }

    private boolean checkServer(String address, String addressType) throws IOException {
        IRequest req = RequestCreator.getRequest(addressType);
        return req.request(address);   
    }

    private String getUtcTimestamp() { 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"); 
		dateFormat.setTimeZone(TimeZone.getTimeZone("UTC")); 
		return dateFormat.format(new Date()) + " UTC";
	}

    private void updateNotifics(boolean isConnected, boolean wasUnhealthy, Pair<String, String> observer) {
        if (isConnected && wasUnhealthy) this.notified.remove(observer);
        else if (!isConnected && !wasUnhealthy) this.notified.add(observer);
    }

    private void scanServer(String address, String addressType, String contact, String contactType) throws IOException, MessagingException {
        String timestamp = getUtcTimestamp();
        boolean isConnected = this.checkServer(address, addressType);

        Pair<String, String> observer = new Pair<String,String>(address, contact);
        boolean wasUnhealthy = this.notified.contains(observer);

        if ((isConnected && wasUnhealthy) || (!isConnected && !wasUnhealthy)) {
            AlertMessage newAlert = new AlertMessage(isConnected, address, timestamp);
            newAlert.notify(contact, contactType);
            this.updateNotifics(isConnected, wasUnhealthy, observer);
        }
    }

    public void run() throws ClassNotFoundException, SQLException, InterruptedException, IOException, MessagingException {
        Config config = Config.getInstance();
        MySql db = new MySql(
            config.get("server"),
            config.getInt("port"),
            config.get("userid"),
            config.get("password"),
            config.get("dbName")
        );

        while (true) {
            ResultSet data = db.getRows(
                "servers s JOIN observes o ON s.address = o.address JOIN accounts a ON a.contact = o.contact",
                "s.address, s.address_type, a.contact, a.contact_type"
            );
            
            while (data.next()) 
                this.scanServer(
                    data.getString("address"),
                    data.getString("address_type"),
                    data.getString("contact"),
                    data.getString("contact_type")
                );
                
            Thread.sleep(this.CHECK_TIME * 1000);
        }
    }
}