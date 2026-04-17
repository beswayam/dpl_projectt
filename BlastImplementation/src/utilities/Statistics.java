package utilities;

import java.util.HashMap;

/**
 * The Statistics class provides basic statistics of a protein or DNA sequence.
 * 
 * It can compute sequence length and count the occurrences of each base 
 * in the sequence.
 */
public class Statistics {
	
	/** The sequence to compute statistics of. */
	private Sequence seq;

	/**
	 * Constructs a Statistics object for a given sequence.
	 * 
	 * @param sequence the sequence to compute statistics of 
	 */
	public Statistics(Sequence sequence) {
		setSeq(sequence);
	}

	/**
	 * Returns the sequence as a string (without the FASTA header).
	 * 
	 * @return the raw sequence string
	 */
	public String getSeq() {
		return this.seq.getSequenceNoHeader();
	}

	/**
	 * Sets the sequence to compute statistics of.
	 * 
	 * @param seq the Sequence object to store
	 */
	public void setSeq(Sequence seq) {
		this.seq = seq;
	}

	/**
	 * Returns the length of the sequence.
	 * 
	 * @return the number of characters in the sequence
	 */
	public int seqLength() {
		int sl = getSeq().length(); // sl = sequence length
		return sl;
	}

	/**
	 * Counts the occurrences of each character in the sequence.
	 * 
	 * <p> The result is stored in a map where each key is a character
	 * and the value is the number of times it appears.
	 * 
	 * @return a map containing the character counts 
	 */
	public HashMap<Character, Integer> seqContents() {
		// initiate a map (dictionary in Python)
		HashMap<Character, Integer> baseCounts = new HashMap<>();

		// add a value of one each time you strike a base to that
		// corresponding base count
		for (int i = 0; i < seqLength(); i++) {
			char base = getSeq().charAt(i);

			if (!baseCounts.containsKey(base)) {
				baseCounts.put(base, 0);
			}

			baseCounts.put(base, baseCounts.get(base) + 1);
		}

		// return the base - count map.
		return baseCounts;
	}
}