package View;

import javax.swing.*;
import java.awt.*;
import Model.*;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

public class MazePanel extends JPanel implements Serializable {
    private static final long serialVersionUID = 389L;
    CharacterModel myCharacter;
    private Image doorImage;

    private Image characterImage;
    private Image potionImage;

    static final int cols = 5;
    static final int rows = 5;
    //starting x position
    static final int originX = 23;

    //starting y position
    static final int originY = 37;
    //length of each grid square side
    static final int cellSide = 90;

    private int myNewCount;

    //exit square
    private static final int exitRow = 4;
    private static final int exitCol = 4;

    private Room[][] myRoom;
    private final JLabel gameIconLabel;

    private final TriviaModel myModel;


    public MazePanel(TriviaModel theModel) {
        myModel = theModel;
        Icon  gameIcon = new ImageIcon("trivia/src/Images/triviaGame.png");

        gameIconLabel = new JLabel(gameIcon);
        //mazeGrid = new int[rows][cols];
        myRoom = new Room[rows][cols];
        myCharacter = new CharacterModel(0, 0);
        initializeDoors();
        myNewCount = 0;
        myCharacter.setCurrentRoom(myRoom);


        loadDoorImage();
        loadPotionImage();

        loadImages();

        setPreferredSize(new Dimension(cols * cellSide + originX, rows * cellSide + originY));

        layoutComponents();


    }
    public Room[][] getRoom(){
        return myRoom;
    }


/*
    private void initializeDoors() {
        doors = new Door[rows][cols];

        for(int i =0; i <rows; i++){
            for(int j =0; j <cols; j++){
                doors[i][j] = new Door();
            }
        }
       // doors[0][0].unlock();
        doors[0][0].setDoorStatus(Door.LOCKED);
        doors[0][1].setDoorStatus(Door.LOCKED);
        doors[0][2].setDoorStatus(Door.LOCKED);
        doors[0][3].setDoorStatus(Door.LOCKED);
        doors[0][4].setDoorStatus(Door.LOCKED);
        doors[1][0].setDoorStatus(Door.LOCKED);


    }

 */
/*
    private void initializeDoors() {
        room = new Room[rows][cols];

        for(int i =0; i <rows; i++){
            for(int j =0; j <cols; j++){
                room[i][j] = new Room(myModel);
                myNewCount++;
               // myCharacter.setRoomCounter(myNewCount);

               // System.out.println("room length: " + room.length); // = 5
                //System.out.println("room[0][0]: " + room[0][0]);
                //System.out.println("room[0][1]: " + room[0][1]);
            }
        }

        room[3][3].setHasPotion(true);
        room[2][1].setHasPotion(true);
        room[0][4].setHasPotion(true);

        room[4][0].setHasPotion(true);
         //room 0
        room[0][0].setUpDoor(false);
        room[0][0].setDownDoor(true);
        room[0][0].setLeftDoor(false);
        room[0][0].setRightDoor(true);
        room[0][0].setRoomName("Room 0");
        room[0][0].setRoomNumber(0);



        //room 1
        room[0][1].setUpDoor(false);
        room[0][1].setDownDoor(true);
        room[0][1].setLeftDoor(true);
        room[0][1].setRightDoor(true);
        room[0][1].setRoomName("Room 1");
        room[0][1].setRoomNumber(1);


          //room 2
        room[0][2].setUpDoor(false);
        room[0][2].setDownDoor(true);
        room[0][2].setLeftDoor(true);
        room[0][2].setRightDoor(true);
        room[0][2].setRoomName("Room 2");
        room[0][2].setRoomNumber(2);

        //room 3
        room[0][3].setUpDoor(false);
        room[0][3].setDownDoor(true);
        room[0][3].setLeftDoor(true);
        room[0][3].setRightDoor(true);
        room[0][3].setRoomName("Room 3");
        room[0][3].setRoomNumber(3);

        //room 4
        room[0][4].setUpDoor(false);
        room[0][4].setDownDoor(true);
        room[0][4].setLeftDoor(true);
        room[0][4].setRightDoor(false);
        room[0][4].setRoomName("Room 4");
        room[0][4].setRoomNumber(4);


        //room 5
        room[1][0].setUpDoor(true);
        room[1][0].setDownDoor(true);
        room[1][0].setLeftDoor(false);
        room[1][0].setRightDoor(true);
        room[1][0].setRoomName("Room 5");
        room[1][0].setRoomNumber(5);

        //room 6
        room[1][1].setUpDoor(true);
        room[1][1].setDownDoor(true);
        room[1][1].setLeftDoor(true);
        room[1][1].setRightDoor(true);
        room[1][1].setRoomName("Room 6");
        room[1][1].setRoomNumber(6);

        //room 7
        room[1][2].setUpDoor(true);
        room[1][2].setDownDoor(true);
        room[1][2].setLeftDoor(true);
        room[1][2].setRightDoor(true);
        room[1][2].setRoomName("Room 7");
        room[1][2].setRoomNumber(7);

        //room 8
        room[1][3].setUpDoor(true);
        room[1][3].setDownDoor(true);
        room[1][3].setLeftDoor(true);
        room[1][3].setRightDoor(true);
        room[1][3].setRoomName("Room 8");
        room[1][3].setRoomNumber(8);

        //room 9
        room[1][4].setUpDoor(true);
        room[1][4].setDownDoor(true);
        room[1][4].setLeftDoor(true);
        room[1][4].setRightDoor(false);
        room[1][4].setRoomName("Room 9");
        room[1][4].setRoomNumber(9);


        //room 10
        room[2][0].setUpDoor(true);
        room[2][0].setDownDoor(true);
        room[2][0].setLeftDoor(false);
        room[2][0].setRightDoor(true);
        room[2][0].setRoomName("Room 10");
        room[2][0].setRoomNumber(10);

        //room 11
        room[2][1].setUpDoor(true);
        room[2][1].setDownDoor(true);
        room[2][1].setLeftDoor(true);
        room[2][1].setRightDoor(true);
        room[2][1].setRoomName("Room 11");
        room[2][1].setRoomNumber(11);

        //room 12
        room[2][2].setUpDoor(true);
        room[2][2].setDownDoor(true);
        room[2][2].setLeftDoor(true);
        room[2][2].setRightDoor(true);
        room[2][2].setRoomName("Room 12");
        room[2][2].setRoomNumber(12);

        //room 13
        room[2][3].setUpDoor(true);
        room[2][3].setDownDoor(true);
        room[2][3].setLeftDoor(true);
        room[2][3].setRightDoor(true);
        room[2][3].setRoomName("Room 13");
        room[2][3].setRoomNumber(13);

        //room 14
        room[2][4].setUpDoor(true);
        room[2][4].setDownDoor(true);
        room[2][4].setLeftDoor(true);
        room[2][4].setRightDoor(false);
        room[2][4].setRoomName("Room 14");
        room[2][4].setRoomNumber(14);

        //room 15
        room[3][0].setUpDoor(true);
        room[3][0].setDownDoor(true);
        room[3][0].setLeftDoor(false);
        room[3][0].setRightDoor(true);
        room[3][0].setRoomName("Room 15");
        room[3][0].setRoomNumber(15);

        //room 16
        room[3][1].setUpDoor(true);
        room[3][1].setDownDoor(true);
        room[3][1].setLeftDoor(true);
        room[3][1].setRightDoor(true);
        room[3][1].setRoomName("Room 16");
        room[3][1].setRoomNumber(16);

        //room 17
        room[3][2].setUpDoor(true);
        room[3][2].setDownDoor(true);
        room[3][2].setLeftDoor(true);
        room[3][2].setRightDoor(true);
        room[3][2].setRoomName("Room 17");
        room[3][2].setRoomNumber(17);

        //room 18
        room[3][3].setUpDoor(true);
        room[3][3].setDownDoor(true);
        room[3][3].setLeftDoor(true);
        room[3][3].setRightDoor(true);
        room[3][3].setRoomName("Room 18");
        room[3][3].setRoomNumber(18);

        //room 19
        room[3][4].setUpDoor(true);
        room[3][4].setDownDoor(true);
        room[3][4].setLeftDoor(true);
        room[3][4].setRightDoor(false);
        room[3][4].setRoomName("Room 19");
        room[3][4].setRoomNumber(19);

         //room 20
        room[4][0].setUpDoor(true);
        room[4][0].setDownDoor(false);
        room[4][0].setLeftDoor(false);
        room[4][0].setRightDoor(true);
        room[4][0].setRoomName("Room 20");
        room[4][0].setRoomNumber(20);

        //room 21
        room[4][1].setUpDoor(true);
        room[4][1].setDownDoor(false);
        room[4][1].setLeftDoor(true);
        room[4][1].setRightDoor(true);
        room[4][1].setRoomName("Room 21");
        room[4][1].setRoomNumber(21);

        //room 22
        room[4][2].setUpDoor(true);
        room[4][2].setDownDoor(false);
        room[4][2].setLeftDoor(true);
        room[4][2].setRightDoor(true);
        room[4][2].setRoomName("Room 22");
        room[4][2].setRoomNumber(22);

        //room 23
        room[4][3].setUpDoor(true);
        room[4][3].setDownDoor(false);
        room[4][3].setLeftDoor(true);
        room[4][3].setRightDoor(true);
        room[4][3].setRoomName("Room 23");
        room[4][3].setRoomNumber(23);

        //room 24
        room[4][4].setUpDoor(true);
        room[4][4].setDownDoor(false);
        room[4][4].setLeftDoor(true);
        room[4][4].setRightDoor(false);
        room[4][4].setRoomName("Room 24");
        room[4][4].setRoomNumber(24);

    }


 */

