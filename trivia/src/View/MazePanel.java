/**
 * A package for view.
 */
package View;

import javax.swing.*;
import java.awt.*;
import Model.*;
import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;

/**
 * MazePanel is a class representing the panel of the maze in the game view.
 * It displays the maze, character, doors, potions, and exit.
 *
 *  @author Sado Iman, Rohit Ark
 *  @version 06/7/24
 *
 */
public class MazePanel extends JPanel implements Serializable {
    /** Unique class identifier used for serialization */
    private static final long serialVersionUID = 389329324849384L;

    /* A private field representing the character in the maze.*/
    private CharacterModel myCharacter;

    /** A private transient BufferedImage representing the character's image. */
    private transient BufferedImage myCharacterImage;

    /** A private transient Image representing the door image. */
    private transient Image doorImage;

    /** A private transient Image representing the potion image. */
    private transient Image potionImage;

    /** A private transient final JLabel representing the game icon label. */
    private transient final JLabel gameIconLabel;

    /** A private  field for the number of columns in the maze. */
    private static final int cols = 5;

    /** A private  field for the number of rows in the maze. */
    private  static final int rows = 5;

    /** A private field representing the x-coordinate origin. */
    private static final int originX = 23;

    /** A private field representing the y-coordinate origin. */
    private static final int originY = 37;

    /** A private field representing the side length of each cell. */
    private static final int cellSide = 90;

    /** A private field representing the row index of the exit cell. */
    private static final int exitRow = 4;
    /** A private field representing the column index of the exit cell. */
    private static final int exitCol = 4;

    /** A private 2D array representing the rooms in the maze. */
    private Room[][] myRoom;


    /**
     * MazePanel is a constructor that constructs a new MazePanel object.
     */
    public MazePanel() {
        Icon  gameIcon = new ImageIcon("trivia/src/Images/triviaGame.png");
        gameIconLabel = new JLabel(gameIcon);
        myRoom = new Room[rows][cols];
        myCharacter = new CharacterModel(0, 0);
        initializeDoors();
        myCharacter.setCurrentRoom(myRoom);
        loadDoorImage();
        loadPotionImage();
        loadImages();
        setPreferredSize(new Dimension(cols * cellSide + originX, rows * cellSide + originY));
        layoutComponents();
    }

    /**
     * getRoom is a getter method that retrieves the array of rooms.
     *
     * @return The array of rooms.
     */
    public Room[][] getRoom(){
        return myRoom;
    }



