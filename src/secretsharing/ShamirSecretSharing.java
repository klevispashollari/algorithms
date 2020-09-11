package secretsharing;

import java.math.BigInteger;
import java.security.SecureRandom;

/**
 * This class implements Shamir's (t,n) secret sharing.
 * 
 * Secrets are represented as BigInteger objects, shares as ShamirShare objects.
 * 
 * Randomness is taken from a {@link java.security.SecureRandom} object.
 * 
 * @see ShamirShare
 * @see BigInteger
 * @see SecureRandom
 * 
 * @author ewti
 * 
 */
public class ShamirSecretSharing {

	/**
	 * Creates a (t,n) Shamir secret sharing object for n shares with threshold t.
	 * 
	 * @param t
	 *            threshold: any subset of t <= i <= n shares can recover the
	 *            secret.
	 * @param n
	 *            number of shares to use. Needs to fulfill n >= 2.
	 */
	public ShamirSecretSharing(int t, int n) {
		assert (t >= 2);
		assert (n >= t);

		this.t = t;
		this.n = n;
		this.rng = new SecureRandom();

		// use p = 2^2048 + 981
		this.p = BigInteger.ONE.shiftLeft(2048).add(BigInteger.valueOf(981));
	}
	

	/**
	 * Shares the secret into n parts.
	 * 
	 * @param secret
	 *            The secret to share.
	 * 
	 * @return An array of the n shares.
	 */
	public ShamirShare[] share(BigInteger secret) {
		ShamirShare[] shares = new ShamirShare[t];
		BigInteger a[] = new BigInteger[t];
		for(int i = 1; i< t; i++) {
			a[i] = new BigInteger (p.bitLength (), rng);
			shares[i] = new ShamirShare(BigInteger.valueOf(i), horner(BigInteger.valueOf(i),a).mod(p));
		}
		return shares;
		
	}

	/**
	 * Evaluates the polynomial a[0] + a[1]*x + ... + a[t-1]*x^(t-1) modulo p at
	 * point x using Horner's rule.
	 * 
	 * @param x
	 *            point at which to evaluate the polynomial
	 * @param a
	 *            array of coefficients
	 * @return value of the polynomial at point x
	 */
	private BigInteger horner(BigInteger x, BigInteger[] a) {
		BigInteger sum = new BigInteger("0");
		
		for(int i = a.length-1;  i>=1; i--) {
			sum = (sum.add(a[i])).multiply(x.pow(i));
		}
		sum = sum.add(a[0]);
		
		return sum;
	}

	/**
	 * Recombines the given shares into the secret.
	 * 
	 * @param shares
	 *            A set of at least t out of the n shares for this secret.
	 * 
	 * @return The reconstructed secret.
	 */
	public BigInteger combine(ShamirShare[] shares) {

		// TODO: implement this

		return null;
	}

	public int getT() {
		return t;
	}

	public int getN() {
		return n;
	}

	private int t;
	private int n;
	private SecureRandom rng;
	private BigInteger p;
	
}