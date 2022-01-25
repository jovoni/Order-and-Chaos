package orderandchaos;

import orderandchaos.Exceptions.NonIntegerException;
import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

public class Main {

    public static void main(String[] args) throws NonValidPosException, NonIntegerException, Cell.PosAlreadyOccupiedException, NonValidPieceException {

        Game game = new Game();
        Display display = new Display(game.board);

        int n_turn = 0;
        boolean orderHasWon = false;
        while (!orderHasWon && n_turn <= 36) {
            Position position = game.AskPosition();
            Piece piece = game.AskPiece();
            game.MakeMove(position, piece);
            display.PrintBoard();
            orderHasWon = game.hasOrderWon(position, piece);
        }

        if (orderHasWon) {
            System.out.println("order won :)");
        } else {
            System.out.println("chaos won :)");
        }
    }
}
