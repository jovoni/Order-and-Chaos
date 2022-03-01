package orderandchaos;

import orderandchaos.Entities.*;
import orderandchaos.Utils.*;

public class Game {
    protected final Board board;
    protected final Win win;
    protected BlockChecker blockChecker;
    protected boolean chaosWon;
    protected boolean orderWon;
    protected final Display display;

    public Game() {
        this.board = new Board();
        this.display = new Display(this.board);
        this.blockChecker = new BlockChecker(this.board);
        this.win = new Win(this.board);
    }

    public void playGame() {
        this.init();
        this.play();
    }

    public void init() {
        this.display.printWelcome();
        this.display.insertStart();
        this.display.initPlayers();
    }

    public void play(){
        while (!this.chaosWon && !this.orderWon) {
            this.display.displayPlayer(this.display.currentPlayer);
            this.display.currentPlayer = this.display.changePlayer(this.display.currentPlayer,this.display.player1, this.display.player2);
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
        this.blockChecker.update(inputPosition);
        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = this.win.checkWin(lastMove);
        this.chaosWon = this.blockChecker.isEmpty();
    }

    public Board getBoard(){
        return this.board;
    }

    public BlockChecker getBoardChecker(){
        return this.blockChecker;
    }

    public boolean getOrderWon(){
        return this.orderWon;
    }

    public boolean getChaosWon(){
        return this.chaosWon;
    }
}
