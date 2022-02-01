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

    public boolean checkRowCol(Set<Cell> line) {
        return !allSixMatch(line) && (firstFiveMatch(line) || lastFiveMatch(line));
    }

    public boolean checkRow(){
        Set<Cell> row = board.getRow(lastMove);
        return checkRowCol(row);
    }

    public boolean checkCol(){
        Set<Cell> col = board.getCol(lastMove);
        return checkRowCol(col);
    }

    public boolean checkDiagAntiDiag(Set<Cell> diagonal) {
        if (diagonal != null) {
            if (diagonal.size()==6) {
                return !allSixMatch(diagonal) && (firstFiveMatch(diagonal) || lastFiveMatch(diagonal));
            } else {
                return firstFiveMatch(diagonal);
            }
        }
        return false;
    }

    public boolean checkDiag(){
        Set<Cell> diag = board.getDiag(lastMove);
        return checkDiagAntiDiag(diag);
    }

    public boolean checkAntiDiag() {
        Set<Cell> antiDiag = board.getAntiDiag(lastMove);
        return checkDiagAntiDiag(antiDiag);
    }
}
