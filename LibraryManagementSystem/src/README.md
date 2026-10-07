# Library Management System

A simple desktop-based Library Management System developed using **Java** and **Java Swing** for the **CST 124-2 Object Oriented Programming** final assessment at **Uva Wellassa University of Sri Lanka**.

## 📌 Project Overview

This application helps a university librarian manage books, members, and borrowing activities. It provides a simple graphical user interface to perform all essential library operations. The main focus of this project is to demonstrate Object-Oriented Programming (OOP) concepts such as classes, objects, encapsulation, constructors, methods, object relationships, and collections.

## ✨ Features

### 📚 Book Management
- Add a new book (Book ID, Title, Author, Category)
- View all books in a table
- Search for a book by Book ID, Title, or Author
- Remove a book (only if it is available)
- Prevent duplicate Book IDs
- Validate empty fields

### 👤 Member Management
- Add a new member (Member ID, Name, Contact Number)
- View all members in a table
- Search for a member by Member ID, Name, or Contact Number
- Prevent duplicate Member IDs
- Validate empty fields

### 🔄 Borrow Book
- Select a member and an available book
- Check if the book is available
- Change book status to "Borrowed"
- Create a borrowing record
- Prevent borrowing an already borrowed book
- Display appropriate error messages

### ↩️ Return Book
- Select a borrowed book
- Update book status to "Available"
- Update the borrowing record as returned
- Prevent returning an already available book
- Display appropriate messages

### 📊 Dashboard
- Total number of books
- Number of available books
- Number of borrowed books
- Total number of members
- Automatically updates when operations are performed

## 🧠 OOP Concepts Used

- **Classes and Objects**: `Book`, `Member`, `BorrowRecord`, `Library`, `LibraryGUI`
- **Encapsulation**: Private attributes with public getters and setters
- **Constructors**: Used to initialize objects
- **Methods**: Business logic implemented in `Library` class
- **Object Relationships**: `Library` has `ArrayList` of `Book`, `Member`, `BorrowRecord`; `BorrowRecord` has a `Book` and a `Member`
- **Collections**: `ArrayList` used to store books, members, and borrow records
- **Event Handling**: `ActionListener` used for button actions
- **Java Swing**: GUI built with `JFrame`, `JPanel`, `JLabel`, `JButton`, `JTextField`, `JTable`, `JScrollPane`, `JOptionPane`, `CardLayout`

## 🏗️ Class Structure

| Class | Description |
|---|---|
| `Book` | Represents a book with ID, title, author, category, and availability status |
| `Member` | Represents a library member with ID, name, and contact number |
| `BorrowRecord` | Represents a borrowing transaction with book, member, and returned status |
| `Library` | Manages all books, members, and borrow records. Contains business logic |
| `LibraryGUI` | Main GUI class. Handles all Swing components and user interactions |
| `Main` | Entry point of the application |

## 🛠️ Technologies Used

- **Java** (JDK 8 or above)
- **Java Swing** (for GUI)
- **ArrayList** (for data storage)
- No database required (data is stored in memory)

## 🚀 How to Run

1. Clone the repository:
   ```bash
   git clone https://github.com/your-username/library-management-system.git