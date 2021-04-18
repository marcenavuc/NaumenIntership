import java.util.*;

public class ExitFinder implements RouteFinder {
    private final ArrayDeque<Cell> deque = new ArrayDeque<>();
    private final HashMap<Cell, ArrayList<Cell>> visited = new HashMap<>();

    /**
     * Поиск кратчайшего маршрута между двумя точками
     *
     * @param map карта
     * @return карта с простроенным маршрутом
     */
    @Override
    public char[][] findRoute(char[][] map) {
        Cell robotPosition = findRobot(map);

        if (robotPosition == null)
            return null;

        deque.add(robotPosition);
        visited.put(robotPosition, new ArrayList<>());
        visited.get(robotPosition).add(robotPosition);

        ArrayList<Cell> endPath= breadthFirstSearch(map);
        return endPath != null ? restorePath(map, endPath) : null;
    }

    /**
     * Поиск в ширину
     * @param map карта
     * @return возвращает путь до конечной точки
     */
    private ArrayList<Cell> breadthFirstSearch(char[][] map){
        while (deque.size() > 0) {
            Cell point = deque.pop();
            ArrayList<Cell> path = visited.get(point);
            ArrayList<Cell> neighbours = findNeighbours(map, point);

            for (Cell neighbour: neighbours) {
                if (visited.get(neighbour) == null) {
                    deque.add(neighbour);
                    visited.put(neighbour, new ArrayList<>(path));
                    visited.get(neighbour).add(neighbour);
                }

                if (map[neighbour.x][neighbour.y] == 'X')
                    return visited.get(neighbour);
            }
        }
        return null;
    }

    /**
     * Отображение пути на карте
     * @param map карта
     * @param path путь
     * @return карта с путем
     */
    private char[][] restorePath(char[][] map, ArrayList<Cell> path) {
        for (Cell point: path.subList(1, path.size() - 1)) {
            map[point.x][point.y] = '+';
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
            result.add(new Cell(point.x + 1, point.y));
        if (point.x - 1 >= 0 && map[point.x - 1][point.y] != '#')
            result.add(new Cell(point.x - 1, point.y));
        if (point.y + 1 < map[0].length && map[point.x][point.y + 1] != '#')
            result.add(new Cell(point.x, point.y + 1));
        if (point.y - 1 >= 0 && map[point.x][point.y - 1] != '#')
            result.add(new Cell(point.x, point.y - 1));
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
            return new Cell(i, j);
        return null;
    }
}
