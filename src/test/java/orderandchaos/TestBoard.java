package orderandchaos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void checkFullBoard(boolean full) throws Cell.PosAlreadyOccupiedException {
        if (full) {
            for (int i = 1; i <= 6; i++) {
                for (int j = 1; j <= 6; j++) {
                    board.getCellAt(new Position(i, j)).placePiece(Piece.X);
                }
            }
        }
        assertEquals(full, board.isFull());
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void checkGetRow(int i) throws Cell.PosAlreadyOccupiedException {
        for (int j = 1; j <= 6; j++) {
            board.getCellAt(new Position(i, j)).placePiece(Piece.X);
        }
        Set<Cell> row = board.getRow(new Position(i,1));
        assertTrue(row.stream().allMatch(c->c.getPiece()==Piece.X));
    }

    @ParameterizedTest
    @ValueSource(ints = {1,2,3})
    void checkGetCol(int i) throws Cell.PosAlreadyOccupiedException {
        for (int j = 1; j <= 6; j++) {
            board.getCellAt(new Position(j, i)).placePiece(Piece.O);
        }
        Set<Cell> col = board.getCol(new Position(1,i));
        assertTrue(col.stream().allMatch(c->c.getPiece() == Piece.O));
    }

    @ParameterizedTest
    @CsvSource({"4,2,6","5,2,7","5,3,8"})
    void checkGetAntiDiag(int x, int y, int sum) throws Cell.PosAlreadyOccupiedException {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i+j == sum) {
                    board.getCellAt(new Position(i, j)).placePiece(Piece.X);
                }
            }
        }
        Set<Cell> antiDiag = board.getAntiDiag(new Position(x,y));
        assertTrue(antiDiag.stream().allMatch(c->c.getPiece() == Piece.X));
    }

    @ParameterizedTest
    @CsvSource({"2,2,0","2,1,1","1,2,-1"})
    void checkGetDiag(int x, int y, int diff) throws Cell.PosAlreadyOccupiedException {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i-j == diff) {
                    board.getCellAt(new Position(i, j)).placePiece(Piece.X);
                }
            }
        }
        Set<Cell> diag = board.getDiag(new Position(x,y));
        assertTrue(diag.stream().allMatch(c->c.getPiece() == Piece.X));
    }

}

