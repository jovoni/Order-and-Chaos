package orderandchaos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;


public class TestBlock {
    private final Game game = new Game();

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void CheckBlockingRow(int row){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(row,3)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(row,3));
        game.board.getCellAt(new Position(row,4)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(row,4));


        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void CheckBlockingCol(int col){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(3,col)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(3,col));
        game.board.getCellAt(new Position(4,col)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(4,col));


        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }

    @Test
    void CheckBlockingDiag(){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(3,3)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(3,3));
        game.board.getCellAt(new Position(4,4)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(4,4));


        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }

    @ParameterizedTest
    @ValueSource(ints = {1, 2, 3, 4, 5, 6})
    void CheckBlockingLastAndFirstEquals(int col){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(1,col)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(1,col));
        game.board.getCellAt(new Position(6,col)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(6,col));

        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }

    @Test
    void CheckBlockingShortDiag(){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(1,2)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(1,2));
        game.board.getCellAt(new Position(5,6)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(5,6));


        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }


}
