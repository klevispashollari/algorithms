package grid;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Test;

import boomfilter.DefaultHashFunctionGenerator;
import boomfilter.HashFunction;

public class CuckooHashMapTest {

	@Test
	public void testInsertion() {
		CuckooHashMap<String, String> cuckoo = new CuckooHashMap<String, String>(
				new DefaultHashFunctionGenerator<String>(), 1, 1);
			System.out.println(cuckoo.getHashingArray().length);
			System.out.println(cuckoo.getHashMap().length);
		cuckoo.insert("0", "0");
		assertEquals("0", cuckoo.get("0"));
		cuckoo.remove("0");
		assertNull(cuckoo.get("0"));

	}

}
