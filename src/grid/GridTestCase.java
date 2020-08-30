
package grid;




import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class GridTestCase {
	GridImpl<String> grid = new GridImpl<String>(3,4);


	public GridImpl<String> getGrid(){

		List<List<String>> gridData = new ArrayList<>();
		List<String> column = new ArrayList<>();
		column.add("1");
		column.add("2");
		column.add("3");
		column.add("4");
		gridData.add(column);
		gridData.add(column);
		gridData.add(column);
		grid.setGrid(gridData);
		return grid;
	}

	
	@Test
	public void testRowIterator() {
		grid = getGrid();
        Iterator<String> rowIterator = grid.rowIterator();
		assertEquals("1", rowIterator.next());
		assertEquals("2", rowIterator.next());
		assertEquals("3", rowIterator.next());
		assertEquals("4", rowIterator.next());

	}
	
	@Test
	public void testColumnIterator() {
		grid = getGrid();
        Iterator<String> columnIterator = grid.columnIterator();
		assertEquals("1", columnIterator.next());
		assertEquals("1", columnIterator.next());
		assertEquals("1", columnIterator.next());
		assertEquals("2", columnIterator.next());
		assertEquals("2", columnIterator.next());
		assertEquals("2", columnIterator.next());

	}
	
	@Test
	public void testValidCases() {
		grid = getGrid();
		assertEquals("4",grid.get(2, 3));
		assertEquals("4",grid.remove(2, 3));
		grid.insert("7", 2, 2);
		assertEquals("7",grid.get(2, 2));
	}

	@Test
	public void testInvalidCases() {
		grid = getGrid();
		grid.insert("7", 2, 7);
		assertEquals(null, grid.get(4,8));
		assertEquals(null, grid.remove(4,8));
	}

}

