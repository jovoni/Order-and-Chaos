package orderandchaos;

import java.util.Scanner;


public class Game {
    protected Board board ;
    protected final Player order ;
    protected final Player chaos ;

    public Game(Player order, Player chaos) {
        this.order = Player.Order;
        this.chaos = Player.Chaos;
        this.board = new Board();
    }

    public void MakeMove(Position position, Piece piece){
        board.getCellAt(position).placePiece(piece);
    }

//    public Position AskPosition() throws NonValidPosException {
//        System.out.println("Insert position x");
//        Scanner myInput = new Scanner(System.in);
//        int x = myInput.nextInt();
//        System.out.println("Insert position y");
//        int y = myInput.nextInt();
//
//        if (x > 6 || y > 6 || x < 1 || y < 1) {
//            throw new NonValidPosException("Not valid Position");
//        }
//
//            return new Position(x,y);
//
//        }


    public static class NonValidPosException extends Exception {

        public NonValidPosException(String message) {
        super(message);
    }
}

    public Piece AskPiece(){
        Piece p = null;
        do {
            System.out.println("Insert piece");
            Scanner myInput = new Scanner( System.in );
            String piece = myInput.next();

            if (piece.equals("X")) {
                p = Piece.X;
            } else if (piece.equals("O")) {
                p = Piece.O;
            }
        }
        while(p == null);
        return p;
    }
}