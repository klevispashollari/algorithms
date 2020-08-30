package boomfilter;

import org.junit.BeforeClass;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class BloomFilterCollectionTest {

    private static Collection<Integer> bloomFilterCollection;


    @BeforeClass
    public static void setup(){
        IBloomFilter<Integer> bloomFilter = new BloomFilter<>(100,DefaultHashFunctionGenerator.hashFunctions(2));
        bloomFilterCollection = new BloomFilterCollection<>(bloomFilter,new ArrayList<>());
        bloomFilterCollection.addAll(IntStream.rangeClosed(20,50).boxed().collect(Collectors.toList()));
    }

    @Test
    public void testValidCases() {
        assertTrue(bloomFilterCollection.contains(20));
        assertTrue(bloomFilterCollection.contains(21));
        assertTrue(bloomFilterCollection.contains(49));
        assertTrue(bloomFilterCollection.contains(50));
        assertFalse(bloomFilterCollection.contains(19));
        assertFalse(bloomFilterCollection.contains(10));
    }
}