    /**
     * paintComponent is a method that overrides the paintComponent method to
     * customize the panel's appearance.
     *
     * @param g represents the Graphics object to paint on.
     */

    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                if (myRoom[i][j].hasPotion()) {
                    int potionX = originX + j * cellSide;
                    int potionY = originY + i * cellSide;
                    g.drawImage(potionImage, potionX + 10, potionY + 10, cellSide - 10, cellSide - 10, this);
                }
            }
        }
        Graphics2D g2d = (Graphics2D) g;

        g2d.setStroke(new BasicStroke(2));
        g2d.setColor(new Color(139, 69, 19));
        for (int i = 0; i < rows + 1; i++) {
            g2d.drawLine(originX,originY + i * cellSide, originX+cols* cellSide, originY +i * cellSide);
        }
        for(int i = 0;i <cols +  1; i++){
            g2d.drawLine(originX + i*cellSide, originY, originX + i * cellSide, originY + rows * cellSide);

        }

        g.setColor(new Color(139, 69, 19));
        for (int i = 1; i < rows ; i++) {
          g.fillRect((originX + cellSide /4), (originY + i * cellSide - cellSide / 4) + 10 , cellSide / 2, cellSide / 4);

          g.fillRect((originX + cellSide) + 20, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

          g.fillRect((originX + cellSide) + 110, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

          g.fillRect((originX + cellSide) + 200, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

          g.fillRect((originX + cellSide) + 290, (originY + i * cellSide - cellSide /4) +10, cellSide / 2 , cellSide / 4);

        }


        for (int i = 1; i < cols ; i++) {
           g.fillRect(originX + i * cellSide - cellSide /8, originY + cellSide / 4, cellSide / 4, cellSide / 2);

            g.fillRect(originX + i * cellSide - cellSide /8, (originY + cellSide) + 17 , cellSide / 4, cellSide / 2);

            g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 115, cellSide / 4, cellSide / 2);

            g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 200, cellSide / 4, cellSide / 2);

           g.fillRect(originX + i * cellSide - cellSide / 8, (originY + cellSide) + 290, cellSide / 4, cellSide / 2);

        }

        int doorX = originX + exitCol * cellSide;
        int doorY = originY + exitRow * cellSide;
        g.drawImage(doorImage, doorX + 10, doorY, cellSide, cellSide, this);
        int characterX = originX + myCharacter.getCol() * cellSide;
        int characterY = originY + myCharacter.getRow() * cellSide;
        g.drawImage(myCharacterImage, characterX, characterY, cellSide, cellSide, this);
        int exitX = (originX + exitCol * cellSide + cellSide / 3) + 5  ;
        int exitY = (originY + exitRow * cellSide + cellSide /2) + 1;
        g.setColor(Color.RED);
        Font font = new Font("Arial", Font.BOLD, 11);
        g.setFont(font);
        g.drawString("EXIT", exitX, exitY);
    }


    /**
     * moveCharacter is a method that moves the player
     * in the given direction.
     *
     * @param theDirection represents the direction player wants to move.
     *
     */
    public void moveCharacter(String theDirection) {
   switch(theDirection){
           case "up":
               myCharacter.moveUp();
               myCharacter.setCurrentRoom(myRoom);
                break;
            case "down":
                myCharacter.moveDown();
              myCharacter.setCurrentRoom(myRoom);
                break;
         case"left":
              myCharacter.moveLeft();
             myCharacter.setCurrentRoom(myRoom);
                break;
            case "right":
                myCharacter.moveRight();
                myCharacter.setCurrentRoom(myRoom);
                break;
     }
     repaint();
    }


    /**
     * initializeDoors is a method that initializes the rooms and sets up
     * their properties and connections.
     */
    private void initializeDoors() {
        myRoom = new Room[rows][cols];
        for (int i = 0; i < rows; i++) {
            for (int j = 0; j < cols; j++) {
                myRoom[i][j] = new Room();
            }
        }

        myRoom[3][3].setHasPotion(true);
        myRoom[2][1].setHasPotion(true);
        myRoom[0][4].setHasPotion(true);
        myRoom[4][0].setHasPotion(true);

        // Set up room 0
        setRoomAndDoors(myRoom[0][0], "Room 0", 0, false, true, false, true);

        // Set up room 1
        setRoomAndDoors(myRoom[0][1], "Room 1", 1, false, true, true, true);

        // Set up room 2
        setRoomAndDoors(myRoom[0][2], "Room 2", 2, false, true, true, true);

        // Set up room 3
        setRoomAndDoors(myRoom[0][3], "Room 3", 3, false, true, true, true);

        // Set up room 4
        setRoomAndDoors(myRoom[0][4], "Room 4", 4, false, true, true, false);

        // Set up room 5
        setRoomAndDoors(myRoom[1][0], "Room 5", 5, true, true, false, true);

        // Set up room 6
        setRoomAndDoors(myRoom[1][1], "Room 6", 6, true, true, true, true);

        // Set up room 7
        setRoomAndDoors(myRoom[1][2], "Room 7", 7, true, true, true, true);

        // Set up room 8
        setRoomAndDoors(myRoom[1][3], "Room 8", 8, true, true, true, true);

        // Set up room 9
        setRoomAndDoors(myRoom[1][4], "Room 9", 9, true, true, true, false);

        // Set up room 10
        setRoomAndDoors(myRoom[2][0], "Room 10", 10, true, true, false, true);

        // Set up room 11
        setRoomAndDoors(myRoom[2][1], "Room 11", 11, true, true, true, true);

        // Set up room 12
        setRoomAndDoors(myRoom[2][2], "Room 12", 12, true, true, true, true);

        // Set up room 13
        setRoomAndDoors(myRoom[2][3], "Room 13", 13, true, true, true, true);

        // Set up room 14
        setRoomAndDoors(myRoom[2][4], "Room 14", 14, true, true, true, false);

        // Set up room 15
        setRoomAndDoors(myRoom[3][0], "Room 15", 15, true, true, false, true);

        // Set up room 16
        setRoomAndDoors(myRoom[3][1], "Room 16", 16, true, true, true, true);

        // Set up room 17
        setRoomAndDoors(myRoom[3][2], "Room 17", 17, true, true, true, true);

        // Set up room 18
        setRoomAndDoors(myRoom[3][3], "Room 18", 18, true, true, true, true);

        // Set up room 19
        setRoomAndDoors(myRoom[3][4], "Room 19", 19, true, true, true, false);

        // Set up room 20
        setRoomAndDoors(myRoom[4][0], "Room 20", 20, true, false, false, true);

        // Set up room 21
        setRoomAndDoors(myRoom[4][1], "Room 21", 21, true, false, true, true);

        // Set up room 22
        setRoomAndDoors(myRoom[4][2], "Room 22", 22, true, false, true, true);

        // Set up room 23
        setRoomAndDoors(myRoom[4][3], "Room 23", 23, true, false, true, true);

        // Set up room 24
        setRoomAndDoors(myRoom[4][4], "Room 24", 24, true, false, true, false);
    }

    /**
     * setRoomAndDoors is a method that sets up the properties and connections of a room.
     *
     * @param theRoom represents the room to set up.
     * @param theRoomName represents the name of the room.
     * @param theRoomNumber represents the number of the room.
     * @param theUp represents whether there is an upward door.
     * @param theDown represents whether there is a downward door.
     * @param theLeft represents whether there is a leftward door.
     * @param theRight represents whetherr there is a rightward door.
     */

    private void setRoomAndDoors(Room theRoom, String theRoomName, int theRoomNumber, boolean theUp, boolean theDown, boolean theLeft, boolean theRight) {
        theRoom.setRoomName(theRoomName);
        theRoom.setRoomNumber(theRoomNumber);
        if (theUp) theRoom.getUpDoor().unlock(); else theRoom.getUpDoor().lock();
        if (theDown) theRoom.getDownDoor().unlock(); else theRoom.getDownDoor().lock();
        if (theLeft) theRoom.getLeftDoor().unlock(); else theRoom.getLeftDoor().lock();
        if (theRight) theRoom.getRightDoor().unlock(); else theRoom.getRightDoor().lock();
    }

    /**
     * loadImages is a method that loads images required for the maze.
     */
    private void loadImages() {
        try {
            myCharacterImage = ImageIO.read(new File("trivia/src/Images/mike2.png"));
            serializeBufferedImage(myCharacterImage, "trivia/src/Images/mike2_serialized.png");
        } catch (IOException e) {
            e.printStackTrace();
        }
         }

    /**
     * deserializeBufferedImage is a method that deserializes a BufferedImage from a file.
     *
     * @param theSerializedFile represents the file containing the serialized image.
     * @return The deserialized BufferedImage.
     */
    private BufferedImage deserializeBufferedImage(String theSerializedFile) {
        try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(theSerializedFile))) {
            int length = ois.readInt();
            if (length > 0) {
                byte[] imageBytes = new byte[length];
                ois.readFully(imageBytes);
                ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
                return ImageIO.read(bais);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }


    /**
     * loadDoorImage is a method that loads the door image required for the maze.
     */
    private void loadDoorImage() {
        try {
            doorImage = ImageIO.read(new File("trivia/src/Images/doorPixel.png"));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * serializeBufferedImage is a method that serializes a BufferedImage to a file.
     *
     * @param theImage represents the BufferedImage to serialize.
     * @param theFilename represents the name of the file to write the serialized image to.
     */
    public static void serializeBufferedImage(BufferedImage theImage, String theFilename) {
        try {
            File outputFile = new File(theFilename);
            ImageIO.write(theImage, "png", outputFile);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    /**
     * loadPotionImage is a methof that loads the potion image.
     */
    private void loadPotionImage() {
        try {

            potionImage = ImageIO.read(new File("trivia/src/Images/potionNew.png"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * getCol is a getter method for the column value.
     *
     * @return the column value
     */
    public int getCols(){
        return cols;
    }

    /**
     * getRow is a getter method for the row value.
     *
     * @return the row value
     */
    public int getRows(){
        return rows;
    }

    /**
     * layoutComponents is a method that sets up the
     * formatting of the panel to be put on frame.
     *
     */
    private void layoutComponents() {
        this.setBackground(new Color(0, 137, 165));
        this.add(gameIconLabel, BorderLayout.NORTH);
    }


    /**
     *  getBufferedImage is a method that Gets the buffered image.
     *
     * @return The buffered image.
     */
    public BufferedImage getBufferedImage() {
        return myCharacterImage;
    }

    /**
     * setBufferedImageis a methof that sets the buffered image.
     *
     * @param theBufferedImage The buffered image to set.
     */
    public void setBufferedImage(BufferedImage theBufferedImage) {
        myCharacterImage = theBufferedImage;
        repaint();
    }

    /**
     * writeObject is a method that Serializes the object to the output stream.
     *
     * @param theOut represent the output stream to write to.
     * @throws IOException If an I/O error occurs.
     */
    public void writeObject(java.io.ObjectOutputStream theOut) throws IOException{
        theOut.defaultWriteObject();
        writeBufferedImage(theOut, myCharacterImage);
    }

    /**
     * readObject is a method that deserializes the object from the input stream.
     *
     * @param theIn represents the input stream to read from.
     * @throws IOException  If an I/O error occurs.
     * @throws ClassNotFoundException If the class of a serialized object cannot be found.
     */
public void readObject(ObjectInputStream theIn) throws IOException, ClassNotFoundException {
    theIn.defaultReadObject();
    myCharacterImage = readBufferedImage(theIn);
    repaint();
}

    /**
     * writeBufferedImage is a method that writes the BufferedImage to the output stream.
     *
     * @param theOos reprsent the output stream to write to.
     * @param theImage reprsents the BufferedImage to write.
     * @throws IOException If an I/O error occurs.
     */
    public void writeBufferedImage(ObjectOutputStream theOos, BufferedImage theImage) throws IOException {
        if (theImage != null) {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            ImageIO.write(theImage, "png", baos);
            byte[] imageBytes = baos.toByteArray();
            theOos.writeInt(imageBytes.length);
            theOos.write(imageBytes);
        } else {
            theOos.writeInt(0);
        }
    }

    /**
     * readBufferedImage is a method that reads a BufferedImage from the input stream.
     *
     * @param theOis reprsents the input stream to read from.
     * @return The read BufferedImage.
     * @throws IOException If an I/O error occurs.
     */
    private BufferedImage readBufferedImage(ObjectInputStream theOis) throws IOException {
        int length = theOis.readInt();
        if (length > 0) {
            byte[] imageBytes = new byte[length];
            theOis.readFully(imageBytes);
            ByteArrayInputStream bais = new ByteArrayInputStream(imageBytes);
            return ImageIO.read(bais);

        } else {
            return null;
        }
    }


    /**
     * setRoom is a stter method that sets the rooms of the maze.
     *
     * @param theRoom represents the rooms to set.
     */
    public void setRoom(Room[][] theRoom) {
        myRoom = myRoom;
    }
}
