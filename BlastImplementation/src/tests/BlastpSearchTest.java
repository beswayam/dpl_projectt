
package tests;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

import javax.swing.JOptionPane;

import gui.BlastGui;
import gui.BlastOutputGui;
import utilities.BlastpSearch;
import utilities.Sequence;
import junit.framework.TestCase;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastResult;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtHit;


public class BlastpSearchTest extends TestCase {
	private static BlastpSearch blastpsearch = new BlastpSearch();
	
	public void testBlastP() {
	Sequence sequence = new Sequence(">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
			+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
			+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
			+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
			+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n"
			+ "FqknPvtaq");
	
	blastpsearch.setSequence(sequence);
	blastpsearch.runUniprotBlast();
	BlastResult<UniProtHit> uniprotBlastResult=blastpsearch.getblastResult();
	assertNotNull(uniprotBlastResult);
	}

	public void testBlastToFile() {
	
		Sequence sequence = new Sequence(">seq1\nnQktalhdPITtiAMtGdeGeIkIMlelypnkVHIyKQPETqqqHysaIitWYGtGldAf\r\n"
				+ "TAedLdSriENLLekDqFTVePLSVdtdCnPHqKNkFrCnGvgIDHRllNNesnqgKIwA\r\n"
				+ "WwCsaFNCATNPDDQvkCKKvGaadQsAnfTtvLeWvPWvirikKgYykEMvNvpkkkPV\r\n"
				+ "TmMTYRQrIttKiEasTnvSgQttfhFQtvaDFgeCfNCrWitCqntEgLKkQqHWKlrR\r\n"
				+ "SCdGsafyncFEGayELtlQyLQnnrqffScHCagPvdfPfCGDaSvFCPQdiGArrQWs\r\n"
				+ "FqknPvtaq");
		
		blastpsearch.setSequence(sequence);
		blastpsearch.runUniprotBlast();
		float mineval = 0.00000000000000000001f;
		int maxseq = 10;
		File file = new File("project_data"+File.separator+"temp_output.tsv");
		blastpsearch.writeUniprotBlastOutput(mineval,maxseq,file);
		
        FileReader fileReader=null;
        try
        {
            fileReader = new FileReader(file);
        }
        catch (FileNotFoundException fe)
        {
		    JOptionPane.showMessageDialog(new JOptionPane(), 
		    		"Failed to open output file", 
	                "Output Error", 
	                JOptionPane.ERROR_MESSAGE);
		    fe.printStackTrace();
        }
        try {
			System.out.println(fileReader.read());
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(new JOptionPane(), 
		    		"Failed to open output file", 
	                "Output Error", 
	                JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		
        try {
			assertNotNull(fileReader.read());
		} catch (IOException e) {
		    JOptionPane.showMessageDialog(new JOptionPane(), 
		    		"Failed to open output file", 
	                "Output Error", 
	                JOptionPane.ERROR_MESSAGE);			
			e.printStackTrace();
		}
	}
	
	
	}