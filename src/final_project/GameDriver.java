package final_project;
/*
* File: GameDriver.java
* Author: Connor King
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/7/2025
*
* Description:	
* 
*/
import java.util.ArrayList;
import java.util.Random;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.event.ActionEvent;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class GameDriver extends Application {
	Scene menu; // menu scene stored globally; can be accessed later
	RadioButton easyButton;
	RadioButton mediumButton;
	RadioButton hardButton;
	TextField playerField;
	Stage stage; // used for accessing the Javafx stage globally (rather than just in the start method)
	public void start(Stage ps) {
		Text titleText = new Text("Sustainability Match Game");
		Text subtitleText = new Text("A memory matching game involving sustainability trivia");
		Text difficultyText = new Text("Select a difficulty:");
		Text playerNameText = new Text("Enter player name:");
		playerField = new TextField();
		playerField.setMaxWidth(100);
		
		// Create buttons and set their handlers
		Button startButton = new Button("Start Game");
		Button infoButton = new Button("Info");
		
		startButton.setOnAction(this :: startButtonClick);
		infoButton.setOnAction(this :: infoButtonClick);
		
		// Create title text as bold and with a larger font size
		FontWeight weight = FontWeight.BOLD;
		titleText.setFont(Font.font("times",weight,24));
		
		// Create and add the difficulty radio buttons to a ToggleGroup
		ToggleGroup difficultyGroup = new ToggleGroup();
		
		easyButton = new RadioButton("Easy");
		mediumButton = new RadioButton("Medium");
		hardButton = new RadioButton("Hard");
		
		easyButton.setToggleGroup(difficultyGroup);
		mediumButton.setToggleGroup(difficultyGroup);
		hardButton.setToggleGroup(difficultyGroup);
		
		easyButton.setSelected(true); // easy button selected by default
		
		HBox playerNameBox = new HBox(); // HBox with text and textfield for entering the player name
		playerNameBox.setSpacing(10);
		playerNameBox.setAlignment(Pos.CENTER);
		playerNameBox.getChildren().addAll(playerNameText, playerField);
		
		HBox buttonBox = new HBox(); // HBox for the two buttons (start and info)
		buttonBox.setSpacing(10);
		buttonBox.setAlignment(Pos.CENTER);
		buttonBox.getChildren().addAll(startButton,infoButton);
		
		// create VBox for storing difficulty text, difficulty buttons, and the buttonBox (containing start and info buttons)
		VBox difficultyBox = new VBox();
		difficultyBox.setSpacing(10);
		difficultyBox.setAlignment(Pos.CENTER);
		difficultyBox.getChildren().addAll(playerNameBox,difficultyText,easyButton,mediumButton,hardButton,buttonBox);
		
		// VBox coontaining title and subtitle text
		VBox topTextBox = new VBox();
		topTextBox.setSpacing(10);
		topTextBox.setAlignment(Pos.CENTER);
		topTextBox.getChildren().addAll(titleText,subtitleText);
		
		// Box containing the entire menu window (used for creating the menu scene)
		VBox menuBox = new VBox();
		menuBox.setSpacing(30);
		menuBox.setAlignment(Pos.TOP_CENTER);
		menuBox.getChildren().addAll(topTextBox, difficultyBox);
		
		// Create menu scene and set as active scene
		menu = new Scene(menuBox,400,300);
		
		stage = ps; // create alias of ps (the primary stage) for use elsewhere
		
		ps.setScene(menu);
		ps.setTitle("Sustainability Match Game");
		ps.show();
	}

	/// Method for handling when the user clicks the start button (unfinished)
	public void startButtonClick(ActionEvent event) {

	    String playerName = playerField.getText().trim();
	    if (playerName.isEmpty()) {
	        Alert alert = new Alert(AlertType.WARNING);
	        alert.setHeaderText(null);
	        alert.setTitle("Missing Name");
	        alert.setContentText("Please enter a player name before starting the game.");
	        alert.showAndWait();
	        return;
	    }

	    // Score label shown during the game
	    Label scoreLabel = new Label("Score: 0");
	    Label timerLabel = new Label("Time: 60s");

	    MemoryGame game;

	    if (easyButton.isSelected()) {
	        game = new Easy(stage, scoreLabel);
	    }
	    else if (mediumButton.isSelected()) {
	        game = new Medium(stage, scoreLabel);
	    }
	    else {
	        game = new Hard(stage, scoreLabel, timerLabel);
	    }

	    // Switch to the MemoryGame scene
	    stage.setScene(game.getScene());
	}

	
	/// Method for showing game info when the user clicks the info button (unfinished)
	public void infoButtonClick(ActionEvent event) {
		Alert alert = new Alert(AlertType.INFORMATION);
		alert.setHeaderText(null);
		alert.setTitle("Game Information");
		alert.setContentText("insert game information here");
		alert.showAndWait(); // Wait until user clicks "OK" before continuing
	}
	
	//public static void main (String[] args) {
	//	launch(args);
	//}

}