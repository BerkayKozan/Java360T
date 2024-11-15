package com.example;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Timeout;

import java.io.*;
import java.util.Scanner;

import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.*;

class InitiatorTest {

    private BufferedReader inMock;
    private BufferedWriter out;
    private BufferedWriter outMock;
    private StringWriter outContent;
    private static final int MAX_MESSAGES = 10;

    @BeforeEach
    void setup() {
        // Mock input and output streams
        inMock = mock(BufferedReader.class);
        // Use a StringWriter to capture the output for verification
        outContent = new StringWriter();
        out = new BufferedWriter(outContent);
        outMock = mock(BufferedWriter.class);
    }

    @Test
    @Timeout(5)
    void testStartConversation_withValidMessages() throws IOException {
        // Simulate 10 valid messages as input
        String input = "Hello\nHow are you?\nI'm good, thanks!\nHow's it going?\nSee you!\n" +
                "Take care!\nHave a great day!\nStay safe!\nGoodbye!\nFinal message\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock responses from responder
        when(inMock.readLine())
                .thenReturn("confirmation: Responder received message")
                .thenReturn("How are you?")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("I'm good, thanks!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("How's it going?")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("See you!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Take care!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Have a great day!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Stay safe!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Goodbye!")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Final message");

        Initiator.startConversation(inMock, out, scanner);
        // Flush the BufferedWriter to make sure all data is written to the StringWriter
        out.flush();

        // Capture and inspect the output
        String result = outContent.toString();

        // Verify that the output contains expected messages and confirmations
        assertTrue(result.contains("Hello"));
        assertTrue(result.contains("confirmation: Initiator received: How are you?"));
        assertTrue(result.contains("confirmation: Initiator received: Final message"));
    }

    @Test
    @Timeout(5)
    void testStartConversation_withEmptyMessages() throws IOException {
        // Simulate empty messages followed by a valid message
        String input = "\n\nHello Responder\n\n\n\n"; // Added extra lines at the end
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);

        // Mock response from responder
        when(inMock.readLine()).thenReturn("confirmation: Responder received message")
                .thenReturn("Hello back to you!");
        // Run the conversation
        Initiator.startConversation(inMock, out, scanner);

        // Flush any remaining output
        out.flush();

        // Get the output as a string
        String output = outContent.toString();
        System.out.println("Captured Output:\n" + output);

        // Verify that only the valid message "Hello Responder" is present
        assertTrue(output.contains("Hello Responder\n"));
        assertTrue(output
                .contains("confirmation: Initiator received: Hello back to you! | Initiator has sent 1 message(s)"));
    }

    @Test
    @Timeout(5)
    void testStartConversation_ResponderDisconnects() throws IOException {
        // Simulate valid message from initiator
        String input = "Hello Responder\n";
        InputStream inputStream = new ByteArrayInputStream(input.getBytes());
        Scanner scanner = new Scanner(inputStream);
        // Simulate responder disconnecting (readLine returns null)
        when(inMock.readLine()).thenReturn(null);

        Initiator.startConversation(inMock, outMock, scanner);

        // Verify the message was sent before disconnection
        verify(outMock, times(1)).write("Hello Responder\n");
        verify(outMock, times(1)).flush();
    }

    @Test
    @Timeout(5)
    void testStartConversation_MaxMessagesReached() throws IOException {
        // Simulate MAX_MESSAGES valid messages
        StringBuilder inputBuilder = new StringBuilder();
        for (int i = 1; i <= MAX_MESSAGES; i++) {
            inputBuilder.append("Message ").append(i).append("\n");
        }
        InputStream inputStream = new ByteArrayInputStream(inputBuilder.toString().getBytes());
        Scanner scanner = new Scanner(inputStream);
        // Mock responder's responses
        when(inMock.readLine()).thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 1")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 2")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 3")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 4")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 5")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 6")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 7")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 8")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 9")
                .thenReturn("confirmation: Responder received message")
                .thenReturn("Reply to 10");

        Initiator.startConversation(inMock, outMock, scanner);

        // Verify exactly MAX_MESSAGES*2 (messages with confirmation) were sent
        verify(outMock, times(MAX_MESSAGES * 2)).write(anyString() + "\n");
        verify(outMock, times(MAX_MESSAGES * 2)).flush();
    }
}