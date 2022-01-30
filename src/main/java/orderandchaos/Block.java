package orderandchaos;

import java.util.Map;
import java.util.Optional;
import java.util.Set;

public class Block {
    protected Board board;
    protected Position lastMove;
    protected Piece lastPiece;

    public Block(Board board, Position lastMove) {
        this.board = board;
        this.lastMove = lastMove;
        this.lastPiece = board.getCellAt(lastMove).getPiece();
    }

    public Map<String, Set<Position>> updateNonBlocked(Map<String, Set<Position>> nonBlocked){
        if(checkRow()){
            nonBlocked.get("row").remove(new Position(lastMove.getX(),1));
        }
        if (checkCol()){
            nonBlocked.get("col").remove(new Position(1,lastMove.getY()));
        }

        if(checkDiag()){
            nonBlocked.get("diag").remove(board.getDiag(lastMove).stream().findFirst());
        }
        if(checkAntiDiag()){
            nonBlocked.get("antidiag").remove(board.getAntiDiag(lastMove).stream().findFirst());

        }
        return nonBlocked;
    }


    public boolean firstFiveBlocked(Set<Cell> set) {
        return set.stream().
                limit(5).
                filter(c -> c.getPiece().name().equals("X") || c.getPiece().name().equals("O")).
                distinct().
                limit(2).
                count() == 2;
    }

    public boolean lastFiveBlocked(Set<Cell> set) {
        return set.stream().
                skip(1).
                filter(c -> c.getPiece().name().equals("X") || c.getPiece().name().equals("O")).
                distinct().
                limit(2).
                count() == 2;
    }

    public boolean firstAndLastEquals(Set<Cell> set) {
        Optional<Cell> firstCell = set.stream().findFirst();
        Optional<Cell> lastCell = set.stream().skip(5).findFirst();
        return firstCell.get().getPiece().equals((lastCell.get()).getPiece());
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
        Set<Cell> antiDiag = board.getDiag(lastMove);
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
