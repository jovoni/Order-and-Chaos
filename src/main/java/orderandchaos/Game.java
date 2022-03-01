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
        this.display = new Display(this.board);
        this.BlockChecker = new BlockChecker(this.board);
        this.WinChecker = new Win(this.board);
    }

    public void playGame() {
        this.init();
        this.play();
    }

    public void init() {
        this.display.printWelcome();
        while (!this.display.insertStart());
        this.display.initPlayers();
    }

    public void play(){
        while (!this.chaosWon && !this.orderWon) {
            this.display.displayPlayer(this.display.currentPlayer);
            this.display.changePlayer();
            Position lastMove = this.makeMove();
            this.checkBoard(lastMove);
            this.display.printBoard();
        }

        if(this.orderWon) {
            System.out.println("Order you won!");
        } else {
            System.out.println("Chaos you won!");
        }
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

    public BlockChecker getBoardChecker(){
        return this.BlockChecker;
    }

    public boolean getOrderWon(){
        return this.orderWon;
    }

    public boolean getChaosWon(){
        return this.chaosWon;
    }
}
