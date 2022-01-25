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

    public Position AskPosition() throws NonValidPosException, NonIntegerException {
        System.out.println("Insert position x");
        Scanner myInput = new Scanner(System.in);
        String x_s = myInput.next();
        System.out.println("Insert position y");
        String y_s = myInput.next();

        try {
            int x = Integer.parseInt(x_s);
            int y = Integer.parseInt(y_s);
            if (x > 6 || y > 6 || x < 1 || y < 1) {
                throw new NonValidPosException("Not valid Position");
            }
            return new Position(x,y);
        } catch (NumberFormatException e) {
            throw new NonIntegerException(e);
        }
    }

    public static Integer tryParse(String text) {
        try {
            return Integer.parseInt(text);
        } catch (NumberFormatException e) {
            return null;
        }
    }

    public static class NonIntegerException extends Exception {
        public NonIntegerException(Exception ex) {
            super(ex);
        }
    }

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