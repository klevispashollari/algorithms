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
		int maxColumnSize = 0;
		for (List<E> list : grid) {
			if(list.size() > maxColumnSize) {
				maxColumnSize = list.size();
			}
		}
		return maxColumnSize;
	}

	@Override
	public int getMaxRowSize() {
		return grid.size();
	}

	@Override
	public boolean isEmpty() {
		return grid.isEmpty();
	}

	@Override
	public int size() {
		int size = 0;
		for (List<E> list : grid) {
			size = size + list.size();
		}
		return size;
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
