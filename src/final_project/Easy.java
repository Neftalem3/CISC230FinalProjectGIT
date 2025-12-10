package final_project;

import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.stage.Stage;
import java.io.File;

public class Easy extends MemoryGame {

    private Answer answerManager;
    private int score = 0;
    private Label scoreLabel;

    private Stage primaryStage;  // needed to swap scenes

    public Easy(Stage primaryStage, Label scoreLabel) {
        super(); // builds grid
        this.primaryStage = primaryStage;
        this.scoreLabel = scoreLabel;

        answerManager = new Answer();
        loadTFQuestions();
    }

    private void loadTFQuestions() {
        try {
            answerManager.loadQuestions(
                new File("TFQuestions.txt"),
                Question.Type.TRUE_FALSE
            );
        } catch (Exception e) {
            System.out.println("Error loading TF questions: " + e.getMessage());
        }
    }

    @Override
    public Scene getScene() {
        return gameScene;
    }

    @Override
    public void askQuestion() {

        // Pick a TF question
        answerManager.selectQuestion(Question.Type.TRUE_FALSE);
        Question q = answerManager.getQuestion();

        // Build QuestionDisplay scene
        QuestionDisplay qd = new QuestionDisplay(primaryStage, gameScene);

        // Switch to the question scene
        primaryStage.setScene(qd.getScene());
    }
}
