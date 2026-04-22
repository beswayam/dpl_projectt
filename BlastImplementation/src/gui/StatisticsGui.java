package gui;

import java.awt.Color;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import utilities.GUIutilities;
import utilities.NucleotideStatistics;
import utilities.ProteinStatistics;
import java.awt.GridBagLayout;

import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.math.BigDecimal;
import java.math.RoundingMode;

import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JLabel;

import javax.swing.SwingConstants;
import utilities.Sequence;

import javax.swing.JButton;

import java.awt.event.ActionListener;

import java.awt.event.ActionEvent;

import java.awt.Dimension;
import java.awt.Cursor;

import javax.swing.JSeparator;

/**
 * GUI window for displaying sequence statistics and suggesting a suitable tool.
 * <p>
 * This window reads a FASTA file, determines whether the sequence is protein
 * or nucleotide, displays basic statistics, and suggests BLASTP or BLASTN
 * based on the input type.
 * </p>
 */
public class StatisticsGui extends JFrame {
	
	GUIutilities ui = new GUIutilities();
	
	private static final long serialVersionUID = 1L;
	
	/** Main content panel for the window. */
	private JPanel contentPane;
	
	/** Input FASTA file used to generate statistics. */
	private Sequence seq;

	/**
	 * Creates the statistics window for the provided file.
	 * <p>
	 * The constructor sets up the interface, loads the input sequence, displays
	 * file statistics, and generates a tool suggestion based on the sequence type.
	 * </p>
	 *
	 * @param inputFile FASTA file to analyse
	 */
	public StatisticsGui(Sequence inputSequence) {
		
		
		
		this.seq = inputSequence;
		setBounds(100, 100, 900, 580);
		setTitle("EzBLAST — File Statistics"); // ── CHANGED: window title

		contentPane = new JPanel();
		contentPane.setBackground(new Color(13, 17, 28)); // ── CHANGED: dark navy
		contentPane.setBorder(new EmptyBorder(25, 30, 25, 30)); // ── CHANGED: more padding
		setContentPane(contentPane);

		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[] { 300, 300, 300, 0 };
		gbl_contentPane.rowHeights = new int[] { 30, 10, 0, 10, 500, 0 };
		gbl_contentPane.columnWeights = new double[] { 1.0, 1.0, 1.0, Double.MIN_VALUE };
		gbl_contentPane.rowWeights = new double[] { 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE };
		contentPane.setLayout(gbl_contentPane);

		// ── ADDED: Page title at the top ─────────────────────────────────────
		
		JLabel lblPageTitle = new JLabel("File Statistics");
		lblPageTitle.setFont(new Font("Monospaced", Font.BOLD, 22)); // ── ADDED
		lblPageTitle.setForeground(new Color(56, 189, 248)); // ── ADDED: sky blue
		GridBagConstraints gbc_lblPageTitle = new GridBagConstraints();
		gbc_lblPageTitle.anchor = GridBagConstraints.WEST;
		gbc_lblPageTitle.gridwidth = 3;
		gbc_lblPageTitle.insets = new Insets(0, 0, 5, 0);
		gbc_lblPageTitle.gridx = 0;
		gbc_lblPageTitle.gridy = 0;
		contentPane.add(lblPageTitle, gbc_lblPageTitle);

		// ── ADDED: Separator below title ─────────────────────────────────────
		
		JSeparator sep = new JSeparator();
		sep.setForeground(new Color(30, 41, 59));
		GridBagConstraints gbc_sep = new GridBagConstraints();
		gbc_sep.fill = GridBagConstraints.HORIZONTAL;
		gbc_sep.gridwidth = 3;
		gbc_sep.insets = new Insets(0, 0, 12, 0);
		gbc_sep.gridx = 0;
		gbc_sep.gridy = 1;
		contentPane.add(sep, gbc_sep);

		// ── Column headers ────────────────────────────────────────────────────
		
		JLabel lblInputFile = new JLabel("Input File");
		lblInputFile.setFont(new Font("Monospaced", Font.BOLD, 13)); // ── CHANGED: font
		lblInputFile.setForeground(new Color(52, 211, 153)); // ── CHANGED: teal
		lblInputFile.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblInputFile = new GridBagConstraints();
		gbc_lblInputFile.insets = new Insets(0, 0, 8, 8);
		gbc_lblInputFile.gridx = 0;
		gbc_lblInputFile.gridy = 3;
		contentPane.add(lblInputFile, gbc_lblInputFile);

		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setFont(new Font("Monospaced", Font.BOLD, 13)); // ── CHANGED
		lblStatistics.setForeground(new Color(52, 211, 153)); // ── CHANGED: teal
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.insets = new Insets(0, 0, 8, 8);
		gbc_lblStatistics.gridx = 1;
		gbc_lblStatistics.gridy = 3;
		contentPane.add(lblStatistics, gbc_lblStatistics);

		// statistics header end

		// file overview text area start
		JTextArea textOverviewInput = new JTextArea();
		textOverviewInput.setRows(2);
		GridBagConstraints gbc_textOverviewInput = new GridBagConstraints();
		textForOverviewInput(textOverviewInput, gbc_textOverviewInput);
		// file overview text area end

		// statistics text area start
		JTextArea textStatistics = new JTextArea();
		GridBagConstraints gbc_textStatistics = new GridBagConstraints();
		textForStatistics(textStatistics, gbc_textStatistics);
		// statistics text area stop

		// tools text area start
		JTextArea textTools = new JTextArea();
		textTools.setBackground(new Color(22, 28, 45)); // added
		textTools.setForeground(new Color(226, 232, 240)); // added
		textTools.setFont(new Font("Monospaced", Font.PLAIN, 12)); // added
		textTools.setBorder(new EmptyBorder(10, 10, 10, 10)); // added
		textTools.setOpaque(true); // ← make sure background actually paints
		GridBagConstraints gbc_textTools = new GridBagConstraints();

		textForTools(textTools, gbc_textTools);
	}


