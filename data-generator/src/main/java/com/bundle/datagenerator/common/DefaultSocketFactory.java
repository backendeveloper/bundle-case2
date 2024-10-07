package com.bundle.datagenerator.common;

import org.springframework.stereotype.Component;

import java.io.IOException;
import java.net.Socket;

@Component
public class DefaultSocketFactory implements SocketFactory {
    @Override
    public Socket createSocket(String host, int port) throws IOException {
        return new Socket(host, port);
    }
}
