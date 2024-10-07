package com.bundle.datagenerator.client;

import java.io.IOException;

public interface SocketClient {
    void send(String data) throws IOException;
}
