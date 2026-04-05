// Statistics is a class within package utilities
package utilities;

import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;

// define the Statistics class
public class Statistics {

	// define all global variables
	private String seq;

	// Standard codon table for DNA->protein translation.
	private static final HashMap<String, Character> CODON_TABLE = new HashMap<>();

	static {
		CODON_TABLE.put("TTT", 'F'); CODON_TABLE.put("TTC", 'F');
		CODON_TABLE.put("TTA", 'L'); CODON_TABLE.put("TTG", 'L');
		CODON_TABLE.put("CTT", 'L'); CODON_TABLE.put("CTC", 'L');
		CODON_TABLE.put("CTA", 'L'); CODON_TABLE.put("CTG", 'L');
		CODON_TABLE.put("ATT", 'I'); CODON_TABLE.put("ATC", 'I');
		CODON_TABLE.put("ATA", 'I'); CODON_TABLE.put("ATG", 'M');
		CODON_TABLE.put("GTT", 'V'); CODON_TABLE.put("GTC", 'V');
		CODON_TABLE.put("GTA", 'V'); CODON_TABLE.put("GTG", 'V');

		CODON_TABLE.put("TCT", 'S'); CODON_TABLE.put("TCC", 'S');
		CODON_TABLE.put("TCA", 'S'); CODON_TABLE.put("TCG", 'S');
		CODON_TABLE.put("CCT", 'P'); CODON_TABLE.put("CCC", 'P');
		CODON_TABLE.put("CCA", 'P'); CODON_TABLE.put("CCG", 'P');
		CODON_TABLE.put("ACT", 'T'); CODON_TABLE.put("ACC", 'T');
		CODON_TABLE.put("ACA", 'T'); CODON_TABLE.put("ACG", 'T');
		CODON_TABLE.put("GCT", 'A'); CODON_TABLE.put("GCC", 'A');
		CODON_TABLE.put("GCA", 'A'); CODON_TABLE.put("GCG", 'A');

		CODON_TABLE.put("TAT", 'Y'); CODON_TABLE.put("TAC", 'Y');
		CODON_TABLE.put("TAA", '*'); CODON_TABLE.put("TAG", '*');
		CODON_TABLE.put("CAT", 'H'); CODON_TABLE.put("CAC", 'H');
		CODON_TABLE.put("CAA", 'Q'); CODON_TABLE.put("CAG", 'Q');
		CODON_TABLE.put("AAT", 'N'); CODON_TABLE.put("AAC", 'N');
		CODON_TABLE.put("AAA", 'K'); CODON_TABLE.put("AAG", 'K');
		CODON_TABLE.put("GAT", 'D'); CODON_TABLE.put("GAC", 'D');
		CODON_TABLE.put("GAA", 'E'); CODON_TABLE.put("GAG", 'E');

		CODON_TABLE.put("TGT", 'C'); CODON_TABLE.put("TGC", 'C');
		CODON_TABLE.put("TGA", '*'); CODON_TABLE.put("TGG", 'W');
		CODON_TABLE.put("CGT", 'R'); CODON_TABLE.put("CGC", 'R');
		CODON_TABLE.put("CGA", 'R'); CODON_TABLE.put("CGG", 'R');
		CODON_TABLE.put("AGT", 'S'); CODON_TABLE.put("AGC", 'S');
		CODON_TABLE.put("AGA", 'R'); CODON_TABLE.put("AGG", 'R');
		CODON_TABLE.put("GGT", 'G'); CODON_TABLE.put("GGC", 'G');
		CODON_TABLE.put("GGA", 'G'); CODON_TABLE.put("GGG", 'G');
	}

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
		if (seq == null || seq.isEmpty()) {
			return 0;
		}

