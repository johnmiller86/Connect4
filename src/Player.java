/**
 * Class to model a player.
 * @author John Miller
 */
class Player{

    // Global constant
    static final String PLAYER = "Player";
    private static final String COMPUTER = "Computer";

    // Instance vars
    private char playerMark;
    private String currentPlayer;

    // Constructor
    Player(){
        currentPlayer = PLAYER;
        playerMark = 'X';
    }

    /**
     * Gets the current player's mark.
     * @return the mark.
     */
    char getMark(){
        return playerMark;
    }

    /**
     * Gets the current player.
     * @return the player.
     */
    String getCurrentPlayer(){
        return currentPlayer;
    }
    /**
     * Switches players.
     */
    void switchPlayer(){
        if (currentPlayer.equals(PLAYER)){
            currentPlayer = COMPUTER;
            playerMark = 'O';
        }else{
            currentPlayer = PLAYER;
            playerMark = 'X';
        }
    }
}
