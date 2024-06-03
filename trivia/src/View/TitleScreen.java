package View;

import Controller.TriviaController;
import Model.TriviaModel;

import javax.swing.*;
import javax.swing.text.FlowView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen {
    private JFrame muFrame = new JFrame();
    private JPanel myButtonPanel = new JPanel();
    private JPanel myTitalPanel = new JPanel();
    private JPanel myNamePanel = new JPanel();
    public static void main(String[] args){
        new TitleScreen();
    }
    public TitleScreen(){
        createFrame();

    }
    public void createFrame(){
        muFrame = new JFrame();
        myButtonPanel = new JPanel();
        myNamePanel = new JPanel();
        myTitalPanel = new JPanel();

        muFrame.setSize(400,200);
        muFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        muFrame.setTitle("Trivia Maze");

        myButtonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        myNamePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        myTitalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        muFrame.add(myButtonPanel, BorderLayout.SOUTH);
        muFrame.add(myNamePanel, BorderLayout.CENTER);
        muFrame.add(myTitalPanel, BorderLayout.NORTH);


        //frame.pack();
        addButton();
        namePanel();
        addTital();
        muFrame.setVisible(true);

    }
    public void addButton(){
        JButton button = new JButton("Continue");
        myButtonPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                muFrame.setVisible(false);
                TriviaController TV = new TriviaController();
                TV.createAndShowGUI();
            }
        });
    }
    public void namePanel(){
        JLabel titalLabel = new JLabel("Rohit Ark & Sado Iman");
        myNamePanel.add(titalLabel);
    }
    public void addTital(){
        JLabel titalLabel = new JLabel("Trivia Game");
        myTitalPanel.add(titalLabel);
    }


}
