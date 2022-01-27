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
    void checkPiece() throws Cell.PosAlreadyOccupiedException {
        Position position = new Position(4, 4);
        board.getCellAt(position).placePiece(Piece.X);
        assertEquals(board.getCellAt(position).getPiece(), Piece.X);
    }

    @Test
    void checkFullBoard() throws Cell.PosAlreadyOccupiedException {
        for (int i = 1; i<=6; i++) {
            for (int j = 1; j <= 6; j++) {
                board.getCellAt(new Position(i, j)).placePiece(Piece.X);
            }
        }
        assertTrue(board.isFull());
    }
}
