package tests;

import utilities.Sequence;
import java.util.ArrayList;
import java.util.HashMap;
import junit.framework.TestCase;
import utilities.NucleotideStatistics;

public class NucleotideStatisticsTest extends TestCase {
	NucleotideStatistics sequence = new NucleotideStatistics(new Sequence("GGCCGA"));

	// Test GC skew calculation.
	public void testGcSkew() {
		double gcSkew = sequence.gcSkew();
		assertEquals(gcSkew, 0.2);
	}

	// calculate the GC content
	public void testGcContent() {
		double gcProportion = sequence.gcContent();
		assertEquals(gcProportion, (double) 5 / 6);
	}

	// note: introns are not taken into account
	public void testReadingFrame() {
		// initiate a Statistics variable
		sequence.setSeq(new Sequence("GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA"));
		int k = 0;

		// get the readingframe k
		String rFrame = sequence.readingFrame(k);
		assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
	}

	// note: introns are not taken into account
	public void testAllReadingFrames() {
		sequence.setSeq(new Sequence("GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA"));

		// make an array of all reading frames you want to find
		ArrayList<Integer> k = new ArrayList<>();
		k.add(0);
		k.add(1);
		k.add(2);

		ArrayList<String> readingFrames = sequence.allReadingFrames(k);
		for (String rFrame : readingFrames) {

			// check whether a reading frame has successfully retrieved coding sequence
			if (!rFrame.isEmpty()) {
				assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
			}
		}
	}

	// Test DNA translation using full codons.
	public void testTranslate() {
		sequence.setSeq(new Sequence("ATGTTTTAA"));
		String protein = sequence.translate();
		assertEquals(protein, "MF*");
	}

	// get the reverse compliment sequence
	public void testReverseCompliment() {
		sequence.setSeq(new Sequence("GGTCCA"));

		String revCompSeq = sequence.reverseCompliment();
		assertEquals(revCompSeq, "TGGACC");
	}

	// Test codon frequency counting.
	public void testGetCodonFrequency() {
		sequence.setSeq(new Sequence("ATGATGTTTTAA"));
		HashMap<String, Integer> codonFreq = sequence.getCodonFrequency();

		assertEquals((int) codonFreq.get("ATG"), 2);
		assertEquals((int) codonFreq.get("TTT"), 1);
		assertEquals((int) codonFreq.get("TAA"), 1);
	}
}
