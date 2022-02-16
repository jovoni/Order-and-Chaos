package orderandchaos;

import orderandchaos.Entities.Player;
import orderandchaos.Entities.Position;
import orderandchaos.Utils.Display;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to Order and Chaos!");

        Game game = new Game();
        Display display = new Display(game.board);

        while(!display.insertStart());

        Player p1 = display.insertPlayer();
        Player p2 = display.insertPlayer();
        Player currentPlayer = display.findOrder(p1,p2);

        while (!game.chaosWon && !game.orderWon) {
            display.displayPlayer(currentPlayer);
            currentPlayer = display.changePlayer(currentPlayer,p1,p2);
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

