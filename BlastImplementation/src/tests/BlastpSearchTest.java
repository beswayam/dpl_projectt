
package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

import javax.swing.JOptionPane;

import utilities.BlastpSearch;
import utilities.Sequence;
import junit.framework.TestCase;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastResult;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtHit;

public class BlastpSearchTest extends TestCase {
	private BlastpSearch blastpsearch = new BlastpSearch();

	public void testBlastP() {
		Sequence sequence = new Sequence(">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
				+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
				+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
				+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
				+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n" + "FqknPvtaq");

		blastpsearch.setSequence(sequence);
		blastpsearch.runUniprotBlast();
		BlastResult<UniProtHit> uniprotBlastResult = blastpsearch.getBlastResult();
		assertNotNull(uniprotBlastResult);
	}

	public void testBlastToFile() {

		Sequence sequence = new Sequence(">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
				+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
				+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
				+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
				+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n" + "FqknPvtaq");

		blastpsearch.setSequence(sequence);
		blastpsearch.runUniprotBlast();
		float mineval = 0.00000000000000000001f;
		int maxseq = 10;
		File file = new File("project_data" + File.separator + "temp_output.tsv");
		blastpsearch.writeUniprotBlastOutput(mineval, maxseq, file);

		try (Scanner blastOutputTsv = new Scanner(file)) {
			String header;
			header = blastOutputTsv.nextLine();
			assertEquals(
					"hit_num\tuniprot_ID\tdescription\tsequence\te-value\tbit-score\tidentity\tquery_seq\tquery_start\tquery_end\tmatch_start\tmatch_end",
					header);

		} catch (FileNotFoundException fe) {
			JOptionPane.showMessageDialog(new JOptionPane(), "Failed to open output file", "Output Error",
					JOptionPane.ERROR_MESSAGE);
			fe.printStackTrace();
		}
	}
}