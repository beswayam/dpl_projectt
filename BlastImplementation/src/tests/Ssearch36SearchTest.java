package tests;

import java.io.File;
import java.io.FileWriter;
import junit.framework.TestCase;
import utilities.Ssearch36Search;

// JUnit 3 test class for the ssearch36 utility
public class Ssearch36SearchTest extends TestCase {

    public void testRunReturnsZeroForValidInput() throws Exception {
        File queryFile = File.createTempFile("ssearch_query", ".fasta");
        File dbFile = File.createTempFile("ssearch_db", ".fasta");
        File outputFile = File.createTempFile("ssearch_output", ".txt");
        FileWriter queryWriter = new FileWriter(queryFile);
        queryWriter.write(">query\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        queryWriter.close();
        FileWriter dbWriter = new FileWriter(dbFile);
        dbWriter.write(">db\nMKTAYIAKQRQISFVKSHFSRQDILDLWQ\n");
        dbWriter.close();
        int exitCode = Ssearch36Search.run(
                queryFile,
                dbFile,
                "1e-5",
                "10",
                "BLOSUM62",
                outputFile.getAbsolutePath());
        assertEquals(0, exitCode);
    }
}