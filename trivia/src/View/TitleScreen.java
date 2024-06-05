package View;

import Controller.TriviaController;
import Model.TriviaModel;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * Created a title screen with names and button to go to the main game.
 */

public class TitleScreen {
    private JFrame myFrame;
    private JPanel myMainPanel;
    private JLabel doorIconLabel;

    Icon gameIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/triviaGame.png");
    ImageIcon img = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/doorPixel.png");

    Image resizedDoor = img.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
    ImageIcon doorImg= new ImageIcon(resizedDoor);
    public TitleScreen() {
        createFrame();
    }


    public void createFrame() {
        myFrame = new JFrame();
        myMainPanel = new JPanel();
        myFrame.setResizable(false);


        myFrame.setSize(700, 700);
        myFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myFrame.setTitle("Trivia Maze");

        myMainPanel.setBackground(new Color(0, 137, 165));
        myMainPanel.setLayout(new BoxLayout(myMainPanel, BoxLayout.Y_AXIS));

        myFrame.add(myMainPanel, BorderLayout.CENTER);

        addMainPanelContent();
        myFrame.setVisible(true);
    }

    public void addMainPanelContent() {

        //game Icon Panel
        JPanel gameIconPanel = new JPanel();
        gameIconPanel.setBackground(new Color(0, 137, 165));
        JLabel gameIconLabel = new JLabel(gameIcon);
        gameIconPanel.add(gameIconLabel);

        //name Panel
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(0, 137, 165));
        JLabel nameLabel = new JLabel("Rohit Ark & Sado Iman");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 30));

        namePanel.add(nameLabel);



        // Door Icon Panel
        JPanel doorPanel = new JPanel();
        doorPanel.setBackground(new Color(0, 137, 165));
        doorIconLabel = new JLabel(doorImg);
        doorPanel.add(doorIconLabel);

        //button Panel
      JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 137, 165));
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                TriviaController TV = new TriviaController(TriviaModel.getMyTriviaInstance());
                TV.createAndShowGUI();
            }
        });
        buttonPanel.add(playButton);

        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(gameIconPanel);
       // myMainPanel.add(titlePanel);
        myMainPanel.add(namePanel);

        myMainPanel.add(doorPanel);
        myMainPanel.add(buttonPanel);
        myMainPanel.add(Box.createVerticalGlue());
    }


}
