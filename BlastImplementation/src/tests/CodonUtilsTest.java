package tests;

import junit.framework.TestCase;
import utilities.CodonUtils;

public class CodonUtilsTest extends TestCase {
	public void testBaseWeight() {
		char alanine = 'A';
		double mw = CodonUtils.baseWeight(alanine);
		assertEquals(mw, 89.09);
	}
	
	public void testGetBase() {
		String codon = "AAA";
		char aa = CodonUtils.getBase(codon);
		assertEquals(aa, 'K');
	}
}
