package com.bundle.dbwriter.repository;

import com.bundle.dbwriter.model.MessageRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IMessageRepository extends JpaRepository<MessageRecord, Long> {
}
