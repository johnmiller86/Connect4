import java.util.Observable;

/**
 *
 * @author chefj
 */
class BoardModel extends Observable{

    // Instance vars
    private int rows, cols, connect_num;
    private char[][] board;

    // No args constructor
    BoardModel(){}

    /**
     * Get's the boards row count.
     * @return the row count.
     */
    int getRows() {
        return rows;
    }

    void setRows(int rows){
        this.rows = rows;
    }

    /**
     * Get's the boards column count.
     * @return the column count.
     */
    int getCols() {
        return cols;
    }

    void setCols(int cols){
        this.cols = cols;
    }

    /**
     * Gets the connect number.
     * @return the connect number.
     */
    int getConnect_num() {
        return connect_num;
    }

    void setConnect_num(int connect_num){
        this.connect_num = connect_num;
    }

    /**
     * Gets the game board.
     * @return the game board.
     */
    char[][] getBoard() {
        return board;
    }


    // Initializing board as not so have \u0000 null values.
    void initializeBoard(){
        board = new char[rows][cols];
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                board[i][j] = ' ';
            }
        }
    }

    /**
     * Puts a new chip in the board.
     * @param col the chosen column.
     * @param mark the player's mark.
     */
    private void put(int row, int col, char mark){

        board[row][col] = mark;
    }

    /**
     * Returns string representation of the board.
     * @return the board string.
     */
    @Override
    public String toString(){
        String board = "", colHeader = "", divider = "";
        for (int i = 0; i < rows; i++){
            board += (i + 1) + "|";
            for (int j = 0; j < cols; j++){
                board += "\t" + this.board[i][j];
                if (i == 0) {
                    colHeader += "\t" + (j + 1);
                    divider += "-----";
                }
            }
            board += "\n";
        }
        board += divider;
        board += "\n" + colHeader + "\n";
        return board;
    }

    /**
     * Updates the board data.
     * @param col the new column.
     * @param mark the player's mark.
     */
    void updateData(int row, int col, char mark) {
        put(row, col, mark);
        setChanged();
        notifyObservers(toString());
    }
}
