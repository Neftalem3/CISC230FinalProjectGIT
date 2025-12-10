package final_project;
import java.util.*;
import java.io.*;
/*
* File: GameDriver.java
* Author: Zuefeng Xiong 
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/8/2025
*
* Description:	Class to use as parent for the normal and time-based modes of the memory match game. This will implement the necessary methods whilst creating abstract
* 				methods for implementations specific to the child classes.
* 
*/
public abstract class MemoryGame implements Showable {

    ArrayList<Question> questionBank;

    public MemoryGame() {
        questionBank = new ArrayList<>();
        loadTrueFalse("TFQuestions.txt");
        loadMultipleChoice("MCQuestions.txt");
    }

    //Loads True/False questions
    void loadTrueFalse(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));

            while (scan.hasNextLine()) {
                String category = scan.nextLine().trim();
                if (category.isEmpty()) continue; // skip extra blank lines

                String question = scan.nextLine().trim();
                String trueText = scan.nextLine().trim();
                String falseText = scan.nextLine().trim();
                String correct = scan.nextLine().trim();

                // store using your Question class
                String[] options = { trueText, falseText };

                questionBank.add(new Question(
                    category,
                    Question.Type.TRUE_FALSE,
                    question,
                    options,
                    correct,
                    ""   // explanation for the answer (if needed)
                ));
            }
            scan.close();

        } catch (Exception e) {
            System.out.println("ERROR loading True/False file: " + e.getMessage());
        }
    }

    // Loads Multiple-Choice questions
    void loadMultipleChoice(String filename) {
        try {
            Scanner scan = new Scanner(new File(filename));

            while (scan.hasNextLine()) {
                String category = scan.nextLine().trim();
                if (category.isEmpty()) continue;

                String question = scan.nextLine().trim();

                String A = scan.nextLine().trim().substring(3); // remove "A. "
                String B = scan.nextLine().trim().substring(3);
                String C = scan.nextLine().trim().substring(3);
                String D = scan.nextLine().trim().substring(3);

                String correct = scan.nextLine().trim();

                String[] options = { A, B, C, D };

                questionBank.add(new Question(
                    category,
                    Question.Type.MULTIPLE_CHOICE,
                    question,
                    options,
                    correct,
                    ""
                ));
            }
            scan.close();

        } catch (Exception e) {
            System.out.println("ERROR loading MC file: " + e.getMessage());
        }
    }
    

}