/**
 * Class to model a player.
 * @author John Miller
 */
class Player{

    public static final String PLAYER = "Player", COMPUTER = "Computer";
    private char playerMark;
    private String currentPlayer;

    Player(){
        currentPlayer = PLAYER;
        playerMark = 'X';
    }

    char getMark(){
        return playerMark;
    }

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
