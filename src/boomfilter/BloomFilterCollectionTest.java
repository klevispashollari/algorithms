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
        IBloomFilter<Integer> bloomFilter = new BloomFilter<>(100,hashFunctions(2));
        bloomFilterCollection = new BloomFilterCollection<>(bloomFilter,new ArrayList<>());
        bloomFilterCollection.addAll(IntStream.rangeClosed(20,50).boxed().collect(Collectors.toList()));
    }

    private static Collection<HashFunction<Integer>> hashFunctions(int n){
        HashFunctionGenerator<Integer> hashFunctionGenerator = new DefaultHashFunctionGenerator<>();
        Collection<HashFunction<Integer>> functions = new ArrayList<>();
        for (int i = 0; i <n ; i++) {
            functions.add(hashFunctionGenerator.next());
        }
        return functions;
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
