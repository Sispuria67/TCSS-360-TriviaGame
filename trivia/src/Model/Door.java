package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.*;

public class Door implements Serializable {

    private transient PropertyChangeSupport myPcs;
    @Serial
    private static final long serialVersionUID = 1L;

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

    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.removePropertyChangeListener(theListener);
    }
    private void writeObject(ObjectOutputStream oos) throws IOException {
        oos.defaultWriteObject();
        // Manually write static fields
        oos.writeInt(OPEN);
        oos.writeInt(CLOSED);
        oos.writeInt(LOCKED);
    }

    @Serial
    private void readObject(ObjectInputStream ois) throws IOException, ClassNotFoundException {
        ois.defaultReadObject();
        myPcs = new PropertyChangeSupport(this);
        // Manually read static fields
        int open = ois.readInt();
        int closed = ois.readInt();
        int locked = ois.readInt();

        // Reassign static fields if needed (usually not necessary unless static values are changed)
        // Door.OPEN = open;  // Uncomment if you have a need to change the values dynamically
        // Door.CLOSED = closed;
        // Door.LOCKED = locked;
    }
    //instead of setDoorStatus should it be setUpDoor, setDownDoor, etc??
}
