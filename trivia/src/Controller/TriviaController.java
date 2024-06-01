package Controller;

import Model.*;
import View.ArrowsPanel;
import View.CurrentRoomPanel;
import View.MazePanel;
import View.QuestionPanel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

import static Model.QuestionFactoryF.getQuestionById;


public class TriviaController extends JPanel {

    ImageIcon img = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/doorPixel.png");

    ImageIcon questionMark = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/questionMark.png");


    Image resizedImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resizedImage);


    Image resizedQuestion = questionMark.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon questionIcon = new ImageIcon(resizedQuestion);

    private JFrame frame = new JFrame("Trivia Game");
    private JMenu myMenu;

    private int myNewCount;

    private final ArrowsPanel myArrowsPanel;
    private final QuestionPanel questionPanel;


    private final CharacterModel myCharacter;

    private final MazePanel myMazePanel;
    JLabel myText;

    private final Door myDoor;

    private Room[][] myRoom ;

    private CurrentRoomPanel myCurrentRoomPanel;

    private Clip clip;

    private JMenu myMenu2;

    private ImageIcon icon;

    private static JMenuBar myBar;

    private static JMenuItem myReset;


    private static JMenuItem myAbout;

    private static JMenuItem myRules;

    // public static QuestionFactory factory;


    private static JMenuItem myStart;

    private static JMenuItem myExit;

    public TriviaController(final TriviaModel theModel) {


        // super(new GridLayout(2, 1));
        // super(new BorderLayout());

        // factory = new QuestionFactory();


        myArrowsPanel = new ArrowsPanel(theModel);
        questionPanel = new QuestionPanel();
        myMazePanel = new MazePanel(theModel);
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myRoom = myMazePanel.getRoom();
        myCurrentRoomPanel = new CurrentRoomPanel(theModel);
        myNewCount = 0;

      //initializeDoors();
        myCurrentRoomPanel.setMyTextField("You are currently in Room 0");
       assignQuestionsToDoors();

        myText = new JLabel();
        /*
        //SQL
        try {
            dbManager = new DatabaseManager("path to db");
        }catch(SQLException e){
            e.printStackTrace();
        }

         */
        createAndShowGUI();
        createMenuBar();
        layoutComponents();
        addSubmitButtonListener();
        addCurrentArrowListeners();
        addMenuListeners();




        //questionPanel.updateQuestion(theQuestion);
       // addRadioListeners();


    }
