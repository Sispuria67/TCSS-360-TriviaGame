/**
 * A package for view.
 */
package View;

import Controller.TriviaController;

import javax.sound.sampled.*;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.IOException;


/**
 * TitleScreen is a class that create a title screen with names and
 * button to go to the main game.
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class TitleScreen {

    /** A private field for the frame of the title screen. */
    private JFrame myFrame;

    /** A private field for the main panel of the title screen. */
    private JPanel myMainPanel;

    /** A private field for the label of the door icon. */
    private JLabel myDoorIconLabel;

    /** A private field for audio clip */
    private Clip myClip;

    /** A private field for game title image file*/
    private Icon myGameIcon;

    /** A private field for door image file*/
    private ImageIcon myImg;

    /** A private field for resizing door image file*/
    private Image myResizedDoor;

    /** A private field for resized door image file*/
    private ImageIcon myDoorImg;


    /**
     * TitleScreen is a constructor method that constructs a new TitleScreen object.
     */
    public TitleScreen() {
        createFrame();
    }


    /**
     * createFrame is a method that creates the frame for the title screen.
     */
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


    /**
     * addMainPanelContent is a method that adds content to the
     * main panel of the title screen.
     */
    public void addMainPanelContent() {
        myGameIcon = new ImageIcon("trivia/src/Images/triviaGame.png");
        myImg = new ImageIcon("trivia/src/Images/doorPixelCenter.png");
        myResizedDoor = myImg.getImage().getScaledInstance(250, 250, Image.SCALE_SMOOTH);
        myDoorImg= new ImageIcon(myResizedDoor);
        JPanel gameIconPanel = new JPanel();
        gameIconPanel.setBackground(new Color(0, 137, 165));
        JLabel gameIconLabel = new JLabel(myGameIcon);
        gameIconPanel.add(gameIconLabel);
        JPanel namePanel = new JPanel();
        namePanel.setBackground(new Color(0, 137, 165));
        JLabel nameLabel = new JLabel("Rohit Ark & Sado Iman");
        nameLabel.setFont(new Font("Monospaced", Font.BOLD, 30));
        namePanel.add(nameLabel);
        JPanel doorPanel = new JPanel();
        doorPanel.setBackground(new Color(0, 137, 165));
        myDoorIconLabel = new JLabel(myDoorImg);
        doorPanel.add(myDoorIconLabel);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setBackground(new Color(0, 137, 165));
        JButton playButton = new JButton("Play");
        playButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        JButton loadButton = new JButton("Load");
        loadButton.setFont(new Font("Monospaced", Font.BOLD, 25));
        playButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                myFrame.setVisible(false);
                TriviaController TV = new TriviaController();
                TV.createAndShowGUI();
                stopTitleScreenSound();
            }
        });
        buttonPanel.add(playButton);
        buttonPanel.add(loadButton);
        myMainPanel.add(Box.createVerticalGlue());
        myMainPanel.add(gameIconPanel);
        myMainPanel.add(namePanel);
        myMainPanel.add(doorPanel);
        myMainPanel.add(buttonPanel);
        myMainPanel.add(Box.createVerticalGlue());
    }


    /**
     * setTitleScreenSound is a method that sets the sound for the title screen.
     */
    private void setTitleScreenSound() {
        try {
            String fileName = "Sounds/titleSound.wav";
            File file = new File(fileName);
            AudioInputStream inputStream = AudioSystem.getAudioInputStream(file);
            myClip = AudioSystem.getClip();
            myClip.open(inputStream);
            myClip.setFramePosition(0);
            myClip.start();
        } catch (UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            e.printStackTrace();
        }
    }


    /**
     *stopTitleScreenSound is a method that stops the sound of the title screen.
     */
    private void stopTitleScreenSound() {
        if (myClip != null && myClip.isRunning()) {
            myClip.stop();
        }
    }

}
