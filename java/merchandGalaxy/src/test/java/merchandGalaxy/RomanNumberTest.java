package merchandGalaxy;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

import merchandGalaxy.number.RomanNumber;

/**
 * test roman numbers
 * 
 * @author Cintia
 *
 */
public class RomanNumberTest {

	@Test
	public void isValidNumber() {
		RomanNumber n = new RomanNumber("MCMXLIV");
		assertTrue(n.isValid());
	}

	@Test
	public void isInvalidNumberWithWrongOrder() {
		RomanNumber n = new RomanNumber("MCMXLIVMM");
		assertFalse(n.isValid());
	}

	@Test
	public void isInvalidNumberWithWrongRepition() {
		RomanNumber n = new RomanNumber("MCMXLLLIV");
		assertFalse(n.isValid());
	}

	@Test
	public void isInvalidNumberWithLowerCase() {
		RomanNumber n = new RomanNumber("mcmx");
		assertFalse(n.isValid());
	}

	@Test
	public void isInvalidNumberWithRepition4M() {
		RomanNumber n = new RomanNumber("MMMM");
		assertFalse(n.isValid());
	}

	@Test
	public void numberIsEqual() {
		RomanNumber n = new RomanNumber("MCMIII");
		assertEquals(n.romanToInteger(), new Integer(1903));
	}

	@Test
	public void numberIsNotEqual() {
		RomanNumber n = new RomanNumber("MCMIII");
		assertNotEquals(n.romanToInteger(), new Integer(1904));
	}
}
