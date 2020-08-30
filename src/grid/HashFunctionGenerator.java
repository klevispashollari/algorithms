package grid;

/**
 * This interface represents a factory for {@link HashFunction hash functions}.
 * 
 * @param <T> The type for which the hash functions are created
 *
 */
public interface HashFunctionGenerator<T> {

	/**
	 * @return The next hash function
	 */
	HashFunction<T> next();

}
