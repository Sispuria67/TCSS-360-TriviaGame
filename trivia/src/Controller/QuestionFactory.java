package Controller;

import org.sqlite.SQLiteDataSource;

import java.io.File;
import java.io.Serializable;
import java.sql.*;

public class QuestionFactory implements Serializable {


    private static final long serialVersionUID = -6540508260469792206L;
    public static SQLiteDataSource ds;
    private static String answerResult;
    private static String option1Result;
    private static String questionResult;
    public QuestionFactory(){
        createDataBase();
        this.answerResult = QuestionFactory.answerResult;
        this.option1Result = QuestionFactory.option1Result;
        this.questionResult = QuestionFactory.questionResult;
    }
    public static void createDataBase(){

        File dbFile = new File("test2.db");
        if (dbFile.exists()) {
            dbFile.delete();
        }

        SQLiteDataSource ds1 = new SQLiteDataSource();
        var url = "jdbc:sqlite:test2.db";

        try {
            ds1 = new SQLiteDataSource();
            ds1.setUrl(url);
        } catch ( Exception e ) {
            e.printStackTrace();
            System.exit(0);
        }

        // SQL statement for creating a new table
        var query = "CREATE TABLE IF NOT EXISTS questions ("
                + "	doorNumber INTEGER PRIMARY KEY,"
                + "	question text NOT NULL,"
                + " option1 text NOT NULL,"
                + " option2 text NOT NULL,"
                + " option3 text NOT NULL,"
                + " option4 text NOT NULL,"
                + "	answer INTEGER"
                + ");";
        try ( Connection conn = ds1.getConnection();
              Statement stmt = conn.createStatement(); ) {
            int rv = stmt.executeUpdate( query );
            System.out.println( "executeUpdate() returned " + rv );
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        String query1 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (1, 'Last name of Java creator?', 'Gosling', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query2 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (2, 'What galaxy do we live in?', 'Milky Way', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query3 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (3, 'Which planet is known as the Blue Planet?', 'Earth', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query4 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (4, 'Who gave the U.S. The Statue of Liberty?', 'France', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query5 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (5, 'A pickle is made from which fruit or vegetable?', 'Cucumber', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query6 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (6, 'What is the capital of China?', 'Beijing', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query7 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (7, 'How many colors are there in a rainbow?', '7', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query8 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (8, 'What is a supernova?', 'The explosion of a star', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query9 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (9, 'What is the square root of 25?', '5', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query10 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (10, 'What is the largest planet in our solar system?', 'Jupiter', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query11 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (11, 'Name the largest ocean mammal?', 'Blue whale', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query12 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (12, 'In which country would you find the Great Wall?', 'China', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query13 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (13, 'How many meters are in a kilometer?', '1000', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query14 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (14, 'In what year did World War II end?', '1945', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query15 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (15, 'What shape is a stop sign?', 'Octagon', 'FALSE', 'FALSE', 'FALSE', 1)";
        String query16 = "INSERT INTO questions (DoorNumber, QUESTION, option1, option2, option3, option4, ANSWER) VALUES (16, 'How many primary colors are there?', '3', 'FALSE', 'FALSE', 'FALSE', 1)";

        try ( Connection conn = ds1.getConnection();
              Statement stmt = conn.createStatement(); ) {
            stmt.executeUpdate( query1 );
            stmt.executeUpdate( query2 );
            stmt.executeUpdate( query3 );
            stmt.executeUpdate( query4 );
            stmt.executeUpdate( query5 );
            stmt.executeUpdate( query6 );
            stmt.executeUpdate( query7);
            stmt.executeUpdate( query8 );
            stmt.executeUpdate( query9 );
            stmt.executeUpdate( query10 );
            stmt.executeUpdate( query11 );
            stmt.executeUpdate( query12 );
            stmt.executeUpdate( query13 );
            stmt.executeUpdate( query14 );
            stmt.executeUpdate( query15 );
            stmt.executeUpdate( query16 );

        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }

        query = "SELECT * FROM questions";
        try ( Connection conn = ds1.getConnection();
              Statement stmt = conn.createStatement(); ) {

            ResultSet rs = stmt.executeQuery(query);

            //walk through each 'row' of results, grab data by column/field name
            // and print it
            while ( rs.next() ) {
                questionResult = rs.getString( "QUESTION" );
                option1Result = rs.getString( "option1" );
                String option2 = rs.getString( "option2" );
                answerResult = rs.getString( "ANSWER" );

//                System.out.println( "Result: Question = " + question +
//                        ", option1 = " + option1 +
//                        ", option2 = " + option2);
            }
        } catch ( SQLException e ) {
            e.printStackTrace();
            System.exit( 0 );
        }
    }
    public String getAnswerResult(){
        return answerResult;
    }
    public void setAnswerResult(String answerResult){
        this.answerResult = answerResult;
    }
    public String getOption1ResultResult(){
        return option1Result;
    }
    public String getQuestionResultResult(){
        return questionResult;
    }


    public static void main(String[] args){
        createDataBase();
    }


}

