package orderandchaos;

import java.util.Set;

public class Win{
    protected Board board;
    protected Position lastMove;
    protected Piece lastPiece;

    public Win(Board board, Position lastMove){
        this.board = board;
        this.lastMove = lastMove;
        this.lastPiece = board.getCellAt(lastMove).getPiece();
    }

    public boolean checkWin(){
        return checkRow() || checkCol() || checkDiag() || checkAntiDiag();
    }

    public boolean allSixMatch(Set<Cell> set){
        return set.stream()
                .allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean firstFiveMatch(Set<Cell> set){
        return set.stream()
                .limit(5)
                .allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean lastFiveMatch(Set<Cell> set){
        return set.stream()
                .skip(1)
                .allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean checkRow(){
        Set<Cell> row = board.getRow(lastMove);
        return !allSixMatch(row) && (firstFiveMatch(row) || lastFiveMatch(row));
    }

    public boolean checkCol(){
        Set<Cell> col = board.getCol(lastMove);
        return !allSixMatch(col) && (firstFiveMatch(col) || lastFiveMatch(col));
    }

    public boolean checkDiag(){
        Set<Cell> diag = board.getDiag(lastMove);
        if (diag != null) {
            if (diag.size()==6) {
                return !allSixMatch(diag) && (firstFiveMatch(diag) || lastFiveMatch(diag));
            } else {
                return firstFiveMatch(diag);
            }
        }
        return false;
    }

    public boolean checkAntiDiag(){
        Set<Cell> antiDiag = board.getDiag(lastMove);
        if (antiDiag != null) {
            if (antiDiag.size()==6) {
                return !allSixMatch(antiDiag) && (firstFiveMatch(antiDiag) || lastFiveMatch(antiDiag));
            } else {
                return firstFiveMatch(antiDiag);
            }
        }
        return false;
    }

}
