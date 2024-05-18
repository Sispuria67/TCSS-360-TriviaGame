package View;

import javax.swing.*;
import java.awt.*;
import Model.*;

public class MazePanel extends JPanel {
    private static final long serialVersionUID = 1L;
    CharacterModel myCharacter;
    static final int cols = 5;
    static final int rows = 5;
    static final int originX = 23;
    static final int originY = 37;
    static final int cellSide = 90;

    private static final int exitRow = 4;
    private static final int exitCol = 4;

    protected void paintComponent(Graphics g) {
        //the maze will be a 2d array where each index is either a wall, door, or walkable space
       // g.drawRect(originX, originX, rows, cols);


        super.paintComponent(g);
        for (int i = 0; i < rows + 1; i++) {
            g.drawLine(originX,originY + i* cellSide, originX+cols* cellSide, originY +i * cellSide);
        }
        for(int i = 0;i <cols +  1; i++){
            g.drawLine(originX + i*cellSide, originY, originX + i * cellSide, originY + rows * cellSide);
        }

        //doors
        g.setColor(Color.GREEN);
        //horizontal doors
        for (int i = 1; i < rows; i++) {
            g.fillRect(originX + cellSide / 4, originY + i * cellSide - cellSide / 4, cellSide / 2, cellSide / 3);
        }

// Vertical doors
        for (int i = 1; i < cols; i++) {
            g.fillRect(originX + i * cellSide - cellSide / 4, originY + cellSide / 4, cellSide / 3, cellSide / 2);
        }


        //character

        int characterX = originX + myCharacter.getCol() * cellSide;
        int characterY = originY + myCharacter.getRow() * cellSide;
        g.setColor(Color.BLUE);
        g.fillOval(characterX , characterY , cellSide -5 , cellSide- 5);

   //draws green square to signify exut
        /*
          int exitX = originX + exitCol * cellSide + cellSide / 4;
        int exitY = originY + exitRow * cellSide + cellSide / 4;
        g.setColor(Color.GREEN);
        g.fillRect(exitX, exitY, cellSide / 2, cellSide / 2);


         */

        //exit
  //it's position in relation to the maze/square
        int exitX = originX + exitCol * cellSide + cellSide / 4;
        int exitY = originY + exitRow * cellSide + cellSide / 2;
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 25);
        g.setFont(font);
        g.drawString("Exit", exitX, exitY);

    }



    public MazePanel() {
        myCharacter = new CharacterModel(0, 0);

        setPreferredSize(new Dimension(cols * cellSide + originX, rows * cellSide + originY));

        layoutComponents();


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



    private void layoutComponents() {
     //   JPanel myTopCenterPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // this.setLayout(new BorderLayout());
       // myTopCenterPanel.setBorder(BorderFactory.createTitledBorder("Map"));
        //this.add(myTopCenterPanel, BorderLayout.CENTER);

    }




}
