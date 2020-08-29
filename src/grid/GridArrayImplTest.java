
package grid;


import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridArrayImplTest {
    static Grid<String> grid;

    @BeforeClass
    public static void setUp() {
        grid = new GridArrayImpl<>(3, 4);
        grid.insert("1", 0, 0);
        grid.insert("2", 0, 1);
        grid.insert("3", 0, 2);
        grid.insert("4", 0, 3);
        grid.insert("1", 1, 0);
        grid.insert("2", 1, 1);
        grid.insert("3", 1, 2);
        grid.insert("4", 1, 3);
        grid.insert("1", 2, 0);
        grid.insert("2", 2, 1);
        grid.insert("3", 2, 2);
        grid.insert("4", 2, 3);
    }

    @Test
    public void testValidCases() {
        assertEquals("4", grid.get(2, 3));
        assertEquals("4", grid.remove(2, 3));
        grid.insert("7", 2, 2);
        assertEquals("7", grid.get(2, 2));
    }

    @Test(expected = IndexOutOfBoundsException.class)
    public void testInvalidCases() {
        grid.insert("7", 2, 7);
        assertEquals(null, grid.get(4, 8));
        assertEquals(null, grid.remove(4, 8));
    }
}

