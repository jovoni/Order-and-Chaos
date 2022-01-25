package orderandchaos;

import orderandchaos.Exceptions.NonIntegerException;
import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

import java.util.Scanner;


public class Game {
    protected Board board ;
    protected final Player order ;
    protected final Player chaos ;

    public Game() {
        this.order = Player.Order;
        this.chaos = Player.Chaos;
        this.board = new Board();
    }

    public void MakeMove(Position position, Piece piece) throws Cell.PosAlreadyOccupiedException {
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

    public Piece AskPiece() throws NonValidPieceException {
        System.out.println("Insert piece");

        Scanner myInput = new Scanner( System.in );
        String piece = myInput.next();
        if(!(piece.equals("X") || piece.equals("O"))){
            throw new NonValidPieceException("Giovanni Santacatterina");
        }

        return Piece.valueOf(piece);
    }

    public boolean hasOrderWon(Position position, Piece piece) {
        int y = position.getX();
        int x = position.getY();

        // look up and down
        int in_a_row = 1;
        boolean added = true;
        while (y > 1 && added) {
            y = y - 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        System.out.println("up: " + in_a_row);

        y = position.getX();
        x = position.getY();

        added = true;
        while (y < 6 && added) {
            y = y + 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        System.out.println("up and down: " + in_a_row);

        if (in_a_row == 5) return true;

        y = position.getX();
        x = position.getY();

        // look left and rigth
        in_a_row = 1;
        added = true;
        while (x > 1 && added) {
            x = x - 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        y = position.getX();
        x = position.getY();

        added = true;
        while (x < 6 && added) {
            x = x + 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        System.out.println("left and right: " + in_a_row);
        if (in_a_row == 5) return true;

        y = position.getX();
        x = position.getY();

        // look north-east, south-west
        in_a_row = 1;
        added = true;
        while (x < 6 && y > 1 && added) {
            y = y - 1;
            x = x + 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        y = position.getX();
        x = position.getY();

        added = true;
        while (x > 1 && y < 6 && added) {
            y = y + 1;
            x = x - 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        System.out.println("sw and ne: " + in_a_row);
        if (in_a_row == 5) return true;

        y = position.getX();
        x = position.getY();

        // look north-west, south-east
        in_a_row = 1;
        added = true;
        while (x > 1 && y > 1 && added) {
            y = y - 1;
            x = x - 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }

        y = position.getX();
        x = position.getY();

        added = true;
        while (x < 1 && y < 6 && added) {
            y = y + 1;
            x = x + 1;
            Position upperPosition = new Position(x,y);
            Piece upperPiece = board.getCellAt(upperPosition).getPiece();
            if (upperPiece == piece) {
                in_a_row += 1;
            } else {
                added = false;
            }
        }
        System.out.println("se and nw: " + in_a_row);
        if (in_a_row == 5) return true;

        return false;
    }
}