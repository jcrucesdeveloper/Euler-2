package com.jorgecruces.euler1.gameLogic;

/**
 * Logical representation of a Question
 */
public class Question
{

    private int questionNumber;
    private String questionTitle;
    private String questionLabel;
    private String alternative1;
    private String alternative2;
    private String alternative3;
    private String correctAlternative;


    /**
     * Default constructor for empty Question
     */
    public Question() {
        this.questionLabel = "question";
        this.alternative1 = "1";
        this.alternative2 = "2";
        this.alternative3 = "3";
        this.correctAlternative = "4";
        this.questionTitle = "";
    }

    /**
     * Actual constructor for a real Question
     */
    public Question(String questionLabel, String alternative1, String alternative2, String alternative3, String correctAlternative,String questionTitle) {
        this.questionLabel = questionLabel;
        this.alternative1 = alternative1;
        this.alternative2 = alternative2;
        this.alternative3 = alternative3;
        this.correctAlternative = correctAlternative;
        this.questionTitle = questionTitle;
    }


    public void setQuestionNumber(int n) {
        this.questionNumber = n;
    }

    public int getQuestionNumber() {
        return this.questionNumber;
    }

    public String getQuestionNumberString() {
        return Integer.toString(this.questionNumber);
    }

    public String getQuestionLabel() {
        return questionLabel;
    }


    public String getAlternative1() {
        return alternative1;
    }


    public String getAlternative2() {
        return alternative2;
    }


    public String getAlternative3() {
        return alternative3;
    }

    public String getCorrectAlternative() {
        return correctAlternative;
    }


    public String getQuestionTitle() {
        return questionTitle;
    }

}
