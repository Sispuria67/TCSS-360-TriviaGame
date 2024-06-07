package Model;
import java.io.*;
import java.util.HashMap;
import java.util.Map;

public class Room implements Serializable{

    private static final long serialVersionUID = 6399201422927996955L;
    private Door myUpDoor;
    private Door myDownDoor;
    private Door myLeftDoor;
    private Door myRightDoor;

    private String myRoomName;

    private int myRoomNumber;

    private Map<String, Integer> doorQuestions;

    private boolean hasPotion;

  //  private final TriviaModel myModel;



    public Room(){
       // myModel = theModel;
        doorQuestions = new HashMap<>();
        myUpDoor = new Door();
        myDownDoor = new Door();
        myLeftDoor = new Door();
        myRightDoor = new Door();

    }

    public Door getDoor(String direction) {
        switch (direction) {
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



    public void lockDoor(String direction) {
        Door door = getDoor(direction);
        if (door != null) {
            door.lock();
        }
    }

    // Unlock the door in the specified direction
    public void unlockDoor(String direction) {
        Door door = getDoor(direction);
        if (door != null) {
            door.unlock();
        }
    }
    /*
    public Room[][] getRoom(){
        return room;
    }

     */
    public boolean hasPotion() {
        return hasPotion;
    }

    public void setQuestionForDoor(String direction, int questionId) {
        doorQuestions.put(direction, questionId);
    }

    public Integer getQuestionForDoor(String direction) {
        return doorQuestions.get(direction);
    }

    public void setHasPotion(boolean hasPotion) {
        this.hasPotion = hasPotion;
    }
//these will all return false if a door in that direction does not exist
public Door getUpDoor(){
        return myUpDoor;
}

    public Door getDownDoor(){
        return myDownDoor;
    }
    public Door getLeftDoor(){
        return myLeftDoor;
    }
    public Door getRightDoor(){
        return myRightDoor;
    }

    public void setRoomName(String theRoomName){
        myRoomName = theRoomName;
    }
    public int getRoomNumber(){
        return myRoomNumber;
    }

    public void setRoomNumber(int theRoomNumber){
        myRoomNumber = theRoomNumber;
    }

    public String getRoomName(){
        return myRoomName;
    }
    //if a door above exists return true
    public void setUpDoor(Door theUpDoor){
        myUpDoor = theUpDoor;
    }

    //if a door below exists return true
    public void setDownDoor(Door theDownDoor){
        myDownDoor = theDownDoor;
    }
    //if door to the left exists return true
    public void setLeftDoor(Door theLeftDoor){
        myLeftDoor = theLeftDoor;
    }
    //if door to the right exists return true
    public void setRightDoor(Door theRightDoor){
        myRightDoor = theRightDoor;
    }


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


    /*
    public String toString(){
        return

    }



     */

}
