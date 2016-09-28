import java.util.Observable;

/**
 * Class to model a player.
 */
public class Player{

    private final String PLAYER = "Player", COMPUTER = "Computer";
    private char playerMark;
    private String currentPlayer;

    public Player(){
        currentPlayer = PLAYER;
        playerMark = 'X';
    }

    public char getMark(){
        return playerMark;
    }

    public String getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * Switches players.
     */
    public void switchPlayer(){
        if (currentPlayer.equals(PLAYER)){
            currentPlayer = COMPUTER;
            playerMark = 'O';
        }else{
            currentPlayer = PLAYER;
            playerMark = 'X';
        }
    }
}
