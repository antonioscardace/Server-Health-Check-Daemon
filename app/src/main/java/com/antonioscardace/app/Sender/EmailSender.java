package com.antonioscardace.app.Sender;

import com.antonioscardace.app.Config;

import java.util.Properties;
import javax.mail.*;
import javax.mail.internet.*;

public final class EmailSender implements ISender {
    private final String SENDER_EMAIL;
    private final String SENDER_NAME;
    private final String SENDER_USERNAME;
    private final String SENDER_PASSWORD;

    private final String EMAIL_SUBJECT;

    private final String SMTP_HOST;
    private final Integer SMTP_PORT;

    private static EmailSender instance = new EmailSender();

    private EmailSender() {
        this.SENDER_EMAIL = Config.getInstance().get("senderEmail");
        this.SENDER_NAME = Config.getInstance().get("senderName");
        this.SENDER_USERNAME = Config.getInstance().get("senderUsername");
        this.SENDER_PASSWORD = Config.getInstance().get("senderPassword");

        this.EMAIL_SUBJECT = Config.getInstance().get("emailSubject");

        this.SMTP_HOST = Config.getInstance().get("smtpHost");
        this.SMTP_PORT = Config.getInstance().getInt("smtpPort");
    }

    public static EmailSender getInstance() {
        return EmailSender.instance;
    }

    @Override
    public void send(String recipient, String message) throws MessagingException {
        Properties props = new Properties();
        props.put("mail.smtp.user", this.SENDER_NAME);
        props.put("mail.smtp.host", this.SMTP_HOST);
        props.put("mail.smtp.port", this.SMTP_PORT.toString());
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");

        final String username = this.SENDER_USERNAME;
        final String password = this.SENDER_PASSWORD;

        Session session = Session.getDefaultInstance(props, new Authenticator() {
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(username, password);
            }
        });

        MimeMessage msg = new MimeMessage(session);
        msg.setFrom(new InternetAddress(this.SENDER_EMAIL));
        msg.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
        msg.setSubject(this.EMAIL_SUBJECT);
        msg.setText(message);

        Transport.send(msg);
    }
}