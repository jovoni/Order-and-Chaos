package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestDisplay {
    private final Board board = new Board();
    private final Display display = new Display(board);

    @ParameterizedTest
    @ValueSource(strings = {"X", "O"})
    void testInputPiece(String expected) throws NonValidPieceException {
        System.setIn(new ByteArrayInputStream(expected.getBytes()));
        Piece exp_piece = Piece.valueOf(expected);
        assertEquals(exp_piece, display.askPiece());
    }

    @ParameterizedTest
    @CsvSource({"1,1", "6,6", "6,1", "4,3"})
    public void testInputPosition(String x, String y) {
        String simulatedUserInput = x + "," + y;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));
        Position position = new Position(Integer.parseInt(x), Integer.parseInt(y));
        assertEquals(position, display.askPosition());
    }
}
