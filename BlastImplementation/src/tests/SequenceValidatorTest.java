package tests;

import junit.framework.TestCase;
import utilities.SequenceValidator;

public class SequenceValidatorTest extends TestCase {
	
	
	public void testprepareSequence() {
		// Input without fasta header
		String without_header = "atcgatcg";
		SequenceValidator check1 = new SequenceValidator(without_header);
		assertEquals(">sequence\nATCGATCG", check1.getSequence());
		
		// Input with fasta header
		String with_header = ">mysequence\natcgatcg";
		SequenceValidator check2 = new SequenceValidator(with_header);
		assertEquals(">mysequence\nATCGATCG", check2.getSequence());

		// Input all lowercase
		String all_lowercase = "atcgatcg";
		SequenceValidator check3 = new SequenceValidator(all_lowercase);
		assertEquals(">sequence\nATCGATCG", check3.getSequence());

		// Input lower and upper 
		String mixed_case = "AtCgAtCg";
		SequenceValidator check4 = new SequenceValidator(mixed_case);
		assertEquals(">sequence\nATCGATCG", check4.getSequence());

		// Input with fasta header but no sequence 
		String header_no_newline = ">mysequence";
		try {
		    SequenceValidator check5 = new SequenceValidator(header_no_newline);
		    fail();
		} catch (IllegalArgumentException e) {
		}
		
		// No input
		String no_input = "";
		try {
			SequenceValidator check5 = new SequenceValidator(no_input);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
	}
	
	public void testresiduCheck() {
		// Wrong residus
		String wrong_res1 = ">mysequence\nATwCATCG";
		try {
			SequenceValidator check1 = new SequenceValidator(wrong_res1);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
	}
}
