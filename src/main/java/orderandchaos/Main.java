package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

import java.util.Set;

public class Main {

    public static void main(String[] args) throws NonValidPosException, Cell.PosAlreadyOccupiedException, NonValidPieceException {

        Game game = new Game();
        Display display = new Display(game.board);

        boolean orderHasWon = false;
        while (!orderHasWon && !game.board.isFull()) {
            game.MakeMove();
            display.PrintBoard();
            // game.hasOrderWon();
        }

        if (orderHasWon) {
            System.out.println("order won :)");
        } else {
            System.out.println("chaos won :)");
        }
    }
}
