package gui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JComboBox;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import java.awt.Color;
import java.awt.Font;

public class BlastOutGui extends JFrame {

	public BlastOutGui() {
		GridBagLayout gridBagLayout = new GridBagLayout();
		gridBagLayout.columnWidths = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.rowHeights = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
		gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		gridBagLayout.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
		getContentPane().setLayout(gridBagLayout);
//		getContentPane().setBackground(new Color(20, 20, 30));
		
				JLabel OutTitleLabel = new JLabel("BLAST Output Screen");
				OutTitleLabel.setFont(new Font("Monospaced", Font.BOLD, 16));
//				OutTitleLabel.setForeground(new Color(140, 180, 255));
				GridBagConstraints gbc_OutTitleLabel = new GridBagConstraints();
				gbc_OutTitleLabel.insets = new Insets(20, 10, 20, 5); // ── CHANGED: more top/bottom padding
				gbc_OutTitleLabel.gridx = 2;
				gbc_OutTitleLabel.gridy = 3;
				getContentPane().add(OutTitleLabel, gbc_OutTitleLabel);

		JButton OutHelpButton = new JButton("Help");
		OutHelpButton.setFont(new Font("Monospaced", Font.BOLD, 11));
//		OutHelpButton.setBackground(new Color(30, 32, 48));
//		OutHelpButton.setForeground(new Color(140, 180, 255));
		OutHelpButton.setFocusPainted(false);
		GridBagConstraints gbc_OutHelpButton = new GridBagConstraints();
		gbc_OutHelpButton.insets = new Insets(20, 0, 20, 10); // ── CHANGED
		gbc_OutHelpButton.gridx = 9;
		gbc_OutHelpButton.gridy = 3;
		getContentPane().add(OutHelpButton, gbc_OutHelpButton);

		JLabel BlastHitLabel = new JLabel("Select Blast Hit");
		BlastHitLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		BlastHitLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_BlastHitLabel = new GridBagConstraints();
		gbc_BlastHitLabel.insets = new Insets(0, 10, 15, 10); // ── CHANGED
		gbc_BlastHitLabel.gridx = 1;
		gbc_BlastHitLabel.gridy = 5;
		getContentPane().add(BlastHitLabel, gbc_BlastHitLabel);

		JComboBox comboBox = new JComboBox();
//		comboBox.setBackground(new Color(28, 33, 52));
//		comboBox.setForeground(new Color(210, 220, 245));
		comboBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
		GridBagConstraints gbc_comboBox = new GridBagConstraints();
		gbc_comboBox.insets = new Insets(0, 0, 15, 10); // ── CHANGED
		gbc_comboBox.fill = GridBagConstraints.HORIZONTAL;
		gbc_comboBox.gridx = 2;
		gbc_comboBox.gridy = 5;
		getContentPane().add(comboBox, gbc_comboBox);

		JButton HitLoadButton = new JButton("Load Hit");
		HitLoadButton.setFont(new Font("Monospaced", Font.BOLD, 11));
//		HitLoadButton.setBackground(new Color(30, 32, 48));
//		HitLoadButton.setForeground(new Color(60, 210, 140));
		HitLoadButton.setFocusPainted(false);
		HitLoadButton.setHorizontalAlignment(SwingConstants.LEFT);
		GridBagConstraints gbc_HitLoadButton = new GridBagConstraints();
		gbc_HitLoadButton.anchor = GridBagConstraints.WEST;
		gbc_HitLoadButton.insets = new Insets(0, 0, 15, 5); // ── CHANGED
		gbc_HitLoadButton.gridx = 3;
		gbc_HitLoadButton.gridy = 5;
		getContentPane().add(HitLoadButton, gbc_HitLoadButton);

		JLabel UniprotIDLabel = new JLabel("UniProt ID");
		UniprotIDLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		UniprotIDLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_UniprotIDLabel = new GridBagConstraints();
		gbc_UniprotIDLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_UniprotIDLabel.gridx = 1;
		gbc_UniprotIDLabel.gridy = 7;
		getContentPane().add(UniprotIDLabel, gbc_UniprotIDLabel);

		JLabel UniprotIDValueLabel = new JLabel("P40763");
		UniprotIDValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		UniprotIDValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_UniprotIDValueLabel = new GridBagConstraints();
		gbc_UniprotIDValueLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_UniprotIDValueLabel.gridx = 2;
		gbc_UniprotIDValueLabel.gridy = 7;
		getContentPane().add(UniprotIDValueLabel, gbc_UniprotIDValueLabel);

		JLabel QuerySeqLabel = new JLabel("Query Sequence");
		QuerySeqLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		QuerySeqLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_QuerySeqLabel = new GridBagConstraints();
		gbc_QuerySeqLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_QuerySeqLabel.gridx = 1;
		gbc_QuerySeqLabel.gridy = 9;
		getContentPane().add(QuerySeqLabel, gbc_QuerySeqLabel);

		JLabel QuerySeqValueLabel = new JLabel("MTRKLMLKRTAS");
		QuerySeqValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		QuerySeqValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_QuerySeqValueLabel = new GridBagConstraints();
		gbc_QuerySeqValueLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_QuerySeqValueLabel.gridx = 2;
		gbc_QuerySeqValueLabel.gridy = 9;
		getContentPane().add(QuerySeqValueLabel, gbc_QuerySeqValueLabel);

		JLabel QuerySeqAlignLenLabel = new JLabel("(1:382)");
		QuerySeqAlignLenLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
//		QuerySeqAlignLenLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_QuerySeqAlignLenLabel = new GridBagConstraints();
		gbc_QuerySeqAlignLenLabel.anchor = GridBagConstraints.WEST;
		gbc_QuerySeqAlignLenLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_QuerySeqAlignLenLabel.gridx = 3;
		gbc_QuerySeqAlignLenLabel.gridy = 9;
		getContentPane().add(QuerySeqAlignLenLabel, gbc_QuerySeqAlignLenLabel);

		JLabel MatchSeqLabel = new JLabel("Match Sequence");
		MatchSeqLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		MatchSeqLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_MatchSeqLabel = new GridBagConstraints();
		gbc_MatchSeqLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_MatchSeqLabel.gridx = 1;
		gbc_MatchSeqLabel.gridy = 11;
		getContentPane().add(MatchSeqLabel, gbc_MatchSeqLabel);

		JLabel MatchSeqValueLabel = new JLabel("MLKLMRTQLk");
		MatchSeqValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		MatchSeqValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_MatchSeqValueLabel = new GridBagConstraints();
		gbc_MatchSeqValueLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_MatchSeqValueLabel.gridx = 2;
		gbc_MatchSeqValueLabel.gridy = 11;
		getContentPane().add(MatchSeqValueLabel, gbc_MatchSeqValueLabel);

		JLabel MatchSeqAlignLenLabel = new JLabel("(20:420)");
		MatchSeqAlignLenLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
//		MatchSeqAlignLenLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_MatchSeqAlignLenLabel = new GridBagConstraints();
		gbc_MatchSeqAlignLenLabel.anchor = GridBagConstraints.BASELINE_LEADING;
		gbc_MatchSeqAlignLenLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_MatchSeqAlignLenLabel.gridx = 3;
		gbc_MatchSeqAlignLenLabel.gridy = 11;
		getContentPane().add(MatchSeqAlignLenLabel, gbc_MatchSeqAlignLenLabel);

		JLabel EvalueLabel = new JLabel("E-value");
		EvalueLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		EvalueLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_EvalueLabel = new GridBagConstraints();
		gbc_EvalueLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_EvalueLabel.gridx = 1;
		gbc_EvalueLabel.gridy = 13;
		getContentPane().add(EvalueLabel, gbc_EvalueLabel);

		JLabel EvalueAnnotLabel = new JLabel("1e-20");
		EvalueAnnotLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		EvalueAnnotLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_EvalueAnnotLabel = new GridBagConstraints();
		gbc_EvalueAnnotLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_EvalueAnnotLabel.gridx = 2;
		gbc_EvalueAnnotLabel.gridy = 13;
		getContentPane().add(EvalueAnnotLabel, gbc_EvalueAnnotLabel);

		JLabel BitScoreLabel = new JLabel("Bit Score");
		BitScoreLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		BitScoreLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_BitScoreLabel = new GridBagConstraints();
		gbc_BitScoreLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_BitScoreLabel.gridx = 1;
		gbc_BitScoreLabel.gridy = 15;
		getContentPane().add(BitScoreLabel, gbc_BitScoreLabel);

		JLabel BitScoreAnnotLabel = new JLabel("1683.72");
		BitScoreAnnotLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		BitScoreAnnotLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_BitScoreAnnotLabel = new GridBagConstraints();
		gbc_BitScoreAnnotLabel.insets = new Insets(0, 0, 12, 5);
		gbc_BitScoreAnnotLabel.gridx = 2;
		gbc_BitScoreAnnotLabel.gridy = 15;
		getContentPane().add(BitScoreAnnotLabel, gbc_BitScoreAnnotLabel);
		
		JButton VisualResultButton = new JButton("Visualise Results");
//		VisualResultButton.setBackground(new Color(30, 32, 48));
//		VisualResultButton.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_VisualResultButton = new GridBagConstraints();
		gbc_VisualResultButton.insets = new Insets(0, 0, 5, 5);
		gbc_VisualResultButton.gridx = 1; 
		gbc_VisualResultButton.gridy = 20; 
		getContentPane().add(VisualResultButton, gbc_VisualResultButton);
		
		JButton ExportButton = new JButton("Export Results");
//		ExportButton.setBackground(new Color(30, 32, 48));
//		ExportButton.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_ExportButton = new GridBagConstraints();
		gbc_ExportButton.insets = new Insets(0, 0, 5, 5);
		gbc_ExportButton.gridx = 2; // ── CHANGED
		gbc_ExportButton.gridy = 20; // ── CHANGED
		getContentPane().add(ExportButton, gbc_ExportButton);
		
		JButton RecommendationButton = new JButton("Future Recommendations");
//		RecommendationButton.setBackground(new Color(30, 32, 48));
//		RecommendationButton.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_RecommendationButton = new GridBagConstraints();
		gbc_RecommendationButton.insets = new Insets(0, 0, 5, 0);
		gbc_RecommendationButton.gridx = 3; // ── CHANGED
		gbc_RecommendationButton.gridy = 20; // ── CHANGED
		getContentPane().add(RecommendationButton, gbc_RecommendationButton);

		JLabel IdentityLabel = new JLabel("Identity");
		IdentityLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
//		IdentityLabel.setForeground(new Color(100, 115, 150));
		GridBagConstraints gbc_IdentityLabel = new GridBagConstraints();
		gbc_IdentityLabel.insets = new Insets(0, 10, 12, 5); // ── CHANGED
		gbc_IdentityLabel.gridx = 1;
		gbc_IdentityLabel.gridy = 18;
		getContentPane().add(IdentityLabel, gbc_IdentityLabel);

		JLabel IdentityValueLabel = new JLabel("99.37%");
		IdentityValueLabel.setFont(new Font("Monospaced", Font.BOLD, 12));
//		IdentityValueLabel.setForeground(new Color(60, 210, 140));
		GridBagConstraints gbc_IdentityValueLabel = new GridBagConstraints();
		gbc_IdentityValueLabel.insets = new Insets(0, 0, 12, 5); // ── CHANGED
		gbc_IdentityValueLabel.gridx = 2;
		gbc_IdentityValueLabel.gridy = 18;
		getContentPane().add(IdentityValueLabel, gbc_IdentityValueLabel);

		setSize(660, 480);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setVisible(true);
	}

	public static void main(String[] args) {
		new BlastOutGui();
	}
}