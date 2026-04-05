// the test case belongs to the tests package
package tests;

// libraries to import contain JDunit code and our Statistics package
import junit.framework.TestCase;
import utilities.Statistics;

import java.util.ArrayList;
import java.util.Set;
import java.util.HashMap;


// test all methods within Statistics
public class StatisticsTest extends TestCase {
	public void testGetSeq() {
		// initiate an input sequence
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// check whether the sequence can be retrieved
		assertEquals(seq.getSeq(), initialSeq.toUpperCase());
	}
	
	public void testSetSeq() {
		// initiate an input sequence
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// change the sequence
		String newSeq = "ttgccaaa";
		seq.setSeq(newSeq);
		
		// check whether the sequence was successfully changed
		assertEquals(seq.getSeq(), newSeq.toUpperCase());
	}
	
	public void testSeqLength() {
		// initiate a Statistics variable
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// calculate the sequence's length
		int seqLength = seq.seqLength();
		assertEquals(initialSeq.length(), seqLength);
	}
	
	public void testCountNuc() {
		// initiate a Statistics variable
		String initialSeq = "aattcccgggcc";
		Statistics seq = new Statistics(initialSeq);
		
		// choose base to search
		char nuc = 'C'; 
		
		// count instances of base in sequence
		int count = seq.countNuc(nuc, seq.getSeq());
		//System.out.printf("Sequence: %s%nNumber of %s in sequence: %d%n%n", seq.getSeq(), nuc, count);
		assertEquals(5, count);
	}
	
	public void testSeqContents() {
		// initiate a Statistics variable
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		//System.out.println("Sequence base counts are: ");
		
		// isolate the bases from the map
		Set<Character> allBases = seq.SeqContents().keySet();
		
		// check per base how many there are in the sequence
		for (Character base : allBases) {
			int count = seq.SeqContents().get(base);
			//System.out.printf("%s : %d%n", base, count);
			assertEquals(seq.countNuc(base, seq.getSeq()), count);
		}
	}
	
	public void testGCContent() {
		// initiate a Statistics variable
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// calculate the GC content
		double gcProportion = seq.GCContent();
		System.out.printf("GC content in sequence: %.1f%%%n%n", (double) Math.round(gcProportion * 100));
		assertEquals(gcProportion, 0.5);
	}
				
	// note: introns are not taken into account
	public void testReadingFrame() {
		// initiate a Statistics variable
		String initialSeq = "GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA";
		Statistics seq = new Statistics(initialSeq);
		int k = 1;
		
		//get the readingframe k
		String rFrame = seq.ReadingFrame(k);
		assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
		}
	
	public void testReverseCompliment() {
		String initialSeq = "GGTCCA";
		Statistics seq = new Statistics(initialSeq);
		
		// get the reverse compliment sequence
		String revCompSeq = seq.ReverseCompliment();
		assertEquals(revCompSeq, "TGGACC");
	}
	
	// note: introns are not taken into account
	public void testAllReadingFrames() {
		String initialSeq = "GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA";
		Statistics seq = new Statistics(initialSeq);
		
		// make an array of all reading frames you want to find
		int[] k = {1,2,3};
		
		ArrayList<String> readingFrames = seq.AllReadingFrames(k);
		for (String rFrame : readingFrames) {
			
			// check whether a reading frame has successfully retrieved coding sequence
			if (!rFrame.isEmpty()) {
				assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
			}
		}	
	}
	
	public void testMolecularWeightt() {
		String initialSeq = "ACDEFGHIKL";
		Statistics seq = new Statistics(initialSeq);
		
		double protWeight = seq.ProteinWeight();
		assertEquals(protWeight, 1294.45);
	}

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

	


