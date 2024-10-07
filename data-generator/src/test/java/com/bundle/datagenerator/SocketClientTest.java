//package com.bundle.datagenerator;
//
//import com.bundle.datagenerator.client.SocketClientImpl;
//import com.bundle.datagenerator.common.SocketFactory;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.DisplayName;
//import org.junit.jupiter.api.Test;
//import org.junit.jupiter.api.extension.ExtendWith;
//import org.mockito.*;
//import org.mockito.junit.jupiter.MockitoExtension;
//
//import java.io.IOException;
//import java.io.OutputStream;
//import java.net.Socket;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.mockito.Mockito.*;
//
//@ExtendWith(MockitoExtension.class)
//public class SocketClientTest {
//    @Mock
//    private SocketFactory mockSocketFactory;
//
//    @Mock
//    private Socket mockSocket;
//
//    @Mock
//    private OutputStream mockOutputStream;
//
//    @InjectMocks
//    private SocketClientImpl socketClient;
//
//    @BeforeEach
//    void setUp() throws IOException {
//        // Mock the SocketFactory to return the mockSocket when createSocket is called with specific host and port
//        when(mockSocketFactory.createSocket("localhost", 12345)).thenReturn(mockSocket);
//        // Mock the Socket's getOutputStream method to return the mockOutputStream
//        when(mockSocket.getOutputStream()).thenReturn(mockOutputStream);
//        // Manually instantiate the SocketClientImpl with required parameters
//        socketClient = new SocketClientImpl("localhost", 12345, mockSocketFactory);
//    }
//
//    @Test
//    @DisplayName("send should write data to socket and flush")
//    void send_ShouldWriteDataToSocketAndFlush() throws IOException {
//        // Arrange
//        String testData = "Test JSON Data";
//        String expectedData = testData + System.lineSeparator();
//
//        // Act
//        socketClient.send(testData);
//
//        // Assert
//        // Verify that createSocket was called with correct parameters
//        verify(mockSocketFactory, times(1)).createSocket("localhost", 12345);
//        // Verify that getOutputStream was called on the socket
//        verify(mockSocket, times(1)).getOutputStream();
//        // Verify that data was written to the OutputStream
//        verify(mockOutputStream, times(1)).write(expectedData.getBytes());
//        // Verify that flush was called
//        verify(mockOutputStream, times(1)).flush();
//        // Verify that socket was closed
//        verify(mockSocket, times(1)).close();
//    }
//
//
////    @Test
////    @DisplayName("send should throw IOException when socket connection fails")
////    void send_ShouldThrowIOException_WhenSocketConnectionFails() throws IOException {
////        // Arrange
////        String testData = "Test JSON Data";
////
////        // Mock Socket creation to throw IOException
////        try (MockedStatic<Socket> mockedSocket = mockStatic(Socket.class)) {
////            mockedSocket.when(() -> new Socket("localhost", 12345)).thenThrow(new IOException("Connection failed"));
////
////            // Act & Assert
////            assertThrows(IOException.class, () -> socketClient.send(testData), "Should throw IOException when connection fails");
////        }
////    }
//}
