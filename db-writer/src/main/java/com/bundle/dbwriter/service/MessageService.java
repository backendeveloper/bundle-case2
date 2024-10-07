package com.bundle.dbwriter.service;

import com.bundle.dbwriter.model.MessageRecord;
import com.bundle.dbwriter.repository.IMessageRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@Service
public class MessageService {
    private static final Logger logger = LoggerFactory.getLogger(MessageService.class);
    private final IMessageRepository messageRepository;

    public MessageService(IMessageRepository messageRepository) {
        this.messageRepository = messageRepository;
    }

    public void saveDataRecord(MessageRecord messageRecord) {
        try {
            messageRepository.save(messageRecord);
            logger.info("DataRecord saved successfully: {}", messageRecord);
        } catch (Exception e) {
            logger.error("Failed to save DataRecord: {}", messageRecord, e);
            throw new RuntimeException("Database insertion failed", e);
        }
    }
}
