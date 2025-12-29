package final_project;
/*
* File: 
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/7/2025
*
* Description:	Class for storing all questions and checking if the user has the correct answer. Selects a current question randomly for then removes it from the
* 				ArrayList to avoid repeating the question.
* 
*/
import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.io.File;
import java.io.FileNotFoundException;

public class Answer {
	private Question currentQuestion;
	// Original question list not to be altered
	private ArrayList<Question> trueFalseQuestions; 
	private ArrayList<Question> multipleChoiceQuestions;
	// Questions which will be altered during gameplay
	private ArrayList<Question> gameTFQuestions; 
	private ArrayList<Question> gameMCQuestions;
	
	public Answer() {
		trueFalseQuestions = new ArrayList<Question>();
		multipleChoiceQuestions = new ArrayList<Question>();
		gameTFQuestions = new ArrayList<Question>();
		gameMCQuestions = new ArrayList<Question>();
	}
	
	/// Method for adding questions one by one
	public void addQuestion(Question question) {
		if (question.getType() == Question.Type.TRUE_FALSE)
			trueFalseQuestions.add(question);
		else
			multipleChoiceQuestions.add(question);
	}
	
	/// Method for loading questions using a file. Takes a file and a question type, then adds the questions to both question lists of the given type (the one that
	///	doesn't change and the one edited during the game)
	public void loadQuestions(File file, Question.Type type) throws FileNotFoundException {
		Scanner scan = new Scanner(file);
		String questionString;
		String topic;
		String explanation = "default";
		char correctAnswer;
		int length;
		ArrayList<Question> questionList;
		ArrayList<Question> gameQuestionList;
		if  (type == Question.Type.TRUE_FALSE) { // two answers for true / false; references the corresponding lists of the given type
			length = 2;
			questionList = trueFalseQuestions;
			gameQuestionList = gameTFQuestions;
		}
		else {
			length = 4; // four answers for multiple choice
			questionList = multipleChoiceQuestions;
			gameQuestionList = gameMCQuestions;
		}

		// Scan until reaching end of file
		while (scan.hasNextLine()) {
			topic = scan.nextLine();
			questionString = scan.nextLine();
			String[] answers = new String[length];
			for (int i = 0; i < length; i++) {
				answers[i] = scan.nextLine();
			}
			String correctLine = scan.nextLine();
			if  (type == Question.Type.TRUE_FALSE) {
				if (correctLine.toLowerCase().equals("true")) {
					correctAnswer = 'T';
				}
				else
					correctAnswer = 'F';
			}
			else
				correctAnswer = correctLine.charAt(0);
			// Create a Question object using the scanned data and add it to the corresponding list
			Question question = new Question(topic,questionString,answers,explanation,type,correctAnswer);
			questionList.add(question);
			gameQuestionList.add(question);
		}
		
		scan.close();
	}
	
	/// Method for adding all questions via a single question ArrayList
	public void setQuestions(ArrayList<Question> questions) {
		for (Question question : questions) {
			if (question.getType() == Question.Type.TRUE_FALSE)
				trueFalseQuestions.add(question);
			else
				multipleChoiceQuestions.add(question);
		}
	}
	
	/// Method for checking a given answer, returning true for correct and false for wrong
	public boolean checkAnswer(char ans) {
		if (ans == currentQuestion.getCorrectAnswer())
			return true;
		else
			return false;
	}
	
	/// Getter method that returns the current selected question
	public Question getQuestion() {
		return currentQuestion;
	}
	
	/// Getter method that returns the list of true / false questions	
	public ArrayList<Question> getTrueFalseQuestions() {
		return trueFalseQuestions;
	}
	
	/// Getter method that returns the list of multiple-choice questions	
	public ArrayList<Question> getMultipleChoiceQuestions() {
		return multipleChoiceQuestions;
	}
	
	/// Method that randomly selects a question of a given type before removing it from the answer pool (i.e. its respective ArrayList).
	public void selectQuestion(Question.Type type) {
		Random rand = new Random();
		if (type == Question.Type.TRUE_FALSE) {
			currentQuestion  = trueFalseQuestions.get(rand.nextInt(trueFalseQuestions.size()));
			trueFalseQuestions.remove(currentQuestion);
		}
		else {
			currentQuestion  = multipleChoiceQuestions.get(rand.nextInt(multipleChoiceQuestions.size()));
			multipleChoiceQuestions.remove(currentQuestion);
		}
	}
	/// Method which resets either the multiple choice or the true/false question lists used in the game based on the input type.
	public void refreshQuestions(Question.Type type) {
		if (type == Question.Type.TRUE_FALSE) {
			gameTFQuestions.clear();
			for (Question question : trueFalseQuestions) {
				gameTFQuestions.add(question);
			}
		}
		else {
			gameMCQuestions.clear();
			for (Question question : multipleChoiceQuestions) {
				gameMCQuestions.add(question);
			}
		}
			
		

	}
}
