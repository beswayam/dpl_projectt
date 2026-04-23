package tests;

import junit.framework.TestCase;
import java.io.File;

import utilities.Sequence;

public class SequenceTest extends TestCase {

	public void setUp() {
		new File("project_data/blast_input.fa").delete();
		for (int i = 1; i <= 10; i++) {
			new File("project_data/blast_input_" + i + ".fa").delete();
		}
	}

	public void testInstantiateSequenceString() {

		// Input without fasta header
		String withoutHeader = "atcgatcg";
		Sequence check1 = new Sequence(withoutHeader);
		assertEquals(">sequence\nATCGATCG", check1.getSequence());
		assertEquals("project_data" + File.separator + "blast_input.fa", check1.getFastaFile().getPath());

		// Input with fasta header
		String with_header = ">sequence\natcgatcg";
		Sequence check2 = new Sequence(with_header);
		assertEquals(">sequence\nATCGATCG", check2.getSequence());

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
			Sequence check6 = new Sequence(no_input);
			fail();
		} catch (IllegalArgumentException e) {
		}

		// Input with invalid amino acid
		String wrong_res = ">mysequence\nMRzkMou7b";
		try {
			Sequence check7 = new Sequence(wrong_res);
			fail();
		} catch (IllegalArgumentException e) {
		}

	}

	public void testInstantiateSequenceFile() {
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
