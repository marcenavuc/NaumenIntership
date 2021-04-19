import java.util.Comparator;

/**
 * Класс, релизующий метрику для оптимизации bfs
 */
public class EuclideanComparator implements Comparator<Cell> {
    private Cell freezeCell;

    public EuclideanComparator(Cell freezeCell) {
        this.freezeCell = freezeCell;
    }

    @Override
    public int compare(Cell cell1, Cell cell2) {
        double cellDistance1 = getDistance(cell1, freezeCell);
        double cellDistance2 = getDistance(cell2, freezeCell);
        if (cellDistance1 > cellDistance2)
            return 1;
        if (cellDistance1 == cellDistance2)
            return 0;
        return  -1;
    }

    /**
     * Евклидовая дистаниция
     * @param cell1 первая клетка
     * @param cell2 вторая клетка
     * @return дистаниция
     */
    private double getDistance(Cell cell1, Cell cell2) {
        int xSquare = (cell1.getX() - cell2.getX()) * (cell1.getX() - cell2.getX());
        int ySquare = (cell1.getY() - cell2.getY()) * (cell1.getY() - cell2.getY());
        return Math.sqrt(xSquare + ySquare);
    }
}