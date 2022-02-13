package orderandchaos;

import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;
import orderandchaos.Exceptions.PosAlreadyOccupiedException;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) throws NonValidPosException, PosAlreadyOccupiedException, NonValidPieceException {

        System.out.println("Welcome to Order and Chaos!");

        Boolean start = false;
        while (!start){
            System.out.println("If you want to know the rules write RULE, otherwise write PLAY to start playing the game.");
            Scanner input = new Scanner(System.in);
            String i = input.next();
            if (i.equals("RULE")) {
                System.out.print("RULES OF THE GAME \n \n \n");
            }
            else
                start=true;
        }

        Game game = new Game();
        Display display = new Display(game.board);

        while (!game.chaosWon && !game.orderWon) {
            Position lastMove = game.makeMove();
            game.checkBoard(lastMove);
            display.printBoard();
        }

        if (game.orderWon) {
            System.out.println( game.order.playerName+"-Order you won!");
        } else {
            System.out.println(game.order.playerName+"-Chaos you won, son of a bitch!");
        }
    }
}
