package orderandchaos;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;


public class TestCell {
    @Test
    void Instantiate() {
        Position position = new Position(3,4);
        Cell cell = new Cell(position);
        assertFalse(cell.isOccupied());
    }



public class TestPosition {
    @Test
    void getPosition() {
        Position position = new Position(3, 4);
        Cell cell = new Cell(position);

        Assertions.assertEquals(position, cell.getPosition());

    }

}