package Model;

import org.sqlite.SQLiteDataSource;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class TriviaModel {

    private final PropertyChangeSupport myPcs;

    private static TriviaModel myTriviaInstance;

    private int myBank;

    private boolean myGameWon;

    private String gameState;

    private static TriviaModel instance;
    private Room[][] rooms;
    private CharacterModel character;
    //private Map<Integer, Question> questions;
    private SQLiteDataSource dataSource;

    public void startGame(){
        setGameOver(false);
        this.gameState = "";
    }

    public String getGameState() {
        return gameState;
    }

    private void setGameState(final String theState) {
        String oldState = gameState;
        gameState = theState;
        this.myPcs.firePropertyChange("gameState", oldState, gameState);
    }

    public void setGameOver(final boolean theGameWon) {
        myGameWon = theGameWon;
        myPcs.firePropertyChange("gameWon", false, myGameWon);
    }
    private TriviaModel(){
        myPcs = new PropertyChangeSupport(this);
    }

    public static TriviaModel getMyTriviaInstance(){
        if (myTriviaInstance == null){
            myTriviaInstance = new TriviaModel();
        }
        return myTriviaInstance;
    }


//arrows (up, down, left, right)
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        this.myPcs.removePropertyChangeListener(theListener);
    }
}
