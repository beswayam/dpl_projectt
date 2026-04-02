package utilities;
import java.io.File;                  // File class

import java.io.FileNotFoundException; // Import this class to handle errors
import java.io.FileWriter;
import java.io.IOException;

public class Sequence {
  
	private String sequence;
	private File fastaFile;
	
	// constructor for sting input 
	public Sequence (String sequence) {
		this.sequence = sequence;
		isNotEmpty();
		checkHeader();
		checkSequence();
		sequenceToUpperCase();
		checkSequenceElements();
		sequenceToFile();
	}
	
	//Writes the sequence instance variable to fastaFile instance variable
	private void sequenceToFile() {
		File file = new File("data" + File.separator + "blast_input.fa");
		
		try (FileWriter writer = new FileWriter(file)) {
			writer.write(this.sequence);
			this.fastaFile = file;
		}
		catch (IOException e) {
			throw new IllegalArgumentException("Invalid input");
		}
		
	}

	//constructor for FASTA file input 
	public Sequence (File fastaFile) {
		this.fastaFile = fastaFile;
		//verifyFile();
	}
	
	//accessor method for Sequence
	public String getSequence() {
	    return this.sequence;
	}
	
	public File getFastaFile() {
		return this.fastaFile;
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
	
	//Check if FASTA entry has a sequence
	private void checkSequence() {
		if (!this.sequence.contains("\n")) {
		    throw new IllegalArgumentException("Invalid input");
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
	
	//Check if all amino acids in sequence have valid letter
	private void checkSequenceElements() {
		int start = this.sequence.indexOf('\n') + 1;
		String residues = this.sequence.substring(start).replaceAll("\\n", "").trim(); //remove \n in sequence
		for (char residue : residues.toCharArray()) {
			if ("ACDEFGHIKLMNPQRSTVWY".indexOf(residue) == -1) {
				throw new IllegalArgumentException("Invalid input");
			}
		}	
		}
	
	//private void verifyFile() {
		
	//}
	}

		
	 