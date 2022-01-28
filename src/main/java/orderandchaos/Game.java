package orderandchaos;

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

    public void MakeMove() throws Cell.PosAlreadyOccupiedException, NonValidPosException, NonValidPieceException {
        Position inputPosition = AskPosition();
        Piece inputPiece = AskPiece();
        System.out.println(new Win(this.board, inputPosition).checkWin());
        board.getCellAt(inputPosition).placePiece(inputPiece);
    }

    public static Position AskPosition() throws NonValidPosException{
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
        }catch (NonValidPosException | NumberFormatException e){
            return AskPosition();
        }
    }

    public static Piece AskPiece() throws NonValidPieceException {
        System.out.println("Insert piece");

        Scanner myInput = new Scanner( System.in );
        String piece = myInput.next();
        try {
            if (!(piece.equals("X") || piece.equals("O"))) {
                throw new NonValidPieceException("Giovanni Santacatterina");
            }
            return Piece.valueOf(piece);
        } catch(NonValidPieceException e){
            return AskPiece();
        }
    }

    public int canWin(int x){
        int y = 1;
        Piece currentPiece = board.getCellAt(new Position(x, y)).getPiece();
        Piece lastPiece = board.getCellAt(new Position(x, y + 5)).getPiece();
        if(currentPiece.equals(lastPiece)){
            return 2;
        }
        Boolean goOn = true;
        y +=1;
        int end = 5;
        Piece newPiece = board.getCellAt(new Position(x, y)).getPiece();
        if(!newPiece.equals(currentPiece) && !newPiece.equals(null)) {
            currentPiece = newPiece;
            end = 6;
        }
        int streak = 1;
        while (goOn && y <= end){
            y+=1;
            newPiece = board.getCellAt(new Position(x, y)).getPiece();
            if(!newPiece.equals(currentPiece) && !newPiece.equals(null)){
                goOn = false;
            }
            else if(!newPiece.equals(null)) streak +=1;

        }

        if(!goOn) return 2;

        if(streak == 5) return 1;
        else return 0;

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