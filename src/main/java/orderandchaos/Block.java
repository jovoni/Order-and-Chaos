package orderandchaos;

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
        Set<Cell> Row = board.getRow(lastMove);
        return (firstFiveBlocked(Row) && lastFiveBlocked(Row)) || firstAndLastEquals(Row);
    }

    public boolean checkCol() {
        Set<Cell> Col = board.getCol(lastMove);
        return (firstFiveBlocked(Col) && lastFiveBlocked(Col)) || firstAndLastEquals(Col);
    }
}
