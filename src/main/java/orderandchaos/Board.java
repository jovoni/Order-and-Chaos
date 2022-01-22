package orderandchaos;

import java.util.HashSet;
import java.util.Set;
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



}
