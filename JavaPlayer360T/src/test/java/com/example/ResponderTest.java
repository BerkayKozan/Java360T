package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class ResponderTest {

    private BufferedReader inMock;
    private BufferedWriter out;
    private BufferedWriter outMock;
    private StringWriter outContent;
    private static final int MAX_MESSAGES = 10;

    @BeforeEach
    void setup() {
        // Mock input for initiator's messages
        inMock = mock(BufferedReader.class);
        // Use StringWriter to capture the output for verification
        outContent = new StringWriter();
        out = new BufferedWriter(outContent);
        // Mock BufferedWriter for verification
        outMock = mock(BufferedWriter.class);
    }

    @Test
    @Timeout(5)
    void testStartConversation_withValidMessages() throws IOException {
        // Simulate responder's input messages
        String input = "Hi\nHow are you?\nI'm good, thanks!\nSee you!\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock initiator messages and confirmations
        when(inMock.readLine())
                .thenReturn("Hello")
                .thenReturn("confirmation: Initiator received: Hello")
                .thenReturn("What’s up?")
                .thenReturn("confirmation: Initiator received: What’s up?")
                .thenReturn("Take care!");

        // Run the conversation
        Responder.startConversation(inMock, out, scanner);

        // Flush any remaining output
        out.flush();

        // Capture and inspect the output
        String result = outContent.toString();
        System.out.println("Captured Output:\n" + result);

        // Verify that the responder sends valid messages and confirmations
        assertTrue(result.contains("confirmation: Responder received: Hello"));
        assertTrue(result.contains("confirmation: Responder received: What’s up?"));
        assertTrue(result.contains("Hi"));
        assertTrue(result.contains("How are you?"));
    }

    @Test
    @Timeout(5)
    void testStartConversation_withEmptyMessages() throws IOException {
        // Simulate empty messages followed by a valid message
        String input = "\n\nHello Initiator\n\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock initiator response
        when(inMock.readLine())
                .thenReturn("Hello there!")
                .thenReturn("confirmation: Initiator received: Hello there!");

        // Run the conversation
        Responder.startConversation(inMock, out, scanner);

        // Flush any remaining output
        out.flush();

        // Capture and inspect the output
        String output = outContent.toString();
        System.out.println("Captured Output:\n" + output);

        // Verify that only the valid message "Hello Initiator" is present
        assertTrue(output.contains("Hello Initiator\n"));
        assertTrue(output.contains("confirmation: Responder received: Hello there!"));
    }

    @Test
    @Timeout(5)
    void testStartConversation_InitiatorDisconnects() throws IOException {
        // Simulate responder message
        String input = "Goodbye Initiator\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock initiator messages and then simulate disconnection
        when(inMock.readLine())
                .thenReturn("Goodbye!")
                .thenReturn(null); // Initiator disconnects after sending "Goodbye!"

        // Run the conversation
        Responder.startConversation(inMock, outMock, scanner);

        // Verify the message was sent before disconnection
        verify(outMock, times(1)).write("Goodbye Initiator\n");
        verify(outMock, times(1)).write(startsWith("confirmation: Responder received:"));
        verify(outMock, times(2)).flush();

    }

    @Test
    @Timeout(5)
    void testStartConversation_MaxMessagesReached() throws IOException {
        // Simulate MAX_MESSAGES valid messages
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 1; i <= MAX_MESSAGES; i++) {
            inputBuilder.append("Responder message ").append(i).append("\n");
        }
        InputStream inputStream = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock initiator responses
        when(inMock.readLine())
                .thenReturn("Initiator message 1")
                .thenReturn("confirmation: Initiator received message 1")
                .thenReturn("Initiator message 2")
                .thenReturn("confirmation: Initiator received message 2")
                .thenReturn("Initiator message 3")
                .thenReturn("confirmation: Initiator received message 3")
                .thenReturn("Initiator message 4")
                .thenReturn("confirmation: Initiator received message 4")
                .thenReturn("Initiator message 5")
                .thenReturn("confirmation: Initiator received message 5")
                .thenReturn("Initiator message 6")
                .thenReturn("confirmation: Initiator received message 6")
                .thenReturn("Initiator message 7")
                .thenReturn("confirmation: Initiator received message 7")
                .thenReturn("Initiator message 8")
                .thenReturn("confirmation: Initiator received message 8")
                .thenReturn("Initiator message 9")
                .thenReturn("confirmation: Initiator received message 9")
                .thenReturn("Initiator message 10");

        // Run the conversation
        Responder.startConversation(inMock, outMock, scanner);

        // Verify exactly MAX_MESSAGES*2 (messages with confirmation) were sent
        verify(outMock, times(MAX_MESSAGES * 2)).write(anyString() + "\n");
        verify(outMock, times(MAX_MESSAGES * 2)).flush();
    }
}