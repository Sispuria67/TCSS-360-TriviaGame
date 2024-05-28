package View;

import Model.Question;

import javax.swing.*;
import java.awt.*;
import java.util.Collections;



public class QuestionPanel extends JPanel {
/*
    private final JRadioButton myRadioOne;
    private final JRadioButton myRadioTwo;
    private final JRadioButton myRadioThree;
    private final JRadioButton myRadioFour;
 */
    private ButtonGroup buttonGroup;
    private final JButton mySubmit;
    private final JLabel myQuestion;

    private JTextField myShortAnswer;

    private JPanel optionsPanel;


    public QuestionPanel(Question theQuestion) {
        setLayout(new BorderLayout());
        myQuestion = new JLabel();
        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));
        mySubmit = new JButton("Submit");

        add(myQuestion, BorderLayout.NORTH);
        add(optionsPanel, BorderLayout.CENTER);
        add(mySubmit, BorderLayout.SOUTH);
        layoutComponents();
        updateQuestion(theQuestion);

    }


    /*
    public JRadioButton getMyRadioOne(){
        return myRadioOne;
    }
    public JRadioButton getMyRadioTwo(){
        return myRadioTwo;
    }
    public JRadioButton getMyRadioThree(){
        return myRadioThree;
    }
    public JRadioButton getMyRadioFour(){
        return myRadioFour;
    }


     */
    public JButton getMySubmit() {
        return mySubmit;
    }

    //use radio buttons
    private void layoutComponents() {
        this.setBorder(BorderFactory.createTitledBorder("Trivia Question"));


      //  this.add(myQuestion);

       // setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        // this.add(myRadioOne);
        // this.add(myRadioTwo);
        // this.add(myRadioThree);
        //this.add(myRadioFour);


        //  this.add(mySubmit);


        //JOptionPane.showMessageDialog(this, "The Question is: ");
    }


    public void updateQuestion(Question question) {
        myQuestion.setText(question.getQuestion());
        optionsPanel.removeAll();
//if it's a multiple choice question
        if (question instanceof Question.MultipleChoiceQuestion) {
            Question.MultipleChoiceQuestion mcq = (Question.MultipleChoiceQuestion) question;
            buttonGroup = new ButtonGroup();
            for (String option : mcq.getMyOptions()) {
                JRadioButton optionButton= new JRadioButton(option);
                buttonGroup.add(optionButton);
                optionsPanel.add(optionButton);

            }
            //if it's true/false queston
        } else if (question instanceof Question.TrueFalseQuestion) {
            buttonGroup = new ButtonGroup();
            JRadioButton trueButton = new JRadioButton("True");
            JRadioButton falseButton = new JRadioButton("False");
            buttonGroup.add(trueButton);
            buttonGroup.add(falseButton);
            optionsPanel.add(trueButton);
            optionsPanel.add(falseButton);

        } else if (question instanceof Question.ShortAnswerQuestion) {
            myShortAnswer = new JTextField(20);
            optionsPanel.add(myShortAnswer);
        }
        revalidate();
        repaint();

    }

    public String getSelectedOption() {
        if (buttonGroup != null) {
            for (AbstractButton button : Collections.list(buttonGroup.getElements())) {
                if (button.isSelected()) {
                    return button.getText();
                }
            }
        }
        return null;
    }


    public String getAnswerFieldText() {
        if (myShortAnswer != null) {
            return myShortAnswer.getText();
        }
        return null;
    }

}