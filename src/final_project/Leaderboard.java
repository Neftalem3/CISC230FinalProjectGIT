package final_project;
/*
* File: Leaderboard.java
* Author: Connor King, 
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/7/2025
* 
* Description:	
* 
*/
import java.util.Scanner;
import java.util.Collections;
import java.util.ArrayList;

import java.io.FileWriter;
import java.io.IOException;
import java.io.File;
import java.io.FileNotFoundException;

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.stage.Stage;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.control.Button;
import javafx.event.ActionEvent;




public class Leaderboard implements Showable {
	private ArrayList<Player> players; // will be used for sorting past players plus the current player
	private Player currentPlayer;
	
	/// Constructor for the leaderboard that adds the current player and initializes the past players list
	public Leaderboard(Player player) {
		currentPlayer = player;
		players = new ArrayList<Player>();
	}
	
	/// Method for adding a player to the leaderboard
	public void addPlayer(Player player) {
		players.add(player);
	}
	
	/// Getter method for players ArrayList
	public ArrayList<Player> getPlayers() {
		return players;
	}
	
	/// Helper method which loads the past top players from a file along with their scores, using this data to create an ArrayList of players.
	public void loadPastLeaderboard() throws FileNotFoundException{
		File file = new File("leaderboard.txt");
		Scanner scan = new Scanner(file);
		String playerName;
		int points;
		while (scan.hasNextLine()) {
			playerName = scan.nextLine();
			if (playerName.trim().isEmpty())
				break;
			String pointString = scan.nextLine();
			points = Integer.parseInt(pointString.trim());
			Player player = new Player(playerName);
			player.setScore(points);
			players.add(player);
		}
		scan.close();
		
		Collections.sort(players);
	}
	
	public void writeFile() throws IOException {
		
		FileWriter writer = new FileWriter("leaderboard.txt",false);
		for (Player player : players) {
			writer.write(player.getName() + "\n" + player.getScore() + "\n");
		}
		writer.close();
		
	}
	
	/// Method for creating and returning a scene to be shown via the Javafx GUI.
	public Scene getScene() {
		Text playerText = new Text(" Player Name:");
		Text scoreText = new Text(" Score:");
		Text[][] textArray = new Text[11][2];
		Text currentPlayerText = new Text("Current Player: " + currentPlayer);

		Button continueButton = new Button("Continue");
		continueButton.setOnAction(this :: continueClick);
		
		Text titleText = new Text("Leaderboard");
		
		FontWeight weight = FontWeight.BOLD;
		titleText.setFont(Font.font("times",weight,24));
		playerText.setFont(Font.font("times",weight,12));
		scoreText.setFont(Font.font("times",weight,12));
		
		// Create array of Text objects used for displaying text on the leaderboard within the gridpane
		textArray[0][0] = playerText;
		textArray[0][1] = scoreText;
		for (int i = 1; i < textArray.length; i++) {
			textArray[i][0] = new Text(" " + i + ". ");
			textArray[i][1] = new Text();
		}
		// Add current player if they make it on the leaderboard
		if (players.size() < 10) {
			players.add(currentPlayer);
		}
		else if (Collections.min(players).getScore() <= currentPlayer.getScore()) {
			players.removeLast();
			players.add(currentPlayer);
		}
		Collections.sort(players);
		
		
		
		int playerNum = 1;
		for (Player player : players) {
			String nameText = textArray[playerNum][0].getText() + player.getName();
			textArray[playerNum][0].setText(nameText);
			textArray[playerNum][1].setText(" " + player.getScore());
			playerNum++;
		}

		
		GridPane gridpane = new GridPane();
		gridpane.setAlignment(Pos.CENTER);
		
		// Create gridpane leaderboard rectangles and text
		for (int i = 0; i < 11; i++) {
			Rectangle rectangle = new Rectangle(150,30);
			rectangle.setFill(Color.LIGHTSLATEGRAY);
			rectangle.setStrokeWidth(3);
			gridpane.add(rectangle, 0, i);
			
			rectangle = new Rectangle(50,30);
			rectangle.setFill(Color.LIGHTSLATEGRAY);
			rectangle.setStrokeWidth(3);
			gridpane.add(rectangle, 1, i);
			
			gridpane.add(textArray[i][0], 0, i);
			gridpane.add(textArray[i][1], 1, i);
				
		}
		// HBox for storing currentPlayerText and continueButton side by side
		HBox hbox = new HBox();
		hbox.setSpacing(20);
		hbox.setAlignment(Pos.CENTER);
		hbox.getChildren().addAll(currentPlayerText, continueButton);
		
		VBox root = new VBox();
		root.setSpacing(20);
		root.setAlignment(Pos.CENTER);
		root.getChildren().addAll(titleText,gridpane,hbox);
		
		Scene scene = new Scene(root,300,500);
		return scene;
	}
	
	public void continueClick (ActionEvent event) {
		GameDriver.getPrimaryStage().setScene(GameDriver.getMenuScene());
		try {
			writeFile();
		}
		catch (IOException e) {
			System.out.println("File error: Cannot find file :(");
		}
		
	}
}
