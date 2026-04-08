package utilities;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class NucleotideStatistics extends Statistics{

	public NucleotideStatistics(String initialValue) {
		super(initialValue);
		// TODO Auto-generated constructor stub
	}
	
	// calculate the GC content
	public double GCContent() {
		if (seqLength() == 0) {
			return 0.0;
		}

		int gc = 0;
		for (int i = 0; i < seqLength(); i++) {
			char base = this.seq.charAt(i);
			if (base == 'C' || base == 'G') {
				gc++;
			}
		}
		return (double) gc / seqLength();
	}

	// GC skew = (G-C)/(G+C). Returns 0 when there are no G/C bases.
	/* public double GCSkew() {
		int gCount = countNuc('G', getSeq());
		int cCount = countNuc('C', getSeq());
		int denominator = gCount + cCount;
		if (denominator == 0) {
			return 0.0;
		}
		return (double) (gCount - cCount) / denominator;
	}
	*/
	
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
		
		public ArrayList<String> AllReadingFrames(int[] readingFrames) {
			ArrayList<String> rFrames = new ArrayList<>();

			for (int k : readingFrames) {
				rFrames.add(ReadingFrame(k));
			}
			return rFrames;
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
		
		// note: introns are not taken into account
		public void testReadingFrame() {
			// initiate a Statistics variable
			String initialSeq = "GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA";
			Statistics seq = new Statistics(initialSeq);
			int k = 1;
			
			//get the readingframe k
			String rFrame = seq.ReadingFrame(k);
			assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
			}
		
		public void testReverseCompliment() {
			String initialSeq = "GGTCCA";
			Statistics seq = new Statistics(initialSeq);
			
			// get the reverse compliment sequence
			String revCompSeq = seq.ReverseCompliment();
			assertEquals(revCompSeq, "TGGACC");
		}
		
		// note: introns are not taken into account
		public void testAllReadingFrames() {
			String initialSeq = "GCGTACGTTAGCATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAACGTACGATGCTA";
			Statistics seq = new Statistics(initialSeq);
			
			// make an array of all reading frames you want to find
			int[] k = {1,2,3};
			
			ArrayList<String> readingFrames = seq.AllReadingFrames(k);
			for (String rFrame : readingFrames) {
				
				// check whether a reading frame has successfully retrieved coding sequence
				if (!rFrame.isEmpty()) {
					assertEquals(rFrame, "ATGGAATTCCGATTTGGCAACCCTGGATCAAGTTAA");
				}
			}	
		}
		
		public void testGCContent() {
			// initiate a Statistics variable
			String initialSeq = "aattcggg";
			Statistics seq = new Statistics(initialSeq);
			
			// calculate the GC content
			double gcProportion = seq.GCContent();
			System.out.printf("GC content in sequence: %.1f%%%n%n", (double) Math.round(gcProportion * 100));
			assertEquals(gcProportion, 0.5);
		}
		
		public void testCountNuc() {
			// initiate a Statistics variable
			String initialSeq = "aattcccgggcc";
			Statistics seq = new Statistics(initialSeq);
			
			// choose base to search
			char nuc = 'C'; 
			
			// count instances of base in sequence
			int count = seq.countNuc(nuc, seq.getSeq());
			//System.out.printf("Sequence: %s%nNumber of %s in sequence: %d%n%n", seq.getSeq(), nuc, count);
			assertEquals(5, count);
		}
}

