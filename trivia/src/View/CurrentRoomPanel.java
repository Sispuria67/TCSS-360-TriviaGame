package View;

import Model.TriviaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;

public class CurrentRoomPanel extends JPanel {

    private final TriviaModel myModel;

    JLabel myText;

    public CurrentRoomPanel(TriviaModel theModel) {
        myModel = theModel;
        myText = new JLabel();

       // this.add(myText);

        layoutComponents();

    }

    private void layoutComponents() {
        setLayout(new BorderLayout());

        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));
        // this.setBorder(BorderFactory.createTitledBorder("Current Room"));
       this.setBackground(new Color(0, 130, 120));
       // this.setBackground(new Color(0, 137, 165));

        String imagePath = "/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/currentRoom2.png";
        ImageIcon titleIcon = new ImageIcon(imagePath);
        JLabel titleLabel = new JLabel(titleIcon);
        titleLabel.setPreferredSize(new Dimension(30, 30));
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


