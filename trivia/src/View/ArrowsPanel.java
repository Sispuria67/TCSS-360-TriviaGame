package View;

import javax.swing.*;
import javax.swing.plaf.basic.BasicArrowButton;
import java.awt.*;
import java.awt.event.ActionListener;

public class ArrowsPanel extends JPanel {

    private final JButton myUpArrow;
    private final JButton myDownArrow;
    private final JButton myLeftArrow;
    private final JButton myRightArrow;


    public ArrowsPanel() {





        ImageIcon upIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/upArrow.png");
        ImageIcon downIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/downArrow.png");
        ImageIcon leftIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/leftArrow.png");
        ImageIcon rightIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/rightArrow.png");

        Image image = upIcon.getImage(); // transform it
        Image imageDown = downIcon.getImage();
        Image imageRight = rightIcon.getImage();
        Image imageLeft = leftIcon.getImage();
        Image newimg = image.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        Image newimg2 = imageDown.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        Image newimg3 = imageRight.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        Image newimg4 = imageLeft.getScaledInstance(42, 42,  java.awt.Image.SCALE_SMOOTH); // scale it the smooth way
        upIcon = new ImageIcon(newimg);
        downIcon = new ImageIcon(newimg2);
        rightIcon = new ImageIcon(newimg3);
        leftIcon = new ImageIcon(newimg4);

        myUpArrow = new JButton(upIcon);
        myDownArrow = new JButton(downIcon);
        myLeftArrow = new JButton(leftIcon);
        myRightArrow = new JButton(rightIcon);

        System.out.println("Left Icon: " + (leftIcon.getImage() != null));
        System.out.println("Right Icon: " + (rightIcon.getImage() != null));


        /*
        myUpArrow= new BasicArrowButton(BasicArrowButton.NORTH);
       myDownArrow=new BasicArrowButton(BasicArrowButton.SOUTH);
       myRightArrow=new BasicArrowButton(BasicArrowButton.EAST);
      myLeftArrow=new BasicArrowButton(BasicArrowButton.WEST);

         */
       setButtonSizes();
        layoutComponents();
        //up and left are disabed to behin with
        myUpArrow.setEnabled(false);
        myLeftArrow.setEnabled(false);

    }

    public Dimension getPreferredSize() {
        return new Dimension(100, 100);
    }
    public void layoutComponents() {

        this.setBackground(new Color(0, 137, 165));

        setPreferredSize(new Dimension(300, 300));

        this.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        //myUpArrow.setBackground(Color.green);


        //left arrow
        gbc.gridx = 0;
        gbc.gridy= 1;
        gbc.anchor=GridBagConstraints.LINE_END;
        gbc.insets = new Insets(3, 3, 3, 3);
       // gbc.gridheight = 5;
       // gbc.gridwidth = 5;
        this.add(myLeftArrow, gbc);

       // up arrow
       gbc.gridx= 1;
       gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(myUpArrow, gbc);


        //donw arrow
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        this.add(myDownArrow, gbc);

        // right arrow
        gbc.gridx = 2;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.LINE_START;
        this.add(myRightArrow, gbc);
    }

    public void addArrowListener(final ActionListener theListener) {
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
        /*
        myUpArrow.setPreferredSize(getPreferredSize());
        myDownArrow.setPreferredSize(getPreferredSize());
        myLeftArrow.setPreferredSize(getPreferredSize());
        myRightArrow.setPreferredSize(getPreferredSize());

         */
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

}
