<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JavaPlayer360T Project Documentation</title>
    <style>
        body { font-family: Arial, sans-serif; line-height: 1.6; }
        h1, h2, h3 { color: #333; }
        code, pre { background-color: #f5f5f5; padding: 4px; border-radius: 4px; }
        .code-block { background-color: #f5f5f5; padding: 10px; border-radius: 5px; }
        .command { color: #333; font-weight: bold; }
        .section { margin-top: 20px; }
    </style>
</head>
<body>
    <h1>JavaPlayer360T</h1>
    <p>JavaPlayer360T is a Java-based project using Maven for managing dependencies and building the project. The main classes, <code>Initiator</code> and <code>Responder</code>, establish a simple client-server communication model using sockets.</p>

    <div class="section">
        <h2>Requirements</h2>
        <ul>
            <li><strong>Java</strong>: JDK 8 or higher</li>
            <li><strong>Maven</strong>: To build and run the project</li>
            <li><strong>JUnit and Mockito</strong>: For running unit tests (dependencies are managed through Maven)</li>
        </ul>
    </div>

    <div class="section">
        <h2>Project Structure</h2>
        <p>The main classes are located in <code>src/main/java/com/example</code>:</p>
        <ul>
            <li><code>Initiator.java</code>: The client/server class that initiates communication.</li>
            <li><code>Responder.java</code>: The server/client class that responds to <code>Initiator</code>.</li>
        </ul>
        <p>The unit tests are in <code>src/test/java/com/example</code>:</p>
        <ul>
            <li><code>InitiatorTest.java</code>: Unit tests for <code>Initiator</code>.</li>
            <li><code>ResponderTest.java</code>: Unit tests for <code>Responder</code>.</li>
        </ul>
    </div>

    <div class="section">
        <h2>Getting Started</h2>

        <h3>1. Clone the Repository</h3>
        <pre class="code-block"><code class="command">git clone https://github.com/your-username/JavaPlayer360T.git
cd JavaPlayer360T</code></pre>

        <h3>2. Install Dependencies</h3>
        <pre class="code-block"><code class="command">mvn clean install</code></pre>

        <h3>3. Open the Project in an IDE</h3>
        <p>To run and test the code, open the project in an IDE that supports Maven projects, such as IntelliJ IDEA or Eclipse.</p>

        <h4>Using IntelliJ IDEA</h4>
        <ol>
            <li><strong>Open</strong> the project by selecting the <code>pom.xml</code> file.</li>
            <li><strong>Wait for Maven</strong> to download dependencies.</li>
            <li><strong>Run the Classes</strong>: Right-click <code>Initiator.java</code> or <code>Responder.java</code> and select <strong>Run 'Initiator.main()'</strong> or <strong>Run 'Responder.main()'</strong>.</li>
        </ol>

        <h4>Using Eclipse</h4>
        <ol>
            <li>Import the project as an <strong>Existing Maven Project</strong>.</li>
            <li>Wait for dependencies to load.</li>
            <li>Run <code>Initiator.java</code> or <code>Responder.java</code> as a <strong>Java Application</strong>.</li>
        </ol>
    </div>

    <div class="section">
        <h2>Running the Project from the Command Line</h2>

        <h3>1. Compile the Project</h3>
        <pre class="code-block"><code class="command">mvn clean package</code></pre>

        <h3>2. Run the Initiator Class</h3>
        <pre class="code-block"><code class="command">mvn exec:java -Dexec.mainClass="com.example.Initiator"</code></pre>

        <h3>3. Run the Responder Class</h3>
        <pre class="code-block"><code class="command">mvn exec:java -Dexec.mainClass="com.example.Responder"</code></pre>
    </div>

    <div class="section">
        <h2>Running Unit Tests</h2>
        <p>Unit tests are written using <strong>JUnit 5</strong> and <strong>Mockito</strong>. They are located in <code>src/test/java/com/example</code>.</p>

        <h3>Dependencies for Testing</h3>
        <p>To use JUnit and Mockito, ensure these dependencies are in your <code>pom.xml</code> file:</p>
        <pre class="code-block"><code>&lt;dependency&gt;
    &lt;groupId&gt;org.junit.jupiter&lt;/groupId&gt;
    &lt;artifactId&gt;junit-jupiter-engine&lt;/artifactId&gt;
    &lt;version&gt;5.7.0&lt;/version&gt;
    &lt;scope&gt;test&lt;/scope&gt;
&lt;/dependency&gt;
&lt;dependency&gt;
    &lt;groupId&gt;org.mockito&lt;/groupId&gt;
    &lt;artifactId&gt;mockito-core&lt;/artifactId&gt;
    &lt;version&gt;3.6.28&lt;/version&gt;
    &lt;scope&gt;test&lt;/scope&gt;
&lt;/dependency&gt;</code></pre>

        <h3>Run All Tests</h3>
        <pre class="code-block"><code class="command">mvn test</code></pre>

        <h3>Test Descriptions</h3>
        <ul>
            <li><strong>InitiatorTest</strong>: Tests the <code>Initiator</code> class using mocked <code>Socket</code> and I/O streams to verify that messages are correctly sent and received.</li>
            <li><strong>ResponderTest</strong>: Tests the <code>Responder</code> class with similar mocked components, ensuring it behaves as expected when receiving and responding to messages.</li>
        </ul>
    </div>

    <div class="section">
        <h2>Troubleshooting</h2>
        <ul>
            <li><strong>Package Mismatch</strong>: Ensure that your package declarations match the directory structure. The root directory should contain <code>src/main/java/com/example</code> for source files and <code>src/test/java/com/example</code> for tests.</li>
            <li><strong>Network Port Conflicts</strong>: If <code>Initiator</code> or <code>Responder</code> fails to connect, ensure that no other services are using the specified port (<code>12345</code>).</li>
        </ul>
    </div>
</body>
</html>
