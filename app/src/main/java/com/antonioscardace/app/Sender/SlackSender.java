package com.antonioscardace.app.Sender;

import com.slack.api.Slack;
import com.slack.api.webhook.Payload;
import com.slack.api.webhook.WebhookResponse;

import java.io.IOException;

import javax.mail.MessagingException;

public final class SlackSender implements ISender {
    
    private static SlackSender instance = new SlackSender();

    private SlackSender() {
        
    }

    public static SlackSender getInstance() {
        return SlackSender.instance;
    }

    @Override
    public void send(String webhook, String message) throws MessagingException, IOException {
        Slack slack = Slack.getInstance();
        String webhookUrl = "https://hooks.slack.com/services/" + webhook;

        Payload payload = Payload.builder()
            .text(message)
            .build();

        WebhookResponse response = slack.send(webhookUrl, payload);
    }
}