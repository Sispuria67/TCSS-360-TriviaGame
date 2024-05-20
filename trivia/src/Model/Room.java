package Model;

public class Room {

    private boolean myUpDoor;
    private boolean myDownDoor;
    private boolean myLeftDoor;
    private boolean myRightDoor;

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
