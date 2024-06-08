/**
 * A package for model.
 */
package Model;

import java.beans.PropertyChangeSupport;
import java.io.Serial;
import java.io.Serializable;

/**
 * Door is a class that represents the doors
 * in the rooms.
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class Door implements Serializable {

    /** Unique class identifier used for serialization */
    @Serial
    private static final long serialVersionUID = 1988664346L;

    /** A private field for door status value */
    private int myDoorStatus;

    /** A private field for door open status */
    public static final int OPEN = 0;

    /** A private field for door closed status */
    public static final int CLOSED = 1;

    /** A private field for door locked status */
    public static final int LOCKED = 2;

    /**
     * Door is constructor method that initializes
     * the door status.
     *
     */
    public Door() {
        myDoorStatus = CLOSED;
    }


    /**
     * isLocked is a method that checks if the door is locked.
     *
     * @return whether the door is locked or not.
     */
    public Boolean isLocked() {
        return myDoorStatus == LOCKED;
    }

    /**
     * getDoorStatus is a getter method for the door status.
     * @return the door status
     */
    public int getDoorStatus() {
        return myDoorStatus;
    }

    /**
     * setDoorStatus is a setter method for the door status.
     *
     * @param theDoorStatus represents the door status.
     */
    public void setDoorStatus(int theDoorStatus) {
        int oldStatus = myDoorStatus;
        myDoorStatus = theDoorStatus;
    }

    /**
     * close is a method that closes the door.
     */
    public void close(){
        setDoorStatus(CLOSED);
    }

    /**
     * unlock is a method that unlocks the door.
     */
    public void unlock() {
        setDoorStatus(OPEN);
    }

    /**
     * lock is a method that locks the door.
     */
    public void lock(){
        setDoorStatus(LOCKED);
    }

    /**
     * toString method for the entire class.
     *
     * @return the string value of the class
     */
    @Override
    public String toString() {
        String status;
        switch (myDoorStatus) {
            case OPEN -> status = "OPEN";
            case CLOSED -> status = "CLOSED";
            case LOCKED -> status = "LOCKED";
            default -> status = "UNKNOWN";
        }
        return "Door{" +
                "myDoorStatus=" + status +
                '}';
    }

}
