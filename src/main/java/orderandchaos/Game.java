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
    protected String turn;


    public Game() {
//        this.order = Player.Order;
//        this.chaos = Player.Chaos;
        this.board = new Board();
        this.BlockChecker = new BlockChecker(this.board);
        this.turn = "Order";
    }

    public Position makeMove() {
        Display display = new Display(board);
        display.displayTurn(this.turn);
        Position inputPosition = display.askPosition();
        Piece inputPiece =  display.askPiece();
        board.getCellAt(inputPosition).placePiece(inputPiece);
        this.BlockChecker.update(inputPosition);
        this.turn = changeTurn(turn);
        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = new Win(this.board, lastMove).checkWin();
        this.chaosWon = this.BlockChecker.isEmpty();
    }

    public String changeTurn(String oldturn) {
        if (oldturn == "Chaos"){
            return "Order";
        }
        else
            return "Chaos";
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