/*
    private void initializeDoors() {
        myRoom = new Room[myMazePanel.getRows()][myMazePanel.getCols()];
        for(int i =0; i <myMazePanel.getRows(); i++){
            for(int j =0; j <myMazePanel.getCols(); j++){
                myRoom[i][j] = new Room();
                System.out.println("room length: " + myRoom.length); // = 5
            }
        }
        //room 1
        myRoom[0][0].setUpDoor(false);
        myRoom[0][0].setDownDoor(true);
        myRoom[0][0].setLeftDoor(false);
        myRoom[0][0].setRightDoor(true);
        //room 2
        myRoom[0][1].setUpDoor(false);
        myRoom[0][1].setDownDoor(true);
        myRoom[0][1].setLeftDoor(true);
        myRoom[0][1].setRightDoor(true);

        //room[0][1].
        //  room[0][2].
        // room[0][3].
        //  room[0][4].
        // room[1][0].


    }

 */

    private void layoutComponents() {


        //setting background colors
      //  myCurrentRoomPanel.setBackground(Color.pink);
      //  myMazePanel.setBackground(Color.PINK);
       // myArrowsPanel.setBackground(Color.PINK);
      //  questionPanel.setBackground(Color.cyan);

        JPanel mazeAndArrowsPanel = new JPanel(new BorderLayout());
        mazeAndArrowsPanel.add(myMazePanel, BorderLayout.CENTER);
        mazeAndArrowsPanel.add(myArrowsPanel, BorderLayout.EAST);
        mazeAndArrowsPanel.add(myCurrentRoomPanel, BorderLayout.SOUTH);
       // mazeAndArrowsPanel.add(myQuestionPanel, BorderLayout.SOUTH);




        setLayout(new BorderLayout());

        add(mazeAndArrowsPanel, BorderLayout.NORTH);


        add(questionPanel, BorderLayout.SOUTH);

        myCurrentRoomPanel.setPreferredSize(new Dimension(100, 80));
        questionPanel.setPreferredSize(new Dimension(100, 205));
        // JPanel myTopPanel = new JPanel(new GridLayout(1, 1, 10, 10));

        // this.setLayout(new BorderLayout());
        //  this.add(myMazePanel, BorderLayout.NORTH);
        //this.add(myMazePanel);
        // this.add(myArrowsPanel, BorderLayout.CENTER);
        // this.add(myQuestionPanel, BorderLayout.SOUTH);

        //  JPanel myTopPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // myTopPanel.add(myMazePanel);
        //  myTopPanel.add(myArrowsPanel);


        // this.add(myArrowsPanel);

        //  myTopPanel.add(myQuestionPanel);
        // myTopPanel.add(myMazePanel, BorderLayout.CENTER);
        //  myTopPanel.add(myArrowsPanel, BorderLayout.CENTER);
        //this.setBorder(BorderFactory.createEmptyBorder(50, 50, 50, 50));
        // add( myTopPanel, BorderLayout.SOUTH);

    }

    public void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.requestFocusInWindow();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900, 830);
        frame.setTitle("Trivia Game");


       icon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/doorPixel.png");
        frame.setIconImage(icon.getImage());
      //  frame.setBackground(Color.CYAN);


        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });

    }

    public void assignQuestionsToDoors() {
        // room 0
        myRoom[0][0].setQuestionForDoor("right", 1);
        myRoom[0][0].setQuestionForDoor("down", 20);

        // room 1
        myRoom[0][1].setQuestionForDoor("right", 3);
        myRoom[0][1].setQuestionForDoor("down", 29);
        myRoom[0][1].setQuestionForDoor("left", 1);

        // room 2
        myRoom[0][2].setQuestionForDoor("right", 16);
        myRoom[0][2].setQuestionForDoor("down", 38);
        myRoom[0][2].setQuestionForDoor("left", 3);

        // room 3
        myRoom[0][3].setQuestionForDoor("right", 19);
        myRoom[0][3].setQuestionForDoor("down", 14);
        myRoom[0][3].setQuestionForDoor("left", 16);

        // room 4
        myRoom[0][4].setQuestionForDoor("down", 18);
        myRoom[0][4].setQuestionForDoor("left", 19);

        // room 5
        myRoom[1][0].setQuestionForDoor("right", 6);
        myRoom[1][0].setQuestionForDoor("down", 22);
        myRoom[1][0].setQuestionForDoor("up", 20);

        // room 6
        myRoom[1][1].setQuestionForDoor("right", 23);
        myRoom[1][1].setQuestionForDoor("down", 4);
        myRoom[1][1].setQuestionForDoor("left", 6);
        myRoom[1][1].setQuestionForDoor("up", 29);

        // room 7
        myRoom[1][2].setQuestionForDoor("right", 25);
        myRoom[1][2].setQuestionForDoor("down", 11);
        myRoom[1][2].setQuestionForDoor("left", 23);
        myRoom[1][2].setQuestionForDoor("up", 38);

        // room 8
        myRoom[1][3].setQuestionForDoor("right", 9);
        myRoom[1][3].setQuestionForDoor("down", 24);
        myRoom[1][3].setQuestionForDoor("left", 25);
        myRoom[1][3].setQuestionForDoor("up", 14);

        // room 9
        myRoom[1][4].setQuestionForDoor("down", 31);
        myRoom[1][4].setQuestionForDoor("left", 9);
        myRoom[1][4].setQuestionForDoor("up", 18);

        // room 10
        myRoom[2][0].setQuestionForDoor("right", 26);
        myRoom[2][0].setQuestionForDoor("down", 28);
        myRoom[2][0].setQuestionForDoor("up", 22);

        // room 11
        myRoom[2][1].setQuestionForDoor("right", 21);
        myRoom[2][1].setQuestionForDoor("down", 2);
        myRoom[2][1].setQuestionForDoor("left", 26);
        myRoom[2][1].setQuestionForDoor("up", 4);

        // room 12
        myRoom[2][2].setQuestionForDoor("right", 13);
        myRoom[2][2].setQuestionForDoor("down", 10);
        myRoom[2][2].setQuestionForDoor("left", 21);
        myRoom[2][2].setQuestionForDoor("up", 11);

        // room 13
        myRoom[2][3].setQuestionForDoor("right", 17);
        myRoom[2][3].setQuestionForDoor("down", 12);
        myRoom[2][3].setQuestionForDoor("left", 13);
        myRoom[2][3].setQuestionForDoor("up", 24);

        // room 14
        myRoom[2][4].setQuestionForDoor("down", 7);
        myRoom[2][4].setQuestionForDoor("left", 17);
        myRoom[2][4].setQuestionForDoor("up", 31);

        // room 15
        myRoom[3][0].setQuestionForDoor("right", 5);
        myRoom[3][0].setQuestionForDoor("down", 15);
        myRoom[3][0].setQuestionForDoor("up", 28);

        // room 16
        myRoom[3][1].setQuestionForDoor("right", 32);
        myRoom[3][1].setQuestionForDoor("down", 8);
        myRoom[3][1].setQuestionForDoor("left", 5);
        myRoom[3][1].setQuestionForDoor("up", 2);

        // room 17
        myRoom[3][2].setQuestionForDoor("right", 30);
        myRoom[3][2].setQuestionForDoor("down", 27);
        myRoom[3][2].setQuestionForDoor("left", 32);
        myRoom[3][2].setQuestionForDoor("up", 10);

        // room 18
        myRoom[3][3].setQuestionForDoor("right", 33);
        myRoom[3][3].setQuestionForDoor("down", 34);
        myRoom[3][3].setQuestionForDoor("left", 30);
        myRoom[3][3].setQuestionForDoor("up", 12);

        // room 19
        myRoom[3][4].setQuestionForDoor("down", 36);
        myRoom[3][4].setQuestionForDoor("left", 33);
        myRoom[3][4].setQuestionForDoor("up", 7);

        // room 20
        myRoom[4][0].setQuestionForDoor("right", 35);
        myRoom[4][0].setQuestionForDoor("up", 15);

        // room 21
        myRoom[4][1].setQuestionForDoor("right", 37);
        myRoom[4][1].setQuestionForDoor("left", 35);
        myRoom[4][1].setQuestionForDoor("up", 8);

        // room 22
        myRoom[4][2].setQuestionForDoor("right", 39);
        myRoom[4][2].setQuestionForDoor("left", 37);
        myRoom[4][2].setQuestionForDoor("up", 27);

        // room 23
        myRoom[4][3].setQuestionForDoor("right", 40);
        myRoom[4][3].setQuestionForDoor("left", 39);
        myRoom[4][3].setQuestionForDoor("up", 34);

        // room 24
        myRoom[4][4].setQuestionForDoor("left", 40);
        myRoom[4][4].setQuestionForDoor("up", 36);

    }
    private void createMenuBar() {
        myBar = new JMenuBar();
        myMenu = new JMenu("File");
        myMenu2 = new JMenu("Help");
        myStart = new JMenuItem("Save Game");
        myReset = new JMenuItem("Load Game");
        myExit = new JMenuItem("Exit");
        myAbout = new JMenuItem("About");
        myRules = new JMenuItem("Rules");


        myMenu.add(myStart);
        myMenu.add(myReset);
        myMenu.add(myExit);
        myMenu2.add(myAbout);
        myMenu2.add(myRules);
        myBar.setPreferredSize(new Dimension(25, 30));
        myBar.add(myMenu);
        myBar.add(myMenu2);
        frame.setJMenuBar(myBar);



        myAbout.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This is a Trivia Game \nJava Version: 21.0\nAuthor: Rohit Ark, Sado Iman\n ", "About", JOptionPane.ERROR_MESSAGE, icon));

        myRules.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This a Trivia Game, the rules are as follows:\n 1. To win the game you must get to the end of the maze, marked by the exit door. \n 2. To get into the rooms you must correctly answer the trivia question at each door. " +
                        " \n 3.If you get the answer the door will be locked and must find another door to pass through. \n 4. If all the doors are locked, you lose. \n 5. There are hints in certain rooms that can help you answer the questions for those rooms.",
                "Rules", JOptionPane.ERROR_MESSAGE, icon));
    }


    private void updateCurrentRoomPanel() {
        myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());
    }

    private void enableAllArrows() {
        enableUpArrow();
        enableDownArrow();
        enableLeftArrow();
        enableRightArrow();
    }

    private void disableAllArrows() {
        disableUpArrow();
        disableDownArrow();
        disableLeftArrow();
        disableRightArrow();
    }

    /*
    private void addCurrentArrowListeners() {
        myArrowsPanel.addArrowListener(new ActionListener() {


            @Override
            public void actionPerformed(ActionEvent theEvent) {
                String direction = getEnteredDirection(theEvent.getSource());
                if (theEvent.getSource().equals(myArrowsPanel.getMyRightArrow())) {
                   // myCharacter.moveRight();
                  //  myMazePanel.moveCharacter("right");
                    moveCharacter(direction);


               updateCurrentRoomPanel();
                enableAllArrows();

                    checkWon();
                    correctAnswerPanel();



                } else if (theEvent.getSource().equals(myArrowsPanel.getMyLeftArrow())) {
                    //myCharacter.moveLeft();
                   // myMazePanel.moveCharacter("left");
                   // myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());
                    moveCharacter(direction);
                   enableAllArrows();
                    checkWon();

                }

                //and check if space is valid
                else if (theEvent.getSource().equals(myArrowsPanel.getMyDownArrow()) ) {

                    myCharacter.moveDown();
                    myMazePanel.moveCharacter("down");
                  //  myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                   enableUpArrow();
                    moveCharacter(direction);
                    checkWon();
                    incorrectAnswerPanel();



                } else if (theEvent.getSource().equals(myArrowsPanel.getMyUpArrow()) ) {
                    myCharacter.moveUp();
                    myMazePanel.moveCharacter("up");
                   // myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                   enableAllArrows();
                    moveCharacter(direction);
                    checkWon();
                }



            }

        });
    }



     */

