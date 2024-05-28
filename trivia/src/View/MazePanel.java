package View;

import javax.swing.*;
import java.awt.*;
import Model.*;
import javax.imageio.ImageIO;

import java.io.File;
import java.io.IOException;

public class MazePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    CharacterModel myCharacter;
    private Image doorImage;
    static final int cols = 5;
    static final int rows = 5;
    //starting x position
    static final int originX = 23;

    //starting y position
    static final int originY = 37;
    //length of each grid square side
    static final int cellSide = 90;

    //exit square
    private static final int exitRow = 4;
    private static final int exitCol = 4;

    private Room[][] room;
private int myNewCount;

    public MazePanel() {
        //mazeGrid = new int[rows][cols];
        room = new Room[rows][cols];
        myCharacter = new CharacterModel(0, 0);
        initializeDoors();
        myCharacter.setCurrentRoom(room);
        myNewCount = 0;

        //loadDoorImage();

        setPreferredSize(new Dimension(cols * cellSide + originX, rows * cellSide + originY));

        layoutComponents();


    }

    public Room[][] getRoom(){
        return room;
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

    private void initializeDoors() {
        room = new Room[rows][cols];

        for(int i =0; i <rows; i++){
            for(int j =0; j <cols; j++){
                room[i][j] = new Room();
                myNewCount++;
               // myCharacter.setRoomCounter(myNewCount);

               // System.out.println("room length: " + room.length); // = 5
                //System.out.println("room[0][0]: " + room[0][0]);
                //System.out.println("room[0][1]: " + room[0][1]);
            }
        }
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


    protected void paintComponent(Graphics g) {
        //the maze will be a 2d array where each index is either a wall, door, or walkable space
        //should we add some free spaces on maze where player doesnt need to answer question to pass
       // g.drawRect(originX, originX, rows, cols);


        super.paintComponent(g);
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(originX,originY + i * cellSide, originX+cols* cellSide, originY +i * cellSide);
        }
        for(int i = 0;i <cols +  1; i++){
            g.drawLine(originX + i*cellSide, originY, originX + i * cellSide, originY + rows * cellSide);

        }


        //doors
        g.setColor(Color.orange);
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
        g.drawImage(doorImage, doorX, doorY, cellSide, cellSide, this);

        //character
        int characterX = originX + myCharacter.getCol() * cellSide;
        int characterY = originY + myCharacter.getRow() * cellSide;
        g.setColor(Color.BLUE);
        g.fillOval(characterX +20 , characterY +20 , cellSide -40 , cellSide- 40);

   //draws green square to signify exut
        /*
          int exitX = originX + exitCol * cellSide + cellSide / 4;
        int exitY = originY + exitRow * cellSide + cellSide / 4;
        g.setColor(Color.GREEN);
        g.fillRect(exitX, exitY, cellSide / 2, cellSide / 2);



         */

        //exit
  //it's position in relation to the maze/square
        int exitX = originX + exitCol * cellSide + cellSide / 3;
        int exitY = (originY + exitRow * cellSide + cellSide /2) + 5;
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 17);
        g.setFont(font);
        g.drawString("Exit", exitX, exitY);

    }


    public void moveCharacter(String direction) {
   switch(direction){
           case "up":
                myCharacter.moveUp();
               myCharacter.setCurrentRoom(room);
                break;
            case "down":
                myCharacter.moveDown();
              myCharacter.setCurrentRoom(room);
                break;
         case"left":
              myCharacter.moveLeft();
             myCharacter.setCurrentRoom(room);
                break;
            case "right":
                myCharacter.moveRight();
                myCharacter.setCurrentRoom(room);
                break;
     }
       //myCharacter.setCurrentRoom(room[myCharacter.getRow()][myCharacter.getCol()]);


   //System.out.println("CurrentRoomLocation:" + room[myCharacter.getRow()][myCharacter.getCol()]);
        System.out.println("row:" + myCharacter.getRow() + "col:" + myCharacter.getCol());
        System.out.println("Right door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getRightDoor());
        System.out.println("Left door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getLeftDoor());
        System.out.println("Up door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getUpDoor());
        System.out.println("Down door?: " + room[myCharacter.getRow()][myCharacter.getCol()].getDownDoor());
       // System.out.println("Current room: " + myCharacter.getCurrentRoom().toString());
        System.out.println("Current room: " + myCharacter.toString());

        repaint();
    }

    private void loadDoorImage() {
        try {
            doorImage = ImageIO.read(new File("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/door.png")); // Load the door image file
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
     //   JPanel myTopCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // this.setLayout(new BorderLayout());
       // myTopCenterPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        //this.add(myTopCenterPanel, BorderLayout.CENTER);

    }




}
