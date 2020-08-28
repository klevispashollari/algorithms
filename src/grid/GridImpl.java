package grid;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

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
        return new GridImpl.RowItr();
    }

    @Override
    public Iterator<E> columnIterator() {
    	return new GridImpl.ColumnItr();
    }

    public List<List<E>> getGrid() {
        return grid;
    }

    public void setGrid(List<List<E>> grid) {
        this.grid = grid;
    }

    private class RowItr implements Iterator<E> {
        int cursorCol;
        int row;

        RowItr() {
            row = 0;
        }

        @Override
        public boolean hasNext() {
            return row != grid.size() && cursorCol != grid.get(row).size();
        }

        @Override
        public E next() {
            int var1 = this.cursorCol;
            int var2 = this.row;

            if (var1 >= grid.get(var2).size()) {
                throw new NoSuchElementException();
            } else {
                this.cursorCol = var1 + 1;
                if(var1 == grid.get(var2).size()-1){
                    row++;
                    cursorCol=0;
                }
                return grid.get(var2).get(var1);
            }


        }

    }
    
    private class ColumnItr implements Iterator<E> {
        int cursorRow;
        int col;

        ColumnItr() {
        	col = 0;
        }

        @Override
        public boolean hasNext() {
            return col != grid.get(0).size() && cursorRow != grid.size();
        }

        @Override
        public E next() {
            int var1 = this.cursorRow;
            int var2 = this.col;

            if (var1 >= grid.size()) {
                throw new NoSuchElementException();
            } else {
                this.cursorRow = var1 + 1;
                if(var1 == grid.size()-1){
                	col++;
                    cursorRow=0;
                }
                return grid.get(var1).get(var2);
            }


        }

    }
    
    public static void main(String[] args) {
        Iterator<String> i = getGridI().rowIterator();
        Iterator<String> k = getGridI().columnIterator();
        System.out.println("COLUMN ITERATOR");
        while (k.hasNext()){
        System.out.println(k.next());
        }
        
        System.out.println("ROW ITERATOR");
        while (i.hasNext()){
            System.out.println(i.next());
        }

}
    
    public static GridImpl<String> getGridI(){
        GridImpl<String> grid = new GridImpl<String>();
        List<List<String>> gridData = new ArrayList<>();
        List<String> column = new ArrayList<>();
        column.add("1");
        column.add("2");
        column.add("3");
        column.add("4");
        List<String> column2 = new ArrayList<>();
        column2.add("2");
        column2.add("2");
        column2.add("2");
        column2.add("3");
        gridData.add(column);
        gridData.add(column2);
        //gridData.add(column);
        grid.setGrid(gridData);
        return grid;
    }
}