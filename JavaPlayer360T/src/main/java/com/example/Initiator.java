package com.example;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Initiator {

    private static final int PORT = 12345;
    private static final int MAX_MESSAGES = 10;

    // ANSI color codes
    private static final String RESET = "\u001B[0m";
    private static final String GREEN = "\u001B[32m";
    private static final String CYAN = "\u001B[36m";
    private static final String YELLOW = "\u001B[33m";
    private static final String RED = "\u001B[31m";

    public static void main(String[] args) {
        System.out.println(RED + "Initiator waiting for responder to connect..." + RESET);

        try (ServerSocket serverSocket = new ServerSocket(PORT);
             Socket socket = serverSocket.accept();
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             Scanner scanner = new Scanner(System.in)) {

            System.out.println("Initiator connected to responder.");
            int messageCount = 0;

            // Start conversation
            while (messageCount < MAX_MESSAGES) {
                String message;
                do {
                    // Prompt user to enter a message
                    System.out.print(YELLOW + "Initiator: " + RESET);
                    message = scanner.nextLine().trim();
                    if (message.isEmpty()) {
                        System.out.println(RED + "Message cannot be empty. Please enter a valid message." + RESET);
                    }
                } while (message.isEmpty());
                messageCount++;

                // Send message to responder
                out.write(message + "\n");
                out.flush();

                // Wait for response from responder
                String receivedMessage = in.readLine();
                if (receivedMessage == null) break; // Responder disconnected

                if (receivedMessage.startsWith("confirmation:")) {
                    // Handle confirmation message
                    System.out.println(GREEN + "Responder's " + receivedMessage + RESET);
                    System.out.println("Waiting for responder to send next message...");
                    receivedMessage = in.readLine();
                    if (receivedMessage == null) break; // Responder disconnected

                } 
                if (!receivedMessage.startsWith("confirmation:")) {
                    // Handle actual message
                    System.out.println(CYAN + "Responder: " + receivedMessage + RESET);

                    // Send confirmation back to responder
                    String confirmationText = "confirmation: Initiator received: " + receivedMessage + " | Initiator has sent " + (messageCount) + " message(s).";
                    out.write(confirmationText + "\n");
                    out.flush();
                    //System.out.println(GREEN + "Initiator sent " + confirmationText + RESET);
                }
            }

            System.out.println("Initiator finished after " + MAX_MESSAGES + " messages.");

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}