    protected void paintComponent(Graphics g) {
        //the maze will be a 2d array where each index is either a wall, door, or walkable space
        //should we add some free spaces on maze where player doesnt need to answer question to pass
       // g.drawRect(originX, originX, rows, cols);


        super.paintComponent(g);

        //draw potions
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (myRoom[i][j].hasPotion()) {
                    int potionX = originX + j * cellSide;
                    int potionY = originY + i * cellSide;
                    g.drawImage(potionImage, potionX + 10, potionY + 10, cellSide - 10, cellSide - 10, this);
                }
            }
        }
//for maze
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(139, 69, 19));
        for (int i = 0; i < rows + 1; i++) {
            g2d.drawLine(originX,originY + i * cellSide, originX+cols* cellSide, originY +i * cellSide);
        }
        for(int i = 0;i <cols +  1; i++){
            g2d.drawLine(originX + i*cellSide, originY, originX + i * cellSide, originY + rows * cellSide);

        }


        //doors
        g.setColor(new Color(139, 69, 19));
        //horizontal doors
        //i =1 means skip first row, start at second row
        for (int i = 1; i < rows ; i++) {
           // g.drawLine(originX,originY + i* cellSide, originX+cols* cellSide, originY +i * cellSide);
            //draws one rectangle

            //the only thing changing between each row is where the x value is being drawn, + moves it left, - moves it right
            //first row
          g.fillRect((originX + cellSide /4), (originY + i * cellSide - cellSide / 4) + 10 , cellSide / 2, cellSide / 4);
          //second row
          g.fillRect((originX + cellSide) + 20, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);
           //third row
            g.fillRect((originX + cellSide) + 110, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

            //fourth row
            g.fillRect((originX + cellSide) + 200, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

            //fifth row
            g.fillRect((originX + cellSide) + 290, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

        }

// Vertical doors
        for (int i = 1; i < cols ; i++) {
            //the only thign chaging each time is the y value, + moves it down, - moves it up
         //first col
           g.fillRect(originX + i * cellSide - cellSide /8, originY + cellSide / 4, cellSide / 4, cellSide / 2);
            //second col
            g.fillRect(originX + i * cellSide - cellSide /8, (originY + cellSide) + 17 , cellSide / 4, cellSide / 2);
            //third col
            g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 115, cellSide / 4, cellSide / 2);
            //fourth col
            g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 200, cellSide / 4, cellSide / 2);
            //fifth col
           g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 290, cellSide / 4, cellSide / 2);

        }



        //exit door image
        int doorX = originX + exitCol * cellSide;
        int doorY = originY + exitRow * cellSide;
        g.drawImage(doorImage, doorX + 10, doorY, cellSide, cellSide, this);

        /*
        //character( blue circle)
        int characterX = originX + myCharacter.getCol() * cellSide;
        int characterY = originY + myCharacter.getRow() * cellSide;
        g.setColor(Color.BLUE);
        g.fillOval(characterX +20 , characterY +20 , cellSide -40 , cellSide- 40);

         */

        // Draw character image
        int characterX = originX + myCharacter.getCol() * cellSide;
        int characterY = originY + myCharacter.getRow() * cellSide;
        g.drawImage(characterImage, characterX, characterY, cellSide, cellSide, this);

   //draws green square to signify exut
        /*
          int exitX = originX + exitCol * cellSide + cellSide / 4;
        int exitY = originY + exitRow * cellSide + cellSide / 4;
        g.setColor(Color.GREEN);
        g.fillRect(exitX, exitY, cellSide / 2, cellSide / 2);



         */

        //exit
  //it's position in relation to the maze/square
        int exitX = (originX + exitCol * cellSide + cellSide / 3) + 5  ;
        int exitY = (originY + exitRow * cellSide + cellSide /2) + 1;
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 11);
        g.setFont(font);
        g.drawString("EXIT", exitX, exitY);

    }


    public void moveCharacter(String direction) {
   switch(direction){
           case "up":
               myCharacter.moveUp();
               myCharacter.setCurrentRoom(myRoom);
                break;
            case "down":
                myCharacter.moveDown();
              myCharacter.setCurrentRoom(myRoom);
                break;
         case"left":
              myCharacter.moveLeft();
             myCharacter.setCurrentRoom(myRoom);
                break;
            case "right":
                myCharacter.moveRight();
                myCharacter.setCurrentRoom(myRoom);
                break;
     }
     repaint();
    }




        //myCharacter.setCurrentRoom(room[myCharacter.getRow()][myCharacter.getCol()]);


   //System.out.println("CurrentRoomLocation:" + room[myCharacter.getRow()][myCharacter.getCol()]);
        /*
        System.out.println("row:" + myCharacter.getRow() + "col:" + myCharacter.getCol());
        System.out.println("Right door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getRightDoor());
        System.out.println("Left door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getLeftDoor());
        System.out.println("Up door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getUpDoor());
        System.out.println("Down door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getDownDoor());
       // System.out.println("Current room: " + myCharacter.getCurrentRoom().toString());
        System.out.println("Current room: " + myCharacter.toString());


         */




    private void initializeDoors() {
        myRoom = new Room[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                myRoom[i][j] = new Room(TriviaModel.getMyTriviaInstance());
                myNewCount++;
            }
        }

        myRoom[3][3].setHasPotion(true);
        myRoom[2][1].setHasPotion(true);
        myRoom[0][4].setHasPotion(true);
        myRoom[4][0].setHasPotion(true);

        // Set up room 0
        setRoomAndDoors(myRoom[0][0], "Room 0", 0, false, true, false, true);

        // Set up room 1
        setRoomAndDoors(myRoom[0][1], "Room 1", 1, false, true, true, true);

        // Set up room 2
        setRoomAndDoors(myRoom[0][2], "Room 2", 2, false, true, true, true);

        // Set up room 3
        setRoomAndDoors(myRoom[0][3], "Room 3", 3, false, true, true, true);

        // Set up room 4
        setRoomAndDoors(myRoom[0][4], "Room 4", 4, false, true, true, false);

        // Set up room 5
        setRoomAndDoors(myRoom[1][0], "Room 5", 5, true, true, false, true);

        // Set up room 6
        setRoomAndDoors(myRoom[1][1], "Room 6", 6, true, true, true, true);

        // Set up room 7
        setRoomAndDoors(myRoom[1][2], "Room 7", 7, true, true, true, true);

        // Set up room 8
        setRoomAndDoors(myRoom[1][3], "Room 8", 8, true, true, true, true);

        // Set up room 9
        setRoomAndDoors(myRoom[1][4], "Room 9", 9, true, true, true, false);

        // Set up room 10
        setRoomAndDoors(myRoom[2][0], "Room 10", 10, true, true, false, true);

        // Set up room 11
        setRoomAndDoors(myRoom[2][1], "Room 11", 11, true, true, true, true);

        // Set up room 12
        setRoomAndDoors(myRoom[2][2], "Room 12", 12, true, true, true, true);

        // Set up room 13
        setRoomAndDoors(myRoom[2][3], "Room 13", 13, true, true, true, true);

        // Set up room 14
        setRoomAndDoors(myRoom[2][4], "Room 14", 14, true, true, true, false);

        // Set up room 15
        setRoomAndDoors(myRoom[3][0], "Room 15", 15, true, true, false, true);

        // Set up room 16
        setRoomAndDoors(myRoom[3][1], "Room 16", 16, true, true, true, true);

        // Set up room 17
        setRoomAndDoors(myRoom[3][2], "Room 17", 17, true, true, true, true);

        // Set up room 18
        setRoomAndDoors(myRoom[3][3], "Room 18", 18, true, true, true, true);

        // Set up room 19
        setRoomAndDoors(myRoom[3][4], "Room 19", 19, true, true, true, false);

        // Set up room 20
        setRoomAndDoors(myRoom[4][0], "Room 20", 20, true, false, false, true);

        // Set up room 21
        setRoomAndDoors(myRoom[4][1], "Room 21", 21, true, false, true, true);

        // Set up room 22
        setRoomAndDoors(myRoom[4][2], "Room 22", 22, true, false, true, true);

        // Set up room 23
        setRoomAndDoors(myRoom[4][3], "Room 23", 23, true, false, true, true);

        // Set up room 24
        setRoomAndDoors(myRoom[4][4], "Room 24", 24, true, false, true, false);
    }

    private void setRoomAndDoors(Room room, String roomName, int roomNumber, boolean up, boolean down, boolean left, boolean right) {
        room.setRoomName(roomName);
        room.setRoomNumber(roomNumber);

        if (up) room.getUpDoor().unlock(); else room.getUpDoor().lock();
        if (down) room.getDownDoor().unlock(); else room.getDownDoor().lock();
        if (left) room.getLeftDoor().unlock(); else room.getLeftDoor().lock();
        if (right) room.getRightDoor().unlock(); else room.getRightDoor().lock();
    }
    private void loadImages() {
        try {
            characterImage = ImageIO.read(new File("trivia/src/Images/mike2.png")); // load the character image file

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public void repaints(){
        repaint();
    }

    private void loadDoorImage() {
        try {
            doorImage = ImageIO.read(new File("trivia/src/Images/doorPixel.png")); // load the door image file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    private void loadPotionImage() {
        try {

            potionImage = ImageIO.read(new File("trivia/src/Images/potionNew.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public int getCols(){
        return cols;
    }

    public int getRows(){
        return rows;
    }

    private void layoutComponents() {

        this.setBackground(new Color(0, 137, 165));
        this.add(gameIconLabel, BorderLayout.NORTH);
     //   JPanel myTopCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // this.setLayout(new BorderLayout());
       // myTopCenterPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        //this.add(myTopCenterPanel, BorderLayout.CENTER);

    }


    public void setRoom(Room[][] myRoom2) {
        myRoom = myRoom2;
    }
}
