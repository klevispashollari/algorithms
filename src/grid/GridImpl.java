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
		if (grid.size() >= row && grid.get(row).size() >= column) {
			grid.get(row).set(column, element);
		} else {
			System.out.println("Index out of bound");
		}
	}

	@Override
	public E remove(int row, int column) {
		if (grid.size() >= row && grid.get(row).size() >= column) {
			return grid.get(row).remove(column);
		} else {
			return null;
		}
	}

	@Override
	public E get(int row, int column) {
		if (grid.size() >= row && grid.get(row).size() >= column) {
			return grid.get(row).get(column);
		} else {
			return null;
		}
	}

	@Override
	public int getMaxColumnSize() {
		int maxColumnSize = 0;
		for (List<E> list : grid) {
			if (list.size() > maxColumnSize) {
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

	public List<List<E>> getGrid() {
		return grid;
	}

	public void setGrid(List<List<E>> grid) {
		this.grid = grid;
	}

}
