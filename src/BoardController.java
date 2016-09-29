/**
 * Controller class for connect 4
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
        for (int i = board.getRows() - 1; i >= 0; i--){
            if (board.getBoard()[i][col] == ' '){
                board.updateData(i, col, player.getMark());
                return true;
            }
        }
        return false;
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
                }else if(board.getBoard()[i][j] == 'O'){
                    o++;
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
                }else if(board.getBoard()[j][i] == 'O'){
                    o++;
                }
                if (x >= board.getConnect_num() || o >= board.getConnect_num()){
                    return true;
                }
            }
        }

        // Checking forward slash
        for (int i = 0; i < board.getRows(); i++){
            for (int j = 0; j < board.getCols(); j++){
                if (i + 2 < board.getRows() && j + 2 < board.getCols()) {
                    if (board.getBoard()[i][j] == 'X' && board.getBoard()[i + 1][j + 1] == 'X' && board.getBoard()[i + 2][j + 2] == 'X'){
                        return true;
                    } else if (board.getBoard()[i][j] == 'O' && board.getBoard()[i + 1][j + 1] == 'O' && board.getBoard()[i + 2][j + 2] == 'O'){
                        return true;
                    }
                }
            }
        }
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