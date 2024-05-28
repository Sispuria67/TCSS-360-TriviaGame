package Model;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TriviaModel {

    private final PropertyChangeSupport myPcs;

    private static TriviaModel myTriviaInstance;


    private TriviaModel(){
        myPcs = new PropertyChangeSupport(this);
    }

    public static TriviaModel getMyTriviaInstance(){
        if (myTriviaInstance == null){
            myTriviaInstance = new TriviaModel();
        }
        return myTriviaInstance;
    }


/*
    public void setMyPlayerWins(final int theWins){
        int old = myPlayerWins;
        myPlayerWins = theWins;
        this.myPcs.firePropertyChange("playerWins", old, myPlayerWins);
    }


 */
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
}
