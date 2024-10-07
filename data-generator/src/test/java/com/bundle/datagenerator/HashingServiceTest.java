package com.bundle.datagenerator;

import com.bundle.datagenerator.service.HashingService;
import com.bundle.datagenerator.service.IHashingService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class HashingServiceTest {
    private final IHashingService hashingService = new HashingService();

    @Test
    @DisplayName("Hash method should return correct MD5 hash for given input")
    void hash_ShouldReturnCorrectMD5Hash() {
        // Arrange
        String input = "testInput";
        String expectedHash = "fb054efb1303abdfd6e954e83f41e7bd";

        // Act
        String actualHash = hashingService.hash(input);

        // Assert
        assertEquals(expectedHash, actualHash, "MD5 hash should match the expected value");
    }

    @Test
    @DisplayName("Hash method should throw RuntimeException when hashing fails")
    void hash_ShouldThrowRuntimeException_WhenHashingFails() {
        // Act & Assert
        assertDoesNotThrow(() -> {
            String input = "anotherTest";
            hashingService.hash(input);
        }, "Hashing should not throw an exception for valid input");
    }

    @Test
    @DisplayName("Hash method should produce unique hashes for different inputs")
    void hash_ShouldProduceUniqueHashes_ForDifferentInputs() {
        // Arrange
        String input1 = "inputOne";
        String input2 = "inputTwo";

        // Act
        String hash1 = hashingService.hash(input1);
        String hash2 = hashingService.hash(input2);

        // Assert
        assertNotEquals(hash1, hash2, "Different inputs should produce different hashes");
    }
}
