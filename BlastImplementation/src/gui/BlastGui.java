package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.awt.Dimension;
//Imports to handle file selection and windows file explorer
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.util.Scanner;
import java.util.ArrayList;

import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastResult;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtHit;
import utilities.BlastpSearch;
import utilities.MultipleSequenceParser;
import utilities.Sequence;
import utilities.Statistics;
import utilities.Ssearch36Search;


public class BlastGui extends JFrame {
	
	private static final long serialVersionUID = 1L;
    private JPanel contentPane;
    private JLabel txtBlastpAlgorithm;

    // Variables to store the selected files
    public File queryFile = null;
    public File dbFile = null;

	/**
	 * Create the frame.
	 */
//    public BlastGui(File fastaFile) {
//    	this.queryFile = fastaFile;
//    }
    
	public BlastGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 676);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{102, 92, 80, 80, 80, 80, 201, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 0, 27, 0, 0, 0, 0, 39, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// Header of the BLASTP tool 
		txtBlastpAlgorithm = new JLabel();
		txtBlastpAlgorithm.setFont(new Font("Tahoma", Font.BOLD, 18));
		txtBlastpAlgorithm.setText("BLASTP ALGORITHM");
		GridBagConstraints gbc_txtBlastpAlgorithm = new GridBagConstraints();
		gbc_txtBlastpAlgorithm.gridwidth = 2;
		gbc_txtBlastpAlgorithm.anchor = GridBagConstraints.ABOVE_BASELINE_LEADING;
		gbc_txtBlastpAlgorithm.insets = new Insets(5, 5, 5, 5);
		gbc_txtBlastpAlgorithm.gridx = 2;
		gbc_txtBlastpAlgorithm.gridy = 0;
		contentPane.add(txtBlastpAlgorithm, gbc_txtBlastpAlgorithm);
		
		// Help button 
		JButton btnHelp = new JButton("Help");
		btnHelp.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.anchor = GridBagConstraints.EAST;
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.gridx = 6;
		gbc_btnHelp.gridy = 0;
		contentPane.add(btnHelp, gbc_btnHelp);
		btnHelp.addActionListener(e -> {
		    JFrame helpFrame = new JFrame("BLASTP Help");
		    helpFrame.setSize(400, 300);

		    JTextArea textArea = new JTextArea();
		    textArea.setText(
		        "BLASTP Instructions:\n\n" +
		        "1. Enter your sequence(s) in FASTA format in the text box or click the 'Upload Input Sequence (FASTA file)' button to upload your own file.\n" +
		        "2. Click the upload database button if you want to upload your own database, or ignore to use UNIPROT-SWISSPROT databases.\n" +
		        "3. Adjust the e-value threshold, maximum number of sequences, and scoring matrix if necessary, tool uses default otherwise.\n" +
		        "4. Click 'BLAST'\n\n" +
		        "Results will show similar protein sequences to your input sequence in your Downloads folder."
		    );
		    textArea.setEditable(false);
		    textArea.setLineWrap(true);
		    textArea.setWrapStyleWord(true);

		    JScrollPane scrollPane = new JScrollPane(textArea);
		    helpFrame.getContentPane().add(scrollPane);

		    helpFrame.setLocationRelativeTo(null);
		    helpFrame.setVisible(true);
		});
		
		// Label for text box 
		JLabel lblEnterSeqeuence = new JLabel("Enter sequence in FASTA format:");
		lblEnterSeqeuence.setFont(new Font("Tahoma", Font.PLAIN, 12));
		GridBagConstraints gbc_lblEnterSeqeuence = new GridBagConstraints();
		gbc_lblEnterSeqeuence.insets = new Insets(0, 5, 5, 5);
		gbc_lblEnterSeqeuence.fill = GridBagConstraints.BOTH;
		gbc_lblEnterSeqeuence.gridx = 0;
		gbc_lblEnterSeqeuence.gridy = 1;
		contentPane.add(lblEnterSeqeuence, gbc_lblEnterSeqeuence);
		
		// Scroll pane of the interface 
		JScrollPane scrollPane = new JScrollPane();
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 5, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		JTextArea txtrInputsequence = new JTextArea();
		scrollPane.setViewportView(txtrInputsequence);
		GridBagConstraints gbc_btnInputSequenceUpload = new GridBagConstraints();
		gbc_btnInputSequenceUpload.fill = GridBagConstraints.BOTH;
		gbc_btnInputSequenceUpload.insets = new Insets(5, 5, 10, 5);
		gbc_btnInputSequenceUpload.gridx = 0;
		gbc_btnInputSequenceUpload.gridy = 3;

		// Button for upload input sequence (FASTA file)
		final JButton btnInputSequenceUpload = new JButton("Upload Input Sequence (FASTA file)");
		btnInputSequenceUpload.setBackground(Color.WHITE);
		btnInputSequenceUpload.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInputSequenceUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				if (!txtrInputsequence.getText().trim().isEmpty()) {
		            JOptionPane.showMessageDialog(BlastGui.this, 
		                "Please clear the text area before uploading a file.", 
		                "Input Error", 
		                JOptionPane.WARNING_MESSAGE);
		            return;
		        }
				
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fasta_filter = new FileNameExtensionFilter("Fasta file", "fasta");
				fileChooser.setDialogTitle("Select Query FASTA File");
				fileChooser.setFileFilter(fasta_filter);
				if (fileChooser.showOpenDialog(BlastGui.this) == JFileChooser.APPROVE_OPTION) { {
						File selectedFile = fileChooser.getSelectedFile();
						
						try {
					        Sequence sequence = new Sequence(selectedFile); // runs verifySequence() internally
					        // If no exception was thrown, the sequence is valid
					        queryFile = selectedFile;
					        btnInputSequenceUpload.setText("Selected: " + queryFile.getName());

					    } catch (IllegalArgumentException e) { // replace with whatever exception verifySequence() throws
					        JOptionPane.showMessageDialog(BlastGui.this,
					            "Invalid FASTA sequence: " + e.getMessage(),
					            "Validation Error",
					            JOptionPane.ERROR_MESSAGE);
					        // queryFile stays null / unchanged — no file is accepted
					  
						//btnInputSequenceUpload.setText("Selected: " + queryFile.getName());
						//SequenceValidator path = new SequenceValidator(queryFile.getPath()); // checks the the sequence and gives the path 
					}
				}
			}}}
			);
		contentPane.add(btnInputSequenceUpload, gbc_btnInputSequenceUpload);

		// Label to show if file is actually FASTA
		JLabel lblUploadInputFastaFile = new JLabel("");
		GridBagConstraints gbc_lblUploadInputFastaFile = new GridBagConstraints();
		gbc_lblUploadInputFastaFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblUploadInputFastaFile.gridx = 1;
		gbc_lblUploadInputFastaFile.gridy = 3;
		contentPane.add(lblUploadInputFastaFile, gbc_lblUploadInputFastaFile);
		
		// Adding logic to handle file selection for database
		final JButton btnUploadDatabase = new JButton("Upload Database (FASTA file)");
		btnUploadDatabase.setBackground(Color.WHITE);
		btnUploadDatabase.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnUploadDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select Database FASTA File");
				if (fileChooser.showOpenDialog(BlastGui.this) == JFileChooser.APPROVE_OPTION) {
					try {
						// Validate database file at upload time
						File selectedFile = fileChooser.getSelectedFile();
						new Sequence(selectedFile);
						dbFile = selectedFile;
						btnUploadDatabase.setText("Database: " + dbFile.getName());
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(BlastGui.this,
							"Invalid database file: " + ex.getMessage(),
							"Database Error",
							JOptionPane.WARNING_MESSAGE);
					}
				}
			}
		});

		// Upload Database button 
		GridBagConstraints gbc_btnUploadDatabase = new GridBagConstraints();
		gbc_btnUploadDatabase.fill = GridBagConstraints.BOTH;
		gbc_btnUploadDatabase.insets = new Insets(0, 5, 5, 5);
		gbc_btnUploadDatabase.gridx = 0;
		gbc_btnUploadDatabase.gridy = 4;
		contentPane.add(btnUploadDatabase, gbc_btnUploadDatabase);
		
		// Label for Database button 
		JLabel lblUploadDatabaseFastaFile = new JLabel("New label");
		GridBagConstraints gbc_lblUploadDatabaseFastaFile = new GridBagConstraints();
		gbc_lblUploadDatabaseFastaFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblUploadDatabaseFastaFile.gridx = 1;
		gbc_lblUploadDatabaseFastaFile.gridy = 4;
		contentPane.add(lblUploadDatabaseFastaFile, gbc_lblUploadDatabaseFastaFile);
		
		// Label for E-value thresholds
		JLabel lblEvalue = new JLabel("E-value Threshold:");
		lblEvalue.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblEvalue = new GridBagConstraints();
		gbc_lblEvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEvalue.insets = new Insets(10, 5, 5, 5);
		gbc_lblEvalue.gridx = 0;
		gbc_lblEvalue.gridy = 5;
		contentPane.add(lblEvalue, gbc_lblEvalue);
		
		// Drop-down box for E-value thresholds 
		JComboBox<String> Evalue = new JComboBox<String>();
		Evalue.setModel(new DefaultComboBoxModel<String>(new String[] {"1e-50", "1e-10", "1e-5", "1e-2", "1e-1", "1"}));
		Evalue.setBackground(Color.WHITE);
		Evalue.setSelectedIndex(3);
		GridBagConstraints gbc_Evalue = new GridBagConstraints();
		gbc_Evalue.anchor = GridBagConstraints.WEST;
		gbc_Evalue.insets = new Insets(0, 0, 5, 5);
		gbc_Evalue.gridx = 1;
		gbc_Evalue.gridy = 5;
		contentPane.add(Evalue, gbc_Evalue);
		
		// Label for maximum number of sequences
		JLabel lblMaxSeqs = new JLabel("Maximum Number of Sequences:");
		lblMaxSeqs.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblMaxSeqs = new GridBagConstraints();
		gbc_lblMaxSeqs.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMaxSeqs.insets = new Insets(0, 5, 5, 5);
		gbc_lblMaxSeqs.gridx = 0;
		gbc_lblMaxSeqs.gridy = 6;
		contentPane.add(lblMaxSeqs, gbc_lblMaxSeqs);
		
		// Drop-down box for maximum number of sequences
		JComboBox<String> MaxSeqs = new JComboBox<String>();
		MaxSeqs.setModel(new DefaultComboBoxModel<String>(new String[] {"10", "50", "100", "250", "500", "1000", "5000"}));
		MaxSeqs.setBackground(Color.WHITE);
		MaxSeqs.setSelectedIndex(2);
		GridBagConstraints gbc_MaxSeqs = new GridBagConstraints();
		gbc_MaxSeqs.anchor = GridBagConstraints.WEST;
		gbc_MaxSeqs.insets = new Insets(0, 0, 5, 5);
		gbc_MaxSeqs.gridx = 1;
		gbc_MaxSeqs.gridy = 6;
		contentPane.add(MaxSeqs, gbc_MaxSeqs);
		
		// Label for scoring matrix 
		JLabel lblScoringMatric = new JLabel("Scoring Matrix:");
		lblScoringMatric.setFont(new Font("Tahoma", Font.BOLD, 12));
		GridBagConstraints gbc_lblScoringMatric = new GridBagConstraints();
		gbc_lblScoringMatric.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblScoringMatric.insets = new Insets(0, 5, 5, 5);
		gbc_lblScoringMatric.gridx = 0;
		gbc_lblScoringMatric.gridy = 7;
		contentPane.add(lblScoringMatric, gbc_lblScoringMatric);

		// Drop-down for scoring matrix
		JComboBox<String> ScoringMatrix = new JComboBox<String>();
		ScoringMatrix.setModel(new DefaultComboBoxModel<String>(new String[] {"BLOSUM45", "BLOSUM50", "BLOSUM62", "BLOSUM80", "BLOSUM90", "PAM30", "PAM70", "PAM250"}));
		ScoringMatrix.setBackground(Color.WHITE);
		ScoringMatrix.setSelectedIndex(2);
		GridBagConstraints gbc_ScoringMatrix = new GridBagConstraints();
		gbc_ScoringMatrix.fill = GridBagConstraints.BOTH;
		gbc_ScoringMatrix.anchor = GridBagConstraints.WEST;
		gbc_ScoringMatrix.insets = new Insets(0, 0, 5, 5);
		gbc_ScoringMatrix.gridx = 1;
		gbc_ScoringMatrix.gridy = 7;
		contentPane.add(ScoringMatrix, gbc_ScoringMatrix);
		
		// Blast button 
		JButton btnBLAST = new JButton("BLAST");
		btnBLAST.setForeground(new Color(0, 0, 0));
		btnBLAST.setBackground(Color.WHITE);
		btnBLAST.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBLAST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<Sequence> sequencelist = null;
				try {
					String raw = txtrInputsequence.getText();
					// Textbox input still validated here since there is no upload event for it
					if (raw != null && !raw.trim().isEmpty()) {
						sequencelist = MultipleSequenceParser.parseMultipleSeqs(raw);
					}
					// Files are already validated at upload time, just parse here
					if (queryFile != null) {
						sequencelist = MultipleSequenceParser.parseMultipleSeqs(queryFile);
					}
				} catch (IllegalArgumentException ex) {
					JOptionPane.showMessageDialog(
					        BlastGui.this,
					        ex.getMessage(),
					        "Input Error",
					        JOptionPane.WARNING_MESSAGE
					    );
					return;
				}
				if (sequencelist == null) {
				    JOptionPane.showMessageDialog(BlastGui.this, 
				    		"Please provide a sequence via textbox or file", 
			                "Input Error", 
			                JOptionPane.WARNING_MESSAGE);
				}
				else {
				
				// database uploaded → always use ssearch36 local search
				if (dbFile != null) {
					try {
						Sequence sequence = sequencelist.get(0);	
						File queryFile = sequence.getFastaFile();
						String outPath = dbFile.getParent() + File.separator + "ssearch_results.txt";
						int exitCode = Ssearch36Search.run(
							queryFile,
							dbFile,
							Evalue.getSelectedItem().toString(),
							MaxSeqs.getSelectedItem().toString(),
							ScoringMatrix.getSelectedItem().toString(),
							outPath								
						);
						if (exitCode == 0) {
							parseBlastCustomDatabase();
							String filename = "temp_output.tsv";
							File file = new File(filename);
							//String seqString = sequence.getSequence();
							//String header = seqString.split("\\r?\\n")[0].split(" ")[0].substring(1);
							String header = "Sequence";
							ArrayList<File> fileList = new ArrayList<File>();
							fileList.add(file);
							ArrayList<String> headerList = new ArrayList<String>();
							headerList.add(header);
							BlastOutputGui blastpout = new BlastOutputGui(fileList, headerList);
							blastpout.setLocationRelativeTo(null);
						    blastpout.setVisible(true);
							
							JOptionPane.showMessageDialog(BlastGui.this,
								"Search complete!\nResults saved to:\n" + outPath);
						} else {
							JOptionPane.showMessageDialog(BlastGui.this,
								"SSEARCH36 failed (exit code " + exitCode + ").\n"
								+ "Check that ssearch36.exe exists in the tools folder.",
								"Search Error", JOptionPane.ERROR_MESSAGE);
						}
					} catch (Exception ex) {
						JOptionPane.showMessageDialog(BlastGui.this,
							"SSEARCH36 failed: " + ex.getMessage(),
							"Search Error", JOptionPane.ERROR_MESSAGE);
					}
					return; // stop here — don't fall through to UniProt
				}
				ArrayList<File> fileList = new ArrayList<File>();
				ArrayList<String> headerList = new ArrayList<String>();
				
				// no database uploaded → search against UniProt online
				for(int i = 0; i < sequencelist.size(); i++) {
					Sequence sequence = sequencelist.get(i);
					Object[] fileData = performBlastP(sequence, Float.valueOf(Evalue.getSelectedItem().toString()), Integer.parseInt(MaxSeqs.getSelectedItem().toString()));
					File file = (File) fileData[0];
					String header = (String) fileData[1];
					fileList.add(file);
					headerList.add(header);
				}
				BlastOutputGui blastpout = new BlastOutputGui(fileList, headerList);
				blastpout.setLocationRelativeTo(null);
			    blastpout.setVisible(true);
				}
			}
		});

		GridBagConstraints gbc_btnBLAST = new GridBagConstraints();
		gbc_btnBLAST.fill = GridBagConstraints.BOTH;
		gbc_btnBLAST.insets = new Insets(0, 5, 0, 5);
		gbc_btnBLAST.gridx = 0;
		gbc_btnBLAST.gridy = 8;
		contentPane.add(btnBLAST, gbc_btnBLAST);
		
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	}

	private static Object[] performBlastP(Sequence sequence, float mineval, int maxseq) {
		String seqstring = sequence.getSequence();
		BlastResult<UniProtHit> uniprotblastResult = BlastpSearch.runUniprotBlast(seqstring);

		File file = new File("project_data"+File.separator+"temp_output.tsv");
		int filenum = 1;
		while(file.isFile()) {
			file = new File("project_data"+File.separator+"temp_output_"+filenum+".tsv");
			filenum++;
		}
		BlastpSearch.writeUniprotBlastOutput(uniprotblastResult, mineval, maxseq, file);
		String header = seqstring.split("\\r?\\n")[0].split(" ")[0].substring(1);
		Object[] fileData = {file, header};
		return fileData;
	}
	
	private void parseBlastCustomDatabase() {
		// Claude generated this parser code for us based on a non functional template of us.
		// We checked that the code works correctly.

	    // ── Build path to the SSEARCH output file ─────────────────────────────
	    // The ssearch_results.txt is expected in the same folder as the database file
	    String pathBlastFile = dbFile.getParent() + File.separator + "ssearch_results.txt";
	    File blastOutputCustomDatabase = new File(pathBlastFile);
	    String tsvFileName = "temp_output.tsv";

	    try (Scanner blastOutput = new Scanner(blastOutputCustomDatabase)) {

	        // false = overwrite existing file (not append)
	        FileWriter blastOutputTsv = new FileWriter(tsvFileName, false);

	        // ── Write TSV header row ───────────────────────────────────────────
	        blastOutputTsv.write(
	            "hit\tid\tdescription\tmatch_sequence\teval\tbitscore\tidentity\t" +
	            "query_sequence\tquery_start\tquery_end\tmatch_start\tmatch_end\n"
	        );

	        int hitNum = 0; // counts how many hits have been processed
	        String pendingLine = null;

	        // ── Scan through every line of the SSEARCH output ─────────────────
	        while (blastOutput.hasNextLine()) {
	        	
	        	String line;
	        	// Take the previous line of the inner while loop after the first sequence
	        	if (pendingLine != null) {
	        		line = pendingLine;
	        		pendingLine = null;
	        	} else {
	        		line = blastOutput.nextLine();
	        	}

	            // Every new hit block starts with >>
	            // Example: >>sp|P68871|HBB_HUMAN Hemoglobin subunit beta OS=Homo sa
	            if (line.startsWith(">>")) {

	                hitNum++;

	                // ── STEP 1: Parse the >> header line ──────────────────────
	                // Remove the leading >> and split by | to get ID and description
	                // Example after removing >>: sp|P68871|HBB_HUMAN Hemoglobin...
	                // pipeParts[0] = "sp"
	                // pipeParts[1] = "P68871"          <- UniProt accession ID
	                // pipeParts[2] = "HBB_HUMAN Hemoglobin subunit beta OS=Homo sa..."
	                //String withoutArrows = line.substring(2).trim();
	                String[] pipeParts   = line.split("\\|");
	                String id            = pipeParts[1].trim();

	                // Description is everything after the gene name (HBB_HUMAN)
	                // split by space with limit 2 to get ["HBB_HUMAN", "Hemoglobin..."]
	                String description = pipeParts[2].trim();

	                // ── STEP 2: Parse the score line ──────────────────────────
	                // Example: s-w opt: 780  Z-score: 1810.1  bits: 340.9 E(3): 1.5e-098
	                String scoreLine = blastOutput.nextLine().trim();

	                // Extract bit score: split on "bits:" take part after it, trim,
	                // then split by space and take first token
	                // "bits: 340.9 E(3)..." -> ["bits: ", "340.9 E(3)..."] -> "340.9"
	                String bitScore = scoreLine.split("bits:")[1].trim().split(" ")[0];

	                // Extract e-value: split on "E(" take part after it,
	                // then split on "):" and take part after that
	                // "E(3): 1.5e-098" -> ["E(", "3): 1.5e-098"] -> ["3", " 1.5e-098"] -> "1.5e-098"
	                String eval = scoreLine.split("E\\(")[1].split("\\):")[1].trim();

	                // ── STEP 3: Parse the Smith-Waterman summary line ─────────
	                // Example: Smith-Waterman score: 780; 100.0% identity (100.0% similar) in 147 aa overlap (1-147:1-147)
	                String swLine = blastOutput.nextLine().trim();

	                // Extract identity: split on ";" take right part, trim,
	                // split on "%" take left part
	                // "; 100.0% identity..." -> "100.0% identity..." -> "100.0"
	                String identity = swLine.split(";")[1].trim().split("%")[0].trim();

	                // Extract overlap length: split on " in " take right part,
	                // split on "overlap" take left part
	                // "...in 147 aa overlap..." -> "147 aa overlap..." -> "147 aa"
	                String overlap = swLine.split(" in ")[1].trim().split("overlap")[0].trim();

	                // Extract positions using lastIndexOf to get the LAST ( in the line
	                // This avoids accidentally picking up "(100.0% similar)" earlier in the line
	                // "...(1-147:1-147)" -> "1-147:1-147"
	                String positions = swLine
	                    .substring(swLine.lastIndexOf("(") + 1)
	                    .replace(")", "")
	                    .trim();

	                // Split positions "1-147:1-147" by ":" to separate query and match ranges
	                // queryRange = "1-147", matchRange = "1-147"
	                String queryRange = positions.split(":")[0];
	                String matchRange = positions.split(":")[1];

	                String queryStart = queryRange.split("-")[0]; // "1"
	                String queryEnd   = queryRange.split("-")[1]; // "147"
	                String matchStart = matchRange.split("-")[0]; // "1"
	                String matchEnd   = matchRange.split("-")[1]; // "147"

	                // ── STEP 4: Extract full sequences across all alignment blocks ──
	                // The alignment is shown in blocks of 60 characters, like:
	                //
	                //   sp|P68 MVHLTPEEKS...   <- query sequence block
	                //          ::::::::::      <- alignment symbols (skip this)
	                //   sp|P68 MVHLTPEEKS...   <- match sequence block
	                //
	                // We loop through all blocks and concatenate to get the full sequence

	                StringBuilder querySeq = new StringBuilder();
	                StringBuilder matchSeq = new StringBuilder();

	                // A sequence line matches this pattern:
	                // starts with "sp|" followed by non-space chars, then whitespace, then letters
	                // Example: "sp|P68 MVHLTPEEKS..."
	                // We use a regex: starts with sp| then word chars then space(s) then capital letters
	                String seqLinePattern = "sp\\|\\S+\\s+[A-Z-]+";

	                boolean firstBlockFound = false; // tracks if we found the first query line

	                while (blastOutput.hasNextLine()) {
	                    String seqLine = blastOutput.nextLine();

	                    // Check if this line is a sequence line
	                    if (seqLine.trim().matches(seqLinePattern)) {

	                        if (!firstBlockFound) {
	                            // First sequence line in a block = query sequence
	                            // split by one or more spaces, sequence is the last token
	                            String[] parts = seqLine.trim().split("\\s+");
	                            querySeq.append(parts[parts.length - 1]);

	                            // Next line is the :::: alignment line — skip it
	                            blastOutput.nextLine();

	                            // Line after that is the match sequence
	                            String matchLine = blastOutput.nextLine().trim();
	                            String[] matchParts = matchLine.split("\\s+");
	                            matchSeq.append(matchParts[matchParts.length - 1]);

	                            firstBlockFound = true;

	                        } else {
	                            // Subsequent blocks — same structure, just append
	                            String[] parts = seqLine.trim().split("\\s+");
	                            querySeq.append(parts[parts.length - 1]);

	                            blastOutput.nextLine(); // skip :::: line

	                            String matchLine = blastOutput.nextLine().trim();
	                            String[] matchParts = matchLine.split("\\s+");
	                            matchSeq.append(matchParts[matchParts.length - 1]);
	                        }

	                    } else if (firstBlockFound && seqLine.startsWith(">>")) {
	                        // We hit the next hit block — stop reading sequences
	                        // Note: this line will be missed so we handle it below
	                    	pendingLine = seqLine;
	                        break;
	                    } else if (firstBlockFound && seqLine.trim().isEmpty()) {
	                        // An empty line after sequences means the block ended
	                        // Continue to check if there's another block coming
	                        continue;
	                    }
	                }

	                // ── STEP 5: Write this hit to the TSV file ────────────────
	                blastOutputTsv.write(
	                    hitNum      + "\t" +
	                    id          + "\t" +
	                    description + "\t" +
	                    matchSeq.toString() + "\t" +
	                    eval        + "\t" +
	                    bitScore    + "\t" +
	                    identity    + "\t" +
	                    querySeq.toString() + "\t" +
	                    queryStart  + "\t" +
	                    queryEnd    + "\t" +
	                    matchStart  + "\t" +
	                    matchEnd    + "\t" + "\n"
	                );
	            }
	        }

	        // ── Close the TSV file after all hits are written ──────────────────
	        blastOutputTsv.close();

	    } catch (FileNotFoundException e) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Could not find the SSEARCH results file at:\n" + pathBlastFile,
	            "File Not Found",
	            JOptionPane.ERROR_MESSAGE
	        );
	    } catch (IOException e) {
	        JOptionPane.showMessageDialog(
	            null,
	            "Failed to write output to TSV file.",
	            "Output Error",
	            JOptionPane.ERROR_MESSAGE
	        );
	    }
	}

	}