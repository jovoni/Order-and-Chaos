package orderandchaos;

public class Display {
    public void PrintBoard(Board board){
        System.out.println();
        for (int i = 6; i>0; i--){
            for(int j = 1; j <= 6; j++){
                Cell cell = board.getCellAt(new Position(i,j));
                if(cell.isOccupied()){
                    System.out.println("*");
                }
                else{
                    System.out.println(cell.getPiece());
                }
            }
            System.out.println();
        }
    }
}
