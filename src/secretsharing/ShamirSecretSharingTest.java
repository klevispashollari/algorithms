package secretsharing;

import java.math.BigInteger;

import org.junit.Assert;
import org.junit.Test;

public class ShamirSecretSharingTest {

	@Test
	public void shamirTest() {

		ShamirSecretSharing shamirSecretSharing = new ShamirSecretSharing(2, 4);
		BigInteger secret = new BigInteger("4");

		BigInteger newSecret = shamirSecretSharing.combine(shamirSecretSharing.share(secret));

		Assert.assertEquals(secret, newSecret);
	}
}
