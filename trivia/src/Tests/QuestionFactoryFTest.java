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

public class QuestionFactoryFTest {

    @Before
    public void setUp() {
        // Initialize and create the database
        QuestionFactoryF.createDataBase();
    }

    @Test
    public void testCreateDatabase() {
        // Check if the database file exists
        File dbFile = new File("practSQL.db");
        assertTrue(dbFile.exists());

        // Check if the table was created
        try (Connection conn = QuestionFactoryF.ds.getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT name FROM sqlite_master WHERE type='table' AND name='questions'")) {
            assertTrue(rs.next());
            assertEquals("questions", rs.getString("name"));
        } catch (SQLException e) {
            fail("Exception thrown: " + e.getMessage());
        }
    }

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
        assertArrayEquals(new String[]{"True", "False"}, ((Question.TrueFalseQuestion) trueFalseQuestion).getMyOptions());

        Question shortAnswerQuestion = QuestionFactoryF.getQuestionById(28);
        assertNotNull(shortAnswerQuestion);
        assertTrue(shortAnswerQuestion instanceof Question.ShortAnswerQuestion);
        assertEquals("Who is considered the father of modern computer science?", shortAnswerQuestion.getQuestionText());
    }

    @Test
    public void testGetQuestionTextById() {
        String questionText = QuestionFactoryF.getQuestionTextById(1);
        assertEquals("What is the capital of Canada?", questionText);
    }

    @Test
    public void testGetAnswerForQuestion() {
        String answer = QuestionFactoryF.getAnswerForQuestion(1);
        assertEquals("B", answer);
    }

    @Test
    public void testCheckAnswer() {
        boolean isCorrect = QuestionFactoryF.checkAnswer(1, "B");
        assertTrue(isCorrect);

        isCorrect = QuestionFactoryF.checkAnswer(1, "A");
        assertFalse(isCorrect);
    }
}
