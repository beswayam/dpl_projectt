package utilities;

public class ProteinStatistics extends Statistics{
	public ProteinStatistics(String initialValue) {
		super(initialValue);
	}
	
	public double ProteinWeight() {
		String seq = getSeq();
		double totalWeight = 0;
		
		for (char aa : seq.toCharArray()) {
			if (CodonUtils.AMINO_MASS.get(aa) != null) {
				totalWeight += CodonUtils.AMINO_MASS.get(aa);
			} else {
				throw new IllegalArgumentException("Invalid protein sequence used");
			}
		}

		return totalWeight;
	}
	
}
