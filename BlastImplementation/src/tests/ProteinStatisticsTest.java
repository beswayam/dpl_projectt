package tests;

import junit.framework.TestCase;
import utilities.ProteinStatistics;
import utilities.Sequence;

public class ProteinStatisticsTest extends TestCase {
	private String initialSeq = "ACDEFGHIKL";
	private Sequence protSeq = new Sequence(initialSeq);
	private ProteinStatistics seq = new ProteinStatistics(protSeq);

	public void testMolecularWeight() {
		double protWeight = seq.proteinWeight();
		assertEquals(protWeight, 1294.42);
	}
}
