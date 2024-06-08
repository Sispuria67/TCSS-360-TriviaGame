/**
 * A package for model.
 */
package Model;

import org.sqlite.SQLiteDataSource;
import java.io.File;
import java.io.Serializable;
import java.sql.*;


/**
 * QuestionFactoryF is a class that represents the SQl lite database
 * which stores the questions for the game
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class QuestionFactoryF implements Serializable {


    /** Unique class identifier used for serialization */
    private static final long serialVersionUID = 13454L;

    /** A private field that adds a sqlite datasource */
    private static SQLiteDataSource ds;

    /**
     * QuestionFactoryF is constructor method that initializes
     * the question factory.
     *
     */
    public QuestionFactoryF() {
        createDataBase();
    }

    /**
     * getDs is a getter method that retrieves the SQLiteDataSource object
     * used for database connections.
     *
     * @return The SQLiteDataSource object.
     */
    public static SQLiteDataSource getDs() {
        return ds;
    }
    /**
     * createDataBase is a method that creates a SQLite database and initializes the
     * questions to be used in the game.
     */
    public static void createDataBase() {
       SQLiteDataSource ds1 =  new SQLiteDataSource();
        var url = "jdbc:sqlite:practSQL.db";

        File dbFile = new File("practSQL.db");

        if(dbFile.exists()){
            dbFile.delete();
        }

        try {
            ds = new SQLiteDataSource();
            ds.setUrl(url);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }


        var query = "CREATE TABLE IF NOT EXISTS questions ("
                + " doorNumber INTEGER PRIMARY KEY,"
                + " question TEXT NOT NULL,"
                + " option1 TEXT,"
                + " option2 TEXT,"
                + " option3 TEXT,"
                + " option4 TEXT,"
                + " answer TEXT NOT NULL,"
                + " type TEXT NOT NULL"
                + ");";

        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
            int rv = stmt.executeUpdate(query);
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }
               String[] queries = {
                      "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (1, 'What is the capital of Canada?', 'Toronto', 'Ottawa', 'Montreal', 'Vancouver', 'B', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (2, 'Which of the following is not a primary color?', 'Red', 'Green', 'Yellow', 'Black', 'D', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (3, 'Who painted the Mona Lisa?', 'Leonardo da Vinci', 'Vincent van Gogh', 'Pablo Picasso', 'Michelangelo', 'A', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (4, 'What is the chemical symbol for gold?', 'Au', 'Ag', 'Fe', 'Pb', 'A', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (5, 'What is the powerhouse of the cell?', 'Mitochondria', 'Nucleus', 'Ribosome', 'Golgi apparatus', 'A', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (6, 'What is the largest planet in our solar system?', 'Mars', 'Venus', 'Saturn', 'Jupiter', 'D', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (7, 'Which gas is most abundant in the Earths atmosphere?', 'Oxygen', 'Nitrogen', 'Carbon dioxide', 'Hydrogen', 'B', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (8, 'What is the chemical symbol for water?', 'CO2', 'NaCl', 'O3', 'H2O', 'D', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (9, 'Which planet is known as the Red Planet?', 'Mars', 'Jupiter', 'Venus', 'Mercury', 'A', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (10, 'How many colors are there in a rainbow?', 'One', 'Ten', 'Seven', 'Five', 'C', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (11, 'What film won Best Picture at the Oscars in 2020?', 'Parasite', '1917', 'Joker', 'Once Upon a Time in Hollywood', 'A', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (12, 'Which NBA team won the finals in 2016?', 'Warriors', 'Cavaliers',  'Celtics', 'Knicks', 'B', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, option3, option4, answer, type) VALUES (13, 'What is the most popular ice cream flavor in America?', 'Chocolate', 'Mint Chocolate Chip', 'Strawberry', 'Vanilla', 'D', 'multiple')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (14, 'The Pacific Ocean is the largest ocean in the world.', 'True', 'False', 'True', 'truefalse')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (15, 'The Java programming language was created by James Gosling.', 'True', 'False', 'True', 'truefalse')",
                       "INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (16, 'The square root of 25 is 5.', 'True', 'False', 'True', 'truefalse')",
                      " INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (17, 'The longest river in the world is the Nile River.', 'True', 'False', 'True', 'truefalse')",
                      "INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (18, 'Spiders are insects.', 'True', 'False', 'False', 'truefalse')",
                      "INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (19, 'Mount Everest is the tallest mountain in the world.', 'True', 'False', 'True', 'truefalse')",
                       " INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (20, 'The moon orbits the Earth.', 'True', 'False', 'True', 'truefalse')",
                        " INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (21, 'The Olympic Games are held every ten years.', 'True', 'False', 'False', 'truefalse')",
                       " INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (22, 'The galaxy we live in is the Milky Way.', 'True', 'False', 'True', 'truefalse')",
                       "  INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (23, 'HTML stands for Hyper Text Markup Language.', 'True', 'False', 'True', 'truefalse')",
                       "  INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (24, 'American Gothic was painted by Vincent van Gogh.', 'True', 'False', 'False', 'truefalse')",
                       "  INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (25, 'The American Civil War ended in 1865.', 'True', 'False', 'True', 'truefalse')",
                       "  INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (26, 'The Shawshank Redemption is the highest-rated movie on IMDb.', 'True', 'False', 'True', 'truefalse')",
                       "   INSERT INTO questions (doorNumber, question, option1, option2, answer, type) VALUES (27, 'Harry Potter and the Philosophers Stone is the first book in the Harry Potter series.', 'True', 'False', 'True', 'truefalse')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (28, 'Who is considered the father of modern computer science?', 'Alan Turing', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (29, 'What color is a stop sign?', 'Red', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (30, 'What is the most populated country in the world?', 'India', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (31, 'Who was the first president of the United States of America?', 'George Washington', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (32, 'What is the tallest mammal on Earth?', 'Giraffe', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (33, 'What is the capital of France?', 'Paris', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (34, 'Who wrote \"To be, or not to be\"?', 'William Shakespeare', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (35, 'What is the largest internal organ in the human body?', 'Liver', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (36, ' What is the name of the band lead by Freddie Mercury?', 'Queen', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (37, 'Which country is known for originating the dish sushi?', 'Japan', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (38, 'What year did the Titanic sink?', '1912', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (39, 'What language is most commonly spoken in Brazil?', 'Portuguese', 'shortanswer')",
                       "INSERT INTO questions (doorNumber, question, answer, type) VALUES (40, 'What is the main ingredient in guacamole?', 'Avocado', 'shortanswer')",


};
        try (Connection conn = ds.getConnection();
             Statement stmt = conn.createStatement()) {
           for (String query1 : queries) {
            stmt.executeUpdate( query1 );
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.exit(0);
        }

    }

    /**
     * getQuestionById is a method that retrieves a question from the database based on its unique ID.
     *
     * @param theId represents the unique ID of the question to retrieve.
     * @return The Question object representing the retrieved question.
     */
    public static Question getQuestionById(int theId) {
        Question question = null;
        String sql = "SELECT doorNumber, question, option1, option2, option3, option4, answer, type FROM questions WHERE doorNumber = ?";
        try (Connection conn = ds.getConnection(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setInt(1, theId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                int questionId = rs.getInt("doorNumber");
                String questionText = rs.getString("question");
                String answer = rs.getString("answer");
                String type = rs.getString("type");
                if (type.equals("multiple")) {
                    String option1 = rs.getString("option1");
                    String option2 = rs.getString("option2");
                    String option3 = rs.getString("option3");
                    String option4 = rs.getString("option4");
                    String[] options = {option1, option2, option3, option4};
                    question = new Question.MultipleChoiceQuestion(questionId, questionText, options, answer, type);
                } else if (type.equals("truefalse")) {
                    String option1 = rs.getString("option1");
                    String option2 = rs.getString("option2");
                    String[] options = {option1, option2};
                    question = new Question.TrueFalseQuestion(questionId, questionText, options, answer, type);
                } else if (type.equals("shortanswer")) {
                    question = new Question.ShortAnswerQuestion(questionId, questionText, answer, type);
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return question;
    }


    /**
     * getQuestionTextById is a method that retrieves the text of a question from the
     * database based on its unique ID.
     *
     * @param theQuestionId represents the unique ID of the question.
     * @return The text of the question.
     */
    public static String getQuestionTextById(int theQuestionId) {
        String questionText = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT question FROM questions WHERE doorNumber = ?")) {
            pstmt.setInt(1,  theQuestionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                questionText = rs.getString("question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return questionText;
    }

    /**
     * getAnswerForQuestion is a method that retrieves the correct answer for a question
     * from the database based on its unique ID.
     *
     * @param theQuestionId represents the unique ID (doorNumber) of the question.
     * @return The correct answer to the question.
     */
    public static String getAnswerForQuestion(int theQuestionId) {
        String answer = null;
        try (Connection conn = ds.getConnection();
             PreparedStatement pstmt = conn.prepareStatement("SELECT answer FROM questions WHERE doorNumber = ?")) {
            pstmt.setInt(1,  theQuestionId);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                answer = rs.getString("answer");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return answer;
    }

    /**
     * checkAnswer is a method that checks if the player's answer matches the
     * correct answer for a question in the database.
     *
     * @param theQuestionId represents the unique ID of the question.
     * @param thePlayerAnswer represents the player's answer to the question.
     * @return True if the player's answer matches the correct answer, false otherwise.
     */
    public static boolean checkAnswer(int  theQuestionId, String thePlayerAnswer) {
        String correctAnswer = getAnswerForQuestion( theQuestionId);
        return correctAnswer.equalsIgnoreCase(thePlayerAnswer);
    }





}
