package Tests;

import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.Test;
import Model.CharacterModel;
import Model.Room;

public class CharacterModelTest {

    private CharacterModel character;
    private Room[][] rooms;

    @Before
    public void setUp() {
        rooms = new Room[5][5];
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                rooms[i][j] = new Room();
                rooms[i][j].setRoomName("Room " + i + "," + j);
            }
        }
        character = new CharacterModel(2, 2);
        character.setCurrentRoom(rooms);
    }

    @Test
    public void testInitialPosition() {
        assertEquals(2, character.getRow());
        assertEquals(2, character.getCol());
    }

    @Test
    public void testMoveUp() {
        character.moveUp();
        assertEquals(1, character.getRow());
        assertEquals(2, character.getCol());
    }

    @Test
    public void testMoveDown() {
        character.moveDown();
        assertEquals(3, character.getRow());
        assertEquals(2, character.getCol());
    }

    @Test
    public void testMoveLeft() {
        character.moveLeft();
        assertEquals(2, character.getRow());
        assertEquals(1, character.getCol());
    }

    @Test
    public void testMoveRight() {
        character.moveRight();
        assertEquals(2, character.getRow());
        assertEquals(3, character.getCol());
    }

    @Test
    public void testMoveUpBoundary() {
        character = new CharacterModel(0, 2);
        character.setCurrentRoom(rooms);
        character.moveUp();
        assertEquals(0, character.getRow());
    }

    @Test
    public void testMoveDownBoundary() {
        character = new CharacterModel(4, 2);
        character.setCurrentRoom(rooms);
        character.moveDown();
        assertEquals(4, character.getRow());
    }

    @Test
    public void testMoveLeftBoundary() {
        character = new CharacterModel(2, 0);
        character.setCurrentRoom(rooms);
        character.moveLeft();
        assertEquals(0, character.getCol());
    }

    @Test
    public void testMoveRightBoundary() {
        character = new CharacterModel(2, 4);
        character.setCurrentRoom(rooms);
        character.moveRight();
        assertEquals(4, character.getCol());
    }

    @Test
    public void testGetCurrentRoom() {
        assertEquals("Room 2,2", character.getCurrentRoom().getRoomName());
    }

    @Test
    public void testToString() {
        assertEquals("room number is Room 2,2", character.toString());
    }
}
