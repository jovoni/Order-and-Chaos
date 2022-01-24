package orderandchaos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
    private final Board board = new Board();

    @Test
    void checkDimension() {
        int expected = 36;
        assertEquals(board.size(), expected);
    }

    @Test
    void checkPiece(){
        Position position = new Position(4, 4);
        board.getCellAt(position).placePiece(Piece.X);
        assertEquals(board.getCellAt(position).getPiece(), Piece.X);
    }

}
