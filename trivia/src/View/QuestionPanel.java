/**
 * A package for view.
 */
package View;

import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;

/**
 * QuestionPanel is a class that represents a panel for displaying questions and options.
 * It allows users to select answers and submit them.
 *
 * @author Sado Iman, Rohit Ark
 * @version 06/7/24
 */

public class QuestionPanel extends JPanel {

    /** A private field to store the submit button listeners. */
    private ArrayList<ActionListener> mySubmitButtonListeners;

    /** A private field representing the button group for multiple choice questions. */
    private ButtonGroup myButtonGroupMultiple;

    /** A private field representing the button group for true/false questions. */
    private ButtonGroup myButtonGroupTrueFalse;

    /** A private final field representing the submit button. */
    private final JButton mySubmit;

    /** A private final field representing the label for the question. */
    private final JLabel myQuestion;

    /** A private field representing the text field for short answer questions. */
    private JTextField myShortAnswer;

    /** A private field representing the panel for displaying options. */
    private JPanel myOptionsPanel;


    /** A private field representing the panel for layout components. */
    private JPanel myNewPanel;

    /** A private final field representing the label for the game icon. */
    private final JLabel myGameIconLabel;


    /**
     * QuestionPanel is a constructor that constructs a new QuestionPanel object.
     */
    public QuestionPanel() {
        setLayout(new BorderLayout());
        Icon gameIcon = new ImageIcon("trivia/src/Images/triviaQuestion.png");
        myGameIconLabel = new JLabel(gameIcon);
        myNewPanel = new JPanel();
        myGameIconLabel.setPreferredSize(new Dimension(30, 30));
        myGameIconLabel.setHorizontalAlignment(SwingConstants.CENTER);
        myQuestion = new JLabel();
        myOptionsPanel = new JPanel();
        myOptionsPanel.setLayout(new BoxLayout(myOptionsPanel, BoxLayout.Y_AXIS));
        mySubmit = new JButton("Submit");
        mySubmit.setFont(new Font("Monospaced", Font.BOLD, 14));
        mySubmit.setMnemonic(KeyEvent.VK_ENTER);
        this.add(myGameIconLabel, BorderLayout.NORTH);
        this.add(myNewPanel, BorderLayout.CENTER);
        mySubmitButtonListeners = new ArrayList<>();
        myNewPanel.setLayout(new BorderLayout());
        myNewPanel.add(myQuestion, BorderLayout.NORTH);
        myNewPanel.add(myOptionsPanel, BorderLayout.CENTER);
        myNewPanel.add(mySubmit, BorderLayout.SOUTH);
        layoutComponents();
    }

    /**
     * getMySubmit is a getter method that gets the submit button.
     *
     * @return The submit button.
     */
    public JButton getMySubmit() {
        return mySubmit;
    }


    /**
     * layoutComponents is a method that sets up the
     * formatting of the panel to be put on frame.
     *
     */
    private void layoutComponents() {
        this.setBackground(new Color(0, 137, 165));
        myNewPanel.setBackground(new Color(0, 137, 165));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }


    /**
     * clearSubmitButtonListeners is a method that clears all submit button listeners.
     */
    public void clearSubmitButtonListeners() {
        for (ActionListener listener : mySubmitButtonListeners) {
            mySubmit.removeActionListener(listener);
        }
        mySubmitButtonListeners.clear();
    }

    /**
     * updateQuestion is a method that updates the question panel with a new question.
     *
     * @param theQuestion represents the new question.
     */
    public void updateQuestion(Question theQuestion) {
        myQuestion.setText(theQuestion.getQuestion());
        myQuestion.setFont(new Font("Monospaced", Font.BOLD, 16));
        myQuestion.setForeground(new Color(0, 220, 120));
        myOptionsPanel.setBackground(new Color(0, 137, 165));
        myOptionsPanel.removeAll();
        myButtonGroupMultiple = null;
        myButtonGroupTrueFalse = null;
        myShortAnswer = null;
        if (theQuestion instanceof Question.MultipleChoiceQuestion) {
            Question.MultipleChoiceQuestion mcq = (Question.MultipleChoiceQuestion) theQuestion;
            myButtonGroupMultiple = new ButtonGroup();
            for (String option : mcq.getMyOptions()) {
               JRadioButton optionButton = new JRadioButton(option);
                myButtonGroupMultiple.add(optionButton);
                myOptionsPanel.add(optionButton);
            }
        }else if (theQuestion instanceof Question.TrueFalseQuestion) {
            myButtonGroupTrueFalse = new ButtonGroup();
            JRadioButton trueButton = new JRadioButton("True");
            JRadioButton falseButton = new JRadioButton("False");
            myButtonGroupTrueFalse.add(trueButton);
            myButtonGroupTrueFalse.add(falseButton);
            myOptionsPanel.add(trueButton);
            myOptionsPanel.add(falseButton);

        } else if (theQuestion instanceof Question.ShortAnswerQuestion) {
            myShortAnswer = new JTextField(5);
            myShortAnswer.setBackground(Color.white);
            myOptionsPanel.add(myShortAnswer);
        }
        revalidate();
        repaint();

    }


    /**
     *  getSelectedAnswer is a method that gets the selected answer
     *  that the player selects.
     *
     * @return The selected answer as a string.
     */
    public String getSelectedAnswer() {
        if (myButtonGroupMultiple != null) {
            int index = 0;
            for (AbstractButton button : Collections.list(myButtonGroupMultiple.getElements())) {
                if (button.isSelected()) {
                    char optionLetter = (char) ('A' + index);
                    return String.valueOf(optionLetter);
                }
                index++;
            }
        } else if (myShortAnswer != null) {
            return myShortAnswer.getText();

        } else if (myButtonGroupTrueFalse != null) {
            for (AbstractButton button : Collections.list(myButtonGroupTrueFalse.getElements())) {
                if (button.isSelected()) {
                    return button.getText();
                }
            }
        }
        return null;
    }


    /**
     * addSubmitButtonListener is a method that adds a submit button listener.
     *
     * @param theListener represents the listener to add.
     */
    public void addSubmitButtonListener(ActionListener theListener) {
        mySubmit.addActionListener(theListener);
    }
}