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
public class Question {
	private String topic;
	private String questionString;
	private String[] answers;
	private String explanation;
	private char correctAnswer;
	private int points;
	private Type type;
	
	/// Type enum for creating either multiple choice or true/false questions. Each have a set point score.
	enum Type {MULTIPLE_CHOICE(100), TRUE_FALSE(50);
		private int points;
		Type(int points) {
			this.points = points;
		}
		
		int getPoints() {
			return this.points;
		}
		
	}
	
	public Question(String topic, String questionString, String[] answers, String explanation, Type type, char correctAnswer) {
		this.topic = topic;
		this.questionString = questionString;
		this.answers = answers;
		this.type = type;
		this.correctAnswer = correctAnswer;
		this.explanation = explanation;
		points = type.getPoints();
	}
	
	public String getTopic() {
		return topic;
	}
	
	public String getExplanation() {
		return explanation;
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
	
	public char getCorrectAnswer() {
		return correctAnswer;
	}
	
	public Type getType() {
		return type;
	}
	
	public String toString() {
		return questionString;
	}
}
