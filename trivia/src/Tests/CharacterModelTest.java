/**
 * A package for Tests.
 */
package Tests;

import Model.CharacterModel;
import Model.Room;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 * CharacterModelTest is a class that contains unit tests for the CharacterModel class.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class CharacterModelTest {

    private CharacterModel characterModel;
    private Room[][] rooms;

    /**
     * Sets up a new CharacterModel object and a grid of rooms before each test.
     */
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

    /**
     * Tests the initial position of the character.
     */
    @Test
    public void testInitialPosition() {
        assertEquals(2, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    /**
     * Tests the movement of the character upwards.
     */
    @Test
    public void testMoveUp() {
        characterModel.moveUp();
        assertEquals(1, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    /**
     * Tests the movement of the character downwards.
     */
    @Test
    public void testMoveDown() {
        characterModel.moveDown();
        assertEquals(3, characterModel.getRow());
        assertEquals(2, characterModel.getCol());
    }

    /**
     * Tests the movement of the character to the left.
     */
    @Test
    public void testMoveLeft() {
        characterModel.moveLeft();
        assertEquals(2, characterModel.getRow());
        assertEquals(1, characterModel.getCol());
    }

    /**
     * Tests the movement of the character to the right.
     */
    @Test
    public void testMoveRight() {
        characterModel.moveRight();
        assertEquals(2, characterModel.getRow());
        assertEquals(3, characterModel.getCol());
    }

    /**
     * Tests the boundary condition for moving the character upwards.
     */
    @Test
    public void testMoveUpBoundary() {
        characterModel.setRow(0);
        characterModel.moveUp();
        assertEquals(0, characterModel.getRow());
    }

    /**
     * Tests the boundary condition for moving the character downwards.
     */
    @Test
    public void testMoveDownBoundary() {
        characterModel.setRow(4);
        characterModel.moveDown();
        assertEquals(4, characterModel.getRow());
    }

    /**
     * Tests the boundary condition for moving the character to the left.
     */
    @Test
    public void testMoveLeftBoundary() {
        characterModel.setCol(0);
        characterModel.moveLeft();
        assertEquals(0, characterModel.getCol());
    }

    /**
     * Tests the boundary condition for moving the character to the right.
     */
    @Test
    public void testMoveRightBoundary() {
        characterModel.setCol(4);
        characterModel.moveRight();
        assertEquals(4, characterModel.getCol());
    }
}
