package final_project;

import javafx.event.ActionEvent;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import java.io.File;

/*
* File: Easy.java
* Author: Daniel Grib
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/10/2025
*
* Description: Class used to describe a easy level of difficulty
* 				of a game. It extends the main GUI, which is
* 				MemoryGame.java
*/

public class Easy extends MemoryGame {
	private final String typeClass = "Easy";
    private Answer answerManager; // loads, stores, and selects questions
    private int score = 0; // player score
    private Label scoreLabel; // label for top bar
    private Stage primaryStage;  // needed to swap scenes
    private Button endGame;

    // Constructor
    public Easy(Stage primaryStage, Label scoreLabel) {
        super(); // builds grid
        this.primaryStage = primaryStage;
        this.scoreLabel = scoreLabel;
        

        answerManager = new Answer();
        loadTFQuestions();
        
        updateScore();
        buildTopBar();
    }

    // Loads True/False questions into Answer class from text file
    private void loadTFQuestions() {
        try {
            answerManager.loadQuestions(
                new File("TFQuestions.txt"),
                Question.Type.TRUE_FALSE
            );
        } catch (Exception e) {
            System.out.println("Error loading TF questions: " + e.getMessage());
        }
    }
    
    // Builds the display bar at the top of the game screen
    private void buildTopBar() {
    	endGame = new Button("End Game");
    	endGame.setOnAction(this::endCurrentGame);
        HBox topBar = new HBox(30);
        topBar.setPadding(new Insets(50, 0, 20, 0));
        topBar.setAlignment(Pos.CENTER);

        scoreLabel.setText("Score: 0");
        scoreLabel.setFont(Font.font("System", FontWeight.BOLD, 24));


        topBar.getChildren().add(scoreLabel);
        topBar.getChildren().add(endGame);

        this.setTop(topBar);
    }

    // Returns the active game scene (grid + top bar)
    @Override
    public Scene getScene() {
        return gameScene;
    }
    
    // Updates score label
    public void endCurrentGame(ActionEvent end) {
    	primaryStage.setScene(board.getScene());
    	
    }
    public void updateScore() {
        scoreLabel.setText("Score: " + GameDriver.player.getScore());
    }

    /**
     * Called automatically when the user successfully matches two cards.
     *
     * Behavior for Easy mode:
	     * Select a TRUE/FALSE question
	     * Open a QuestionDisplay scene
	     * If the answer is correct, award +50 points
     */
    @Override
    public void askQuestion() {

        // Pick a TF question
        answerManager.selectQuestion(Question.Type.TRUE_FALSE);
        Question q = answerManager.getQuestion();

        // Build QuestionDisplay scene
        QuestionDisplay qd = new QuestionDisplay(
        	typeClass,
        primaryStage,
          q,
            gameScene,
            () -> {
                // Callback when returning to the grid
                score += 50;
                
                updateScore();
            }
        );

        // Switch to the question scene
        primaryStage.setScene(qd.getScene());
    }

	
}
