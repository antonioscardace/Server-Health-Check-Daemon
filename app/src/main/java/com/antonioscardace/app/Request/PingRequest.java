package com.antonioscardace.app.Request;

import java.io.IOException;
import java.net.InetAddress;

import com.antonioscardace.app.Config;

public final class PingRequest implements IRequest {
    private final Integer PING_TIMEOUT;
    private static PingRequest instance = new PingRequest();

    private PingRequest() {
        this.PING_TIMEOUT = Config.getInstance().getInt("pingTimeout");
    }

    public static PingRequest getInstance() {
        return PingRequest.instance;
    }

    public boolean request(String ip) throws IOException {
        InetAddress address = InetAddress.getByName(ip);
        return address.isReachable(this.PING_TIMEOUT * 1000);
    }
}