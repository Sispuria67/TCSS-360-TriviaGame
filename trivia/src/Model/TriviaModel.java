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

    public static void gameLogic(){

    }

    public void startGame() {
    }


    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }
}
