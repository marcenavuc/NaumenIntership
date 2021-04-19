import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

import org.junit.runners.Parameterized;
import org.junit.runner.RunWith;

import java.util.Arrays;
import java.util.Collection;

@RunWith(Parameterized.class)
public class ExitFinderParametrizedTest {
    char[][] map;
    char[][] expected;
    RouteFinder routeFinder;

    public ExitFinderParametrizedTest(char[][] map, char[][] expected) {
        this.map = map;
        this.expected = expected;
    }

    @Before
    public void setUp() {
        routeFinder = new BreadthFirstFinder();
    }

    @Parameterized.Parameters
    public static Collection findRoutes() {
        return Arrays.asList(new Object[][] {
                {
                    new char[][] {
                            "...@.".toCharArray(),
                            ".##.#".toCharArray(),
                            ".....".toCharArray(),
                            ".####".toCharArray(),
                            ".X...".toCharArray(),
                    },
                    new char[][] {
                            "...@.".toCharArray(),
                            ".##+#".toCharArray(),
                            "++++.".toCharArray(),
                            "+####".toCharArray(),
                            "+X...".toCharArray(),
                    }
                },
                {
                    new char[][] {
                            "...@.".toCharArray(),
                            ".##.#".toCharArray(),
                            ".....".toCharArray(),
                            "#####".toCharArray(),
                            ".X...".toCharArray(),
                    },
                    null
                },
        });
    }

    @Test
    public void findRouteTest() {
        assertEquals(expected, routeFinder.findRoute(map));
    }
}
