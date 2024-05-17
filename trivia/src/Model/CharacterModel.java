package Model;

import View.ArrowsPanel;

import javax.swing.*;

public class CharacterModel {

    private int row;
   private  int col;

   //make the maze a 2D array?[row][col]


    public CharacterModel(int initialRow, int initialCol) {
        this.row = initialRow;
        this.col = initialCol;

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    //shoudl this be in controller
    public void moveUp() {
        if (row > 0) {
            row--;

        }

    }

    public void moveDown() {
        if (row < 4) {
            row++;

        }
    }

    public void moveLeft() {
        if (col > 0) {
            col--;


        }
    }

    public void moveRight() {
        if (col < 4) {
            col++;
        }
    }

}

