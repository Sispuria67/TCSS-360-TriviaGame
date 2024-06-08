/**
 * A package for model.
 */
package Model;


import java.io.Serializable;

/**
 * CharacterModel is a class that represents the player.
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class CharacterModel implements Serializable {
    /** Unique class identifier used for serialization */
    private static final long serialVersionUID = 1387874L;

    /** A private field for row value */
    private int myRow;

    /** A private field for column value */
    private  int myCol;

    /** A private field for the current room */
    private Room [][] myCurrentRoom;

    /**
     * CharacterModel is constructor method that initializes
     * the rooms row and column.
     *
     */
    public CharacterModel(int theInitialRow, int theInitialCol) {
        myRow = theInitialRow;
        myCol = theInitialCol;
        myCurrentRoom = new Room[myRow][myCol];

    }

    /**
     * getRow is a getter method for the row value.
     *
     * @return the row value
     */

    public int getRow() {
        return myRow;
    }
    /**
     * getCol is a getter method for the column value.
     *
     * @return the column value
     */
    public int getCol() {
        return myCol;
    }

    /**
     * setRow is a setter method for the row value.
     *
     * @param theRow represents the row value.
     */
    public void setRow(int theRow) {
       myRow = theRow;
    }

    /**
     * setCol is a setter method for the column value.
     *
     * @param theCol represents the column value.
     */
    public void setCol(int theCol) {
        myCol = theCol;
    }

    /**
     * moveUp is a method that moves the row up
     * by one.
     */
    public void moveUp() {
        if (myRow > 0) {
            myRow--;
        }
    }
    /**
     * moveDown is a method that moves the row down
     * by one.
     */
    public void moveDown() {
        if (myRow < 4) {
            myRow++;
        }
    }

    /**
     * moveLeft is a method that moves the row left
     * by one.
     */
    public void moveLeft() {
        if (myCol > 0) {
            myCol--;
        }
    }

    /**
     * moveRight is a method that moves the row right
     * by one.
     */
    public void moveRight() {
        if (myCol < 4) {
            myCol++;
        }
    }

    /**
     * getCurrentRoom is a getter method for the current room.
     *
     * @return the current room.
     */
    public Room getCurrentRoom() {
        return myCurrentRoom[getRow()][getCol()];
    }

    /**
     * setCurrentRoom is a setter method for the current room.
     *
     * @param theRoom represents the current room.
     */
    public void setCurrentRoom(Room [][] theRoom) {
            myCurrentRoom = theRoom;
    }

    /**
     * toString method for the entire class.
     *
     * @return the string value of the class
     */
    @Override
    public String toString() {
        return "CharacterModel{" +
                "row=" + myRow +
                ", col=" + myCol +
                ", currentRoom=" + (myCurrentRoom[myRow][myCol] != null ? myCurrentRoom[myRow][myCol].getRoomName() : "null") +
                '}';
    }

}

