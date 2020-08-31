package grid;

import boomfilter.DefaultHashFunctionGenerator;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CuckooHashMapTest {

    private Map<Integer,Integer> cuckooHashMap;

    @Before
    public void setup(){
        cuckooHashMap = new Map<>();
        cuckooHashMap.add(1,1);
        cuckooHashMap.add(2,1);
    }

    @Test
    public void insertTest(){
        assertEquals(cuckooHashMap.get(1).intValue(),1);
    }
}
