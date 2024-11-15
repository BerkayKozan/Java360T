/**
 * The {@code Initiator} class simulates a conversation starter in a client-server architecture.
 * It establishes a connection with a responder, sends messages, and processes responses.
 *
 * <p>This class is designed to work with a {@link Responder} counterpart. It uses a 
 * {@link ServerSocket} to listen for connections from the responder, exchanges messages, and 
 * provides feedback to the user.
 *
 * <p>The class uses ANSI color codes to enhance console output readability, and 
 * limits the conversation to {@value #MAX_MESSAGES} messages.
 * 
 * @author Berkay Kozan
 */

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

            startConversation(in, out, scanner);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void startConversation(BufferedReader in, BufferedWriter out, Scanner scanner) throws IOException {
        System.out.println("Initiator connected to responder.");
        int messageCount = 0;

        while (messageCount < MAX_MESSAGES) {
            String message;
            do {
                System.out.print(YELLOW + "Initiator: " + RESET);
                message = scanner.nextLine().trim();
                if (message.isEmpty()) {
                    System.out.println(RED + "Message cannot be empty. Please enter a valid message." + RESET);
                }
            } while (message.isEmpty() && scanner.hasNextLine());
            messageCount++;

            // Break the loop if there is no more input
            if (message == null || message.isEmpty())
                break;
            out.write(message + "\n");
            out.flush();

            String receivedMessage = in.readLine();
            if (receivedMessage == null)
                break;

            if (receivedMessage.startsWith("confirmation:")) {
                System.out.println(GREEN + "Responder's " + receivedMessage + RESET);
                System.out.println("Waiting for responder to send next message...");
                receivedMessage = in.readLine();
                if (receivedMessage == null)
                    break;
            }

            if (!receivedMessage.startsWith("confirmation:")) {
                System.out.println(CYAN + "Responder: " + receivedMessage + RESET);

                String confirmationText = "confirmation: Initiator received: " + receivedMessage
                        + " | Initiator has sent " + messageCount + " message(s).";
                out.write(confirmationText + "\n");
                out.flush();
            }
        }

        System.out.println("Initiator finished after " + MAX_MESSAGES + " messages.");
    }
}