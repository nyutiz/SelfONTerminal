# SelfON

SelfON is a console-based application designed to help users manage their passwords, tasks, and general database entries efficiently. This README provides comprehensive instructions on how to install, use, and customize the SelfON application, along with a detailed overview of its features.

![Demo](https://github.com/nyutiz/SelfONTerminal/blob/main/demo.png)

## Table of Contents
- [Installation](#installation)
- [Usage](#usage)
  - [Main Menu](#main-menu)
  - [Loading a Database](#loading-a-database)
  - [Password Manager](#password-manager)
  - [Task Manager](#task-manager)
  - [Database Manager](#database-manager)
    - [Creating an Account](#creating-an-account)
    - [Editing an Account](#editing-an-account)
    - [Showing Accounts](#showing-accounts)
    - [Sorting the Database](#sorting-the-database)
    - [Creating a Database](#creating-a-database)
- [Database Structure](#database-structure)
- [Printer Class](#printer-class)
  - [Usage](#usage-1)
    - [Available Methods](#available-methods)
    - [Example Usage](#example-usage)
  - [Customization](#customization)
- [Program Description](#program-description)
- [Contributing](#contributing)
- [Ideas](#ideas)

Run the jar with

```sh
java -jar SelfONTerminal.jar
```

## Program Description

The `Main` class is the entry point of the SelfON application. It provides a console-based interface for managing databases, password credentials, and tasks. Below is an in-depth look at how the program functions and how to use it.

### How It Works

1. **Initialization:** When the application starts, it initializes by setting the maximum print length and displaying the main menu using the `Printer` class to format the output.

2. **Main Menu:** The main menu presents the user with options to:
   - Load a database or configuration
   - Access the password manager
   - Access the task manager
   - Access the general database manager

3. **Loading Databases:**
   - **Option 0:** Load an existing database by providing the file path. This database is then stored in a map (`dbMap`) for easy access. 
   - **Option 1:** Load a configuration file that can specify paths for password and task manager databases.

4. **Password Manager:**
   - **Register Credentials:** Add new credentials to the password database.
   - **Show a Credential:** Retrieve and display specific credentials.
   - **Edit Credentials:** Modify existing credentials in the database.
   - **Delete a Credential:** Remove credentials from the database.

5. **Task Manager:**
   - **Add Task:** Create a new task with various descriptors such as priority and status.
   - **Show all Tasks:** Display all tasks in the database.
   - **Show a Task:** Retrieve and display a specific task.
   - **Edit Task:** Modify existing tasks in the database.
   - **Sort Task:** Sort tasks based on specified descriptors.
   - **Delete a Task:** Remove tasks from the database.

6. **Database Manager:**
   - **Select Current Database:** Choose which database to operate on.
   - **Create Account:** Add new accounts to the database.
   - **Edit Account:** Modify existing accounts in the database.
   - **Show Accounts:** Display all accounts in the database.
   - **Delete Account:** Remove accounts from the database.
   - **Sort Database:** Sort the database based on specified descriptors.
   - **Create Database:** Create a new database with specified fields.

7. **User Interaction:** The application uses a `Scanner` object to read user input and execute commands accordingly, providing a responsive and interactive console experience.

### Using the Program

1. **Starting the Application:**
   - Run the application using `java -jar SelfONTerminal.jar`.
   - You will be greeted with the main menu.

2. **Loading a Database or Configuration:**
   - Select option `0` from the main menu.
   - Choose to load a database or a configuration file.
   - Provide the file path when prompted.

3. **Using the Password Manager:**
   - Ensure a password database is loaded.
   - Select option `1` from the main menu.
   - Follow the prompts to register, show, edit, or delete credentials.

4. **Using the Task Manager:**
   - Ensure a task manager database is loaded.
   - Select option `2` from the main menu.
   - Follow the prompts to add, show, edit, sort, or delete tasks.

5. **Using the Database Manager:**
   - Ensure a general database is loaded or create a new one.
   - Select option `3` from the main menu.
   - Follow the prompts to select, create, edit, show, sort, or delete accounts.

# Printer Class

The `Printer` class provides various static methods for formatted printing to the console. It allows for text alignment, padding, and formatted output to enhance the visual presentation of console-based applications. 

## Usage

### Available Methods

1. **printWith(String text, String start, String end)**
   - **Description:** Prints a string with specified start and end characters, padding with `═` to fit the maximum print length.
   - **Parameters:**
     - `text`: The main text to be printed.
     - `start`: The starting characters of the formatted string.
     - `end`: The ending characters of the formatted string.
   - **Example:**
     ```java
     Printer.printWith("Welcome to SelfON", "╔", "╗");
     ```

2. **printWith(String text)**
   - **Description:** Prints a string with padding and appropriate ending characters based on the last printed string.
   - **Parameters:**
     - `text`: The main text to be printed.
   - **Example:**
     ```java
     Printer.printWith("Main Menu");
     ```

3. **printWith(String text, String space)**
   - **Description:** Prints a string with custom spacing string padding.
   - **Parameters:**
     - `text`: The main text to be printed.
     - `space`: The string used for padding.
   - **Example:**
     ```java
     Printer.printWith("Options", " ");
     ```

4. **printLarge(String text)**
   - **Description:** Prints a large text that exceeds the `maxPrintLength`, breaking it into smaller chunks that fit the defined length.
   - **Parameters:**
     - `text`: The large text to be printed.
   - **Example:**
     ```java
     Printer.printLarge("This is a very long text that needs to be broken into multiple lines to fit within the maximum print length.");
     ```

5. **print(String text)**
   - **Description:** Prints the text without a newline, prefixed by "╠ ".
   - **Parameters:**
     - `text`: The text to be printed.
   - **Example:**
     ```java
     Printer.print("Loading database...");
     ```

6. **println(String text)**
   - **Description:** Prints the text with a newline, formatted with padding and specific end characters depending on the input.
   - **Parameters:**
     - `text`: The text to be printed.
   - **Example:**
     ```java
     Printer.println("Database loaded successfully");
     ```

7. **printf(String format, Object... args)**
   - **Description:** Prints formatted text using the `String.format` method, padding it to fit the maximum print length.
   - **Parameters:**
     - `format`: The format string.
     - `args`: Arguments referenced by the format specifiers in the format string.
   - **Example:**
     ```java
     Printer.printf("Hello %s", "World");
     ```

8. **setMaxPrintLength(int maxPrintLength)**
   - **Description:** Sets the maximum print length for the console output.
   - **Parameters:**
     - `maxPrintLength`: The maximum length for printed lines.
   - **Example:**
     ```java
     Printer.setMaxPrintLength(80);
     ```

### Example Usage

To utilize the `Printer` class in your project:

1. **Print a centered welcome message with borders:**
    ```java
    Printer.printWith("Welcome to SelfON", "╔", "╗");
    ```

2. **Print a large text broken into multiple lines:**
    ```java
    Printer.printLarge("This is a very long text that needs to be broken into multiple lines to fit within the maximum print length.");
    ```

3. **Print a formatted string:**
    ```java
    Printer.printf("Hello %s", "World");
    ```

## Customization

You can customize the maximum print length by modifying the `maxPrintLength` variable:

```java
Printer.setMaxPrintLength(80);
```

This customization allows you to adjust the console output width to better fit your display requirements.

## Contributing

We welcome contributions! Please fork the repository and create a pull request with your changes.

## Ideas

- Connect to the browser with an extension to save passwords.
- Implement encryption for passwords.
- Provide backup for data deletion.
- Develop an algorithm that automatically fills empty fields of a task.
- Add more customization to the database, such as specifying the separator.
- Consider simplifying the visual appearance.

SelfON is a versatile tool for managing personal data securely and efficiently, designed with a user-friendly console interface. Whether you need to keep track of passwords, manage tasks, or organize other data, SelfON provides a robust solution tailored to your needs.
