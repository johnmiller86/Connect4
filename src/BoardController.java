/**
 * Controller class for connect 4
 * @author John Miller
 */
public class BoardController {

    // BoardModel model
    private BoardModel board;
    private Player player;

    /// Constructor
    public BoardController(BoardModel board) {
        this.board = board;
        player = new Player();
    }

    public String printBoard(){
        return board.toString();
    }

    public boolean complete(){
        return board.gameComplete();
    }

    public boolean addMarker(int cols, char mark) {
        return board.updateData(cols, mark);
    }

    public void switchPlayer(){
        player.switchPlayer();
    }

    public String getPlayer(){
        return player.getCurrentPlayer();
    }

    public char getMark(){
        return player.getMark();
    }
}