/*
    private void addCurrentArrowListeners() {

        myArrowsPanel.addArrowListener(e -> {
            String direction = getEnteredDirection(e.getSource());

            if (canPass(direction)) {
                System.out.println("if can pass true");
                moveCharacter(direction);
            }
        });
    }



 */

    private void addCurrentArrowListeners() {
        myArrowsPanel.addArrowListener(e -> {
            String direction = getEnteredDirection(e.getSource());
            int questionId = myRoom[myCharacter.getRow()][myCharacter.getCol()].getQuestionForDoor(direction);
            //checkGameOver();
            if (questionId != -1) {
                Question question = getQuestionById(questionId);
                questionPanel.updateQuestion(question);

                // Add a temporary listener for the submit button
                questionPanel.addSubmitButtonListener(submitEvent -> {
                    String selectedAnswer = questionPanel.getSelectedAnswer();
                    if (selectedAnswer != null && !selectedAnswer.isEmpty()) {
                        if (canPass(selectedAnswer, question)) {
                            setDoorOpenSound();
                            moveCharacter(direction);
                            questionPanel.clearSubmitButtonListeners(); //clear the listener after use
                        } else {
                            incorrectAnswerPanel();
                            questionPanel.clearSubmitButtonListeners(); //clear the listener after use
                            //lock the door and if player tries to go on on this door, joption pops up tp say the door is closed
                        }
                    } else {
                        //duplicated, also in submit button
                      //  JOptionPane.showMessageDialog(this, "Please select or enter an answer.");
                    }
                    checkGameOver();
                });
            } else {
                moveCharacter(direction); // no question for the door, so move character
                checkGameOver();
            }
        });
    }

 /*
private void addCurrentArrowListeners() {
    myArrowsPanel.addArrowListener(e -> {
        String direction = getEnteredDirection(e.getSource());
        moveCharacter(direction);
    });
}

 */


    private void moveCharacter(String direction) {
        System.out.println("Entered move character method");

        switch (direction) {
            case "right":
                System.out.println("Direction is: " + direction);
                myCharacter.moveRight();
                myMazePanel.moveCharacter("right");
                break;
            case "left":
                myCharacter.moveLeft();
                myMazePanel.moveCharacter("left");
                break;
            case "down":
                myCharacter.moveDown();
                myMazePanel.moveCharacter("down");
                break;
            case "up":
                myCharacter.moveUp();
                myMazePanel.moveCharacter("up");
                break;
        }
        System.out.println("Current location: " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName() );
        updateCurrentRoomPanel();
        enableAllArrows();
        checkWon();
        checkGameOver();
        showHint();
        //System.out.println("Check won: " );

    }

    private void checkGameOver() {
        boolean allDoorsLocked = true;
        int row = myCharacter.getRow();
        int col = myCharacter.getCol();

        if (myRoom[row][col].getUpDoor() != null && !myRoom[row][col].getUpDoor().isLocked()) allDoorsLocked = false;
        if (myRoom[row][col].getDownDoor() != null && !myRoom[row][col].getDownDoor().isLocked()) allDoorsLocked = false;
        if (myRoom[row][col].getLeftDoor() != null && !myRoom[row][col].getLeftDoor().isLocked()) allDoorsLocked = false;
        if (myRoom[row][col].getRightDoor() != null && !myRoom[row][col].getRightDoor().isLocked()) allDoorsLocked = false;

        if (allDoorsLocked) {
            setLoseSound();
            JOptionPane.showMessageDialog(frame, "Game Over! You are stuck with no way out.", "Game Over", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
            //frame.dispose();
        }
    }

    // Method to fetch question text based on questionId
    private String getQuestionText(int questionId) {
        return QuestionFactoryF.getQuestionTextById(questionId);
    }
    private String getEnteredDirection(Object arrowButton) {
        System.out.println("Entered getEnteredDirection");
        if (arrowButton.equals(myArrowsPanel.getMyRightArrow())) {
            return "right";
        } else if (arrowButton.equals(myArrowsPanel.getMyLeftArrow())) {
            return "left";
        } else if (arrowButton.equals(myArrowsPanel.getMyDownArrow())) {
            return "down";
        } else if (arrowButton.equals(myArrowsPanel.getMyUpArrow())) {
            return "up";
        } else {
            return ""; // Return empty string for unknown direction
        }
    }

    public void incorrectAnswerPanel(){

        JOptionPane.showMessageDialog(this, "Incorrect Answer! This door is now locked.", "Door locked", JOptionPane.INFORMATION_MESSAGE, resizedIcon);

    }

    public void correctAnswerPanel(){
        JOptionPane.showMessageDialog(
                this, "Correct Answer! You may now proceed through this door.", "Door Unlocked",  JOptionPane.INFORMATION_MESSAGE, resizedIcon);

    }



    private void addMenuListeners() {


        myReset.addActionListener(e -> {
            if (e.getSource().equals(myReset)) {

                myStart.setEnabled(true);

            }

        });

        myStart.addActionListener(e -> {
            if (e.getSource().equals(myStart)) {
                myStart.setEnabled(false);


            }

        });

        myExit.addActionListener(e -> {

            if (e.getSource().equals(myExit)) {
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION);
                if (result == JOptionPane.YES_OPTION) {

                    frame.dispose();

                }
            }
        });


    }


    /**
     * addAllPropertiesListeners is a method that creates
     * actions listeners for all the property changes in the model.
     *
     */
    private void addAllPropertiesListeners() {


    }
