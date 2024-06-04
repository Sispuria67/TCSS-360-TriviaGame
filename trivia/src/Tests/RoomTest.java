/*
TCSS 360
Spirng 2024
 */
package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.Room;

/**
 * Test cases for room class
 */
public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room();
    }
    @Test
    public void testInitialDoorStates() {
        assertFalse(room.getUpDoor());
        assertFalse(room.getDownDoor());
        assertFalse(room.getLeftDoor());
        assertFalse(room.getRightDoor());
    }

    @Test
    public void testSetUpDoor() {
        room.setUpDoor(true);
        assertTrue(room.getUpDoor());
    }

    @Test
    public void testSetDownDoor() {
        room.setDownDoor(true);
        assertTrue(room.getDownDoor());
    }

    @Test
    public void testSetLeftDoor() {
        room.setLeftDoor(true);
        assertTrue(room.getLeftDoor());
    }

    @Test
    public void testSetRightDoor() {
        room.setRightDoor(true);
        assertTrue(room.getRightDoor());
    }

    @Test
    public void testSetRoomName() {
        String roomName = "Test Room";
        room.setRoomName(roomName);
        assertEquals(roomName, room.getRoomName());
    }

}
