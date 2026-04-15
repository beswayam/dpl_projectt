package gui;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;
import interfaces.StatisticsInterface;
import utilities.NucleotideStatistics;
import utilities.ProteinStatistics;
import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.io.File;
import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.HashMap;
import java.util.Map.Entry;
import javax.swing.JLabel;
import java.awt.Rectangle;
import javax.swing.SwingConstants;
import utilities.Sequence;
import javax.swing.JSeparator;


public class StatisticsGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private File file;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					StatisticsGui frame = new StatisticsGui(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	

	/**
	 * Create the frame.
	 */
	public StatisticsGui(File inputFile) {	
		this.file = inputFile;
        setBounds(100, 100, 900, 580);
        setTitle("EzBLAST — File Statistics"); // ── CHANGED: window title

        contentPane = new JPanel();
        contentPane.setBackground(new Color(13, 17, 28)); // ── CHANGED: dark navy
        contentPane.setBorder(new EmptyBorder(25, 30, 25, 30)); // ── CHANGED: more padding
        setContentPane(contentPane);

        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths  = new int[]{300, 300, 300, 0};
        gbl_contentPane.rowHeights    = new int[]{30, 10, 10, 500, 0};
        gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights    = new double[]{0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);

        // ── ADDED: Page title at the top ─────────────────────────────────────
        JLabel lblPageTitle = new JLabel("File Statistics");
        lblPageTitle.setFont(new Font("Monospaced", Font.BOLD, 22)); // ── ADDED
        lblPageTitle.setForeground(new Color(56, 189, 248));          // ── ADDED: sky blue
        GridBagConstraints gbc_lblPageTitle = new GridBagConstraints();
        gbc_lblPageTitle.anchor    = GridBagConstraints.WEST;
        gbc_lblPageTitle.gridwidth = 3;
        gbc_lblPageTitle.insets    = new Insets(0, 0, 4, 0);
        gbc_lblPageTitle.gridx     = 0;
        gbc_lblPageTitle.gridy     = 0;
        contentPane.add(lblPageTitle, gbc_lblPageTitle);

        // ── ADDED: Separator below title ─────────────────────────────────────
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(30, 41, 59));
        GridBagConstraints gbc_sep = new GridBagConstraints();
        gbc_sep.fill      = GridBagConstraints.HORIZONTAL;
        gbc_sep.gridwidth = 3;
        gbc_sep.insets    = new Insets(0, 0, 12, 0);
        gbc_sep.gridx     = 0;
        gbc_sep.gridy     = 1;
        contentPane.add(sep, gbc_sep);

        // ── Column headers ────────────────────────────────────────────────────
        JLabel lblInputFile = new JLabel("Input File");
        lblInputFile.setFont(new Font("Monospaced", Font.BOLD, 13));  // ── CHANGED: font
        lblInputFile.setForeground(new Color(52, 211, 153));           // ── CHANGED: teal
        lblInputFile.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblInputFile = new GridBagConstraints();
        gbc_lblInputFile.insets = new Insets(0, 0, 8, 8);
        gbc_lblInputFile.gridx  = 0;
        gbc_lblInputFile.gridy  = 2;
        contentPane.add(lblInputFile, gbc_lblInputFile);

        JLabel lblStatistics = new JLabel("Statistics");
        lblStatistics.setFont(new Font("Monospaced", Font.BOLD, 13)); // ── CHANGED
        lblStatistics.setForeground(new Color(52, 211, 153));          // ── CHANGED: teal
        lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
        gbc_lblStatistics.insets = new Insets(0, 0, 8, 8);
        gbc_lblStatistics.gridx  = 1;
        gbc_lblStatistics.gridy  = 2;
        contentPane.add(lblStatistics, gbc_lblStatistics);

        JLabel lblToolSuggestions = new JLabel("Tool Suggestions");
        lblToolSuggestions.setFont(new Font("Monospaced", Font.BOLD, 13)); // ── CHANGED
        lblToolSuggestions.setForeground(new Color(52, 211, 153));          // ── CHANGED: teal
        lblToolSuggestions.setHorizontalAlignment(SwingConstants.CENTER);
        GridBagConstraints gbc_lblToolSuggestions = new GridBagConstraints();
        gbc_lblToolSuggestions.fill   = GridBagConstraints.BOTH;
        gbc_lblToolSuggestions.insets = new Insets(0, 0, 8, 0);
        gbc_lblToolSuggestions.gridx  = 2;
        gbc_lblToolSuggestions.gridy  = 2;
        contentPane.add(lblToolSuggestions, gbc_lblToolSuggestions);
		// statistics header end
		
		// file overview text area start
		JTextArea textOverviewInput = new JTextArea();
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
		textTools.setBackground(new Color(22, 28, 45));    //added
		textTools.setForeground(new Color(226, 232, 240)); //added
		textTools.setFont(new Font("Monospaced", Font.PLAIN, 12)); //added
		textTools.setBorder(new EmptyBorder(10, 10, 10, 10)); //added
		GridBagConstraints gbc_textTools = new GridBagConstraints();
		gbc_textTools.insets = new Insets(0, 0, 0, 0);
		gbc_textTools.gridx = 2;
		gbc_textTools.gridy = 3; // ── CHANGED: was 1, now matches header row 2 + content row 3
		gbc_textTools.weightx = 1.0;
		gbc_textTools.weighty = 1.0;
		gbc_textTools.fill = GridBagConstraints.BOTH;
		JScrollPane scrollPaneTools = new JScrollPane(textTools);
		scrollPaneTools.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); //added
		scrollPaneTools.getViewport().setBackground(new Color(22, 28, 45)); //added
		contentPane.add(scrollPaneTools, gbc_textTools); // ── CHANGED: was new JScrollPane(textTools)
		// tools text area end
	
	}
	
	private void textForStatistics(JTextArea textStatistics, GridBagConstraints gbc_textStatistics) {
		Sequence unknownSeq = new Sequence(this.file);
				
		gbc_textStatistics.insets = new Insets(0, 0, 0, 5);
		gbc_textStatistics.gridx = 1;
		gbc_textStatistics.gridy = 3;
		gbc_textStatistics.weightx = 1.0;
		gbc_textStatistics.weighty = 1.0;
		gbc_textStatistics.fill = GridBagConstraints.BOTH;
		textStatistics.setEditable(false);
		
		if (unknownSeq.isProtein()) {
			textForStatisticsIfProtein(unknownSeq, textStatistics);
		} else {
			textForStatisticsIfNucleotide(unknownSeq, textStatistics);
		}
				
		textStatistics.setBackground(new Color(22, 28, 45));    //added
		textStatistics.setForeground(new Color(226, 232, 240)); //added
		textStatistics.setFont(new Font("Monospaced", Font.PLAIN, 12)); //added
		textStatistics.setBorder(new EmptyBorder(10, 10, 10, 10)); //added

		JScrollPane scrollPaneStats = new JScrollPane(textStatistics);
		scrollPaneStats.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); //added
		scrollPaneStats.getViewport().setBackground(new Color(22, 28, 45)); //added
		contentPane.add(scrollPaneStats, gbc_textStatistics); // ── CHANGED: was new JScrollPane(textStatistics)
