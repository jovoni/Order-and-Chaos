package orderandchaos.Utils;

import orderandchaos.Entities.*;

import java.util.Set;

public class Win{
    private final Board board;
    private Position lastMove;
    private Piece lastPiece;

    public Win(Board board){
        this.board = board;
    }

    public boolean checkWin(Position lastMove){
        this.lastMove = lastMove;
        this.lastPiece = board.getCellAt(lastMove).getPiece();
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
        return checkRowCol(board.getRow(lastMove));
    }

    public boolean checkCol(){
        return checkRowCol(board.getCol(lastMove));
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
        return checkDiagAntiDiag(board.getDiag(lastMove));
    }

    public boolean checkAntiDiag() {
        return checkDiagAntiDiag(board.getAntiDiag(lastMove));
    }
}
