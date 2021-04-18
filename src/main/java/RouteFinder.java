/**
 * Интерфейс поиска маршрута
 */
public interface RouteFinder {
    /**
     * Поиск кратчайшего маршрута между двумя точками
     * @param map карта
     * @return карта с простроенным маршрутом
     */
    char[][] findRoute(char [][] map);
}
