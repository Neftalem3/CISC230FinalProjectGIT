package final_project;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;

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
public class Medium extends MemoryGame {

    private Answer answerManager;
    private int score = 0;
    private Label scoreLabel;

    private Stage primaryStage;  // needed to swap scenes

    public Medium(Stage primaryStage, Label scoreLabel) {
        super(); // builds MemoryGame grid
        this.primaryStage = primaryStage;
        this.scoreLabel = scoreLabel;

        answerManager = new Answer();
        loadMCQuestions();
    }

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

    @Override
    public Scene getScene() {
        return gameScene;
    }

    @Override
    public void askQuestion() {

        // Select a random multiple-choice question
        answerManager.selectQuestion(Question.Type.MULTIPLE_CHOICE);
        Question q = answerManager.getQuestion();

        // Build QuestionDisplay scene (MC version)
        QuestionDisplay qd = new QuestionDisplay(primaryStage, gameScene);

        // switch to question scene
        primaryStage.setScene(qd.getScene());
    }
}