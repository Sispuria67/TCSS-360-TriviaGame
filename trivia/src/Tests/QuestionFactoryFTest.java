package Tests;

import Model.Question;
import Model.QuestionFactoryF;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import static org.junit.Assert.*;

/**
 * QuestionFactoryFTest is a class that contains unit tests for the QuestionFactoryF class.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class QuestionFactoryFTest {

    /**
     * setUp method initializes the database before each test.
     */
    @Before
    public void setUp() {
        QuestionFactoryF.createDataBase();
    }

    /**
     * testCreateDatabase method tests the creation of the database and the questions table.
     */
    @Test
    public void testCreateDatabase() {
        File dbFile = new File("practSQL.db");
        assertTrue(dbFile.exists());

        try (Connection conn = QuestionFactoryF.getDs().getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='questions'")) {
            assertTrue(rs.next());
            assertEquals("questions", rs.getString("name"));
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

    /**
     * testGetQuestionById method tests the retrieval of questions by ID.
     */
    @Test
    public void testGetQuestionById() {
        Question question = QuestionFactoryF.getQuestionById(1);
        assertNotNull(question);
         assertEquals("What is the capital of Canada?", question.getQuestionText());
         Question multipleChoiceQuestion = QuestionFactoryF.getQuestionById(2);
        assertNotNull(multipleChoiceQuestion);
        assertTrue(multipleChoiceQuestion instanceof Question.MultipleChoiceQuestion);
        assertArrayEquals(new String[]{"Red", "Green", "Yellow", "Black"}, ((Question.MultipleChoiceQuestion) multipleChoiceQuestion).getMyOptions());

        Question trueFalseQuestion = QuestionFactoryF.getQuestionById(14);
        assertNotNull(trueFalseQuestion);
        assertTrue(trueFalseQuestion instanceof Question.TrueFalseQuestion);

        Question shortAnswerQuestion = QuestionFactoryF.getQuestionById(28);
        assertNotNull(shortAnswerQuestion);
        assertTrue(shortAnswerQuestion instanceof Question.ShortAnswerQuestion);
        assertEquals("Who is considered the father of modern computer science?", shortAnswerQuestion.getQuestionText());
    }

    /**
     * testGetQuestionTextById method tests the retrieval of question text by ID.
     */
    @Test
    public void testGetQuestionTextById() {
        String questionText = QuestionFactoryF.getQuestionTextById(1);
        assertEquals("What is the capital of Canada?", questionText);
    }

    /**
     * testGetAnswerForQuestion method tests the retrieval of answers for questions by ID.
     */
    @Test
    public void testGetAnswerForQuestion() {
        String answer = QuestionFactoryF.getAnswerForQuestion(1);
        assertEquals("B", answer);
    }

    /**
     * testCheckAnswer method tests the checking of answers for questions by ID.
     */
    @Test
    public void testCheckAnswer() {
        // Tests for correct and incorrect answers
        boolean isCorrect = QuestionFactoryF.checkAnswer(1, "B");
        assertTrue(isCorrect);

        isCorrect = QuestionFactoryF.checkAnswer(1, "A");
        assertFalse(isCorrect);
    }
}
