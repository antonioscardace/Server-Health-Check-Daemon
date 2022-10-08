package com.antonioscardace.app.Daemon;

import java.io.IOException;

import javax.mail.MessagingException;

import com.antonioscardace.app.Config;
import com.antonioscardace.app.Sender.ISender;
import com.antonioscardace.app.Sender.SenderCreator;

public final class AlertMessage {
    private final String errorMessage;
    private final String solveMessage;

    private final boolean outcome;
    private final String serverAddress;
    private final String checkTimestamp;

    public AlertMessage(boolean outcome, String server, String timestamp) {
        this.outcome = outcome;
        this.serverAddress = server;
        this.checkTimestamp = timestamp;

        this.solveMessage = Config.getInstance().get("solveMessage");
        this.errorMessage = Config.getInstance().get("errorMessage");
    }

    private String generateText() {
        String text = this.outcome ? this.solveMessage : this.errorMessage;
        return text.replace("[SERVER_ADDRESS]", this.serverAddress)
                .replace("[DATE]", this.checkTimestamp)
                .replace("[HI]", "👋🏻")
                .replace("[ALERT]", "⚠️")
                .replace("[OK]", "✅");
    }

    public void notify(String to, String contactType) throws IOException, MessagingException {
        ISender sender = SenderCreator.getSender(contactType);
        String message = this.generateText();
        sender.send(to, message);
    }
}