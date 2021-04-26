import java.util.*;

public class BreadthFirstFinder implements RouteFinder {
    private ArrayDeque<Cell> deque;
    private HashSet<Cell> visited;
    private static final int[][] directions = {{1, 0}, {0, 1}, {-1, 0}, {0, -1}};

    public BreadthFirstFinder() {
        visited = new HashSet<>();
    }

    /**
     * Поиск кратчайшего маршрута между двумя точками
     * с помощью bfs с очередью приориететов
     * @param map карта
     * @return карта с простроенным маршрутом
     */
    @Override
    public char[][] findRoute(char[][] map) {
        Cell robotPosition = findChar(map, '@');

        deque = new ArrayDeque<>();
        visited = new HashSet<>(map.length * map[0].length);

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
                    neighbour.setParent(point);
                    deque.add(neighbour);
                    visited.add(neighbour);
                }

                if (map[neighbour.getX()][neighbour.getY()] == 'X')
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
        Cell parent = endCell.getParent();
        while (parent != null && parent.getParent() != null) {
            map[parent.getX()][parent.getY()] = '+';
            parent = parent.getParent();
        }
        return map;
    }

    /**
     * Поиск ближайших соседей для клетки
     * @param map карта
     * @param cell клетка
     * @return соседи для point
     */
    private ArrayList<Cell> findNeighbours(char[][] map, Cell cell) {
        ArrayList<Cell> result = new ArrayList<>();
        for (int[] dir : directions) {
            int x = cell.getX() + dir[0];
            int y = cell.getY() + dir[1];

            if (!isOutOfMap(x, y, map) && !isWall(x, y, map))
                result.add(new Cell(x, y, cell));
        }
        return result;
    }

    private boolean isWall(int x, int y, char[][] map)
    {
        return map[x][y] == '#';
    }

    private boolean isOutOfMap(int x, int y, char[][] map)
    {
        return (x < 0 || x >= map.length || y < 0 || y >= map[0].length);
    }

    /**
     * Поиск определенного символа на карте
     * @param map карта
     * @return позиция символа
     */
    private Cell findChar(char[][] map, char letter) {
        for(int i=0; i < map.length; i++)
        for(int j=0; j < map[0].length; j++)
        if (map[i][j] == letter)
            return new Cell(i, j, null);
        return null;
    }
}
