package final_project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;

import java.util.HashMap;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javafx.geometry.Pos;


/*
* File: MemoryGame.java
* Author: Zuefeng Xiong and Daniel Grib
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/10/2025
*
* Description: This class generates the grid itself and
* 				does most of the function controls.
* 				Easy, Medium, and Hard are all classes that alter the MemoryGame class.
*/

public abstract class MemoryGame implements Showable {
	
	// Scene and layout components
    protected Scene gameScene; // Scene containing game board
    protected GridPane grid; // 4x4 card layout
    protected BorderPane root; // Container for grid and top bar

    // Card State Tracking
    protected Rectangle firstCard = null; // First flip
    protected Rectangle secondCard = null; // Second flip

    // Maps each Rectangle card object to the value it represents
    protected HashMap<Rectangle, Integer> cardValues = new HashMap<>(); // 1-8
    protected HashMap<Rectangle, Text> symbols = new HashMap<>(); // Card Symbol
    
    protected int[] shuffledValues = new int[16]; // 8 pairs (1-8 twice)

    // Constructor
    public MemoryGame() {
        generateCardPairs();
        buildGridUI();
    }
    
    // Allows child classes to place items on the top of the game scene.
    //	Made for score and timer labels
    public void setTop(Node n) {
        root.setTop(n);
    }

    // ==========================
    // Grid and Symbol Generation
    // ==========================

    // Generates 16 card values, where 1-8 appears twice,
    // Shuffles cards for random card placement.
    private void generateCardPairs() {
        // Values 1–8 twice (pairs)
        for (int i = 0; i < 16; i++) {
            shuffledValues[i] = (i / 2) + 1;
        }

        // Shuffle
        Random rand = new Random();
        for (int i = 15; i > 0; i--) {
            int j = rand.nextInt(i + 1);
            int temp = shuffledValues[i];
            shuffledValues[i] = shuffledValues[j];
            shuffledValues[j] = temp;
        }
    }
    
    // Returns Unicode symbol for each card value
    private String getSymbolForValue(int v) {
        switch (v) {
            case 1: return "♻";   // recycling
            case 2: return "🌱";   // seedling
            case 3: return "🚲";   // bicycle
            case 4: return "🐝";   // bee
            case 5: return "💡";   // light bulb
            case 6: return "🦋";   // butterfly
            case 7: return "🌧";   // rain
            case 8: return "🍎";   // apple
            default: return "?";
        }
    }

    /*
     * Builds the visual game board consisting of:
     	* a GridPane with 16 cards
     	* each card is a StackPane containing a Rectangle + hidden symbol
     	* the board is placed inside a BorderPane (root)
     */
    protected void buildGridUI() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);

        int index = 0;

        // Create 4x4 grid of cards
        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {

            		// Base card rectangle
                Rectangle card = new Rectangle(100, 100);
                card.setFill(Color.DARKGRAY);
                card.setStroke(Color.BLACK);

                // Assign card value (1-8)
                int val = shuffledValues[index];
                cardValues.put(card, val);

                card.setOnMouseClicked(e -> handleCardClick(card));

                // Stacks symbol over card
                StackPane cardPane = new StackPane();
                cardPane.getChildren().add(card);

                // Create symbol text (hidden at start)
                Text symbol = new Text(getSymbolForValue(val));
                symbol.setVisible(false);
                symbol.setFont(Font.font("System", FontWeight.BOLD, 60));

                cardPane.getChildren().add(symbol);

                // Tracks which symbol belongs to which card
                symbols.put(card, symbol);
                
                grid.add(cardPane, col, row);
                index++;
            }
        }

        root = new BorderPane();
        root.setCenter(grid);

        gameScene = new Scene(root, 600, 600);
    }

    @Override
    public Scene getScene() {
        return gameScene;
    }

    // ============================
    // Card Flip and Matching Logic
    // ============================

    // Handles the logic when the user clicks a card.
    // Stores 2 selected cards and checks for a match.
    private void handleCardClick(Rectangle card) {
    	
    		// First card selected
        if (firstCard == null) {
            firstCard = card;
            reveal(card);
            return;
        }
        // Second card selected (different card)
        else if (secondCard == null && card != firstCard) {
            secondCard = card;
            reveal(card);
            checkMatch();
        }
    }

    // Reveals a card by changing color and showing symbol
    protected void reveal(Rectangle card) {
        card.setFill(Color.LIGHTBLUE);
        symbols.get(card).setVisible(true);
    }

    // Hides a card by returning to the original color and hiding symbol
    protected void hide(Rectangle card) {
        card.setFill(Color.DARKGRAY);
        symbols.get(card).setVisible(false);
    }

    // Compares two selected cards and either asks a question or hides the pair
    private void checkMatch() {
        int v1 = cardValues.get(firstCard);
        int v2 = cardValues.get(secondCard);

        if (v1 == v2) {
            askQuestion();

            firstCard = null;
            secondCard = null;
        } else {
            // not a match → hide both after short delay
            Timer t = new Timer();
            t.schedule(new TimerTask() {
                @Override
                public void run() {
                    javafx.application.Platform.runLater(() -> {
                        hide(firstCard);
                        hide(secondCard);
                        firstCard = null;
                        secondCard = null;
                    });
                }
            }, 600);
        }
    }

    public abstract void askQuestion();
}
