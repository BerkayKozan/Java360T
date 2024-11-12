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
            System.out.println("Waiting for initiator to send the first message...");

            int messageCount = 0;

            // Start conversation
            while (messageCount < MAX_MESSAGES) {
                // Wait for message from initiator
                String receivedMessage = in.readLine();
                if (receivedMessage == null) break; // Initiator disconnected

                if (receivedMessage.startsWith("confirmation:")) {
                    // Handle confirmation message
                    System.out.println(GREEN + "Initiator's " + receivedMessage + RESET);
                    System.out.println("Waiting for initiator to send next message...");
                    receivedMessage = in.readLine();
                    if (receivedMessage == null) break; // Initiator disconnected
                } 
                if (!receivedMessage.startsWith("confirmation:")) {
                    // Handle actual message
                    System.out.println(CYAN + "Initiator: " + receivedMessage + RESET);

                    // Send confirmation back to initiator
                    String confirmationText = "confirmation: Responder received: " + receivedMessage + " | Responder has sent " + (messageCount) + " message(s).";
                    out.write(confirmationText + "\n");
                    out.flush();
                    //System.out.println(GREEN + "Responder sent " + confirmationText + RESET);
                }
                    // Prompt user to enter a response message
                    System.out.print(YELLOW + "Responder: " + RESET);
                    String message;
                    do {
                        message = scanner.nextLine().trim();
                        if (message.isEmpty()) {
                            System.out.println(RED + "Message cannot be empty. Please enter a valid message." + RESET);
                        }
                    } while (message.isEmpty());
                    messageCount++;

                    // Send response message to initiator
                    out.write(message + "\n");
                    out.flush();
            }

            System.out.println("Responder finished after " + MAX_MESSAGES + " messages.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}