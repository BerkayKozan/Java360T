/**
 * The {@code Responder} class simulates a responder in a client-server architecture.
 * It establishes a connection with an initiator, receives messages, and sends responses.
 *
 * <p>This class is designed to work with a {@link Initiator} counterpart. It connects to the 
 * initiator via a {@link Socket}, exchanges messages, and provides feedback to the user.
 *
 * <p>The class uses ANSI color codes to enhance console output readability and 
 * limits the conversation to {@value #MAX_MESSAGES} messages.
 * 
 * <p>Usage:
 * <ul>
 *   <li>The responder connects to the initiator on the specified {@code HOST} and {@code PORT}.</li>
 *   <li>Receives messages from the initiator and sends confirmations back.</li>
 *   <li>Sends user-inputted responses back to the initiator.</li>
 * </ul>
 * 
 * @author Berkay Kozan
 */

package com.example;

import java.io.*;
import java.net.Socket;
import java.util.Scanner;

public class Responder {

    private static final String HOST = "localhost";
    private static final int PORT = 12345;
    private static final int MAX_MESSAGES = 10;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        try (Socket socket = new Socket(HOST, PORT);
                BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
                Scanner scanner = new Scanner(System.in)) {

            System.out.println(RED + "Responder connected to initiator." + RESET);
            startConversation(in, out, scanner);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startConversation(BufferedReader in, BufferedWriter out, Scanner scanner) throws IOException {
        System.out.println("Waiting for initiator to send the first message...");
        int messageCount = 0;

        while (messageCount < MAX_MESSAGES) {
            String receivedMessage = in.readLine();
            if (receivedMessage == null)
                break; // Initiator disconnected

            if (receivedMessage.startsWith("confirmation:")) {
                System.out.println(GREEN + "Initiator's " + receivedMessage + RESET);
                System.out.println("Waiting for initiator to send next message...");
                receivedMessage = in.readLine();
                if (receivedMessage == null)
                    break; // Initiator disconnected
            }

            if (!receivedMessage.startsWith("confirmation:")) {
                System.out.println(CYAN + "Initiator: " + receivedMessage + RESET);

                String confirmationText = "confirmation: Responder received: " + receivedMessage
                        + " | Responder has sent " + messageCount + " message(s).";
                out.write(confirmationText + "\n");
                out.flush();
            }

            System.out.print(YELLOW + "Responder: " + RESET);
            String message;
            do {
                message = scanner.hasNextLine() ? scanner.nextLine().trim() : "";
                if (message.isEmpty()) {
                    System.out.println(RED + "Message cannot be empty. Please enter a valid message." + RESET);
                }
            } while (message.isEmpty() && scanner.hasNextLine());

            if (message.isEmpty())
                break; // End if no more input

            messageCount++;
            out.write(message + "\n");
            out.flush();
        }

        System.out.println("Responder finished after " + messageCount + " messages.");
    }
}