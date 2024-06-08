package Controller;

import Model.*;
import View.*;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;

import static Model.QuestionFactoryF.getQuestionById;


public class TriviaController extends JPanel implements Serializable{

    private String lastEnteredDirection = "";

    ImageIcon img = new ImageIcon("trivia/src/Images/doorPixel.png");

    ImageIcon questionMark = new ImageIcon("trivia/src/Images/questionMark.png");

    ImageIcon celebrationIcon = new ImageIcon("trivia/src/Images/celebration.png");

    ImageIcon exitIcon = new ImageIcon("trivia/src/Images/exit.png");
    ImageIcon lockDoorIcon = new ImageIcon("trivia/src/Images/lockedDoor.png");


    Image resizedImage = img.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon resizedIcon = new ImageIcon(resizedImage);

    Image resizedLock = lockDoorIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon lockedDoor = new ImageIcon(resizedLock);


    Image resizedQuestion = questionMark.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon questionIcon = new ImageIcon(resizedQuestion);

    Image resizedCelebration = celebrationIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon celebration = new ImageIcon(resizedCelebration);

    Image resizedExit = exitIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
    ImageIcon exitImg= new ImageIcon(resizedExit);

    private JFrame frame = new JFrame("Trivia Game");
    private JMenu myMenu;

   // private final TitleScreen myTitleScreen;

    private int myNewCount;

    private final ArrowsPanel myArrowsPanel;
    private final QuestionPanel questionPanel;

    private final TriviaModel myTriviaModel;
    private JOptionPane myFailed = new JOptionPane();
    private JButton myFailButton= new JButton("Play Again");


    private static CharacterModel myCharacter;

    private static MazePanel myMazePanel;
    JLabel myText;

    private final Door myDoor;

    private Room[][] myRoom ;

    private final JButton myPlayAgainButton;

    private static CurrentRoomPanel myCurrentRoomPanel;

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
    private static final String DOOR_FILE = "door.ser";
    private static final String ROOM_FILE = "room.ser";
    private static final String QUESTION_FILE = "question.ser";

    private static final String MAZE_FILE = "maze.ser";

    private static final String CHARACTER_FILE = "char.ser";

    private static Door myDoor2 = new Door();
    private static Room[][] myRoom2;
    private static QuestionFactoryF myQuestionFactory2 = new QuestionFactoryF();


    private static MazePanel myMazePanel2 = new MazePanel(TriviaModel.getMyTriviaInstance());

    private static CharacterModel myCharacter2 = new CharacterModel(0, 0);
/*
    public TriviaController(){
        myArrowsPanel = new ArrowsPanel();
        myQuestionPanel = new QuestionPanel();
        myMazePanel = new MazePanel();
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myCurrentRoomPanel = new CurrentRoomPanel();
        myRoom = myMazePanel.getRoom();
        myRoom2 = myMazePanel.getRoom();
        myCurrentRoomPanel.setMyTextField("You are currently in Room 0");

        myText = new JLabel();
        createAndShowGUI();
        createMenuBar();
        layoutComponents();
        addCurrentArrowListeners();
        addMenuListeners();
        addRadioListeners();
    }


 */
    public TriviaController(final TriviaModel theModel) {
      //  myTitleScreen = new TitleScreen();



        // super(new GridLayout(2, 1));
        // super(new BorderLayout());

        // factory = new QuestionFactory();

        myTriviaModel = theModel;

        myArrowsPanel = new ArrowsPanel(theModel);
        questionPanel = new QuestionPanel();
        myMazePanel = new MazePanel(theModel);
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myRoom = myMazePanel.getRoom();
        myCurrentRoomPanel = new CurrentRoomPanel(theModel);
        myNewCount = 0;

        myPlayAgainButton = new JButton("Play Again");



        myPlayAgainButton.setEnabled(false);

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
        //addSubmitButtonListener();
        addCurrentArrowListeners();
        addPlayAgainButtonListener();
        addMenuListeners();



    }


