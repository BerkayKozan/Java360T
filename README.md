# JavaPlayer360T

JavaPlayer360T is a Java-based project managed using Maven. It implements a simple messaging system using both a client-server architecture (with sockets) and a single-process simulation. The project is designed to demonstrate communication between two participants (Initiator and Responder) using Java’s I/O and networking capabilities.

## Requirements

- **Java**: JDK 8 or higher
- **Maven**: To build and run the project
- **JUnit and Mockito**: For running unit tests (dependencies are managed through Maven)

## Project Structure

The main classes are located in `src/main/java/com/example`:

- **`SingleProcess.java`**  
  Combines both `Initiator` and `Responder` logic into a single process, enabling a conversation between two players without a network setup.

- **`Initiator.java`**  
  The server class that initiates communication with the `Responder`. Handles sending, receiving, and validating messages.

- **`Responder.java`**  
  The client class that responds to the `Initiator`. Processes incoming messages, sends confirmations, and generates appropriate responses.

- **`Player.java`**  
  A helper class representing a participant in the conversation. Manages player-specific details such as the player's name, message count, and the last received message. Provides methods for sending and receiving messages.

The unit tests are in `src/test/java/com/example`:

- **`SingleProcessTest.java`**  
  Unit tests for the `SingleProcess` class, ensuring the correct flow of messages between two players in a single process.

- **`InitiatorTest.java`**  
  Unit tests for the `Initiator` class, covering scenarios such as valid messages, empty messages, responder disconnections, and maximum message limits.

- **`ResponderTest.java`**  
  Unit tests for the `Responder` class, verifying behavior when handling valid and empty messages, initiator disconnections, and message limits.

- **`PlayerTest.java`**  
  Unit tests for the `Player` class, testing the core functionality such as message sending, receiving, and formatting the player's state.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/BerkayKozan/Java360T
cd Java360T/JavaPlayer360T
```
### 2. Install Dependencies

Ensure all Maven dependencies are installed by running:

```bash
mvn clean install
```
### 3. Open the Project in an IDE

To run and test the code, open the project in an IDE that supports Maven projects, such as IntelliJ IDEA or Eclipse.

#### Using IntelliJ IDEA

1. **Open** the project by selecting the `pom.xml` file.
2. **Wait for Maven** to download dependencies.
3. **Run the Classes**: Right-click `SingleProcess.java`, `Initiator.java` or `Responder.java` and select **Run 'SingleProcess.main()', **Run 'Initiator.main()'** or **Run 'Responder.main()'**.

#### Using Eclipse

1. Import the project as an **Existing Maven Project**.
2. Wait for dependencies to load.
3. Run `SingleProcess.java`, `Initiator.java` or `Responder.java` as a **Java Application**.

## Running the Project from Command Line

This project supports two different types of runs: **Single Process** and **Multi-Process**.

### Single Process Run

To run the single process version, only `SingleProcess.java` needs to be executed.

#### 1. Run the SingleProcess Class:

   ```bash
   mvn exec:java -Dexec.mainClass="com.example.SingleProcess"
   ```
### Multi Process Run

For the multi-process version, Initiator and Responder should be run separately.

#### 1. Compile the Project

```bash
mvn clean package
```

#### 2. Run the Initiator Class

```bash
mvn exec:java -Dexec.mainClass="com.example.Initiator"
```

#### 3. Run the Responder Class
```bash
mvn exec:java -Dexec.mainClass="com.example.Responder"
```

## Running Unit Tests

Unit tests are written using **JUnit 5** and **Mockito**. They are located in `src/test/java/com/example`.

### Dependencies for Testing

To use JUnit and Mockito, ensure these dependencies are in your `pom.xml` file:

```xml
<dependency>
    <groupId>org.junit.jupiter</groupId>
    <artifactId>junit-jupiter-engine</artifactId>
    <version>5.8.1</version>
    <scope>test</scope>
</dependency>
<dependency>
    <groupId>org.mockito</groupId>
    <artifactId>mockito-core</artifactId>
    <version>4.2.0</version>
    <scope>test</scope>
</dependency>
```

### Run All Tests

To run the tests, execute:

```bash
mvn test
```

## Unit Tests Overview

This project includes a comprehensive suite of unit tests to ensure the reliability and correctness of the implemented classes and methods. Below is a summary of the key unit tests:

### `InitiatorTest`
Tests for the `Initiator` class, which simulates a conversation starter in a client-server architecture.

- **`testStartConversation_withValidMessages`**  
  Validates that the `Initiator` handles a conversation correctly when provided with valid input messages. Ensures that confirmations are sent and received appropriately.

- **`testStartConversation_withEmptyMessages`**  
  Tests the behavior when empty messages are entered. Confirms that empty messages are rejected and valid messages proceed as expected.

- **`testStartConversation_ResponderDisconnects`**  
  Simulates the scenario where the responder disconnects during the conversation. Verifies that the initiator gracefully handles this situation without crashing.

- **`testStartConversation_MaxMessagesReached`**  
  Ensures that the conversation terminates after reaching the maximum allowed number of messages (`MAX_MESSAGES`). Validates that all messages and confirmations are exchanged properly.

### `ResponderTest`
Tests for the `Responder` class, which simulates a responder in a client-server architecture.

- **`testStartConversation_withValidMessages`**  
  Confirms that the responder processes valid incoming messages and sends appropriate replies. Validates the exchange of confirmations.

- **`testStartConversation_withEmptyMessages`**  
  Validates that the responder rejects empty messages and only processes valid input.

- **`testStartConversation_InitiatorDisconnects`**  
  Simulates the scenario where the initiator disconnects during the conversation. Verifies that the responder handles the disconnection gracefully.

- **`testStartConversation_MaxMessagesReached`**  
  Ensures that the responder terminates the conversation correctly after reaching the maximum allowed number of messages (`MAX_MESSAGES`).

### `PlayerTest`
Tests for the `Player` class, which represents participants in the conversation.

- **`testSendMessage`**  
  Verifies that the `sendMessage` method correctly increments the message count for a player.

- **`testReceiveMessage`**  
  Ensures that the `receiveMessage` method updates the player's last received message correctly.

- **`testReturnMessage`**  
  Validates the formatting of the string returned by the `returnMessage` method, ensuring it includes the player's name, last message, and message count.

### `SingleProcessTest`
Tests for the `SingleProcess` class, which handles a self-contained conversation between two players.

- **`testHandleMessageExchange_withValidInput`**  
  Validates that a valid message is exchanged between the sender and receiver correctly.

- **`testHandleMessageExchange_withEmptyInput`**  
  Ensures that the `handleMessageExchange` method rejects empty messages and prompts the sender for a valid input.

- **`testConversationCompletes`**  
  Confirms that the conversation completes successfully after the specified number of message exchanges (`MAX_MESSAGES`).

---

These tests provide robust coverage for the critical components of the application, ensuring proper behavior in normal and edge-case scenarios.