	/**
	 * Configures the button that opens the recommended BLAST tool.
	 * <p>
	 * The button text and action depend on whether the loaded sequence is
	 * protein or nucleotide.
	 * </p>
	 *
	 * @param btnGoToTool button used to navigate to the suggested tool
	 * @param gbc_btnGoToTool layout constraints for the button
	 */
	private void btnTool(JButton btnGoToTool, GridBagConstraints gbc_btnGoToTool) {

		ui.applyRoundedStyle(btnGoToTool, new Color(56, 189, 248), new Color(22, 28, 45));

//		GridBagConstraints gbc_btnInputStatistics = new GridBagConstraints();
//		gbc_btnInputStatistics.fill = GridBagConstraints.BOTH;

		String toolToReferTo;

		if (this.seq.isProtein()) {
			toolToReferTo = "BLASTP";
		} else {
			toolToReferTo = "BLASTN";
		}

		btnGoToTool.setText(toolToReferTo);

		btnGoToTool.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				if (seq.isProtein()) {
					BlastpGui stats = new BlastpGui();
					stats.setLocationRelativeTo(null);
					stats.setVisible(true);
				} else {
					BlastnGui blastn = new BlastnGui();
					blastn.setLocationRelativeTo(null);
					blastn.setVisible(true);

				}
			}
		});
	}

	/**
	 * Builds the left panel showing the loaded input sequence.
	 *
	 * @param textOverviewInput text area used to display the sequence
	 * @param gbc_textOverviewInput layout constraints for the sequence panel
	 */
	private void textForOverviewInput(JTextArea textOverviewInput, GridBagConstraints gbc_textOverviewInput) {

		textOverviewInput.setEditable(false);
		textOverviewInput.setLineWrap(true);
		textOverviewInput.setWrapStyleWord(true);
		textOverviewInput.setBackground(new Color(22, 28, 45)); // added
		textOverviewInput.setForeground(new Color(52, 211, 153)); // added
		textOverviewInput.setFont(new Font("Monospaced", Font.PLAIN, 12)); // added
		textOverviewInput.setBorder(new EmptyBorder(10, 10, 10, 10)); // added
		gbc_textOverviewInput.insets = new Insets(0, 0, 0, 5);
		gbc_textOverviewInput.gridx = 0;
		gbc_textOverviewInput.gridy = 4;
		gbc_textOverviewInput.weightx = 1.0;
		gbc_textOverviewInput.weighty = 1.0;
		gbc_textOverviewInput.fill = GridBagConstraints.BOTH;

		JScrollPane scrollPane = new JScrollPane(textOverviewInput);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); // added
		scrollPane.getViewport().setBackground(new Color(22, 28, 45)); // added

		textOverviewInput.append(seq.getSequence());

		JLabel lblToolSuggestions = new JLabel("Tool Suggestions");
		GridBagConstraints gbc_lblToolSuggestions = new GridBagConstraints();
		gbc_lblToolSuggestions.insets = new Insets(0, 0, 5, 0);
		gbc_lblToolSuggestions.gridx = 2;
		gbc_lblToolSuggestions.gridy = 3;
		contentPane.add(lblToolSuggestions, gbc_lblToolSuggestions);
		lblToolSuggestions.setFont(new Font("Monospaced", Font.BOLD, 13)); // ── CHANGED
		lblToolSuggestions.setForeground(new Color(52, 211, 153)); // ── CHANGED: teal
		lblToolSuggestions.setHorizontalAlignment(SwingConstants.CENTER);

		contentPane.add(scrollPane, gbc_textOverviewInput);
	}

	/**
	 * Builds the middle panel containing sequence statistics.
	 *
	 * @param textStatistics text area used to display calculated statistics
	 * @param gbc_textStatistics layout constraints for the statistics panel
	 */
	private void textForStatistics(JTextArea textStatistics, GridBagConstraints gbc_textStatistics) {

		gbc_textStatistics.insets = new Insets(0, 0, 0, 5);
		gbc_textStatistics.gridx = 1;
		gbc_textStatistics.gridy = 4;
		gbc_textStatistics.weightx = 1.0;
		gbc_textStatistics.weighty = 1.0;
		gbc_textStatistics.fill = GridBagConstraints.BOTH;
		textStatistics.setEditable(false);

		JScrollPane scrollPane = new JScrollPane(textStatistics);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		if (this.seq.isProtein()) {
			textForStatisticsIfProtein(textStatistics);
		} else {
			textForStatisticsIfNucleotide(textStatistics);
		}

		textStatistics.setBackground(new Color(22, 28, 45)); // added
		textStatistics.setForeground(new Color(226, 232, 240)); // added
		textStatistics.setFont(new Font("Monospaced", Font.PLAIN, 12)); // added
		textStatistics.setBorder(new EmptyBorder(10, 10, 10, 10)); // added

		JScrollPane scrollPaneStats = new JScrollPane(textStatistics);
		scrollPaneStats.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); // added
		scrollPaneStats.getViewport().setBackground(new Color(22, 28, 45)); // added
		contentPane.add(scrollPaneStats, gbc_textStatistics);
	}

	/**
	 * Builds the right panel describing the recommended tool.
	 *
	 * @param textTools text area used to display the tool recommendation
	 * @param gbc_textTools layout constraints for the tool panel
	 */
	private void textForTools(JTextArea textTools, GridBagConstraints gbc_textTools) {

		JScrollPane scrollPaneTools = new JScrollPane(textTools);
		scrollPaneTools.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); // added
		scrollPaneTools.getViewport().setBackground(new Color(22, 28, 45)); // added
		scrollPaneTools.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPaneTools.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		textTools.setLineWrap(true);
		textTools.setWrapStyleWord(true);
		gbc_textTools.gridx = 2;
		gbc_textTools.gridy = 4;
		gbc_textTools.weightx = 1.0;
		gbc_textTools.weighty = 1.0;
		gbc_textTools.fill = GridBagConstraints.BOTH;

		if (this.seq.isProtein()) {
			textTools.append("The sequence contains amino acids. Use BLASTP to find"
					+ " which organism the sequence comes from.");
		} else {
			textTools.append("The sequence contains nucleotides. Use BLASTN to find"
					+ " which organism the sequence comes from.");
		}

		contentPane.add(scrollPaneTools, gbc_textTools);

		// button to refer to next tool
		JButton btnGoToTool = new JButton();
		scrollPaneTools.setColumnHeaderView(btnGoToTool);
		btnGoToTool.setBackground(new Color(0, 0, 128));
		btnGoToTool.setOpaque(false);
		btnGoToTool.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		btnGoToTool.setPreferredSize(new Dimension(250, 50));
		btnGoToTool.setHorizontalTextPosition(SwingConstants.CENTER);
		GridBagConstraints gbc_btnGoToTool = new GridBagConstraints();
		gbc_btnGoToTool.insets = new Insets(0, 0, 5, 0);
		btnTool(btnGoToTool, gbc_btnGoToTool);

	}

	/**
	 * Displays statistics for a protein sequence.
	 *
	 * @param textStatistics text area to populate
	 */
	private void textForStatisticsIfProtein(JTextArea textStatistics) {
		ProteinStatistics seqStat = new ProteinStatistics(this.seq);

		// protein weight
		double protWeight = ((ProteinStatistics) seqStat).proteinWeight();

		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Protein length; " + length + " amino acids\n\n");

		// sequence content counts
		textStatistics.append("Protein sequence contents;\n");
		printSeqContents(seqStat, textStatistics);
		
		// protein weight
		textStatistics.append("\n\nProtein weight; " + protWeight + " Da");
	}

	/**
	 * Displays statistics for a nucleotide sequence.
	 *
	 * @param textStatistics text area to populate
	 */
	private void textForStatisticsIfNucleotide(JTextArea textStatistics) {
		NucleotideStatistics seqStat = new NucleotideStatistics(this.seq);

		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Nucleotide sequence length; \n" + length + " nucleotides\n\n");

		// sequence content counts
		textStatistics.append("Nucleotide sequence contents;\n");
		printSeqContents(seqStat, textStatistics);

		// nucleotide GC%
		double gcContent = ((NucleotideStatistics) seqStat).gcContent();
		BigDecimal rounded = new BigDecimal(gcContent * 100).setScale(1, RoundingMode.HALF_UP);
		textStatistics.append("\n\nGC%; " + rounded);

		// codon frequency
		textStatistics.append("\n\nCodon frequency: \n");
		HashMap<String, Integer> codonFreq = ((NucleotideStatistics) seqStat).getCodonFrequency();
		int count = 1;
		for (String i : codonFreq.keySet()) {
			textStatistics.append(i + " : " + codonFreq.get(i));
			if (count % 3 == 0) {
				textStatistics.append("\n");
			} else {
				textStatistics.append("\t");
			}
			count++;
		}

	}

	/**
	 * Appends per-symbol sequence counts to the statistics text area.
	 *
	 * @param seqStat statistics object containing sequence counts
	 * @param textStatistics text area to populate
	 */
	private void printSeqContents(ProteinStatistics seqStat, JTextArea textStatistics) {
		HashMap<Character, Integer> moleculeDict = seqStat.seqContents();

		int count = 1;
		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();

			textStatistics.append(key + " : " + value.toString());
			if (count % 3 == 0) {
				textStatistics.append("\n");
			} else {
				textStatistics.append("\t");
			}
			count++;
		}

	}
	
	/**
	 * Appends per-symbol sequence counts to the statistics text area.
	 *
	 * @param seqStat statistics object containing sequence counts
	 * @param textStatistics text area to populate
	 */
	private void printSeqContents(NucleotideStatistics seqStat, JTextArea textStatistics) {
		HashMap<Character, Integer> moleculeDict = seqStat.seqContents();

		int count = 1;
		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();

			textStatistics.append(key + " : " + value.toString());
			if (count % 3 == 0) {
				textStatistics.append("\n");
			} else {
				textStatistics.append("\t");
			}
			count++;
		}

	}
}