/*
    public boolean canPass() {
        //for loop that iterates entire maze
        //if myDoor.getDoorIsLocked then pop up a question
        if (myCharacter.getRow() == 0 && myCharacter.getRow() == 0){
            return false;
    }else{
        return true;

    }

}


 */
    /*
String direction = getEnteredDirection(theEvent.getSource());
    Integer questionId = myRoom[myCharacter.getRow()][myCharacter.getCol()].getQuestionForDoor(direction);
                if (questionId != null) {
        // Fetch the question details from the database
        Question question = getQuestionById(questionId);

        if (question != null) {
            // Update the question panel with the new Question object
            questionPanel.updateQuestion(question);
        }

    }

     */

    public void addSubmitButtonListener() {
        questionPanel.addSubmitButtonListener(e -> {
           // String selectedOption = questionPanel.getSelectedOption();
            String selectedOption = questionPanel.getSelectedAnswer();
            String answerFieldText = questionPanel.getAnswerFieldText();
            if (selectedOption != null) {
             // canPass(selectedOption);
             //   // For multiple choice and true/false questions
                System.out.println("Selected Option in sumbitButton: " + selectedOption);
            } else if (answerFieldText != null && !answerFieldText.isEmpty()) {
                // For short answer questions
                System.out.println("Answer: " + answerFieldText);
                //canPass(answerFieldText);
            } else {
                JOptionPane.showMessageDialog(this, "Please select or enter an answer.", "Error", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
            }
        });

    }

    private void showHint() {
        int row = myCharacter.getRow();
        int col = myCharacter.getCol();


        if ((row == 0 && col == 4) ||
                (row == 2 && col == 1) ||
                (row == 3 && col == 3) ||
                (row == 4 && col == 0)) {

            if (row == 0 && col == 4) {
                roomFourPotion();

            } else if (row == 2 && col == 1) {
                roomElevenPotion();

            } else if (row == 3 && col == 3) {
                roomEighteenPotion();

            } else if (row == 4 && col == 0) {
                roomTwentyPotion();

            }

        }
    }

    public void roomFourPotion(){
        JOptionPane.showMessageDialog(this, "Hints\nLeft Door: \n Down door: ", "Room 4 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }
    public void roomElevenPotion(){
        JOptionPane.showMessageDialog(this, "Hints\n Left Door: \nRight Door: \n Up door: \n Down door: \n", "Room 11 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }

    public void roomTwentyPotion(){
        JOptionPane.showMessageDialog(this, "Hints\n Up Door: \nRight Door:", "Room 20 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }

    public void roomEighteenPotion(){
        JOptionPane.showMessageDialog(this, "Hints: \n Left Door: \nRight Door: \n. Up door: \n Down door: \n", "Room 18 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }
/*
    private boolean canPass(String direction) {
        int questionId = myRoom[myCharacter.getRow()][myCharacter.getCol()].getQuestionForDoor(direction);
        if (questionId != -1) {
            Question question = getQuestionById(questionId);
            //display question
            questionPanel.updateQuestion(question);
          //  int response = JOptionPane.showConfirmDialog(this, question.getQuestionText(), "Answer the question", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
           // if (response == JOptionPane.YES_OPTION) {
                //String selectedAnswer = questionPanel.getSelectedOption();
            String selectedAnswer = questionPanel.getSelectedAnswer();
           System.out.println("Correct Answer in canpass: " + question.getAnswer() );
            System.out.println("Selected Answer in canpass: " + selectedAnswer);
                if (question.getAnswer().equalsIgnoreCase(selectedAnswer)) {
                    System.out.println("Correct Answer: " + question.getAnswer() );
                    System.out.println("Answer equals: "  + question.getAnswer().equalsIgnoreCase(selectedAnswer));
                    JOptionPane.showMessageDialog(this, "Correct Answer! You may now proceed through this door.", "Door Unlocked", JOptionPane.INFORMATION_MESSAGE);
                    myRoom[myCharacter.getRow()][myCharacter.getCol()].unlockDoor(direction);
                    return true;
                } else {
                    JOptionPane.showMessageDialog(this, "Incorrect Answer! This door is now locked.", "Door locked", JOptionPane.INFORMATION_MESSAGE);
                    myRoom[myCharacter.getRow()][myCharacter.getCol()].lockDoor(direction);
                    checkGameOver();
                    return false;
                }
           // }
        }
        return true;
    }


 */

    private boolean canPass(String selectedAnswer, Question question) {
        if (question.getAnswer().equalsIgnoreCase(selectedAnswer)) {
            correctAnswerPanel();
            return true;
        } else {
            return false;
        }
    }

    public void disableLeftArrow(){
        myArrowsPanel.setEnabledLeft(false);
    }
    public void disableRightArrow(){
        myArrowsPanel.setEnabledRight(false);
    }
    public void disableUpArrow(){
        myArrowsPanel.setEnabledUp(false);
    }
    public void disableDownArrow(){
        myArrowsPanel.setEnabledDown(false);
    }
    public void enableUpArrow(){
        if(  myCharacter.getRow() == 0){
            myArrowsPanel.setEnabledUp(false);
        }else {
            myArrowsPanel.setEnabledUp(true);

        }

    }

    public void enableDownArrow(){
        if( myCharacter.getRow() == 4){
            myArrowsPanel.setEnabledDown(false);
        }else {
            myArrowsPanel.setEnabledDown(true);
        }
    }

    public void enableLeftArrow(){
        if( myCharacter.getCol() == 0){
            myArrowsPanel.setEnabledLeft(false);
        }else {
            myArrowsPanel.setEnabledLeft(true);

        }

    }


    public void enableRightArrow(){
        if( myCharacter.getCol() == 4){
            myArrowsPanel.setEnabledRight(false);
        }else {
            myArrowsPanel.setEnabledRight(true);

        }

    }
    //the winning/exit square is located at 4, 4

    public void checkWon(){
        if(myCharacter.getRow() == 4 && myCharacter.getCol() == 4 ){
            JOptionPane.showMessageDialog(frame, "You won the game!!", "Game Won", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
            disableAllArrows();
            questionPanel.getMySubmit().setEnabled(false);
            setWinSound();
        }
    }


    public static void gameLogic(){

    }


    private void setWrongAnswerSound() {
        try {
            //set path file to this when submiting final project
            //String fileName = "sounds/applause10.wav";
            String fileName = "/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/Sounds/wrongBuzzer.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    private void setWinSound() {
        try {
            //set path file to this when submiting final project
            //String fileName = "sounds/applause10.wav";
            String fileName = "/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/Sounds/gameWon.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void setLoseSound() {
        try {
            //String fileName = "sounds/applause10.wav";
            String fileName = "/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/Sounds/gameLost.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void setDoorOpenSound() {
        try {
            //String fileName = "sounds/applause10.wav";
            String fileName = "Sounds/doorOpen.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] theArgs){

            SwingUtilities.invokeLater(() -> {
                new QuestionFactoryF();
                new TriviaController(TriviaModel.getMyTriviaInstance());


            });

        }


}
