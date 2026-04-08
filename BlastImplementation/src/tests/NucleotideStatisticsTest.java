package tests;

import java.util.HashMap;

import junit.framework.TestCase;
import utilities.Statistics;

public class NucleotideStatisticsTest extends TestCase {
	// Test GC skew calculation.
	public void testGCSkew() {
		Statistics seq = new Statistics("GGCCGA");
		double gcSkew = seq.GCSkew();
		assertEquals(gcSkew, 0.2);
	}

	// Test DNA translation using full codons.
	public void testTranslate() {
		Statistics seq = new Statistics("ATGTTTTAA");
		String protein = seq.translate();
		assertEquals(protein, "MF*");
	}

	// Test codon frequency counting.
	public void testGetCodonFrequency() {
		Statistics seq = new Statistics("ATGATGTTTTAA");
		HashMap<String, Integer> codonFreq = seq.getCodonFrequency();

		assertEquals((int) codonFreq.get("ATG"), 2);
		assertEquals((int) codonFreq.get("TTT"), 1);
		assertEquals((int) codonFreq.get("TAA"), 1);
	}

}
