/**
 * A package for controller.
 */
package Controller;

import Model.*;
import View.*;
import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import static Model.QuestionFactoryF.getQuestionById;

/**
 * TriviaController is a class that acts as the controller
 * for the trivia game and handles the game logic and runs the main frame.
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class TriviaController extends JPanel {

    /** A private field for character image file*/
    private static String IMAGE_FILE;

    /** A private field to keep track of questions answered */
    private ArrayList<Integer> myAnsweredQuestionIds;
    
    /** A private field for door image file*/
    private ImageIcon myImg;

    /** A private field for question mark image file*/
    private ImageIcon myQuestionMark;

    /** A private field for game won celebration image file*/
    private ImageIcon myCelebrationIcon;

    /** A private field for exit image file*/
    private ImageIcon myExitIcon;

    /** A private field for locked door image file*/
    private ImageIcon myLockDoorIcon;

    /** A private field for game saved image file*/
    private ImageIcon mySaveGameIcon;

    /** A private field for resizing game saved image file*/
    private Image myResizeSaveImage;

    /** A private field for resized game saved image icon file*/
    private ImageIcon myResizeSaveIcon;

    /** A private field for resizing door image icon file*/
    private Image myResizedImage;

    /** A private field for resized door image icon file*/
    private ImageIcon myResizedIcon;
    /** A private field for resizing locked door image icon file*/
    private Image myResizedLock;
    /** A private field for resized locked door image icon file*/
    private ImageIcon myLockedDoor;

    /** A private field for resizing question mark image icon file*/
    private Image myResizedQuestion;

    /** A private field for resized question mark image icon file*/
    private ImageIcon myQuestionIcon;

    /** A private field for resizing game won image icon file*/
    private Image myResizedCelebration;

    /** A private field for resized game won image icon file*/
    private ImageIcon myCelebration;

    /** A private field for resizing exit door image icon file*/
    private Image myResizedExit;

    /** A private field for resized exit image icon file*/
    private ImageIcon myExitImg;

    /** A private field for the main game frame*/
    private JFrame myFrame;

    /** A private field for the games menu*/
    private JMenu myMenu;
    /** A private instance field for the arrow panel class*/
    private final ArrowsPanel myArrowsPanel;

    /** A private instance field for the question panel class*/
    private final QuestionPanel myQuestionPanel;

    /** A private instance field for the character panel class*/
    private static CharacterModel myCharacter;

    /** A private instance field for the maze panel class*/
    private static MazePanel myMazePanel;

    /** A private instance field for the door class*/
    private static Door myDoor;

    /** A private instance field for the room class*/
    private static Room[][] myRoom ;

    /** A private instance field for the current room panel class*/
    private static CurrentRoomPanel myCurrentRoomPanel;

    /** A private field for audio clip */
    private Clip myClip;

    /** A private field for the games menu*/
    private JMenu myMenu2;

    private ImageIcon myIcon;

    /** A private field for menu bar*/

    private static JMenuBar myBar;

    /** A private field for load menu item*/

    private static JMenuItem myLoad;

    /** A private field for about menu item*/
    private static JMenuItem myAbout;

    /** A private field for rules menu item*/
    private static JMenuItem myRules;

    /** A private field for save menu item*/
    private static JMenuItem mySave;

    /** A private field for exit menu item*/
    private static JMenuItem myExit;

    /* String that represents the serialized door file */
    private static String DOOR_FILE;

    /* String that represents the serialized room file */
    private static String ROOM_FILE;

    /* String that represents the serialized maze file */
    private static String MAZE_FILE;

    /* String that represents the serialized character file */
    private static String CHARACTER_FILE;

    /** A private instance field for the question factory class*/
    private static QuestionFactoryF myQuestionFactory;

    /**
     * TriviaController is constructor method that sets trivia panels instance,
     * creates text fields, and calls the layout for the frame.
     *
     */
    public TriviaController() {
        myFrame = new JFrame("Trivia Game");
        myQuestionFactory = new QuestionFactoryF();
        myArrowsPanel = new ArrowsPanel();
        myQuestionPanel = new QuestionPanel();
        myMazePanel = new MazePanel();
        myCharacter = new CharacterModel(0, 0);
        myDoor = new Door();
        myRoom = myMazePanel.getRoom();
        myCurrentRoomPanel = new CurrentRoomPanel();
        myCurrentRoomPanel.setMyTextField("You are currently in Room 0");
        assignQuestionsToDoors();
        myAnsweredQuestionIds = new ArrayList<>();
        createMenuBar();
        layoutComponents();
        createAndShowGUI();
        addCurrentArrowListeners();
        addMenuListeners();
    }
    /**
     * layoutComponents is a method that sets up the
     * formatting of the panel to be put on frame.
     *
     */
    private void layoutComponents() {
        IMAGE_FILE = "trivia/src/Images/mike2.png";
        DOOR_FILE = "door.ser";
        CHARACTER_FILE = "char.ser";
        MAZE_FILE = "maze.ser";
        ROOM_FILE = "room.ser";
        myImg = new ImageIcon("trivia/src/Images/doorPixel.png");
        myQuestionMark = new ImageIcon("trivia/src/Images/questionMark.png");
        myCelebrationIcon = new ImageIcon("trivia/src/Images/celebration.png");
        myExitIcon = new ImageIcon("trivia/src/Images/exit.png");
        myLockDoorIcon = new ImageIcon("trivia/src/Images/lockedDoor.png");
        mySaveGameIcon = new ImageIcon("trivia/src/Images/saveGame.png");
        myIcon = new ImageIcon("trivia/src/Images/doorPixel.png");
        myResizeSaveImage = mySaveGameIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        myResizeSaveIcon = new ImageIcon(myResizeSaveImage);
        myResizedImage = myImg.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        myResizedIcon = new ImageIcon(myResizedImage);
        myResizedLock = myLockDoorIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        myLockedDoor = new ImageIcon(myResizedLock);
        myResizedQuestion = myQuestionMark.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        myQuestionIcon = new ImageIcon(myResizedQuestion);
        myResizedCelebration = myCelebrationIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        myCelebration = new ImageIcon(myResizedCelebration);
        myResizedExit = myExitIcon.getImage().getScaledInstance(80, 80, Image.SCALE_SMOOTH);
        JPanel mazeAndArrowsPanel = new JPanel(new BorderLayout());
        mazeAndArrowsPanel.add(myMazePanel, BorderLayout.CENTER);
        mazeAndArrowsPanel.add(myArrowsPanel, BorderLayout.EAST);
        mazeAndArrowsPanel.add(myCurrentRoomPanel, BorderLayout.SOUTH);
        setLayout(new BorderLayout());
        add(mazeAndArrowsPanel, BorderLayout.NORTH);
        add(myQuestionPanel, BorderLayout.SOUTH);
        myCurrentRoomPanel.setPreferredSize(new Dimension(100, 80));
        myQuestionPanel.setPreferredSize(new Dimension(100, 205));
        myLoad.setEnabled(false);
        myExitImg= new ImageIcon(myResizedExit);

    }
    /**
     *  createAndShowGUI is a method that sets up the
     * formatting of the frame and sets it to be visible.
     *
     */
    public void createAndShowGUI() {
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setContentPane(this);
        myFrame.requestFocusInWindow();
        myFrame.setVisible(true);
        myFrame.setResizable(false);
        myFrame.setSize(900, 830);
        myFrame.setTitle("Trivia Game");
        myFrame.setIconImage(myIcon.getImage());
        SwingUtilities.invokeLater(() -> {
            myFrame.setVisible(true);
        });

    }

    /**
     * assignQuestionsToDoors is a method that connects questions
     * to the doors in each direction they exist in.
     */
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
    /**
     * createMenuBar is a method that sets up the
     * formatting of the menu bar for the frame.
     *
     */
    private void createMenuBar() {
        myBar = new JMenuBar();
        myMenu = new JMenu("File");
        myMenu2 = new JMenu("Help");
        mySave = new JMenuItem("Save Game");
        myLoad = new JMenuItem("Load Game");
        myExit = new JMenuItem("Exit");
        myAbout = new JMenuItem("About");
        myRules = new JMenuItem("Rules");
        myMenu.add(mySave);
        myMenu.add(myLoad);
        myMenu.add(myExit);
        myMenu2.add(myAbout);
        myMenu2.add(myRules);
        myBar.setPreferredSize(new Dimension(25, 30));
        myBar.add(myMenu);
        myBar.add(myMenu2);
        myFrame.setJMenuBar(myBar);

        myAbout.addActionListener(e -> JOptionPane.showMessageDialog(myFrame, "This is a Trivia Game \nJava Version: 21.0\nAuthor: Rohit Ark, Sado Iman\n ", "About", JOptionPane.ERROR_MESSAGE, myIcon));

        myRules.addActionListener(e -> JOptionPane.showMessageDialog(myFrame, "This a Trivia Game, the rules are as follows:\n 1. To win the game you must get to the end of the maze, marked by the exit door. \n 2. To get into the rooms you must correctly answer the trivia question at each door. " +
                        " \n 3. If you get the answer the door will be locked and must find another door to pass through. \n 4. If all the doors are locked, you lose. \n 5. There are hints in certain rooms that can help you answer the questions for those rooms.",
                "Rules", JOptionPane.ERROR_MESSAGE, myIcon));
    
        myLoad.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadObjects();
                mySave.setEnabled(true);
            }
        });
        mySave.addActionListener(e -> {
            JOptionPane.showMessageDialog(myFrame, "Game was successfully saved!", "Game Saved", JOptionPane.INFORMATION_MESSAGE, myResizeSaveIcon);
            saveObjects();
            myLoad.setEnabled(true);
        });
    }

    /*
     * updateCurrentRoomPanel is a method that displays onto the currentRoomPanel
     * the room the character is currently in.
     *
     */
    private void updateCurrentRoomPanel() {
        myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[myCharacter.getRow()][myCharacter.getCol()].getRoomName());
    }
    /**
     * enableAllArrows is a method that enables
     * all arrow buttons.
     */
    private void enableAllArrows() {
        enableUpArrow();
        enableDownArrow();
        enableLeftArrow();
        enableRightArrow();
    }
    /**
     * disableAllArrows is a method that disables
     * all arrow buttons.
     */
    private void disableAllArrows() {
        disableUpArrow();
        disableDownArrow();
        disableLeftArrow();
        disableRightArrow();
    }

    /** loadObjects is a method that loads the game
     * after players saves it.
     *
     */

    private static void loadObjects() {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(DOOR_FILE))) {
            myDoor = (Door) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        int lastRow = 0;
        int lastCol = 0;

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(CHARACTER_FILE))) {
            lastRow = ois.readInt();
            lastCol = ois.readInt();
        } catch (IOException e){
            e.printStackTrace();
        }
        myCharacter.setRow(lastRow);
        myCharacter.setCol(lastCol);

        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(ROOM_FILE))) {
            myRoom = (Room[][]) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage newImage = ImageIO.read(new File(IMAGE_FILE));
            if (newImage != null) {

                myMazePanel.setBufferedImage(newImage);
                myMazePanel.repaint();

            } else {
                System.out.println("Failed to load image from the saved file");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(MAZE_FILE))) {
            myMazePanel = (MazePanel) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }

        myMazePanel.setRoom(myRoom);
        myCharacter.setCurrentRoom(myRoom);
        myCurrentRoomPanel.setMyTextField("You are currently in " + myRoom[lastRow][lastCol].getRoomName());

    }
    /** saveObjects is a method that saves the game
     * after players touches save.
     *
     */
    private static void saveObjects() {

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(DOOR_FILE))) {
            oos.writeObject(myDoor);
        } catch (IOException e) {
            e.printStackTrace();
        }
        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(CHARACTER_FILE))) {
            oos.writeInt(myCharacter.getRow());
            oos.writeInt(myCharacter.getCol());
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(ROOM_FILE))) {
            oos.writeObject(myRoom);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(MAZE_FILE))) {

            oos.writeObject(myMazePanel);
        } catch (IOException e) {
            e.printStackTrace();
        }

        try {
            BufferedImage image = myMazePanel.getBufferedImage();
            if (image != null) {
                ImageIO.write(image, "png", new File(IMAGE_FILE));
            } else {
                System.out.println("No BufferedImage to save");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    /**
     * addCurrentArrowListeners is a method that adds all the listeners
     * from the arrow panels and creates action listeners for them
     * that controls logic for touching arrow keys and also runs the
     * main game logic.
     *
     */
    private void addCurrentArrowListeners() {
        myArrowsPanel.clearArrowPanelListeners();
        myArrowsPanel.addArrowListener(e -> {
            checkGameOver();
            String direction = getEnteredDirection(e.getSource());
            int questionId = myRoom[myCharacter.getRow()][myCharacter.getCol()].getQuestionForDoor(direction);

            if (isDoorLocked(direction) ){
                showLockedDoorMessage();
                return;
            }

            if (questionId != -1) {
                Question question = getQuestionById(questionId);
                myQuestionPanel.updateQuestion(question);

                if(isQuestionAnswered(questionId)) {
                    lockDoor(direction);
                    showQuestionAlreadyAnsweredMessage();
                    checkGameOver();
                }

                myQuestionPanel.addSubmitButtonListener(submitEvent -> {
                    String selectedAnswer = myQuestionPanel.getSelectedAnswer();

                    if (selectedAnswer != null && !selectedAnswer.isEmpty()) {

                        if (canPass(selectedAnswer, question) && !myRoom[myCharacter.getRow()][myCharacter.getCol()].getDoor(direction).isLocked() && !isQuestionAnswered(questionId)) {

                            setDoorOpenSound();
                            moveCharacter(direction);
                            myQuestionPanel.clearSubmitButtonListeners();
                            addAnsweredQuestionId(questionId);



                            checkGameOver();

                      }else {
                            lockDoor(direction);
                            incorrectAnswerPanel();
                            checkGameOver();

                        myQuestionPanel.clearSubmitButtonListeners();

                    }
                }
                     else {
                    }

                });
            } else {

            }
        });
    }

    /**
     * addAnsweredQuestionId is a method that adds the questions
     * that have been answered into the arraylist.
     *
     * @param theQuestionId represents the answered questions ID value.
     *
     */
    private void addAnsweredQuestionId(int theQuestionId) {
        myAnsweredQuestionIds.add(theQuestionId);
    }

    /**
     * isQuestionAnswered is a method that checks if the
     * question was already answered by checking if it is
     * in the arraylist.
     *
     * @param theQuestionId represents the answered questions ID value.
     *
     */
    private boolean isQuestionAnswered(int theQuestionId) {
        return myAnsweredQuestionIds.contains(theQuestionId);
    }


    /**
     * isDoorLocked is a method that checks if the
     * door in the given direction is locked.
     *
     * @param theDirection represents the direction player wants to move.
     *
     */
 private boolean isDoorLocked(String theDirection) {
     int row = myCharacter.getRow();
     int col = myCharacter.getCol();

     switch (theDirection) {
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

    /**
     * moveCharacter is a method that moves the player
     * in the given direction.
     *
     * @param theDirection represents the direction player wants to move.
     *
     */
    private void moveCharacter(String theDirection) {

        int row = myCharacter.getRow();
        int col = myCharacter.getCol();
        boolean canMove = false;

        switch (theDirection) {
            case "right":
                if (myRoom[row][col].getRightDoor() != null ) {

                  if(!myRoom[row][col].getRightDoor().isLocked())
                    myRoom[row][col].getRightDoor().setDoorStatus(Door.LOCKED);
                    myCharacter.moveRight();
                    myMazePanel.moveCharacter("right");
                    canMove = true;

                } else if(myRoom[row][col].getRightDoor().isLocked()){
                    showQuestionAlreadyAnsweredMessage();

                }
                break;
            case "left":

                if (myRoom[row][col].getLeftDoor() != null){
                  if(!myRoom[row][col].getLeftDoor().isLocked())
                    myRoom[row][col].getLeftDoor().setDoorStatus(Door.LOCKED);
                    myCharacter.moveLeft();
                    myMazePanel.moveCharacter("left");
                    canMove = true;

                } else {
                    showQuestionAlreadyAnsweredMessage();
                }
                break;
            case "down":

                if (myRoom[row][col].getDownDoor() != null)
                    if(!myRoom[row][col].getDownDoor().isLocked()) {
                    myRoom[row][col].getDownDoor().setDoorStatus(Door.LOCKED);
                    myCharacter.moveDown();
                    myMazePanel.moveCharacter("down");
                    canMove = true;

                } else {
                        showQuestionAlreadyAnsweredMessage();

                }
                break;
            case "up":

                if (myRoom[row][col].getUpDoor() != null)
                    if(!myRoom[row][col].getUpDoor().isLocked()) {
                    myRoom[row][col].getUpDoor().setDoorStatus(Door.LOCKED);
                    myCharacter.moveUp();
                    myMazePanel.moveCharacter("up");
                    canMove = true;

                } else {
                        showQuestionAlreadyAnsweredMessage();
                }
                break;
        }

        if (canMove) {
            updateCurrentRoomPanel();
            enableAllArrows();
            checkWon();
            showHint();
        }
    }

    /**
     * showQuestionAlreadyAnsweredMessage is a method that
     * displays a message for when player attempts to traverse
     * a room they already answered a question for.
     */
    private void showQuestionAlreadyAnsweredMessage() {
        JOptionPane.showMessageDialog(myFrame, "You have already answered the question for this door.", "Question Answered", JOptionPane.INFORMATION_MESSAGE, myLockedDoor);
    }

    /**
     * showLockedDoorMessage is a method that
     * displays a message for when player gets
     * the question incorrect.
     */
    private void showLockedDoorMessage() {
        JOptionPane.showMessageDialog(myFrame, "This door is locked.", "Locked Door", JOptionPane.INFORMATION_MESSAGE, myLockedDoor);
    }


    /**
     * checkGameOver is a method that checks if
     * all the doors around the player are locked,
     *  signifying the game is over.
     */
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
            JOptionPane.showMessageDialog(myFrame, "Game Over! You are stuck with no way out.", "Game Over", JOptionPane.INFORMATION_MESSAGE, myLockedDoor);
            disableAllArrows();
        }
    }

    private String getEnteredDirection(Object theArrowButton) {
         if (theArrowButton.equals(myArrowsPanel.getMyRightArrow())) {
            return "right";
        } else if (theArrowButton.equals(myArrowsPanel.getMyLeftArrow())) {
            return "left";
        } else if (theArrowButton.equals(myArrowsPanel.getMyDownArrow())) {
            return "down";
        } else if (theArrowButton.equals(myArrowsPanel.getMyUpArrow())) {
            return "up";
        } else {
            return "";
        }
    }

    /**
     * incorrectAnswerPanel is a method that
     * displays a message for when player gets the answer
     * wrong.
     */
    public void incorrectAnswerPanel(){
        setWrongAnswerSound();
        JOptionPane.showMessageDialog(this, "Incorrect Answer! This door is now locked.", "Door locked", JOptionPane.INFORMATION_MESSAGE, myResizedIcon);

    }
    /**
     * correctAnswerPanel is a method that
     * displays a message for when player gets the answer
     * right.
     */
    public void correctAnswerPanel(){
        JOptionPane.showMessageDialog(
                this, "Correct Answer! You may now proceed through this door.", "Door Unlocked",  JOptionPane.INFORMATION_MESSAGE, myResizedIcon);

    }


    /**
     * addMenuListeners is a method that adds all the listeners
     * for the menu panel which includes save and exit.
     */
    private void addMenuListeners() {

        mySave.addActionListener(e -> {
            if (e.getSource().equals(mySave)) {
                mySave.setEnabled(false);

            }

        });

        myExit.addActionListener(e -> {

            if (e.getSource().equals(myExit)) {
                int result = JOptionPane.showConfirmDialog(myFrame, "Are you sure you want to exit?", "Exit Confirmation", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE, myExitImg);
                if (result == JOptionPane.YES_OPTION) {
                    myFrame.dispose();

                }
            }
        });


    }

    /**
     * showHint is a method that checks if the room
     * contains a potion and then displays hints if
     * it contains a potion.
     */
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
    /**
     * roomFourPotion is a method that displays the
     * hints for room 4.
     */
    public void roomFourPotion(){
        JOptionPane.showMessageDialog(this, "\nLeft Door: The name of the tallest mountain in the world starts with letter \"E\"\nDown door: Insects have 6 legs, spiders have 8", "Room 4 Hints", JOptionPane.INFORMATION_MESSAGE, myQuestionIcon);
    }
    /**
     * roomElevenPotion is a method that displays the
     * hints for room 11.
     */
    public void roomElevenPotion(){
        JOptionPane.showMessageDialog(this, "\nLeft Door: The highest-rated movie on IMDb stars Morgan Freeman & Tim Robbins.\nRight Door: The upcoming olympics games is this summer (2024), then again in 2028.\nUp door: This metal's symbol comes from its Latin name, Aurum.\nDown door: The color of charcoal.", "Room 11 Hints", JOptionPane.INFORMATION_MESSAGE, myQuestionIcon);

    }
    /**
     * roomTwentyPotion is a method that displays the
     * hints for room 20.
     */
    public void roomTwentyPotion(){
        JOptionPane.showMessageDialog(this, "\nUp Door: The initials of the person who created Java is JG.\nRight Door: Rhymes with River.", "Room 20 Hints", JOptionPane.INFORMATION_MESSAGE, myQuestionIcon);
    }
    /**
     * roomEighteenPotion is a method that displays the
     * hints for room 18.
     */
    public void roomEighteenPotion(){
        JOptionPane.showMessageDialog(this, "\nLeft Door: This country starts with the letter \"I\".\nRight Door:This city is commonly nicknamed \"the city of love\"\nUp door: This team is from Cleveland.\nDown door: This author also wrote Romeo and Juliet.", "Room 18 Hints", JOptionPane.INFORMATION_MESSAGE, myQuestionIcon);

    }

    /**
     * lockDoor is a method that locks the door in
     * the given direction.
     *
     * @param theDirection represents the direction to lock the door in.
     */
    private void lockDoor(String theDirection) {
        Room currentRoom = myRoom[myCharacter.getRow()][myCharacter.getCol()];
        switch (theDirection) {
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


    /**
     * canPass is a method that checks if the answer the player
     * has selected matches the correct answer and displays
     * whether the answer was correct or not.
     *
     * @param theSelectedAnswer represents the answer player selected.
     * @param theQuestion represents the question for that door.
     */
    private boolean canPass(String theSelectedAnswer, Question theQuestion) {
        myQuestionPanel.updateQuestion(theQuestion);
        if (theQuestion.getAnswer().equalsIgnoreCase(theSelectedAnswer)) {
            correctAnswerPanel();
            return true;
        } else {
            setWrongAnswerSound();

            return false;
        }
    }

    /**
     * disableLeftArrow is a method that disables the
     * left arrow button.
     */
    public void disableLeftArrow(){
        myArrowsPanel.setEnabledLeft(false);
    }
    /**
     * disableRightArrow is a method that disables the
     * right arrow button.
     */
    public void disableRightArrow(){
        myArrowsPanel.setEnabledRight(false);
    }

    /**
     * disableUpArrow is a method that disables the
     * up arrow button.
     */
    public void disableUpArrow(){
        myArrowsPanel.setEnabledUp(false);
    }
    /**
     * disableDownArrow is a method that disables the
     * down arrow button.
     */
    public void disableDownArrow(){
        myArrowsPanel.setEnabledDown(false);
    }

    /**
     * enableUpArrow is a method that enables the
     * up arrow button if it is in bounds.
     */
    public void enableUpArrow(){
        if(  myCharacter.getRow() == 0){
            myArrowsPanel.setEnabledUp(false);
        }else {
            myArrowsPanel.setEnabledUp(true);

        }

    }
    /**
     * enableDownArrow is a method that enables the
     * down arrow button if it is in bounds.
     */
    public void enableDownArrow(){
        if( myCharacter.getRow() == 4){
            myArrowsPanel.setEnabledDown(false);
        }else {
            myArrowsPanel.setEnabledDown(true);
        }
    }
    /**
     * enableLeftArrow is a method that enables the
     * left arrow button if it is in bounds.
     */
    public void enableLeftArrow(){
        if( myCharacter.getCol() == 0){
            myArrowsPanel.setEnabledLeft(false);
        }else {
            myArrowsPanel.setEnabledLeft(true);

        }

    }

    /**
     * enableRightArrow is a method that enables the
     * right arrow button if it is in bounds.
     */
    public void enableRightArrow(){
        if( myCharacter.getCol() == 4){
            myArrowsPanel.setEnabledRight(false);
        }else {
            myArrowsPanel.setEnabledRight(true);

        }

    }

    /**
     * checkWon is a method that checks if the
     * player has reached the winning location.
     *
     */
    public void checkWon(){
        if(myCharacter.getRow() == 4 && myCharacter.getCol() == 4 ){
            JOptionPane.showMessageDialog(myFrame, "You won the game!!", "Game Won", JOptionPane.INFORMATION_MESSAGE, myCelebration);
            disableAllArrows();
            myQuestionPanel.getMySubmit().setEnabled(false);
            setWinSound();
        }
    }



    /**
     * setWrongAnswerSound is a method that sets the
     * sound when the player gets the question wrong.
     *
     */
    private void setWrongAnswerSound() {
        try {

            String fileName = "Sounds/wrongBuzzer.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            myClip = AudioSystem.getClip();

            myClip.open(inputStream);
            setVolume(70);
            myClip.setFramePosition(0);
            myClip.start();

        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * setVolume is a method that manually
     * adjusts the volume of the sounds in the game.
     *
     * @param theVolume represents the volume value
     */
    private void setVolume(int theVolume) {
        if ( myClip == null) return;
        FloatControl gainControl = null;
        if ( myClip.isControlSupported(FloatControl.Type.MASTER_GAIN)) {
            gainControl = (FloatControl)  myClip.getControl(FloatControl.Type.MASTER_GAIN);
        }
        if (gainControl == null &&  myClip.isControlSupported(FloatControl.Type.VOLUME)) {
            gainControl = (FloatControl)  myClip.getControl(FloatControl.Type.VOLUME);
        }
        if (gainControl == null) {
            System.err.println("Volume control is not supported.");
            return;
        }
        float min = gainControl.getMinimum();
        float max = gainControl.getMaximum();
        float value = min + (max - min) * (theVolume / 100.0f);
        gainControl.setValue(value);
    }
    /**
     * setWinSound is a method that sets the
     * sound when the player wins.
     *
     */
    private void setWinSound() {
        try {
            String fileName = "Sounds/gameWon.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            myClip = AudioSystem.getClip();
            myClip.open(inputStream);
            myClip.setFramePosition(0);
            myClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * setLoseSound is a method that sets the
     * sound when the player loses.
     *
     */
    private void setLoseSound() {
        try {
            String fileName = "Sounds/gameLost.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            myClip = AudioSystem.getClip();
            myClip.open(inputStream);
            myClip.setFramePosition(0);
            myClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }
    /**
     * setDoorOpenSound is a method that sets the
     * sound when the player opens a door.
     *
     */
    private void setDoorOpenSound() {
        try {
            String fileName = "Sounds/doorOpen.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            myClip = AudioSystem.getClip();
            myClip.open(inputStream);
            myClip.setFramePosition(0);
            myClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] theArgs){

            SwingUtilities.invokeLater(() -> {
                new TitleScreen();

            });

        }


}
