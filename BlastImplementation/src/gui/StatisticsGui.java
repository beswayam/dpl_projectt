package gui;

import java.awt.EventQueue;
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
		//setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{300, 300, 300, 0};
		gbl_contentPane.rowHeights = new int[]{100, 500, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		// input file header start
		JLabel lblInputFile = new JLabel("Input file");
		lblInputFile.setHorizontalAlignment(SwingConstants.CENTER);
		lblInputFile.setHorizontalTextPosition(SwingConstants.CENTER);
		lblInputFile.setBounds(new Rectangle(0, 0, 300, 100));
		GridBagConstraints gbc_lblInputFile = new GridBagConstraints();
		gbc_lblInputFile.insets = new Insets(0, 0, 5, 5);
		gbc_lblInputFile.gridx = 0;
		gbc_lblInputFile.gridy = 0;
		gbc_lblInputFile.weightx = 1.0;
		contentPane.add(lblInputFile, gbc_lblInputFile);
		// input file header end
		
		
		// statistics header start
		JLabel lblStatistics = new JLabel("Statistics");
		lblStatistics.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatistics.setHorizontalTextPosition(SwingConstants.CENTER);
		lblStatistics.setBounds(new Rectangle(0, 0, 300, 100));
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatistics.gridx = 1;
		contentPane.add(lblStatistics, gbc_lblStatistics);
		
		JLabel lblToolSuggestions = new JLabel("Tool suggestions");
		lblToolSuggestions.setHorizontalAlignment(SwingConstants.CENTER);
		GridBagConstraints gbc_lblToolSuggestions = new GridBagConstraints();
		gbc_lblToolSuggestions.fill = GridBagConstraints.BOTH;
		gbc_lblToolSuggestions.insets = new Insets(0, 0, 5, 0);
		gbc_lblToolSuggestions.gridx = 2;
		gbc_lblToolSuggestions.gridy = 0;
		gbc_lblToolSuggestions.weightx = 1.0;
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
		GridBagConstraints gbc_textTools = new GridBagConstraints();
		gbc_textTools.insets = new Insets(0, 0, 0, 0);
		gbc_textTools.gridx = 2;
		gbc_textTools.gridy = 1;
		gbc_textTools.weightx = 1.0;
		gbc_textTools.weighty = 1.0;
		gbc_textTools.fill = GridBagConstraints.BOTH;
		contentPane.add(new JScrollPane(textTools), gbc_textTools);
		// tools text area end
	
	}
	
	private void textForOverviewInput(JTextArea textOverviewInput, GridBagConstraints gbc_textOverviewInput) {
		Sequence protSeq = new Sequence(this.file);
		
		textOverviewInput.setEditable(false);
		textOverviewInput.setLineWrap(true);
		textOverviewInput.setWrapStyleWord(true);
		gbc_textOverviewInput.insets = new Insets(0, 0, 0, 5);
		gbc_textOverviewInput.gridx = 0;
		gbc_textOverviewInput.gridy = 1;
		gbc_textOverviewInput.weightx = 1.0;
		gbc_textOverviewInput.weighty = 1.0;
		gbc_textOverviewInput.fill = GridBagConstraints.BOTH;
		
		JScrollPane scrollPane = new JScrollPane(textOverviewInput);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		textOverviewInput.append(protSeq.getSequence());
		
		contentPane.add(scrollPane, gbc_textOverviewInput);		
	}
	
	private void textForStatistics(JTextArea textStatistics, GridBagConstraints gbc_textStatistics) {
		Sequence unknownSeq = new Sequence(this.file);
				
		gbc_textStatistics.insets = new Insets(0, 0, 0, 5);
		gbc_textStatistics.gridx = 1;
		gbc_textStatistics.gridy = 1;
		gbc_textStatistics.weightx = 1.0;
		gbc_textStatistics.weighty = 1.0;
		gbc_textStatistics.fill = GridBagConstraints.BOTH;
		textStatistics.setEditable(false);
		
		JScrollPane scrollPane = new JScrollPane(textStatistics);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		
		if (unknownSeq.isProtein()) {
			textForStatisticsIfProtein(unknownSeq, textStatistics);
		} else {
			textForStatisticsIfNucleotide(unknownSeq, textStatistics);
		}
				
		contentPane.add(new JScrollPane(textStatistics), gbc_textStatistics);		
	}
	
	private void textForStatisticsIfProtein(Sequence unknownSeq, JTextArea textStatistics) {
		StatisticsInterface seqStat = new ProteinStatistics(unknownSeq);
		
		// protein weight
		double protWeight = ((ProteinStatistics) seqStat).proteinWeight();
		
		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Protein length; " + length + " amino acids\n\n");
		
		// sequence content counts
		seqContents(seqStat, textStatistics);
		
		// weight if protein, if else nucleotide GC%
		textStatistics.append("\nProtein weight; " + protWeight + " Da");
	}
	
	private void textForStatisticsIfNucleotide(Sequence unknownSeq, JTextArea textStatistics) {
		StatisticsInterface seqStat = new NucleotideStatistics(unknownSeq);
				
		// sequence length
		int length = seqStat.seqLength();
		textStatistics.append("Nucleotide sequence length; \n" + length + " nucleotides\n\n");		
		
		// sequence content counts
		textStatistics.append("Nucleotide sequence contents;\n");
		seqContents(seqStat, textStatistics);
		
		// nucleotide GC%
		double gcContent = ((NucleotideStatistics) seqStat).GCContent();
		BigDecimal rounded = new BigDecimal(gcContent * 100).setScale(1, RoundingMode.HALF_UP);
		textStatistics.append("\nGC%; " + rounded);
		
		// codon frequency
		textStatistics.append("\n\nCodon frequency: \n");
		HashMap<String, Integer> codonFreq = ((NucleotideStatistics) seqStat).getCodonFrequency();
		int count = 1;
		for (String i : codonFreq.keySet()) {
			textStatistics.append(i + " : " + codonFreq.get(i));
			if (count % 3 == 0){
				textStatistics.append("\n");
			} else {
				textStatistics.append("\t");
			}
			count++;
		}
		
	}
	
	private void seqContents(StatisticsInterface seqStat, JTextArea textStatistics) {
		HashMap<Character, Integer> moleculeDict = seqStat.seqContents();
		
		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();
			
			textStatistics.append(key + " : " + value.toString() + "\n");
			
		}
	}
	
}
