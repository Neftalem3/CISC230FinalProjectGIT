package final_project;

import javafx.animation.KeyFrame;
import javafx.animation.PauseTransition;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.animation.KeyFrame;
import javafx.scene.layout.*;
import javafx.scene.control.*;
import javafx.scene.layout.Pane;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.util.Timer;


/*
* File: QuestionDisplay
* Author: Matthew Ramirez
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/7/2025
*
* Description:	Class for storing all questions and checking if the user has the correct answer. Selects a current question randomly for then removes it from the
* 				ArrayList to avoid repeating the question.
* 
*/
public class QuestionDisplay extends MemoryGame{
	//int timeRemaining = 5;
	
    private Scene new_Scene;
    private Label CorrectIncorrect = new Label("");
    private Runnable runner;
    private String question="";
    private String addQuestion ="";
    private Label QuestionDisplay;
    private RadioButton buttonA;
    private RadioButton buttonB;
    private RadioButton buttonC;
    private RadioButton buttonD;
    private ToggleGroup choices = new ToggleGroup();
    private Question currentQuestion;
    private VBox verticleDisplay;
    private StackPane frame;
    private Stage thisStage;
    private String whichModefinal;

    public QuestionDisplay( String whichMode, Stage ps, Question newQuestion, Scene newScene, Runnable runner ) {
    	thisStage=ps;
    	currentQuestion=newQuestion;
    	whichModefinal = whichMode;
    	String correct =String.valueOf(currentQuestion.getCorrectAnswer());
    	Label CorrectAnswer = new Label(correct);
    	String [] answers = currentQuestion.getAnswers();
    	int amountOfQuestions = answers.length;
    	
    	if (amountOfQuestions==2) {
    		buttonA = new RadioButton(answers[0]);
    		buttonB = new RadioButton(answers[1]);
    		buttonC = new RadioButton("");
    		buttonD = new RadioButton("");

    		buttonC.setVisible(false);
    		buttonD.setVisible(false);
    		
    	}
    	else if (amountOfQuestions>2) {
    		buttonA = new RadioButton(answers[0]);
    		buttonB = new RadioButton(answers[1]);
    		buttonC = new RadioButton(answers[2]);
    		buttonD = new RadioButton(answers[3]);
    	}
    	buttonA.setToggleGroup(choices);
    	buttonB.setToggleGroup(choices);
    	buttonC.setToggleGroup(choices);
    	buttonD.setToggleGroup(choices);

    	
    	buttonA.setOnAction(this::buttonControls);
    	buttonB.setOnAction(this::buttonControls);
    	buttonC.setOnAction(this::buttonControls);
    	buttonD.setOnAction(this::buttonControls);

    	
    	addQuestion =currentQuestion.getQuestionString();
    	question=question+addQuestion;
    	QuestionDisplay = new Label(question);
    	
        new_Scene = newScene;
        int X = 200;
        verticleDisplay=new VBox(buttonA, buttonB, buttonC, buttonD, CorrectIncorrect);
        verticleDisplay.setSpacing(20);
        verticleDisplay.setAlignment(Pos.CENTER);
        QuestionDisplay.setFont(Font.font(null, FontWeight.BOLD, 12));
        VBox fullVerticleDisplay = new VBox(QuestionDisplay, verticleDisplay);
        fullVerticleDisplay.setSpacing(100);
        
        
        frame =  new StackPane(fullVerticleDisplay);
        
        fullVerticleDisplay.setAlignment(Pos.CENTER);
        new_Scene = new Scene(frame, 800, 800);
        this.runner = runner;
    }

    // Example method to trigger the callback
    public void triggerReturn() {
        runner.run();
    }

    
	@Override
	public Scene getScene() {
		
		return new_Scene;
	}
	
	
	public void buttonControls(ActionEvent choice) {
		char buttonChoice ;
		if (currentQuestion.getCorrectAnswer()=='T' || currentQuestion.getCorrectAnswer()=='F') {
			if (buttonA.isSelected()) {
				buttonChoice = 'T';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(10);
					//GameDriver.game.updateScore();
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
		
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}

			}
			
			else if (buttonB.isSelected()) {
				buttonChoice = 'F';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(10);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}

			}
		}
			
		if (currentQuestion.getCorrectAnswer()=='A' || currentQuestion.getCorrectAnswer()=='B' || currentQuestion.getCorrectAnswer()=='C' || currentQuestion.getCorrectAnswer()=='D') {	
			if (buttonA.isSelected()) {
				buttonChoice = 'A';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(buttonChoice);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}	

			}
		
			else if (buttonB.isSelected()) {
				buttonChoice = 'B';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(10);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}

			}
			else if (buttonC.isSelected()) {
				buttonChoice = 'C';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(10);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}

			}
			else if (buttonD.isSelected()) {
				buttonChoice = 'D';
				if (buttonChoice==currentQuestion.getCorrectAnswer()) {	
					CorrectIncorrect.setText("Correct!");
					GameDriver.player.addPoints(10);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();
				}
				else if (buttonChoice!=currentQuestion.getCorrectAnswer()) {
					CorrectIncorrect.setText("Incorrect!");
					GameDriver.player.deductPoints(5);
					PauseTransition delay = new PauseTransition(Duration.seconds(3));
					delay.setOnFinished(event -> thisStage.setScene(GameDriver.game.getScene()));
					delay.play();

				}
				
			}
		
		
		
			
		
		}
		
		if (whichModefinal=="Hard") {
			Hard.resumeTimer();
		}
		triggerReturn();

	}

	@Override
	public void askQuestion() {
		// TODO Auto-generated method stub
		
	}



	//@Override
	//public void updateScore() {
		// TODO Auto-generated method stub
		
	//}
	

}
