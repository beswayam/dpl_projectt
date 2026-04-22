package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

/**
 * Utility class for parsing FASTA-formatted sequences into {@link Sequence} objects.
 * 
 * <p>Supports parsing from both a {@link File} and a raw {@link String} input.
 * Multiple sequences in a single FASTA input are supported.
 */
public class MultipleSequenceParser {

	/**
	 * Reads a FASTA file and parses its contents into a list of {@link Sequence} objects.
	 * 
	 * @param sequencefile the FASTA file containing one or more sequences
	 * @return a list of parsed {@link Sequence} objects
	 */
	
	public MultipleSequenceParser() {
		
	}
	
	public ArrayList<Sequence> parseMultipleSeqs(File sequencefile) {
		String sequences = "";
		try {
			sequences = Files.readString(sequencefile.toPath());
		} catch (IOException e) {
			JOptionPane.showMessageDialog(new JOptionPane(), "Failed to find input fasta file", "input Error",
					JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return parseMultipleSeqs(sequences);

	}

	/**
	 * Parses a FASTA-formatted string into a list of {@link Sequence} objects.
	 * 
	 * <p>Each sequence is expected to start with a header line beginning with ">".
	 * Sequences are split based on these headers.
	 * 
	 * @param sequences a string containing one or more FASTA-formatted sequences
	 * @return a list of parsed {@link Sequence} objects
	 * @throws IllegalArgumentException if the input FASTA format is invalid 
	 */
	public ArrayList<Sequence> parseMultipleSeqs(String sequences) {
		String[] lines = sequences.split("\\r?\\n");
		ArrayList<Sequence> seqList = new ArrayList<Sequence>();
		String oneSeq = "";
		for (int i = 0; i < lines.length; i++) {
			if (lines[i].startsWith(">") && oneSeq != "") {
				seqList.add(new Sequence(oneSeq.trim()));
				oneSeq = "";
			}
			oneSeq += lines[i] + "\n";
		}
		seqList.add(new Sequence(oneSeq.trim()));
		
		return seqList;
	}
}
