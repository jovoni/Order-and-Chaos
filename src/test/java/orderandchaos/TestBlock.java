package orderandchaos;

import orderandchaos.Entities.Cell;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.TreeSet;

import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestBlock {
    private final Game game = new Game();

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 5, 6})
    void testCheckBlockingRow(int row){
        Position pos1 = new Position(row,3);
        Position pos2 = new Position(row,4);
        game.board.getCellAt(pos1).placePiece(Piece.X);
        game.blockChecker.update(pos1);
        game.board.getCellAt(pos2).placePiece(Piece.O);
        game.blockChecker.update(pos2);
        assertFalse(game.blockChecker.contains((TreeSet<Cell>) game.board.getRow(pos2)));
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 5, 6})
    void testCheckBlockingCol(int col){
        Position pos1 = new Position(3,col);
        Position pos2 = new Position(4,col);
        game.board.getCellAt(pos1).placePiece(Piece.X);
        game.blockChecker.update(pos1);
        game.board.getCellAt(pos2).placePiece(Piece.O);
        game.blockChecker.update(pos2);
        assertFalse(game.blockChecker.contains((TreeSet<Cell>) game.board.getCol(pos2)));
    }

    @ParameterizedTest
    @CsvSource({"3,3,4,4", "1,2,5,6"})
    void testCheckBlockingDiag(int row1, int col1, int row2, int col2){
        Position pos1 = new Position(row1,col1);
        Position pos2 = new Position(row2,col2);
        game.board.getCellAt(pos1).placePiece(Piece.X);
        game.blockChecker.update(pos1);
        game.board.getCellAt(pos2).placePiece(Piece.O);
        game.blockChecker.update(pos2);
        assertFalse(game.blockChecker.contains((TreeSet<Cell>) game.board.getDiag(pos2)));
    }

    @ParameterizedTest
    @ValueSource(ints={1, 2, 3, 4, 5, 6})
    void testCheckBlockingLastAndFirstEquals(int col){
        Position pos1 = new Position(1,col);
        Position pos2 = new Position(6,col);
        game.board.getCellAt(pos1).placePiece(Piece.X);
        game.blockChecker.update(pos1);
        game.board.getCellAt(pos2).placePiece(Piece.X);
        game.blockChecker.update(pos2);
        assertFalse(game.blockChecker.contains((TreeSet<Cell>) game.board.getCol(pos2)));
    }

}
