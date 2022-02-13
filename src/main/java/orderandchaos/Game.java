package orderandchaos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    protected Board board;
//    protected final Player order;
//    protected final Player chaos;
    protected BlockChecker BlockChecker;
    protected boolean chaosWon;
    protected boolean orderWon;

    public Game() {
//        this.order = Player.Order;
//        this.chaos = Player.Chaos;
        this.board = new Board();
        this.BlockChecker = new BlockChecker(this.board);
    }

    public Position makeMove() {
        Display display = new Display(board);
        Position inputPosition = display.askPosition();
        Piece inputPiece =  display.askPiece();
        board.getCellAt(inputPosition).placePiece(inputPiece);
        this.BlockChecker.update(inputPosition);

        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = new Win(this.board, lastMove).checkWin();
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
