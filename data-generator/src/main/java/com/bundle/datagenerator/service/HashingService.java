package com.bundle.datagenerator.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.DigestUtils;

@Service
public class HashingService implements IHashingService{

    private static final Logger logger = LoggerFactory.getLogger(HashingService.class);

    @Override
    public String hash(String data) {
        try {
            return DigestUtils.md5DigestAsHex(data.getBytes());
        } catch (Exception e) {
            logger.error("Hashing error for data: {}", data, e);
            throw new RuntimeException("Hashing failed", e);
        }
    }
}
