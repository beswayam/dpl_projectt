package tests;

import junit.framework.TestCase;
import utilities.ProteinStatistics;

public class ProteinStatisticsTest extends TestCase {
	String initialSeq = "ACDEFGHIKL";
	ProteinStatistics seq = new ProteinStatistics(initialSeq);
	public void testMolecularWeightt() {		
		double protWeight = seq.ProteinWeight();
		assertEquals(protWeight, 1294.42);
	}
	

}
