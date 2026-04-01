package tests;

import junit.framework.TestCase;
import utilities.Sequence;

public class SequenceTest extends TestCase {
	
	
	public void testprepareSequence() {
		// Input without fasta header
		String without_header = "atcgatcg";
		Sequence check1 = new Sequence(without_header);
		assertEquals(">sequence\nATCGATCG", check1.getSequence());
		
		// Input with fasta header
		String with_header = ">mysequence\natcgatcg";
		Sequence check2 = new Sequence(with_header);
		assertEquals(">mysequence\nATCGATCG", check2.getSequence());

		// Input all lowercase
		String all_lowercase = "atcgatcg";
		Sequence check3 = new Sequence(all_lowercase);
		assertEquals(">sequence\nATCGATCG", check3.getSequence());

		// Input lower and upper 
		String mixed_case = "AtCgAtCg";
		Sequence check4 = new Sequence(mixed_case);
		assertEquals(">sequence\nATCGATCG", check4.getSequence());

		// Input with fasta header but no sequence 
		String header_no_newline = ">mysequence";
		try {
		    Sequence check5 = new Sequence(header_no_newline);
		    fail();
		} catch (IllegalArgumentException e) {
		}
		
		// No input
		String no_input = "";
		try {
			Sequence check5 = new Sequence(no_input);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
	}
	
	public void testresiduCheck() {
//		 Wrong residus
		String wrong_res1 = ">mysequence\nATzCATCG";
		try {
			Sequence check1 = new Sequence(wrong_res1);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
		
		String wrong_res2 = ">mysequence\nMRzkM";
		try {
			Sequence check2 = new Sequence(wrong_res2);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
	}
	
}
