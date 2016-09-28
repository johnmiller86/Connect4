/*
 * Connect 4 Main Method
 */
/**
 * @author John D. Miller
 */
public class Main {

    public static void main(String[] args) {

        // Instantiate MVC
        BoardModel board = new BoardModel();
        BoardController boardController = new BoardController(board);
        BoardView boardView = new BoardView(boardController);

        // Register view
        board.addObserver(boardView);

        // Starting program
        boardView.start();
    }
}