import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class LoadTest {

    @Test
    public void hugeFindRouteTest() {
        int maxLength = 10000;
        char[][] map = new char[maxLength][maxLength];
        char[][] expected = new char[maxLength][maxLength];
        for (int i = 0; i < maxLength; i++)
            for (int j = 0; j < maxLength; j++) {
                map[i][j] = '.';
                expected[i][j] = '.';
                if (i == j || i == j + 1)
                    expected[i][j] = '+';
            }

        map[0][0] = '@';
        expected[0][0] = '@';
        map[maxLength - 1][maxLength - 1] = 'X';
        expected[maxLength - 1][maxLength - 1] = 'X';
        BreadthFirstFinder routeFinder = new BreadthFirstFinder();
        char[][] result = routeFinder.findRoute(map);
        assertEquals(expected, routeFinder.findRoute(map));
    }
}
