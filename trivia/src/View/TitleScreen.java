package View;

import Controller.TriviaController;
import Model.TriviaModel;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;

/**
 * Created a title screen with names and button to go to the main game.
 */

public class TitleScreen {
    private JFrame myFrame;
    private JPanel myMainPanel;
    private JLabel doorIconLabel;

    private Clip clip;

    Icon gameIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/triviaGame.png");
    ImageIcon img = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/doorPixelCenter.png");

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
        setTitleScreenSound();
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

        //load Panel
        JButton loadButton = new JButton("Load");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 25));


        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                TriviaController TV = new TriviaController(TriviaModel.getMyTriviaInstance());
                TV.createAndShowGUI();
                stopTitleScreenSound();
            }
        });
        buttonPanel.add(playButton);
        buttonPanel.add(loadButton);

        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(gameIconPanel);
       // myMainPanel.add(titlePanel);
        myMainPanel.add(namePanel);

        myMainPanel.add(doorPanel);
        myMainPanel.add(buttonPanel);
        myMainPanel.add(Box.createVerticalGlue());
    }

    private void setTitleScreenSound() {
        try {

            String fileName = "Sounds/titleSound.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            clip = AudioSystem.getClip();
            clip.open(inputStream);
            clip.setFramePosition(0);
            clip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }

    private void stopTitleScreenSound() {
        if (clip != null && clip.isRunning()) {
            clip.stop();
        }
    }

}
