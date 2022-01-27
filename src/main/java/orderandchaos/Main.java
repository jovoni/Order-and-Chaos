package orderandchaos;

import orderandchaos.Exceptions.NonIntegerException;
import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

public class Main {

    public static void main(String[] args) throws NonValidPosException, NonIntegerException, Cell.PosAlreadyOccupiedException, NonValidPieceException {

        Game game = new Game();
        Display display = new Display(game.board);


        boolean orderHasWon = false;
        while (!orderHasWon && !game.board.isFull()) {
            game.MakeMove();
            display.PrintBoard();
            orderHasWon = false; //game.hasOrderWon(inputPosition, inputPiece);
        }

        if (orderHasWon) {
            System.out.println("order won :)");
        } else {
            System.out.println("chaos won :)");
        }
    }
}
