package utilities;
import java.io.File;                  // File class
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;

public class Sequence {
  
	private String sequence;
	private File fastaFile;
	
	// constructor for sting input 
	public Sequence (String sequence) {
		this.sequence = sequence;
		verifySequence();
		sequenceToFile();
	}

	//constructor for FASTA file input 
	public Sequence (File fastaFile) {
		this.fastaFile = fastaFile;
		fileToSequence();
		verifySequence();
	}
	
	//accessor method for Sequence
	public String getSequence() {
	    return this.sequence;
	}
	
	public File getFastaFile() {
		return this.fastaFile;
	}
	
	//Writes the sequence instance variable to fastaFile instance variable
	private void sequenceToFile() {
		int filenum=2;
		File file = new File("project_data" + File.separator + "blast_input.fa");
		while(file.isFile()) {
		file = new File("project_data" + File.separator + "blast_input_" + filenum + ".fa");
		filenum++;
		}
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(this.sequence);
			this.fastaFile = file;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Invalid input");
		}	
	}
	
	//Writes the fastaFile instance variable to sequence instance variable
	private void fileToSequence() {
		String sequence;
		try {
			sequence = Files.readString(this.fastaFile.toPath());
		} catch (IOException e) {
			throw new IllegalArgumentException("Invalid input");
		}
		this.sequence = sequence;
	}
	
	
	//Check if entry is not empty
	private void isNotEmpty() {
		if (this.sequence.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}
	}
	
	//Check if FASTA entry has a header
	private void checkHeader() {
		char first_char = this.sequence.charAt(0);
		if (first_char != '>') {
			this.sequence = ">sequence\n" + this.sequence; //add header if no header present
		}
	}
		
	//Convert sequence to upper case 
	private void sequenceToUpperCase() {
		int start = this.sequence.indexOf('\n') + 1; //turn into separate  method 
		int end = this.sequence.length();
		String header = this.sequence.substring(0, start);
		this.sequence = this.sequence.substring(start, end).toUpperCase();
		this.sequence = header + this.sequence;

	}
	
	// Check if residues are valid for either protein or DNA alphabets
	private void checkSequenceElements() {
		int start = this.sequence.indexOf('\n') + 1;
		String residues = this.sequence.substring(start)
				.replaceAll("(?m)^>.*$", "")   // remove additional FASTA headers
				.replaceAll("\\s+", "");       // remove all whitespace

		if (residues.isEmpty()) {
			throw new IllegalArgumentException("Invalid input");
		}

		final String dnaAlphabet = "ACGTN";
		final String proteinAlphabet = "ACDEFGHIKLMNPQRSTVWYBXZJUO*";

		boolean isProtein = true;
		boolean isDna = true;

		for (char residue : residues.toCharArray()) {
			if (proteinAlphabet.indexOf(residue) == -1) {
				isProtein = false;
			}
			if (dnaAlphabet.indexOf(residue) == -1) {
				isDna = false;
			}
			if (!isProtein && !isDna) {
				throw new IllegalArgumentException("Invalid input");
			}
		}
	}
	
	private void verifySequence() {
		isNotEmpty();
		checkHeader();
		sequenceToUpperCase();
		checkSequenceElements();
		}
	}

		