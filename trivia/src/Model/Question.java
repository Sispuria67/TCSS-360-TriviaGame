/**
 * A package for model.
 */
package Model;

import java.util.Arrays;

/**
 * Question is a class that represents the trivia 
 * questions in the game. 
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class Question {

    /** A private field for the question */
    private String myQuestion;

    /** A private field for the answer */
    private String myAnswer;

    /** A private field for the questionID */
    private int myQuestionId;

    /** A private field for the type of question */
    private String myType;


    /**
     * getMyOptions is a getter method for the options of the question.
     *
     * @return an array of options for the question.
     */
    public String[] getMyOptions() {
        return new String[0];
    }


    /**
     * Question is a constructor method that creates a new Question object.
     *
     * @param theQuestionId represents the unique identifier for the question.
     * @param theQuestion represents the text of the question.
     * @param theAnswer represents the correct answer to the question.
     * @param theType represents the type of the question ("shortanswer", "multiple", "truefalse").
     */
    public Question(int theQuestionId, String theQuestion, String theAnswer, String theType){
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myQuestionId = theQuestionId;
        myType = theType;
    }

    /**
     * getQuestionText is a getter method that
     * return the text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestionText() {
        return myQuestion;
    }


    /**
     * checkAnswer is a method that checks if the
     * given answer is correct.
     *
     * @param theSelectedAnswer represents the answer to check.
     * @return True if the answer is correct, false otherwise.
     */
    public boolean checkAnswer(String theSelectedAnswer) {
        if (myType.equals("shortanswer")) {
            return myAnswer.equalsIgnoreCase(theSelectedAnswer);
        } else if (myType.equals("multiple")) {
            return myAnswer.equalsIgnoreCase(theSelectedAnswer);
        } else if (myType.equals("truefalse")) {
            return myAnswer.equalsIgnoreCase(theSelectedAnswer);
        }
        return false; 
    }


    /**
     * getSelectedAnswer is a method that returns the selected answer
     * based on the type of the question.
     *
     * @param theOptions represents the options available for the question.
     * @param theSelectedIndex represents the index of the selected option.
     * @return The selected answer, or null if not applicable.
     */
    public String getSelectedAnswer(String[] theOptions, int theSelectedIndex) {
        if (myType.equals("shortanswer")) {
            return null; 
        } else if (myType.equals("multiple")) {
            if (theSelectedIndex >= 0 && theSelectedIndex < theOptions.length) {
                return theOptions[theSelectedIndex]; 
            }
        } else if (myType.equals("truefalse")) {
            if (theSelectedIndex == 0) {
                return "True";
            } else if (theSelectedIndex == 1) {
                return "False";
            }
        }
        return null; 
    }

    /**
     * getQuestion is a getter method that returns the
     * text of the question.
     *
     * @return The text of the question.
     */
    public String getQuestion(){
        return myQuestion;
    }

    /**
     * getAnswer is a getter method for the answer text.
     *
     * @return the answer text.
     */
    public String getAnswer(){
        return myAnswer;
    }
    /**
     * getMyType is a getter method for the type of the question.
     *
     * @return the type of the question.
     */
    public String getMyType(){
        return myType;
    }
    /**
     * getMyQuestionId is a getter method for the question ID.
     *
     * @return the unique identifier for the question.
     */
    public int getMyQuestionId(){
        return myQuestionId;
    }

    /**
     * setQuestion is a setter method for the question text.
     *
     * @param theQuestion represents the question text.
     */
    public void setQuestion(String theQuestion) {
       myQuestion = theQuestion;
    }
    /**
     * setAnswer is a setter method for the answer text.
     *
     * @param theAnswer represents the answer text.
     */

    public void setAnswer(String theAnswer) {
        myAnswer = theAnswer;
    }

    /**
     * setMyType is a setter method for the type of the question.
     *
     * @param theType represents the type of the question.
     */
    public void setMyType(String theType) {
       myType = theType;
    }

    /**
     * setMyQuestionId is a setter method for the question ID.
     *
     * @param theQuestionId represents the unique identifier for the question.
     */
    public void setMyQuestionId(int theQuestionId) {
        myQuestionId = theQuestionId;
    }

    /**
     * toString method for the entire class.
     *
     * @return the string value of the class
     */
    @Override
    public String toString() {
        return "Question{" +
                "myQuestionId=" + myQuestionId +
                ", myQuestion='" + myQuestion + '\'' +
                ", myAnswer='" + myAnswer + '\'' +
                ", myType='" + myType + '\'' +
                '}';
    }


    /**
     * MultipleChoiceQuestion is an inner class that represents the multiple
     * choice questions in the game. 
     * @author Sado Iman, Rohit Ark
     * @version 06/7/24
     */
    public static class MultipleChoiceQuestion extends Question{

        /** A private field for the question options */
        private String[] myOptions;
        /**
         * MultipleChoiceQuestion is constructor method that constructs
         * a new MultipleChoiceQuestion object.
         *
         * @param theQuestionId represents the unique identifier for the question.
         * @param theQuestion represents the text of the question.
         * @param theOption represents the options available for the question.
         * @param theAnswer represents the correct answer to the question.
         * @param theType represents the type of the question.
         */
        public MultipleChoiceQuestion(int theQuestionId,String theQuestion, String[] theOption, String theAnswer, String theType){
            super(theQuestionId, theQuestion, theAnswer, "multiple");
            myOptions = theOption;

        }

        /**
         * getMyOptions is a getter method for the options of the question.
         *
         * @return an array of options for the question.
         */
        public String[] getMyOptions(){
            return myOptions;
        }
        /**
         * setMyOptions is a setter method for the options of the question.
         *
         * @param theOptions represents an array of options for the question.
         */
        public void setMyOptions(String[] theOptions) {
          myOptions = theOptions;
        }

        /**
         * toString method for the entire class.
         *
         * @return the string value of the class
         */
        @Override
        public String toString() {
            return "MultipleChoiceQuestion{" +
                    "myQuestionId=" + getMyQuestionId() +
                    ", myQuestion='" + getQuestion() + '\'' +
                    ", myAnswer='" + getAnswer() + '\'' +
                    ", myType='" + getMyType() + '\'' +
                    ", myOptions=" + Arrays.toString(myOptions) +
                    '}';
        }


    }

    /**
     * TrueFalseQuestion is an inner class that represents the true
     * false questions in the game. 
     * @author Sado Iman, Rohit Ark
     * @version 06/7/24
     */
    public static class TrueFalseQuestion extends Question{

        /** A private field for the question options */
        private String[] myOptions;

        /**
         *  TrueFalseQuestion is constructor method that constructs a new
         *  TrueFalseQuestion object.
         *
         * @param theQuestionId represents the unique identifier for the question.
         * @param theQuestion represents the text of the question.
         * @param theOption represents the options available for the question (unused in true/false questions).
         * @param theAnswer represents the correct answer to the question.
         * @param theType represents the type of the question (e.g., "truefalse").
         */
        public TrueFalseQuestion(int theQuestionId,String theQuestion,String[] theOption,  String theAnswer, String theType){
            super(theQuestionId, theQuestion, theAnswer, "truefalse");
            myOptions = theOption;
        }
        /**
         * toString method for the entire class.
         *
         * @return the string value of the class
         */
        @Override
        public String toString() {
            return "TrueFalseQuestion{" +
                    "myQuestionId=" + getMyQuestionId() +
                    ", myQuestion='" + getQuestion() + '\'' +
                    ", myAnswer='" + getAnswer() + '\'' +
                    ", myType='" + getMyType() + '\'' +
                    ", myOptions=" + Arrays.toString(myOptions) +
                    '}';
        }

    }

    /**
     * ShortAnswerQuestion is an inner class that represents the short
     * answer questions in the game. 
     * @author Sado Iman, Rohit Ark
     * @version 06/7/24
     */
    public static class ShortAnswerQuestion extends Question{

        /**
         * ShortAnswerQuestion is constructor method that constructs a
         * new ShortAnswerQuestion object.
         *
         * @param theQuestionId represents the unique identifier for the question.
         * @param theQuestion represents the text of the question.
         * @param theAnswer represents the correct answer to the question.
         * @param theOption represents the option for the question.
         */
        public ShortAnswerQuestion(int theQuestionId, String theQuestion, String theAnswer, String theOption){
            super(theQuestionId,theQuestion, theAnswer, "shortanswer");
        }
        /**
         * toString method for the entire class.
         *
         * @return the string value of the class
         */
        @Override
        public String toString() {
            return "ShortAnswerQuestion{" +
                    "myQuestionId=" + getMyQuestionId() +
                    ", myQuestion='" + getQuestion() + '\'' +
                    ", myAnswer='" + getAnswer() + '\'' +
                    ", myType='" + getMyType() + '\'' +
                    '}';
        }

    }
}
