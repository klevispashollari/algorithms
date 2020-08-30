package grid;


import java.util.Collection;

public class BloomFilter<E> implements IBloomFilter<E> {

    // TODO

    public BloomFilter(int numBits, Collection<HashFunction<E>> hashFunctions) {
        // TODO
    }

    @Override
    public boolean canContain(Object o) {
        // TODO
		return false;
    }

    @Override
    public void rebuild(Iterable<E> es) {
        // TODO
    }

    @Override
    public void insert(E e) {
        // TODO
    }
}
