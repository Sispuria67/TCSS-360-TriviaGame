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
    //starting x posotion
    static final int originX = 23;

    //starting y position
    static final int originY = 37;
    //length of each grid square side
    static final int cellSide = 90;

    //exit square
    private static final int exitRow = 4;
    private static final int exitCol = 4;

    private int[][] mazeGrid;
    public MazePanel() {
        mazeGrid = new int[rows][cols];
        myCharacter = new CharacterModel(0, 0);
        //loadDoorImage();

        setPreferredSize(new Dimension(cols * cellSide + originX, rows * cellSide + originY));

        layoutComponents();


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
                break;
            case "down":
                myCharacter.moveDown();
                break;
         case"left":
              myCharacter.moveLeft();
                break;
            case "right":
                myCharacter.moveRight();
                break;
     }
        repaint();
    }

    private void loadDoorImage() {
        try {
            doorImage = ImageIO.read(new File("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/door.png")); // Load the door image file
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void layoutComponents() {
     //   JPanel myTopCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // this.setLayout(new BorderLayout());
       // myTopCenterPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        //this.add(myTopCenterPanel, BorderLayout.CENTER);

    }




}
