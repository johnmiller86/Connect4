/**
 * Controller class for connect 4
 * @author John Miller
 */
public class Controller {

    // BoardModel model
    private BoardModel board;

    /// Constructor
    public Controller(BoardModel board) {
        this.board = board;
    }

    public String showBoard(){
        return board.toString();
    }

    public boolean complete(){
        return board.gameComplete();
    }

    public void valid(){
    }
    /**
     * BoardView will call this method when user input is available
     */
    public void addMarker(int cols, char mark) {
        board.updateData(cols, mark);
    }
}
