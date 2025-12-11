package final_project;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
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
import javafx.util.Duration;

import java.io.File;

/*
* File: Hard.java
* Author: Daniel Grib
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/10/2025
*
* Description: Class used to describe a hard level of difficulty
* 				of a game. It extends the main GUI, which is
* 				MemoryGame.java
*/

public class Hard extends MemoryGame {
	private final String typeClass = "Hard";
    private Answer answerManager; // loads, stores, and selects questions
    private int score = 0; // player score
    private Label scoreLabel; // label for top bar
    private Button endGame;

    private Stage primaryStage;

    private int totalTime = 120; // 120-second round by default
    private int timeRemaining = 120;
    private Label timerLabel;

    private static Timeline timer;
    private static boolean timerRunning = false;

    // Constructor
    public Hard(Stage primaryStage, Label scoreLabel, Label timerLabel) {
        super();    // builds grid
        this.primaryStage = primaryStage;
        this.scoreLabel = scoreLabel;
        this.timerLabel = timerLabel;

        answerManager = new Answer();
        loadMCQuestions();

        updateScore();
        updateTimer();
        buildTopBar();
        startTimer();
        
        
        
        
    }

    // Loads Multiple Choice questions into Answer class from text file
    private void loadMCQuestions() {
        try {
            answerManager.loadQuestions(
                new File("MCQuestions.txt"),
                Question.Type.MULTIPLE_CHOICE
            );
        } catch (Exception e) {
            System.out.println("Error loading MC questions: " + e.getMessage());
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
        timerLabel.setText("Time: " + timeRemaining);
        timerLabel.setFont(Font.font("System", FontWeight.BOLD, 24));

        topBar.getChildren().addAll(scoreLabel, timerLabel);
        topBar.getChildren().add(endGame);

        this.setTop(topBar); // ← Using the MemoryGame method!
    }

    // -----------------------------
    //  Timer Control
    // -----------------------------
    public void startTimer() {
        timerRunning = true;

        timer = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeRemaining--;
                updateTimer();

                if (timeRemaining <= 0) {
                    timerRunning = false;
                    timer.stop();
                    endGame();
                }
            })
        );

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
    }


    private void stopTimer() {
        if (timer != null) timer.stop();
    }


    public static void resumeTimer() {
        if (timer != null && timerRunning) timer.play();
    }

    // Updates time label
    private void updateTimer() {
        timerLabel.setText("Time: " + Math.max(timeRemaining, 0) + "s");
    }


    // Updates score label
    public void updateScore() {
        scoreLabel.setText("Score: " + score);
    }

    // Returns the active game scene (grid + top bar)
    @Override
    public Scene getScene() {
        return gameScene;
    }
    public void endCurrentGame(ActionEvent end) {
    	primaryStage.setScene(board.getScene());
    	
    }

    /**
     * Called automatically when the user successfully matches two cards.
     *
     * Behavior for Hard mode:
	     * Select a MULTIPLE CHOICE question
	     * Open a QuestionDisplay scene
	     * If the answer is correct, award +100 points
     */
    @Override
    public void askQuestion() {
        if (!timerRunning) return;

        // Pause timer while answering
        stopTimer();

        answerManager.selectQuestion(Question.Type.MULTIPLE_CHOICE);
        Question q = answerManager.getQuestion();

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


        updateScore();
        primaryStage.setScene(qd.getScene());
    }
    
    private void endGame() {
        stopTimer();
    }

	
}