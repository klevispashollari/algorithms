package boomfilter;


import java.util.BitSet;
import java.util.Collection;
import java.util.function.IntConsumer;

public class BloomFilter<E> implements IBloomFilter<E> {


    private final BitSet bitSet;
    private final Collection<HashFunction<E>> hashFunctions;

    public BloomFilter(int numBits, Collection<HashFunction<E>> hashFunctions) {

        bitSet = new BitSet(numBits);
        this.hashFunctions = hashFunctions;
    }

    /**
     * Check if element exists in bitset using the given hashFunctions.
     * Return false if it doesnt exists
     * Return true if it can exists
     */
    @Override
    public boolean canContain(Object e) {
        if (bitSet.isEmpty()) {
            return false;
        }
        for (HashFunction<E> hashFunction : hashFunctions) {
            int hashNumber = hashFunction.hash((E)e);
            if (!this.bitSet.get(hashNumber)) {
                return false;
            }
        }
        return true;
    }

    /**
     * Clear array of bitset and insert new values from given parameter
     */
    @Override
    public <T extends Iterable<? extends E>> void rebuild(T es) {
        bitSet.clear();
        es.forEach(this::insert);
    }

    /**
     * Removes the given element from the bitSet
     */
    @Override
    public void remove(E e) {
        executeHashFunctions(e, bitSet::clear);
    }

    /**
     * Custom lambda expression that modifies clear method of bitSet 
     * to delete only the element given as parameter if it exists.
     * @param e
     * @param consumer
     */
    private void executeHashFunctions(E e, IntConsumer consumer){
        for (HashFunction<E> hashFunction : hashFunctions) {
            int hashNumber = hashFunction.hash(e);
            consumer.accept(hashNumber);
        }
    }

    /**
     * Insert the given element to bitSet.
     */
    @Override
    public void insert(E e) {
        executeHashFunctions(e, bitSet::set);
    }

    @Override
    public String toString(){
        StringBuilder s = new StringBuilder();
        for( int i = 0; i < bitSet.length();  i++ )
        {
            s.append(bitSet.get(i) ? 1: 0 );
        }
        return s.toString();
    }

}
