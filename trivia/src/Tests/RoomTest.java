/**
 * A package for Tests.
 */
package Tests;

import Model.Door;
import Model.Room;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

/**
 * RoomTest is a class that contains unit tests for the Room class.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class RoomTest {

    private Room room;

    /**
     * setUp method initializes a new Room object before each test.
     */
    @Before
    public void setUp() {
        room = new Room();
    }

    /**
     * testInitialDoorsAreClosed method tests if the initial doors are closed.
     */
    @Test
    public void testInitialDoorsAreClosed() {
        assertEquals(Door.CLOSED, room.getUpDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getDownDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getLeftDoor().getDoorStatus());
        assertEquals(Door.CLOSED, room.getRightDoor().getDoorStatus());
    }

    /**
     * testLockAndUnlockDoors method tests locking and unlocking of doors.
     */
    @Test
    public void testLockAndUnlockDoors() {
        room.lockDoor("up");
        assertTrue(room.getUpDoor().isLocked());

        room.unlockDoor("up");
        assertEquals(Door.OPEN, room.getUpDoor().getDoorStatus());
    }

    /**
     * testSetAndGetDoorQuestions method tests setting and getting questions for doors.
     */
    @Test
    public void testSetAndGetDoorQuestions() {
        room.setQuestionForDoor("up", 1);
        room.setQuestionForDoor("down", 2);

        assertEquals(Integer.valueOf(1), room.getQuestionForDoor("up"));
        assertEquals(Integer.valueOf(2), room.getQuestionForDoor("down"));
    }

    /**
     * testPotionPresence method tests the presence of a potion in the room.
     */
    @Test
    public void testPotionPresence() {
        assertFalse(room.hasPotion());

        room.setHasPotion(true);
        assertTrue(room.hasPotion());
    }

    /**
     * testSetAndGetRoomName method tests setting and getting the room name.
     */
    @Test
    public void testSetAndGetRoomName() {
        room.setRoomName("Test Room");
        assertEquals("Test Room", room.getRoomName());
    }

    /**
     * testSetAndGetRoomNumber method tests setting and getting the room number.
     */
    @Test
    public void testSetAndGetRoomNumber() {
        room.setRoomNumber(1);
        assertEquals(1, room.getRoomNumber());
    }

    /**
     * testGetDoor method tests getting doors in different directions.
     */
    @Test
    public void testGetDoor() {
        assertNotNull(room.getDoor("up"));
        assertNotNull(room.getDoor("down"));
        assertNotNull(room.getDoor("left"));
        assertNotNull(room.getDoor("right"));
        assertNull(room.getDoor("invalid_direction"));
    }

    /**
     * testToString method tests the string representation of the Room object.
     */
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
