package com.bundle.mongowriter.repository;

import com.bundle.mongowriter.model.MessageRecord;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface IMessageRecordRepository extends MongoRepository<MessageRecord, String> {
    Optional<MessageRecord> findTopByOrderByIdDesc();
}
