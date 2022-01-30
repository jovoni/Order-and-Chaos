package orderandchaos;

public class Display {
    private final Board board;

    public Display(Board board){
        this.board = board;
    }

    public void PrintBoard(){
        System.out.println();
        for (int i = 1; i<=6; i++) {
            for (int j = 1; j <= 6; j++) {
                Cell cell = board.getCellAt(new Position(i,j));
                if(!cell.isOccupied()){
                    System.out.print("|\t");
                } else {
                    System.out.print("| " + cell.getPiece() + " ");
                }
                if (j == 6) {
                    System.out.print("|");
                }
            }
            System.out.println();
        }
    }
}
