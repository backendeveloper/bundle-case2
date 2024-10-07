package com.bundle.mongowriter.strategy;

import com.bundle.mongowriter.model.MessageRecord;

public interface IMessageProcessingStrategy {
    void process(MessageRecord dataRecord);
}
