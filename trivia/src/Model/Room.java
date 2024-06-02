package Model;
import java.io.*;

public class Room implements Serializable{

    private static final long serialVersionUID = 6399201422927996955L;
    private boolean myUpDoor;
    private boolean myDownDoor;
    private boolean myLeftDoor;
    private boolean myRightDoor;

    private String myRoomName;

    public Room(){
       // myUpDoor = false;
       // myDownDoor = false;
      //  myLeftDoor = false;
      //  myRightDoor = false;

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
    public void setUpDoor(boolean theUpDoor){
        myUpDoor = theUpDoor;
    }

    public void setRoomName(String theRoomName){
        myRoomName = theRoomName;
    }

    public String getRoomName(){
        return myRoomName;
    }

    public void setDownDoor(boolean theDownDoor){
        myDownDoor = theDownDoor;
    }
    public void setLeftDoor(boolean theLeftDoor){
        myLeftDoor = theLeftDoor;
    }
    public void setRightDoor(boolean theRightDoor){
        myRightDoor = theRightDoor;
    }

    /*
    public String toString(){
        return

    }



     */

}
