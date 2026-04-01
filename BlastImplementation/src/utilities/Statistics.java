// Statistics is a class within package utilities
package utilities;

import java.util.HashMap;

import ch.qos.logback.core.net.SyslogOutputStream;

import java.util.Arrays;


// define the Statistics class
public class Statistics {

	// define all global variables
	private String seq;
	
	// create a class constructor
	public Statistics(String initialValue) {
		setSeq(initialValue);
	}
	
	// method to retrieve the (global) sequence
	public String getSeq() {
		return seq;
	}
	
	// method to set the (global) sequence
	public void setSeq(String seq) {
		this.seq = seq.toUpperCase();
	}
	
	// method to calculate and retrieve the sequence length
	public int seqLength() {
		int sl = seq.length(); // sl = sequence length
		return sl;
	}
	
	// method to count a base in sequence of bases
	public int countNuc(char nuc, String seq) {
		
		// initialise for loop 
		int baseCount = 0;
		for (int i = 0; i < seqLength(); i++) { 
			if (seq.charAt(i) == nuc) {
				baseCount++;
			}
		}
		
		// return integer of instances base occurring in sequence
		return baseCount;
	}

	// method to create a dictionary with bases and their counts
	public HashMap<Character, Integer> SeqContents() {
		// initiate a map (dictionary in Python)
		HashMap<Character, Integer> baseCounts = new HashMap<>();
		baseCounts.put('C', 0);
		baseCounts.put('A', 0);
		baseCounts.put('G', 0);
		baseCounts.put('T', 0);
		
		// add a value of one each time you strike a base to that
		// corresponding base count
		for (int i = 0; i < seqLength(); i++) { 
			char base = seq.charAt(i);
			
			int count = baseCounts.get(base);
			baseCounts.put(base, ++count);
		}
		
		// return the base - count map. 
		return baseCounts;
	}
	
	// calculate the GC content
	public double GCContent() {
		int gc = 0;
		
		// every time we encounter a g or c, add a value 
		// of one to the counter
		for (int i = 0; i < seqLength(); i++) { 
			char base = seq.charAt(i);
			
			if (base == 'C' || base == 'G') {
				gc++;
			} 	
		}
		
		// return the gc proportion
		return (double) gc / seqLength();
	}
	
	// get all codons within the specified reading frame
	public String ReadingFrame(int kframe) {
		//System.out.printf("frame: %d%n", kframe);
		
		String seq = getSeq();
		StringBuilder codon = new StringBuilder();
		StringBuilder codingSeq = new StringBuilder();
		String[] stopCodon = {"TAA", "TAG", "TGA"};
		
		int seqLen = seqLength();
		int count = 0;
		boolean endSequence = false;
		
		for (int i = kframe - 1; i < seqLen ; i++ ) {
			
			char nuc = seq.charAt(i);	
			codon.append(nuc);
			
			// check whether a full codon was formed
			if (codon.length() % 3 == 0 && endSequence == false) {
				
				
				// check whether a start codon is found
				if (codon.toString().contains("ATG")) {
					codingSeq.append(codon);
					
				} 
			
				// check whether the coding sequence was initiated before appending
				else if (codingSeq.length() != 0) {
					codingSeq.append(codon);
				}
				
				// check whether the codon is a stop codon
				else if (Arrays.asList(stopCodon).contains(codon.toString())) {
					codingSeq.append(codon);
					
					endSequence = true;
				}
				
				System.out.println(codon);
				// reset the codon
				codon.setLength(0);
				
			}
			
		}
		
		
		return codingSeq.toString();
	}
	
	// transform dna sequence to amino acid sequence
	public String ProteinSeq() {
		return null;
	}
	
	// transform protein sequence to dna sequence
	public String DNASeq() {
		return null;
	}
	
	// make a reverse compliment sequence of the dna sequence
	public String ReverseCompliment() {
		return null;
	}
	
	// find and count all repeated patters, with their indices
	public String RepeatedPatterns() {
		return null;
	}
	
	// find all K-mer substrings within the sequence,
	// K is the number of nucleotides per substring
	public String KMerSubStrings() {
		return null;
	}
}
