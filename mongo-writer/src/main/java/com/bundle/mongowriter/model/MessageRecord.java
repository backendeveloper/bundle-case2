package com.bundle.mongowriter.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import java.util.ArrayList;
import java.util.List;

@Document(collection = "messages")
public class MessageRecord {

    @Id
    private String id;

    @Field("timestamp")
    private String timestamp;

    @Field("random_number")
    private int randomNumber;

    @Field("md5_hash_last_two_chars")
    private int md5HashLastTwoChars;

    @Field("nested_hashes")
    private List<Integer> nestedHashes = new ArrayList<>();

    public MessageRecord() {
    }

    public MessageRecord(String timestamp, int randomNumber, int md5HashLastTwoChars) {
        this.timestamp = timestamp;
        this.randomNumber = randomNumber;
        this.md5HashLastTwoChars = md5HashLastTwoChars;
    }

    public String getId() {
        return id;
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

    public List<Integer> getNestedHashes() {
        return nestedHashes;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public void setMd5HashLastTwoChars(int md5HashLastTwoChars) {
        this.md5HashLastTwoChars = md5HashLastTwoChars;
    }

    public void setNestedHashes(List<Integer> nestedHashes) {
        this.nestedHashes = nestedHashes;
    }

    @Override
    public String toString() {
        return "MessageRecord{" +
                "id='" + id + '\'' +
                ", timestamp='" + timestamp + '\'' +
                ", randomNumber=" + randomNumber +
                ", md5HashLastTwoChars=" + md5HashLastTwoChars +
                ", nestedHashes=" + nestedHashes +
                '}';
    }
}
