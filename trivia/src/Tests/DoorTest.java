package Tests;

import Model.Door;
import org.junit.Before;
import org.junit.Test;


import static org.junit.Assert.*;

public class DoorTest {

    private Door door;
    private boolean propertyChangeFired;

    @Before
    public void setUp() {
        door = new Door();
        propertyChangeFired = false;
    }

    @Test
    public void testInitialDoorStatus() {
        assertEquals(Door.CLOSED, door.getDoorStatus());
    }

    @Test
    public void testIsLocked() {
        door.setDoorStatus(Door.LOCKED);
        assertTrue(door.isLocked());
        door.setDoorStatus(Door.CLOSED);
        assertFalse(door.isLocked());
    }

    @Test
    public void testSetDoorStatus() {
        door.setDoorStatus(Door.OPEN);
        assertEquals(Door.OPEN, door.getDoorStatus());
    }

    @Test
    public void testClose() {
        door.close();
        assertEquals(Door.CLOSED, door.getDoorStatus());
    }

    @Test
    public void testUnlock() {
        door.unlock();
        assertEquals(Door.OPEN, door.getDoorStatus());
    }

    @Test
    public void testLock() {
        door.lock();
        assertEquals(Door.LOCKED, door.getDoorStatus());
    }


}
