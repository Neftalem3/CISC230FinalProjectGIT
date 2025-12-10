package final_project;

import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

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
    protected Scene gameScene;
    protected GridPane grid;

    protected Rectangle firstCard = null;
    protected Rectangle secondCard = null;

    protected HashMap<Rectangle, Integer> cardValues = new HashMap<>();
    protected int[] shuffledValues = new int[16]; // 8 pairs (1-8 twice)

    protected BorderPane root;
    
    public MemoryGame() {
        generateCardPairs();
        buildGridUI();
    }
    
    public void setTop(Node n) {
        root.setTop(n);
    }

    // -----------------------
    // GRID + SCENE CREATION
    // -----------------------

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

    protected void buildGridUI() {
        grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setHgap(15);
        grid.setVgap(15);

        int index = 0;

        for (int row = 0; row < 4; row++) {
            for (int col = 0; col < 4; col++) {

                Rectangle card = new Rectangle(100, 100);
                card.setFill(Color.DARKGRAY);
                card.setStroke(Color.BLACK);

                int val = shuffledValues[index];
                cardValues.put(card, val);

                card.setOnMouseClicked(e -> handleCardClick(card));

                grid.add(card, col, row);
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

    // -----------------------
    // CARD FLIP + MATCH LOGIC
    // -----------------------

    private void handleCardClick(Rectangle card) {
        if (firstCard == null) {
            firstCard = card;
            reveal(card);
            return;
        }
        else if (secondCard == null && card != firstCard) {
            secondCard = card;
            reveal(card);
            checkMatch();
        }
    }

    protected void reveal(Rectangle card) {
        card.setFill(Color.LIGHTBLUE);
    }

    protected void hide(Rectangle card) {
        card.setFill(Color.DARKGRAY);
    }

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
