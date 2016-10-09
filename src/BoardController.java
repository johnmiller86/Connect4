import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;

/**
 * Controller class for connect 4.
 * @author John Miller
 */
class BoardController {

    // BoardModel model
    private final BoardModel board;
    private final Player player;

    /// Constructor
    BoardController(BoardModel board) {
        this.board = board;
        player = new Player();
    }

    /**
     * Initializes the board.
     * @param rows the row count.
     * @param cols the column count.
     * @param connect_num the connect number.
     */
    void configureBoard(int rows, int cols, int connect_num){
        board.setRows(rows);
        board.setCols(cols);
        board.setConnect_num(connect_num);
        board.initializeBoard();
    }
    /**
     * Checks validity and add a marker to the board.
     * @param col the requested column.
     * @return false if invalid, true otherwise.
     */
    boolean addMarker(int col) {
            col -= 1;

        if (col < board.getCols() && col >= 0) {
            for (int i = board.getRows() - 1; i >= 0; i--) {
                if (board.getBoard()[i][col] == ' ') {
                    board.updateData(i, col, player.getMark());
                    switchPlayer();
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * AI picks a column.
     */
    void computerMove(){
        addMarker(getBestMove());
    }

    /**
     * Gets the best move for the AI to make.
     * @return the best move.
     */
    private int getBestMove(){
        int bestMove = 0;
        ArrayList<HashMap> possibleMoves = getPossibleMoves();
        ArrayList<HashMap> theMoves = new ArrayList<>();
        Iterator iterator;

        for (HashMap move : possibleMoves) {
            iterator = move.entrySet().iterator();
            while ((iterator.hasNext())) {
                HashMap.Entry kvp = (HashMap.Entry) iterator.next();
                int row = (int) kvp.getKey();
                int col = (int) kvp.getValue();
                int blocks = 0;

                // Check for possible blocks
                // Look South
                for (int i = row; i < board.getRows(); i++) {
                    if (board.getBoard()[i][col] == 'X') {
                        blocks++;
                    }
                    else if (board.getBoard()[i][col] == 'O' ){
                        // Chain broken, break
                        i = board.getRows();
                    }
                }

                // Look east
                for (int i = col; i < board.getCols(); i++) {
                    if (board.getBoard()[row][i] == 'X') {
                        blocks++;
                    }
                    // Chain broken, break
                    else if (board.getBoard()[row][i] == 'O' || board.getBoard()[row][i] == ' '){
                        i = board.getCols();
                    }
                }

                // Look west
                for (int i = col; i >= 0; i--) {
                    if (board.getBoard()[row][i] == 'X') {
                        blocks++;
                    }
                    // Chain broken, break
                    else if (board.getBoard()[row][i] == 'O' || board.getBoard()[row][i] == ' '){
                        i = -1;
                    }
                }

                // Look southeast
                for (int i = row; i < board.getRows(); i++) {
                    for (int j = col; j < board.getCols(); j++) {
                        if (board.getBoard()[i][j] == 'X') {
                            blocks++;
                        }
                        // Chain broken, break
                        else if (board.getBoard()[i][j] == 'O'){
                            i = board.getRows();
                            j = board.getCols();
                        }
                    }
                }

                // Look southwest
                for (int i = row; i < board.getRows(); i++) {
                    for (int j = col; j >= 0; j--) {
                        if (board.getBoard()[i][j] == Player.PLAYER_MARK) {
                            blocks++;
                        }
                        // Chain broken, break
                        else if (board.getBoard()[i][j] == 'O'){
                            i = board.getRows();
                            j = -1;
                        }
                    }
                }
                HashMap<Integer, Integer> hashMap = new HashMap<>();
                hashMap.put(col, blocks);
                theMoves.add(hashMap);
            }
        }

        // Determine best move
        int counter = 0;
        for (HashMap hashMap : theMoves){
            iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry)iterator.next();
            if ((int)entry.getValue() > counter){
                counter = (int)entry.getValue();
                bestMove = (int)entry.getKey();
            }

        }
        return bestMove + 1;
    }

    /**
     * Gets all the AI's possible moves.
     * @return the HashMap of moves.
     */
    private ArrayList<HashMap> getPossibleMoves(){
        ArrayList<HashMap> moveCoordinates= new ArrayList<>();
        ArrayList<Integer> columns = new ArrayList<>();
        //HashMap<Integer, Integer> moveCoordinates = new HashMap<>();
        for (int i = board.getRows() - 1; i >= 0; i--){
            for (int j = board.getCols() - 1; j >= 0; j--){
                boolean flag = false;
                // Look for empty space
                if (board.getBoard()[i][j] == ' '){
                    // Check if column has already been recorded
                    for (Integer integer : columns) {
                        if (integer == j){
                            flag = true;
                        }
                    }
                    // Record
                    if (!flag) {
                        HashMap<Integer, Integer> hashMap = new HashMap<>();
                        hashMap.put(i, j);
                        moveCoordinates.add(hashMap);
                        columns.add(j);
                    }
                }
            }
        }
        return moveCoordinates;
    }

    /**
     * Switches the current player.
     */
    private void switchPlayer(){

        if (gameComplete()){
            player.switchPlayer();
        }
    }

    String getPlayer(){
        return player.getCurrentPlayer();
    }

    /**
     * Checks if the game is complete.
     * @return true or false.
     */
    boolean gameComplete(){
        int x, o;

        // Checking horizontal
        for (int i = 0; i < board.getRows(); i++){
            x = 0; o = 0;
            for (int j = 0; j < board.getCols(); j++){
                if (board.getBoard()[i][j] == Player.PLAYER_MARK){
                    x++;
                    o = 0;
                }else if(board.getBoard()[i][j] == Player.COMPUTER_MARK){
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
                if (x >= board.getConnect_num() || o >= board.getConnect_num()){
                    return false;
                }
            }
        }

        // Checking vertical
        for (int i = 0; i < board.getRows(); i++){
            x = 0; o = 0;
            for (int j = 0; j < board.getRows(); j++){
                if (board.getBoard()[j][i] == Player.PLAYER_MARK){
                    x++;
                    o = 0;
                }else if(board.getBoard()[j][i] == Player.COMPUTER_MARK){
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
                if (x >= board.getConnect_num() || o >= board.getConnect_num()){
                    return false;
                }
            }
        }

        // Checking forward slash
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++){
                x = 0; o = 0;
                if (i + board.getConnect_num() -1 < board.getRows() && j + board.getConnect_num() - 1 < board.getCols()) {
                    for (int k = 0; k < board.getConnect_num(); k++) {
                        if (board.getBoard()[i + k][j + k] == Player.PLAYER_MARK) {
                            x++;
                            o = 0;
                        } else if (board.getBoard()[i + k][j + k] == Player.COMPUTER_MARK) {
                            o++;
                            x = 0;
                        } else {
                            x = 0;
                            o = 0;
                        }
                    }
                    if (x >= board.getConnect_num() || o >= board.getConnect_num()) {
                        return false;
                    }
                }
            }
        }

        // Checking backslash
        for (int i = board.getRows() - 1; i >= 0; i--){
            for (int j = board.getCols() - 1; j >= 0; j--){
                x = 0; o = 0;
                if (i - board.getConnect_num() + 1 >= 0 && j + board.getConnect_num() - 1 < board.getCols()) {
                    for (int k = 0; k < board.getConnect_num(); k++) {
                        if (board.getBoard()[i - k][j + k] == Player.PLAYER_MARK) {
                            x++;
                            o = 0;
                        } else if (board.getBoard()[i - k][j + k] == Player.COMPUTER_MARK) {
                            o++;
                            x = 0;
                        } else {
                            x = 0;
                            o = 0;
                        }
                    }
                    if (x >= board.getConnect_num() || o >= board.getConnect_num()) {
                        return false;
                    }
                }
            }
        }
        return true;
    }

    /**
     * Checks if the board is full.
     * @return true or false.
     */
    boolean isFull(){
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++){
                if (board.getBoard()[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }
}