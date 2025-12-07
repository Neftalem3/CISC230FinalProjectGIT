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
import java.util.ArrayList;
import javafx.scene.Scene;
import javafx.scene.layout.*;

public class Leaderboard implements Showable {
	private ArrayList<Player> players;
	
	/// Constructor for the leaderboard that simply initializes the players list
	public Leaderboard() {
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
	
	/// Method for creating and returning a scene to be shown via the Javafx GUI.
	public Scene getScene() {
		// Code still needs to be implemented as to create a scene
		Pane pane = new Pane();
		Scene scene = new Scene(pane,400,400);
		return scene;
	}
}
