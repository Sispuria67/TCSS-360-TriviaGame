package Tests;

import Model.Question;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class QuestionTest {

    private Question shortAnswerQuestion;
    private Question multipleChoiceQuestion;
    private Question trueFalseQuestion;

    @Before
    public void setUp() {
        shortAnswerQuestion = new Question.ShortAnswerQuestion(1, "What is 2 + 2?", "4", "shortanswer");
        multipleChoiceQuestion = new Question.MultipleChoiceQuestion(2, "What is the capital of France?", new String[]{"Paris", "London", "Berlin"}, "Paris", "multiple");
        trueFalseQuestion = new Question.TrueFalseQuestion(3, "The sky is blue.", new String[]{"True", "False"}, "True", "truefalse");
    }

    @Test
    public void testGetQuestionText() {
        assertEquals("What is 2 + 2?", shortAnswerQuestion.getQuestionText());
        assertEquals("What is the capital of France?", multipleChoiceQuestion.getQuestionText());
        assertEquals("The sky is blue.", trueFalseQuestion.getQuestionText());
    }

    @Test
    public void testCheckAnswer() {
        assertTrue(shortAnswerQuestion.checkAnswer("4"));
        assertFalse(shortAnswerQuestion.checkAnswer("5"));

        assertTrue(multipleChoiceQuestion.checkAnswer("Paris"));
        assertFalse(multipleChoiceQuestion.checkAnswer("London"));

        assertTrue(trueFalseQuestion.checkAnswer("True"));
        assertFalse(trueFalseQuestion.checkAnswer("False"));
    }

    @Test
    public void testGetSelectedAnswer() {
        assertNull(shortAnswerQuestion.getSelectedAnswer(new String[]{}, 0));

        String[] options = {"Paris", "London", "Berlin"};
        assertEquals("Paris", multipleChoiceQuestion.getSelectedAnswer(options, 0));
        assertEquals("London", multipleChoiceQuestion.getSelectedAnswer(options, 1));
        assertEquals("Berlin", multipleChoiceQuestion.getSelectedAnswer(options, 2));
        assertNull(multipleChoiceQuestion.getSelectedAnswer(options, 3));

        String[] trueFalseOptions = {"True", "False"};
        assertEquals("True", trueFalseQuestion.getSelectedAnswer(trueFalseOptions, 0));
        assertEquals("False", trueFalseQuestion.getSelectedAnswer(trueFalseOptions, 1));
        assertNull(trueFalseQuestion.getSelectedAnswer(trueFalseOptions, 2));
    }

    @Test
    public void testGetMyOptions() {
        assertArrayEquals(new String[]{"Paris", "London", "Berlin"}, ((Question.MultipleChoiceQuestion) multipleChoiceQuestion).getMyOptions());
        assertArrayEquals(new String[]{"True", "False"}, ((Question.TrueFalseQuestion) trueFalseQuestion).getMyOptions());
        assertArrayEquals(new String[0], shortAnswerQuestion.getMyOptions());
    }
}
