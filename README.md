# Simple Tic-Tac-Toe game on java with servlets and jsp

This project implements a classic Tic-Tac-Toe game, developed in Java using Servlets and JSP (JavaServer Pages). The application is a traditional web application, running on an application server (such as Apache Tomcat) and interacting with the user through web pages.

## Features
* Complete two-player Tic-Tac-Toe game.
* Simple and intuitive web interface.
* Game logic managed on the server-side using Servlets.
* Presentation and user interaction handled by JSP.
* Uses jQuery for client-side JavaScript functionalities.

### Component Details
* **`Field.java`**: Most likely manages the current state of the Tic-Tac-Toe board, such as placing signs and checking for win or draw conditions.
* **`InitServlet.java`**: Responsible for setting up a new game session, initializing the game board and other necessary variables.
* **`LogicServlet.java`**: This is the "brain" of the game. It receives player moves, updates the board, checks if there's a winner or if the game ended in a draw, and prepares the response for the client.
* **`RestartServlet.java`**: Allows users to start a new game without reloading the entire application.
* **`Sign.java`**: Defines the "X" and "O" signs used in the game.
* **`index.jsp`**: The main page that displays the game board and interaction buttons. It receives data from Servlets and renders it dynamically.
* **`main.css`**: Contains styling rules to make the game interface look appealing.
* **`jquery-3.6.0.min.js`**: A popular JavaScript library that simplifies DOM manipulation, event handling, and making AJAX calls.

## Requirements

* Java Development Kit (JDK) 8 or newer
* Apache Maven (for building the project)
* A Servlet API and JSP compatible application server (e.g., Apache Tomcat 8 or newer)
