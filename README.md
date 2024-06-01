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
- [Customization](#customization)
- [Contributing](#contributing)
- [License](#license)

## Installation

To install the SelfON package, clone the repository to your local machine:

```sh
git clone https://github.com/nyutiz/SelfONTerminal.git
```

Navigate to the project directory:

```sh
cd SelfON
```

Compile the Java files:

```sh
javac -d bin src/nyutiz/*.java
```

Run the application:

```sh
java -cp bin nyutiz.Main
```

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

## Customization

You can customize the maximum print length of the console output by modifying the `maxPrintLength` variable in the `Main` class.

## Contributing

We welcome contributions! Please fork the repository and create a pull request with your changes.
