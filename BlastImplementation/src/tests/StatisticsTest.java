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
	// initiate an input sequence
	private String initialSeq = "aattcggg".toUpperCase();
	private Statistics seq = new Statistics(initialSeq);
	
	// check whether the sequence can be retrieved
	public void testGetSeq() {		
		assertEquals(seq.getSeq(), initialSeq);
	}
	
	// check whether the sequence can be changed
	public void testSetSeq() {
		String newSeq = "ttgccaaa".toUpperCase();
		seq.setSeq(newSeq);
		assertEquals(seq.getSeq(), newSeq);
	}
	
	public void testSeqLength() {
		// calculate the sequence's length
		int seqLength = seq.seqLength();
		assertEquals(initialSeq.length(), seqLength);
	}
	
	public void testSeqContents() {
		// isolate the bases from the map
		Set<Character> allBases = seq.SeqContents().keySet();
		
		// check per base how many there are in the sequence
		char base = 'G';
		int count = seq.SeqContents().get(base);	
		assertEquals(3, count);
		}
	}
	

			

	


