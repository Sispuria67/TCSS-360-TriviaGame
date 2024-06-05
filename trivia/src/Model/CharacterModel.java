package Model;


import java.io.Serializable;

public class CharacterModel implements Serializable {

    private static final long serialVersionUID = 134L;
    private int row;
   private  int col;

    private Room [][] currentRoom;

    //private int myRoomCounter;

   //make the maze a 2D array?[row][col]


    public CharacterModel(int initialRow, int initialCol) {
        this.row = initialRow;
        this.col = initialCol;
        this.currentRoom = new Room[row][col];
     //   myRoomCounter = 0;
        //currentRoom = null;
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

/*
    public int getRoomCounter() {
        return myRoomCounter;
    }

    public void setRoomCounter(int theRoomCounter) {
         myRoomCounter = theRoomCounter;
    }


 */
    //should this be in controller
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
      //  System.out.println(currentRoom[getRow()][getCol()]);
        return currentRoom[getRow()][getCol()];
    }

    public void setCurrentRoom(Room [][] room) {
      //  if (room != null && row >= 0 && row < room.length && col >= 0 && col < room[0].length) {
            currentRoom = room;
     //   } else {
           // System.out.println("Invalid room values");
        //}

    }

    public String toString() {
        return "room number is " + currentRoom[getRow()][getCol()].getRoomName().toString();
    }

}

