### System Requirements for Java Console Game

#### Minimum Requirements

- **OS**: Windows 7, macOS 10.10, or Linux (Ubuntu 16.04)
- **Processor**: 1 GHz single-core CPU
- **Memory**: 512 MB RAM
- **Graphics**: Integrated graphics (no specific GPU required)
- **Storage**: 100 MB free space

#### Recommended Requirements

- **OS**: Windows 10, macOS 10.15, or Linux (Ubuntu 20.04)
- **Processor**: 1.5 GHz dual-core CPU
- **Memory**: 1 GB RAM
- **Graphics**: Integrated graphics (no specific GPU required)
- **Storage**: 100 MB free space

Additional requirements depend on the method used to run the application:

- **Java Development Kit (JDK)**: Version 17 or higher (if compiling and building from source)
- **JRE** (Java Runtime Environment) for running the `.jar` file, if the `.exe` file is not being used.
- **Maven** for compiling and building the project from source.

### Executing, compiling and installing the game

To execute the game you should either use the `.exe` or follow the steps below to make a `.jar`.

The source code is stored in a Git repository. Follow these steps to clone and set up the project in your development environment:

1. **Clone the Repository**:
   - Use the following Git command to clone the repository:
     ```bash
     git clone <repository-url>
     ```
   - Replace `<repository-url>` with the actual URL of the Git repository.

2. **Open Project in IDE**:
   - Launch your preferred IDE (e.g., Visual Studio Code, IntelliJ IDEA).
   - Open the cloned repository folder in the IDE. The folder structure should resemble the one shown in the project layout, with directories for `src`, `resources`, and `test`.

3. **Ensure Dependencies are Installed**:
   - In the terminal, navigate to the project root and use Maven to download dependencies:
     ```bash
     mvn install
     ```

### 3. Compiling and Building the Project

To compile and package the application, use Maven:

1. **Compile the Code**:
   ```bash
   mvn compile

   This will compile all source files located in the `src/main/java` directory.

2. **Run Tests**:
   ```bash
   mvn test
   This command will run all test cases located in src/test/java

3. **Build the Project**:
    - Generate a `.jar` file by running:
      ```bash
      mvn package
   - the output `.jar` file will be located in the `target` folder


