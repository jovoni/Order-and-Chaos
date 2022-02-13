package orderandchaos;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;


public class TestBlock {
    private final Game game = new Game();

    @Test
    void CheckBlockingRow(){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(1,3)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(1,3));
        game.board.getCellAt(new Position(1,4)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(1,4));


        assertEquals(game.BlockChecker.size(), (BCSize -1));
    }

    @Test
    void CheckBlockingCol(){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(3,1)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(3,1));
        game.board.getCellAt(new Position(4,1)).placePiece(Piece.O);
        game.BlockChecker.update(new Position(4,1));


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

    @Test
    void CheckBlockingLastAndFirstEquals(){

        int BCSize = game.BlockChecker.size();
        game.board.getCellAt(new Position(1,1)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(1,1));
        game.board.getCellAt(new Position(6,1)).placePiece(Piece.X);
        game.BlockChecker.update(new Position(6,1));

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
