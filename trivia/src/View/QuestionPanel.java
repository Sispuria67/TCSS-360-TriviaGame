package View;

import javax.swing.*;
import java.awt.*;

public class QuestionPanel extends JPanel {

    private final JRadioButton myRadioOne;
    private final JRadioButton myRadioTwo;
    private final JRadioButton myRadioThree;
    private final JRadioButton myRadioFour;

    private final JButton mySubmit;

    private final ButtonGroup buttonGroup;

    private final JLabel myQuestion;





    public QuestionPanel() {
        //create a method that returns a string, connected to the SQL database where questions are and pass them
        //as parameters here where it says earth, jupiter etc
        myRadioOne = new JRadioButton("Jupiter");
        myRadioTwo = new JRadioButton("Earth");
        myRadioThree = new JRadioButton("Mars");
        myRadioFour = new JRadioButton("Venus");
        myQuestion = new JLabel("Question: What is the largest planet?");
        mySubmit = new JButton("Submit");

        //the font settings  of the questions
        Font font = new Font("Arial", Font.ITALIC, 15);
        Font font2 = new Font("Arial", Font.BOLD, 18);
        myRadioOne.setFont(font);
        myRadioTwo.setFont(font);
        myRadioThree.setFont(font);
        myRadioFour.setFont(font);
        myQuestion.setFont(font2);


        buttonGroup = new ButtonGroup();
        buttonGroup.add(myRadioOne);
        buttonGroup.add(myRadioTwo);
        buttonGroup.add(myRadioThree);
        buttonGroup.add(myRadioFour);
        layoutComponents();

    }

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

    public JButton getMySubmit(){
        return mySubmit;
    }
//use radio buttons
    private void layoutComponents() {
        this.setBorder(BorderFactory.createTitledBorder("Trivia Question"));


  this.add(myQuestion);

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));

        this.add(myRadioOne);
        this.add(myRadioTwo);
        this.add(myRadioThree);
        this.add(myRadioFour);


        this.add(mySubmit);



        //JOptionPane.showMessageDialog(this, "The Question is: ");
    }


}
