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

    private JPanel newPanel;

    private final JLabel gameIconLabel;


    public QuestionPanel() {
        setLayout(new BorderLayout());

        // Title panel
        Icon gameIcon = new ImageIcon("/Users/sadoiman/Documents/GitHub/TCSS-360-TriviaGame/trivia/src/triviaQuestion.png");
        gameIconLabel = new JLabel(gameIcon);

      newPanel = new JPanel();





        gameIconLabel.setPreferredSize(new Dimension(30, 30));
        gameIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

        myQuestion = new JLabel();
       // JPanel questionTextPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        //questionTextPanel.add(myQuestion);




        optionsPanel = new JPanel();
        optionsPanel.setLayout(new BoxLayout(optionsPanel, BoxLayout.Y_AXIS));


        mySubmit = new JButton("Submit");
        mySubmit.setFont(new Font("Monospaced", Font.BOLD, 14));


        // add panels to the main panel
        this.add(gameIconLabel, BorderLayout.NORTH);
        this.add(newPanel, BorderLayout.CENTER);


        newPanel.setLayout(new BorderLayout());
        newPanel.add(myQuestion, BorderLayout.NORTH);


        newPanel.add(optionsPanel, BorderLayout.CENTER);
        newPanel.add(mySubmit, BorderLayout.SOUTH);



        layoutComponents();
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
        //this.setBorder(BorderFactory.createTitledBorder("Trivia Question"));
        this.setBackground(new Color(0, 137, 165));
        newPanel.setBackground(new Color(0, 137, 165));
        this.setBorder(BorderFactory.createLineBorder(Color.BLACK, 2));


       // gameIconLabel.setPreferredSize(new Dimension(30, 30));
       // gameIconLabel.setHorizontalAlignment(SwingConstants.CENTER);

      //  this.add(gameIconLabel, BorderLayout.NORTH);
      //  this.add(myQuestion, BorderLayout.CENTER);




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
        myQuestion.setFont(new Font("Monospaced", Font.BOLD, 16));
        myQuestion.setForeground(new Color(0, 220, 120));
        optionsPanel.setBackground(new Color(0, 137, 165));
        optionsPanel.removeAll();

        buttonGroup = null;
        myShortAnswer = null;
//if it's a multiple choice question

        if (question instanceof Question.MultipleChoiceQuestion) {
            Question.MultipleChoiceQuestion mcq = (Question.MultipleChoiceQuestion) question;
            buttonGroup = new ButtonGroup();

            for (String option : mcq.getMyOptions()) {
                JRadioButton optionButton = new JRadioButton(option);

                buttonGroup.add(optionButton);
                optionsPanel.add(optionButton);

            }
        }else if (question instanceof Question.TrueFalseQuestion) {
            buttonGroup = new ButtonGroup();
            JRadioButton trueButton = new JRadioButton("True");
            JRadioButton falseButton = new JRadioButton("False");
         //   trueButton.setBackground(new Color(0, 137, 165));
          //  falseButton.setBackground(new Color(0, 137, 165));
            buttonGroup.add(trueButton);
            buttonGroup.add(falseButton);
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