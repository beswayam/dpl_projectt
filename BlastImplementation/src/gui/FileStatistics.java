package gui;

import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.border.EmptyBorder;

import utilities.Statistics;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import utilities.Statistics;


public class FileStatistics extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FileStatistics frame = new FileStatistics();
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
	public FileStatistics() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 779, 506);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{0, 0, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 108, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 1.0, 1.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblStatistics = new JLabel("Statistics");
		GridBagConstraints gbc_lblStatistics = new GridBagConstraints();
		gbc_lblStatistics.insets = new Insets(0, 0, 5, 5);
		gbc_lblStatistics.gridx = 1;
		gbc_lblStatistics.gridy = 0;
		contentPane.add(lblStatistics, gbc_lblStatistics);
		
		JTextArea textStatistics = new JTextArea();
		GridBagConstraints gbc_textStatistics = new GridBagConstraints();
		gbc_textStatistics.insets = new Insets(0, 0, 5, 5);
		gbc_textStatistics.fill = GridBagConstraints.BOTH;
		gbc_textStatistics.gridx = 1;
		gbc_textStatistics.gridy = 1;
		textStatistics.setEditable(false);
		
		String sequence = "GCTAAAGCTGGTAAAAAACGCGCGCGCGG";
		
		Statistics stats = new Statistics(sequence);
		
			char a ='A';
	        char c = 'C';
	        char g = 'G';
	        char t = 'T';
	        
	        int length = stats.seqLength();
	        
	        int nucCountA = stats.countNuc(a, sequence);
	        int nucCountC = stats.countNuc(c, sequence);
	        int nucCountG = stats.countNuc(g, sequence);
	        int nucCountT = stats.countNuc(t, sequence);
			
			double gcContent = stats.GCContent();
			
			textStatistics.append("GC content of the sequence is " + Math.round(gcContent * 1000)*0.1 + "%\n\n");
			textStatistics.append("Length of the sequence is " + length + " nucleotides\n\n");
			textStatistics.append("Sequence contents\n");
			
			HashMap<Character, Integer> moleculedict = stats.SeqContents();
//			ArrayList<String> nucString = new ArrayList<> ();
//			 nucString.add
			for (Entry<Character, Integer> mol : moleculedict.entrySet()) {
				Character key = mol.getKey();
				Integer value = mol.getValue();
				
				textStatistics.append(key + " : " + value.toString() + "\n");
				
			}
			
			
			
					
	//		textStatistics.append("A " +  "C " + "G " + "T ");
//			textStatistics.append(nucCountA + "A " + nucCountC + "C " + nucCountG + "G " + nucCountT + "T ");
			
			
			contentPane.add(textStatistics, gbc_textStatistics);
		
		JTextArea textOverview = new JTextArea();
		GridBagConstraints gbc_textOverview = new GridBagConstraints();
		gbc_textOverview.insets = new Insets(0, 0, 0, 5);
		gbc_textOverview.fill = GridBagConstraints.BOTH;
		gbc_textOverview.gridx = 0;
		gbc_textOverview.gridy = 9;
		contentPane.add(textOverview, gbc_textOverview);
		
		JTextArea textTools = new JTextArea();
		GridBagConstraints gbc_textTools = new GridBagConstraints();
		gbc_textTools.fill = GridBagConstraints.BOTH;
		gbc_textTools.gridx = 2;
		gbc_textTools.gridy = 9;
		contentPane.add(textTools, gbc_textTools);

	}

}
