package gui;

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
import javax.swing.JDialog;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.JTextArea;
import javax.swing.JScrollPane;
import java.awt.Color;
import java.io.File;
import java.util.ArrayList;
import javax.swing.JFileChooser;
import javax.swing.filechooser.FileNameExtensionFilter;

import utilities.UIHelper;
import utilities.MultipleSequenceParser;
import utilities.Sequence;
import utilities.Ssearch36Search;
import javax.swing.JSeparator; // ── ADDED: separator line

/**
 * Graphical user interface for running a BLASTN-like nucleotide sequence
 * alignment tool.
 *
 * <p>
 * This class provides a Swing-based interface that allows users to:
 * <ul>
 * <li>Input nucleotide sequences in FASTA format</li>
 * <li>Upload query and database FASTA files</li>
 * <li>Configure BLAST parameters (E-value, max sequences, scoring matrix)</li>
 * <li>Run sequence similarity searches using Ssearch36</li>
 * </ul>
 *
 * <p>
 * The results are processed and displayed in a separate output GUI window.
 */
public class BlastnGui extends JFrame {

	/**
	 * Backend search engine used for sequence alignment (SSEARCH36 implementation).
	 */
	private static Ssearch36Search ssearch36search = new Ssearch36Search(false);

	/** List of parsed input sequences from user input or file. */
	private ArrayList<Sequence> sequencelist;

	private static final long serialVersionUID = 1L;

	/** Main content panel of the GUI. */
	private JPanel contentPane;

	/** Label displaying the BLASTN tool title. */
	private JLabel txtBlastpAlgorithm;

	/** File selected as query input (FASTA). */
	private File queryFile = null;

	/** File selected as database input (FASTA). */
	public File dbFile = null;

	/** Utility class for styling and GUI helpers. */
	UIHelper ui = new UIHelper();

