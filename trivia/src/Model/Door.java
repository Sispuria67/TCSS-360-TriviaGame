package Model;

public class Door {

    private int myDoorStatus;

    public static final int OPEN = 0;
    public static final int CLOSED = 1;
    public static final int LOCKED = 2;


    public Door(){
        myDoorStatus = CLOSED;

    }

    public void unlock() {
        setDoorStatus(OPEN);
    }

    public Boolean getDoorIsLocked() {
        return myDoorStatus == LOCKED;
    }

    public int getDoorStatus(){
        return myDoorStatus;
    }

    public void setDoorStatus(int theDoorStatus) {
        myDoorStatus = theDoorStatus;
    }

    //instead of setDoorStatus should it be setUpDoor, setDownDoor, etc??
}
