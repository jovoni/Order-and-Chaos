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
    void TestWinCol(int col, boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getCol(new Position(1,col))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.WinChecker.checkWin(new Position(1,col)), win);
    }

    @ParameterizedTest
    @CsvSource({"1,true", "1,false", "2,true", "2,false", "6,true", "6,false"})
    void TestWinRow(int row, boolean win){
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getRow(new Position(row,1))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.WinChecker.checkWin(new Position(row,1)), win);
    }

    @ParameterizedTest
    @ValueSource(booleans={true, false})
    void TestWinDiag(boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getDiag(new Position(1,1))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.WinChecker.checkWin(new Position(1,1)), win);
    }

    @ParameterizedTest
    @ValueSource(booleans={true, false})
    void TestWinAntiDiag(boolean win) {
        long numberOfCells = 6;
        if (win) { numberOfCells = 5; }
        game.board.getAntiDiag(new Position(1,6))
                .stream()
                .limit(numberOfCells)
                .forEach(cell -> cell.placePiece(Piece.X));
        assertEquals(game.WinChecker.checkWin(new Position(1,6)), win);
    }

//    @ParameterizedTest
//    @ValueSource(ints={1, 2, 3, 4, 5, 6})
//    void TestNotWinCol(int col) {
//        game.board.getCol(new Position(1,col))
//                .stream()
//                .limit(6)
//                .forEach(cell -> cell.placePiece(Piece.X));
//        assertFalse(game.WinChecker.checkWin(new Position(1,col)));
//    }
//
//    @ParameterizedTest
//    @ValueSource(ints={1, 2, 3, 4, 5, 6})
//    void TestNotWinRow(int row) {
//        game.board.getRow(new Position(row,1))
//                .stream()
//                .limit(6)
//                .forEach(cell -> cell.placePiece(Piece.X));
//        assertFalse(game.WinChecker.checkWin(new Position(row,1)));
//    }
//
//    @Test
//    void TestNotWinDiag() {
//        game.board.getDiag(new Position(1,1))
//                .stream()
//                .limit(6)
//                .forEach(cell -> cell.placePiece(Piece.X));
//        assertFalse(game.WinChecker.checkWin(new Position(1,1)));
//    }
//
//    @Test
//    void TestNotWinAntiDiag() {
//        game.board.getAntiDiag(new Position(1,6))
//                .stream()
//                .limit(6)
//                .forEach(cell -> cell.placePiece(Piece.X));
//        assertFalse(game.WinChecker.checkWin(new Position(1,6)));
//    }

}