
package tests;

// ADDED: File handling
import java.io.File;

// ADDED: The class we're testing
import utilities.Ssearch36Search;

// ADDED: JUnit 3 testing
import junit.framework.TestCase;

// ADDED: Tests for Ssearch36Search
public class Ssearch36SearchTest extends TestCase {
	
	// ADDED: Test that search works
	public void testSsearch36Run() {
		try {
			// Create test query file
			File queryFile = File.createTempFile("test_query_", ".fasta");
			queryFile.deleteOnExit();
			try (java.io.FileWriter fw = new java.io.FileWriter(queryFile)) {
				fw.write(">test_query\n");
				fw.write("MKVLWAALLVTFAGCAKAKAEKVFKQYANDNGVDGWWTYDDATKTFTVTE\n");
			}
			
			// Create test database file
			File dbFile = File.createTempFile("test_db_", ".fasta");
			dbFile.deleteOnExit();
			try (java.io.FileWriter fw = new java.io.FileWriter(dbFile)) {
				fw.write(">test_subject\n");
				fw.write("MKVLWAALLVTFAGCAKAKAEKVFKQYANDNGVDGWWTYDDATKTFTVTE\n");
			}
			
			// Create output file path
			File outputFile = File.createTempFile("test_results_", ".txt");
			outputFile.deleteOnExit();
			String outputPath = outputFile.getAbsolutePath();
			
			// Run the search
			int exitCode = Ssearch36Search.run(
				queryFile,
				dbFile,
				"1e-5",          // evalue
				"10",            // max results
				"BLOSUM62",      // matrix
				outputPath
			);
			
			// Check it succeeded
			assertEquals(0, exitCode);
			
		} catch (Exception ex) {
			fail("Error: " + ex.getMessage());
		}
	}
	
	// ADDED: Test all scoring matrices
	public void testMatrixMapping() {
		String[] matrices = {"BLOSUM45", "BLOSUM50", "BLOSUM62", "BLOSUM80", "BLOSUM90", "PAM30", "PAM70", "PAM250"};
		
		// Try each matrix
		for (String matrix : matrices) {
			try {
				// Create test files
				File queryFile = File.createTempFile("test_query_", ".fasta");
				queryFile.deleteOnExit();
				try (java.io.FileWriter fw = new java.io.FileWriter(queryFile)) {
					fw.write(">test\nMKVLWA\n");
				}
				
				// Create test database
				File dbFile = File.createTempFile("test_db_", ".fasta");
				dbFile.deleteOnExit();
				try (java.io.FileWriter fw = new java.io.FileWriter(dbFile)) {
					fw.write(">test\nMKVLWA\n");
				}
				
				// Create output file
				File outputFile = File.createTempFile("test_results_", ".txt");
				outputFile.deleteOnExit();
				
				// Run search with this matrix
				int exitCode = Ssearch36Search.run(
					queryFile,
					dbFile,
					"1e-5",
					"10",
					matrix,
					outputFile.getAbsolutePath()
				);
				
				// Should succeed
				assertEquals("Matrix " + matrix + " failed", 0, exitCode);
				
			} catch (Exception ex) {
				fail("Matrix " + matrix + " error: " + ex.getMessage());
			}
		}
	}
}

