package tests;

import junit.framework.TestCase;

import java.io.File;
import java.util.ArrayList;

import gui.BlastOutputGuiFunctions;

public class BlastOutputGuiFunctionsTest extends TestCase {

	public void testReadBlastTsv() {
		String path = "project_data" + File.separator + "test_blastoutput.tsv";
		File file = new File(path);

		// Input that needs to be parsed from the file
		// For simplicity we check the second and last item
		String id = "P68873";
		String matchEnd = "147";

		BlastOutputGuiFunctions parser = new BlastOutputGuiFunctions();
		ArrayList<String[]> hits = parser.readBlastTsv(file);
		String[] firstHit = hits.get(0);
		assertEquals(id, firstHit[1]);
		assertEquals(matchEnd, firstHit[11]);
	}

	// parseHit was tested by observing the GUI output of the subclasses after
	// running Blastp.
}
