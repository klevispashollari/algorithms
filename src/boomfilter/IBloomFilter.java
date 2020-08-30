package boomfilter;


public interface IBloomFilter<E> {
    /**
     * Returns whether the given element could be in the collection. There is a chance for so-called false positives,
     * i.e., the method returns {@code true} even though the element was not inserted into the list.
     * However, there are no false negatives, i.e., the method never returns {@code false} if the element was indeed inserted.
     * @param e The element to search for in the BloomFilter.
     * @return {@code false}, if the element is not in the BloomFilter; {@code true} otherwise
     */
    boolean canContain(Object e);
    /**
     * Inserts the given element into the BloomFilter, setting the corresponding bits to 1
     * @param e The element which will be inserted
     */
    void insert(E e);
    /**
     * Clears the BloomFilter, i.e., setting all bits to 0 and rebuilds it from scratch based on the given given data.
     * @param es The Iterable from which the BloomFilter will be rebuild.
     */
    public <T extends Iterable<? extends E>> void rebuild(T es);

    public void remove(E e);

    /**
     * Uses {@link #insert(E) insert} in a sequential manner
     * @param es The Iterable.
     */
    default void insert(Iterable<E> es) {
        es.forEach(this::insert);
    }
}
