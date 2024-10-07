package com.bundle.datagenerator.service;

import com.bundle.datagenerator.client.SocketClientImpl;
import com.bundle.datagenerator.model.DataRecord;
import com.bundle.datagenerator.utilty.DataGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class DataGeneratorService {

    private static final Logger logger = LoggerFactory.getLogger(DataGeneratorService.class);

    private final DataGenerator dataGenerator;
    private final SocketClientImpl socketClient;
    private final ObjectMapper objectMapper;

    public DataGeneratorService(DataGenerator dataGenerator,
                                SocketClientImpl socketClient,
                                ObjectMapper objectMapper) {
        this.dataGenerator = dataGenerator;
        this.socketClient = socketClient;
        this.objectMapper = objectMapper;
    }

    //    @Scheduled(fixedRate = 200)
    @Scheduled(fixedRate = 5000)
    public void generateAndSendData() {
        try {
            DataRecord record = dataGenerator.generate();
            String jsonData = objectMapper.writeValueAsString(record);
            socketClient.send(jsonData);
            logger.info("Data sent: {}", record);
        } catch (Exception e) {
            logger.error("Error generating or sending data", e);
        }
    }
}
