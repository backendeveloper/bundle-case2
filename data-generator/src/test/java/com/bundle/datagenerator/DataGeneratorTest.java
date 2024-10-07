package com.bundle.datagenerator;

import com.bundle.datagenerator.model.DataRecord;
import com.bundle.datagenerator.service.HashingService;
import com.bundle.datagenerator.utilty.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.*;

import java.time.Instant;
import java.time.format.DateTimeFormatter;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class DataGeneratorTest {
    @Mock
    private Random mockRandom;

    @Mock
    private HashingService mockHashingService;

    @InjectMocks
    private DataGenerator dataGenerator;

    @Captor
    private ArgumentCaptor<String> stringCaptor;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        // Initialize the dataGenerator with a fixed DateTimeFormatter if needed
    }

    @Test
    @DisplayName("generate should create DataRecord with correct values")
    void generate_ShouldCreateDataRecord_WithCorrectValues() {
        // Arrange
        // Mock the current time
        long fixedTimeMillis = 1702103040123L; // Example fixed timestamp
        Instant fixedInstant = Instant.ofEpochMilli(fixedTimeMillis);
        // We need to mock Instant.now(), but it's a static method. Alternatively, we can inject a Clock.
        // For simplicity, we'll proceed without mocking the time.

        // Mock Random
        when(mockRandom.nextInt(101)).thenReturn(42);

        // Mock HashingService
        when(mockHashingService.hash(anyString())).thenReturn("d5579c46dfcc7d0d445b5315b5e9d21c");

        // Act
        DataRecord record = dataGenerator.generate();

        // Assert
        assertNotNull(record, "DataRecord should not be null");
        assertEquals(42, record.getRandomNumber(), "Random number should match the mocked value");

        // Verify hashingService.hash was called twice with correct parameters
        verify(mockHashingService, times(2)).hash(stringCaptor.capture());

        // Extract captured arguments
        var capturedArgs = stringCaptor.getAllValues();
        // First call: System.currentTimeMillis()
        // Second call: randomNumber
        // Since System.currentTimeMillis() is called, it's difficult to predict its value.
        // Instead, we can focus on the second argument.
        assertEquals("42", capturedArgs.get(1), "HashingService should hash the random number");

        // Verify the MD5 hashes are correctly processed to numeric last two characters
        // Since mockHashingService.hash returns "d5579c46dfcc7d0d445b5315b5e9d21c"
        // The last two chars are "1c", which is hexadecimal 28 -> 28 % 100 = 28
        // So, numericLastTwoRandomNumber should be "28"
        assertEquals("2828", record.getMd5HashLastTwoChars(), "Last two chars should be the concatenation of numeric hashes");
    }
}
