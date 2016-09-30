import java.util.Observable;
import java.util.Observer;
import java.util.Scanner;

/**
 * View class for connect 4
 * @author John Miller
 */
class BoardView implements Observer {

    // Data Members
    private final BoardController boardController;

    // Constructor
    BoardView(BoardController boardController) {
        this.boardController = boardController;
    }

    /**
     * Prints the board when data is changed.
     * @param observable the Model object that notifies the BoardView
     * @param object the data passed from the Model when notification is sent
     */
    @Override
    public void update(Observable observable, Object object) {
        System.out.println((String)object);
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
        boardController.configureBoard(rows, cols, connect_num);

        // Starting game
        System.out.println("Game starting...");

        // Game loop
        while (!boardController.gameComplete() && !boardController.isFull()) {
            System.out.println(boardController.getPlayer() + "'s turn");
            if (boardController.getPlayer().equals(Player.PLAYER)) {
                // Prompting user
                System.out.println("Enter a column number to add your chip.");

                // Attempting to add marker
                while (!boardController.addMarker(input.nextInt())) {
                    System.out.println("This column is not open!!");
                    System.out.println("Enter a column number to add your chip.");
                }
            }else{
                boardController.computerMove();
            }

            // Game won, don't switch player
            if (!boardController.gameComplete()) {
                boardController.switchPlayer();
            }
        }

        // Game over
        if (boardController.isFull()){
            System.out.println("The game is a draw...");
        }else {
            System.out.println(boardController.getPlayer() + " wins!!!");
        }
    }
}
