package utilities;

public class ProteinStatistics extends Statistics{
	private Sequence initialValue;

	public ProteinStatistics(Sequence initialValue) {
		super(initialValue);
		this.initialValue = initialValue;
	}
	
	public double ProteinWeight() {
		String seq = initialValue.getSequenceNoHeader();
		CodonUtils utils = new CodonUtils();
		double totalWeight = 0;
		
		for (char aa : seq.toCharArray()) {
			totalWeight += utils.baseWeight(aa);
			} 
		
		return totalWeight;
	}
}
