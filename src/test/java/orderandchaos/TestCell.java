package orderandchaos;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.*;


public class TestCell {
    private final Position position = new Position(3, 4);
    private final Cell cell = new Cell(position);

    @Test
    void testInstantiate() {
        assertFalse(cell.isOccupied());
    }

    @Test
    void testGetPosition() {
        assertEquals(position, cell.getPosition());
    }

    @ParameterizedTest
    @EnumSource(value=Piece.class, names={"X", "O"})
    void testPiece(Piece piece) {
        cell.placePiece(piece);
        assertEquals(piece, cell.getPiece());
    }
}