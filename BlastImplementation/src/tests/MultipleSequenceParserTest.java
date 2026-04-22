package tests;

import java.io.File;
import java.util.ArrayList;

import junit.framework.TestCase;
import utilities.Sequence;
import utilities.MultipleSequenceParser;

public class MultipleSequenceParserTest extends TestCase {

	public void testReadFromString() {
		String sequences = ">unknown_pectinesterase_pt_1\r\n" + "RVIIKMAPGEYKEKVTIDRNKPFITLMGQP\r\n"
				+ "NAMPVITYDGTAAKYGTVDSASLIILSDYF\r\n" + "MAVNIVVKNT\r\n" + ">unknown_pectinesterase_pt_2\r\n"
				+ "APAPDGKTKGAQALSMRISGNFAAFYNCKF\r\n" + "YGFQDTICDDTGNHFFKDCYVEGTFDFIFG\r\n" + "SGTSMYLGTQ\r\n"
				+ ">unknown_pectinesterase_pt_3\r\n" + "LHVVGDGIRVIAAHAGKSAEEKSGYSFVHC\r\n" + "KVTGTGGGIYLGRAWMSH";

		ArrayList<Sequence> parsedSeqList = new MultipleSequenceParser().parseMultipleSeqs(sequences);
		assertEquals(
				">unknown_pectinesterase_pt_2\nAPAPDGKTKGAQALSMRISGNFAAFYNCKFYGFQDTICDDTGNHFFKDCYVEGTFDFIFGSGTSMYLGTQ",
				parsedSeqList.get(1).getSequence());
	}

	public void testReadFromFile() {
		File sequenceFile = new File("project_data" + File.separator + "pectinesterases.fasta");

		ArrayList<Sequence> parsedSeqList = new MultipleSequenceParser().parseMultipleSeqs(sequenceFile);
		assertEquals(
				">unknown_pectinesterase_pt_2\nAPAPDGKTKGAQALSMRISGNFAAFYNCKFYGFQDTICDDTGNHFFKDCYVEGTFDFIFGSGTSMYLGTQ",
				parsedSeqList.get(1).getSequence());
	}

}
