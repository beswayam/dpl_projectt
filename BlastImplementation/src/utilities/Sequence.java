package utilities;

import java.io.File; // File class
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

/**
 * The Sequence class represents a biological sequence (DNA or protein).
 * 
 * <p>
 * It supports input either as a raw string or from a FASTA file. The class
 * automatically validates the entry and can exports/imports it from a FASTA
 * file.
 */
public class Sequence {

	/** The biological sequence as a string */
	private String sequence;

	/** The FASTA file associated with this sequence. */
	private File fastaFile;

	/**
	 * Constructs a Sequence object from a raw sequence string.
	 * 
	 * <p>
	 * The sequence is validated and written to a FASTA file.
	 * 
	 * @param sequence the raw biological sequence
	 */
	public Sequence(String sequence) {
		this.sequence = sequence;
		verifySequence();
		sequenceToFile();
	}

	/**
	 * Constructs a sequence object from a FASTA file.
	 * 
	 * <p>
	 * The file is read, converted into a sequence string, and validated.
	 * 
	 * @param fastaFile the FASTA file containing the sequence
	 */
	public Sequence(File fastaFile) {
		this.fastaFile = fastaFile;
		fileToSequence();
		verifySequence();
	}

	/**
	 * Returns the full sequence string.
	 * 
	 * @return the sequence as a string
	 */
	public String getSequence() {
		return this.sequence;
	}

	/**
	 * Returns the FASTA file associated with this sequence.
	 * 
	 * @return the FASTA file
	 */
	public File getFastaFile() {
		return this.fastaFile;
	}

	/**
	 * Sets the sequence string.
	 * 
	 * @param sequence the new sequence value
	 */
	public void setSequence(String sequence) {
		this.sequence = sequence;
	}

	/**
	 * Sets the FASTA file
	 * 
	 * @param fastaFile the FASTA file to associate
	 */
	public void setFastaFile(File fastaFile) {
		this.fastaFile = fastaFile;
	}

	/**
	 * Returns the sequence without its FASTA header.
	 * 
	 * @return the sequence without its FASTA header
	 */
	public String getSequenceNoHeader() {
		int start = this.sequence.indexOf('\n') + 1;
		int end = this.sequence.length();
		return this.sequence.substring(start, end);
	}

	/**
	 * Writes the current sequence to a uniquely named FASTA file.
	 * 
	 * <p>
	 * If a file already exists, a new numbered file is created.
	 */
	private void sequenceToFile() {
		int filenum = 2;
		File file = new File("project_data" + File.separator + "blast_input.fa");
		while (file.isFile()) {
			file = new File("project_data" + File.separator + "blast_input_" + filenum + ".fa");
			filenum++;
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(this.sequence);
			this.fastaFile = file;
		} catch (IOException e) {
			throw new IllegalArgumentException("Could not write the sequence to an output file");
		}
	}

	/**
	 * Reads the FASTA file and converts it into a sequence string.
	 */
	private void fileToSequence() {
		String sequence;
		try {
			sequence = Files.readString(this.fastaFile.toPath());
		} catch (IOException e) {
			throw new IllegalArgumentException("The selected FASTA file could not be read");
		}
		this.sequence = sequence;
	}

	/**
	 * Checks that the sequence is not empty.
	 * 
	 * @throws IllegalArgumentException if the sequence is empty
	 */
	private void isNotEmpty() {
		if (this.sequence.isEmpty()) {
			throw new IllegalArgumentException("Entry is empty");
		}
	}

	/**
	 * Ensures that the sequence has a FASTA header.
	 * 
	 * <p>
	 * If no header is present, a default header is added.
	 */
	private void checkHeader() {
		char first_char = this.sequence.charAt(0);
		if (first_char != '>') {
			this.sequence = ">sequence\n" + this.sequence; // add header if no header present
		}
	}

	/**
	 * Converts the sequence body to uppercase letters.
	 */
	private void sequenceToUpperCase() {
		int start = this.sequence.indexOf('\n') + 1; // turn into separate method
		int end = this.sequence.length();
		String header = this.sequence.substring(0, start);
		this.sequence = this.sequence.substring(start, end).toUpperCase();
		this.sequence = header + this.sequence;

	}

	/**
	 * 
	 */
	private void checkSequenceElements() {
		int start = this.sequence.indexOf('\n') + 1;
		String header = this.sequence.substring(0, start);
		String residues = this.sequence.substring(start).replaceAll("(?m)^>.*$", "") // remove additional FASTA headers
				.replaceAll("\\s+", ""); // remove all whitespace

		if (residues.isEmpty()) {
			throw new IllegalArgumentException("Header found, but does not contain sequence");

		}

		this.sequence = header + residues;
	}

	public boolean isProtein() {
		int start = this.sequence.indexOf('\n') + 1;
		String residues = this.sequence.substring(start);

		final String dnaAlphabet = "ACGTN";
		final String proteinAlphabet = "ACDEFGHIKLMNPQRSTVWYBXZJUO*";

		boolean isProtein = false;

		for (char residue : residues.toCharArray()) {
			if (proteinAlphabet.indexOf(residue) == -1) {
				throw new IllegalArgumentException("Invalid aminoacid/nucleotide used: " + residue);
			}
			if (dnaAlphabet.indexOf(residue) == -1) {
				isProtein = true;
			}
		}

		return isProtein;
	}

	private void verifySequence() {
		isNotEmpty();
		checkHeader();
		sequenceToUpperCase();
		checkSequenceElements();
		isProtein();
	}
}
