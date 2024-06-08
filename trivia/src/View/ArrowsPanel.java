package View;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class ArrowsPanel extends JPanel {

    private ArrayList<ActionListener> ArrowButtonListeners = new ArrayList<>();

    private final JButton myUpArrow;
    private final JButton myDownArrow;
    private ActionListener currentListener;
    private final JButton myLeftArrow;
    private final JButton myRightArrow;




    public ArrowsPanel() {
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

    public void removeArrowListeners() {
            myUpArrow.removeActionListener(currentListener);
            myDownArrow.removeActionListener(currentListener);
            myLeftArrow.removeActionListener(currentListener);
            myRightArrow.removeActionListener(currentListener);
        }

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
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

    public void addArrowListener(final ActionListener theListener) {
       currentListener = theListener;
        this.myUpArrow.addActionListener(theListener);
        this.myDownArrow.addActionListener(theListener);
        this.myLeftArrow.addActionListener(theListener);
        this.myRightArrow.addActionListener(theListener);
    }

    private void setButtonSizes() {
        Dimension buttonSize = new Dimension(26, 26);
        myUpArrow.setPreferredSize(buttonSize);
        myDownArrow.setPreferredSize(buttonSize);
        myLeftArrow.setPreferredSize(buttonSize);
        myRightArrow.setPreferredSize(buttonSize);
    }
    public JButton getMyUpArrow() {
        return myUpArrow;
    }

    public JButton getMyDownArrow() {
        return myDownArrow;
    }

    public JButton getMyLeftArrow() {
     return myLeftArrow;
    }

    public JButton getMyRightArrow() {return myRightArrow;
    }

    public void setEnabledUp(final boolean theValue) {
        myUpArrow.setEnabled(theValue);
    }

    public void setEnabledDown(final boolean theValue) {
        myDownArrow.setEnabled(theValue);
    }

    public void setEnabledRight(final boolean theValue) {
        myRightArrow.setEnabled(theValue);
    }
    public void setEnabledLeft(final boolean theValue) {
        myLeftArrow.setEnabled(theValue);
    }

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
