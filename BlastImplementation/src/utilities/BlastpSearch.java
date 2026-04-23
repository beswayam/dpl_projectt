package utilities;

import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.ServiceFactory;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.*;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.input.DatabaseOption;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Utility class for performing BLASTP searches using the UniProt web service.
 * 
 * <p>
 * The class allows setting a query {@link Sequence}, running a BLASTP search
 * against the UniProt Swiss-Prot database, and writing results to a file.
 */
public class BlastpSearch {
	private Sequence sequence;
	private BlastResult<UniProtHit> blastResult;

	/**
	 * Sets the query sequence to be used for the BLASTP search.
	 *
	 * @param sequence the {@link Sequence} object containing the query sequence
	 */
	public void setSequence(Sequence sequence) {
		this.sequence = sequence;
	}

	/**
	 * Returns the result of the most recent BLASTP search.
	 *
	 * @return the {@link BlastResult} containing {@link UniProtHit} objects
	 */
	public BlastResult<UniProtHit> getBlastResult() {
		return (this.blastResult);
	}

	/**
	 * Executes a BLASTP search against the UniProt Swiss-Prot database.
<<<<<<< HEAD
	 * <p>
	 * The query sequence must be set beforehand using
	 * {@link #setSequence(Sequence)}. Results are stored internally and can be
	 * accessed via {@link #getblastResult()}.
=======
	 * <p>The query sequence must be set beforehand using {@link #setSequence(Sequence)}.
	 * Results are stored internally and can be accessed via {@link #getBlastResult()}.
>>>>>>> 7a9f90afe7eb87bfd7c7e64c2497ae7b1f71e5c1
	 * <p>
	 * Displays an error dialog if the search fails or is interrupted.
	 */
	public void runUniprotBlast() {
		ServiceFactory serviceFactoryInstance = Client.getServiceFactoryInstance();
		UniProtBlastService uniProtBlastService = serviceFactoryInstance.getUniProtBlastService();
		BlastInput input = new BlastInput.Builder(DatabaseOption.SWISSPROT, this.sequence.getSequence()).build();
		CompletableFuture<BlastResult<UniProtHit>> resultFuture = uniProtBlastService.runBlast(input);
		try {
			this.blastResult = resultFuture.get();

		} catch (ExecutionException e) {
			JOptionPane.showMessageDialog(new JOptionPane(), "BLAST aborted, please check your internet connection",
					"BLAST Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (InterruptedException e) {
			JOptionPane.showMessageDialog(new JOptionPane(), "BLAST interrupted", "BLAST Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
	}

	/**
	 * Writes filtered BLASTP results to a tab-separated output file.
	 * <p>
	 * Only hits with an E-value below the specified threshold are included, up to a
	 * maximum number of sequences.
	 *
<<<<<<< HEAD
	 * @param mineval the maximum E-value threshold for including hits
	 * @param maxseq  the maximum number of hits to write
	 * @param file    the output file to write results to
=======
	 * @param minEval the maximum E-value threshold for including hits
	 * @param maxSeq the maximum number of hits to write
	 * @param file the output file to write results to
>>>>>>> 7a9f90afe7eb87bfd7c7e64c2497ae7b1f71e5c1
	 */
	public void writeUniprotBlastOutput(float minEval, int maxSeq, File file) {
		try {
			FileWriter myWriter = new FileWriter(file);
			myWriter.write(
					"hit_num\tuniprot_ID\tdescription\tsequence\te-value\tbit-score\tidentity\tquery_seq\tquery_start\tquery_end\tmatch_start\tmatch_end\n");
			myWriter.close();
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JOptionPane(), "failed to write output to file", "output Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		int numseq = 0;
		for (UniProtHit hit : this.blastResult.hits()) {
			UniProtBlastSummary summary = hit.getSummary();
			Alignment alignment = summary.getAlignments().get(0);
			double eval = alignment.getExpectation();
			if (eval < minEval) {
				if (numseq != maxSeq) {
					String accession = hit.getEntry().getPrimaryUniProtAccession().getValue();
					String description = summary.getDescription();
					String sequence = alignment.getMatchSeq();
					float bitscore = alignment.getBitScore();
					float identity = alignment.getIdentity();
					String query_seq = alignment.getQuerySeq();
					int query_start = alignment.getStartQuerySeq();
					int query_end = alignment.getEndQuerySeq();
					int match_start = alignment.getStartMatchSeq();
					int match_end = alignment.getEndMatchSeq();
					numseq++;
					try {
						FileWriter myWriter = new FileWriter(file, true);
						myWriter.append(numseq + "\t" + accession + "\t" + description + "\t" + sequence + "\t" + eval
								+ "\t" + bitscore + "\t" + identity + "\t" + query_seq + "\t" + query_start + "\t"
								+ query_end + "\t" + match_start + "\t" + match_end + "\n");
						myWriter.close();
					} catch (IOException e) {
						JOptionPane.showMessageDialog(new JOptionPane(), "failed to write output to file",
								"output Error", JOptionPane.ERROR_MESSAGE);
						e.printStackTrace();
					}
				}
			}
		}

	}
}