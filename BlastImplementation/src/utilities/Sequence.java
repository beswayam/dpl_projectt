package utilities;
import java.io.File;                  // File class
import java.util.Scanner;

import javax.swing.JLabel;

import java.io.FileNotFoundException; // Import this class to handle errors
import java.nio.file.Path;
import java.io.BufferedReader;

public class Sequence {
  
	private String sequence;
	private File fasta_file;
	
	
	public Sequence (String input) { // constructor for sting input 
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
		String residues = this.sequence.substring(start).replaceAll("\\n", "").trim();
		
		if (residues.matches("[ATGC]+")) {
	        System.out.println("Valid DNA sequence — Length: " + residues.length());
	    } else if (residues.matches("[ACDEFGHIKLMNPQRSTVWY]+")) {
	        System.out.println("Valid protein sequence — Length: " + residues.length());
	    } else {
	        throw new IllegalArgumentException("Invalid input");
	    }
			
		}
	}

		
	 