package orderandchaos;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

public class Game {
    protected Board board;
//    protected final Player order;
//    protected final Player chaos;
    protected Map<String,Set<Position>> nonBlocked;
    protected boolean chaosWon;
    protected boolean orderWon;

    public Game() {
//        this.order = Player.Order;
//        this.chaos = Player.Chaos;
        this.board = new Board();
        this.nonBlocked = initNonBlocked();
    }

    public Position makeMove() {
        Display display = new Display(board);
        Position inputPosition = display.askPosition();
        Piece inputPiece =  display.askPiece();
        board.getCellAt(inputPosition).placePiece(inputPiece);
        this.nonBlocked = new Block(this.board, inputPosition, this.nonBlocked).updateNonBlocked();

//        this.nonBlocked.forEach((k,v) -> {
//            System.out.println(k);
//            v.stream().forEach(c->c.printPosition());
//        });
        return inputPosition;
    }

    public void checkBoard(Position lastMove) {
        this.orderWon = new Win(this.board, lastMove).checkWin();
        this.chaosWon = this.nonBlocked.isEmpty();
    }

    public  Map<String, Set<Position>> initNonBlocked(){
       Map<String, Set<Position>> nonBlocked = new HashMap<>();
       nonBlocked.put("row", board.getCol(new Position(1,1))
               .stream()
               .map(Cell::getPosition)
               .collect(Collectors.toSet()));
       nonBlocked.put("col", board.getRow(new Position(1,1))
               .stream()
               .map(Cell::getPosition)
               .collect(Collectors.toSet()));
       nonBlocked.put("diag", Set.of(board.getCellAt(new Position(1,1)).getPosition(),
               board.getCellAt(new Position(1,2)).getPosition(),
               board.getCellAt(new Position(2,1)).getPosition()));
        nonBlocked.put("antidiag", Set.of(board.getCellAt(new Position(1,6)).getPosition(),
                board.getCellAt(new Position(1,5)).getPosition(),
                board.getCellAt(new Position(2,6)).getPosition()));
       return nonBlocked;
    }

    public Board getBoard(){
        return this.board;
    }
}
