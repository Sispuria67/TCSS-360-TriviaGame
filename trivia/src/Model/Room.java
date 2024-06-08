/**
 * A package for model.
 */
package Model;

import java.io.*;
import java.util.HashMap;
import java.util.Map;

/**
 * Room is a class that represents the rooms in
 * the game
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class Room implements Serializable{
    /** Unique class identifier used for serialization */
    private static final long serialVersionUID = 6399201422927996955L;

    /** A private field for the up door */
    private Door myUpDoor;

    /** A private field for the down door */
    private Door myDownDoor;

    /** A private field for the left door */
    private Door myLeftDoor;

    /** A private field for the right door */
    private Door myRightDoor;

    /** A private field for the rooms name */

    private String myRoomName;

    /** A private field for the rooms number */

    private int myRoomNumber;

    /** A private field for the questions at the doors */
    private Map<String, Integer> doorQuestions;

    /** A private field for whether a room has a potion or not */
    private boolean hasPotion;


    /**
     * Room is constructor method that initializes
     * the doors and the hash map for door questions.
     *
     */
    public Room(){
        doorQuestions = new HashMap<>();
        myUpDoor = new Door();
        myDownDoor = new Door();
        myLeftDoor = new Door();
        myRightDoor = new Door();

    }

    /**
     * getDoor is a method that retrieves the door in the specified direction.
     *
     * @param theDirection represents the direction of the door to retrieve.
     * @return The door in the specified direction, or null if no door exists in that direction.
     */
    public Door getDoor(String theDirection) {
        switch (theDirection) {
            case "up":
                return myUpDoor;
            case "down":
                return myDownDoor;
            case "left":
                return myLeftDoor;
            case "right":
                return myRightDoor;
            default:
                return null;
        }
    }

    /**
     * lockDoor is a method that locks the door in the specified direction.
     *
     * @param theDirection represents the direction of the door to lock.
     */

    public void lockDoor(String theDirection) {
        Door door = getDoor(theDirection);
        if (door != null) {
            door.lock();
        }
    }
    /**
     * unlockDoor is a method that unlocks the door in the specified direction.
     *
     * @param theDirection represents the direction of the door to unlock.
     */
    public void unlockDoor(String theDirection) {
        Door door = getDoor(theDirection);
        if (door != null) {
            door.unlock();
        }
    }


    /**
     * hasPotion is a method that checks if the room has a potion.
     *
     * @return True if the room has a potion, false otherwise.
     */
    public boolean hasPotion() {
        return hasPotion;
    }

    /**
     * setQuestionForDoor is a setter method that sets the
     * question ID for a specific door direction.
     *
     * @param theDirection represents the direction of the door.
     * @param theQuestionId represents the ID of the question associated with the door.
     */
    public void setQuestionForDoor(String theDirection, int theQuestionId) {
        doorQuestions.put(theDirection, theQuestionId);
    }

    /**
     * getQuestionForDoor is a getter method that retrieves the question ID
     * associated with a specific door direction.
     *
     * @param theDirection represents the direction of the door.
     * @return The ID of the question associated with the door.
     */
    public Integer getQuestionForDoor(String theDirection) {
        return doorQuestions.get(theDirection);
    }

    /**
     *  setHasPotion is a setter method that sets whether
     *  the room has a potion or not.
     *
     * @param theHasPotion represents whether the room has a potion.
     */
    public void setHasPotion(boolean theHasPotion) {
        hasPotion = theHasPotion;
    }

    /**
     * setRoomName is a setter method that sets the
     * name of the room.
     *
     * @param theRoomName represents the name of the room.
     */
    public void setRoomName(String theRoomName){
        myRoomName = theRoomName;
    }

    /**
     * setRoomNumber is a setter method for the number of the room.
     *
     * @param theRoomNumber represents the number of the room.
     */
    public void setRoomNumber(int theRoomNumber){
        myRoomNumber = theRoomNumber;
    }


    /**
     * setUpDoor is a setter method that sets the up door.
     *
     * @param theUpDoor represents the up door to set.
     */
    public void setUpDoor(Door theUpDoor) {
        myUpDoor =  theUpDoor;
    }

    /**
     * setDownDoor is a setter method that sets the down door.
     *
     * @param theDownDoor represents the down door to set.
     */
    public void setDownDoor(Door theDownDoor) {
       myDownDoor = theDownDoor;
    }

    /**
     *setLeftDoor is a setter method that sets the left door.
     *
     * @param theLeftDoor represents the left door to set.
     */
    public void setLeftDoor(Door theLeftDoor) {
        myLeftDoor = theLeftDoor;
    }

    /**
     * setRightDoor is a setter method that sets the right door.
     *
     * @param theRightDoor represents the right door to set.
     */
    public void setRightDoor(Door theRightDoor) {
      myRightDoor = theRightDoor;
    }

    /**
     * setDoorQuestions is a setter method that sets the mapping of door directions to question IDs.
     *
     * @param theDoorQuestions represents the mapping of door directions to question IDs.
     */
    public void setDoorQuestions(Map<String, Integer> theDoorQuestions) {
       doorQuestions = theDoorQuestions;
    }


    /**
     * getUpDoor is a getter method for the up door of the room.
     *
     * @return The up door of the room.
     */
    public Door getUpDoor(){
        return myUpDoor;
}

    /**
     * getDownDoor is a getter method for the down door of the room.
     *
     * @return The down door of the room.
     */
    public Door getDownDoor(){
        return myDownDoor;
    }

    /**
     * getLeftDoor is a getter method for the left door of the room.
     *
     * @return The left door of the room.
     */
    public Door getLeftDoor(){
        return myLeftDoor;
    }

    /**
     * getRightDoor is a getter method for the right door of the room.
     *
     * @return The right door of the room.
     */
    public Door getRightDoor(){
        return myRightDoor;
    }
    /**
     * getRoomNumber is a getter method for the number of the room.
     *
     * @return The number of the room.
     */
    public int getRoomNumber(){
        return myRoomNumber;
    }

    /**
     * getRoomName is a getter method for the name of the room.
     *
     * @return The name of the room.
     */
    public String getRoomName(){
        return myRoomName;
    }


    /**
     * toString method for the entire class.
     *
     * @return the string value of the class
     */
    public String toString() {
        return "Room{" +
                "myUpDoor=" + myUpDoor +
                ", myDownDoor=" + myDownDoor +
                ", myLeftDoor=" + myLeftDoor +
                ", myRightDoor=" + myRightDoor +
                ", myRoomName='" + myRoomName + '\'' +
                ", myRoomNumber=" + myRoomNumber +
                ", doorQuestions=" + doorQuestions +
                '}';
    }



}
