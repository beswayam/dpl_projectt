package utilities;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

import interfaces.StatisticsInterface;

public class NucleotideStatistics extends Statistics implements StatisticsInterface{
		
	public NucleotideStatistics(Sequence Sequence ) {
		super(Sequence);
	}
	
	// GC skew = (G-C)/(G+C). Returns 0 when there are no G/C bases.
	public double gcSkew() {
		HashMap<Character, Integer> nuc_counts = seqContents();
		int gCount = nuc_counts.get('G');
		int cCount = nuc_counts.get('C');
		int denominator = gCount + cCount;
		if (denominator == 0) {
			return 0.0;
		}
		return (double) (gCount - cCount) / denominator;
	}
	
	// calculate the GC content
	public double gcContent() {
		if (seqLength() == 0) {
			return 0.0;
		}

		int gc = 0;
		for (int i = 0; i < seqLength(); i++) {
			char base = getSeq().charAt(i);
			if (base == 'C' || base == 'G') {
				gc++;
			}
		}
		return (double) gc / seqLength();
	}
	
	// get all codons within the specified reading frame
	public String readingFrame(int kframe) {
		String seq = getSeq();
		StringBuilder codon = new StringBuilder();
		StringBuilder codingSeq = new StringBuilder();
		String[] stopCodon = {"TAA", "TAG", "TGA"};

		int seqLen = seqLength();
		boolean endSequence = false;

		for (int i = kframe; i < seqLen; i++) {
			
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
	
	// find all reading frames
	public ArrayList<String> allReadingFrames(ArrayList<Integer> rframe_idxs) {
		ArrayList<String> rFrames = new ArrayList<>();
		
		for (int k : rframe_idxs) {
			rFrames.add(readingFrame(k));
		}
		return rFrames;
	}
	
	// Translate full DNA sequence into one-letter amino-acid sequence.
	public String translate() {
		String seq = getSeq();
		StringBuilder protein = new StringBuilder();
		CodonUtils utils = new CodonUtils();
		
		if (seq.length() % 3 == 0) {
			for (int i = 0; i + 2 < seq.length(); i += 3) {
				String codon = seq.substring(i, i + 3);
				Character aa = utils.getBase(codon);
				if (aa != null) {
					protein.append(aa);
				}
			}
		
		}
		return protein.toString();	
	}
		
	// make a reverse compliment sequence of the dna sequence
	public String reverseCompliment() {
		String seq = getSeq();
		StringBuilder revSeq = new StringBuilder(seq);
		
		// define the nuc-to-nuc map
		HashMap<Character, Character> nucToNuc = new HashMap<>();
		nucToNuc.put('A', 'T');
		nucToNuc.put('C', 'G');
		nucToNuc.put('T', 'A');
		nucToNuc.put('G', 'C');
		
		revSeq.reverse();
		char original;
		StringBuilder revCompSeq = new StringBuilder();
		
		for (int i = 0; i < revSeq.length(); i++) {
			original = revSeq.charAt(i);
			 
			if (nucToNuc.get(original) != null) {
				revCompSeq.append(nucToNuc.get(original));
			} else {
				throw new IllegalArgumentException("Invalid DNA sequence used");
			}
		}
		return revCompSeq.toString();
	}
		
	// Count codon usage across complete codons of the sequence.
	public HashMap<String, Integer> getCodonFrequency() {
		HashMap<String, Integer> codonFrequency = new HashMap<>();
		String dna = getSeq();
		CodonUtils utils = new CodonUtils();
		
		for (int i = 0; i + 2 < dna.length(); i += 3) {
			String codon = dna.substring(i, i + 3);
			
			if (!codonFrequency.containsKey(codon) && utils.checkCodon(codon)) {
				codonFrequency.put(codon, 0);
			}
			codonFrequency.put(codon, codonFrequency.get(codon) + 1);
			
		}
		return codonFrequency;
	}
}

