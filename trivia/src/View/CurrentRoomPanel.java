package View;

import javax.swing.*;
import java.awt.event.ActionListener;

public class CurrentRoomPanel extends JPanel {

    JLabel myText;

    public CurrentRoomPanel() {
        myText = new JLabel();

        this.add(myText);
        layoutComponents();

    }

    private void layoutComponents() {
        this.setBorder(BorderFactory.createTitledBorder("Current Room"));
    }


    public void setMyTextField(String theValue) {
        myText.setText(theValue);
    }

    public void addCurrentRoomListener(final ActionListener theListener) {
      //  this.myText.addActionListener(theListener);

    }

}


