package orderandchaos;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

public class TestBoard {

    @Test
    void checkDimension() {
        Board board = new Board();
        int expected = 36;
        assertEquals(board.size(), expected);
    }

}
