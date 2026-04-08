package gui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;

import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.IntStream;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;

import org.apache.commons.io.FileUtils;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class BlastOutputGui extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlastOutputGui(File file,String header) {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
//		getContentPane().setBackground(new Color(20, 20, 30));
		
				JLabel OutTitleLabel = new JLabel("BLAST Output Screen");
				OutTitleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
//				OutTitleLabel.setForeground(new Color(140, 180, 255));
				GridBagConstraints gbc_OutTitleLabel = new GridBagConstraints();
				gbc_OutTitleLabel.insets = new Insets(5, 10, 5, 5); // ── CHANGED: more top/bottom padding
				gbc_OutTitleLabel.gridx = 2;
				gbc_OutTitleLabel.gridy = 3;
				getContentPane().add(OutTitleLabel, gbc_OutTitleLabel);

		JButton OutHelpButton = new JButton("Help");
		OutHelpButton.setFont(new Font("Monospaced", Font.BOLD, 11));
//		OutHelpButton.setBackground(new Color(30, 32, 48));
//		OutHelpButton.setForeground(new Color(140, 180, 255));
		OutHelpButton.setFocusPainted(false);
		OutHelpButton.addActionListener(e -> {
		    JFrame helpFrame = new JFrame("BLASTP Output Help");
		    helpFrame.setSize(600, 600);

		    JTextArea textArea = new JTextArea();
		    textArea.setText(
		    		"BLASTP OUTPUT INTERPRETATION\n\n" +
		    		    
		    		 "HOW TO USE\n\n" +
		    		 "  1. Select a BLAST hit number from the dropdown.\n" +
		    		 "  2. The fields below will update automatically.\n\n" +

		    		 "E-VALUE\n\n" +
		    		 "  The probability that a match occurred by chance.\n\n" +
		    		 "  Low  (e.g. 1e-20)  →  Strong match, likely real.\n" +
		    		 "  High (e.g. 1.0+)   →  Weak match, likely noise.\n\n" +

		    		 "BIT SCORE\n\n" +
		    		 "  A normalised score for the quality of alignment.\n\n" +
		    		 "  High (>200)  →  Very strong alignment.\n" +
		    		 "  Low  (<50)   →  Weak alignment.\n\n" +
		    		 "  Note: comparable across different databases.\n\n" +

		    		 "IDENTITY\n\n" +
		    		 "  Percentage of identical residues in the alignment.\n\n" +
		    		 "  90–100%   →  Very high conservation.\n" +
		    		 "  20–25%    →  Twilight Zone — hard to tell if\n" +
		    		 "               related or similar by chance.\n" +
		    		 "  <20%      →  Likely distant or no relationship.\n\n" 

		    );
		    textArea.setEditable(false);
		    textArea.setLineWrap(true);
		    textArea.setWrapStyleWord(true);

		    JScrollPane scrollPane = new JScrollPane(textArea);
		    helpFrame.getContentPane().add(scrollPane);

		    helpFrame.setLocationRelativeTo(null);
		    helpFrame.setVisible(true);
		});
		GridBagConstraints gbc_OutHelpButton = new GridBagConstraints();
		gbc_OutHelpButton.insets = new Insets(5, 0, 5, 10); 
		gbc_OutHelpButton.gridx = 9;
		gbc_OutHelpButton.gridy = 3;
		getContentPane().add(OutHelpButton, gbc_OutHelpButton);

		JLabel BlastHitLabel = new JLabel("Select Blast Hit");
		BlastHitLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		BlastHitLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_BlastHitLabel = new GridBagConstraints();
		gbc_BlastHitLabel.insets = new Insets(0, 10, 12, 10);
		gbc_BlastHitLabel.gridx = 1;
		gbc_BlastHitLabel.gridy = 5;
		getContentPane().add(BlastHitLabel, gbc_BlastHitLabel);


		JLabel UniprotIDLabel = new JLabel("UniProt ID");
		UniprotIDLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		UniprotIDLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_UniprotIDLabel = new GridBagConstraints();
		gbc_UniprotIDLabel.insets = new Insets(0, 10, 12, 10); 
		gbc_UniprotIDLabel.gridx = 1;
		gbc_UniprotIDLabel.gridy = 7;
		getContentPane().add(UniprotIDLabel, gbc_UniprotIDLabel);

		JLabel UniprotIDValueLabel = new JLabel("-");
		UniprotIDValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		UniprotIDValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_UniprotIDValueLabel = new GridBagConstraints();
		gbc_UniprotIDValueLabel.anchor = GridBagConstraints.WEST;
		gbc_UniprotIDValueLabel.insets = new Insets(0, 0, 12, 5);
		gbc_UniprotIDValueLabel.gridx = 2;
		gbc_UniprotIDValueLabel.gridy = 7;
		getContentPane().add(UniprotIDValueLabel, gbc_UniprotIDValueLabel);

		JLabel ProteinDescLabel = new JLabel("Description");
		ProteinDescLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
		GridBagConstraints gbc_ProteinDescLabel = new GridBagConstraints();
		gbc_ProteinDescLabel.insets = new Insets(0, 10, 12, 5);
		gbc_ProteinDescLabel.gridx = 1;
		gbc_ProteinDescLabel.gridy = 8;
		getContentPane().add(ProteinDescLabel, gbc_ProteinDescLabel);

		JLabel ProteinDescValueLabel = new JLabel("-");
		ProteinDescValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
		GridBagConstraints gbc_ProteinDescValueLabel = new GridBagConstraints();
		gbc_ProteinDescValueLabel.anchor = GridBagConstraints.WEST;
		gbc_ProteinDescValueLabel.insets = new Insets(0, 0, 12, 5);
		gbc_ProteinDescValueLabel.gridx = 2;
		gbc_ProteinDescValueLabel.gridy = 8;
		gbc_ProteinDescValueLabel.gridwidth = 2;
		getContentPane().add(ProteinDescValueLabel, gbc_ProteinDescValueLabel);
		
		JLabel QuerySeqLabel = new JLabel("<html>Query Sequence<br><br>Match Sequence</html>");
		QuerySeqLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		QuerySeqLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_QuerySeqLabel = new GridBagConstraints();
		gbc_QuerySeqLabel.insets = new Insets(0, 10, 12, 5); 
		gbc_QuerySeqLabel.gridx = 1;
		gbc_QuerySeqLabel.gridy = 11;
		getContentPane().add(QuerySeqLabel, gbc_QuerySeqLabel);

		JLabel SeqValueLabel = new JLabel("-");
		SeqValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		QuerySeqValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_QuerySeqValueLabel = new GridBagConstraints();
		gbc_QuerySeqValueLabel.anchor = GridBagConstraints.WEST;
		gbc_QuerySeqValueLabel.insets = new Insets(0, 0, 12, 5); 
		gbc_QuerySeqValueLabel.gridx = 2;
		gbc_QuerySeqValueLabel.gridy = 11;
		getContentPane().add(SeqValueLabel, gbc_QuerySeqValueLabel);

		JLabel SeqAlignLenLabel = new JLabel("<html>(-:-)<br><br>(-:-)</html>");
		SeqAlignLenLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
//		SeqAlignLenLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_SeqAlignLenLabel = new GridBagConstraints();
		gbc_SeqAlignLenLabel.anchor = GridBagConstraints.LINE_END;
		gbc_SeqAlignLenLabel.insets = new Insets(0, 0, 12, 5);
		gbc_SeqAlignLenLabel.gridx = 3;
		gbc_SeqAlignLenLabel.gridy = 11;
		getContentPane().add(SeqAlignLenLabel, gbc_SeqAlignLenLabel);


		
		JLabel EvalueLabel = new JLabel("E-value");
		EvalueLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		EvalueLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_EvalueLabel = new GridBagConstraints();
		gbc_EvalueLabel.insets = new Insets(0, 10, 12, 5); 
		gbc_EvalueLabel.gridx = 1;
		gbc_EvalueLabel.gridy = 14;
		getContentPane().add(EvalueLabel, gbc_EvalueLabel);

		JLabel EvalueAnnotLabel = new JLabel("-");
		EvalueAnnotLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		EvalueAnnotLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_EvalueAnnotLabel = new GridBagConstraints();
		gbc_EvalueAnnotLabel.anchor = GridBagConstraints.WEST;
		gbc_EvalueAnnotLabel.insets = new Insets(0, 0, 12, 5);
		gbc_EvalueAnnotLabel.gridx = 2;
		gbc_EvalueAnnotLabel.gridy = 14;
		getContentPane().add(EvalueAnnotLabel, gbc_EvalueAnnotLabel);

		JLabel BitScoreLabel = new JLabel("Bit Score");
		BitScoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		BitScoreLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_BitScoreLabel = new GridBagConstraints();
		gbc_BitScoreLabel.insets = new Insets(0, 10, 12, 5); 
		gbc_BitScoreLabel.gridx = 1;
		gbc_BitScoreLabel.gridy = 16;
		getContentPane().add(BitScoreLabel, gbc_BitScoreLabel);

		JLabel BitScoreAnnotLabel = new JLabel("-");
		BitScoreAnnotLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		BitScoreAnnotLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_BitScoreAnnotLabel = new GridBagConstraints();
		gbc_BitScoreAnnotLabel.anchor = GridBagConstraints.WEST;
		gbc_BitScoreAnnotLabel.insets = new Insets(0, 0, 12, 5);
		gbc_BitScoreAnnotLabel.gridx = 2;
		gbc_BitScoreAnnotLabel.gridy = 16;
		getContentPane().add(BitScoreAnnotLabel, gbc_BitScoreAnnotLabel);
		
		JLabel IdentityLabel = new JLabel("Identity");
		IdentityLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		IdentityLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_IdentityLabel = new GridBagConstraints();
		gbc_IdentityLabel.insets = new Insets(0, 10, 12, 5); 
		gbc_IdentityLabel.gridx = 1;
		gbc_IdentityLabel.gridy = 18;
		getContentPane().add(IdentityLabel, gbc_IdentityLabel);

		JLabel IdentityValueLabel = new JLabel("-");
		IdentityValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		IdentityValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_IdentityValueLabel = new GridBagConstraints();
		gbc_IdentityValueLabel.anchor = GridBagConstraints.WEST;
		gbc_IdentityValueLabel.insets = new Insets(0, 0, 12, 5); 
		gbc_IdentityValueLabel.gridx = 2;
		gbc_IdentityValueLabel.gridy = 18;
		getContentPane().add(IdentityValueLabel, gbc_IdentityValueLabel);
		
		JButton ExportButton = new JButton("Export Results");
		ExportButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				exportResults(file,header);
			}
		});