	/**
	 * Constructs the BLASTN GUI window and initializes all components, including
	 * input fields, file upload controls, and execution buttons.
	 *
	 * <p>
	 * The graphical user interface allows users to either paste sequences directly
	 * or upload FASTA files, configure alignment parameters, and execute a sequence
	 * similarity search.
	 */
	public BlastnGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 943, 676);
		setTitle("EzBLAST — BLASTN"); // added

		contentPane = new JPanel();
		contentPane.setBackground(new Color(13, 17, 28)); // ── CHANGED: dark navy background
		contentPane.setBorder(new EmptyBorder(25, 35, 25, 35)); // ── CHANGED: more padding
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 102, 92, 80, 80, 80, 80, 201, 0 };
		gbl_contentPane.rowHeights = new int[] { 0, 0, 0, 27, 0, 0, 0, 0, 39, 0 };
		gbl_contentPane.columnWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// Header of the BLASTP tool
		txtBlastpAlgorithm = new JLabel("BLASTN Algorithm");
		txtBlastpAlgorithm.setFont(new Font("Monospaced", Font.BOLD, 22)); // ── CHANGED: font
		txtBlastpAlgorithm.setForeground(new Color(56, 189, 248)); // ── CHANGED: sky blue
		GridBagConstraints gbc_txtBlastpAlgorithm = new GridBagConstraints();
		gbc_txtBlastpAlgorithm.gridwidth = 4;
		gbc_txtBlastpAlgorithm.anchor = GridBagConstraints.WEST;
		gbc_txtBlastpAlgorithm.insets = new Insets(0, 0, 4, 5);
		gbc_txtBlastpAlgorithm.gridx = 0;
		gbc_txtBlastpAlgorithm.gridy = 0;
		contentPane.add(txtBlastpAlgorithm, gbc_txtBlastpAlgorithm);

		// Help button
		JButton btnHelp = new JButton("Help");
		ui.roundStyle(btnHelp, new Color(52, 211, 153), new Color(13, 17, 28));
		btnHelp.addActionListener(e -> {
			JFrame helpFrame = new JFrame("BLASTN Help");
			helpFrame.setSize(400, 300);
			JTextArea textArea = new JTextArea();
			textArea.setText(
					"BLASTN Instructions:\n\n" + "1. Enter your sequence(s) in FASTA format in the text box or\n"
							+ "    click Upload Input Sequence to upload a file.\n"
							+ "2. Click Upload Database (there is no default),\n"
							+ "3. Adjust E-value, max sequences, and scoring matrix if needed.\n"
							+ "4. Click BLAST.\n\n" + "Results will appear in your Downloads folder.");
			textArea.setEditable(false);
			textArea.setLineWrap(true);
			textArea.setWrapStyleWord(true);
			JScrollPane sp = new JScrollPane(textArea);
			helpFrame.getContentPane().add(sp);
			helpFrame.setLocationRelativeTo(null);
			helpFrame.setVisible(true);
		});
		GridBagConstraints gbc_btnHelp = new GridBagConstraints();
		gbc_btnHelp.anchor = GridBagConstraints.EAST;
		gbc_btnHelp.insets = new Insets(0, 0, 5, 0);
		gbc_btnHelp.gridx = 6;
		gbc_btnHelp.gridy = 0;
		contentPane.add(btnHelp, gbc_btnHelp);

		// added: separator below title
		JSeparator sep = new JSeparator();
		sep.setForeground(new Color(30, 41, 59));
		GridBagConstraints gbc_sep = new GridBagConstraints();
		gbc_sep.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep.gridwidth = 7;
		gbc_sep.insets = new Insets(0, 0, 10, 0);
		gbc_sep.gridx = 0;
		gbc_sep.gridy = 1;
		contentPane.add(sep, gbc_sep);

		// Label for text box

		JLabel lblEnterSeqeuence = ui.label("Enter sequence in FASTA format");
		GridBagConstraints gbc_lblEnterSeqeuence = new GridBagConstraints();
		gbc_lblEnterSeqeuence.insets = new Insets(0, 0, 4, 5);
		gbc_lblEnterSeqeuence.fill = GridBagConstraints.BOTH;
		gbc_lblEnterSeqeuence.gridx = 0;
		gbc_lblEnterSeqeuence.gridy = 1; // sits next to separator visually
		contentPane.add(lblEnterSeqeuence, gbc_lblEnterSeqeuence);

		// Scroll pane of the interface
		JTextArea txtrInputsequence = new JTextArea();
		txtrInputsequence.setBackground(new Color(22, 28, 45)); // ── CHANGED: dark card
		txtrInputsequence.setForeground(new Color(52, 211, 153)); // ── CHANGED: teal text
		txtrInputsequence.setCaretColor(new Color(52, 211, 153)); // ── ADDED: caret color
		txtrInputsequence.setFont(new Font("Monospaced", Font.PLAIN, 12)); // ── CHANGED: font

		JScrollPane scrollPane = new JScrollPane(txtrInputsequence);
		scrollPane.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); // ── ADDED: border
		GridBagConstraints gbc_scrollPane = new GridBagConstraints();
		gbc_scrollPane.fill = GridBagConstraints.BOTH;
		gbc_scrollPane.gridwidth = 7;
		gbc_scrollPane.insets = new Insets(0, 0, 10, 0);
		gbc_scrollPane.gridx = 0;
		gbc_scrollPane.gridy = 2;
		contentPane.add(scrollPane, gbc_scrollPane);

		// ── Upload sequence button ───────────────────────────────────────────
		GridBagConstraints gbc_btnInputSequenceUpload = new GridBagConstraints();
		gbc_btnInputSequenceUpload.fill = GridBagConstraints.BOTH;
		gbc_btnInputSequenceUpload.insets = new Insets(0, 0, 8, 5);
		gbc_btnInputSequenceUpload.gridx = 0;
		gbc_btnInputSequenceUpload.gridy = 3;

		// Button for upload input sequence (FASTA file)
		final JButton btnInputSequenceUpload = new JButton("Upload Input Sequence (FASTA file)");
		ui.roundStyle(btnInputSequenceUpload, new Color(22, 28, 45), new Color(13, 17, 28));
		btnInputSequenceUpload.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				if (!txtrInputsequence.getText().trim().isEmpty()) {
					JOptionPane.showMessageDialog(BlastnGui.this, "Please clear the text area before uploading a file.",
							"Input Error", JOptionPane.WARNING_MESSAGE);
					return;
				}
				JFileChooser fileChooser = new JFileChooser();
				FileNameExtensionFilter fasta_filter = new FileNameExtensionFilter("Fasta file", "fasta");
				fileChooser.setDialogTitle("Select Query FASTA File");
				fileChooser.setFileFilter(fasta_filter);
				if (fileChooser.showOpenDialog(BlastnGui.this) == JFileChooser.APPROVE_OPTION) {
					queryFile = fileChooser.getSelectedFile();
					btnInputSequenceUpload.setText("Selected: " + queryFile.getName());
					btnInputSequenceUpload.setForeground(new Color(52, 211, 153)); // ── ADDED: green on select
				}
			}
		});
		contentPane.add(btnInputSequenceUpload, gbc_btnInputSequenceUpload);

		// Label to show if file is actually FASTA
		JLabel lblUploadInputFastaFile = new JLabel("");
		GridBagConstraints gbc_lblUploadInputFastaFile = new GridBagConstraints();
		gbc_lblUploadInputFastaFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblUploadInputFastaFile.gridx = 1;
		gbc_lblUploadInputFastaFile.gridy = 3;
		contentPane.add(lblUploadInputFastaFile, gbc_lblUploadInputFastaFile);

		// ── Upload database button ───────────────────────────────────────────
		final JButton btnUploadDatabase = new JButton("Upload Database (FASTA file)");
		ui.roundStyle(btnUploadDatabase, new Color(22, 28, 45), new Color(13, 17, 28));
		btnUploadDatabase.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JFileChooser fileChooser = new JFileChooser();
				fileChooser.setDialogTitle("Select Database FASTA File");
				if (fileChooser.showOpenDialog(BlastnGui.this) == JFileChooser.APPROVE_OPTION) {
					dbFile = fileChooser.getSelectedFile();
					btnUploadDatabase.setText("Database: " + dbFile.getName());
					btnUploadDatabase.setForeground(new Color(52, 211, 153)); // ── ADDED: green on select
				}
			}
		});
		GridBagConstraints gbc_btnUploadDatabase = new GridBagConstraints();
		gbc_btnUploadDatabase.fill = GridBagConstraints.BOTH;
		gbc_btnUploadDatabase.insets = new Insets(0, 0, 8, 5);
		gbc_btnUploadDatabase.gridx = 0;
		gbc_btnUploadDatabase.gridy = 4;
		contentPane.add(btnUploadDatabase, gbc_btnUploadDatabase);

		// Label for Database button
		JLabel lblUploadDatabaseFastaFile = new JLabel("");
		GridBagConstraints gbc_lblUploadDatabaseFastaFile = new GridBagConstraints();
		gbc_lblUploadDatabaseFastaFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblUploadDatabaseFastaFile.gridx = 1;
		gbc_lblUploadDatabaseFastaFile.gridy = 4;
		contentPane.add(lblUploadDatabaseFastaFile, gbc_lblUploadDatabaseFastaFile);

		// ── E-value label and dropdown ───────────────────────────────────────

		JLabel lblEvalue = ui.boldLabel("E-value Threshold");
		GridBagConstraints gbc_lblEvalue = new GridBagConstraints();
		gbc_lblEvalue.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblEvalue.insets = new Insets(8, 0, 4, 5);
		gbc_lblEvalue.gridx = 0;
		gbc_lblEvalue.gridy = 5;
		contentPane.add(lblEvalue, gbc_lblEvalue);

		// Drop-down box for E-value thresholds
		JComboBox<String> Evalue = new JComboBox<String>();
		Evalue.setModel(new DefaultComboBoxModel<>(new String[] { "1e-50", "1e-10", "1e-5", "1e-2", "1e-1", "1" }));
		Evalue.setBackground(new Color(22, 28, 45)); // ── CHANGED
		Evalue.setForeground(new Color(226, 232, 240)); // ── CHANGED
		Evalue.setFont(new Font("Monospaced", Font.PLAIN, 11)); // ── CHANGED
		Evalue.setSelectedIndex(3);
		GridBagConstraints gbc_Evalue = new GridBagConstraints();
		gbc_Evalue.anchor = GridBagConstraints.WEST;
		gbc_Evalue.insets = new Insets(8, 0, 4, 5);
		gbc_Evalue.gridx = 1;
		gbc_Evalue.gridy = 5;
		contentPane.add(Evalue, gbc_Evalue);

		// ── Max sequences label and dropdown ─────────────────────────────────

		JLabel lblMaxSeqs = ui.boldLabel("Maximum Number of Sequences:");
		GridBagConstraints gbc_lblMaxSeqs = new GridBagConstraints();
		gbc_lblMaxSeqs.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblMaxSeqs.insets = new Insets(0, 0, 4, 5);
		gbc_lblMaxSeqs.gridx = 0;
		gbc_lblMaxSeqs.gridy = 6;
		contentPane.add(lblMaxSeqs, gbc_lblMaxSeqs);

		// Drop-down box for maximum number of sequences
		JComboBox<String> MaxSeqs = new JComboBox<String>();
		MaxSeqs.setModel(new DefaultComboBoxModel<>(new String[] { "10", "50", "100", "250", "500", "1000", "5000" }));
		MaxSeqs.setBackground(new Color(22, 28, 45)); // ── CHANGED
		MaxSeqs.setForeground(new Color(226, 232, 240)); // ── CHANGED
		MaxSeqs.setFont(new Font("Monospaced", Font.PLAIN, 11)); // ── CHANGED
		MaxSeqs.setSelectedIndex(2);
		GridBagConstraints gbc_MaxSeqs = new GridBagConstraints();
		gbc_MaxSeqs.anchor = GridBagConstraints.WEST;
		gbc_MaxSeqs.insets = new Insets(0, 0, 4, 5);
		gbc_MaxSeqs.gridx = 1;
		gbc_MaxSeqs.gridy = 6;
		contentPane.add(MaxSeqs, gbc_MaxSeqs);

		// ── Scoring matrix label and dropdown ────────────────────────────────

		JLabel lblScoringMatric = ui.boldLabel("Scoring Matrix");
		GridBagConstraints gbc_lblScoringMatric = new GridBagConstraints();
		gbc_lblScoringMatric.fill = GridBagConstraints.HORIZONTAL;
		gbc_lblScoringMatric.insets = new Insets(0, 0, 4, 5);
		gbc_lblScoringMatric.gridx = 0;
		gbc_lblScoringMatric.gridy = 7;
		contentPane.add(lblScoringMatric, gbc_lblScoringMatric);

		// Drop-down for scoring matrix
		JComboBox<String> ScoringMatrix = new JComboBox<String>();
		ScoringMatrix.setModel(new DefaultComboBoxModel<>(new String[] { "BLOSUM45", "BLOSUM50", "BLOSUM62", "BLOSUM80",
				"BLOSUM90", "PAM30", "PAM70", "PAM250" }));
		ScoringMatrix.setBackground(new Color(22, 28, 45)); // ── CHANGED
		ScoringMatrix.setForeground(new Color(226, 232, 240)); // ── CHANGED
		ScoringMatrix.setFont(new Font("Monospaced", Font.PLAIN, 11)); // ── CHANGED
		ScoringMatrix.setSelectedIndex(2);
		GridBagConstraints gbc_ScoringMatrix = new GridBagConstraints();
		gbc_ScoringMatrix.fill = GridBagConstraints.BOTH;
		gbc_ScoringMatrix.anchor = GridBagConstraints.WEST;
		gbc_ScoringMatrix.insets = new Insets(0, 0, 4, 5);
		gbc_ScoringMatrix.gridx = 1;
		gbc_ScoringMatrix.gridy = 7;
		contentPane.add(ScoringMatrix, gbc_ScoringMatrix);

		// ── BLAST button — rounded filled blue ───────────────────────────────
		JButton btnBLAST = new JButton("BLAST");
		ui.roundStyle(btnBLAST, new Color(56, 189, 248), new Color(13, 17, 28));
		btnBLAST.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (dbFile != null) {
					try {
<<<<<<< HEAD
						MultipleSequenceParser multSeqs = new MultipleSequenceParser();
						sequencelist = null;
						String raw = txtrInputsequence.getText();

						if (raw != null && !raw.trim().isEmpty()) {
							sequencelist = multSeqs.parseMultipleSeqs(raw);
						}
						if (queryFile != null) {
							sequencelist = multSeqs.parseMultipleSeqs(queryFile);
						}
					} catch (IllegalArgumentException ex) {
						JOptionPane.showMessageDialog(BlastnGui.this, ex.getMessage(), "Input Error",
								JOptionPane.WARNING_MESSAGE);
						return;
					}
					if (sequencelist == null) {
						JOptionPane.showMessageDialog(BlastnGui.this, "Please provide a sequence via textbox or file",
								"Input Error", JOptionPane.WARNING_MESSAGE);
					} else {

						try {
							// show message that BLAST is running
							JDialog dialog = new JDialog(BlastnGui.this, "Loading", false);
							JLabel loadingLabel = new JLabel("BLAST is running, please wait...", JLabel.CENTER);
							loadingLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
							loadingLabel.setBorder(new EmptyBorder(20, 30, 20, 30));
							dialog.getContentPane().add(loadingLabel);
							dialog.pack();
							dialog.setLocationRelativeTo(BlastnGui.this);
							dialog.setVisible(true);
							dialog.paintAll(dialog.getGraphics());

							Sequence sequence = null;
							ArrayList<File> fileList = new ArrayList<File>();
							ArrayList<String> headerList = new ArrayList<String>();
							for (int i = 0; i < sequencelist.size(); i++) {
								sequence = sequencelist.get(i);
								if (sequence.isProtein() == true) {
									JOptionPane.showMessageDialog(BlastnGui.this,
											"File contains protein sequences.\n"
													+ "Please fix the sequence or use BLASTP instead.",
											"Search Error", JOptionPane.ERROR_MESSAGE);
									dialog.dispose();
									return;
								}
=======
						// show message that BLAST is running
						JDialog dialog = new JDialog(BlastnGui.this, "Loading", false);
						JLabel loadingLabel = new JLabel("BLAST is running, please wait...", JLabel.CENTER);
						loadingLabel.setFont(new Font("Monospaced", Font.PLAIN, 13));
						loadingLabel.setBorder(new EmptyBorder(20, 30, 20, 30));
						dialog.getContentPane().add(loadingLabel);
						dialog.pack();
						dialog.setLocationRelativeTo(BlastnGui.this);
						dialog.setVisible(true);
						dialog.paintAll(dialog.getGraphics());
						
						Sequence sequence = null;
						ArrayList<File> fileList = new ArrayList<File>();
						ArrayList<String> headerList = new ArrayList<String>();
						for(int i = 0; i < sequencelist.size(); i++) {
							sequence = sequencelist.get(i);
							if(sequence.isProtein()==true) {
								JOptionPane.showMessageDialog(BlastnGui.this,
										"File contains protein sequences.\n"
										+ "Please fix the sequence or use BLASTP instead.",
										"Search Error", JOptionPane.ERROR_MESSAGE);
								dialog.dispose();
								//If the list contains both protein and nucleotide sequences, stop the blast
								//This is because it's not possible to easily tell the user which sequences are ignored
								break;
							}
>>>>>>> 9a76b1ef1d643abb49ff014063b77596066ee41e
								String outPath = "project_data" + File.separator + "ssearch_results.txt";
								ssearch36search.setSequence(sequence);
								ssearch36search.run(dbFile, Evalue.getSelectedItem().toString(),
										MaxSeqs.getSelectedItem().toString(), outPath);

								if (ssearch36search.getErrorCode() == 0) {
									File file = new File("project_data" + File.separator + "temp_output.tsv");
									int filenum = 1;
									while (file.isFile()) {
										file = new File(
												"project_data" + File.separator + "temp_output_" + filenum + ".tsv");
										filenum++;
									}
									ssearch36search.parseBlastCustomDatabase(file);
									String header = "Sequence";
									fileList.add(file);
									headerList.add(header);
								} else {
									JOptionPane.showMessageDialog(BlastnGui.this,
											"SSEARCH36 failed (exit code " + ssearch36search.getErrorCode() + ").\n"
													+ "Check that ssearch36.exe exists in the tools folder.",
											"Search Error", JOptionPane.ERROR_MESSAGE);
									dialog.dispose();

								}
								// close BLAST running dialog
								dialog.dispose();

								BlastOutputGuiFunctions blastpout = new BlastOutputGui(fileList, headerList);
								blastpout.setLocationRelativeTo(null);
								blastpout.setVisible(true);
							}
						} catch (Exception ex) {

							JOptionPane.showMessageDialog(BlastnGui.this, "SSEARCH36 failed: " + ex.getMessage(),
									"Search Error", JOptionPane.ERROR_MESSAGE);
						}
					}

				} else {
					JOptionPane.showMessageDialog(BlastnGui.this, "Please upload a database file", "Database Error",
							JOptionPane.WARNING_MESSAGE);

				}
			}
		});

		GridBagConstraints gbc_btnBLAST = new GridBagConstraints();
		gbc_btnBLAST.anchor = GridBagConstraints.WEST; // ── CHANGED: fits to text width
		gbc_btnBLAST.insets = new Insets(12, 0, 0, 5);
		gbc_btnBLAST.gridx = 0;
		gbc_btnBLAST.gridy = 8;
		contentPane.add(btnBLAST, gbc_btnBLAST);

		JButton btnCLEAR = new JButton("Clear");
		ui.roundStyle(btnCLEAR, new Color(220, 80, 80), new Color(13, 17, 28));
		btnCLEAR.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				txtrInputsequence.setText(""); // maakt tekstbox leeg
				queryFile = null; // removes input-sequence file to no file selected
				dbFile = null; // removes db file to no file selected
				sequencelist = null; // remove sequence list
				// put text back in import button
				btnInputSequenceUpload.setText("Upload Input Sequence (FASTA file)");
				btnUploadDatabase.setText("Upload Database (FASTA file)");
				// make color white again
				btnInputSequenceUpload.setForeground(Color.WHITE);
				btnUploadDatabase.setForeground(Color.WHITE);

			}
		});

		GridBagConstraints gbc_btnCLEAR = new GridBagConstraints();
		gbc_btnCLEAR.anchor = GridBagConstraints.WEST;
		gbc_btnCLEAR.insets = new Insets(12, 10, 0, 5); // 10px gap from BLAST button
		gbc_btnCLEAR.gridx = 1; // ── next column after BLAST
		gbc_btnCLEAR.gridy = 8; // ── same row as BLAST
		contentPane.add(btnCLEAR, gbc_btnCLEAR);

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}