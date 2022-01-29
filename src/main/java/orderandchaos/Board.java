package orderandchaos;

import java.util.HashSet;
import java.util.Set;
import java.util.TreeSet;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class Board extends HashSet<Cell> {

    public Board() {
        super();
        Set<Cell> cellSet = IntStream.rangeClosed(1, 6)
                .mapToObj(i -> IntStream.rangeClosed(1, 6)
                        .mapToObj(j -> new Cell(new Position(i, j))))
                .flatMap(Function.identity())
                .collect(Collectors.toSet());
        this.addAll(cellSet);
    }

    public Cell getCellAt(Position position) {
        return this.stream()
                .filter(c -> c.getPosition().equals(position))
                .findFirst()
                .orElse(null);
    }

    public boolean isOccupiedAt(Position position) {
        Cell cell = getCellAt(position);
        return cell.isOccupied();
    }

    public boolean isFull(){
        return this.stream()
                .allMatch(c-> isOccupiedAt(c.getPosition()));
    }

    public Set<Cell> getRow(Position position){
        return this.stream()
                .filter(c -> c.getPosition().getX() == position.getX())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Cell> getCol(Position position){
        return this.stream()
                .filter(c -> c.getPosition().getY() == position.getY())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Cell> getAntiDiag(Position position){
        return this.stream()
                .filter(c -> c.getPosition().getSum() == position.getSum())
                .collect(Collectors.toCollection(TreeSet::new));
    }

    public Set<Cell> getDiag(Position position){
        if (position.getX() == position.getY()){
            return this.stream()
                    .filter(c -> c.getPosition().getX() == c.getPosition().getY())
                    .collect(Collectors.toCollection(TreeSet::new));
        } else if (position.getX() == position.getY()-1){
            return this.stream()
                    .filter(c -> c.getPosition().getX() == c.getPosition().getY()-1)
                    .collect(Collectors.toCollection(TreeSet::new));
        } else if (position.getX() == position.getY()+1){
            return this.stream()
                    .filter(c -> c.getPosition().getX() == c.getPosition().getY()+1)
                    .collect(Collectors.toCollection(TreeSet::new));
        } else{
            return null;
        }
    }

}
