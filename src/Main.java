/*
 * Connect 4 Main Method
 */
/**
 * @author John D. Miller
 */
public class Main {

    public static void main(String[] args) {

        // Insatntiate MVC
        BoardModel board = new BoardModel();
        Player player = new Player();
        BoardController boardController = new BoardController(board);
        BoardView view = new BoardView(boardController);

        // Register view
        board.addObserver(view);

        // Starting program
        view.start();
    }
}