package Controller;

import javax.swing.*;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


import Model.Door;
import Model.Room;
import Model.TriviaModel;
import Model.CharacterModel;
import View.ArrowsPanel;
import View.CurrentRoomPanel;
import View.QuestionPanel;
import View.MazePanel;



public class TriviaController extends JPanel {

    private JFrame frame = new JFrame();
    private JMenu myMenu;

    private final ArrowsPanel myArrowsPanel;
    private final QuestionPanel myQuestionPanel;


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

    public TriviaController(){
        myArrowsPanel = new ArrowsPanel();
        myQuestionPanel = new QuestionPanel();
        myMazePanel = new MazePanel();
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myCurrentRoomPanel = new CurrentRoomPanel();
        myRoom = myMazePanel.getRoom();
        myCurrentRoomPanel.setMyTextField("You are currently in Room 0");

        myText = new JLabel();
        createAndShowGUI();
        createMenuBar();
        layoutComponents();
        addCurrentArrowListeners();
        addMenuListeners();
        addRadioListeners();
    }

    public TriviaController(final TriviaModel theModel) {


        // super(new GridLayout(2, 1));
        // super(new BorderLayout());

        // factory = new QuestionFactory();

        myArrowsPanel = new ArrowsPanel();
        myQuestionPanel = new QuestionPanel();
        myMazePanel = new MazePanel();
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myCurrentRoomPanel = new CurrentRoomPanel();
        myRoom = myMazePanel.getRoom();
        myCurrentRoomPanel.setMyTextField("You are currently in Room 0");

        myText = new JLabel();
        createAndShowGUI();
        createMenuBar();
        layoutComponents();
        addCurrentArrowListeners();
        addMenuListeners();
        addRadioListeners();


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

        JPanel mazeAndArrowsPanel = new JPanel(new BorderLayout());
        mazeAndArrowsPanel.add(myMazePanel, BorderLayout.CENTER);
        mazeAndArrowsPanel.add(myArrowsPanel, BorderLayout.EAST);
        mazeAndArrowsPanel.add(myCurrentRoomPanel, BorderLayout.SOUTH);
       // mazeAndArrowsPanel.add(myQuestionPanel, BorderLayout.SOUTH);

        setLayout(new BorderLayout());

        add(mazeAndArrowsPanel, BorderLayout.NORTH);


        add(myQuestionPanel, BorderLayout.SOUTH);

        myCurrentRoomPanel.setPreferredSize(new Dimension(100, 100));
        myQuestionPanel.setPreferredSize(new Dimension(100, 180));
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
        frame.setSize(800, 830);
        frame.setTitle("Trivia Game");


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
            }
        });
    }


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

    public static void main(String[] theArgs){

            SwingUtilities.invokeLater(() -> {
                new TriviaController(TriviaModel.getMyTriviaInstance());


            });

        }


}
