package Model;

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
}
