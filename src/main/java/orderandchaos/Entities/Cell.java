package orderandchaos.Entities;


public class Cell implements Comparable<Cell>{
    private final Position position;
    private boolean occupied;
    private Piece piece;


    public Cell(Position cellPosition) {
        this.position = cellPosition;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return this.occupied;
    }

    public Piece getPiece() {
        if (isOccupied()) {
            return this.piece;
        } else {
            return Piece.Null;
        }
    }

    public Position getPosition() {
        return this.position;
    }

    public void placePiece(Piece piece) {
        this.piece = piece;
        this.occupied = true;
    }

    @Override
    public int compareTo(Cell o) {
        return this.position.compareTo(o.position);
    }
}