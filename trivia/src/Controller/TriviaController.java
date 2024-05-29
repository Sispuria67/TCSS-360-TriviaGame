package Controller;

import Model.*;
import View.ArrowsPanel;
import View.CurrentRoomPanel;
import View.MazePanel;
import View.QuestionPanel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import static Model.QuestionFactoryF.getQuestionById;


public class TriviaController extends JPanel {

    ImageIcon img = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/doorPixel.png");

    Image resizedImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resizedImage);




    private JFrame frame = new JFrame("Trivia Game");
    private JMenu myMenu;

    private final ArrowsPanel myArrowsPanel;
    private final QuestionPanel questionPanel;


    private final CharacterModel myCharacter;

    private final MazePanel myMazePanel;
    JLabel myText;

    private final Door myDoor;

    private Room[][] myRoom ;

    private CurrentRoomPanel myCurrentRoomPanel;



    private JMenu myMenu2;

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


        myArrowsPanel = new ArrowsPanel();
        questionPanel = new QuestionPanel();
        myMazePanel = new MazePanel();
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myCurrentRoomPanel = new CurrentRoomPanel();
        myRoom = myMazePanel.getRoom();
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

        myCurrentRoomPanel.setPreferredSize(new Dimension(100, 100));
        questionPanel.setPreferredSize(new Dimension(100, 180));
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


        ImageIcon icon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/doorPixel.png");
        frame.setIconImage(icon.getImage());
      //  frame.setBackground(Color.CYAN);


        SwingUtilities.invokeLater(() -> {
            frame.setVisible(true);
        });

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



        myAbout.addActionListener(e -> JOptionPane.showMessageDialog(frame, "This is a Trivia Game \nJava Version: 21.0\nAuthor: Rohit Ark, Sado Iman\n ", "About", JOptionPane.ERROR_MESSAGE));

        myRules.addActionListener(e -> JOptionPane.showMessageDialog(frame,
                "NA.",
                "Rules", JOptionPane.ERROR_MESSAGE));
    }

    /*
    public String compare(){
        String temp2;
        if (factory.getAnswerResult().equals("1")){
            temp2 = "works";
        } else {
            temp2 = "no works";
        }
        return temp2;
    }


     */
    private void addCurrentArrowListeners() {
        myArrowsPanel.addArrowListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent theEvent) {

                //add && canPass
               // if (theEvent.getSource().equals(myArrowsPanel.getMyRightArrow()) && canPass()) {
                //&& getRightDoor != false (so a door does exist to the right
               // if (theEvent.getSource().equals(myArrowsPanel.getMyRightArrow()) && !myDoor.getDoorIsLocked()) {
                //&&  myRoom.getRightDoor()
                if (theEvent.getSource().equals(myArrowsPanel.getMyRightArrow())&& myRoom[myCharacter.getRow()][myCharacter.getCol()].getRightDoor()) {
                    myCharacter.moveRight();
                    myMazePanel.moveCharacter("right");
                    //myCurrentRoomPanel.setMyTextField("You are currently in room "+  myCharacter.toString());

                    myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                    enableUpArrow();
                    enableDownArrow();
                    enableLeftArrow();
                    enableRightArrow();
                    checkWon();
                    correctAnswerPanel();


                    //System.out.println("room::::" + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                } else if (theEvent.getSource().equals(myArrowsPanel.getMyLeftArrow()) && myRoom[myCharacter.getRow()][myCharacter.getCol()].getLeftDoor()) {
                    //myMazePanel.character.moveLeft();
                    myCharacter.moveLeft();
                    myMazePanel.moveCharacter("left");
                   // myCurrentRoomPanel.setMyTextField("You are currently in room "+  myRoom[myCharacter.getRow()][myCharacter.getCol()].toString());
                    myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                    enableUpArrow();
                    enableDownArrow();
                    enableLeftArrow();
                    enableRightArrow();
                    checkWon();

                }

                //and check if space is valid
                else if (theEvent.getSource().equals(myArrowsPanel.getMyDownArrow()) && myRoom[myCharacter.getRow()][myCharacter.getCol()].getDownDoor()) {
                    //myMazePanel.myCharacter.moveDown();
                    myCharacter.moveDown();
                    myMazePanel.moveCharacter("down");
                    //myCurrentRoomPanel.setMyTextField("You are currently in room "+  myRoom[myCharacter.getRow()][myCharacter.getCol()].toString());
                    myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                    enableUpArrow();
                    enableDownArrow();
                    enableLeftArrow();
                    enableRightArrow();
                    checkWon();
                    incorrectAnswerPanel();



                } else if (theEvent.getSource().equals(myArrowsPanel.getMyUpArrow()) && myRoom[myCharacter.getRow()][myCharacter.getCol()].getUpDoor()) {
                    myCharacter.moveUp();
                    myMazePanel.moveCharacter("up");
                    //myCurrentRoomPanel.setMyTextField("You are currently in room "+  myRoom[myCharacter.getRow()][myCharacter.getCol()].toString());
                    myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

                    enableUpArrow();
                    enableDownArrow();
                    enableLeftArrow();
                    enableRightArrow();
                    checkWon();
                }

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

            }
        });
    }
    // Method to fetch question text based on questionId
    private String getQuestionText(int questionId) {
        return QuestionFactoryF.getQuestionTextById(questionId);
    }
    private String getEnteredDirection(Object arrowButton) {
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
                this, "Correct Answer! You may now proceed through this door.", "Door Unlocked", JOptionPane.INFORMATION_MESSAGE, resizedIcon);

    }

