// Statistics is a class within package utilities
package utilities;

import java.util.HashMap;
import java.util.ArrayList;
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
					
					// check whether the codon is a stop codon
					if (Arrays.asList(stopCodon).contains(codon.toString())) {
						endSequence = true;
					}					
				}	
				
				// reset the codon
				codon.setLength(0);
			}
		}
		
		return codingSeq.toString();
	}
	
	
	
	// make a reverse compliment sequence of the dna sequence
	public String ReverseCompliment() {
		String seq = getSeq();
		StringBuilder revComp = new StringBuilder(seq);
		revComp.reverse();
		
		HashMap<Character, Character> nucMap = new HashMap<>();
		nucMap.put('A', 'T');
		nucMap.put('C', 'G');
		nucMap.put('T','A');
		nucMap.put('G', 'C');
		
		for (int i = 0 ; i < revComp.length() ; i++) {
			char revNuc = nucMap.get(revComp.charAt(i));
			revComp.setCharAt(i, revNuc);
		}
		
		return revComp.toString();
	}

	public ArrayList<String> AllReadingFrames(int[] readingFrames) {
		String seq = getSeq();
		ArrayList<String> rFrames = new ArrayList<>();
		
		for (int k : readingFrames) {
			rFrames.add(ReadingFrame(k));
		}
		return rFrames;
	}

	public double ProteinWeight() {
		
		String seq = getSeq();
		
        // Create a HashMap to store amino acid weights
        HashMap<Character, Double> protWeights = new HashMap<>();

        // Assign molecular weights in Daltons (average values)
        protWeights.put('A', 89.09);   // Alanine
        protWeights.put('R', 174.20);  // Arginine
        protWeights.put('N', 132.12);  // Asparagine
        protWeights.put('D', 133.10);  // Aspartic acid
        protWeights.put('C', 121.16);  // Cysteine
        protWeights.put('E', 147.13);  // Glutamic acid
        protWeights.put('Q', 146.15);  // Glutamine
        protWeights.put('G', 75.07);   // Glycine
        protWeights.put('H', 155.16);  // Histidine
        protWeights.put('I', 131.18);  // Isoleucine
        protWeights.put('L', 131.18);  // Leucine
        protWeights.put('K', 146.19);  // Lysine
        protWeights.put('M', 149.21);  // Methionine
        protWeights.put('F', 165.19);  // Phenylalanine
        protWeights.put('P', 115.13);  // Proline
        protWeights.put('S', 105.09);  // Serine
        protWeights.put('T', 119.12);  // Threonine
        protWeights.put('W', 204.23);  // Tryptophan
        protWeights.put('Y', 181.19);  // Tyrosine
        protWeights.put('V', 117.15);  // Valine

        // Example usage: get the weight of Methionine
        double totalWeight = 0;
        for (char aa : seq.toCharArray()) {
        	if (protWeights.containsKey(aa)) {
        		totalWeight += protWeights.get(aa);
        	} else {
        		System.out.println("Invalid protein sequence used");
        	}	
        }
   
		return totalWeight;
	}
	
}
