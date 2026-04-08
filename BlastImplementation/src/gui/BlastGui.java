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
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.BlastResult;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.UniProtHit;
import utilities.BlastpSearch;
import utilities.Sequence;
import utilities.Statistics;

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
		
		// Button for upload input sequence (FASTA file) 
		final JButton btnInputSequenceUpload = new JButton("Upload Input Sequence (FASTA file)");
		btnInputSequenceUpload.setBackground(Color.WHITE);
		btnInputSequenceUpload.setFont(new Font("Tahoma", Font.PLAIN, 12));
		btnInputSequenceUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fasta_filter = new FileNameExtensionFilter("Fasta file", "fasta"); //Only shows files with .fasta extension in the file chooser
				fileChooser.setDialogTitle("Select Query FASTA File");
				fileChooser.setFileFilter(fasta_filter); //Applies extension filter;
				if (fileChooser.showOpenDialog(BlastGui.this)==JFileChooser.APPROVE_OPTION) {
					queryFile = fileChooser.getSelectedFile();
					btnInputSequenceUpload.setText("Selected: " + queryFile.getName());
					//SequenceValidator path = new SequenceValidator(queryFile.getPath()); // checks the the sequence and gives the path 
				}
			}
		});
		
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
					dbFile = fileChooser.getSelectedFile();
					btnUploadDatabase.setText("Database: " + dbFile.getName());
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
		
		// Blast button 
		JButton btnBLAST = new JButton("BLAST");
		btnBLAST.setForeground(new Color(0, 0, 0));
		btnBLAST.setBackground(Color.WHITE);
		btnBLAST.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnBLAST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				Sequence sequence = null;
				if (txtrInputsequence.getText().isEmpty() == false){
					sequence = new Sequence(txtrInputsequence.getText());
					
				}
				if(queryFile!=null) {
						sequence = new Sequence(queryFile);
					
				}
				
				if(sequence == null) {
					JFrame inputFrame = new JFrame("Error");
					inputFrame.setSize(500, 100);
					inputFrame.setLocationRelativeTo(null);
					JTextArea textArea = new JTextArea();
				    textArea.setText("Please provide a sequence via textbox or file");
				    textArea.setEditable(false);
					textArea.setLineWrap(true);
				    textArea.setWrapStyleWord(true);
					inputFrame.setVisible(true);
				    JScrollPane scrollPane = new JScrollPane(textArea);
				    inputFrame.getContentPane().add(scrollPane);
				}
				else {
					performBlastP(sequence,Float.valueOf(Evalue.getSelectedItem().toString()),Integer.parseInt(MaxSeqs.getSelectedItem().toString()));	
				}
			    
			}
		});
		
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
		GridBagConstraints gbc_btnBLAST = new GridBagConstraints();
		gbc_btnBLAST.fill = GridBagConstraints.BOTH;
		gbc_btnBLAST.insets = new Insets(0, 5, 0, 5);
		gbc_btnBLAST.gridx = 0;
		gbc_btnBLAST.gridy = 8;
		contentPane.add(btnBLAST, gbc_btnBLAST);
		
		
		
		

	}
	private static void performBlastP(Sequence sequence,float mineval, int maxseq) {
		String seqstring = sequence.getSequence();
		BlastResult<UniProtHit> uniprotblastResult = BlastpSearch.runUniprotBlast(seqstring);
		String filename = "temp_output.tsv";
		BlastpSearch.writeUniprotBlastOutput(uniprotblastResult,mineval,maxseq,filename);
		File file = new File(filename);
		String header=seqstring.split("\\r?\\n")[0].split(" ")[0].substring(1);
		BlastOutputGui blastpout = new BlastOutputGui(file,header);
		blastpout.setLocationRelativeTo(null);
	    blastpout.setVisible(true);
		
		
	}
}