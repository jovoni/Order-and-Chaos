package orderandchaos;

import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {

    private final Game game = new Game();

    @ParameterizedTest
    @CsvSource({"1,True", "1,False", "2,True", "2,False", "6,True", "6,False"})
    void testWinCol(int col, boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getCol(new Position(1,col))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.win.checkWin(new Position(1,col)), win);
    }

    @ParameterizedTest
    @CsvSource({"1,true", "1,false", "2,true", "2,false", "6,true", "6,false"})
    void testWinRow(int row, boolean win){
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getRow(new Position(row,1))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.win.checkWin(new Position(row,1)), win);
    }

    @ParameterizedTest
    @ValueSource(booleans={true, false})
    void testWinDiag(boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getDiag(new Position(1,1))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.win.checkWin(new Position(1,1)), win);
    }

    @ParameterizedTest
    @ValueSource(booleans={true, false})
    void testWinAntiDiag(boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getAntiDiag(new Position(1,6))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.win.checkWin(new Position(1,6)), win);
    }

}