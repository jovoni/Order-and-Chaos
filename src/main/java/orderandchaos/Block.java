package orderandchaos;

import java.util.Map;
import java.util.Optional;
import java.util.Set;
import java.util.stream.Collectors;

public class Block {
    protected Board board;
    protected Position lastMove;
    protected Piece lastPiece;
    protected Map<String, Set<Position>> nonBlocked;


    public Block(Board board, Position lastMove, Map<String, Set<Position>> nonBlocked) {
        this.board = board;
        this.lastMove = lastMove;
        this.lastPiece = board.getCellAt(lastMove).getPiece();
        this.nonBlocked = nonBlocked;
    }

    public Map<String, Set<Position>> updateNonBlocked(){
        if(checkRow()){
            this.nonBlocked.put("row", this.nonBlocked.get("row")
                    .stream()
                    .filter(c->!(c.equals(new Position(lastMove.getX(),1))))
                    .collect(Collectors.toSet()));
        }
        if (checkCol()){
            this.nonBlocked.put("col", this.nonBlocked.get("col")
                    .stream()
                    .filter(c->!(c.equals(new Position(1,lastMove.getY()))))
                    .collect(Collectors.toSet()));

        }

        if(checkDiag()){
            Position pos = board.getDiag(lastMove).stream().findFirst().get().getPosition();
            this.nonBlocked.put("diag", this.nonBlocked.get("diag")
                    .stream()
                    .filter(c->!(c.equals(pos)))
                    .collect(Collectors.toSet()));
        }
        if(checkAntiDiag()){
            Position pos = board.getAntiDiag(lastMove).stream().findFirst().get().getPosition();
            this.nonBlocked.put("antidiag", this.nonBlocked.get("antidiag")
                    .stream()
                    .filter(c->!(c.equals(pos)))
                    .collect(Collectors.toSet()));

        }
        return this.nonBlocked;
    }


    public boolean firstFiveBlocked(Set<Cell> set) {
                return set.stream().
                limit(5).
                filter(c -> c.getPiece().equals(Piece.X) || c.getPiece().equals(Piece.O)).
                map(c->c.getPiece()).
                distinct().
                limit(2).
                count() == 2;
    }

    public boolean lastFiveBlocked(Set<Cell> set) {
        return set.stream().
                skip(1).
                filter(c -> c.getPiece().name().equals("X") || c.getPiece().name().equals("O")).
                map(c->c.getPiece()).
                distinct().
                limit(2).
                count() == 2;
    }

    public boolean firstAndLastEquals(Set<Cell> set) {
        Optional<Cell> firstCell = set.stream().findFirst();
        Optional<Cell> lastCell = set.stream().skip(5).findFirst();

        if (!(firstCell.get().getPiece().equals(Piece.Null) && lastCell.get().getPiece().equals(Piece.Null))) {
            return firstCell.get().getPiece().equals((lastCell.get()).getPiece());
        } else {
            return false;
        }
    }


    public boolean checkRow() {
        Set<Cell> row = board.getRow(lastMove);
        return (firstFiveBlocked(row) && lastFiveBlocked(row)) || firstAndLastEquals(row);
    }

    public boolean checkCol() {
        Set<Cell> col = board.getCol(lastMove);
        return (firstFiveBlocked(col) && lastFiveBlocked(col)) || firstAndLastEquals(col);
    }

    public boolean checkDiag() {
        Set<Cell> diag = board.getDiag(lastMove);
        if (diag != null) {
            if(diag.size()==6){
                return (firstFiveBlocked(diag) && lastFiveBlocked(diag)) || firstAndLastEquals(diag);
            } else {
                return firstFiveBlocked(diag);
            }
        }
        return false;
    }

    public boolean checkAntiDiag() {
        Set<Cell> antiDiag = board.getAntiDiag(lastMove);
        if (antiDiag != null) {
            if (antiDiag.size()==6) {
                return (firstFiveBlocked(antiDiag) && lastFiveBlocked(antiDiag)) || firstAndLastEquals(antiDiag);
            } else {
                return firstFiveBlocked(antiDiag);
            }
        }
        return false;
    }

}
