package Model;

public class Question {

    private String myQuestion;
    private String myAnswer;
    private int myQuestionId;

    private String myType;




    public Question(int theQuestionId, String theQuestion, String theAnswer, String theType){
        myQuestion = theQuestion;
        myAnswer = theAnswer;
        myQuestionId = theQuestionId;
        myType = theType;
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
