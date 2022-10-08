package com.antonioscardace.app.Sender;

import com.antonioscardace.app.Config;
import com.antonioscardace.app.Request.HttpRequest;

import java.io.IOException;
import java.text.MessageFormat;

public final class TelegramSender implements ISender {
    private final String TOKEN;
    private static TelegramSender instance = new TelegramSender();

    private TelegramSender() {
        this.TOKEN = Config.getInstance().get("telegramToken");
    }

    public static TelegramSender getInstance() {
        return TelegramSender.instance;
    }

    @Override
    public void send(String recipient, String message) throws IOException {
        String basicUrl = "https://api.telegram.org/bot{0}/sendMessage?chat_id={1}&text={2}&parse_mode=html";
        String urlApi = MessageFormat.format(basicUrl, this.TOKEN, recipient, message);
        HttpRequest.getInstance().request(urlApi);
    }
}