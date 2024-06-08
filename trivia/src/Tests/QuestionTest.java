/**
 * A package for Tests.
 */
package Tests;

import static org.junit.Assert.*;

import Model.Question;
import org.junit.Before;
import org.junit.Test;

/**
 * QuestionTest is a class that contains unit tests for the Question class.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class QuestionTest {

    private Question question;

    /**
     * setUp method initializes a new Question object before each test.
     */
    @Before
    public void setUp() {
        question = new Question(1, "What is the capital of France?", "Paris", "shortanswer");
    }

    /**
     * testGetQuestionText method checks if the correct question text is returned.
     */
    @Test
    public void testGetQuestionText() {
        assertEquals("What is the capital of France?", question.getQuestionText());
    }

    /**
     * testCheckAnswer method checks if the answer checking method works correctly.
     */
    @Test
    public void testCheckAnswer() {
        assertTrue(question.checkAnswer("Paris"));
        assertFalse(question.checkAnswer("London"));
    }

    /**
     * testGetSelectedAnswer method checks if the correct selected answer is returned.
     */
    @Test
    public void testGetSelectedAnswer() {
        assertNull(question.getSelectedAnswer(new String[]{"A", "B", "C"}, 0));
        assertEquals(null, question.getSelectedAnswer(new String[]{"A", "B", "C"}, 2));
    }

    /**
     * testGetQuestion method checks if the correct question is returned.
     */
    @Test
    public void testGetQuestion() {
        assertEquals("What is the capital of France?", question.getQuestion());
    }

    /**
     * testGetAnswer method checks if the correct answer is returned.
     */
    @Test
    public void testGetAnswer() {
        assertEquals("Paris", question.getAnswer());
    }

    /**
     * testGetMyType method checks if the correct question type is returned.
     */
    @Test
    public void testGetMyType() {
        assertEquals("shortanswer", question.getMyType());
    }

    /**
     * testGetMyQuestionId method checks if the correct question ID is returned.
     */
    @Test
    public void testGetMyQuestionId() {
        assertEquals(1, question.getMyQuestionId());
    }

    /**
     * testSetQuestion method tests setting a new question text.
     */
    @Test
    public void testSetQuestion() {
        question.setQuestion("What is the capital of Spain?");
        assertEquals("What is the capital of Spain?", question.getQuestion());
    }

    /**
     * testSetAnswer method tests setting a new answer text.
     */
    @Test
    public void testSetAnswer() {
        question.setAnswer("Madrid");
        assertEquals("Madrid", question.getAnswer());
    }

    /**
     * testSetMyType method tests setting a new question type.
     */
    @Test
    public void testSetMyType() {
        question.setMyType("multiple");
        assertEquals("multiple", question.getMyType());
    }

    /**
     * testSetMyQuestionId method tests setting a new question ID.
     */
    @Test
    public void testSetMyQuestionId() {
        question.setMyQuestionId(2);
        assertEquals(2, question.getMyQuestionId());
    }

    /**
     * testToString method tests the string representation of the Question object.
     */
    @Test
    public void testToString() {
        String expected = "Question{myQuestionId=1, myQuestion='What is the capital of France?', myAnswer='Paris', myType='shortanswer'}";
        assertEquals(expected, question.toString());
    }
}
