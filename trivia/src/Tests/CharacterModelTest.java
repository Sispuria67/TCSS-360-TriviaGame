package Tests;

import Model.CharacterModel;
import Model.Room;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class CharacterModelTest {

    private CharacterModel characterModel;
    private Room[][] rooms;

    @Before
    public void setUp() {
        rooms = new Room[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rooms[i][j] = new Room();
            }
        }
        characterModel = new CharacterModel(2, 2);
        characterModel.setCurrentRoom(rooms);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(2, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    @Test
    public void testMoveUp() {
        characterModel.moveUp();
        assertEquals(1, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    @Test
    public void testMoveDown() {
        characterModel.moveDown();
        assertEquals(3, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    @Test
    public void testMoveLeft() {
        characterModel.moveLeft();
        assertEquals(2, characterModel.getRow());
        assertEquals(1, characterModel.getCol());
    }

    @Test
    public void testMoveRight() {
        characterModel.moveRight();
        assertEquals(2, characterModel.getRow());
        assertEquals(3, characterModel.getCol());
    }

    @Test
    public void testMoveUpBoundary() {
        characterModel.setRow(0);
        characterModel.moveUp();
        assertEquals(0, characterModel.getRow());
    }

    @Test
    public void testMoveDownBoundary() {
        characterModel.setRow(4);
        characterModel.moveDown();
        assertEquals(4, characterModel.getRow());
    }

    @Test
    public void testMoveLeftBoundary() {
        characterModel.setCol(0);
        characterModel.moveLeft();
        assertEquals(0, characterModel.getCol());
    }

    @Test
    public void testMoveRightBoundary() {
        characterModel.setCol(4);
        characterModel.moveRight();
        assertEquals(4, characterModel.getCol());
    }

    @Test
    public void testSetCurrentRoom() {
        Room[][] newRooms = new Room[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                newRooms[i][j] = new Room();
            }
        }
        characterModel.setCurrentRoom(newRooms);
        assertEquals(newRooms, characterModel.getCurrentRoom().getRoomName());
    }

    @Test
    public void testToString() {
        assertEquals("room number is Room 2,2", characterModel.toString());
    }
}
