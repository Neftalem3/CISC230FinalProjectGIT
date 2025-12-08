package final_project;
/*
* File: Question1.java
* Author: Connor King
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/7/2025
*
* Description:	
* 
*/
public class Question1 {
	private String questionString;
	private String[] answers;
	private char correctAnswer;
	private int correctAnswerIndex;
	private int points;
	private Type type;
	enum Type {MULTIPLE_CHOICE(10), TRUE_FALSE(5);
		private int points;
		Type(int points) {
			this.points = points;
		}
		
		int getPoints() {
			return this.points;
		}
		
	}
	
	public Question1(String questionString, String[] answers, Type type, char correctAnswer) {
		this.questionString = questionString;
		this.answers = answers;
		this.type = type;
		points = type.getPoints();
		this.correctAnswer = correctAnswer;
		correctAnswerIndex = returnIndex(correctAnswer);
	}
	
	private int returnIndex(char ans) {
			switch (ans) {
			case 'A': return 0;
			case 'B': return 1;
			case 'C': return 2;
			case 'D': return 3;
			case 'T': return 0;
			case 'F': return 1;
			default: return -1;
		}
	}
	
	public String getQuestionString() {
		return questionString;
	}
	
	public String[] getAnswers() {
		return answers;
	}
	
	public int getPoints() {
		return points;
	}
	
	public int getCorrectAnswerIndex() {
		return correctAnswerIndex;
	}
	
	public Type getType() {
		return type;
	}
}