//		ExportButton.setBackground(new Color(30, 32, 48));
//		ExportButton.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_ExportButton = new GridBagConstraints();
		gbc_ExportButton.fill = GridBagConstraints.HORIZONTAL;
		gbc_ExportButton.insets = new Insets(5, 10, 5, 5);
		gbc_ExportButton.gridx = 1;
		gbc_ExportButton.gridy = 21; 
		getContentPane().add(ExportButton, gbc_ExportButton);
		
//		JButton RecommendationButton = new JButton("Future Recommendations");
//		RecommendationButton.setBackground(new Color(30, 32, 48));
//		RecommendationButton.setForeground(new Color(60, 210, 140));
//		GridBagConstraints gbc_RecommendationButton = new GridBagConstraints();
//		gbc_RecommendationButton.fill = GridBagConstraints.HORIZONTAL;
//		gbc_RecommendationButton.insets = new Insets(5, 5, 5, 5);
//		gbc_RecommendationButton.gridx = 2; 
//		gbc_RecommendationButton.gridy = 21; 
//		getContentPane().add(RecommendationButton, gbc_RecommendationButton);

		JComboBox<String> comboBox = new JComboBox<>();
		comboBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ArrayList<String[]> hits = readBlastTsv(file);
				ArrayList<JLabel> labelList = new ArrayList<JLabel>();
				labelList.add(UniprotIDValueLabel);
				labelList.add(ProteinDescValueLabel);
				labelList.add(SeqValueLabel);
				labelList.add(SeqAlignLenLabel);
				labelList.add(EvalueAnnotLabel);
				labelList.add(BitScoreAnnotLabel);
				labelList.add(IdentityValueLabel);
				parseHit(hits.get(comboBox.getSelectedIndex()),labelList);
			}
		});
