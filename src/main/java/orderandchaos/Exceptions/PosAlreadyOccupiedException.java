package orderandchaos.Exceptions;

public class PosAlreadyOccupiedException extends Exception {
    public PosAlreadyOccupiedException() {
        super("Position already occupied!");
    }
}
