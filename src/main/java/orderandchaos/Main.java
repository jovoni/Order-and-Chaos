package orderandchaos;

import orderandchaos.Entities.Position;
import orderandchaos.Utils.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        System.out.println("Welcome to Order and Chaos!");

        boolean start = false;
        while (!start){
            System.out.println("If you want to know the rules write RULE, otherwise write PLAY to start playing the game.");
            Scanner input = new Scanner(System.in);
            String i = input.next();
            if (i.equals("RULE")) {
                System.out.print("RULES OF THE GAME \n \n \n");
            }
            else
                start = true;
        }

        Game game = new Game();
        Display display = new Display(game.board);

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
