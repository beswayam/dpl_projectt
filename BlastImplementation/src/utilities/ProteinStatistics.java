package utilities;

/**
 * The ProteinStatistics class extends Statistics to provide
 * protein-specific analysis functionality.
 * 
 * <p>It calculates the molecular weight based on amino acid composition.
 */
public class ProteinStatistics extends Statistics{
	/** The original sequence used for protein statistics.*/
	private Sequence sequence;

	/**
	 * Constructs a ProteinStatistics object for a given sequence.
	 * 
	 * @param initialValue the protein sequence to compute statistics of
	 */
	public ProteinStatistics(Sequence initialValue) {
		super(initialValue);
		this.sequence = initialValue;
	}
	
	/**
	 * Calculates the total molecular weight of the protein sequence.
	 * 
	 * <p>The weight is computed by summing the individual weights
	 * of each amino acid in the sequence.
	 * 
	 * @return the total protein molecular weight
	 */
	public double proteinWeight() {
		String seq = this.sequence.getSequenceNoHeader();
		CodonUtils utils = new CodonUtils();
		double totalWeight = 0;

		for (char aa : seq.toCharArray()) {
			totalWeight += utils.baseWeight(aa);
		}

		return totalWeight;
	}
}
