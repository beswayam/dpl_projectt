package utilities;

public class ProteinStatistics extends Statistics{
	public ProteinStatistics(String initialValue) {
		super(initialValue);
	}
	
	public double ProteinWeight() {
		String seq = getSeq();
		CodonUtils utils = new CodonUtils();
		double totalWeight = 0;
		
		for (char aa : seq.toCharArray()) {
			totalWeight += utils.baseWeight(aa);
			} 
		
		return totalWeight;
	}
}
