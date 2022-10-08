package com.antonioscardace.app.Request;

import com.antonioscardace.app.Config;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URL;

public final class HttpRequest implements IRequest {
    private final Integer HTTP_TIMEOUT;
    private static HttpRequest instance = new HttpRequest();

    private HttpRequest() {
        this.HTTP_TIMEOUT = Config.getInstance().getInt("httpTimeout");
    }

    public static HttpRequest getInstance() {
        return HttpRequest.instance;
    }

    public boolean request(String url_link) throws IOException {
        try {
            URL url = new URL(url_link);
            HttpURLConnection connection = (HttpURLConnection) url.openConnection();

            connection.setRequestMethod("GET");
            connection.setConnectTimeout(this.HTTP_TIMEOUT * 1000);
            connection.connect();
            return connection.getResponseCode() == 200;
        }
        catch (Exception e) {
            System.out.println("HTTP Exception ... " + e.getMessage());
            return false;
        }
    }
}
