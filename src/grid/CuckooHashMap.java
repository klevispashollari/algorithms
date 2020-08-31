package grid;


import java.util.Iterator;
import java.util.NoSuchElementException;

import boomfilter.HashFunction;
import boomfilter.HashFunctionGenerator;

/**
 * Implements Cuckoo hashing with a variable amount of tables.
 *
 * @param <Key>   The key type, used to access the entries
 * @param <Value> The value type
 */
public class CuckooHashMap<Key, Value> implements Iterable<Pair> {
    int numberOfTables;
    private final HashFunction<Key>[] hashingArray = new HashFunction[numberOfTables];
    int numberBuckets;
    HashFunctionGenerator<Key> hashFunctionGenerator;
    private Pair<Key, Value>[][] hashMap = new Pair[numberOfTables][numberBuckets]; //[0][h0(x)] ???
    /**
     * Creates a new instance
     * @param gen        The hash function generator
     * @param k          The number of tables to use
     * @param numBuckets The number of buckets per table
     */
    public CuckooHashMap( HashFunctionGenerator<Key> gen , int k , int numBuckets ) {
        this.hashFunctionGenerator = gen;
        this.numberOfTables = k;
        this.numberBuckets = numBuckets;
        //TODO
    }
    /**
     * Inserts the given key-value pair into this index. If the key
     * already exists, the corresponding value is replaced.
     *
     * @param key   The key used subsequent retrievals
     * @param value The value to store.
     */
    public void insert( final Key key , final Value value ) {
        Pair<Key, Value> input = new Pair<> ( key , value );
        for ( int counter = 1; counter < numberBuckets; counter++ ) {
            int hash = hashingArray[counter % 2].hash ( input.getKey ( ) ); //odd even tables
            Pair<Key, Value> keyValuePair = hashMap[counter % 2][hash]; // cache value at current position
            if ( keyValuePair == null ) {//open slot, just insert the the inout
                hashMap[counter % 2][hash] = input;
            } else {
                //if slot is taken, replace the elements and try inserting the element
                //that were occupying the slot
                hashMap[counter % 2][hash] = input;
                input = keyValuePair;
                insert ( input.getKey ( ) , input.getValue ( ) );
            }

            // 2 ->3
            // 3--?
        }
    }

    /**
     * Retrieves the value stored for the given key.
     *
     * @param key The key of the value to retrieve.
     * @return The value for the given key.
     * @throws NoSuchElementException If no value is stored for the given key.
     */
    public Value get( Key key ) throws NoSuchElementException {
         for ( int i = 0; i < numberOfTables; ++ i ) {
            final int hash = hashingArray[i].hash ( (Key) key );
            if ( hashMap[i][hash] != null && isEqual ( hashMap[i][hash].getKey ( ) , key ) )
                return hashMap[i][hash].getValue ( );
            else throw  new NoSuchElementException (  );
        }
         return  null;

    }

    /**
     * Removes the value stored for the given key.
     *
     * @param key The key to remove.
     * @throws NoSuchElementException If no value is stored for the given key.
     */
    public void remove( Key key ) throws NoSuchElementException {
         for ( int i = 0; i < numberBuckets; ++ i ) {
            final int hash = hashingArray[i].hash ( (Key) key );
            if ( hashMap[i][hash] != null && isEqual ( hashMap[i][hash].getKey ( ) , key ) ) {
 //                Value result = mArrays[i][hash].getValue ( ); //ache the value to return
                /* Wipe this element from the array. */
                hashMap[i][hash] = null;
                -- numberOfTables;
            }
        }

        /* Didn't find it. */
    }

    private boolean isEqual( Key key , Key key1 ) {

        return key.equals ( key1 );
    }

    /**
     * Returns an iterator over elements of type {@code T}.
     *
     * @return an Iterator.
     */
    @Override
    public Iterator<Pair> iterator( ) {
        return new MapIterator ( );
    }

    private class MapIterator implements Iterator<Pair> {
        private int nextTable = 0, nextIndex = 0;
        private Pair<Key, Value> cachedKeyValue = null;

        /**
         * Returns {@code true} if the iteration has more elements.
         * (In other words, returns {@code true} if {@link #next} would
         * return an element rather than throwing an exception.)
         *
         * @return {@code true} if the iteration has more elements
         */
        @Override
        public boolean hasNext( ) {
            return nextTable != numberOfTables;
        }

        /**
         * Returns the next element in the iteration.
         *
         * @return the next element in the iteration
         * @throws NoSuchElementException if the iteration has no more elements
         */
        @Override
        public Pair next( ) {
            if ( ! hasNext ( ) ) {
                throw new NoSuchElementException ( );
            }
            //Cache the value we're going to return.
            Pair<Key, Value> result = hashMap[nextTable][nextIndex];
            ++ nextIndex;
            chekNextIndex ( ); //nextIndex=o if there's no nextIndex
            cachedKeyValue = result;
            return result;
        }

        private void chekNextIndex( ) {
            while ( nextTable < numberOfTables ) {
                while ( nextIndex < numberBuckets ) {
                    if ( hashMap[nextTable][nextIndex] != null )
                        return;
                    nextIndex = 0; // no next element, check the next table
                }
            }
        }
    }
}

