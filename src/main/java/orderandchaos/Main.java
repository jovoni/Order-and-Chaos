package orderandchaos;

import orderandchaos.Entities.Player;
import orderandchaos.Entities.Position;

public class Main {

    public static void main(String[] args) {
        System.out.println("Welcome to \n");
        System.out.println("   ____           __                             __   ________                    \n" +
                "  / __ \\_________/ /__  _____   ____ _____  ____/ /  / ____/ /_  ____ _____  _____\n" +
                " / / / / ___/ __  / _ \\/ ___/  / __ `/ __ \\/ __  /  / /   / __ \\/ __ `/ __ \\/ ___/\n" +
                "/ /_/ / /  / /_/ /  __/ /     / /_/ / / / / /_/ /  / /___/ / / / /_/ / /_/ (__  ) \n" +
                "\\____/_/   \\__,_/\\___/_/      \\__,_/_/ /_/\\__,_/   \\____/_/ /_/\\__,_/\\____/____/  \n" +
                "                                                                                  ");

        Game game = new Game();

        while(!game.display.insertStart());

        Player p1 = game.display.insertPlayer1();
        Player p2 = game.display.insertPlayer2(p1.getPlayerRole());
        Player currentPlayer = game.display.findOrder(p1,p2);

        while (!game.chaosWon && !game.orderWon) {
            game.display.displayPlayer(currentPlayer);
            currentPlayer = game.display.changePlayer(currentPlayer,p1,p2);
            Position lastMove = game.makeMove();
            game.checkBoard(lastMove);
            game.display.printBoard();
        }

        if (game.orderWon) {
            System.out.println("Order you won!");
        } else {
            System.out.println("Chaos you won!");
        }
    }
}

