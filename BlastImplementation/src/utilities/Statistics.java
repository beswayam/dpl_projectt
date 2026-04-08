// Statistics is a class within package utilities
package utilities;

import java.util.HashMap;

// define the Statistics class
public class Statistics {
	private String SEQ;
	
	// create a class constructor
	public Statistics(String sequence) {
		setSeq(sequence);
	}
	
	// method to retrieve the (global) sequence
	public String getSeq() {
		return this.SEQ;
	}
	
	// method to set the (global) sequence
	public void setSeq(String seq) {
		this.SEQ = seq.toUpperCase();
	}
	
	// method to calculate and retrieve the sequence length
	public int seqLength() {
		int sl = this.SEQ.length(); // sl = sequence length
		return sl;
	}
	
	// method to create a dictionary with bases and their counts
	public HashMap<Character, Integer> SeqContents() {
		// initiate a map (dictionary in Python)
		HashMap<Character, Integer> baseCounts = new HashMap<>();
		
		// add a value of one each time you strike a base to that
		// corresponding base count
		for (int i = 0; i < seqLength(); i++) { 
			char base = SEQ.charAt(i);
			
			if (!baseCounts.containsKey(base)) {
				baseCounts.put(base, 0);
			}
			
			baseCounts.put(base, baseCounts.get(base) + 1);
		}
		
		// return the base - count map. 
		return baseCounts;
	}
}