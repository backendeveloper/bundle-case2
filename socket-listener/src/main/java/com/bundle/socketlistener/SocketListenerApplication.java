package com.bundle.socketlistener;

import com.bundle.socketlistener.listener.SocketListener;
import com.bundle.socketlistener.model.DataRecord;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

@SpringBootApplication
public class SocketListenerApplication implements CommandLineRunner {

    private static final Logger logger = LoggerFactory.getLogger(SocketListenerApplication.class);
    private final SocketListener socketListener;

    public SocketListenerApplication(SocketListener socketListener) {
        this.socketListener = socketListener;
    }

    public static void main(String[] args) {
        SpringApplication.run(SocketListenerApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {
        int port = 12345;
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("Socket listener started on port {}", port);
            while (true) {
                Socket clientSocket = serverSocket.accept();
                logger.info("Client connected: {}", clientSocket.getInetAddress());

                new Thread(() -> handleClient(clientSocket)).start();
            }
        }
    }

    private void handleClient(Socket clientSocket) {
        try (BufferedReader reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()))) {
            String line;
            while ((line = reader.readLine()) != null) {
                DataRecord dataRecord = parseData(line);
                if (dataRecord != null) {
                    socketListener.onMessageReceived(dataRecord);
                }
            }
        } catch (Exception e) {
            logger.error("Error handling client: {}", clientSocket.getInetAddress(), e);
        } finally {
            try {
                clientSocket.close();
                logger.info("Client disconnected: {}", clientSocket.getInetAddress());
            } catch (Exception e) {
                logger.error("Error closing client socket: {}", clientSocket.getInetAddress(), e);
            }
        }
    }

    private DataRecord parseData(String data) {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            JsonNode jsonNode = objectMapper.readTree(data);

            String timestamp = jsonNode.get("timestamp").asText();
            int randomNumber = jsonNode.get("randomNumber").asInt();
            int md5HashLastTwoChars = jsonNode.get("md5HashLastTwoChars").asInt();

            String[] parts = data.split(",");
            if (parts.length < 3) {
                logger.warn("Invalid data format: {}", data);
                return null;
            }

            return new DataRecord(timestamp, randomNumber, md5HashLastTwoChars);
        } catch (Exception e) {
            logger.error("Failed to parse data: {}", data, e);
            return null;
        }
    }
}
