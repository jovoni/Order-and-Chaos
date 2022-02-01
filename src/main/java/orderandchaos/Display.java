package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;
import orderandchaos.Exceptions.PosAlreadyOccupiedException;

import java.util.Scanner;

public class Display {
    private final Board board;

    public Display(Board board){
        this.board = board;
    }

    public void printBoard(){
        System.out.println();
        for (int i = 1; i<=6; i++) {
            for (int j = 1; j <= 6; j++) {
                Cell cell = board.getCellAt(new Position(i,j));
                if(!cell.isOccupied()){
                    System.out.print("|\t");
                } else {
                    System.out.print("| " + cell.getPiece() + " ");
                }
                if (j == 6) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public Position askPosition() {
        System.out.println("Insert position as x,y");
        Scanner myInput = new Scanner(System.in);
        myInput.useDelimiter("\\D");
        String x_s = myInput.next();
        String y_s = myInput.next();

        try {
            int x = Integer.parseInt(x_s);
            int y = Integer.parseInt(y_s);
            if (x > 6 || y > 6 || x < 1 || y < 1) {
                throw new NonValidPosException("Not valid Position!");
            }
            if (this.board.getCellAt(new Position(x,y)).isOccupied()) {
                throw new PosAlreadyOccupiedException("Position already occupied!");
            }
            return new Position(x, y);
        } catch (NonValidPosException | NumberFormatException | PosAlreadyOccupiedException e) {
            return askPosition();
        }
    }

    public Piece askPiece() throws NonValidPieceException {
        System.out.println("Insert piece");

        Scanner myInput = new Scanner(System.in);
        String piece = myInput.next();
        try {
            if (!(piece.equals("X") || piece.equals("O"))) {
                throw new NonValidPieceException("Insert X or O!");
            }
            return Piece.valueOf(piece);
        } catch (NonValidPieceException e) {
            return askPiece();
        }
    }
}
