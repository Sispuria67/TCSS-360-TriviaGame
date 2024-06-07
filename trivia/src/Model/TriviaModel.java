package Model;

import org.sqlite.SQLiteDataSource;

import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;
import java.io.Serializable;

public class TriviaModel implements Serializable {

    private final PropertyChangeSupport myPcs;

    private String lastEnteredDirection;

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

    public String getLastEnteredDirection() {
        return lastEnteredDirection;
    }

    public void setLastEnteredDirection(String direction) {
        String oldDirection = this.lastEnteredDirection;
        this.lastEnteredDirection = direction;
        myPcs.firePropertyChange("lastEnteredDirection", oldDirection, this.lastEnteredDirection);
    }

    public void setGameOver(final boolean theGameWon) {
        myGameWon = theGameWon;
        myPcs.firePropertyChange("gameWon", false, myGameWon);
    }
    public TriviaModel(){
        myPcs = new PropertyChangeSupport(this);
    }

    public static TriviaModel getMyTriviaInstance(){
        if (myTriviaInstance == null){
            myTriviaInstance = new TriviaModel();
        }
        return myTriviaInstance;
    }
    public void reset(){
        myBank = 0;
        myGameWon = false;
        gameState = "";
        lastEnteredDirection = "";
        character.setRow(0);
        character.setCol(0);
    }



//arrows (up, down, left, right)
    public void addPropertyChangeListener(final PropertyChangeListener theListener) {
        myPcs.addPropertyChangeListener(theListener);
    }

    public void removePropertyChangeListener(final PropertyChangeListener theListener) {
        this.myPcs.removePropertyChangeListener(theListener);
    }
}
