package com.bundle.mongowriter.strategy;

import com.bundle.mongowriter.model.MessageRecord;
import org.springframework.stereotype.Component;

@Component
public class MessageProcessingStrategySelector {

    private final NestedMessageStrategy nestedMessageStrategy;
    private final NewRecordStrategy newRecordStrategy;

    public MessageProcessingStrategySelector(NestedMessageStrategy nestedMessageStrategy,
                                             NewRecordStrategy newRecordStrategy) {
        this.nestedMessageStrategy = nestedMessageStrategy;
        this.newRecordStrategy = newRecordStrategy;
    }

    public IMessageProcessingStrategy selectStrategy(MessageRecord dataRecord) {
        if (dataRecord.getMd5HashLastTwoChars() > 99) {
            return nestedMessageStrategy;
        } else {
            return newRecordStrategy;
        }
    }
}
