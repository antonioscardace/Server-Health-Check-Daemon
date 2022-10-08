package com.antonioscardace.app.Request;

public final class RequestCreator {

    public static IRequest getRequest(String addressType) {
        if (addressType == null || addressType.trim().isEmpty())
            return null;

        switch (addressType) {
            case "url":
                return HttpRequest.getInstance();
            case "ip":
                return PingRequest.getInstance();
            default:
                throw new IllegalArgumentException("Unknown request type " + addressType);
        }
    }
}