package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NucleotideStatistics extends Statistics{

	public NucleotideStatistics(String initialValue) {
		super(initialValue);
		// TODO Auto-generated constructor stub
	}
	
	// GC skew = (G-C)/(G+C). Returns 0 when there are no G/C bases.
	public double GCSkew() {
		HashMap<Character, Integer> nuc_counts = SeqContents();
		int gCount = nuc_counts.get('G');
		int cCount = nuc_counts.get('C');
		int denominator = gCount + cCount;
		if (denominator == 0) {
			return 0.0;
		}
		return (double) (gCount - cCount) / denominator;
	}
	
	// calculate the GC content
	public double GCContent() {
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
	public String ReadingFrame(int kframe) {
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
	public ArrayList<String> AllReadingFrames(ArrayList<Integer> rframe_idxs) {
		ArrayList<String> rFrames = new ArrayList<>();
		
		for (int k : rframe_idxs) {
			rFrames.add(ReadingFrame(k));
		}
		return rFrames;
	}
		
		// Translate full DNA sequence into one-letter amino-acid sequence.
		public String Translate() {
			String seq = getSeq();
			StringBuilder protein = new StringBuilder();
			if (seq.length() % 3 == 0) {
				for (int i = 0; i + 2 < seq.length(); i += 3) {
					String codon = seq.substring(i, i + 3);
					Character aa = CodonUtils.CODON_TABLE.get(codon);
					if (aa != null) {
						protein.append(aa);
					}
				}
				return protein.toString();
			} else {
				throw new IllegalArgumentException("Sequence contains incomplete codons");
			}
			
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
		/*
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
		}*/
}

