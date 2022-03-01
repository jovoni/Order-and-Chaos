package orderandchaos;

import orderandchaos.Entities.Board;
import orderandchaos.Entities.Cell;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.EnumSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {
    private final Board board = new Board();

    @Test
    void testCheckDimension() {
        int expected = 36;
        assertEquals(board.size(), expected);
    }

    @ParameterizedTest
    @EnumSource(value=Piece.class, names={"X", "O"})
    void testCheckPiece(Piece piece) {
        Position position = new Position(4, 4);
        board.getCellAt(position).placePiece(piece);
        assertEquals(board.getCellAt(position).getPiece(), piece);
    }

    @ParameterizedTest
    @ValueSource(booleans = {true, false})
    void testCheckFullBoard(boolean full) {
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
    @CsvSource({"1,X", "1,O", "2,X", "2,O", "6,X", "6,O"})
    void testCheckGetRow(int i, Piece piece) {
        for (int j = 1; j <= 6; j++) {
            board.getCellAt(new Position(i, j)).placePiece(piece);
        }
        Set<Cell> row = board.getRow(new Position(i,1));
        assertTrue(row.stream().allMatch(c -> c.getPiece() == piece));
    }

    @ParameterizedTest
    @CsvSource({"1,X", "1,O", "2,X", "2,O", "6,X", "6,O"})
    void testCheckGetCol(int i, Piece piece) {
        for (int j = 1; j <= 6; j++) {
            board.getCellAt(new Position(j, i)).placePiece(piece);
        }
        Set<Cell> col = board.getCol(new Position(1,i));
        assertTrue(col.stream().allMatch(c->c.getPiece() == piece));
    }

    @ParameterizedTest
    @CsvSource({"2,2,0,X","2,2,0,O","2,1,1,X","2,1,1,O", "1,2,-1,X","1,2,-1,O"})
    void testCheckGetDiag(int x, int y, int diff, Piece piece) {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i-j == diff) {
                    board.getCellAt(new Position(i, j)).placePiece(piece);
                }
            }
        }
        Set<Cell> diag = board.getDiag(new Position(x,y));
        if (diff < -1 || diff > 1) {
            assertNull(diag);
        } else {
            assertTrue(diag.stream().allMatch(c->c.getPiece() == piece));
        }
    }

    @ParameterizedTest
    @CsvSource({"4,2,6,X", "4,2,6,O", "5,2,7,X", "5,2,7,O"})
    void testCheckGetAntiDiag(int x, int y, int sum, Piece piece) {
        for (int i = 1; i <= 6; i++) {
            for (int j = 1; j <= 6; j++) {
                if (i+j == sum) {
                    board.getCellAt(new Position(i, j)).placePiece(piece);
                }
            }
        }
        Set<Cell> antiDiag = board.getAntiDiag(new Position(x,y));
        if (sum < 6 || sum > 8) {
            assertNull(antiDiag);
        } else {
            assertTrue(antiDiag.stream().allMatch(c->c.getPiece() == piece));
        }
    }

}

