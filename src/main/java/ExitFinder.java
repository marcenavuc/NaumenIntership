import java.util.*;

public class ExitFinder implements RouteFinder {
    private final ArrayDeque<Cell> deque;
    private HashSet<Cell> visited;

    public ExitFinder() {
        deque = new ArrayDeque<>();
        visited = new HashSet<>();
    }

    /**
     * Поиск кратчайшего маршрута между двумя точками
     *
     * @param map карта
     * @return карта с простроенным маршрутом
     */
    @Override
    public char[][] findRoute(char[][] map) {
        visited = new HashSet<>(map.length * map[0].length);
        Cell robotPosition = findRobot(map);

        if (robotPosition == null)
            return null;

        deque.add(robotPosition);
        visited.add(robotPosition);

        Cell endCell = breadthFirstSearch(map);
        return endCell != null ? restorePath(map, endCell) : null;
    }

    /**
     * Поиск в ширину
     * @param map карта
     * @return возвращает путь до конечной точки
     */
    private Cell breadthFirstSearch(char[][] map){
        while (deque.size() > 0) {
            Cell point = deque.pop();
            ArrayList<Cell> neighbours = findNeighbours(map, point);

            for (Cell neighbour: neighbours) {
                if (!visited.contains(neighbour)) {
                    deque.add(neighbour);
                    neighbour.parent = point;
                    visited.add(neighbour);
                }

                if (map[neighbour.x][neighbour.y] == 'X')
                    return neighbour;
            }
        }
        return null;
    }

    /**
     * Отображение пути на карте
     * @param map карта
     * @param endCell конечная точка
     * @return карта с  путем
     */
    private char[][] restorePath(char[][] map, Cell endCell) {
        Cell parent = endCell.parent;
        while (parent != null && parent.parent != null) {
            map[parent.x][parent.y] = '+';
            parent = parent.parent;
        }
        return map;
    }

    /**
     * Поиск ближайших соседей для клетки
     * @param map карта
     * @param point клетка
     * @return соседи для point
     */
    private ArrayList<Cell> findNeighbours(char[][] map, Cell point) {
        ArrayList<Cell> result = new ArrayList<>();
        if (point.x + 1 < map.length && map[point.x + 1][point.y] != '#')
            result.add(new Cell(point.x + 1, point.y, point));
        if (point.x - 1 >= 0 && map[point.x - 1][point.y] != '#')
            result.add(new Cell(point.x - 1, point.y, point));
        if (point.y + 1 < map[0].length && map[point.x][point.y + 1] != '#')
            result.add(new Cell(point.x, point.y + 1, point));
        if (point.y - 1 >= 0 && map[point.x][point.y - 1] != '#')
            result.add(new Cell(point.x, point.y - 1, point));
        return result;
    }

    /**
     * Поиск робота на карте
     * @param map карта
     * @return позиция робота
     */
    private Cell findRobot(char[][] map) {
        for(int i=0; i < map.length; i++)
        for(int j=0; j < map[0].length; j++)
        if (map[i][j] == '@')
            return new Cell(i, j, null);
        return null;
    }
}
