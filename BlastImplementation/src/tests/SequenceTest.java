package tests;

import junit.framework.TestCase;
import java.io.File;

import utilities.Sequence;

public class SequenceTest extends TestCase {
	
	
	public void testInstantiateSequenceString() {
		
		//Input without fasta header
		String without_header = "atcgatcg";
		Sequence check1 = new Sequence(without_header);
		assertEquals(">sequence\nATCGATCG", check1.getSequence());
		
		//Input with fasta header
		String with_header = ">sequence\natcgatcg";
		Sequence check2 = new Sequence(with_header);
		assertEquals(">sequence\nATCGATCG", check2.getSequence());

		//Input all lowercase
		String all_lowercase = "atcgatcg";
		Sequence check3 = new Sequence(all_lowercase);
		assertEquals(">sequence\nATCGATCG", check3.getSequence());

		//Input lower and upper 
		String mixed_case = "AtCgAtCg";
		Sequence check4 = new Sequence(mixed_case);
		assertEquals(">sequence\nATCGATCG", check4.getSequence());

		//Input with fasta header but no sequence 
		String header_no_newline = ">mysequence";
		try {
		    Sequence check5 = new Sequence(header_no_newline);
		    fail();
		} catch (IllegalArgumentException e) {
		}
		
		//No input
		String no_input = "";
		try {
			Sequence check6 = new Sequence(no_input);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
		
		//Input with invalid amino acid
		String wrong_res = ">mysequence\nMRzkM";
		try {
			Sequence check7 = new Sequence(wrong_res);
			fail();
			} 
		catch (IllegalArgumentException e) {
		 	}
	}
	
	public void testInstantiateSequenceFile() {
		File fileName = new File("BlastImplementation/Data/test_fa.txt");
		Sequence check8 = new Sequence(fileName);
		assertNotNull(check8);
	}
}
