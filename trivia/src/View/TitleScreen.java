package View;

import Controller.TriviaController;
import Model.TriviaModel;

import javax.swing.*;
import javax.swing.text.FlowView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created a title screen with names and button to go to main game.
 */

public class TitleScreen {
    private JFrame myFrame = new JFrame();
    private JPanel myButtonPanel = new JPanel();
    private JPanel myTitalPanel = new JPanel();
    private JPanel myNamePanel = new JPanel();
    public static void main(String[] args){
        new TitleScreen();
    }
    public TitleScreen(){
        createFrame();

    }
    //Creates frame
    public void createFrame(){
        myFrame = new JFrame();
        myButtonPanel = new JPanel();
        myNamePanel = new JPanel();
        myTitalPanel = new JPanel();

        myFrame.setSize(700,400);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Trivia Maze");

        myButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        myNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        myTitalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        myFrame.add(myButtonPanel, BorderLayout.SOUTH);
        myFrame.add(myNamePanel, BorderLayout.CENTER);
        myFrame.add(myTitalPanel, BorderLayout.NORTH);

        addButton();
        namePanel();
        addTital();
        myFrame.setVisible(true);

    }
    //Adding continue button with listener
    public void addButton(){
        JButton button = new JButton("Continue");
        myButtonPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                TriviaController TV = new TriviaController();
                TV.createAndShowGUI();
            }
        });
    }
    //Adding name panel
    public void namePanel(){
        JLabel titalLabel = new JLabel("Rohit Ark & Sado Iman");
        myNamePanel.add(titalLabel);
        titalLabel.setFont(myTitalPanel.getFont().deriveFont(Font.BOLD, 50F));
    }
    //Adding tital
    public void addTital(){
        JLabel titalLabel = new JLabel("Trivia Game");
        myTitalPanel.add(titalLabel);
        titalLabel.setFont(myTitalPanel.getFont().deriveFont(Font.BOLD, 70F));
    }


}
