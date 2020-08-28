package grid;

import java.util.Iterator;
/**
 * This interface represents a 2-dimensional Grid data structure with a configurable amount of rows and columns.
 * @param <E> The type of elements stored inside the Grid.
 */
public interface Grid<E> extends Iterable<E> {

    /**
     * Inserts the given element at the specified row and column positions, replacing any element already stored there.
     * @param element The element which will be inserted.
     * @param row The row position of the new element (starting with 0)
     * @param column The column position of the new element (starting at 0)
     */
    void insert(E element, int row, int column);

    /**
     * Removes the element stored at the given row and column positions and returns it.
     * @param row The row position of the new element (starting with 0)
     * @param column The column position of the new element (starting at 0)
     * @return The element stored at the given position; {@code null} if no element is stored at the position.
     */
    E remove(int row, int column);

    /**
     * Retrieves the element stored at the given row and column positions.
     * @param row The row position of the new element (starting with 0)
     * @param column The column position of the new element (starting at 0)
     * @return The element stored at the given position; {@code null} if no element is stored at the position.
     */
    E get(int row, int column);

    /**
     * Returns the number of columns in this grid, i.e., the maximum column position + 1
     * @return the number of columns in this grid
     */
    int getMaxColumnSize();

    /**
     * Returns the number of rows in this grid, i.e., the maximum row position + 1
     * @return the number of row in this grid
     */
    int getMaxRowSize();

    /**
     * Returns {@code true} if this grid contains no elements.
     *
     * @return {@code true} if this grid contains no elements
     */
    boolean isEmpty();

    /**
     * Returns the size of the grid, i.e. the number of inserted elements
     * @return The size of the grid
     */
    int size();

    /**
     * Returns a row iterator over elements in the grid,
     * first returning all elements of the first row (in order of increasing column index),
     * then elements of the second row (in order of increasing column index), etc.
     *
     * @return an Iterator.
     */
    Iterator<E> rowIterator();

    /**
     * Returns a column iterator over elements in the grid,
     * first returning all elements of the first column (in order of increasing row index),
     * then elements of the second column (in order of increasing row index), etc.
     *
     * @return an Iterator.
     */
    Iterator<E> columnIterator();

}
