package utilities;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;

import javax.swing.JOptionPane;

/**
 * The Ssearch36Search class provides an interface for running the
 * SSEARCH36 sequence alignment tool and parsing its output.
 *
 * It supports both protein and nucleotide searches and converts
 * raw alignment output into a structured TSV format.
 */
public class Ssearch36Search {
	private Sequence sequence;
	private File ssearchresult;
	private int errorCode;
	private boolean protein;
	private String matrixFlag;
	
	/**
     * Mapping between full matrix names and SSEARCH short flags.
     */
	private String[][] matrixMap = { { "BLOSUM45", "BL45" }, { "BLOSUM50", "BL50" }, { "BLOSUM62", "BL62" },
			{ "BLOSUM80", "BL80" }, { "BLOSUM90", "BL90" }, { "PAM30", "P30" }, { "PAM70", "P70" },
			{ "PAM250", "P250" } };

    /**
     * Constructs an Ssearch36Search instance.
     *
     * @param protein true if the search is for protein sequences,
     *                false for nucleotide sequences
     */
    public Ssearch36Search(boolean protein) {
		this.protein=protein;
		

	}
    
    /**
     * Sets the substitution matrix used for protein searches.
     *
     * @param displayName the full name of the matrix (e.g. BLOSUM62)
     */
	public void setMatrixFlag(String displayName) {
		for (String[] mapping : matrixMap) {
			if (mapping[0].equals(displayName)) {
				matrixFlag = mapping[1];
			}
		}
	}

	/**
	 * Runs the local ssearch36 executable with the selected options.
	 */

	/**
     * Sets the query sequence to be used in the search.
     *
     * @param sequence the Sequence object containing the query data
     */
    public void setSequence(Sequence sequence) {
    	this.sequence = sequence;
    } 
    
    /**
     * Returns the exit/error code from the SSEARCH execution.
     *
     * @return the process exit code
     */
    public int getErrorCode() {
    	return this.errorCode;
    }
    
    /**
     * Executes the SSEARCH36 alignment tool with the specified parameters.
     *
     * <p>The method selects the correct executable based on the operating system,
     * builds the command, runs the process, and stores the output file.</p>
     *
     * @param dbFile the database file to search against
     * @param evalue the E-value threshold
     * @param maxSeqs maximum number of sequences to report
     * @param outputPath path where the output file will be written
     * @throws Exception if the process fails or cannot be executed
     */
    public void run(File dbFile, String evalue,
        String maxSeqs, String outputPath) 
        throws Exception {
        String osName = System.getProperty("os.name");
        String exeName = osName != null && osName.toLowerCase().contains("win") ? "ssearch36.exe" : "ssearch36";
        String ssearchExe = "tools" + File.separator + exeName;
        File queryFile = this.sequence.getFastaFile();
        ProcessBuilder pb;
        if(this.protein) {
        	pb = new ProcessBuilder(
                ssearchExe,
                "-p",
                "-s", matrixFlag,
                "-E", evalue,
                "-b", maxSeqs,
                "-d", maxSeqs,
                queryFile.getPath(),
                dbFile.getPath());
        pb.redirectErrorStream(true);
        pb.redirectOutput(new File(outputPath));}
        else {
        	pb = new ProcessBuilder(
                    ssearchExe,
                    "-n",
                    "-E", evalue,
                    "-b", maxSeqs,
                    "-d", maxSeqs,
                    queryFile.getPath(),
                    dbFile.getPath());
            pb.redirectErrorStream(true);
            pb.redirectOutput(new File(outputPath));}
        
        Process p = pb.start();
        this.errorCode = p.waitFor();
        this.ssearchresult = new File(outputPath);
    }
    
    /**
     * Parses the SSEARCH output file and converts it into a TSV file.
     *
     * <p>The output includes hit information such as ID, description,
     * alignment sequences, E-value, bit score, identity, and alignment
     * coordinates.
     *
     * @param outfile the file where TSV results will be written
     */

	public void parseBlastCustomDatabase(File outfile) {
		// Claude generated this parser code for us based on a non functional template
		// made by us.
		// We checked that the code works correctly.
		// Part of the code was not written by Claude, this segment is labelled as such

		File blastOutputCustomDatabase = this.ssearchresult;
		String pathBlastFile = blastOutputCustomDatabase.getPath();
		try (Scanner blastOutput = new Scanner(blastOutputCustomDatabase)) {

			FileWriter blastOutputTsv = new FileWriter(outfile, false);
			blastOutputTsv.write("hit\tid\tdescription\tmatch_sequence\teval\tbitscore\tidentity\t"
					+ "query_sequence\tquery_start\tquery_end\tmatch_start\tmatch_end\n");

			int hitNum = 0;
			String line = "";
			while (blastOutput.hasNextLine()) {

				if (line.startsWith(">>")) {
					hitNum++;

					String[] pipeParts = line.split("\\|");
					String id = "";
					String description = "";
					if (pipeParts.length > 2) {
						id = pipeParts[1].trim();
						description = pipeParts[2].trim();
					} else {
						id = pipeParts[0].trim();
						description = "n/a";
					}

					String scoreLine = blastOutput.nextLine().trim();
					String bitScore = scoreLine.split("bits:")[1].trim().split(" ")[0];
					String eval = scoreLine.split("E\\(")[1].split("\\):")[1].trim();

					String swLine = blastOutput.nextLine().trim();
					String identity = swLine.split(";")[1].trim().split("%")[0].trim();
					String positions = swLine.substring(swLine.lastIndexOf("(") + 1).replace(")", "").trim();
					String queryRange = positions.split(":")[0];
					String matchRange = positions.split(":")[1];
					String queryStart = queryRange.split("-")[0];
					String queryEnd = queryRange.split("-")[1];
					String matchStart = matchRange.split("-")[0];
					String matchEnd = matchRange.split("-")[1];

					// This section was not generated by Claude
					String querySeq = "";
					String matchSeq = "";

					line = blastOutput.nextLine();
					line = blastOutput.nextLine();

					while (blastOutput.hasNextLine()) {
						line = blastOutput.nextLine();
						querySeq += line.substring(7).replaceAll("[\\r\\n]+", "");
						line = blastOutput.nextLine();
						line = blastOutput.nextLine();
						matchSeq += line.substring(7).replaceAll("[\\r\\n]+", "");
						line = blastOutput.nextLine();
						line = blastOutput.nextLine();
						line = blastOutput.nextLine();
						if (line.trim().isEmpty() == true) {
							break;
						}
						if (line.trim().substring(0, 1).compareTo(">") == 0) {
							break;
						}

					}

					blastOutputTsv.write(hitNum + "\t" + id + "\t" + description + "\t" + matchSeq + "\t" + eval + "\t"
							+ bitScore + "\t" + identity + "\t" + querySeq + "\t" + queryStart + "\t" + queryEnd + "\t"
							+ matchStart + "\t" + matchEnd + "\t" + "\n");
				} else {
					line = blastOutput.nextLine();
				}
			}
			blastOutputTsv.close();

		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(null, "Could not find the SSEARCH results file at:\n" + pathBlastFile,
					"File Not Found", JOptionPane.ERROR_MESSAGE);
		} catch (IOException e) {
			JOptionPane.showMessageDialog(null, "Failed to write output to TSV file.", "Output Error",
					JOptionPane.ERROR_MESSAGE);
		}
	}
}