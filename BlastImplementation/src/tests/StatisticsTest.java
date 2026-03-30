// the test case belongs to the tests package
package tests;

// libraries to import contain JDunit code and our Statistics package
import junit.framework.TestCase;
import utilities.Statistics;
import java.util.Set;


// test all methods within Statistics
public class StatisticsTest extends TestCase {
	public void testGetSeq() {
		// initiate an input sequence
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// check whether the sequence can be retrieved
		assertEquals(seq.getSeq(), initialSeq);
	}
	
	public void testSetSeq() {
		// initiate an input sequence
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		// change the sequence
		String newSeq = "ttgccaaa";
		seq.setSeq(newSeq);
		
		// check whether the sequence was successfully changed
		assertEquals(seq.getSeq(), newSeq);
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
		char nuc = 'c'; 
		
		// count instances of base in sequence
		int count = seq.countNuc(nuc, seq.getSeq());
		System.out.printf("Sequence: %s%nNumber of %s in sequence: %d%n%n", seq.getSeq(), nuc, count);
		assertEquals(5, count);
	}
	
	public void testSeqContents() {
		// initiate a Statistics variable
		String initialSeq = "aattcggg";
		Statistics seq = new Statistics(initialSeq);
		
		System.out.println("Sequence base counts are: ");
		
		// isolate the bases from the map
		Set<Character> allBases = seq.SeqContents().keySet();
		
		// check per base how many there are in the sequence
		for (Character base : allBases) {
			int count = seq.SeqContents().get(base);
			System.out.printf("%s : %d%n", base, count);
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
				
	
}