    private void layoutComponents() {


        //setting background colors
      //  myCurrentRoomPanel.setBackground(Color.pink);
      //  myMazePanel.setBackground(Color.PINK);
       // myArrowsPanel.setBackground(Color.PINK);
      //  questionPanel.setBackground(Color.cyan);

        myPlayAgainButton.setPreferredSize(new Dimension(100, 50));
        myPlayAgainButton.setFont(new Font("Monospaced", Font.BOLD, 16));

        JPanel mazeAndArrowsPanel = new JPanel(new BorderLayout());
        mazeAndArrowsPanel.add(myMazePanel, BorderLayout.CENTER);
        mazeAndArrowsPanel.add(myArrowsPanel, BorderLayout.EAST);
       // mazeAndArrowsPanel.add(myPlayAgainButton, BorderLayout.NORTH);
        mazeAndArrowsPanel.add(myCurrentRoomPanel, BorderLayout.SOUTH);
       // mazeAndArrowsPanel.add(myQuestionPanel, BorderLayout.SOUTH);




        setLayout(new BorderLayout());

        add(mazeAndArrowsPanel, BorderLayout.NORTH);


        add(questionPanel, BorderLayout.SOUTH);

        myCurrentRoomPanel.setPreferredSize(new Dimension(100, 80));
        questionPanel.setPreferredSize(new Dimension(100, 205));
        // JPanel myTopPanel = new JPanel(new GridLayout(1, 1, 10, 10));

    }

    public void createAndShowGUI() {

        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setContentPane(this);
        frame.requestFocusInWindow();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setSize(900, 830);
        frame.setTitle("Trivia Game");


       icon = new ImageIcon("trivia/src/Images/doorPixel.png");
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
                        " \n 3. If you get the answer the door will be locked and must find another door to pass through. \n 4. If all the doors are locked, you lose. \n 5. There are hints in certain rooms that can help you answer the questions for those rooms.",
                "Rules", JOptionPane.ERROR_MESSAGE, icon));
    
        myReset.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadObjects();
            }
        });
        myStart.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveObjects();
            }
        });
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




