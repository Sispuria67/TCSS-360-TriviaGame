package View;

import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionListener;

public class CurrentRoomPanel extends JPanel {

    JLabel myText;

    public CurrentRoomPanel() {
        myText = new JLabel();

       // this.add(myText);

        layoutComponents();

    }

    private void layoutComponents() {
        setLayout(new BorderLayout());
        // this.setBorder(BorderFactory.createTitledBorder("Current Room"));
       this.setBackground(new Color(0, 130, 120));
       // this.setBackground(new Color(0, 137, 165));

        String imagePath = "/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/currentRoom.png";
        ImageIcon titleIcon = new ImageIcon(imagePath);
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setPreferredSize(new Dimension(50, 50));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER); // Center the image horizontally

       myText.setHorizontalAlignment(SwingConstants.CENTER);
        // Add the image label to the top of the panel
        this.add(titleLabel, BorderLayout.NORTH);
        this.add(myText, BorderLayout.CENTER);

    }


    public void setMyTextField(String theValue) {

        myText.setText(theValue);
        myText.setFont(new Font("Monospaced", Font.BOLD, 17));

        myText.setForeground(new Color(0, 220, 120));


    }

    public void addCurrentRoomListener(final ActionListener theListener) {
      //  this.myText.addActionListener(theListener);

    }

}


