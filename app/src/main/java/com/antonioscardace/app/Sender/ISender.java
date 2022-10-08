package com.antonioscardace.app.Sender;

import java.io.IOException;

import javax.mail.MessagingException;

public interface ISender {
    public void send(String recipient, String message) throws IOException, MessagingException;
}