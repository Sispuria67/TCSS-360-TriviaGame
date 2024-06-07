package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;


public class Door implements Serializable {

    private final PropertyChangeSupport myPcs;
    @Serial
    private static final long serialVersionUID = 1988664346L;

    private int myDoorStatus;

    public static final int OPEN = 0;
    public static final int CLOSED = 1;
    public static final int LOCKED = 2;


   

    public Door() {
         myPcs = new PropertyChangeSupport(this);
        myDoorStatus = CLOSED;
    }

    public Boolean isLocked() {
        return myDoorStatus == LOCKED;
    }

    public int getDoorStatus() {
        return myDoorStatus;
    }

    public void setDoorStatus(int theDoorStatus) {
        int oldStatus = myDoorStatus;
        myDoorStatus = theDoorStatus;
        myPcs.firePropertyChange("doorStatus", oldStatus, myDoorStatus);
    }

    public void close(){
        setDoorStatus(CLOSED);
    }
    public void unlock() {
        setDoorStatus(OPEN);
    }
    public void lock(){
        setDoorStatus(LOCKED);
    }


    //instead of setDoorStatus should it be setUpDoor, setDownDoor, etc??
}
