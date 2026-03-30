
package tests;


import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import utilities.BlastpSearch;
import junit.framework.TestCase;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastResult;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtHit;


public class BlastpSearchTest extends TestCase {
	public void testBlastP() {
	String sequence = ">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
			+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
			+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
			+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
			+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n"
			+ "FqknPvtaq";
	BlastResult<UniProtHit> uniprotBlastResult = BlastpSearch.runUniprotBlast(sequence);
	assertNotNull(uniprotBlastResult);
	}

	public void testBlastToFile() {
	
		String sequence = ">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
				+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
				+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
				+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
				+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n"
				+ "FqknPvtaq";
		float mineval = 0.00000000000000000001f;
		int maxseq = 10;
		String filename = "temp_output.tsv";
		BlastResult<UniProtHit> uniprotblastResult = BlastpSearch.runUniprotBlast(sequence);
		BlastpSearch.writeUniprotBlastOutput(uniprotblastResult,mineval,maxseq,filename);
		
        FileReader fileReader=null;
        try
        {
            fileReader = new FileReader(filename);
        }
        catch (FileNotFoundException fe)
        {
            System.out.println("File not found");
        }
        try {
			System.out.println(fileReader.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
		
        try {
			assertNotNull(fileReader.read());
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
	}