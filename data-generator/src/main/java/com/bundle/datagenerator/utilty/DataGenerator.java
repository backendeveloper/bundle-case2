package com.bundle.datagenerator.utilty;

import com.bundle.datagenerator.model.DataRecord;
import com.bundle.datagenerator.service.HashingService;
import org.springframework.stereotype.Component;

import java.time.Instant;
import java.util.Random;

@Component
public class DataGenerator implements IDataGenerator {
    private final Random random;
    private final HashingService hashingService;

    public DataGenerator(Random random, HashingService hashingService) {
        this.random = random;
        this.hashingService = hashingService;
    }

    @Override
    public DataRecord generate() {
        Instant now = Instant.now();
        String timestamp = now.toString();
        int randomNumber = random.nextInt(101);

        long checksum = now.toEpochMilli() + randomNumber;
        String md5HashData = hashingService.hash(Long.toString(checksum));
        int numericHashData = getNumericLastTwoChars(md5HashData);

        return new DataRecord(timestamp, randomNumber, numericHashData);
    }

    private int getNumericLastTwoChars(String md5Hash) {
        String lastTwoChars = md5Hash.substring(md5Hash.length() - 2);

        long numericValue = Long.parseLong(lastTwoChars, 16);

        return (int) (numericValue % 100);
    }
}
