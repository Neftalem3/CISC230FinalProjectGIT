package final_project;
/*
* File: Question.java
* Author: Neftalem Sida
* Course: CISC230
* Lab: Group Sustainability Lab
* Date: 12/8/2025
*
* Description: Represents a single trivia question.
* Contains all game questions.
*/

import java.util.ArrayList;
import java.util.Random;
public class Question {
    
    public enum Type { MULTIPLE_CHOICE, TRUE_FALSE }

    private String category;
    private Type type;
    private String questionText;
    private String[] options;     // Null for True/False
    private String correctOption; 
    private String explanation;   // The statement after answering

    //Constructor
    public Question(String category, Type type, String questionText, String[] options, String correctOption, String explanation) {
        this.category = category;
        this.type = type;
        this.questionText = questionText;
        this.options = options;
        this.correctOption = correctOption;
        this.explanation = explanation;
    }

    // Getter methods
    public String getCategory() { return category; }
    public Type getType() { return type; }
    public String getQuestionText() { return questionText; }
    public String[] getOptions() { return options; }
    public String getCorrectOption() { return correctOption; }
    public String getExplanation() { return explanation; }

    @Override
    public String toString() {
        return category + ": " + questionText;
    }

    
    public static ArrayList<Question> getQuestions() {
        ArrayList<Question> list = new ArrayList<>();

        // FOOD QUESTIONS
        list.add(new Question(
            "Food",
            Type.MULTIPLE_CHOICE,
            "Which of the following is NOT a helpful tip for reducing food waste?",
            new String[]{"Storing produce properly", "Planning meals at the beginning of the week", "Only purchasing what you need", "Storing all items from the store in the refrigerator"},
            "D",
            "Storing all items from the store in the refrigerator is incorrect; some items spoil faster in the fridge."
        ));

        list.add(new Question(
            "Food",
            Type.TRUE_FALSE,
            "In The View and the Northsider, you can mix and match food from different stations to make the perfect meal for you.",
            new String[]{"True", "False"},
            "True",
            "You can customize your meals and reduce food waste by only taking the food you like from each station."
        ));

        // ACADEMICS/RESEARCH QUESTIONS
        list.add(new Question(
            "Academics",
            Type.MULTIPLE_CHOICE,
            "The Sustainability minor is available to students in which of the following fields?",
            new String[]{"Arts and Sciences", "Engineering", "Business", "All of the above and more"},
            "D",
            "The Sustainability minor is available to all majors except Environmental Studies majors."
        ));

        list.add(new Question(
            "Academics",
            Type.TRUE_FALSE,
            "The Sustainability Scholars research designation through the Undergraduate Research Opportunities Program is open to students in all fields and majors.",
            new String[]{"True", "False"},
            "True",
            "This program is open to all students to encourage sustainability research."
        ));

        //WATER QUESTIONS
        list.add(new Question(
            "Water",
            Type.MULTIPLE_CHOICE,
            "Which of the following is NOT a way to conserve water?",
            new String[]{"Eating more plant-based meals", "Taking shorter showers", "Leaving the sink on while brushing your teeth", "Only washing full loads of laundry"},
            "C",
            "Leaving the sink on wastes water. The water from campus storm drains ends up in the Mississippi River."
        ));

        // SPECIALIZED RECYCLING
        list.add(new Question(
             "Specialized Recycling",
             Type.MULTIPLE_CHOICE,
             "Where is there a specialized recycling bin located on campus?",
             new String[]{"Outside the Campus Store in Murray-Herrick", "In the entrance to the Facilities & Design Center", "In the create[space]", "All of the above"},
             "D",
             "Specialized recycling bins can be found at all of those locations."
        ));
        
        // ENERGY
        list.add(new Question(
             "Energy",
             Type.MULTIPLE_CHOICE,
             "Which of the following is NOT a way to conserve energy?",
             new String[]{"Unplugging any devices when not in use.", "Turning off lights when leaving the room.", "Keeping windows closed to retain heat indoors in the winter.", "Washing laundry in small loads instead of full loads."},
             "D",
             "Washing full loads of laundry saves energy since each load of laundry takes energy to wash."
        ));
        Random rand = new Random();
        
        // Loop backwards from the end of the list
        for (int i = list.size() - 1; i > 0; i--) {
            // Pick a random index from 0 to i
            int j = rand.nextInt(i + 1);
            
            // Swap element at i with element at j
            Question temp = list.get(i);
            list.set(i, list.get(j));
            list.set(j, temp);
        }
        return list;
    }
}
