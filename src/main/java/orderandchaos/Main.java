package orderandchaos;

import orderandchaos.Exceptions.NonIntegerException;
import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

public class Main {

    public static void main(String[] args) throws NonValidPosException, NonIntegerException, Cell.PosAlreadyOccupiedException, NonValidPieceException {

        Game game = new Game();
        Display display = new Display(game.board);
        Position position = game.AskPosition();
        Piece piece = game.AskPiece();
        game.MakeMove(position, piece);
        display.PrintBoard();
    }

    
}
