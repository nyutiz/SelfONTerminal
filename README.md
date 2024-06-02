# SelfON

SelfON is a console-based application that provides functionalities for managing a password manager, a task manager, and a database manager. This README explains how to use the SelfON package and the available functionalities.

![Demo](https://github.com/nyutiz/SelfONTerminal/blob/main/example.png)

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
- [Function Descriptions](#function-descriptions)
- [Customization](#customization)
- [Contributing](#contributing)
  
## Installation

To install the SelfON package, clone the repository to your local machine:

```sh
git clone https://github.com/nyutiz/SelfONTerminal.git
```

Navigate to the project directory:

```sh
cd main/src/main/java/nyutiz
```

Compile the Java files:

```sh
javac *.java
```

Run the application:

```sh
java Main
```

(Or Use Maven)

## Usage

### Main Menu

Upon running the application, you will see the following main menu:

```
╔════════════════════════ Welcome to SelfON ═════════════════════════╗
╠ 0 : Load Database                                                  ║
╠ 1 : Password Manager                                               ║
╠ 2 : Task Manager                                                   ║
╠ 3 : Database Manager                                               ║
╠════════════════════════════════════════════════════════════════════╝
```

### Loading a Database

To load an existing database, select option `0` and provide the path to the database file when prompted:

```
╠═ 0 : Load Database
╠═ Please provide the Database path : 
```

### Password Manager

To access the Password Manager, select option `1`. Ensure that a database is loaded before accessing the Password Manager. (not implemented yet)

### Task Manager

To access the Task Manager, select option `2`. Ensure that a database is loaded before accessing the Task Manager. (not implemented yet)

### Database Manager

To access the Database Manager, select option `3`. Ensure that a database is loaded before accessing the Database Manager.

#### Creating an Account

To create a new account, select option `1` from the Database Manager menu. You will be prompted to provide values for the account fields.

#### Editing an Account

To edit an existing account, select option `2` from the Database Manager menu. You will need to provide the ID of the account you wish to edit and the new values for the fields.

#### Showing Accounts

To display all accounts in the database, select option `3` from the Database Manager menu.

#### Sorting the Database

To sort the database by a specific descriptor, select option `4` from the Database Manager menu and choose the descriptor you wish to sort by.

#### Creating a Database

To create a new database, select option `5` from the Database Manager menu. Provide a name for the new database and the data fields.

## Database Structure

The database is structured with descriptors that define the fields of each account. Each account has a unique ID and additional fields as defined by the descriptors.

## Function Descriptions

1. **printWith(String text, String start, String end)**
   - **Purpose:** Formats and prints a text string with specific start and end characters, padding the text with equal amounts of a specific character to fit a defined maximum length.
   - **Parameters:**
     - `text`: The main text to be printed.
     - `start`: The starting characters of the formatted string.
     - `end`: The ending characters of the formatted string.
   - **Example:** If `maxPrintLength` is 50, `start` is "╠", `end` is "╣", and `text` is "Hello", the output will be a string with "Hello" centered and padded with "═" to fit the total length of 50 characters.

2. **printWith(String text)**
   - **Purpose:** Similar to the previous function but only adds default starting and ending characters based on the context provided by `lastPrint`.
   - **Parameters:**
     - `text`: The main text to be printed.
   - **Example:** Prints the `text` padded with "═" and ending with "╗" or "╣" based on the previous print's ending character.

3. **printWith(String text, String space)**
   - **Purpose:** Prints the text padded with a custom spacing string.
   - **Parameters:**
     - `text`: The main text to be printed.
     - `space`: The string used for padding.
   - **Example:** If `space` is " ", the function pads the text with spaces and prints it with the appropriate ending character.

4. **printLarge(String text)**
   - **Purpose:** Prints a large text that exceeds the `maxPrintLength`, breaking it into smaller chunks that fit the defined length.
   - **Parameters:**
     - `text`: The large text to be printed.
   - **Example:** If `text` is a long string, it will be broken into segments that each fit within the maximum print length and printed line by line.

5. **print(String text)**
   - **Purpose:** Prints the text without a newline, prefixed by "╠ ".
   - **Parameters:**
     - `text`: The text to be printed.
   - **Example:** Prints the `text` starting with "╠ ".

6. **println(String text)**
   - **Purpose:** Prints the text with a newline, formatted with padding and specific end characters depending on the input.
   - **Parameters:**
     - `text`: The text to be printed.
   - **Example:** Depending on the `text` content (e.g., "╝", "╣", "╗"), it prints different formatted strings. For regular text, it pads the text to fit the `maxPrintLength`.

7. **printf(String format, Object... args)**
   - **Purpose:** Prints formatted text using the `String.format` method, padding it to fit the maximum print length.
   - **Parameters:**
     - `format`: The format string.
     - `args`: Arguments referenced by the format specifiers in the format string.
   - **Example:** If `format` is "Hello %s", and `args` is ["World"], it prints "Hello World" padded to fit the maximum length.

### Usage Example

For example, to print a centered welcome message with borders, you can use:
```java
printWith("Welcome to SelfON", "╔", "╗");
```
This would produce a formatted string with "Welcome to SelfON" centered and padded with "═", starting with "╔" and ending with "╗".

For printing a large text broken into multiple lines:
```java
printLarge("This is a very long text that needs to be broken into multiple lines to fit within the maximum print length.");
```

To print a formatted string:
```java
printf("Hello %s", "World");
```
This would produce "Hello World" formatted and padded to fit the maximum print length.

These functions are used to enhance the console-based interface of SelfON, making the text output visually structured and easy to read.

## Customization

You can customize the maximum print length of the console output by modifying the `maxPrintLength` variable in the `Main` class. You can modify the Database by modifying the first line of the file (ex Id;Name;Password or Credit;Username;Email;....)

## Contributing

We welcome contributions! Please fork the repository and create a pull request with your changes.
