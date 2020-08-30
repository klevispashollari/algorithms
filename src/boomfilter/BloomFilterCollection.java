package boomfilter;

import java.util.Collection;
import java.util.Iterator;

public class BloomFilterCollection<E> implements Collection<E> {

    private IBloomFilter<E> bloomFilter;
    private Collection<E> collection;

    public BloomFilterCollection(IBloomFilter<E> bitFilter, Collection<E> collection) {
        this.bloomFilter = bitFilter;
        this.collection = collection;
    }

    @Override
    public int size() {
        return collection.size();
    }

    @Override
    public boolean isEmpty() {
        return collection.isEmpty();
    }

    @Override
    public boolean contains(Object o) {
        if (bloomFilter.canContain(o)) {
            return collection.contains(o);
        }
        return false;
    }

    @Override
    public Iterator<E> iterator() {
        return collection.iterator();
    }

    @Override
    public Object[] toArray() {
        return collection.toArray();
    }

    @Override
    public <T> T[] toArray(T[] a) {
        return collection.toArray(a);
    }

    @Override
    public boolean add(E e) {
        bloomFilter.insert(e);
        return collection.add(e);
    }

    @Override
    public boolean remove(Object o) {
        bloomFilter.remove((E) o);
        return collection.remove(o);
    }

    @Override
    public boolean containsAll(Collection<?> c) {
        return c.stream().allMatch(this::contains);
    }

    @Override
    public boolean addAll(Collection<? extends E> c) {
        return c.stream().allMatch(this::add);
    }

    @Override
    public boolean removeAll(Collection<?> c) {
        return c.stream().allMatch(this::remove);
    }

    @Override
    public boolean retainAll(Collection<?> c) {
        bloomFilter.rebuild((Iterable<E>) c);
        return collection.retainAll(c);
    }

    @Override
    public void clear() {
        collection.clear();
    }
}
