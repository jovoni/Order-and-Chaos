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
        if(checkRow()){
            this.nonBlocked.put("row", this.nonBlocked.get("row")
                    .stream()
                    .filter(c->!(c.equals(new Position(lastMove.getX(),1))))
                    .collect(Collectors.toSet()));
        }
        if (checkCol()){
            this.nonBlocked.put("col", this.nonBlocked
                    .get("col")
                    .stream()
                    .filter(c->!(c.equals(new Position(1,lastMove.getY()))))
                    .collect(Collectors.toSet()));
        }
        if(checkDiag()){
            Position pos = board.getDiag(lastMove)
                    .stream()
                    .findFirst()
                    .get()
                    .getPosition();
            this.nonBlocked.put("diag", this.nonBlocked
                    .get("diag")
                    .stream()
                    .filter(c->!(c.equals(pos)))
                    .collect(Collectors.toSet()));
        }
        if(checkAntiDiag()){
            Position pos = board.getAntiDiag(lastMove)
                    .stream()
                    .findFirst()
                    .get()
                    .getPosition();
            this.nonBlocked.put("antidiag", this.nonBlocked
                    .get("antidiag")
                    .stream()
                    .filter(c->!(c.equals(pos)))
                    .collect(Collectors.toSet()));
        }
        return this.nonBlocked;
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
