package gui;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;


/**
 * Utility class providing helper methods for processing and displaying
 * BLAST output data in a GUI.
 *
 * <p>This class is responsible for:
 * <ul>
 *   <li>Reading BLAST results from a TSV (tab-separated values) file</li>
 *   <li>Parsing individual BLAST hits</li>
 *   <li>Checking which parts of two given sequences align</li>
 * </ul>
 *
 * <p>The parsed data is displayed through a list of {@link JLabel} components
 * maintained in {@code labelList}.
 */
public class BlastOutputGuiFunctions extends JFrame {
	private static final long serialVersionUID = 1L;
	
	/** List of parsed BLAST hits, where each hit is represented as a String array. */
	protected ArrayList<String[]> hits;
	
	/** List of labels used to display BLAST output values in the GUI. */
	protected ArrayList<JLabel> labelList = new ArrayList<JLabel>();

	/**
	 * Constructs a BlastOutputGuiFunctions object.
	 */
	public BlastOutputGuiFunctions( ) {
	}
	
	/**
	 * Reads a BLAST output TSV file and extracts all hits.
	 *
	 * <p>The first line of the file is assumed to be a header and is skipped.
	 * Each subsequent line is split on tab characters and stored as a String array.
	 *
	 * @param file the BLAST TSV file to read
	 * @return a list of BLAST hits, each represented as a String array
	 */
	public ArrayList<String[]> readBlastTsv(File file) {
		ArrayList<String[]> hits = new ArrayList<String[]>();
		try (Scanner myReader = new Scanner(file)) {
			myReader.nextLine();
			while (myReader.hasNextLine()) {
				String data = myReader.nextLine();
				String[] dataArray = data.split("\t");
				hits.add(dataArray);
			}
		} catch (FileNotFoundException e) {
			JOptionPane.showMessageDialog(this, "Temporary BLAST output tsv " + file + " not found", "File Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return hits;
	}

	/**
	 * Parses a single BLAST hit and updates the GUI labels with its data.
	 *
	 * <p>The expected format of {@code hitdata} is:
	 * <pre>
	 * [0] hit number
	 * [1] UniProt ID
	 * [2] description
	 * [3] match sequence
	 * [4] E-value
	 * [5] bit score
	 * [6] identity percentage
	 * [7] query sequence
	 * [8] query start
	 * [9] query end
	 * [10] match start
	 * [11] match end
	 * </pre>
	 *
	 * <p>The method updates the labels in {@code labelList} with formatted
	 * alignment and metadata.
	 *
	 * @param hitdata String array containing BLAST hit information
	 */
	public void parseHit(String[] hitdata) {
		String id = hitdata[1];
		String description = hitdata[2];
		String match_seq = hitdata[3];
		String eval = hitdata[4];
		String bitscore = hitdata[5];
		String identity = hitdata[6];
		String query_seq = hitdata[7];
		String query_start = hitdata[8];
		String query_end = hitdata[9];
		String match_start = hitdata[10];
		String match_end = hitdata[11];
		String matches = findMatches(query_seq, match_seq);
		labelList.get(0).setText(id);
		labelList.get(1).setText(description);
		labelList.get(2).setText("<html>" + query_seq.replaceAll(" ", "\u00a0") + "<br>" + matches + "<br>"
				+ match_seq.replaceAll(" ", "\u00a0") + "</html>");
		labelList.get(3).setText(
				"<html>(" + query_start + ":" + query_end + ")<br><br>(" + match_start + ":" + match_end + ")</html>");
		labelList.get(4).setText(eval);
		labelList.get(5).setText(bitscore);
		labelList.get(6).setText(identity + "%");

	}

	/**
	 * Generates a visual alignment string indicating matching positions
	 * between two sequences.
	 *
	 * <p>Matching characters are represented by {@code '|'}, while mismatches
	 * are represented by non-breaking spaces ({@code &nbsp}) for HTML rendering.
	 *
	 * @param seq1 the query sequence
	 * @param seq2 the matched sequence
	 * @return a string representing alignment matches for HTML display
	 */
	private String findMatches(String seq1, String seq2) {
		int seqlen = seq1.length();
		String matches = "";
		for (int i = 0; i < seqlen; i++) {
			if (seq1.charAt(i) == seq2.charAt(i)) {
				matches += "|";
			} else {
				matches += "&nbsp";
			}
		}
		return matches;
	}

}