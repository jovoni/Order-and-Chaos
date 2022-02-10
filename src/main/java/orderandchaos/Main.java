package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;
import orderandchaos.Exceptions.PosAlreadyOccupiedException;
public class Main {

    public static void main(String[] args) throws NonValidPosException, PosAlreadyOccupiedException, NonValidPieceException {

        Game game = new Game();
        Display display = new Display(game.board);

        while (!game.chaosWon && !game.orderWon) {
            Position lastMove = game.makeMove();
            game.checkBoard(lastMove);
            display.printBoard();
        }

        if (game.orderWon) {
            System.out.println("Order you won!");
        } else {
            System.out.println("Chaos you won, son of a bitch!");
        }

    }
}
