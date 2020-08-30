package boomfilter;


import java.util.BitSet;
import java.util.Collection;
import java.util.function.Consumer;
import java.util.function.IntConsumer;

public class BloomFilter<E> implements IBloomFilter<E> {


    private final BitSet bitSet;
    private final Collection<HashFunction<E>> hashFunctions;

    public BloomFilter(int numBits, Collection<HashFunction<E>> hashFunctions) {

        bitSet = new BitSet(numBits);
        this.hashFunctions = hashFunctions;
    }

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

    @Override
    public <T extends Iterable<? extends E>> void rebuild(T es) {
        bitSet.clear();
        es.forEach(this::insert);
    }

    @Override
    public void remove(E e) {
        executeHashFunctions(e, bitSet::clear);
    }

    private void executeHashFunctions(E e, IntConsumer consumer){
        for (HashFunction<E> hashFunction : hashFunctions) {
            int hashNumber = hashFunction.hash(e);
            consumer.accept(hashNumber);
        }
    }

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
