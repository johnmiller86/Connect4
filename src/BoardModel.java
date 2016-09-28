import java.util.Observable;

/**
 *
 * @author chefj
 */
class BoardModel extends Observable{

    // Instance vars
    private int rows, cols, connect_num;
    private char[][] board;
    private Player player;

    BoardModel(){}

    // Constructor
    BoardModel(int rows, int cols, int connect_num){
        this.rows = rows;
        this.cols = cols;
        this.connect_num = connect_num;
        board = new char[rows][cols];
        initializeBoard();
    }

    // Initializing board as not so have \u0000 null values.
    private void initializeBoard(){
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
     * @return valid move, true or false;
     */
    private Boolean put(int col, char mark){

        col -= 1;
        for (int i = rows - 1; i >= 0; i--){
//            if (board[i][col] == '\u0000'){
            if (board[i][col] == ' '){
                board[i][col] = mark;
                return true;
            }
        }
        return false;
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
        // Column numbers
        //board += "-------------------------------------------------------------";
        board += divider;
        board += "\n" + colHeader + "\n";
        return board;
    }

    boolean gameComplete(){
        int x, o;

        // Checking horizontal
        for (int i = 0; i < rows; i++){
            x = 0; o = 0;
            for (int j = 0; j < cols; j++){
                if (board[i][j] == 'X'){
                    x++;
                }else if(board[i][j] == 'O'){
                    o++;
                }
                if (x >= connect_num || o >= connect_num){
                    return true;
                }
            }
        }

        // Checking vertical
        for (int i = 0; i < cols; i++){
            x = 0; o = 0;
            for (int j = 0; j < rows; j++){
                if (board[j][i] == 'X'){
                    x++;
                }else if(board[j][i] == 'O'){
                    o++;
                }
                if (x >= connect_num || o >= connect_num){
                    return true;
                }
            }
        }

        // Checking forward slash
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (i + 2 < rows && j + 2 < cols) {
                    if (board[i][j] == 'X' && board[i + 1][j + 1] == 'X' && board[i + 2][j + 2] == 'X'){
                        return true;
                    } else if (board[i][j] == 'O' && board[i + 1][j + 1] == 'O' && board[i + 2][j + 2] == 'O'){
                        return true;
                    }
                }
            }
        }
        // Checking backslash
        for (int i = rows - 1; i >= 0; i--){
            for (int j = cols - 1; j >= 0; j--){
                if (i - 2 >= 0 && j + 2 < cols) {
                    if (board[i][j] == 'X' && board[i - 1][j + 1] == 'X' && board[i - 2][j + 2] == 'X'){
                        return true;
                    } else if (board[i][j] == 'O' && board[i - 1][j + 1] == 'O' && board[i - 2][j + 2] == 'O'){
                        return true;
                    }
                }
            }
        }
        return false;
    }

//    // Tester method
//    public static void main(String[] args) {
//        BoardModel board = new BoardModel(5, 4);
//        System.out.println(board.toString());
//        board.put(3, 'X');
//        board.put(3, 'O');
//        board.put(3, 'X');
//        board.put(3, '0');
//        boolean valid = board.put(3, 'X');
//        System.out.println(board.toString());
//        System.out.println(valid);
//    }

    /**
     * Checks if the board is full.
     * @return true or false.
     */
    boolean isFull(){
        for (int i = 0; i < rows; i++){
            for (int j = 0; j < cols; j++){
                if (board[i][j] == ' '){
                    return false;
                }
            }
        }
        return true;
    }

    /**
     * Updates the board data.
     * @param col the new column.
     * @param mark the player's mark.
     */
    boolean updateData(int col, char mark) {
        return put(col, mark);
    }
}
