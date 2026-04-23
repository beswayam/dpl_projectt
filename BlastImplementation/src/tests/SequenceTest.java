package tests;

import junit.framework.TestCase;
import java.io.File;

import utilities.Sequence;

public class SequenceTest extends TestCase {

	// Remove any pre-existing files
	public void setUp() {
		new File("project_data/blast_input.fa").delete();
		for (int i = 1; i <= 1000; i++) {
			new File("project_data/blast_input_" + i + ".fa").delete();
		}
	}

	public void testCheckHeader() {
		// Input without fasta header
		String withoutHeader = "atcgatcg";
		Sequence check1 = new Sequence(withoutHeader);
		assertEquals(">sequence\nATCGATCG", check1.getSequence());
	}

	public void testSequenceToFile() {
		String withoutHeader = "atcgatcg";
		Sequence check1 = new Sequence(withoutHeader);
		assertEquals("project_data" + File.separator + "blast_input.fa", check1.getFastaFile().getPath());

	}

	public void testSequenceToUpperCase() {
		// Input with fasta header
		String withHeader = ">sequence\natcgatcg";
		String mixedCase = ">sequence\nAtCgAtCg";
		Sequence check2 = new Sequence(withHeader);
		Sequence check3 = new Sequence(mixedCase);
		assertEquals(">sequence\nATCGATCG", check2.getSequence());
		assertEquals(">sequence\nATCGATCG", check3.getSequence());
	}

	public void testCheckSequenceElements() {
		// Input with fasta header but no sequence
		String header_no_newline = ">mysequence";
		try {
			Sequence check5 = new Sequence(header_no_newline);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testIsNotEmpty() {
		// No input
		String no_input = "";
		try {
			Sequence check6 = new Sequence(no_input);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testIsProtein() {
		// Input with invalid amino acid
		String wrong_res = ">mysequence\nMRzkMou7b";
		try {
			Sequence check7 = new Sequence(wrong_res);
			fail();
		} catch (IllegalArgumentException e) {
		}
	}

	public void testFileToSequence() {
		// Check constructor that takes a File argument
		File fileName = new File("project_data/test_fa.txt");
		Sequence check8 = new Sequence(fileName);
		String expected = ">tr|A0A222AH43|A0A222AH43_9HYST Hemoglobine alpha globin subunit (Fragment) OS=Ctenomys rionegrensis OX=88126 GN=HBA PE=3 SV=1\n"
				+ "MVLSPADKTNVKAAWDKIGSHGAEYGAEALFRMFLSFPTTKTYFHHFDLSPGSAQVKAHG"
				+ "KKVSDALTTAVGHLDDLPSALSALSDLHAHKLRVDPVNFKLLSHCLLVTLSLHHPAEFTP" + "AVHASLDKFLATVSTVLTS";
		assertNotNull(check8);
		assertEquals(expected.replace("\r\n", "\n"), check8.getSequence().replace("\r\n", "\n"));

	}
}