//    private static void loadObjects() {
//        //load Door object
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOOR_FILE))) {
//            myDoor2 = (Door) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        int lastRow = 0;
//        int lastCol = 0;
//
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CHARACTER_FILE))) {
//            lastRow = ois.readInt();
//            lastCol = ois.readInt();
//        } catch (IOException e){
//            e.printStackTrace();
//        }
//
//        myCharacter.setRow(lastRow);
//        myCharacter.setCol(lastCol);
//
//        // load Room object
//        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROOM_FILE))) {
//            myRoom2 = (Room[][]) ois.readObject();
//        } catch (IOException | ClassNotFoundException e) {
//            e.printStackTrace();
//        }
//
//        // load Maze object
////        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MAZE_FILE))) {
////            myMazePanel = (MazePanel) ois.readObject();
////        } catch (IOException | ClassNotFoundException e) {
////            e.printStackTrace();
////        }
//
//
//        myMazePanel.setRoom(myRoom2);
//        myCharacter.setCurrentRoom(myRoom2);
//
//
//        myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom2[lastRow][lastCol].getRoomName());
//
//        myMazePanel.repaint();
//        System.out.println(  "repaineted:");
//    }
private static void loadObjects() {
    //load Door object
    try (FileInputStream fi = new FileInputStream(DOOR_FILE)) {
        ObjectInputStream ois = new ObjectInputStream(fi);
        myDoor2 = (Door) ois.readObject();
        System.out.println("Door object deserialized successfully.");
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }

    int lastRow = 0;
    int lastCol = 0;

    try (FileInputStream fi = new FileInputStream(CHARACTER_FILE)) {
        ObjectInputStream ois = new ObjectInputStream(fi);
        lastRow = ois.readInt();
        lastCol = ois.readInt();
        System.out.println("Character position deserialized successfully.");
    } catch (IOException e){
        e.printStackTrace();
    }

    myCharacter.setRow(lastRow);
    myCharacter.setCol(lastCol);

    // load Room object
    try (FileInputStream fi = new FileInputStream(ROOM_FILE)) {
        ObjectInputStream ois = new ObjectInputStream(fi);
        myRoom2 = (Room[][]) ois.readObject();
        System.out.println("Room object deserialized successfully.");
//        if (myRoom2 == null) {
//            System.out.println("myRoom2 is null after deserialization.");
//        } else {
//            System.out.println("myRoom2 length: " + myRoom2.length);
//            if (myRoom2.length > 0) {
//                System.out.println("myRoom2[0] length: " + myRoom2[0].length);
//            }
//        }
    } catch (IOException | ClassNotFoundException e) {
        e.printStackTrace();
    }
//    if (myRoom2 == null || myRoom2.length == 0 || myRoom2[0].length == 0) {
//        throw new NullPointerException("myRoom2 is null or empty after deserialization.");
//    }

    // load Maze object
    try (FileInputStream fi = new FileInputStream(MAZE_FILE)) {
        ObjectInputStream ois = new ObjectInputStream(fi);
            myMazePanel = (MazePanel) ois.readObject();
        System.out.println("MazePanel object deserialized successfully.");
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
//    if (myRoom2 == null) {
//        throw new NullPointerException("myRoom2 is null after deserialization.");
//    }


    myMazePanel.setRoom(myRoom2);
    myCharacter.setCurrentRoom(myRoom2);


    myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom2[myCharacter.getRow()][myCharacter.getCol()].getRoomName());

    myMazePanel.repaint();
    System.out.println(  "repaineted:");
}
    private static void saveObjects() {
        // Serialize Door object
        try (FileOutputStream fs = new FileOutputStream(DOOR_FILE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(myDoor2);
            oos.close();
            fs.close();
            System.out.println("Door object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fs = new FileOutputStream(CHARACTER_FILE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeInt(myCharacter.getRow());
            oos.writeInt(myCharacter.getCol());
            oos.close();
            fs.close();
            System.out.println("Character position saved");
        } catch (IOException e) {
            e.printStackTrace();
        }
        // Serialize Room object
        try (FileOutputStream fs = new FileOutputStream(ROOM_FILE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(myRoom2);
            oos.close();
            fs.close();
            System.out.println("Room object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (FileOutputStream fs = new FileOutputStream(MAZE_FILE)) {
            ObjectOutputStream oos = new ObjectOutputStream(fs);
            oos.writeObject(myMazePanel);
            oos.close();
            fs.close();
            System.out.println("MazePanel object has been serialized");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
//    private static void saveObjects() {
//        // Serialize Door object
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOOR_FILE))) {
//            oos.writeObject(myDoor2);
//            oos.close();
//            System.out.println("Door object has been serialized");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CHARACTER_FILE))) {
//            oos.writeInt(myCharacter.getRow());
//            oos.writeInt(myCharacter.getCol());
//            oos.close();
//            System.out.println("Character position saved");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//        // Serialize Room object
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOM_FILE))) {
//            oos.writeObject(myRoom2);
//            oos.close();
//            System.out.println("Room object has been serialized");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }

        // Serialize MAze object
//        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MAZE_FILE))) {
//            oos.writeObject(myMazePanel);
//            oos.close();
//            System.out.println("MazePanel object has been serialized");
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
//    }
    private void addCurrentArrowListeners() {
        myArrowsPanel.clearArrowPanelListeners();

        myArrowsPanel.addArrowListener(e -> {

            String direction = getEnteredDirection(e.getSource());
            System.out.print("String directon: " + direction);
            int questionId = myRoom[myCharacter.getRow()][myCharacter.getCol()].getQuestionForDoor(direction);
            //checkGameOver();

            if (isDoorLocked(direction) ){
                showLockedDoorMessage();
                return;
            }
            if (questionId != -1) {
                Question question = getQuestionById(questionId);
                questionPanel.updateQuestion(question);

                // a temporary listener for the sumbit button
                questionPanel.addSubmitButtonListener(submitEvent -> {
                 String selectedAnswer = questionPanel.getSelectedAnswer();


                    if(selectedAnswer!= null && !selectedAnswer.isEmpty()) {
                       // !myRoom[myCharacter.getRow()][myCharacter.getCol()].getUpDoor().isLocked()
                        //the door in that direction is not locked, this is only checking right door

                if (canPass(selectedAnswer, question) && !myRoom[myCharacter.getRow()][myCharacter.getCol()].getDoor(direction).isLocked()){

                            setDoorOpenSound();
                            moveCharacter(direction);
                            questionPanel.clearSubmitButtonListeners(); //clear listener
                   // myArrowsPanel.clearArrowPanelListeners();
                     //   } else if(!question.getAnswer().equalsIgnoreCase(selectedAnswer)) {
                } else {
                    lockDoor(direction);
                   // setWrongAnswerSound();
                    incorrectAnswerPanel();
                   // myArrowsPanel.clearArrowPanelListeners();
                            //incorrectAnswerPanel();
                            questionPanel.clearSubmitButtonListeners(); //clear the listener after use
                            //lock the door and if player tries to go on on this door, joption pops up tp say the door is closed
                       // }else {
                   // questionPanel.clearSubmitButtonListeners(); //clear the listener after use

                }
                    } else {
                       // questionPanel.clearSubmitButtonListeners();
                       // myArrowsPanel.clearArrowPanelListeners();

                     //   checkGameOver();
                        //duplicated, also in submit button
                        //JOptionPane.showMessageDialog(this, "Please select or enter an answer.", "Error", JOptionPane.INFORMATION_MESSAGE, resizedIcon);
                    }

                    checkGameOver();
                });


            } else {

                moveCharacter(direction); // no question for the door, so move character
                //checkGameOver();
            }

        });
       // checkGameOver(); //doesnt execute

    }



 private boolean isDoorLocked(String direction) {
     int row = myCharacter.getRow();
     int col = myCharacter.getCol();

     switch (direction) {
         case "right":
             return myRoom[row][col].getRightDoor() != null && myRoom[row][col].getRightDoor().isLocked();
         case "left":
             return myRoom[row][col].getLeftDoor() != null && myRoom[row][col].getLeftDoor().isLocked();
         case "down":
             return myRoom[row][col].getDownDoor() != null && myRoom[row][col].getDownDoor().isLocked();
         case "up":
             return myRoom[row][col].getUpDoor() != null && myRoom[row][col].getUpDoor().isLocked();
     }
     return false;
 }


    private void moveCharacter(String direction) {
        System.out.println("Entered move character method");
        System.out.println("Move character method directon: " + direction);

        int row = myCharacter.getRow();
        int col = myCharacter.getCol();
        boolean canMove = false;

        switch (direction) {
            case "right":
                if (myRoom[row][col].getRightDoor() != null ) {

                  if(!myRoom[row][col].getRightDoor().isLocked())
                    myRoom[row][col].getRightDoor().setDoorStatus(Door.LOCKED);
                    System.out.println("Right door locked? " + myRoom[row][col].getRightDoor().isLocked() );
                    myCharacter.moveRight();
                   // myRoom[row][col].getRightDoor().setDoorStatus(Door.LOCKED);
                    myMazePanel.moveCharacter("right");
                    canMove = true;

                  //  myArrowsPanel.setEnabledRight(false);  // disable right arrow
                   // showQuestionAlreadyAnsweredMessage();
                } else if(myRoom[row][col].getRightDoor().isLocked()){
                    showQuestionAlreadyAnsweredMessage();
                    System.out.println("Right door locked? " + myRoom[row][col].getRightDoor().isLocked() );
                   // showLockedDoorMessage();
                }
                break;
            case "left":

                if (myRoom[row][col].getLeftDoor() != null){
                  if(!myRoom[row][col].getLeftDoor().isLocked())
                    myRoom[row][col].getLeftDoor().setDoorStatus(Door.LOCKED);
                    System.out.println("Left door locked? " + myRoom[row][col].getLeftDoor().isLocked() );
                    myCharacter.moveLeft();
                    myMazePanel.moveCharacter("left");
                    canMove = true;
                   // myRoom[row][col].getLeftDoor().setDoorStatus(Door.LOCKED);
                   // myArrowsPanel.setEnabledLeft(false);
                   // showQuestionAlreadyAnsweredMessage();
                } else {
                    System.out.println("Left door locked? " + myRoom[row][col].getLeftDoor().isLocked() );
                    //showLockedDoorMessage();
                }
                break;
            case "down":

                if (myRoom[row][col].getDownDoor() != null)
                    if(!myRoom[row][col].getDownDoor().isLocked()) {
                    myRoom[row][col].getDownDoor().setDoorStatus(Door.LOCKED);
                    System.out.println("Down door locked? " + myRoom[row][col].getDownDoor().isLocked() );
                    myCharacter.moveDown();
                    myMazePanel.moveCharacter("down");
                    canMove = true;
                      //  myRoom[row][col].getDownDoor().setDoorStatus(Door.LOCKED);

                   // myArrowsPanel.setEnabledDown(false);
                    //showQuestionAlreadyAnsweredMessage();
                } else {
                    System.out.println("Down door locked? " + myRoom[row][col].getDownDoor().isLocked());
                   // showLockedDoorMessage();

                }
                break;
            case "up":

                if (myRoom[row][col].getUpDoor() != null)
                    if(!myRoom[row][col].getUpDoor().isLocked()) {
                    myRoom[row][col].getUpDoor().setDoorStatus(Door.LOCKED);
                    System.out.println("Up door locked? " + myRoom[row][col].getUpDoor().isLocked() );
                    myCharacter.moveUp();
                    myMazePanel.moveCharacter("up");
                    canMove = true;
                       // myRoom[row][col].getUpDoor().setDoorStatus(Door.LOCKED);

                    //myArrowsPanel.setEnabledUp(false);
                    //showQuestionAlreadyAnsweredMessage();
                } else {
                    System.out.println("Up door locked? " + myRoom[row][col].getUpDoor().isLocked() );
                   // showLockedDoorMessage();
                }
                break;
        }

        if (canMove) {
            System.out.println("Current location: " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());
            updateCurrentRoomPanel();
            enableAllArrows();
            checkWon();
           // checkGameOver();
            showHint();
        }
    }

    private void showQuestionAlreadyAnsweredMessage() {
        JOptionPane.showMessageDialog(frame, "You have already answered the question for this door.", "Question Answered", JOptionPane.INFORMATION_MESSAGE);
    }


    private void showLockedDoorMessage() {
        JOptionPane.showMessageDialog(frame, "This door is locked.", "Locked Door", JOptionPane.INFORMATION_MESSAGE, lockedDoor);
    }



    private void checkGameOver() {

        boolean allDoorsLocked = true;
        int row = myCharacter.getRow();
        int col = myCharacter.getCol();

        if (myRoom[row][col].getUpDoor() != null && !myRoom[row][col].getUpDoor().isLocked())
            allDoorsLocked = false;
        if (myRoom[row][col].getDownDoor() != null && !myRoom[row][col].getDownDoor().isLocked())
            allDoorsLocked = false;
        if (myRoom[row][col].getLeftDoor() != null && !myRoom[row][col].getLeftDoor().isLocked())
            allDoorsLocked = false;
        if (myRoom[row][col].getRightDoor() != null && !myRoom[row][col].getRightDoor().isLocked())
            allDoorsLocked = false;

        if (allDoorsLocked) {
            setLoseSound();
            //JOptionPane.showMessageDialog(frame, "Game Over! You are stuck with no way out.", "Game Over", JOptionPane.INFORMATION_MESSAGE, lockedDoor);
            newGameOption();
            disableAllArrows();
            //when game is over enable play again button again, set enabled true.
            myPlayAgainButton.setEnabled(true);

            addPlayAgainButtonListener();
            return;
            //frame.dispose();
        }
    }
    private void newGameOption(){
        myFailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
                resetGame();
                myPlayAgainButton.setEnabled(false);
            }
        });

        myFailed.setMessage("Game Over2! You are stuck with no way out.");
        myFailed.setMessageType(JOptionPane.PLAIN_MESSAGE);
        myFailed.setOptionType(JOptionPane.DEFAULT_OPTION);
        myFailed.add(myFailButton);
        JDialog dialog = myFailed.createDialog("Action Listener Example");
        dialog.setVisible(true);
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
            return "";
        }
    }

    public void incorrectAnswerPanel(){
        setWrongAnswerSound();
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
                int result = JOptionPane.showConfirmDialog(frame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, exitImg);
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

    public void addPlayAgainButtonListener(){
//        myPlayAgainButton.addActionListener(e -> {
//            if (e.getSource().equals(myPlayAgainButton)) {
//                //move character back to sqaure one
//                //reset all questions and answers and doors
//                //unlock all doors
//                myCharacter.setRow(0);
//                myCharacter.setCol(0);
//
//                //repaint character
//
//
//                /*
//                myModel.setMyDice1(0);
//                myModel.setMyDice2(0);
//                myModel.setMyDiceTotal(0);
//                myModel.setMyPoint(0);
//                myCurrentRoll.reset();
//                myModel.setGameOver(false);
//                myCurrentRoll.getRoll().setEnabled(true);
//                myBetPanel.setEnabled(true);
//
//                 */
//
//                myPlayAgainButton.setEnabled(false);
//
//                //enable arrows for room 0 only
//               // enableAllArrows();
//
//            }
//        });
        myPlayAgainButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
                resetGame();
                myPlayAgainButton.setEnabled(false);
            }
        });
    }
    public void addFailedButton(){
        myFailButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                createAndShowGUI();
                resetGame();
                myPlayAgainButton.setEnabled(false);
            }
        });
    }

    private void resetGame(){
        myTriviaModel.reset(); // Reset the game model
        myMazePanel.reset();   // Reset the maze panel (assuming MazePanel has a reset method)
        myCharacter.reset();   // Reset the character

        // Revalidate and repaint the panel to reflect changes
        revalidate();
        repaint();
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
        JOptionPane.showMessageDialog(this, "\nLeft Door: The name of the tallest mountain in the world starts with letter \"E\"\nDown door: Insects have 6 legs, spiders have 8", "Room 4 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }
    public void roomElevenPotion(){
        JOptionPane.showMessageDialog(this, "\nLeft Door: The highest-rated movie on IMDb stars Morgan Freeman & Tim Robbins.\nRight Door: The upcoming olympics games is this summer (2024), then again in 2028.\nUp door: This metal's symbol comes from its Latin name, Aurum.\nDown door: The color of charcoal.", "Room 11 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);

    }

    public void roomTwentyPotion(){
        JOptionPane.showMessageDialog(this, "\nUp Door: The initials of the person who created Java is JG.\nRight Door: Rhymes with River.", "Room 20 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);
    }

    public void roomEighteenPotion(){
        JOptionPane.showMessageDialog(this, "\nLeft Door: This country starts with the letter \"I\".\nRight Door:This city is commonly nicknamed \"the city of love\"\nUp door: This team is from Cleveland.\nDown door: This author also wrote Romeo and Juliet.", "Room 18 Hints", JOptionPane.INFORMATION_MESSAGE, questionIcon);

    }

    private void lockDoor(String direction) {
        Room currentRoom = myRoom[myCharacter.getRow()][myCharacter.getCol()];
        switch (direction) {
            case "right":
                currentRoom.getRightDoor().lock();
                break;
            case "left":
                currentRoom.getLeftDoor().lock();
                break;
            case "up":
                currentRoom.getUpDoor().lock();
                break;
            case "down":
                currentRoom.getDownDoor().lock();
                break;
        }
    }


    private boolean canPass(String selectedAnswer, Question question) {

        questionPanel.updateQuestion(question);
        System.out.println("selected answerss: " + selectedAnswer);
        System.out.println("qiuestion: " + question);
        System.out.println("real answers: " + question.getAnswer());
        if (question.getAnswer().equalsIgnoreCase(selectedAnswer)) {
            correctAnswerPanel();
            return true;
        } else {
            setWrongAnswerSound();
           // incorrectAnswerPanel();
            System.out.println("Cannot PASS!");
           // JOptionPane.showMessageDialog(this, "You cannot go this way, door is locked, find another door.", "Door Locked", JOptionPane.INFORMATION_MESSAGE, questionIcon);

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
            JOptionPane.showMessageDialog(frame, "You won the game!!", "Game Won", JOptionPane.INFORMATION_MESSAGE, celebration);
            disableAllArrows();
            questionPanel.getMySubmit().setEnabled(false);
            setWinSound();
        }
    }




    private void setWrongAnswerSound() {
        try {

            String fileName = "Sounds/wrongBuzzer.wav";
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
            String fileName = "Sounds/gameWon.wav";
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

            String fileName = "Sounds/gameLost.wav";
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
                new TitleScreen();

                new QuestionFactoryF();
              //  new TriviaController(TriviaModel.getMyTriviaInstance());


            });

        }


}
