package tests;

import java.util.ArrayList;

import junit.framework.TestCase;
import utilities.NucleotideStatistics;

public class NucleotideStatisticsTest extends TestCase {
	NucleotideStatistics seq = new NucleotideStatistics("GGCCGA");
	
	// Test GC skew calculation.
	public void testGCSkew() {
		double gcSkew = seq.GCSkew();
		assertEquals(gcSkew, 0.2);
	}
	
	// calculate the GC content
	public void testGCContent() {	
		double gcProportion = seq.GCContent();
		assertEquals(gcProportion, (double) 5 / 6);
	}
	
	// note: introns are not taken into account
	public String testReadingFrame() {
		// initiate a Statistics variable
		seq.setSeq("GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA");
		int k = 0;
		
		//get the readingframe k
		String rFrame = seq.ReadingFrame(k);
		assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
		return rFrame;
		}
	
	// note: introns are not taken into account
	public void testAllReadingFrames() {
		seq.setSeq("GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA");
		
		// make an array of all reading frames you want to find
		ArrayList<Integer> k = new ArrayList<>();
		k.add(0);
		k.add(1);
		k.add(2);
		
		ArrayList<String> readingFrames = seq.AllReadingFrames(k);
		
		for (String rFrame : readingFrames) {
			
			// check whether a reading frame has successfully retrieved coding sequence
			if (!rFrame.isEmpty()) {
				assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
			}
		}	
	}
	
	
	// Test DNA translation using full codons.
	public void testTranslate() {
		seq.setSeq("ATGTTTTAA");
		//seq.setSeq(testReadingFrame());
		String protein = seq.Translate();
		//assertEquals(protein, "MEFRFGNPGSS*");
		assertEquals(protein, "MF*");
	}
	
	/*
	// Test codon frequency counting.
	public void testGetCodonFrequency() {
		Statistics seq = new Statistics("ATGATGTTTTAA");
		HashMap<String, Integer> codonFreq = seq.getCodonFrequency();

		assertEquals((int) codonFreq.get("ATG"), 2);
		assertEquals((int) codonFreq.get("TTT"), 1);
		assertEquals((int) codonFreq.get("TAA"), 1);
	}
	

	
	public void testReverseCompliment() {
		String initialSeq = "GGTCCA";
		Statistics seq = new Statistics(initialSeq);
		
		// get the reverse compliment sequence
		String revCompSeq = seq.ReverseCompliment();
		assertEquals(revCompSeq, "TGGACC");
	}
	

	
	
	*/

}
