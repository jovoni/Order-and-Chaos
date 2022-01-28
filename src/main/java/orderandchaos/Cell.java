package orderandchaos;

public class Cell implements Comparable<Cell>{
    private final Position position;
    private boolean occupied;
    private Piece piece;

    public Cell(Position cellPosition) {
        this.position = cellPosition;
        this.occupied = false;
    }

    public boolean isOccupied() {
        return occupied;
    }

    public Piece getPiece() {
        if (isOccupied()) {
            return this.piece;
        } else {
            return null;
        }
    }

    public Position getPosition() {
        return position;
    }

    public void placePiece(Piece piece) throws PosAlreadyOccupiedException {
        if (this.occupied) {throw new PosAlreadyOccupiedException("");}
        this.piece = piece;
        this.occupied = true;
    }

    @Override
    public int compareTo(Cell o) {
        return this.position.compareTo(o.position);
    }


    public static class PosAlreadyOccupiedException extends Exception {
        public PosAlreadyOccupiedException(String message) {
            super(message);
        }
    }



}