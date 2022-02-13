package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;
import orderandchaos.Exceptions.PosAlreadyOccupiedException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NonValidPosException, PosAlreadyOccupiedException, NonValidPieceException {
        Game game = new Game();
        Display display = new Display(game.board);
        Boolean start = false;

        System.out.println("Welcome to Order and Chaos");
        while (!start){
            start = display.startInput();
        }

        while (!game.chaosWon && !game.orderWon) {
            Position lastMove = game.makeMove();
            game.checkBoard(lastMove);
            display.printBoard();
        }

        if (game.orderWon) {
            System.out.println("Order you won!");
        } else {
            System.out.println("Chaos you won, son of a bitch!");
        }
    }
}