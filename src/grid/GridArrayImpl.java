package grid;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class GridArrayImpl<E> implements Grid<E> {
    private static final int DEFAULT_ROW = 10;
    private static final int DEFAULT_COL = 10;
    private transient Object[][] elements;
    private int size;
    private int[] colSize;

    private void init(int row, int col) {
        this.elements = new Object[row][col];
        this.colSize = new int[row];
    }

    public GridArrayImpl() {
        init(DEFAULT_ROW, DEFAULT_COL);
    }

    public GridArrayImpl(int row, int col) {
        init(row, col);
    }

    public static void main(String[] args) {
        GridArrayImpl<Integer> grid = new GridArrayImpl<>(3,3);
        grid.insert(20,0,0);
        grid.insert(10,0,1);
        grid.insert(10,0,2);
        grid.insert(20,1,0);
        grid.insert(20,2,0);
        grid.print();
        System.out.println(grid.size());
        System.out.println(grid.getMaxRowSize());
        System.out.println(grid.getMaxColumnSize());
        System.out.println(grid.remove(1,2));
        grid.print();
        System.out.println("ROW ITERATOR");
        Iterator<Integer> i = grid.columnIterator();
        while (i.hasNext()){
            System.out.println(i.next());
        }
    }

    private void print(){
        for (int i = 0; i <elements.length ; i++) {
            for (int j = 0; j <elements[i].length ; j++) {
                System.out.print(" "+elements[i][j]);
            }
            System.out.println();
        }
    }

    @Override
    public void insert(E element, int row, int column) {
         rangeCheckForAdd(row,column);
         this.colSize[row]++;
         this.size++;
         this.elements[row][column]=element;
    }

    @Override
    public E remove(int row, int column) {
        E element= (E)elements[row] [column];
        this.colSize[row]--;
        this.size--;
        this.elements[row][column] = null;
        return element;
    }

    @Override
    public E get(int row, int column) {
        rangeCheck(row, column);
        return (E) this.elements[row][column];
    }

    private void rangeCheck(int row, int var1) {
        if (var1 >= this.colSize[row]) {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(row, var1));
        }
    }
    private void rangeCheckForAdd(int row, int col) {
        if (row > this.elements.length || row < 0 || col<0 || col>this.elements[row].length)  {
            throw new IndexOutOfBoundsException(this.outOfBoundsMsg(row,col));
        }
    }

    private String outOfBoundsMsg(int row, int var1) {
        return String.format("Index: %d , Size: %d. ", var1, this.colSize[row]);
    }

    @Override
    public int getMaxColumnSize() {
        int maxColSize = 0;
        for (int value : colSize) {
            if (value > maxColSize) {
                maxColSize = value;
            }
        }
        return maxColSize;
    }

    @Override
    public int getMaxRowSize() {
        int result = 0;
        for (int value : colSize) {
            if (value > 0) {
                result++;
            }
        }
        return result;
    }

    @Override
    public boolean isEmpty() {
        return  size == 0;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public Iterator<E> rowIterator() {
        return new GridArrayImpl.RowItr();
    }

    @Override
    public Iterator<E> columnIterator() {
        return new GridArrayImpl.ColItr();
    }

    @Override
    public Iterator<E> iterator() {
        return rowIterator();
    }

    private class RowItr implements Iterator<E> {
        int cursorCol;
        int row;

        RowItr() {
            row = 0;
        }

        @Override
        public boolean hasNext() {
            return row != elements.length && cursorCol != elements[row].length;
        }

        @Override
        public E next() {
            int var1 = this.cursorCol;
            int var2 = this.row;

            if (var1 >= elements[row].length) {
                throw new NoSuchElementException();
            } else {
                this.cursorCol = var1 + 1;
                if(var1 == elements[row].length-1){
                    row++;
                    cursorCol=0;
                }
                return (E)elements[var2][var1];
            }


        }

    }

    private class ColItr implements Iterator<E> {
        int cursorRow;
        int col;

        ColItr() {
            col = 0;
        }

        @Override
        public boolean hasNext() {
            return col != elements.length && cursorRow != elements[col].length;
        }

        @Override
        public E next() {
            int var1 = this.cursorRow;
            int var2 = this.col;

            if (var1 >= elements[var2].length) {
                throw new NoSuchElementException();
            } else {
                this.cursorRow=var1+1;
                if(var1 == elements[col].length-1){
                    col++;
                    cursorRow=0;
                }
                return (E)elements[var1][var2];
            }


        }

    }
}
