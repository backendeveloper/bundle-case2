//package com.bundle.socketlistener.handler;
//
//import com.bundle.socketlistener.common.MessageHandlingException;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//
//import java.io.BufferedWriter;
//import java.io.FileWriter;
//import java.io.IOException;
//
//public class FileMessageHandler implements IMessageHandler {
//    private static final Logger logger = LoggerFactory.getLogger(FileMessageHandler.class);
//
//    private final String filePath;
//
//    public FileMessageHandler(String filePath) {
//        this.filePath = filePath;
//    }
//
//    @Override
//    public void handle(String message) throws MessageHandlingException {
//        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
//            writer.append(message).append(System.lineSeparator());
//            logger.debug("Message appended to file: {}", message);
//        } catch (IOException e) {
//            logger.error("Failed to append message to file", e);
//            throw new MessageHandlingException("Failed to append message to file", e);
//        }
//    }
//}
