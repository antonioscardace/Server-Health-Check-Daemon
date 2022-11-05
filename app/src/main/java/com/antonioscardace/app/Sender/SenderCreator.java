package com.antonioscardace.app.Sender;

public final class SenderCreator {

    public static ISender getSender(String contactType) {
        if (contactType == null || contactType.trim().isEmpty())
            return null;

        switch (contactType) {
            case "email":
                return EmailSender.getInstance();
            case "slack":
                return SlackSender.getInstance();
            case "telegram":
                return TelegramSender.getInstance();
            default:
                throw new IllegalArgumentException("Unknown contact type " + contactType);
        }
    }
}