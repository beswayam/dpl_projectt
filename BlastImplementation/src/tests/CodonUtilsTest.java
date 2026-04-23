package tests;

import junit.framework.TestCase;
import utilities.CodonUtils;

public class CodonUtilsTest extends TestCase {
	private CodonUtils utils = new CodonUtils();

	public void testBaseWeight() {
		char alanine = 'A';
		double mw = utils.baseWeight(alanine);
		assertEquals(mw, 89.09);
	}

	public void testGetBase() {
		String codon = "AAA";
		char aa = utils.getBase(codon);
		assertEquals(aa, 'K');
	}

	public void testCheckCodon() {
		String codon = "ATG";
		boolean valid = utils.checkCodon(codon);
		assertEquals(valid, true);
	}
}
