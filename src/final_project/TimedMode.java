package final_project;

/*
* File: 
* Author: Daniel Grib
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/10/2025
*
* Description:	Class for determining the timed mode for this game.
* 				This is the hard difficulty for the game.
* 
*/

import java.util.Random;

import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.util.Duration;
import javafx.stage.Stage;
import javafx.scene.layout.*;
import javafx.geometry.Pos;

public class TimedMode extends MemoryGame {
	
	private int totalTime;
	private int timeRemaining;
	private int score;
	
	private Timeline timer;
	private boolean timerRunning;
	
	private Random rand = new Random();
	
	private Label timerLabel;
	private Label scoreLabel;
	
	private Scene gameScene;
	
	public TimedMode(Scene scene, Label timerLabel, Label scoreLabel, int totalTimeSeconds) {
        super();
        this.gameScene = scene;
        this.timerLabel = timerLabel;
        this.scoreLabel = scoreLabel;

        this.totalTime = totalTimeSeconds;
        this.timeRemaining = totalTimeSeconds;

        this.score = 0;
        this.timerRunning = false;
    }
	
	@Override
	public Scene getScene() {
		return gameScene;
	}
	
	public void start() {
		timerRunning = true;

        timer = new Timeline(
            new KeyFrame(Duration.seconds(1), e -> {
                timeRemaining--;

                updateTimerLabel();

                if (timeRemaining <= 0) {
                    timer.stop();
                    timerRunning = false;
                    endGame();
                }
            })
        );

        timer.setCycleCount(Timeline.INDEFINITE);
        timer.play();
	}
	
	private void updateTimerLabel() {
        timerLabel.setText("Time Left: " + timeRemaining + "s");
    }
	
	
    public void askQuestion(Question q) {
        showMCQuestionPopup(q);
    }
	
	public void showMCQuestionPopup(Question q) {
		Stage stage = new Stage();
		stage.setTitle("Question");
		
		VBox layout = new VBox(15);
        layout.setAlignment(Pos.CENTER);

        Label questionText = new Label(q.getQuestionText());
        questionText.setWrapText(true);

        String[] ops = q.getOptions();

        ToggleGroup group = new ToggleGroup();

        RadioButton A = new RadioButton("A) " + ops[0]);
        RadioButton B = new RadioButton("B) " + ops[1]);
        RadioButton C = new RadioButton("C) " + ops[2]);
        RadioButton D = new RadioButton("D) " + ops[3]);

        A.setToggleGroup(group);
        B.setToggleGroup(group);
        C.setToggleGroup(group);
        D.setToggleGroup(group);

        Button submit = new Button("Submit");

        submit.setOnAction(e -> {
            RadioButton selected = (RadioButton) group.getSelectedToggle();
            if (selected != null) {
                String answerLetter = selected.getText().substring(0,1); // "A", "B", "C", or "D"
                checkAnswer(q, answerLetter);
                stage.close();
            }
        });

        layout.getChildren().addAll(questionText, A, B, C, D, submit);

        Scene scene = new Scene(layout, 400, 300);
        stage.setScene(scene);
        stage.show();
	}
	
	private void checkAnswer(Question q, String userLetter) {

        Answer ans = new Answer(userLetter);

        if (ans.isCorrect(q)) {
            int base = 100;
            int bonus = Math.max(0, timeRemaining / 3);

            score += (base + bonus);
            updateScoreLabel();

            showAlert("Correct!", "You earned 100 points + " + bonus + " bonus!");
        } else {
            showAlert("Incorrect", "Better luck on the next one!");
        }
    }
	
	private void updateScoreLabel() {
        scoreLabel.setText("Score: " + score);
    }
	
	private void showAlert(String title, String msg) {
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(msg);
        alert.showAndWait();
    }
	
	private void endGame() {
        showAlert("Time's Up!", "Final Score: " + score);
        // may transition to leaderboard scene here
    }
}
