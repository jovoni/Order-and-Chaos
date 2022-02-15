package orderandchaos;

import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import orderandchaos.Utils.Win;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {

    private final Game game = new Game();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void TestWinRow(int col){
        game.board.getCol(new Position(1,col)).stream().limit(5).forEach(cell -> cell.placePiece(Piece.X));

        assertTrue(game.WinChecker.checkWin(new Position(1,col)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void TestWinCol(int row){
        game.board.getRow(new Position(row,1)).stream().limit(5).forEach(cell -> cell.placePiece(Piece.X));

        assertTrue(game.WinChecker.checkWin(new Position(row,1)));
    }

    @Test
    void TestWinDiag(){
        game.board.getRow(new Position(1,1)).stream().limit(5).forEach(cell -> cell.placePiece(Piece.X));

        assertTrue(game.WinChecker.checkWin(new Position(1,1)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void TestNotWinRow(int col){
        game.board.getCol(new Position(1,col)).stream().limit(6).forEach(cell -> cell.placePiece(Piece.X));

        assertFalse(game.WinChecker.checkWin(new Position(1,col)));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void TestNotWinCol(int row){
        game.board.getRow(new Position(row,1)).stream().limit(6).forEach(cell -> cell.placePiece(Piece.X));

        assertFalse(game.WinChecker.checkWin(new Position(row,1)));
    }







}

