# Library Management System

## Introduction

This Library Management System is designed to facilitate the management of books and members in a library. It provides functionalities for both librarians and members, allowing them to perform various operations such as adding/removing books, registering/removing members, issuing/returning books, and calculating fines.

## Features

### For Librarians:
1. **Register a Member**: Add a new member to the library.
2. **Remove a Member**: Remove an existing member from the library.
3. **Add a Book**: Add a new book to the library.
4. **Remove a Book**: Remove an existing book from the library.
5. **View All Members**: Display a list of all registered members along with their borrowed books and any fines.
6. **View All Books**: Display a list of all available books in the library.

### For Members:
1. **List Available Books**: View a list of all available books in the library.
2. **List My Books**: View a list of books borrowed by the member.
3. **Issue Book**: Borrow a book from the library (if no fines are pending and within the borrow limit).
4. **Return Book**: Return a borrowed book and calculate any applicable fines.
5. **Pay Fine**: Pay any pending fines.

## Assumptions

1. I have hardcoded the 6 minimum animals that were needed.
2. The name for each animal is different, I have assumed that.
3. The popularity of attraction is determined by the number of visitors.
4. When the user enters the number of tickets, like if he enters 3 tickets, then I will apply the best special deal he has, even when if there exists a case where his money remained less to buy all the 3 tickets.
5. If a user has basic membership, then If he is going to buy premium membership, then I am charging him 50 rupees (not 30).
6. If a user has a ticket for an attraction and then again buys an attraction, then the visitor will have 2 tickets for the same attraction, meaning he can visit that attraction 2 times.
7. The id of attraction starts from 0.
8. All the attractions are closed initially whenever the user adds a new attraction.
9. The phone number entering should be of 10 digits, and in email also it should contain both @ and . and at least 2 letters after .
10. If a user has premium membership, then he cannot buy basic membership again.
11. If the user enters the wrong discount code, then he will not get the student/senior citizen discount, and if he enters the right code then he will get a discount on both membership and buy tickets.

## Installation

To run this application, you need to have Maven installed on your system. Follow the instructions below to set up and run the application:

### Clone the Repository

1. **git clone**: https://github.com/Akshat22052/Library-Management-System.git
2. **Navigate to the Project Directory**: cd LibraryManagementSystem
3. **Build the Project**: mvn clean 
mvn compile
mvn package
4. **Run the Application**: java -jar target/AP_ass-2-1.0-SNAPSHOT.jar (If any error is coming in this, it means the name of the jar file is changed, so to run instead of AP_ass-2-1.0-SNAPSHOT write the new jar file name.)




```sh
