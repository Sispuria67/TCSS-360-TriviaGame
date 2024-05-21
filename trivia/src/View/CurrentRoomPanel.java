package View;

import javax.swing.*;

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

}


