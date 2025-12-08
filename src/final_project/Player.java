package final_project;
/*
* File: Player.java
* Author: Neftalem Sida
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/8/2025
*
* Description:	Tracks the player's name and current score.
* Implements Comparable to allow  sorting for the Leaderboard.
*
* 
*/
public class Player {
	//player variable types
    private String name;
    private int score;

    //Sets the name and starts score at 0
    public Player(String name) {
        this.name = name;
        this.score = 0;
    }
   
    //Getter method for name
    public String getName() {
        return name;
    }
    //Getter method for score
    public int getScore() {
        return score;
    }
    
    // Use this to update score during gameplay
    public void setScore(int score) {
        this.score = score;
    }

    // Helper method to add points 
    public void addPoints(int points) {
        this.score += points;
    }
    
    // Helper method to wrong answers
    public void deductPoints(int points) {
        this.score -= points;
        if (this.score < 0) this.score = 0; //No negative scores
    }


    // toString method for Leaderboard
    @Override
    public String toString() {
        return name + ": " + score + " pts";
    }
}

