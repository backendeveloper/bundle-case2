package com.bundle.dbwriter.model;

import jakarta.persistence.*;

@Entity
@Table(name = "message")
public class MessageRecord {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "timestamp")
    private String timestamp;

    @Column(name = "random_number")
    private int randomNumber;

    @Column(name = "md5_hash_last_two_chars")
    private int md5HashLastTwoChars;

    public MessageRecord() {
    }

    public MessageRecord(String timestamp, int randomNumber, int md5HashLastTwoChars) {
        this.timestamp = timestamp;
        this.randomNumber = randomNumber;
        this.md5HashLastTwoChars = md5HashLastTwoChars;
    }

    public Long getId() {
        return id;
    }

    public String getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(String timestamp) {
        this.timestamp = timestamp;
    }

    public int getRandomNumber() {
        return randomNumber;
    }

    public void setRandomNumber(int randomNumber) {
        this.randomNumber = randomNumber;
    }

    public int getMd5HashLastTwoChars() {
        return md5HashLastTwoChars;
    }

    public void setMd5HashLastTwoChars(int md5HashLastTwoChars) {
        this.md5HashLastTwoChars = md5HashLastTwoChars;
    }

    @Override
    public String toString() {
        return "MessageRecord{" +
                "id=" + id +
                ", timestamp='" + timestamp + '\'' +
                ", randomNumber=" + randomNumber +
                ", md5HashLastTwoChars=" + md5HashLastTwoChars +
                '}';
    }
}
