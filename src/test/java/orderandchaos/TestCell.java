package orderandchaos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertFalse;

public class TestCell {
    @Test
    void Instatiate() {
        Position position = new Position(3,4);
        Cell cell = new Cell(position);
        assertFalse(cell.isOccupied());
    }

}
