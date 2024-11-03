# LINK TO THE GITHUB REPO
https://github.com/philippzhuravlev/CDIO-2.git

# System Requirements for Java Console Game

## Minimum Requirements

- **OS**: Windows 7, macOS 10.10, or Linux (Ubuntu 16.04)
- **Processor**: 1 GHz single-core CPU
- **Memory**: 512 MB RAM
- **Graphics**: Integrated graphics (no specific GPU required)
- **Storage**: 100 MB free space

## Recommended Requirements

- **OS**: Windows 10, macOS 10.15, or Linux (Ubuntu 20.04)
- **Processor**: 1.5 GHz dual-core CPU
- **Memory**: 1 GB RAM
- **Graphics**: Integrated graphics (no specific GPU required)
- **Storage**: 100 MB free space

## Additional requirements depend on the method used to run the game:

- **Java Development Kit (JDK)**: Version 17 or higher (if compiling and building from source)
- **JRE** (Java Runtime Environment) for running the `.jar` file, if the `.exe` file is not being used.
- **Maven** for compiling and building the project from source.
   https://maven.apache.org/install.html

# .EXE AND .JAR
There will be both a `.exe` and a `.jar` file in the target folder.
You can run the `.exe` file or open a terminal in the `target` folder and type
   ```bash
   java -jar CDIO.2.1.0.jar
   ```

# Executing, compiling and installing the game

To execute the game you should either use the `.exe` or follow the steps below to make a `.jar`.

The source code is stored in a Git repository. Follow these steps to clone and set up the project in your development environment:

1. **Clone the Repository**:
   - Use the following Git command to clone the repository:
     ```bash
     git clone https://github.com/philippzhuravlev/CDIO-2.git
     ```

2. **Open Project in IDE**:
   - Launch your preferred IDE (e.g., Visual Studio Code, IntelliJ IDEA).
   - Open the cloned repository folder in the IDE.

3. **Ensure Dependencies are Installed**:
   - In the terminal, navigate to the project root and use Maven to download dependencies:
     ```bash
     mvn install
     ```

2. **Compiling and Building the Project**

   To compile and package the game, use Maven:
   
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

# Project Structure Overview

## Package: `com.cdio2`
   This package contains the core classes for the CDIO-2 project, which implements a dice game.

### Classes in `com.cdio2`

1. **Class:** `Dice.java`
   - **Responsibility:** Represents a die (or dice if it handles multiple dice) in the game. This class contains logic to simulate rolling a die.

2. **Class:** `Game.java`
   - **Responsibility:** Acts as the main game logic controller, handling the game's flow and rules.

3. **Class:** `Main.java`
   - **Responsibility:** The main entry point for the application. This class contains the `main` method, which initializes and starts the game.

4. **Class:** `Player.java`
   - **Responsibility:** Represents a player in the game, containing information about the player’s score and gold.

5. **Class:** `Tiles.java`
   - **Responsibility:** Represents the tiles on a game board. This class defines different types of tiles and their effects on players.

## Resource Files
   - **`messages_da.properties` & `messages_en.properties`**:
     - These files contain localization strings for Danish (`da`) and English (`en`). They will be used to display text in the user's preferred language.

## Test Package: `test.java.com.cdio2`
   Contains unit tests to verify the functionality of the main game classes.

   - **Class:** `GameTest.java`
     - **Responsibility:** Tests various functionalities of the class to ensure the game logic works as expected.
