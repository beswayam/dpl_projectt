// the test case belongs to the tests package
package tests;

// libraries to import contain JDunit code and our Statistics package
import junit.framework.TestCase;
import utilities.Sequence;
import utilities.Statistics;

// test all methods within Statistics
public class StatisticsTest extends TestCase {
	// initiate an input sequence
	private Sequence initialSeq = new Sequence("aattcggg");
	private Statistics seq = new Statistics(initialSeq);

	// check whether the sequence can be retrieved
	public void testGetSeq() {
		assertEquals(seq.getSeq(), initialSeq.getSequenceNoHeader());
	}

	// check whether the sequence can be changed
	public void testSetSeq() {
		Sequence newSeq = new Sequence("ttgccaaa");
		seq.setSeq(newSeq);
		assertEquals(seq.getSeq(), newSeq.getSequenceNoHeader());
	}

	public void testSeqLength() {
		// calculate the sequence's length
		int seqLength = seq.seqLength();
		assertEquals(8, seqLength);
	}

	public void testSeqContents() {
		// check per base how many there are in the sequence
		char base = 'G';
		int count = seq.seqContents().get(base);
		assertEquals(3, count);
	}
}
