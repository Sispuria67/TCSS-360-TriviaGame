/**
 * A package for view.
 */

package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;


/**
 * ArrowsPanel is a class that holds the necessary
 * information for the arrows panel
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */
public class ArrowsPanel extends JPanel {

   /* A private field that stores ActionListeners for arrow buttons.*/
    private ArrayList<ActionListener> ArrowButtonListeners;

    /** A private final field representing the up arrow button.*/
    private final JButton myUpArrow;

    /* A private final field representing the down arrow button. */
    private final JButton myDownArrow;

    /* A private field representing the current ActionListener.*/
    private ActionListener currentListener;
    /* A private final field representing the left arrow button.*/
    private final JButton myLeftArrow;

    /* A private final field representing the right arrow button.*/
    private final JButton myRightArrow;



    /**
     * ArrowsPanel is a constructor that constructs an ArrowsPanel with
     * arrow buttons for navigation.
     */
    public ArrowsPanel() {
        ArrowButtonListeners = new ArrayList<>();
        ImageIcon upIcon = new ImageIcon("trivia/src/Images/upArrow.png");
        ImageIcon downIcon = new ImageIcon("trivia/src/Images/downArrow.png");
        ImageIcon leftIcon = new ImageIcon("trivia/src/Images/leftArrow.png");
        ImageIcon rightIcon = new ImageIcon("trivia/src/Images/rightArrow.png");
        Image image = upIcon.getImage();
        Image imageDown = downIcon.getImage();
        Image imageRight = rightIcon.getImage();
        Image imageLeft = leftIcon.getImage();
        Image newimg = image.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH);
        Image newimg2 = imageDown.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH);
        Image newimg3 = imageRight.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH);
        Image newimg4 = imageLeft.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH);
        upIcon = new ImageIcon(newimg);
        downIcon = new ImageIcon(newimg2);
        rightIcon = new ImageIcon(newimg3);
        leftIcon = new ImageIcon(newimg4);
        myUpArrow = new JButton(upIcon);
        myDownArrow = new JButton(downIcon);
        myLeftArrow = new JButton(leftIcon);
        myRightArrow = new JButton(rightIcon);
        setButtonSizes();
        layoutComponents();
        myUpArrow.setEnabled(false);
        myLeftArrow.setEnabled(false);
    }

    /**
     * removeArrowListeners is a method that removes all ActionListener
     * from the arrow buttons.
     */
    public void removeArrowListeners() {
            myUpArrow.removeActionListener(currentListener);
            myDownArrow.removeActionListener(currentListener);
            myLeftArrow.removeActionListener(currentListener);
            myRightArrow.removeActionListener(currentListener);
        }


    /**
     * getPreferredSize is a method that specifies the
     * preferred size of this component.
     *
     * @return The preferred size of the ArrowsPanel.
     */
    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
    /**
     * layoutComponents is a method that sets up the
     * formatting of the panel to be put on frame.
     *
     */
    public void layoutComponents() {
        this.setBackground(new Color(0, 137, 165));
        setPreferredSize(new Dimension(300, 300));
        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy= 1;
        gbc.anchor=GridBagConstraints.LINE_END;
        gbc.insets = new Insets(3, 3, 3, 3);
        this.add(myLeftArrow, gbc);
        gbc.gridx= 1;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(myUpArrow, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(myDownArrow, gbc);
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(myRightArrow, gbc);
    }



    /**
     * addArrowListener is a method that adds an ActionListener to all arrow buttons.
     *
     * @param theListener represents the ActionListener to be added.
     */
    public void addArrowListener(final ActionListener theListener) {
        currentListener = theListener;
        this.myUpArrow.addActionListener(theListener);
        this.myDownArrow.addActionListener(theListener);
        this.myLeftArrow.addActionListener(theListener);
        this.myRightArrow.addActionListener(theListener);
    }


    /**
     * setButtonSizes is a method that sets the
     * sizes for the arrow buttons.
     *
     */
    private void setButtonSizes() {
        Dimension buttonSize = new Dimension(26, 26);
        myUpArrow.setPreferredSize(buttonSize);
        myDownArrow.setPreferredSize(buttonSize);
        myLeftArrow.setPreferredSize(buttonSize);
        myRightArrow.setPreferredSize(buttonSize);
    }

    /**
     * getMyUpArrow is a getter method that retrieves the JButton representing the up arrow.
     *
     * @return The JButton representing the up arrow.
     */
    public JButton getMyUpArrow() {
        return myUpArrow;
    }
    /**
     * getMyDownArrow is a getter method that retrieves the JButton representing the down arrow.
     *
     * @return The JButton representing the down arrow.
     */
    public JButton getMyDownArrow() {
        return myDownArrow;
    }

    /**
     * getMyLeftArrow is a getter method that retrieves the JButton representing the left arrow.
     *
     * @return The JButton representing the left arrow.
     */
    public JButton getMyLeftArrow() {
        return myLeftArrow;
    }
    /**
     * getMyRightArrow is a getter method that retrieves the JButton representing the right arrow.
     *
     * @return The JButton representing the right arrow.
     */
    public JButton getMyRightArrow() {
        return myRightArrow;
    }


    /**
     * setEnabledUp is a method that sets the enabled status of the up arrow button.
     *
     * @param theValue represents the boolean value indicating whether the up arrow button should be enabled.
     */
    public void setEnabledUp(final boolean theValue) {
        myUpArrow.setEnabled(theValue);
    }

    /**
     * setEnabledDown is a method that sets the enabled status of the down arrow button.
     *
     * @param theValue represents the boolean value indicating whether the down arrow button should be enabled.
     */
    public void setEnabledDown(final boolean theValue) {
        myDownArrow.setEnabled(theValue);
    }

    /**
     * setEnabledRight is a method that sets the enabled status of the right arrow button.
     *
     * @param theValue represents the boolean value indicating whether the right arrow button should be enabled.
     */
    public void setEnabledRight(final boolean theValue) {
        myRightArrow.setEnabled(theValue);
    }
    /**
     * setEnabledLeft is a method that sets the enabled status of the left arrow button.
     *
     * @param theValue represents the boolean value indicating whether the left arrow button should be enabled.
     */
    public void setEnabledLeft(final boolean theValue) {
        myLeftArrow.setEnabled(theValue);
    }


    /**
     * clearArrowPanelListeners is a method that clears all ActionListener from the arrow buttons.
     */
    public void clearArrowPanelListeners() {
        for (ActionListener listener : ArrowButtonListeners) {
            myUpArrow.removeActionListener(listener);
            myDownArrow.removeActionListener(listener);
            myRightArrow.removeActionListener(listener);
            myLeftArrow.removeActionListener(listener);
        }
        ArrowButtonListeners.clear();
    }

}
