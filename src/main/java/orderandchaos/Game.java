package orderandchaos;

import orderandchaos.Entities.*;
import orderandchaos.Utils.*;

public class Game {
    protected final Board board;
    protected final Win WinChecker;
    protected BlockChecker BlockChecker;
    protected boolean chaosWon;
    protected boolean orderWon;
    protected final Display display;

    public Game() {
        this.board = new Board();
        this.display = new Display(board);
        this.BlockChecker = new BlockChecker(this.board);
        this.WinChecker = new Win(this.board);
    }

    protected Position makeMove() {
        Position inputPosition = display.askPosition();
        Piece inputPiece =  display.askPiece();
        board.getCellAt(inputPosition).placePiece(inputPiece);
        this.BlockChecker.update(inputPosition);
        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = this.WinChecker.checkWin(lastMove);
        this.chaosWon = this.BlockChecker.isEmpty();
    }

    public Board getBoard(){
        return this.board;
    }

    public BlockChecker getBC(){
        return this.BlockChecker;
    }

    public boolean getOrderWon(){
        return this.orderWon;
    }

    public boolean getChaosWon(){
        return this.chaosWon;
    }
}
