# JavaPlayer360T

JavaPlayer360T is a Java-based project using Maven for managing dependencies and building the project. The main classes, `Initiator` and `Responder`, establish a simple client-server communication model using sockets.

## Requirements

- **Java**: JDK 8 or higher
- **Maven**: To build and run the project
- **JUnit and Mockito**: For running unit tests (dependencies are managed through Maven)

## Project Structure

The main classes are located in `src/main/java/com/example`:
- `Initiator.java`: The client/server class that initiates communication.
- `Responder.java`: The server/client class that responds to `Initiator`.

The unit tests are in `src/test/java/com/example`:
- `InitiatorTest.java`: Unit tests for `Initiator`.
- `ResponderTest.java`: Unit tests for `Responder`.

## Getting Started

### 1. Clone the Repository

```bash
git clone https://github.com/your-username/JavaPlayer360T.git
cd JavaPlayer360T
