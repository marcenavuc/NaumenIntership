import java.util.Objects;

public class Cell {
    public int x;
    public int y;
    public Cell parent;

    public Cell(int x, int y, Cell parent) {
        this.x = x;
        this.y = y;
        this.parent = parent;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cell cell = (Cell) o;
        return x == cell.x && y == cell.y;
    }

    @Override
    public int hashCode() {
        return Objects.hash(x, y);
    }
}
