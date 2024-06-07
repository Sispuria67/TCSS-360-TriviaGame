package Tests;

import Model.Door;
import Model.Room;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class RoomTest {

    private Room room;

    @Before
    public void setUp() {
        room = new Room();
    }

    @Test
    public void testInitialDoorsAreClosed() {
        assertEquals(Door.CLOSED, room.getUpDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getDownDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getLeftDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getRightDoor().getDoorStatus());
    }

    @Test
    public void testLockAndUnlockDoors() {
        room.lockDoor("up");
        assertTrue(room.getUpDoor().isLocked());

        room.unlockDoor("up");
        assertEquals(Door.OPEN, room.getUpDoor().getDoorStatus());
    }

    @Test
    public void testSetAndGetDoorQuestions() {
        room.setQuestionForDoor("up", 1);
        room.setQuestionForDoor("down", 2);

        assertEquals(Integer.valueOf(1), room.getQuestionForDoor("up"));
        assertEquals(Integer.valueOf(2), room.getQuestionForDoor("down"));
    }

    @Test
    public void testPotionPresence() {
        assertFalse(room.hasPotion());

        room.setHasPotion(true);
        assertTrue(room.hasPotion());
    }

    @Test
    public void testSetAndGetRoomName() {
        room.setRoomName("Test Room");
        assertEquals("Test Room", room.getRoomName());
    }

    @Test
    public void testSetAndGetRoomNumber() {
        room.setRoomNumber(1);
        assertEquals(1, room.getRoomNumber());
    }

    @Test
    public void testGetDoor() {
        assertNotNull(room.getDoor("up"));
        assertNotNull(room.getDoor("down"));
        assertNotNull(room.getDoor("left"));
        assertNotNull(room.getDoor("right"));
        assertNull(room.getDoor("invalid_direction"));
    }

    @Test
    public void testToString() {
        room.setRoomName("Test Room");
        room.setRoomNumber(1);
        room.setQuestionForDoor("up", 1);
        room.setHasPotion(true);

        String expected = "Room{myUpDoor=" + room.getUpDoor() +
                ", myDownDoor=" + room.getDownDoor() +
                ", myLeftDoor=" + room.getLeftDoor() +
                ", myRightDoor=" + room.getRightDoor() +
                ", myRoomName='Test Room'" +
                ", myRoomNumber=1" +
                ", doorQuestions={up=1}" +
                '}';
        assertEquals(expected, room.toString());
    }
}
