package orderandchaos;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestCell {
    private final Position position = new Position(3, 4);
    private final Cell cell = new Cell(position);

    @Test
    void Instantiate() {
        assertFalse(cell.isOccupied());
    }

    @Test
    void testGetPosition() {
        assertEquals(position, cell.getPosition());
    }

    @Test
    void testPieceX() {
        Piece piece = Piece.X;
        Piece expected = Piece.X;
        cell.placePiece(piece);
        assertEquals(expected, cell.getSign());
    }

    @Test
    void testPieceO() {
        Piece piece = Piece.O;
        Piece expected = Piece.O;
        cell.placePiece(piece);
        assertEquals(expected, cell.getSign());
    }
}