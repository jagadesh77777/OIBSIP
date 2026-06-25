# Online Examination System

## Description

The **Online Examination System** is a Java console-based application that simulates an online exam. Users can log in with valid credentials, update their profile details, take a timed multiple-choice examination, and receive their final score after submission.

## Features

* User login authentication
* Profile update (name and password)
* 60-second timed examination
* Multiple-choice questions (MCQs)
* Automatic submission when the timer expires
* Instant score calculation
* Logout functionality

## Technologies Used

* Java
* Object-Oriented Programming (OOP)
* Scanner Class
* Timer & TimerTask

## Project Structure

```
OnlineExamination/
├── OnlineExamination.java
└── README.md
```

## Default Login Credentials

| Username | Password |
| -------- | -------- |
| admin    | 1234     |

> You can change the password using the **Update Profile** option after logging in.

## How to Run

1. Clone the repository.
2. Open the project in VS Code, IntelliJ IDEA, or Eclipse.
3. Compile the program:

   ```bash
   javac OnlineExamination.java
   ```
4. Run the program:

   ```bash
   java OnlineExamination
   ```

## Sample Menu

```
===== ONLINE EXAMINATION SYSTEM =====

1. Update Profile
2. Start Examination
3. Logout
```

## Concepts Used

* Classes and Methods
* Conditional Statements
* Loops
* User Input (Scanner)
* Timer and TimerTask
* Variables and Basic Authentication

## Future Enhancements

* Store user credentials in a database
* Add more questions with randomized order
* Save exam results automatically
* Display correct answers after submission
* Support multiple users and admin panel

## Author

**Kalisetti Jagadeshwar Rao**

