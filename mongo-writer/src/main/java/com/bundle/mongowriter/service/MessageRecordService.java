package com.bundle.mongowriter.service;

import com.bundle.mongowriter.model.MessageRecord;
import com.bundle.mongowriter.repository.IMessageRecordRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MessageRecordService {
    private static final Logger logger = LoggerFactory.getLogger(MessageRecordService.class);
    private final IMessageRecordRepository messageRecordRepository;

    public MessageRecordService(IMessageRecordRepository messageRecordRepository) {
        this.messageRecordRepository = messageRecordRepository;
    }

    public void processAndSave(MessageRecord dataRecord) {
        try {
            if (dataRecord.getMd5HashLastTwoChars() > 99) {
                Optional<MessageRecord> latestRecordOpt = messageRecordRepository.findTopByOrderByIdDesc();
                if (latestRecordOpt.isPresent() && latestRecordOpt.get().getMd5HashLastTwoChars() > 99) {
                    MessageRecord latestRecord = latestRecordOpt.get();
                    latestRecord.getNestedHashes().add(dataRecord.getMd5HashLastTwoChars());
                    messageRecordRepository.save(latestRecord);
                    logger.info("Nested hash added to existing record: {}", latestRecord);
                } else {
                    messageRecordRepository.save(dataRecord);
                    logger.info("New MessageRecord created with high hash: {}", dataRecord);
                }
            } else {
                messageRecordRepository.save(dataRecord);
                logger.info("New MessageRecord created with normal hash: {}", dataRecord);
            }
        } catch (Exception e) {
            logger.error("Failed to process and save MessageRecord: {}", dataRecord, e);
            throw new RuntimeException("MongoDB insertion failed", e);
        }
    }
}