		int baseCount = 0;
		char target = Character.toUpperCase(nuc);
		for (int i = 0; i < seq.length(); i++) {
			if (Character.toUpperCase(seq.charAt(i)) == target) {
				baseCount++;
			}
		}
		return baseCount;
	}

	// method to create a dictionary with bases and their counts
	public HashMap<Character, Integer> SeqContents() {
		// initiate a map (dictionary in Python)
		HashMap<Character, Integer> baseCounts = new HashMap<>();
		
		// add a value of one each time you strike a base to that
		// corresponding base count
		for (int i = 0; i < seqLength(); i++) { 
			char base = seq.charAt(i);
			
			if (!baseCounts.containsKey(base)) {
				baseCounts.put(base, 0);
			}
			
			baseCounts.put(base, baseCounts.get(base) + 1);
		}
		
		// return the base - count map. 
		return baseCounts;
	}
	
	// calculate the GC content
	public double GCContent() {
		if (seqLength() == 0) {
			return 0.0;
		}

		int gc = 0;
		for (int i = 0; i < seqLength(); i++) {
			char base = seq.charAt(i);
			if (base == 'C' || base == 'G') {
				gc++;
			}
		}
		return (double) gc / seqLength();
	}

	// GC skew = (G-C)/(G+C). Returns 0 when there are no G/C bases.
	public double GCSkew() {
		int gCount = countNuc('G', getSeq());
		int cCount = countNuc('C', getSeq());
		int denominator = gCount + cCount;
		if (denominator == 0) {
			return 0.0;
		}
		return (double) (gCount - cCount) / denominator;
	}

	// get all codons within the specified reading frame
	public String ReadingFrame(int kframe) {
		String seq = getSeq();
		StringBuilder codon = new StringBuilder();
		StringBuilder codingSeq = new StringBuilder();
		String[] stopCodon = {"TAA", "TAG", "TGA"};

		int seqLen = seqLength();
		boolean endSequence = false;

		for (int i = kframe - 1; i < seqLen; i++) {
			char nuc = seq.charAt(i);
			codon.append(nuc);

			if (codon.length() % 3 == 0 && !endSequence) {
				String currentCodon = codon.toString();

				if (currentCodon.equals("ATG")) {
					codingSeq.append(currentCodon);
				} else if (codingSeq.length() != 0) {
					codingSeq.append(currentCodon);
					if (Arrays.asList(stopCodon).contains(currentCodon)) {
						endSequence = true;
					}
				}

				codon.setLength(0);
			}
		}

		return codingSeq.toString();
	}

	// Translate full DNA sequence into one-letter amino-acid sequence.
	public String translate() {
		String dna = getSeq();
		StringBuilder protein = new StringBuilder();

		for (int i = 0; i + 2 < dna.length(); i += 3) {
			String codon = dna.substring(i, i + 3);
			Character aa = CODON_TABLE.get(codon);
			if (aa != null) {
				protein.append(aa);
			}
		}
		return protein.toString();
	}

	// Count codon usage across complete codons of the sequence.
	public HashMap<String, Integer> getCodonFrequency() {
		HashMap<String, Integer> codonFrequency = new HashMap<>();
		String dna = getSeq();

		for (int i = 0; i + 2 < dna.length(); i += 3) {
			String codon = dna.substring(i, i + 3);
			if (CODON_TABLE.containsKey(codon)) {
				if (!codonFrequency.containsKey(codon)) {
					codonFrequency.put(codon, 0);
				}
				codonFrequency.put(codon, codonFrequency.get(codon) + 1);
			}
		}
		return codonFrequency;
	}

	// make a reverse compliment sequence of the dna sequence
	public String ReverseCompliment() {
		String seq = getSeq();
		StringBuilder revComp = new StringBuilder(seq);
		revComp.reverse();

		HashMap<Character, Character> nucMap = new HashMap<>();
		nucMap.put('A', 'T');
		nucMap.put('C', 'G');
		nucMap.put('T', 'A');
		nucMap.put('G', 'C');

		for (int i = 0; i < revComp.length(); i++) {
			char original = revComp.charAt(i);
			Character revNuc = nucMap.get(original);
			if (revNuc == null) {
				throw new IllegalArgumentException("Invalid DNA sequence used");
			}
			revComp.setCharAt(i, revNuc);
		}

		return revComp.toString();
	}

	public ArrayList<String> AllReadingFrames(int[] readingFrames) {
		ArrayList<String> rFrames = new ArrayList<>();

		for (int k : readingFrames) {
			rFrames.add(ReadingFrame(k));
		}
		return rFrames;
	}

	public double ProteinWeight() {
		String seq = getSeq();

		HashMap<Character, Double> protWeights = new HashMap<>();
		protWeights.put('A', 89.09);
		protWeights.put('R', 174.20);
		protWeights.put('N', 132.12);
		protWeights.put('D', 133.10);
		protWeights.put('C', 121.16);
		protWeights.put('E', 147.13);
		protWeights.put('Q', 146.15);
		protWeights.put('G', 75.07);
		protWeights.put('H', 155.16);
		protWeights.put('I', 131.18);
		protWeights.put('L', 131.18);
		protWeights.put('K', 146.19);
		protWeights.put('M', 149.21);
		protWeights.put('F', 165.19);
		protWeights.put('P', 115.13);
		protWeights.put('S', 105.09);
		protWeights.put('T', 119.12);
		protWeights.put('W', 204.23);
		protWeights.put('Y', 181.19);
		protWeights.put('V', 117.15);

		double totalWeight = 0;
		for (char aa : seq.toCharArray()) {
			if (protWeights.containsKey(aa)) {
				totalWeight += protWeights.get(aa);
			} else {
				throw new IllegalArgumentException("Invalid protein sequence used");
			}
		}

		return totalWeight;
	}
}