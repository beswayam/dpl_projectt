package tests;

import junit.framework.TestCase;
import utilities.ProteinStatistics;
import utilities.Sequence;

public class ProteinStatisticsTest extends TestCase {
	String initialSeq = "ACDEFGHIKL";
	Sequence protSeq = new Sequence(initialSeq);
	ProteinStatistics seq = new ProteinStatistics(protSeq);
	public void testMolecularWeightt() {		
		double protWeight = seq.ProteinWeight();
		assertEquals(protWeight, 1294.42);
	}
}
