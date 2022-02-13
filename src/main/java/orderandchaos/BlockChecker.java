package orderandchaos;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class BlockChecker extends HashSet<TreeSet<Cell>>{

    public BlockChecker(Board board){
        super();
        for (Cell c : board){
            if(board.getAntiDiag(c.getPosition()) != null) this.add((TreeSet<Cell>) board.getAntiDiag(c.getPosition()));
            if(board.getDiag(c.getPosition()) != null) this.add((TreeSet<Cell>) board.getDiag(c.getPosition()));
            this.add((TreeSet<Cell>) board.getCol(c.getPosition()));
            this.add((TreeSet<Cell>) board.getRow(c.getPosition()));
        }

    }

    public void update(Position position){

        HashSet<TreeSet<Cell>> SetToRemove = this.stream().filter(s -> s.contains(new Cell(position))).
                filter(s -> (firstFiveBlocked(s) && lastFiveBlocked(s) || firstAndLastEquals(s))).
                collect(Collectors.toCollection(HashSet::new));
        //System.out.println(SetToRemove.size());
        SetToRemove.forEach(this::remove);
        //System.out.println(this.size());
    }




    public boolean firstFiveBlocked(Set<Cell> set) {
        return checkFiveBlocked(set.stream().limit(5));
    }

    public boolean lastFiveBlocked(Set<Cell> set) {
        if(set.size() == 5) return true;
        return checkFiveBlocked(set.stream().skip(1));
    }

    public boolean checkFiveBlocked(Stream<Cell> set){
        return set.filter(c -> c.getPiece().equals(Piece.X) || c.getPiece().equals(Piece.O))
                .map(Cell::getPiece)
                .distinct()
                .count() == 2;
    }

    public boolean firstAndLastEquals(Set<Cell> set) {
        if(set.size() == 5) return false;
        Piece firstPiece = set.stream().findFirst().get().getPiece();
        Piece lastPiece = set.stream().skip(5).findFirst().get().getPiece();
        return firstPiece.equals(lastPiece) && !firstPiece.equals(Piece.Null);
    }
}


