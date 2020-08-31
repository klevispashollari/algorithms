package grid;


/**
 * Represents a pair of values
 * @param <Key> The type of the first value
 * @param <Value> The type of the second value
 */
public class Pair<Key, Value> {

	/** The key */
    private Key key;
    
    /** The value */
    private Value value;

    /**
     * Creates a new instance
     * @param key The key
     * @param value The value
     */
    public Pair(Key key, Value value) {
        this.key = key;
        this.value = value;
    }

	/**
	 * @return the key
	 */
	public Key getKey() {
		return key;
	}

	/**
	 * Sets the key
	 * @param key the key to set
	 */
	public void setKey(Key key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	public Value getValue() {
		return value;
	}

	/**
	 * Sets the value
	 * @param value the value to set
	 */
	public void setValue(Value value) {
		this.value = value;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((key == null) ? 0 : key.hashCode());
		result = prime * result + ((value == null) ? 0 : value.hashCode());
		return result;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Pair<?,?> other = (Pair<?,?>) obj;
		if (key == null) {
			if (other.key != null)
				return false;
		} else if (!key.equals(other.key))
			return false;
		if (value == null) {
			if (other.value != null)
				return false;
		} else if (!value.equals(other.value))
			return false;
		return true;
	}

	/**
	 * {@inheritDoc}
	 */
	@Override
	public String toString() {
		return "Pair(" + key + ", " + value + ")";
	}
	
	
}
