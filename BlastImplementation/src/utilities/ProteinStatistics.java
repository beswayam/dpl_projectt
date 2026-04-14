package utilities;

import interfaces.StatisticsInterface;

public class ProteinStatistics extends Statistics implements StatisticsInterface{
	private Sequence initialValue;

	public ProteinStatistics(Sequence initialValue) {
		super(initialValue);
		this.initialValue = initialValue;
	}
	
	public double proteinWeight() {
		String seq = this.initialValue.getSequenceNoHeader();
		CodonUtils utils = new CodonUtils();
		double totalWeight = 0;
		
		for (char aa : seq.toCharArray()) {
			totalWeight += utils.baseWeight(aa);
			} 
		
		return totalWeight;
	}
}
