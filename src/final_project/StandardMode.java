package final_project;

import javafx.scene.Scene;
import java.util.Collections;
import java.util.Scanner;

/**
*
* File: GameDriver.java
* Author: Zuefeng Xiong 
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/9/2025
*StandardMode:
* Players answer questions at their own pace.
*/
public class StandardMode extends MemoryGame {

    Scanner input = new Scanner(System.in);

    // This runs the normal, untimed game
    public void startGame() {

        // Shuffle the questions so every playthrough is different
        Collections.shuffle(questionBank);

        int score = 0;

        System.out.println("=== STANDARD MODE ===");

        // Loop through every question in the bank
        for (int i = 0; i < questionBank.size(); i++) {

            Question q = questionBank.get(i);

            // Print question number and text
            System.out.println("\nQuestion " + (i + 1));
            System.out.println(q.getQuestionText());

            // Multiple choice
            if (q.getType() == Question.Type.MULTIPLE_CHOICE) {

                System.out.println("A) " + q.getOptions()[0]);
                System.out.println("B) " + q.getOptions()[1]);
                System.out.println("C) " + q.getOptions()[2]);
                System.out.println("D) " + q.getOptions()[3]);

                System.out.print("Your answer: ");
                String answer = input.nextLine().trim().toUpperCase();

                if (answer.equals(q.getCorrectOption().toUpperCase())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect.");
                }
            }

            // True/False
            else if (q.getType() == Question.Type.TRUE_FALSE) {

                System.out.println("A) True");
                System.out.println("B) False");

                System.out.print("Your answer: ");
                String answer = input.nextLine().trim().toUpperCase();

                // Convert A/B to True/False strings
                if (answer.equals("A")) answer = "TRUE";
                if (answer.equals("B")) answer = "FALSE";

                if (answer.equals(q.getCorrectOption().toUpperCase())) {
                    System.out.println("Correct!");
                    score++;
                } else {
                    System.out.println("Incorrect.");
                }
            }
        }

        // Final score
        System.out.println("\n=== END OF GAME ===");
        System.out.println("Final Score: " + score + " / " + questionBank.size());
    }
    
	public Scene getScene() {
		return null;
	}

}