package orderandchaos.Exceptions;

public class NonValidPieceException extends Exception {
    public NonValidPieceException() {
        super("Insert X or O!");
    }
}
