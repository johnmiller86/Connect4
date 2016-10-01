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
        if (col > 0) {
            col -= 1;
        }
        if (col < board.getCols() && col >= 0) {
            for (int i = board.getRows() - 1; i >= 0; i--) {
                if (board.getBoard()[i][col] == ' ') {
                    board.updateData(i, col, player.getMark());
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
        //addMarker(getBestMove());
//        getPossibleMoves();
        addMarker(getBestMove());
    }

//    private int getBestMove() {
//        int x, bestMove, position = 0;
//        ArrayList<Integer> count = new ArrayList<>();
//
//        // Checking horizontal
//        for (int i = 0; i < board.getRows(); i++) {
//            x = 0;
//            for (int j = 0; j < board.getCols(); j++) {
//                if (board.getBoard()[i][j] == 'X') {
//                    x++;
//                }
//            }
//            count.add(x);
//        }
//
//        // Checking vertical
//        for (int i = 0; i < board.getRows(); i++){
//            x = 0;
//            for (int j = 0; j < board.getRows(); j++){
//                if (board.getBoard()[j][i] == 'X'){
//                    x++;
//                }
//            }
//            count.add(x);
//        }
//
//        // Determining best move
//        bestMove = 0;
//        for (int i = 0; i < count.size(); i++){
//            if (count.get(i) > bestMove){
//                bestMove = count.get(i);
//                position = i;
//            }
//        }
//
//        if (position < board.getRows()){
//            bestMove = count.get(position);
//        }
////        // Checking forward slash
////        for (int i = 0; i < board.getRows(); i++){
////            for (int j = 0; j < board.getCols(); j++){
////                if (i + 2 < board.getRows() && j + 2 < board.getCols()) {
////                    if (board.getBoard()[i][j] == 'X' && board.getBoard()[i + 1][j + 1] == 'X' && board.getBoard()[i + 2][j + 2] == 'X'){
////                        return true;
////                    } else if (board.getBoard()[i][j] == 'O' && board.getBoard()[i + 1][j + 1] == 'O' && board.getBoard()[i + 2][j + 2] == 'O'){
////                        return true;
////                    }
////                }
////            }
////        }
////        // Checking backslash
////        for (int i = board.getRows() - 1; i >= 0; i--){
////            for (int j = board.getCols() - 1; j >= 0; j--){
////                if (i - 2 >= 0 && j + 2 < board.getCols()) {
////                    if (board.getBoard()[i][j] == 'X' && board.getBoard()[i - 1][j + 1] == 'X' && board.getBoard()[i - 2][j + 2] == 'X'){
////                        return true;
////                    } else if (board.getBoard()[i][j] == 'O' && board.getBoard()[i - 1][j + 1] == 'O' && board.getBoard()[i - 2][j + 2] == 'O'){
////                        return true;
////                    }
////                }
////            }
////        }
////        return false;
//        return bestMove;
//    }

    /**
     * Gets the best move for the AI to make.
     * @return the best move.
     */
    private int getBestMove(){
        int bestMove = 0;
        HashMap<Integer, Integer> possibleMoves = getPossibleMoves();
        ArrayList<HashMap> theMoves = new ArrayList<>();
        Iterator iterator = possibleMoves.entrySet().iterator();
        while ((iterator.hasNext())){
            HashMap.Entry kvp = (HashMap.Entry)iterator.next();
            int row = (int)kvp.getKey();
            int col = (int)kvp.getValue();
            int blocks = 0;

            // Check for blocks down left right left diagonal right diagonal
            // Down
            for (int i = row; i >= 0; i--){
                if (board.getBoard()[i][col] == 'X'){
                    blocks++;
                }
            }
            HashMap<Integer, Integer> hashMap = new HashMap<>();
            hashMap.put(col, blocks);
            theMoves.add(hashMap);
            blocks = 0;
            // Left
            for (int i = col; i >= 0; i--){
                if (board.getBoard()[i][col] == 'X'){
                    blocks++;
                }
            }
            hashMap = new HashMap<>();
            hashMap.put(col, blocks);
            theMoves.add(hashMap);
            //Right
            for (int i = 0; i < col; i++){
                if (board.getBoard()[i][col] == 'X'){
                    blocks++;
                }
            }
            hashMap = new HashMap<>();
            hashMap.put(col, blocks);
            theMoves.add(hashMap);
            // Left Diagonal
            // Right Diagonal
        }
        // Determine best move
        int tracker = 0;
        for (HashMap hashMap : theMoves){
            iterator = hashMap.entrySet().iterator();
            HashMap.Entry entry = (HashMap.Entry)iterator.next();
            if ((int)entry.getValue() > tracker){
                tracker = (int)entry.getValue();
                bestMove = (int)entry.getKey();
            }
        }
        return bestMove;
    }

    /**
     * Gets all the AI's possible moves.
     * @return the HashMap of moves.
     */
    private HashMap<Integer, Integer> getPossibleMoves(){
        ArrayList<Integer> columns = new ArrayList<>();
        HashMap<Integer, Integer> moveCoordinates = new HashMap<>();
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
                        columns.add(j);
                        moveCoordinates.put(i, j);
                        System.out.println(i + " " + j);
                    }
                }
            }
        }
        return moveCoordinates;
    }

    void switchPlayer(){
        player.switchPlayer();
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
                if (board.getBoard()[i][j] == 'X'){
                    x++;
                    o = 0;
                }else if(board.getBoard()[i][j] == 'O'){
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
                if (x >= board.getConnect_num() || o >= board.getConnect_num()){
                    return true;
                }
            }
        }

        // Checking vertical
        for (int i = 0; i < board.getRows(); i++){
            x = 0; o = 0;
            for (int j = 0; j < board.getRows(); j++){
                if (board.getBoard()[j][i] == 'X'){
                    x++;
                    o = 0;
                }else if(board.getBoard()[j][i] == 'O'){
                    o++;
                    x = 0;
                }else{
                    x = 0;
                    o = 0;
                }
                if (x >= board.getConnect_num() || o >= board.getConnect_num()){
                    return true;
                }
            }
        }

        // Checking forward slash
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++){
                if (i + board.getConnect_num() < board.getRows() && j + board.getConnect_num() < board.getCols()) {
                    x = 0;
                    o = 0;
//                      if (board.getBoard()[i][j] == 'X' && board.getBoard()[i + 1][j + 1] == 'X' && board.getBoard()[i + 2][j + 2] == 'X'){
//                        return true;
//                    } else if (board.getBoard()[i][j] == 'O' && board.getBoard()[i + 1][j + 1] == 'O' && board.getBoard()[i + 2][j + 2] == 'O'){
//                        return true;
//                    }
//                }
                    for (int k = 0; i < board.getConnect_num(); k++) {
                        if (board.getBoard()[i + k][j + k] == 'X') {
                            x++;
                            o = 0;
                        } else if (board.getBoard()[i][j + k] == 'O') {
                            o++;
                            x = 0;
                        } else {
                            x = 0;
                            o = 0;
                        }
                    }
                    if (x >= board.getConnect_num() || o >= board.getConnect_num()) {
                        return true;
                    }
                }
            }
        }
        // TODO Change this
        // Checking backslash
        for (int i = board.getRows() - 1; i >= 0; i--){
            for (int j = board.getCols() - 1; j >= 0; j--){
                if (i - 2 >= 0 && j + 2 < board.getCols()) {
                    if (board.getBoard()[i][j] == 'X' && board.getBoard()[i - 1][j + 1] == 'X' && board.getBoard()[i - 2][j + 2] == 'X'){
                        return true;
                    } else if (board.getBoard()[i][j] == 'O' && board.getBoard()[i - 1][j + 1] == 'O' && board.getBoard()[i - 2][j + 2] == 'O'){
                        return true;
                    }
                }
            }
        }
        return false;
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