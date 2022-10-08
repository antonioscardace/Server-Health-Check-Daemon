package com.antonioscardace.app.Request;

import java.io.IOException;

public interface IRequest {
    public boolean request(String address) throws IOException;
}
