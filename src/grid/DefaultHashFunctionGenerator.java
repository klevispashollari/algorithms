package grid;


/**
 * Generates a sequence of hash function as follows:
 * Generate two hash function h(k) and h'(k) with h(k) = p1 * k.hashCode()
 * and h'(k) = p2 * k.hashCode(), whereby p1 and p2 are prime numbers.
 * Then, the sequence is generated as hj(k) = h(k) + j*h'(k), with j being 
 * sequence number of the generated funtion.
 * 
 * @param <T> The type for which a hash value is calculated
 */
public class DefaultHashFunctionGenerator<T> implements HashFunctionGenerator<T> {
	
	/** The first hash function h(k) */
	private final HashFunction<T> h1 = element -> (17*element.hashCode());

    /** The first hash function h'(k) */
    private final HashFunction<T> h2 = element -> (31*element.hashCode());
    
    /** The sequence counter */
    private int j = 1;
	

    /** 
     * {@inheritDoc}
     */
	@Override
	public HashFunction<T> next() {
		final int multiplier = j++;
		return element -> (h1.hash(element) + multiplier*h2.hash(element));
	}

}
