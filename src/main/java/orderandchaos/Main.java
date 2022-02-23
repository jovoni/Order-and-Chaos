package orderandchaos;

import orderandchaos.Entities.Player;
import orderandchaos.Entities.Position;
import orderandchaos.Utils.Display;

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
        Display display = new Display(game.board);

        while(!display.insertStart());

        Player p1 = display.insertPlayer1();
        Player p2 = display.insertPlayer2(p1.getPlayerRole());
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