/*
    //if you select first radio button and touch submit, Joption pane pops up and says right answer
    private void addRadioListeners() {
        myQuestionPanel.getMyRadioOne().addActionListener(e -> {
            if (e.getSource().equals(myQuestionPanel.getMyRadioOne())) {
                myQuestionPanel.getMySubmit().addActionListener(p -> {
                    if (p.getSource().equals(myQuestionPanel.getMySubmit())) {
                        JOptionPane.showMessageDialog(frame, "Yes, correct answer!");
                    }
                });

            }
        });
    }


 */

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

    public void assignQuestionsToDoors(){
        //room 0
        myRoom[0][0].setQuestionForDoor("right", 1);
        myRoom[0][0].setQuestionForDoor("down", 20);

        //room 1
        myRoom[0][1].setQuestionForDoor("right", 3);
        myRoom[0][1].setQuestionForDoor("down", 29);
        myRoom[0][1].setQuestionForDoor("left", 1);

        //room 2
        myRoom[0][2].setQuestionForDoor("right", 16);
        myRoom[0][2].setQuestionForDoor("down", 38);
        myRoom[0][2].setQuestionForDoor("left", 3);

        //room 3
        myRoom[0][3].setQuestionForDoor("right", 19);
        myRoom[0][3].setQuestionForDoor("down", 14);
        myRoom[0][3].setQuestionForDoor("left", 16);

        //room 4
        myRoom[0][4].setQuestionForDoor("right", 30);
        myRoom[0][4].setQuestionForDoor("down", 39);
        myRoom[0][4].setQuestionForDoor("left", 19);


        //room 5
        myRoom[1][0].setQuestionForDoor("right", 15);
        myRoom[1][0].setQuestionForDoor("down", 23);
        myRoom[1][0].setQuestionForDoor("up", 40);

        //room 6
        myRoom[1][1].setQuestionForDoor("right", 5);
        myRoom[1][1].setQuestionForDoor("down", 11);
        myRoom[1][1].setQuestionForDoor("left", 1);
        myRoom[1][1].setQuestionForDoor("up", 29);

        //room 7
        myRoom[1][2].setQuestionForDoor("right", 25);
        myRoom[1][2].setQuestionForDoor("down", 15);
        myRoom[1][2].setQuestionForDoor("left", 4);
        myRoom[1][2].setQuestionForDoor("up", 32);

        //room 8
        myRoom[1][3].setQuestionForDoor("right", 19);
        myRoom[1][3].setQuestionForDoor("down", 24);
        myRoom[1][3].setQuestionForDoor("left", 35);
        myRoom[1][3].setQuestionForDoor("up", 36);

        //room 9
        myRoom[1][4].setQuestionForDoor("down", 9);
        myRoom[1][4].setQuestionForDoor("left", 12);
        myRoom[1][4].setQuestionForDoor("up", 7);
    }


    /**
     * addAllPropertiesListeners is a method that creates
     * actions listeners for all the property changes in the model.
     *
     */
    private void addAllPropertiesListeners() {


    }

    public boolean canPass() {
        //for loop that iterates entire maze
        //if myDoor.getDoorIsLocked then pop up a question
        if (myCharacter.getRow() == 0 && myCharacter.getRow() == 0){
            return false;
    }else{
        return true;

    }

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
            JOptionPane.showMessageDialog(frame, "You won the game!!");

        }
    }


    public static void gameLogic(){

    }

    public void startGame() {
        while(myCharacter.getRow() != 4 && myCharacter.getCol() != 4){

        }
    }

    public static void main(String[] theArgs){

            SwingUtilities.invokeLater(() -> {
                new QuestionFactoryF();
                new TriviaController(TriviaModel.getMyTriviaInstance());


            });

        }


}
