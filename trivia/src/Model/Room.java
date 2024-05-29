package Model;

import java.util.HashMap;
import java.util.Map;

public class Room {

    private boolean myUpDoor;
    private boolean myDownDoor;
    private boolean myLeftDoor;
    private boolean myRightDoor;

    private String myRoomName;

    private int myRoomNumber;

    private Map<String, Integer> doorQuestions;

    private boolean hasPotion;


    public Room(){
        doorQuestions = new HashMap<>();
       // myUpDoor = false;
       // myDownDoor = false;
      //  myLeftDoor = false;
      //  myRightDoor = false;

    }

    public boolean hasPotion() {
        return hasPotion;
    }

    public void setHasPotion(boolean hasPotion) {
        this.hasPotion = hasPotion;
    }
//these will all return false if a door in that direction does not exist
public boolean getUpDoor(){
        return myUpDoor;
}

    public boolean getDownDoor(){
        return myDownDoor;
    }
    public boolean getLeftDoor(){
        return myLeftDoor;
    }
    public boolean getRightDoor(){
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
    public void setUpDoor(boolean theUpDoor){
        myUpDoor = theUpDoor;
    }

    //if a door below exists return true
    public void setDownDoor(boolean theDownDoor){
        myDownDoor = theDownDoor;
    }
    //if door to the left exists return true
    public void setLeftDoor(boolean theLeftDoor){
        myLeftDoor = theLeftDoor;
    }
    //if door to the right exists return true
    public void setRightDoor(boolean theRightDoor){
        myRightDoor = theRightDoor;
    }
    public void setQuestionForDoor(String direction, int questionId) {
        doorQuestions.put(direction, questionId);
    }

    public Integer getQuestionForDoor(String direction) {
        return doorQuestions.get(direction);
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
