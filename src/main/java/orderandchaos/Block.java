package orderandchaos;

import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Block {
    protected Board board;
    protected Position lastMove;
    protected Map<String, Set<Position>> nonBlocked;

    public Block(Board board, Position lastMove, Map<String, Set<Position>> nonBlocked) {
        this.board = board;
        this.lastMove = lastMove;
        this.nonBlocked = nonBlocked;
    }

    public Map<String,Set<Position>> updateNonBlocked(){
        if(checkRow()) {
            Position lastRowPos = new Position(lastMove.getX(),1);
            this.nonBlocked.put("row", updateSet("row", lastRowPos));
        }
        if (checkCol()) {
            Position lastColPos = new Position(1, lastMove.getY());
            this.nonBlocked.put("col", updateSet("col", lastColPos));
        }
        if(checkDiag()) {
            Position pos = getFirst(board.getDiag(lastMove));
            this.nonBlocked.put("diag", updateSet("diag", pos));
        }
        if(checkAntiDiag()) {
            Position pos = getFirst(board.getAntiDiag(lastMove));
            this.nonBlocked.put("antidiag", updateSet("antidiag", pos));
        }
        return this.nonBlocked;
    }

    public Set<Position> updateSet(String key, Position pos) {
        return this.nonBlocked.get(key)
                .stream()
                .filter(c->!(c.equals(pos)))
                .collect(Collectors.toSet());
    }

    public Position getFirst(Set<Cell> diagonal) {
        return diagonal.stream()
                .findFirst()
                .get()
                .getPosition();
    }

    public boolean firstFiveBlocked(Set<Cell> set) {
        return checkFiveBlocked(set.stream().limit(5));
    }

    public boolean lastFiveBlocked(Set<Cell> set) {
        return checkFiveBlocked(set.stream().skip(1));
    }

    public boolean checkFiveBlocked(Stream<Cell> set){
        return set.filter(c -> c.getPiece().equals(Piece.X) || c.getPiece().equals(Piece.O))
                .map(Cell::getPiece)
                .distinct()
                .count() == 2;
    }

    public boolean firstAndLastEquals(Set<Cell> set) {
        Piece firstPiece = set.stream().findFirst().get().getPiece();
        Piece lastPiece = set.stream().skip(5).findFirst().get().getPiece();
        return firstPiece.equals(lastPiece) && !firstPiece.equals(Piece.Null);
    }

    public boolean checkRow() {
        return checkRowCol(board.getRow(lastMove));
    }

    public boolean checkCol() {
        return checkRowCol(board.getCol(lastMove));
    }

    public boolean checkRowCol(Set<Cell> line){
        return (firstFiveBlocked(line) && lastFiveBlocked(line)) || firstAndLastEquals(line);

    }

    public boolean checkDiag() {
        return checkDiagAntiDiag(board.getDiag(lastMove));
    }

    public boolean checkAntiDiag() {
        return checkDiagAntiDiag(board.getAntiDiag(lastMove));
    }

    public boolean checkDiagAntiDiag(Set<Cell> diagonal ){
        if (diagonal != null) {
            if (diagonal.size()==6) {
                return (firstFiveBlocked(diagonal) && lastFiveBlocked(diagonal)) || firstAndLastEquals(diagonal);
            } else {
                return firstFiveBlocked(diagonal);
            }
        }
        return false;
    }
}
