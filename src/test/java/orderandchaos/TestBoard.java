package orderandchaos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @Test
    void checkDimension() {
        Board board = new Board();
        int expected = 36;
        assertEquals(board.size(), expected);
    }

    @Test
    void checkPiece(){
        Board board = new Board();
        Position position = new Position(4, 4);
        board.getCellAt(position).placePiece(Piece.X);
        assertEquals(board.getCellAt(position).getPiece(), Piece.X);

    }

}
