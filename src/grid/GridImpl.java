package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridImpl<E> implements Grid<E> {

	List<List<E>> grid = new ArrayList<>();
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(E element, int row, int column) {
		grid.get(row).set(column, element);
	}

	@Override
	public E remove(int row, int column) {
		return grid.get(row).remove(column);
	}

	@Override
	public E get(int row, int column) {
			return grid.get(row).get(column);
	}

	@Override
	public int getMaxColumnSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int getMaxRowSize() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}

	@Override
	public int size() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public Iterator<E> rowIterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Iterator<E> columnIterator() {
		// TODO Auto-generated method stub
		return null;
	}

}
