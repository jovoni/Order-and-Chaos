package orderandchaos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;
import java.io.InputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TestGame {
    private final Player order = Player.Order;
    private final Player chaos = Player.Chaos;
    private final Game game = new Game(order, chaos);

    @ParameterizedTest
    @ValueSource(strings={"X", "O"})
    void testInputPiece(String expected) {
        InputStream stdin = System.in;
        System.setIn(new ByteArrayInputStream(expected.getBytes()));
        Piece exp_piece = Piece.valueOf(expected);
        assertEquals(exp_piece, game.AskPiece());
    }
}