//		comboBox.setBackground(new Color(28, 33, 52));
//		comboBox.setForeground(new Color(210, 220, 245));
		comboBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 15, 10); 
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		getContentPane().add(comboBox, gbc_comboBox);
		JScrollPane mainScroll = new JScrollPane(getContentPane());
		mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
		setContentPane(mainScroll);
		
		setSize(660, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
		
		//set up windowlistener
		WindowListener taskStarterWindowListener = new WindowListener() {
	        @Override
	        public void windowOpened(WindowEvent e) {
	        	ArrayList<String[]> hits=readBlastTsv(file);
	        	int hitnum = hits.size();
	        	int[] hitnumrange = IntStream.range(1,hitnum+1).toArray();
	        	String[] hitrange=Arrays.toString(hitnumrange).split("[\\[\\]]")[1].split(", ");
	        	DefaultComboBoxModel<String> hitsModel = new DefaultComboBoxModel<String>(hitrange) ;
	        	comboBox.setModel(hitsModel);
	        	comboBox.setSelectedIndex(0);
	        }

			@Override
			public void windowClosing(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowClosed(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowIconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeiconified(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowActivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void windowDeactivated(WindowEvent e) {
				// TODO Auto-generated method stub
				
			}
		};
	
		//set window to not close other windows
	this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);	
	this.addWindowListener(taskStarterWindowListener);
	
	}

	public static void main(String[] args) {
		new BlastOutputGui(new File("temp_output.tsv"),"testheader");
	}
	
	private void parseHit(String[] hitdata,ArrayList<JLabel> labelList) {
	String hitnum = hitdata[0];
	String id = hitdata[1];
	String description = hitdata[2];
	String match_seq = hitdata[3];
	String eval= hitdata[4];
	String bitscore = hitdata[5];
	String identity = hitdata[6];
	String query_seq = hitdata[7];
	String query_start = hitdata[8];
	String query_end = hitdata[9];
	String match_start = hitdata[10];
	String match_end = hitdata[11];
	String matches = findMatches(query_seq,match_seq);
	labelList.get(0).setText(id);
	labelList.get(1).setText(description);
	labelList.get(2).setText("<html>"+query_seq+"<br>"+matches+"<br>"+match_seq+"</html>");
	labelList.get(3).setText("<html>("+query_start+ ":"+ query_end+ ")<br><br>("+match_start+ ":"+ match_end+ ")</html>");
	labelList.get(4).setText(eval);
	labelList.get(5).setText(bitscore);
	labelList.get(6).setText(identity + "%");
	}
	
	private String findMatches(String seq1, String seq2) {
	int seqlen = seq1.length();
	String matches = "";
	for(int i =0;i < seqlen; i++){
	if(seq1.charAt(i)==seq2.charAt(i)) {
	matches+="|";
	}
	else {
	matches+="&nbsp";
	}
	}
	return matches;
	}
	
	private void exportResults(File infile,String header) {
		String outfilename = System.getProperty("user.home")+ File.separator + "downloads"+ File.separator + header+"_blastoutput.tsv";
		File outfile = new File(outfilename);
		int fileid=2;
		while(outfile.isFile()) {
			outfilename = System.getProperty("user.home")+ File.separator + "downloads"+ File.separator + header+"_blastoutput_"+fileid+"_.tsv";
			outfile = new File(outfilename);
			fileid++;
		}
		
		try {
			FileUtils.copyFile(infile, outfile);
			JFrame exportFrame = new JFrame("Export successful");
			exportFrame.setSize(500, 100);
			exportFrame.setLocationRelativeTo(null);
			JTextArea textArea = new JTextArea();
		    textArea.setText("Result saved to " + outfilename);
		    textArea.setEditable(false);
			textArea.setLineWrap(true);
		    textArea.setWrapStyleWord(true);
			exportFrame.setVisible(true);	
		    JScrollPane scrollPane = new JScrollPane(textArea);
		    exportFrame.getContentPane().add(scrollPane);

		} catch (IOException e) {
			JFrame exportFrame = new JFrame("Error");
			exportFrame.setSize(500, 100);
			exportFrame.setLocationRelativeTo(null);
			JTextArea textArea = new JTextArea();
		    textArea.setText("Failed to save results");
		    textArea.setEditable(false);
			textArea.setLineWrap(true);
		    textArea.setWrapStyleWord(true);
			exportFrame.setVisible(true);	
		    JScrollPane scrollPane = new JScrollPane(textArea);
		    exportFrame.getContentPane().add(scrollPane);
			e.printStackTrace();
		}
	}
	
	private ArrayList<String[]> readBlastTsv(File file) {
	    ArrayList<String[]> hits = new ArrayList<String[]>();
		try (Scanner myReader = new Scanner(file)) {
			String fileheader = myReader.nextLine();
	        while (myReader.hasNextLine()) {
	        String data = myReader.nextLine();
	        String[] dataArray = data.split("\t");
	        hits.add(dataArray);
	        }
	      } catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	   
		return hits;
	}
}

