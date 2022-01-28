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
        return CheckRow() || CheckCol();
    }
    public boolean AllSixMatch(Set<Cell> set){
        return set.stream().allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean FirstFiveMatch(Set<Cell> set){
        return set.stream().limit(5).allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean LastFiveMatch(Set<Cell> set){
        return set.stream().skip(1).allMatch(c -> c.getPiece().equals(lastPiece));
    }

    public boolean CheckRow(){
        Set<Cell> Row = board.getRow(lastMove);

        return !AllSixMatch(Row) && (FirstFiveMatch(Row) || LastFiveMatch(Row));

    }

    public boolean CheckCol(){
        Set<Cell> Col = board.getCol(lastMove);

        return !AllSixMatch(Col) && (FirstFiveMatch(Col) || LastFiveMatch(Col));

    }

}
