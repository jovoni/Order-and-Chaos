package orderandchaos;

import java.util.Comparator;

public class Position implements Comparable<Position> {
    private final int x;
    private final int y;

    public Position(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return this.x;
    }

    public int getY() {
        return this.y;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Position position = (Position) o;
        return x == position.x &&
                y == position.y;
    }


    @Override
    public int compareTo(Position o) {
        int i = this.x - o.x;
        if (i == 0){
            return this.y - o.y;
        }

        else return i;
    }
}
