Sustainability Match Game 
A JavaFX memory-matching game that combines classic card flipping mechanics with sustainability trivia. Developed as a final project for CISC 230 (Object-Oriented Programming).

-Game Overview
Players flip cards to find matching symbols. When a match is found, the player must answer a sustainability-related question to earn points.

Features
Three Difficulty Levels:
  *Easy: Standard matching with True/False questions.
  *Medium: Standard matching with Multiple Choice questions.
  *Hard: Multiple Choice questions + a Countdown Timer (Bonus points for speed).
  *Dynamic Leaderboard: Saves and loads top player scores using local file persistence.
  *Randomized Gameplay: Card positions and questions are shuffled every session using custom algorithms.

##Technical Highlights
This project demonstrates core OOP principles:
*Inheritance & Abstraction: The `MemoryGame` abstract class handles the grid generation and card flipping logic. Specific game modes (`Easy`, `Hard`) extend this class to implement unique scoring and question logic.
* Polymorphism: The `askQuestion()` method behaves differently depending on which game mode is currently active.
* Interfaces: Implements `Showable` for scene management and `Comparable` for sorting the leaderboard.
* JavaFX: Uses `Stage`, `Scene`, `GridPane`, and `Timeline` for the graphical interface.

How to Run
1.  **Prerequisites:** Ensure you have Java (JDK) and JavaFX installed.
2.  **Clone the Repo:**
3.  **Setup in Eclipse:**
    * Import the project as a standard Java Project.
    * Ensure the `TFQuestions.txt` and `MCQuestions.txt` files are in the project root directory.
4.  **Run:** Execute `GameDriver.java` to launch the application.

## Project Structure
* `GameDriver.java`: Main entry point and menu system.
* `MemoryGame.java`: Abstract engine handling the 4x4 grid logic.
* `Answer.java`: Manages question loading and randomization.
* `Leaderboard.java`: Handles file I/O for saving high scores.

<p align="center">
  <video src="https://github.com/user-attachments/assets/4b3c8989-5182-4d0c-94ca-3e4499222246" width="80%" controls></video>
</p>

