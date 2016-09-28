/**
 * Controller class for connect 4
 * @author John Miller
 */
class BoardController {

    // BoardModel model
    private BoardModel board;
    private Player player;

    /// Constructor
    BoardController(BoardModel board) {
        this.board = board;
        player = new Player();
    }

    String printBoard(){
        return board.toString();
    }

    boolean complete(){
        return board.gameComplete();
    }

    boolean isFull(){
        return board.isFull();
    }

    boolean addMarker(int cols, char mark) {
        return board.updateData(cols, mark);
    }

    void switchPlayer(){
        player.switchPlayer();
    }

    String getPlayer(){
        return player.getCurrentPlayer();
    }

    char getMark(){
        return player.getMark();
    }
}