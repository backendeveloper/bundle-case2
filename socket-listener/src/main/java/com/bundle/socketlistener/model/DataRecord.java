package com.bundle.socketlistener.model;

public class DataRecord {
    private String timestamp;
    private int randomNumber;
    private int md5HashLastTwoChars;

    public DataRecord(String timestamp, int randomNumber, int md5HashLastTwoChars) {
        this.timestamp = timestamp;
        this.randomNumber = randomNumber;
        this.md5HashLastTwoChars = md5HashLastTwoChars;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public int getMd5HashLastTwoChars() {
        return md5HashLastTwoChars;
    }

    @Override
    public String toString() {
        return "DataRecord{" +
                "timestamp='" + timestamp + '\'' +
                ", randomNumber=" + randomNumber +
                ", md5HashLastTwoChars='" + md5HashLastTwoChars + '\'' +
                '}';
    }
}
