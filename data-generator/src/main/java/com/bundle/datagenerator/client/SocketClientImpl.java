package com.bundle.datagenerator.client;

import com.bundle.datagenerator.common.SocketFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.BufferedWriter;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

@Component
public class SocketClientImpl implements SocketClient {
    private static final Logger logger = LoggerFactory.getLogger(SocketClientImpl.class);

    private final String host;
    private final int port;
    private final SocketFactory socketFactory;

    public SocketClientImpl(@Value("${socket.host}") String host,
                            @Value("${socket.port}") int port,
                            SocketFactory socketFactory) {
        this.host = host;
        this.port = port;
        this.socketFactory = socketFactory;
    }

    @Override
    public void send(String data) throws IOException {
        try (Socket socket = socketFactory.createSocket(host, port);
             BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()))) {

            writer.write(data);
            writer.newLine();
            writer.flush();

            logger.debug("Data sent over socket: {}", data);
        } catch (IOException e) {
            logger.error("Failed to send data over socket to {}:{}", host, port, e);
            throw e;
        }
    }
}
