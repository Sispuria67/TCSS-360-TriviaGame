package Model;

public class Question {

    private String myQuestion;
    private String myAnswer;
    private int myQuestionId;

    private String myType;

    public String[] getMyOptions() {
        return new String[0];
    }


    public Question(int theQuestionId, String theQuestion, String theAnswer, String theType){
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myQuestionId = theQuestionId;
        myType = theType;
    }

    public String getQuestionText() {
        return myQuestion;
    }

    // Method to check the answer
    public boolean checkAnswer(String selectedAnswer) {
        if (myType.equals("shortanswer")) {
            return myAnswer.equalsIgnoreCase(selectedAnswer); // Case-insensitive comparison for short answer
        } else if (myType.equals("multiple")) {
            return myAnswer.equalsIgnoreCase(selectedAnswer); // Case-insensitive comparison for multiple-choice questions
        } else if (myType.equals("truefalse")) {
            return myAnswer.equalsIgnoreCase(selectedAnswer); // Case-insensitive comparison for true/false questions
        }
        return false; // Return false for unsupported question types
    }

    // Method to get the selected answer
    public String getSelectedAnswer(String[] options, int selectedIndex) {
        if (myType.equals("shortanswer")) {
            return null; // Return null for short answer questions
        } else if (myType.equals("multiple")) {
            if (selectedIndex >= 0 && selectedIndex < options.length) {
                return options[selectedIndex]; // Return the selected option for multiple-choice questions
            }
        } else if (myType.equals("truefalse")) {
            if (selectedIndex == 0) {
                return "True"; // Return "True" for true/false questions if the first option is selected
            } else if (selectedIndex == 1) {
                return "False"; // Return "False" for true/false questions if the second option is selected
            }
        }
        return null; // Return null for unsupported question types or invalid selected index
    }
    public String getQuestion(){
        return myQuestion;
    }

    public String getAnswer(){
        return myAnswer;
    }

    public static class MultipleChoiceQuestion extends Question{
        private String[] myOptions;

        public MultipleChoiceQuestion(int theQuestionId,String theQuestion, String[] theOption, String theAnswer, String theType){
            super(theQuestionId, theQuestion, theAnswer, "multiple");
            myOptions = theOption;

        }

        public String[] getMyOptions(){
            return myOptions;
        }

    }



    public static class TrueFalseQuestion extends Question{
        private String[] myOptions;
        public TrueFalseQuestion(int theQuestionId,String theQuestion,String[] theOption,  String theAnswer,  String theType){
            super(theQuestionId, theQuestion, theAnswer, "truefalse");
            myOptions = theOption;
        }
    }

    public static class ShortAnswerQuestion extends Question{
        public ShortAnswerQuestion(int theQuestionId, String theQuestion, String theAnswer,  String theOption){
            super(theQuestionId,theQuestion, theAnswer, "shortanswer");
        }
    }
}
