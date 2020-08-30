package grid;

/**
 * A default implementation of the {@link HashFunction} interface.
 * This implementation simply returns {@link Object#hashCode()}.
 * 
 * @param <T> The type for which a hash value is calculated
 */
public class DefaultHashFunction<T> implements HashFunction<T> {

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hash(T element) {
		return element.hashCode();
	}
}
