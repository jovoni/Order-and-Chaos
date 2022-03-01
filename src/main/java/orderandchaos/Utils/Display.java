package orderandchaos.Utils;

import orderandchaos.Entities.Board;
import orderandchaos.Entities.Cell;
import orderandchaos.Entities.Piece;
import orderandchaos.Entities.Position;
import orderandchaos.Entities.Player;
import orderandchaos.Exceptions.NonValidPieceException;
import orderandchaos.Exceptions.NonValidPosException;
import orderandchaos.Exceptions.PosAlreadyOccupiedException;

import java.util.Scanner;
import java.util.StringTokenizer;

public class Display {
    private final Board board;
    private Player player1;
    private Player player2;
    public Player currentPlayer;

    public Display(Board board){
        this.board = board;
    }

    public void printWelcome() {
        System.out.println("Welcome to \n");
        System.out.println("   ____           __                             __   ________                    \n" +
                "  / __ \\_________/ /__  _____   ____ _____  ____/ /  / ____/ /_  ____ _____  _____\n" +
                " / / / / ___/ __  / _ \\/ ___/  / __ `/ __ \\/ __  /  / /   / __ \\/ __ `/ __ \\/ ___/\n" +
                "/ /_/ / /  / /_/ /  __/ /     / /_/ / / / / /_/ /  / /___/ / / / /_/ / /_/ (__  ) \n" +
                "\\____/_/   \\__,_/\\___/_/      \\__,_/_/ /_/\\__,_/   \\____/_/ /_/\\__,_/\\____/____/  \n" +
                "                                                                                  ");
    }

    public void initPlayers() {
        this.player1 = this.insertPlayer1();
        this.player2 = this.insertPlayer2(this.player1.getPlayerRole());
        this.currentPlayer = this.findOrder(this.player1, this.player2);
    }

    public void printBoard(){
        System.out.println();
        for (int i = 1; i<=6; i++) {
            for (int j = 1; j <= 6; j++) {
                Cell cell = board.getCellAt(new Position(i,j));
                if(!cell.isOccupied()) {
                    System.out.print("|\t");
                } else {
                    System.out.print("|   " + cell.getPiece() + "\t");
                }
                if (j == 6) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }

    public Position askPosition() {
        System.out.println("Insert position as x,y");
        Scanner myInput = new Scanner(System.in);
        String str = myInput.nextLine();
        StringTokenizer st = new StringTokenizer(str, " ,");
        if (st.countTokens() != 2) {
            return askPosition();
        }
        String x_s = st.nextToken();
        String y_s = st.nextToken();

        try {
            int x = Integer.parseInt(x_s);
            int y = Integer.parseInt(y_s);
            if (x > 6 || y > 6 || x < 1 || y < 1) {
                throw new NonValidPosException();
            }
            if (this.board.getCellAt(new Position(x,y)).isOccupied()) {
                throw new PosAlreadyOccupiedException();
            }
            return new Position(x, y);
        } catch (NonValidPosException | NumberFormatException | PosAlreadyOccupiedException e) {
            return askPosition();
        }
    }

    public Piece askPiece() {
        System.out.println("Insert piece");
        Scanner myInput = new Scanner(System.in);
        String piece = myInput.next();
        try {
            if (!(piece.equals("X") || piece.equals("O"))) {
                throw new NonValidPieceException();
            }
            return Piece.valueOf(piece);
        } catch (NonValidPieceException e) {
            return askPiece();
        }
    }

    public Boolean insertStart() {
        System.out.println("If you want to know the rules write RULE, otherwise write PLAY to start playing the game.");
        Scanner input = new Scanner(System.in);
        String i = input.next().toLowerCase();
        if (i.equals("rule")) {
            System.out.print("RULES OF THE GAME\n");
            System.out.print("Order and Chaos is a variant of the game tic-tac-toe on a 6×6 gameboard.\n" +
                    "The player Order strives to create a five-in-a-row of either Xs or Os either vertically, horizontally, or diagonally.\n" +
                    "The opponent Chaos endeavors to prevent this.\n" +
                    "Order plays first, then turns alternate.\n" +
                    "Six-in-a-row does not qualify as a win.\n\n");
            return insertStart();
        } else if (i.equals("play")) {
            return true;
        } else {
            return this.insertStart();
        }
    }

    private Player insertPlayer1() {
        System.out.println("Insert your name and your role (order or chaos)!");
        Scanner myInput = new Scanner(System.in);
        String str = myInput.nextLine();
        StringTokenizer st = new StringTokenizer(str);
        if (st.countTokens() != 2) {
            return insertPlayer1();
        }
        String name = st.nextToken();
        String role = st.nextToken().toLowerCase();
        while (!(role.equals("order") || role.equals("chaos"))) {
            role = askRole().toLowerCase();
        }
        return new Player(name,role);
    }

    private Player insertPlayer2(String other_role) {
        String role = other_role.equals("order") ? "chaos" : "order";
        System.out.printf("You will play as %s, insert your name!%n", role);
        Scanner myInput = new Scanner(System.in);
        String str = myInput.nextLine();
        StringTokenizer st = new StringTokenizer(str);
        String name = st.nextToken();
        return new Player(name, role);
    }

    private String askRole() {
        System.out.println("Insert a valid role (Order or Chaos)!");
        Scanner input = new Scanner(System.in);
        return input.next();
    }

    private Player findOrder(Player p1, Player p2) {
        if (p1.playerRole.equals("order")){
            return new Player(p1.playerName,p1.playerRole);
        } else {
            return new Player(p2.playerName,p2.playerRole);
        }
    }

    public void displayPlayer(Player currentPlayer){
        System.out.println(currentPlayer.playerName + "-" + currentPlayer.playerRole + " it's your turn!");
    }

    public void changePlayer() {
        if (this.currentPlayer.equals(this.player1)) {
            this.currentPlayer = this.player2;
        } else
            this.currentPlayer = this.player1;
    }
}
