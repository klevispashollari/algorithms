package grid;

/**
 * This interface represents a generic hash function.
 * @param <T> The type for which a hash value is calculated
 */
public interface HashFunction<T> {

	/**
	 * Computes a hash value for the given input
	 * @param element the element to hash
	 * @return the hash value for the given element
	 */
	int hash(T element);

	/**
	 * Computes the hash for the given element and performs a modulo computation
	 * with the given value. The result of this function is always a positive
	 * integer number.
	 * @param element the element to hash
	 * @param modulo the modulo value
	 * @return the hash value for the given element modulo the given number.
	 */
	default int hashMod(T element, int modulo) {
		int v = hash(element) % modulo;
		return v < 0 ? modulo + v : v;
	}
	
}
