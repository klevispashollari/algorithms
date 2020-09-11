package secretsharing;

import java.security.SecureRandom;
import java.util.Random;

/**
 * This class implements the simple XOR-based (n,n) secret sharing.
 * 
 * Secrets and shares are both represented as byte[] arrays.
 * 
 * Randomness is taken from a {@link java.security.SecureRandom} object.
 * 
 * @see SecureRandom
 * 
 */
public class XorSecretSharing {

	/**
	 * Creates a XOR secret sharing object for n shares
	 * 
	 * @param n
	 *            number of shares to use. Needs to fulfill n >= 2.
	 */
	public XorSecretSharing(int n) {
		assert (n >= 2);
		this.n = n;
		this.rng = new SecureRandom();
	}

	/**
	 * Shares the secret into n parts.
	 * 
	 * @param secret
	 *            The secret to share.
	 * 
	 * @return An array of the n shares.
	 */
	public byte[][] share(final byte[] secret) {
		byte[][] result = new byte[n][secret.length];
		for(int i=0; i<n; i++){
			byte[] randomBytes = new byte[secret.length];
			rng.nextBytes(randomBytes);
			result[i]=randomBytes;
		}

		return result;
	}

	/**
	 * Recombines the given shares into the secret.
	 * 
	 * @param shares
	 *            The complete set of n shares for this secret.
	 * 
	 * @return The reconstructed secret.
	 */
	public byte[] combine(final byte[][] shares) {

		// TODO: implement this

		return null;
	}

	// we suppose the length of arrays are the same ( if this wasnt the case we could take the length of the longest array and add zeros to the shorter array)
	private byte[] xor(final byte[] array1, final byte[] array2){
		byte[] result = new byte[array1.length];
		for (int i=0; i <array1.length; i++)
			result[i] = (byte) (array1[i]^array2[i]);
		return result;
	}

	private int n;

	public int getN() {
		return n;
	}

	private Random rng;
}
