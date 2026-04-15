package tests;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.util.Scanner;

import javax.swing.JOptionPane;

import junit.framework.TestCase;
import utilities.Sequence;
import utilities.Ssearch36Search;

// JUnit 3 test class for the ssearch36 utility
public class Ssearch36SearchTest extends TestCase {
	private static Ssearch36Search ssearch36search = new Ssearch36Search();
	
    public void testRunReturnsZeroForValidInput() throws Exception {
        File dbFile = File.createTempFile("ssearch_db", ".fasta");
        File outputFile = File.createTempFile("ssearch_output", ".txt");
        Sequence sequence = new Sequence(">query\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        FileWriter dbWriter = new FileWriter(dbFile);
        dbWriter.write(">db\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        dbWriter.close();
        ssearch36search.setSequence(sequence);
       	ssearch36search.run(
                dbFile,
                "1e-5",
                "10",
                "BLOSUM62",
                outputFile.getAbsolutePath());
        assertEquals(0, ssearch36search.getErrorCode());
    }
    
    public void testWriteToFile() throws Exception {
        File dbFile = File.createTempFile("ssearch_db", ".fasta");
        File outputFile = File.createTempFile("ssearch_output", ".txt");
        Sequence sequence = new Sequence(">query\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        FileWriter dbWriter = new FileWriter(dbFile);
        dbWriter.write(">db\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        dbWriter.close();
        ssearch36search.setSequence(sequence);
       	ssearch36search.run(
                dbFile,
                "1e-5",
                "10",
                "BLOSUM62",
                outputFile.getAbsolutePath());
        assertEquals(0, ssearch36search.getErrorCode());
        File outputTsv = new File("project_data"+File.separator+"temp_output.tsv");
        ssearch36search.parseBlastCustomDatabase(outputTsv);
        try (Scanner blastOutputTsv = new Scanner(outputTsv)) {
        	String header;
        	header = blastOutputTsv.nextLine();
        	assertEquals("hit\tid\tdescription\tmatch_sequence\teval\tbitscore\tidentity\tquery_sequence\tquery_start\tquery_end\tmatch_start\tmatch_end",header);
		
	}
    catch (FileNotFoundException fe)
    {
	    JOptionPane.showMessageDialog(new JOptionPane(), 
	    		"Failed to open output file", 
                "Output Error", 
                JOptionPane.ERROR_MESSAGE);
	    fe.printStackTrace();
    }}
}