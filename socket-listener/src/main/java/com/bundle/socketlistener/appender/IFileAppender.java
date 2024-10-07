package com.bundle.socketlistener.appender;

import com.bundle.socketlistener.model.DataRecord;

public interface IFileAppender {
    void appendToFile(DataRecord dataRecord);
}
