package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class GridImpl<E> implements Grid<E> {

	List<E> gridRows = new ArrayList<>();
	
	List<List<E>> grid = new ArrayList<>();
	
	@Override
	public Iterator<E> iterator() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void insert(E element, int row, int column) {
		for (int i = 0 ; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(i).size(); j++) {
				if(i == row && j == column) {
					grid.get(i).add(j, element);
				}
			}
		}
	}

	@Override
	public E remove(int row, int column) {
		E deletedElement = null;
		for (int i = 0 ; i < grid.size(); i++) {
			for(int j = 0; j < grid.get(i).size(); j++) {
				if(i == row && j == column) {
					 deletedElement = grid.get(i).remove(j);
				}
			}
		}
		return deletedElement;
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
