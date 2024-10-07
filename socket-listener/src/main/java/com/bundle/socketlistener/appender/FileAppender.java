package com.bundle.socketlistener.appender;

import com.bundle.socketlistener.model.DataRecord;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.io.FileWriter;
import java.io.IOException;

@Component
public class FileAppender implements IFileAppender {
    private static final Logger logger = LoggerFactory.getLogger(FileAppender.class);
    private final ObjectMapper objectMapper;
    private static final String FILE_PATH = "filtered_data.log";

    public FileAppender(ObjectMapper objectMapper) {
        this.objectMapper = objectMapper;
    }

    @Override
    public void appendToFile(DataRecord dataRecord) {
        try (FileWriter fileWriter = new FileWriter(FILE_PATH, true)) {
            String jsonData = objectMapper.writeValueAsString(dataRecord);
            fileWriter.write(jsonData + System.lineSeparator());
            logger.info("Data appended to file: {}", jsonData);
        } catch (IOException e) {
            logger.error("Failed to append data to file", e);
            throw new RuntimeException("File append failed", e);
        }
    }
}
