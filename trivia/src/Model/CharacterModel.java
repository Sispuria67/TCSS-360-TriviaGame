package Model;


import java.io.Serializable;

public class CharacterModel implements Serializable {

    private static final long serialVersionUID = 1387874L;
    private int row;
   private  int col;

    private Room [][] currentRoom;

    //private int myRoomCounter;

   //make the maze a 2D array?[row][col]


    public CharacterModel(int initialRow, int initialCol) {
        this.row = initialRow;
        this.col = initialCol;
        this.currentRoom = new Room[row][col];

    }

    public int getRow() {
        return row;
    }

    public int getCol() {
        return col;
    }

    public void setRow(int theRow) {
       row = theRow;
    }

    public void setCol(int theCol) {
        col = theCol;
    }

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

    public Room getCurrentRoom() {
        return currentRoom[getRow()][getCol()];
    }

    public void setCurrentRoom(Room [][] room) {
            currentRoom = room;
    }

    public String toString() {
        return "room number is " + currentRoom[getRow()][getCol()].getRoomName().toString();
    }

}

