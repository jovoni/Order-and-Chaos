package orderandchaos;

import orderandchaos.Entities.Board;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import orderandchaos.Utils.BlockChecker;
import orderandchaos.Utils.Display;
import orderandchaos.Utils.Win;

public class Game {
    protected Board board;
    protected Win WinChecker;
    protected orderandchaos.Utils.BlockChecker BlockChecker;
    protected boolean chaosWon;
    protected boolean orderWon;
    protected Display display;

    public Game() {
        this.board = new Board();
        this.display = new Display(board);
        this.BlockChecker = new BlockChecker(this.board);
        this.WinChecker = new Win(this.board);

    }


    public Position makeMove() {
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
