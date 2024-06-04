package View;

import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Collections;



public class QuestionPanel extends JPanel {

    private ArrayList<ActionListener> submitButtonListeners = new ArrayList<>();
    private ButtonGroup buttonGroupMultiple;

    private ButtonGroup buttonGroupTrueFalse;


    private final JButton mySubmit;

    private int selectedIndex;

    private final JLabel myQuestion;

    private JTextField myShortAnswer;

    private JPanel optionsPanel;

    private JPanel newPanel;

    private final JLabel gameIconLabel;
    private Question currentQuestion;



    public QuestionPanel() {

        setLayout(new BorderLayout());

        // Title panel
        Icon gameIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/Images/triviaQuestion.png");
        gameIconLabel = new JLabel(gameIcon);

      newPanel = new JPanel();



        gameIconLabel.setPreferredSize(new Dimension(30, 30));
        gameIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        myQuestion = new JLabel();


        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));


        mySubmit = new JButton("Submit");
        mySubmit.setFont(new Font("Monospaced", Font.BOLD, 14));
        mySubmit.setMnemonic(KeyEvent.VK_ENTER);


        // add panels to the main panel
        this.add(gameIconLabel, BorderLayout.NORTH);
        this.add(newPanel, BorderLayout.CENTER);


        newPanel.setLayout(new BorderLayout());
        newPanel.add(myQuestion, BorderLayout.NORTH);


        newPanel.add(optionsPanel, BorderLayout.CENTER);
        newPanel.add(mySubmit, BorderLayout.SOUTH);



        layoutComponents();
    }



    public JButton getMySubmit() {
        return mySubmit;
    }

    //use radio buttons
    private void layoutComponents() {
        this.setBackground(new Color(0, 137, 165));
        newPanel.setBackground(new Color(0, 137, 165));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));

    }

    public void setSelectedIndex(int selectedIndex) {
        this.selectedIndex = selectedIndex;
    }

    // Method to get the selected index
    public int getSelectedIndex() {
        return selectedIndex;
    }





    public void clearSubmitButtonListeners() {
        for (ActionListener listener : submitButtonListeners) {
            mySubmit.removeActionListener(listener);
        }
        submitButtonListeners.clear();
    }
    public void updateQuestion(Question question) {
        this.currentQuestion = question;
        myQuestion.setText(question.getQuestion());
        myQuestion.setFont(new Font("Monospaced", Font.BOLD, 16));
        myQuestion.setForeground(new Color(0, 220, 120));
        optionsPanel.setBackground(new Color(0, 137, 165));
        optionsPanel.removeAll();

        buttonGroupMultiple = null;
        buttonGroupTrueFalse = null;
        myShortAnswer = null;


        if (question instanceof Question.MultipleChoiceQuestion) {
            Question.MultipleChoiceQuestion mcq = (Question.MultipleChoiceQuestion) question;
            buttonGroupMultiple = new ButtonGroup();

            for (String option : mcq.getMyOptions()) {
               JRadioButton optionButton = new JRadioButton(option);

                buttonGroupMultiple.add(optionButton);
                optionsPanel.add(optionButton);

            }
        }else if (question instanceof Question.TrueFalseQuestion) {
            buttonGroupTrueFalse = new ButtonGroup();
            JRadioButton trueButton = new JRadioButton("True");
            JRadioButton falseButton = new JRadioButton("False");
         //   trueButton.setBackground(new Color(0, 137, 165));
          //  falseButton.setBackground(new Color(0, 137, 165));
            buttonGroupTrueFalse.add(trueButton);
            buttonGroupTrueFalse.add(falseButton);
            optionsPanel.add(trueButton);
            optionsPanel.add(falseButton);
            //if it's true/false queston

        } else if (question instanceof Question.ShortAnswerQuestion) {
            myShortAnswer = new JTextField(5);
            myShortAnswer.setBackground(Color.white);
           // myShortAnswer.setSize(new Dimension(20, 10));
            optionsPanel.add(myShortAnswer);
        }
        revalidate();
        repaint();

    }


    public String getSelectedOption() {
        if (buttonGroupMultiple != null) {
            for (AbstractButton button : Collections.list(buttonGroupMultiple.getElements())) {
                if (button.isSelected()) {
                    return button.getText();
                }
            }
        }
        return null;
    }

    public String getSelectedAnswer() {
        if (buttonGroupMultiple != null) { // If it's a multiple choice question
            int index = 0;
            for (AbstractButton button : Collections.list(buttonGroupMultiple.getElements())) {
                if (button.isSelected()) {
                    // Convert index to option letter (A, B, C, D)
                    char optionLetter = (char) ('A' + index);
                    return String.valueOf(optionLetter);
                }
                index++;
            }
        } else if (myShortAnswer != null) { // If it's a short answer question
            return myShortAnswer.getText(); // Return the text entered in the short answer field

        } else if (buttonGroupTrueFalse != null) { // If it's a true/false question
            // Check which true/false option is selected
            for (AbstractButton button : Collections.list(buttonGroupTrueFalse.getElements())) {
                if (button.isSelected()) {
                    return button.getText(); // Return "True" or "False"
                }
            }


        }
        return null; // Return null if no option is selected or no text is entered
    }







    public Question getCurrentQuestion() {
        return currentQuestion;
    }

    public String getAnswerFieldText() {
        if (myShortAnswer != null) {
            return myShortAnswer.getText();
        }
        return null;
    }

    public void addSubmitButtonListener(ActionListener theListener) {
        mySubmit.addActionListener(theListener);
    }
}