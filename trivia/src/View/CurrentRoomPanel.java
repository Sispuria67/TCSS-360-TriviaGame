/**
 * A package for view.
 */
package View;


import javax.swing.*;
import java.awt.*;
/**
 * CurrentRoomPanel is a class that holds the necessary
 * information for the current room panel
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class CurrentRoomPanel extends JPanel {
    /** A private field for displaying the current room location  */
    private JLabel myText;

    /**
     * CurrentRoomPanel is constructor method that creates a label
     * for the text and calls the layout for the panel.
     *
     */
    public CurrentRoomPanel() {
        myText = new JLabel();
        layoutComponents();
    }
    /**
     * layoutComponents is a method that sets up the
     * formatting of the panel to be put on frame.
     *
     */
    private void layoutComponents() {
        setLayout(new BorderLayout());
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        this.setBackground(new Color(0, 130, 120));
        String imagePath = "trivia/src/Images/currentRoom2.png";
        ImageIcon titleIcon = new ImageIcon(imagePath);
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setPreferredSize(new Dimension(30, 30));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myText.setHorizontalAlignment(SwingConstants.CENTER);
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(myText, BorderLayout.CENTER);
    }

    /**
     * getTextLabel is a getter method that retrieves the
     * JLabel representing the text.
     *
     * @return The JLabel representing the text.
     */
    public JLabel getTextLabel() {
        return myText;
    }

    /**
     * setMyTextField is a setter method that sets
     * the text field to the new Value.
     *
     * @param theValue is the updated value of the text field.
     */
    public void setMyTextField(String theValue) {
        myText.setText(theValue);
        myText.setFont(new Font("Monospaced", Font.BOLD, 17));
        myText.setForeground(new Color(0, 220, 120));
    }

}


