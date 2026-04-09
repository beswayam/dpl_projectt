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

import utilities.ProteinStatistics;
import utilities.Statistics;

import java.awt.GridBagLayout;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import javax.swing.JLabel;
import utilities.Statistics;
import java.awt.Rectangle;
import javax.swing.SwingConstants;


public class FileStatistics extends JFrame {

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
					FileStatistics frame = new FileStatistics(null);
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
	public FileStatistics(File inputFile) {	
		this.file = inputFile;
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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
		textOverviewInput.setEditable(false);
		textOverviewInput.setLineWrap(true);
		textOverviewInput.setWrapStyleWord(true);
		
		GridBagConstraints gbc_textOverviewInput = new GridBagConstraints();
		gbc_textOverviewInput.insets = new Insets(0, 0, 0, 5);
		gbc_textOverviewInput.gridx = 0;
		gbc_textOverviewInput.gridy = 1;
		gbc_textOverviewInput.weightx = 1.0;
		gbc_textOverviewInput.weighty = 1.0;
		gbc_textOverviewInput.fill = GridBagConstraints.BOTH;
		
		JScrollPane scrollPane = new JScrollPane(textOverviewInput);
		scrollPane.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		scrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);

		contentPane.add(scrollPane, gbc_textOverviewInput);
		
		try (BufferedReader br = new BufferedReader(new FileReader(this.file))) {
            String line;
            int count = 0;

            while ((line = br.readLine()) != null && count < 10) {
            	textOverviewInput.append(line + "\n");
                count++;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
		// file overview text area end
		
		// statistics text area start
		JTextArea textStatistics = new JTextArea();
		GridBagConstraints gbc_textStatistics = new GridBagConstraints();
		gbc_textStatistics.insets = new Insets(0, 0, 0, 5);
		gbc_textStatistics.gridx = 1;
		gbc_textStatistics.gridy = 1;
		gbc_textStatistics.weightx = 1.0;
		gbc_textStatistics.weighty = 1.0;
		gbc_textStatistics.fill = GridBagConstraints.BOTH;
		textStatistics.setEditable(false);
		
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
	
	private String FastaParser(File file) {
		try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            String line;
            String header = null;
            StringBuilder sequence = new StringBuilder();

            while ((line = br.readLine()) != null) {
                if (line.startsWith(">")) {
                    header = line.substring(1); // remove '>'
                } else {
                    sequence.append(line.trim());
                }
            }

    		return sequence.toString();

        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }	
	}
	
	private void textForStatistics(JTextArea textStatistics, GridBagConstraints gbc_textStatistics) {
		String rawSeq = FastaParser(this.file);
		ProteinStatistics protSeq = new ProteinStatistics(rawSeq);
		      
		// protein length
        int length = protSeq.seqLength();
		textStatistics.append("Length of the protein is " + length + " amino acids\n\n");
		
		// protein base counts
		textStatistics.append("Protein contents\n");
		HashMap<Character, Integer> moleculeDict = protSeq.SeqContents();

		for (Entry<Character, Integer> mol : moleculeDict.entrySet()) {
			Character key = mol.getKey();
			Integer value = mol.getValue();
			
			textStatistics.append(key + " : " + value.toString() + "\n");
		}
		
		// protein weight
		textStatistics.append("\nProtein weight: " + protSeq.ProteinWeight() + " Da");
		
		contentPane.add(new JScrollPane(textStatistics), gbc_textStatistics);		
	}
}
