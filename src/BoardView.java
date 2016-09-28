import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * View class for connect 4
 * @author John Miller
 */
class BoardView implements Observer {

    // Data Members
    private BoardController boardController;

    // Constructor
    BoardView(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     * This method is called when the Model is changed.
     * @param o the Model object that notifies the BoardView
     * @param arg the data passed from the Model when notification is sent
     */
    @Override
    public void update(Observable o, Object arg) {
//        System.out.println((String)arg + " 's turn... ");
    }

    /**
     * This method is essentially to start the program.
     */
    void start() {
        Scanner input = new Scanner(System.in);
        int connect_num, rows, cols;

        // Get desired game
        System.out.print("Enter 3 for connect 3, 4 for connect 4 or 5 for connect 5: ");
        connect_num = input.nextInt();

        // Validating
        while (connect_num > 5 || connect_num < 3){
            System.out.println("You must choose between connect 3, 4 or 5!");
            System.out.print("Enter 3 for connect 3, 4 for connect 4 or 5 for connect 5: ");
            connect_num = input.nextInt();
        }

        // Get desired height
        System.out.print("Enter number of rows: ");
        rows = input.nextInt();

        // Validating
        while (rows < connect_num){
            System.out.println("You must have at least " + connect_num + " rows for connect " + connect_num + "!");
            System.out.print("Enter number of rows: ");
            rows = input.nextInt();
        }

        // Get desired width
        System.out.print("Enter number of columns: ");
        cols = input.nextInt();

        // Validating
        while (cols < connect_num){
            System.out.println("You must have at least " + connect_num + " columns for connect " + connect_num + "!");
            System.out.print("Enter number of columns: ");
            cols = input.nextInt();
        }

        // Configuring boardController
        boardController = new BoardController(new BoardModel(rows, cols, connect_num));

        // Starting game
        System.out.println("Game starting...");
        System.out.println(boardController.printBoard());

        // Game loop
        while (!boardController.complete() && !boardController.isFull()) {
            System.out.println(boardController.getPlayer() + "'s turn");
            System.out.println("Enter a column number to add your chip.");
            while (!boardController.addMarker(input.nextInt(), boardController.getMark())){
                System.out.println("This column is not open!!");
                System.out.println("Enter a column number to add your chip.");
            }
//            boardController.addMarker(input.nextInt(), boardController.getMark());
            System.out.println(boardController.printBoard());
            // Don't switch on last winning move
            if (!boardController.complete()) {
                boardController.switchPlayer();
            }
        }

        // Game over
        System.out.println(boardController.getPlayer() + " wins!!!");
        // Maybe tie....
    }
}
