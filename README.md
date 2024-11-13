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
3. **Run the Classes**: Right-click `Initiator.java` or `Responder.java` and select **Run 'Initiator.main()'** or **Run 'Responder.main()'**.

#### Using Eclipse

1. Import the project as an **Existing Maven Project**.
2. Wait for dependencies to load.
3. Run `Initiator.java` or `Responder.java` as a **Java Application**.

## Running the Project from Command Line

This project supports two different types of runs: **Single Process** and **Multi-Process**.

### Single Process Run

To run the single process version, only `SingleProcess.java` needs to be executed.

1. **Run the SingleProcess Class**:

   ```bash
   mvn exec:java -Dexec.mainClass="com.example.SingleProcess"
   ```
### Multi Process Run

For the multi-process version, Initiator and Responder should be run separately.

### 1. Compile the Project

```bash
mvn clean package
```

### 2. Run the Initiator Class

```bash
mvn exec:java -Dexec.mainClass="com.example.Initiator"
```

### 3. Run the Responder Class
```bash
mvn exec:java -Dexec.mainClass="com.example.Responder"
```
