package orderandchaos;

import orderandchaos.Exceptions.NonIntegerException;
import orderandchaos.Exceptions.NonValidPosException;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.*;

public class TestGame {
    private final Player order = Player.Order;
    private final Player chaos = Player.Chaos;
    private final Game game = new Game(order, chaos);

    @ParameterizedTest
    @ValueSource(strings = {"X", "O"})
    void testInputPiece(String expected) {
        System.setIn(new ByteArrayInputStream(expected.getBytes()));
        Piece exp_piece = Piece.valueOf(expected);
        assertEquals(exp_piece, game.AskPiece());
    }

    @ParameterizedTest
    @CsvSource({"5,5,false", "10,7,true", "ciao,2,true"})
    public void testPositionThrowsException(String x, String y, Boolean expected) throws NonValidPosException {
        String simulatedUserInput = x + System.getProperty("line.separator") + y;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        boolean thrown = false;
        try {
            game.AskPosition();
        } catch (NonValidPosException | NonIntegerException e ) {
            thrown = true;
        }
        assertEquals(thrown, expected);
    }

    @ParameterizedTest
    @CsvSource({"1,1", "6,6", "6,1", "4,3"})
    public void testInputPosition(String x, String y) throws NonValidPosException, NonIntegerException {
        String simulatedUserInput = x + System.getProperty("line.separator") + y;
        System.setIn(new ByteArrayInputStream(simulatedUserInput.getBytes()));

        Position position = new Position(Integer.parseInt(x), Integer.parseInt(y));
        assertEquals(position, game.AskPosition());
    }
}

