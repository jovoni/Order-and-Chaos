package orderandchaos;

import orderandchaos.Entities.Board;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import orderandchaos.Utils.BlockChecker;
import orderandchaos.Utils.Display;
import orderandchaos.Utils.Win;

public class Game {
    protected Board board;

    //protected final Player order;
    //protected final Player chaos;

    protected orderandchaos.Utils.BlockChecker BlockChecker;
    protected boolean chaosWon;
    protected boolean orderWon;
    //protected Player currentPlayer;
    protected Display display;

    public Game() {
        this.board = new Board();
        this.display = new Display(board);
        this.BlockChecker = new BlockChecker(this.board);

        //Player p1 = createPlayer();
        //Player p2 = createPlayer();


        //if (p1.playerRole.equals("Order")){
        //    this.order = p1;
        //    this.chaos = p2;
        //}
        //else{
        //    this.chaos = p1;
        //    this.order = p2;}
        //this.currentPlayer = order;
    }

    //public Player createPlayer(){
    //    return display.insertPlayer();
    //}

    public Position makeMove() {
        //display.displayTurn(this.currentPlayer);
        Position inputPosition = display.askPosition();
        Piece inputPiece =  display.askPiece();
        board.getCellAt(inputPosition).placePiece(inputPiece);
        this.BlockChecker.update(inputPosition);
        //this.currentPlayer = changeTurn(currentPlayer);
        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = new Win(this.board, lastMove).checkWin();
        this.chaosWon = this.BlockChecker.isEmpty();
    }

    //public Player changeTurn(Player oldPlayer) {
    //    if (oldPlayer.playerRole.equals("Chaos")){
    //        return order;
    //    }
    //    else
    //        return chaos;
    //}

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
