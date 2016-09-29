/*
 * Connect 4 Main Method
 */
/**
 * @author John D. Miller
 */
class Main {

    public static void main(String[] args) {

        // Instantiate MVC
        BoardModel boardModel = new BoardModel();
        BoardController boardController = new BoardController(boardModel);
        BoardView boardView = new BoardView(boardController);

        // Register view
        boardModel.addObserver(boardView);

        // Starting program
        boardView.start();
    }
}