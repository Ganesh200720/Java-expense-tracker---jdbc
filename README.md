# Expense Tracker (Java + JDBC + MySQL)

## Project Overview

This is a console-based Expense Tracker application developed in Java.
The program allows users to add, view, and delete expenses while storing data in a MySQL database.

## Features

* Add new expense
* View all expenses
* Delete expense by ID
* Data stored in MySQL database
* Secure SQL queries using PreparedStatement

## Technologies Used

* Java
* JDBC
* MySQL

## Database Structure

Table: expenses

| Column   | Type    |
| -------- | ------- |
| id       | INT     |
| category | VARCHAR |
| amount   | DOUBLE  |
| date     | DATE    |

## How to Run

Compile the program

javac -cp ".;mysql-connector-j-9.6.0.jar" ExpenseTrackerDB.java

Run the program

java -cp ".;mysql-connector-j-9.6.0.jar" ExpenseTrackerDB

## Author

Ganesh
