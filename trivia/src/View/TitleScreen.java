package View;

import Controller.TriviaController;
import Model.TriviaModel;

import javax.swing.*;
import javax.swing.text.FlowView;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class TitleScreen {
    private JFrame frame = new JFrame();
    private JPanel buttonPanel = new JPanel();
    private JPanel titalPanel = new JPanel();
    private JPanel namePanel = new JPanel();
    public static void main(String[] args){
        new TitleScreen();
    }
    public TitleScreen(){
        createFrame();

    }
    public void createFrame(){
        frame = new JFrame();
        buttonPanel = new JPanel();
        namePanel = new JPanel();
        titalPanel = new JPanel();

        frame.setSize(400,200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setTitle("Test");

        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        namePanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        titalPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 5));
        frame.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(namePanel, BorderLayout.CENTER);
        frame.add(titalPanel, BorderLayout.NORTH);


        //frame.pack();
        addButton();
        namePanel();
        addTital();
        frame.setVisible(true);

    }
    public void addButton(){
        JButton button = new JButton("Continue");
        buttonPanel.add(button);
        button.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                frame.setVisible(false);
                TriviaController TV = new TriviaController();
                TV.createAndShowGUI();
            }
        });
    }
    public void namePanel(){
        JLabel titalLabel = new JLabel("Rohit Ark & Sado Iman");
        namePanel.add(titalLabel);
    }
    public void addTital(){
        JLabel titalLabel = new JLabel("Trivia Game");
        titalPanel.add(titalLabel);
    }


}
