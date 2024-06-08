/**
 * A package for Tests.
 */
package Tests;

import Model.Door;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * DoorTest is a class that contains unit tests for the Door class.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class DoorTest {

    private Door door;
    private boolean propertyChangeFired;

    /**
     * Sets up a new Door object before each test.
     */
    @Before
    public void setUp() {
        door = new Door();
        propertyChangeFired = false;
    }

    /**
     * Tests the initial door status, which should be CLOSED.
     */
    @Test
    public void testInitialDoorStatus() {
        assertEquals(Door.CLOSED, door.getDoorStatus());
    }

    /**
     * Tests the isLocked method to check if the door is locked.
     */
    @Test
    public void testIsLocked() {
        door.setDoorStatus(Door.LOCKED);
        assertTrue(door.isLocked());
        door.setDoorStatus(Door.CLOSED);
        assertFalse(door.isLocked());
    }

    /**
     * Tests the setDoorStatus method to set the door status.
     */
    @Test
    public void testSetDoorStatus() {
        door.setDoorStatus(Door.OPEN);
        assertEquals(Door.OPEN, door.getDoorStatus());
    }

    /**
     * Tests the close method to close the door.
     */
    @Test
    public void testClose() {
        door.close();
        assertEquals(Door.CLOSED, door.getDoorStatus());
    }

    /**
     * Tests the unlock method to unlock the door.
     */
    @Test
    public void testUnlock() {
        door.unlock();
        assertEquals(Door.OPEN, door.getDoorStatus());
    }

    /**
     * Tests the lock method to lock the door.
     */
    @Test
    public void testLock() {
        door.lock();
        assertEquals(Door.LOCKED, door.getDoorStatus());
    }
}
