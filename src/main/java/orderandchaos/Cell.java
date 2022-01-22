package orderandchaos;

public class Cell {
    private final Position position;
    private boolean isOccupied;
    private Piece piece;

    public Cell(Position cellPosition) {
        this.position = cellPosition;
        this.isOccupied = false;
    }

    public boolean isOccupied() {
        return isOccupied;
    }

    public Position getPosition() {
        Position k = new Position(2,3);
        return k;
    }

}