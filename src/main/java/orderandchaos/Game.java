package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Game {
    protected Board board;
    protected final Player order;
    protected final Player chaos;
    protected Map<String, List<Position>> nonBlocked;

    public Game() {
        this.order = Player.Order;
        this.chaos = Player.Chaos;
        this.board = new Board();
        this.nonBlocked = initNonBlocked();
    }

    public void MakeMove() throws Cell.PosAlreadyOccupiedException, NonValidPosException, NonValidPieceException {
        Position inputPosition = AskPosition();
        Piece inputPiece = AskPiece();
        System.out.println(new Win(this.board, inputPosition).checkWin());
        board.getCellAt(inputPosition).placePiece(inputPiece);
    }

    public static Position AskPosition() throws NonValidPosException {
        System.out.println("Insert position x");
        Scanner myInput = new Scanner(System.in);
        String x_s = myInput.next();
        System.out.println("Insert position y");
        String y_s = myInput.next();
        try {
            int x = Integer.parseInt(x_s);
            int y = Integer.parseInt(y_s);
            if (x > 6 || y > 6 || x < 1 || y < 1) {
                throw new NonValidPosException("Not valid Position!");
            }
            return new Position(x, y);
        } catch (NonValidPosException | NumberFormatException e) {
            return AskPosition();
        }
    }

    public static Piece AskPiece() throws NonValidPieceException {
        System.out.println("Insert piece");

        Scanner myInput = new Scanner(System.in);
        String piece = myInput.next();
        try {
            if (!(piece.equals("X") || piece.equals("O"))) {
                throw new NonValidPieceException("Insert X or O!");
            }
            return Piece.valueOf(piece);
        } catch (NonValidPieceException e) {
            return AskPiece();
        }
    }


   public  Map<String, List<Position>> initNonBlocked(){

       Map<String, List<Position>> nonBlocked = new HashMap<>();

       nonBlocked.put("row", board.getCol(new Position(1,1)).stream().map(Cell::getPosition).collect(Collectors.toList()));
       nonBlocked.put("col", board.getRow(new Position(1,1)).stream().map(Cell::getPosition).collect(Collectors.toList()));
       nonBlocked.put("diag", List.of(new Position(1,1), new Position(1,2), new Position(2,1)));
       nonBlocked.put("antidiag", List.of(new Position(1,6), new Position(1,5), new Position(2,6)));
       return nonBlocked;
    }
}