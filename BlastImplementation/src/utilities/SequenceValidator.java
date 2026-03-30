package utilities;
import java.io.File;                  // File class
import java.util.Scanner; 
import java.io.FileNotFoundException; // Import this class to handle errors
import java.nio.file.Path;
import java.io.BufferedReader;

public class SequenceValidator {
  
	private String sequence;
	private File fasta_file;
	
	
	public SequenceValidator (String input) { // constructor for sting input 
		this.sequence = input;
		prepareSequence();
		residuCheck();
	}
	
	//public SequneceValidator (File input) { //constructor for file input 
	// check file
	//}
	
	public String getSequence() {
	    return this.sequence;
	}

	// Checks input sequence and returns string in fasta format uppercase 
	public void prepareSequence() {
		
		//no input check 
		if (this.sequence.isEmpty()) {
			 throw new IllegalArgumentException("Invalid input");
		}
		
		// add fasta header
		char first_char = this.sequence.charAt(0);
		if (first_char != '>') {
			this.sequence = ">sequence\n" + this.sequence;
		}
		
		// Checks for header without a sequence 
		else if (!this.sequence.contains("\n")) {
		    throw new IllegalArgumentException("Invalid input");
		}
		
		// only sequence to upper case 
		int start = this.sequence.indexOf('\n') + 1; //turn into separate  method 
		int end = this.sequence.length();
		String header = this.sequence.substring(0, start);
		this.sequence = this.sequence.substring(start, end).toUpperCase();
		this.sequence = header + this.sequence;
	}
	
	public void residuCheck(){
		int start = this.sequence.indexOf('\n') + 1;
		int end = this.sequence.length();
		
		for (int i = start; i < end; i++) {

			if (this.sequence.charAt(i) != 'A' && this.sequence.charAt(i) != 'T' &&
				this.sequence.charAt(i) != 'C' && this.sequence.charAt(i) != 'G' ) {
				throw new IllegalArgumentException("Invalid input");
			}
		}
	}
}
		
	 