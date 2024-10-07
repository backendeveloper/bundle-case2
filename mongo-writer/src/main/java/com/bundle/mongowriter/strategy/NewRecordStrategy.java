package com.bundle.mongowriter.strategy;

import com.bundle.mongowriter.model.MessageRecord;
import com.bundle.mongowriter.service.MessageRecordService;
import org.springframework.stereotype.Component;

@Component
public class NewRecordStrategy implements IMessageProcessingStrategy {

    private final MessageRecordService messageRecordService;

    public NewRecordStrategy(MessageRecordService messageRecordService) {
        this.messageRecordService = messageRecordService;
    }

    @Override
    public void process(MessageRecord dataRecord) {
        messageRecordService.processAndSave(dataRecord);
    }
}