//		contentPane.add(new JScrollPane(textStatistics), gbc_textStatistics);		
	}
	
	private void textForOverviewInput(JTextArea textOverviewInput, GridBagConstraints gbc_textOverviewInput) {
		Sequence protSeq = new Sequence(this.file);
		
		textOverviewInput.setEditable(false);
		textOverviewInput.setLineWrap(true);
		textOverviewInput.setWrapStyleWord(true);
		textOverviewInput.setBackground(new Color(22, 28, 45));    //added
		textOverviewInput.setForeground(new Color(52, 211, 153));  //added
		textOverviewInput.setFont(new Font("Monospaced", Font.PLAIN, 12)); //added
		textOverviewInput.setBorder(new EmptyBorder(10, 10, 10, 10)); //added
		gbc_textOverviewInput.insets = new Insets(0, 0, 0, 5);
		gbc_textOverviewInput.gridx = 0;
		gbc_textOverviewInput.gridy = 3;
		gbc_textOverviewInput.weightx = 1.0;
		gbc_textOverviewInput.weighty = 1.0;
		gbc_textOverviewInput.fill = GridBagConstraints.BOTH;
		
		JScrollPane scrollPane = new JScrollPane(textOverviewInput);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		scrollPane.setBorder(new javax.swing.border.LineBorder(new Color(30, 41, 59), 1)); //added
		scrollPane.getViewport().setBackground(new Color(22, 28, 45)); //added

		textOverviewInput.append(protSeq.getSequence());
		
		
		contentPane.add(scrollPane, gbc_textOverviewInput);		
	}
	
	private void textForStatisticsIfProtein(Sequence unknownSeq, JTextArea textStatistics) {
		StatisticsInterface seqStat = new ProteinStatistics(unknownSeq);
		
		// protein weight
		double protWeight = ((ProteinStatistics) seqStat).proteinWeight();
		
		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Protein length; " + length + " amino acids\n\n");
		
		// sequence content counts
		HashMap<Character, Integer> moleculeDict = seqStat.seqContents();
		textStatistics.append("Protein contents;\n");
		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();
			
			textStatistics.append(key + " : " + value.toString() + "\n");
		}
		
		// weight if protein, if else nucleotide GC%
		textStatistics.append("\nProtein weight; " + protWeight + " Da");
	}
	
	private void textForStatisticsIfNucleotide(Sequence unknownSeq, JTextArea textStatistics) {
		StatisticsInterface seqStat = new NucleotideStatistics(unknownSeq);
				
		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Nucleotide sequence length; \n" + length + " amino acids\n\n");
		
		// sequence content counts
		HashMap<Character, Integer> moleculeDict = seqStat.seqContents();
		textStatistics.append("Nucleotide sequence contents;\n");
		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();
			
			textStatistics.append(key + " : " + value.toString() + "\n");
		}
		
		
		// nucleotide GC%
		double gcContent = ((NucleotideStatistics) seqStat).GCContent();
		BigDecimal rounded = new BigDecimal(gcContent * 100).setScale(1, RoundingMode.HALF_UP);
		textStatistics.append("\nGC%; " + rounded);
		
		
	}
	
}
