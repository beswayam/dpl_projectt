package gui;

import javax.swing.JFrame;
import java.awt.GridBagLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.stream.IntStream;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.ArrayUtils;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Cursor;        //added
import java.awt.Graphics;      //added
import java.awt.Graphics2D;    //added
import java.awt.RenderingHints;//added
import javax.swing.JSeparator; //added
import javax.swing.border.EmptyBorder; //added

public class BlastViewGui extends BlastOutputGuiFunctions {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public BlastViewGui(File file) {
		super();
		GridBagLayout gridBagLayout = new GridBagLayout();
        gridBagLayout.columnWidths  = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.rowHeights    = new int[]{0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0};
        gridBagLayout.columnWeights = new double[]{0.0, 0.0, 1.0, 1.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
        gridBagLayout.rowWeights    = new double[]{0.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 1.0, 0.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 1.0, 0.0, 0.0, Double.MIN_VALUE};
        getContentPane().setLayout(gridBagLayout);
        getContentPane().setBackground(new Color(13, 17, 28)); // ── CHANGED: dark navy

        // ── Title ────────────────────────────────────────────────────────────
        JLabel OutTitleLabel = new JLabel("BLAST Output");
        OutTitleLabel.setFont(new Font("Monospaced", Font.BOLD, 20)); // ── CHANGED
        OutTitleLabel.setForeground(new Color(56, 189, 248));          // ── CHANGED: sky blue
        GridBagConstraints gbc_OutTitleLabel = new GridBagConstraints();
        gbc_OutTitleLabel.anchor = GridBagConstraints.WEST;
        gbc_OutTitleLabel.insets = new Insets(15, 15, 5, 5);
        gbc_OutTitleLabel.gridx  = 2;
        gbc_OutTitleLabel.gridy  = 3;
        getContentPane().add(OutTitleLabel, gbc_OutTitleLabel);

        // ── Help button ───────────────────────────────────────────────────────
        JButton OutHelpButton = new JButton("Help");
        OutHelpButton.setFont(new Font("Monospaced", Font.BOLD, 11));
        OutHelpButton.setBackground(new Color(22, 28, 45));     // ── CHANGED
        OutHelpButton.setForeground(new Color(100, 116, 139));  // ── CHANGED
        OutHelpButton.setFocusPainted(false);
        OutHelpButton.setBorder(new javax.swing.border.CompoundBorder(
            new javax.swing.border.LineBorder(new Color(30, 41, 59), 1),
            new EmptyBorder(6, 14, 6, 14)
        ));
        OutHelpButton.setCursor(new Cursor(Cursor.HAND_CURSOR)); // ── ADDED
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
                "IDENTITY\n\n" +
                "  Percentage of identical residues in the alignment.\n\n" +
                "  90–100%  →  Very high conservation.\n" +
                "  20–25%   →  Twilight Zone.\n" +
                "  <20%     →  Likely distant relationship.\n"
            );
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            textArea.setBackground(new Color(22, 28, 45));      // ── CHANGED
            textArea.setForeground(new Color(226, 232, 240));   // ── CHANGED
            textArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
            JScrollPane sp = new JScrollPane(textArea);
            helpFrame.getContentPane().add(sp);
            helpFrame.setLocationRelativeTo(null);
            helpFrame.setVisible(true);
        });
        GridBagConstraints gbc_OutHelpButton = new GridBagConstraints();
        gbc_OutHelpButton.insets = new Insets(15, 0, 5, 15);
        gbc_OutHelpButton.gridx  = 9;
        gbc_OutHelpButton.gridy  = 3;
        getContentPane().add(OutHelpButton, gbc_OutHelpButton);

        // ── ADDED: separator below title ─────────────────────────────────────
        JSeparator sep = new JSeparator();
        sep.setForeground(new Color(30, 41, 59));
        GridBagConstraints gbc_sep = new GridBagConstraints();
        gbc_sep.fill      = GridBagConstraints.HORIZONTAL;
        gbc_sep.gridwidth = 9;
        gbc_sep.insets    = new Insets(0, 15, 8, 15);
        gbc_sep.gridx     = 1;
        gbc_sep.gridy     = 4;
        getContentPane().add(sep, gbc_sep);

        // ── Select blast hit label ────────────────────────────────────────────
        JLabel BlastHitLabel = new JLabel("Select Blast Hit");
        BlastHitLabel.setFont(new Font("Monospaced", Font.PLAIN, 12));
        BlastHitLabel.setForeground(new Color(100, 116, 139)); // ── CHANGED
        GridBagConstraints gbc_BlastHitLabel = new GridBagConstraints();
        gbc_BlastHitLabel.insets = new Insets(0, 15, 12, 10);
        gbc_BlastHitLabel.gridx  = 1;
        gbc_BlastHitLabel.gridy  = 6;
        getContentPane().add(BlastHitLabel, gbc_BlastHitLabel);

        // ── Data labels — helper method keeps it clean ────────────────────────
        // UniProt ID
        JLabel UniprotIDLabel      = dataLabel("UniProt ID", 1, 7);
        JLabel UniprotIDValueLabel = valueLabel("-", 2, 7);
        getContentPane().add(UniprotIDLabel,      constraintsFor(1, 7));
        getContentPane().add(UniprotIDValueLabel, constraintsFor(2, 7));

        // Description
        JLabel ProteinDescLabel      = dataLabel("Description", 1, 8);
        JLabel ProteinDescValueLabel = valueLabel("-", 2, 8);
        GridBagConstraints gbc_desc = constraintsFor(2, 8);
        gbc_desc.gridwidth = 2;
        getContentPane().add(ProteinDescLabel,      constraintsFor(1, 8));
        getContentPane().add(ProteinDescValueLabel, gbc_desc);

        // Query / Match sequence
        JLabel QuerySeqLabel = dataLabel("<html>Query Sequence<br><br>Match Sequence</html>", 1, 11);
        JLabel SeqValueLabel = valueLabel("-", 2, 11);
        JLabel SeqAlignLenLabel = new JLabel("<html>(-:-)<br><br>(-:-)</html>");
        SeqAlignLenLabel.setFont(new Font("Monospaced", Font.PLAIN, 11));
        SeqAlignLenLabel.setForeground(new Color(100, 116, 139)); // ── CHANGED
        GridBagConstraints gbc_align = constraintsFor(3, 11);
        gbc_align.anchor = GridBagConstraints.LINE_END;
        getContentPane().add(QuerySeqLabel,    constraintsFor(1, 11));
        getContentPane().add(SeqValueLabel,    constraintsFor(2, 11));
        getContentPane().add(SeqAlignLenLabel, gbc_align);

        // E-value
        JLabel EvalueLabel      = dataLabel("E-value",   1, 14);
        JLabel EvalueAnnotLabel = valueLabel("-",         2, 14);
        getContentPane().add(EvalueLabel,      constraintsFor(1, 14));
        getContentPane().add(EvalueAnnotLabel, constraintsFor(2, 14));

        // Bit Score
        JLabel BitScoreLabel      = dataLabel("Bit Score", 1, 16);
        JLabel BitScoreAnnotLabel = valueLabel("-",         2, 16);
        getContentPane().add(BitScoreLabel,      constraintsFor(1, 16));
        getContentPane().add(BitScoreAnnotLabel, constraintsFor(2, 16));

        // Identity
        JLabel IdentityLabel      = dataLabel("Identity", 1, 18);
        JLabel IdentityValueLabel = valueLabel("-",        2, 18);
        getContentPane().add(IdentityLabel,      constraintsFor(1, 18));
        getContentPane().add(IdentityValueLabel, constraintsFor(2, 18));

        // ── Combo box for blast hits ──────────────────────────────────────────
        JComboBox<String> comboBox = new JComboBox<>();
        comboBox.setFont(new Font("Monospaced", Font.PLAIN, 12));
        comboBox.setBackground(new Color(22, 28, 45));     // ── CHANGED
        comboBox.setForeground(new Color(226, 232, 240));  // ── CHANGED
        GridBagConstraints gbc_comboBox = new GridBagConstraints();
        gbc_comboBox.insets = new Insets(0, 0, 12, 10);
        gbc_comboBox.fill   = GridBagConstraints.HORIZONTAL;
        gbc_comboBox.gridx  = 2;
        gbc_comboBox.gridy  = 6;
        getContentPane().add(comboBox, gbc_comboBox);
        comboBox.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                parseHit(hits.get(comboBox.getSelectedIndex()));
            }
        });

        // ── Scroll pane wrapping everything ──────────────────────────────────
        JScrollPane mainScroll = new JScrollPane(getContentPane());
        mainScroll.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
        mainScroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        setContentPane(mainScroll);

        setSize(660, 520);
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setVisible(true);

		WindowListener taskStarterWindowListener = new WindowListener() {
	        @Override
	        public void windowOpened(WindowEvent e) {
                labelList.add(UniprotIDValueLabel);
                labelList.add(ProteinDescValueLabel);
                labelList.add(SeqValueLabel);
                labelList.add(SeqAlignLenLabel);
                labelList.add(EvalueAnnotLabel);
                labelList.add(BitScoreAnnotLabel);
                labelList.add(IdentityValueLabel);
	        	
	        	hits = readBlastTsv(file);
                int hitnum = hits.size();
                if (hitnum == 0) {
                    JOptionPane.showMessageDialog(BlastViewGui.this,
                        "No BLAST results found", "Output Error", JOptionPane.WARNING_MESSAGE);
                } else {
                    int[] hitnumrange = IntStream.range(1, hitnum + 1).toArray();
                    String[] hitrange = Arrays.toString(hitnumrange).split("[\\[\\]]")[1].split(", ");
                    comboBox.setModel(new DefaultComboBoxModel<>(hitrange));
                    comboBox.setSelectedIndex(0);
                }
	        }
			@Override public void windowClosing(WindowEvent e)    {}
			@Override public void windowClosed(WindowEvent e)     {}
			@Override public void windowIconified(WindowEvent e)  {}
			@Override public void windowDeiconified(WindowEvent e){}
			@Override public void windowActivated(WindowEvent e)  {}
			@Override public void windowDeactivated(WindowEvent e){}
		};

		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.addWindowListener(taskStarterWindowListener);
	}
	
    // ── Helper: creates a muted grey data label ───────────────────────────────
    private JLabel dataLabel(String text, int col, int row) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Monospaced", Font.PLAIN, 12));
        lbl.setForeground(new Color(100, 116, 139)); // ── CHANGED: muted grey
        return lbl;
    }

    // ── Helper: creates a bright value label ─────────────────────────────────
    private JLabel valueLabel(String text, int col, int row) {
        JLabel lbl = new JLabel(text);
        lbl.setFont(new Font("Monospaced", Font.BOLD, 12));
        lbl.setForeground(new Color(226, 232, 240)); // ── CHANGED: near white
        return lbl;
    }

    // ── Helper: standard GridBagConstraints for data rows ────────────────────
    private GridBagConstraints constraintsFor(int col, int row) {
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.anchor = GridBagConstraints.WEST;
        gbc.insets = new Insets(0, col == 1 ? 15 : 0, 12, 5);
        gbc.gridx  = col;
        gbc.gridy  = row;
        return gbc;
    }
}