package orderandchaos;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.io.ByteArrayInputStream;

import static org.junit.jupiter.api.Assertions.assertEquals;

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

//    @ParameterizedTest
//    @CsvSource({"5,2"})
//    public void testPositionThrowsException(String x , String y) {
//        System.setIn(new ByteArrayInputStream(x.getBytes()));
//        System.setIn(new ByteArrayInputStream(y.getBytes()));
//        boolean thrown = false;
//        try {
//            game.AskPosition();
//        } catch (Game.NonValidPosException e ) {
//            thrown = true;
//        }
//
//        assertFalse(thrown);
//    }




}

