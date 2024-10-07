package com.bundle.socketlistener.processor;

import com.bundle.socketlistener.model.DataRecord;

public interface IMessageProcessor {
    void process(DataRecord dataRecord);
}
