package gui;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import javax.swing.filechooser.FileNameExtensionFilter;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.io.File;
import java.time.Instant;
import java.awt.event.ActionEvent;
import javax.swing.JFileChooser;
import java.awt.SystemColor;

import utilities.Date;
import utilities.Sequence;
import java.awt.Cursor; // ── ADDED: for hand cursor
import javax.swing.border.Border; // ── ADDED
import java.awt.Graphics;          // ── ADDED
import java.awt.Graphics2D;        // ── ADDED
import java.awt.RenderingHints;    // ── ADDED
import javax.swing.Timer;
import utilities.Time;
import javax.swing.SwingConstants;

public class MainGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public File inputFile;
	public Sequence inputSeq;

	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainGui frame = new MainGui();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 559);
		setTitle("EzBLAST"); //added
		
		contentPane = new JPanel();
        contentPane.setBackground(new Color(13, 17, 28)); // ── CHANGED: dark navy background
        contentPane.setBorder(new EmptyBorder(30, 40, 0, 0)); // ── CHANGED: more padding
        setContentPane(contentPane);
        
        GridBagLayout gbl_contentPane = new GridBagLayout();
        gbl_contentPane.columnWidths  = new int[]{25, 200, 169, 0};
        gbl_contentPane.rowHeights    = new int[]{10, 40, 10, 10, 50, 10, 10, 0, 26, 0, 0, 0, 0, 10, 10, 50, 10, 0, 0, 0};
        gbl_contentPane.columnWeights = new double[]{0.0, 1.0, 0.0, Double.MIN_VALUE};
        gbl_contentPane.rowWeights    = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 1.0, Double.MIN_VALUE};
        contentPane.setLayout(gbl_contentPane);


        // ── ADDED: App title ─────────────────────────────────────────────────
        JLabel lblAppTitle = new JLabel("EzBLAST");
        lblAppTitle.setFont(new Font("Monospaced", Font.BOLD, 26)); // ── CHANGED: font
        lblAppTitle.setForeground(new Color(56, 189, 248));          // ── CHANGED: sky blue
        GridBagConstraints gbc_lblAppTitle = new GridBagConstraints();
        gbc_lblAppTitle.anchor = GridBagConstraints.WEST;
        gbc_lblAppTitle.insets = new Insets(0, 0, 5, 5);
        gbc_lblAppTitle.gridx  = 1;
        gbc_lblAppTitle.gridy  = 0;
        contentPane.add(lblAppTitle, gbc_lblAppTitle);

        // ── Header label ─────────────────────────────────────────────────────
        JLabel lblPageHeader = new JLabel("Welcome! Please pick a tool to use:");
        lblPageHeader.setFont(new Font("Monospaced", Font.PLAIN, 13)); // ── CHANGED: font
        lblPageHeader.setForeground(new Color(100, 116, 139));          // ── CHANGED: muted grey
        GridBagConstraints gbc_lblPageHeader = new GridBagConstraints();
        gbc_lblPageHeader.anchor = GridBagConstraints.WEST;
        gbc_lblPageHeader.insets = new Insets(0, 0, 6, 5);
        gbc_lblPageHeader.gridx  = 1;
        gbc_lblPageHeader.gridy  = 1;
        contentPane.add(lblPageHeader, gbc_lblPageHeader);

        // ── ADDED: Separator line ─────────────────────────────────────────────
        JSeparator separator = new JSeparator();
        separator.setForeground(new Color(30, 41, 59));
        GridBagConstraints gbc_sep = new GridBagConstraints();
        gbc_sep.anchor = GridBagConstraints.WEST;
        gbc_sep.insets = new Insets(0, 0, 10, 5);
        gbc_sep.gridx  = 1;
        gbc_sep.gridy  = 2;
        contentPane.add(separator, gbc_sep);

        // ── BLASTP button label ───────────────────────────────────────────────
        // ── ADDED: label above button acts as the button title ───────────────
        JLabel lblBlast = new JLabel("Run a protein sequence alignment");
        lblBlast.setFont(new Font("Monospaced", Font.PLAIN, 12)); // ── ADDED
        lblBlast.setForeground(new Color(100, 116, 139));          // ── CHANGED: muted text
        
        GridBagConstraints gbc_lblBlast = new GridBagConstraints();
        gbc_lblBlast.anchor = GridBagConstraints.WEST;
        gbc_lblBlast.insets = new Insets(0, 6, 8, 5); // ── CHANGED: bottom 2 → 8
        gbc_lblBlast.gridx  = 1;
        gbc_lblBlast.gridy  = 3;
        contentPane.add(lblBlast, gbc_lblBlast);

        // ── BLASTP button ─────────────────────────────────────────────────────
        JButton btnBlastInterface = new JButton("BLASTP") {
            @Override
            protected void paintComponent(Graphics g) {
                // RenderingHints makes the edges smooth and not jagged
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(56, 189, 248)); // ── blue fill
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20); // ── 20 = roundness of corners
                super.paintComponent(g);
            }
        };
        
        btnBlastInterface.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnBlastInterface.setForeground(Color.WHITE);
        btnBlastInterface.setContentAreaFilled(false); // ── lets our custom paint show
        btnBlastInterface.setBorderPainted(false);      // ── removes default border
        btnBlastInterface.setFocusPainted(false);
        btnBlastInterface.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnBlastInterface.setBorder(new EmptyBorder(8, 18, 8, 18)); // ── padding inside button
        btnBlastInterface.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent arg0) {
                BlastGui blastp = new BlastGui();
                blastp.setLocationRelativeTo(null);
                blastp.setVisible(true);
            }
        });
        GridBagConstraints gbc_btnBlastInterface = new GridBagConstraints();
        gbc_btnBlastInterface.anchor = GridBagConstraints.WEST; // ── CHANGED: no fill, fits to text
        gbc_btnBlastInterface.insets = new Insets(0, 0, 16, 5);
        gbc_btnBlastInterface.gridx  = 1;
        gbc_btnBlastInterface.gridy  = 4;
        contentPane.add(btnBlastInterface, gbc_btnBlastInterface);

        // ── File Statistics label ─────────────────────────────────────────────
        JLabel lblStats = new JLabel("Analyse a FASTA file and view sequence statistics");
        lblStats.setFont(new Font("Monospaced", Font.PLAIN, 12)); // ── ADDED
        lblStats.setForeground(new Color(100, 116, 139));          // ── CHANGED: muted text
        
        GridBagConstraints gbc_lblStats = new GridBagConstraints();
        gbc_lblStats.anchor = GridBagConstraints.WEST;
        gbc_lblStats.insets = new Insets(0, 6, 8, 5); // ── CHANGED: bottom 2 → 8
        gbc_lblStats.gridx  = 1;
        gbc_lblStats.gridy  = 6;
        contentPane.add(lblStats, gbc_lblStats);

        // ── File Statistics button ────────────────────────────────────────────
        JButton btnInputStatistics = new JButton("File Statistics") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(52, 211, 153)); // ── teal fill
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        btnInputStatistics.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnInputStatistics.setForeground(Color.WHITE);
        btnInputStatistics.setContentAreaFilled(false);
        btnInputStatistics.setBorderPainted(false);
        btnInputStatistics.setFocusPainted(false);
        btnInputStatistics.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnInputStatistics.setBorder(new EmptyBorder(8, 18, 8, 18));
        btnInputStatistics.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) { 	
            	JFileChooser fileChooser = new JFileChooser();
                FileNameExtensionFilter fasta_filter = new FileNameExtensionFilter(
						"FASTA files (*.fasta, *.fa, *.fna)", 
						"fasta", "fa", "fna"); 
                FileNameExtensionFilter txt_filter = new FileNameExtensionFilter(
						"Text files (*.txt)", 
						"txt");
                fileChooser.setDialogTitle("Select Query FASTA File");
                fileChooser.addChoosableFileFilter(fasta_filter);
				fileChooser.addChoosableFileFilter(txt_filter);	
                fileChooser.setFileFilter(fasta_filter);
                int file = fileChooser.showOpenDialog(MainGui.this);
                if (file == JFileChooser.APPROVE_OPTION) {
					inputFile = fileChooser.getSelectedFile();
					Sequence inputSeq = new Sequence(inputFile);
					
					// pass file to second GUI
				    StatisticsGui stats = new StatisticsGui(inputFile);
				    stats.setLocationRelativeTo(null);
				    stats.setVisible(true);
				}
            }
        });
        GridBagConstraints gbc_btnInputStatistics = new GridBagConstraints();
        gbc_btnInputStatistics.anchor = GridBagConstraints.WEST; // ── CHANGED: fits to text size
        gbc_btnInputStatistics.insets = new Insets(0, 0, 16, 5);
        gbc_btnInputStatistics.gridx  = 1;
        gbc_btnInputStatistics.gridy  = 8;
        contentPane.add(btnInputStatistics, gbc_btnInputStatistics);
        Date today = new Date();
        String todayFormat = today.display();
        
        JLabel lblUpload = new JLabel("Upload your .tsv result file to visualise results");
        lblUpload.setFont(new Font("Monospaced", Font.PLAIN, 12)); // ── ADDED
        lblUpload.setForeground(new Color(100, 116, 139));          // ── CHANGED: muted text
        
        GridBagConstraints gbc_lblUpload = new GridBagConstraints();
        gbc_lblUpload.anchor = GridBagConstraints.WEST;
        gbc_lblUpload.insets = new Insets(0, 6, 8, 5); // ── CHANGED: bottom 2 → 8
        gbc_lblUpload.gridx  = 1;
        gbc_lblUpload.gridy  = 8;
        contentPane.add(lblUpload, gbc_lblUpload);
        
        JButton btnUploadtsv = new JButton("Upload .tsv file") {
            @Override
            protected void paintComponent(Graphics g) {
                Graphics2D g2 = (Graphics2D) g;
                g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
                g2.setColor(new Color(52, 211, 153)); // ── teal fill
                g2.fillRoundRect(0, 0, getWidth(), getHeight(), 20, 20);
                super.paintComponent(g);
            }
        };
        btnUploadtsv.setFont(new Font("Monospaced", Font.BOLD, 12));
        btnUploadtsv.setForeground(Color.WHITE);
        btnUploadtsv.setContentAreaFilled(false);
        btnUploadtsv.setBorderPainted(false);
        btnUploadtsv.setFocusPainted(false);
        btnUploadtsv.setCursor(new Cursor(Cursor.HAND_CURSOR));
        btnUploadtsv.setBorder(new EmptyBorder(8, 18, 8, 18));
        
        GridBagConstraints gbc_btnUploadtsv = new GridBagConstraints();
        gbc_btnUploadtsv.anchor = GridBagConstraints.WEST; // ── CHANGED: fits to text size
        gbc_btnUploadtsv.insets = new Insets(0, 0, 16, 5);
        gbc_btnUploadtsv.gridx  = 1;
        gbc_btnUploadtsv.gridy  = 9;
        contentPane.add(btnUploadtsv, gbc_btnUploadtsv);
        
        // Help button of File Statistics
        JButton btnHelpFileStatistics = new JButton("Help");
        btnHelpFileStatistics.setVerticalAlignment(SwingConstants.TOP);
        btnHelpFileStatistics.setHorizontalAlignment(SwingConstants.LEFT);
        GridBagConstraints gbc_btnHelpFileStatistics = new GridBagConstraints();
        gbc_btnHelpFileStatistics.anchor = GridBagConstraints.WEST;
        gbc_btnHelpFileStatistics.insets = new Insets(0, 0, 5, 0);
        gbc_btnHelpFileStatistics.gridx = 2;
        gbc_btnHelpFileStatistics.gridy = 8;
        contentPane.add(btnHelpFileStatistics, gbc_btnHelpFileStatistics);
        
        StatisticsGui.applyRoundedStyle(btnHelpFileStatistics, new Color(56, 189, 248), new Color(22, 28, 45));
        
        btnHelpFileStatistics.setCursor(new Cursor(Cursor.HAND_CURSOR));   // ── ADDED: hand cursor
        btnHelpFileStatistics.addActionListener(e -> {
            JFrame helpFrame = new JFrame("FileStatistics Help");
            helpFrame.setSize(400, 300);
            JTextArea textArea = new JTextArea();
            textArea.setText(
                "File Statistics Instructions:\n\n" +
                "1. Fill in what we should do for file statistics here"
            );
            textArea.setEditable(false);
            textArea.setLineWrap(true);
            textArea.setWrapStyleWord(true);
            JScrollPane sp = new JScrollPane(textArea);
            helpFrame.getContentPane().add(sp);
            helpFrame.setLocationRelativeTo(null);
            helpFrame.setVisible(true);
        });
        
        JLabel lblShowCurrDate = new JLabel("");
        lblShowCurrDate.setForeground(new Color(255, 255, 255));
        GridBagConstraints gbc_lblShowCurrDate = new GridBagConstraints();
        gbc_lblShowCurrDate.insets = new Insets(0, 0, 5, 5);
        gbc_lblShowCurrDate.gridx = 0;
        gbc_lblShowCurrDate.gridy = 18;
        contentPane.add(lblShowCurrDate, gbc_lblShowCurrDate);
        lblShowCurrDate.setText(todayFormat);
        
        
        
        // Show Runtime of program 

        JLabel lblShowAppTime = new JLabel();
        lblShowAppTime.setVerticalAlignment(SwingConstants.BOTTOM);
        lblShowAppTime.setHorizontalAlignment(SwingConstants.RIGHT);
        lblShowAppTime.setForeground(new Color(255, 255, 255));
        GridBagConstraints gbc_lblShowAppTime = new GridBagConstraints();
        gbc_lblShowAppTime.gridx = 2;
        gbc_lblShowAppTime.gridy = 18;
        contentPane.add(lblShowAppTime, gbc_lblShowAppTime);
        Time time = new Time();
        Instant startTime = time.getStartTime();
        Timer timer = new Timer(1000, new ActionListener() {
        	public void actionPerformed(ActionEvent e) {
        		lblShowAppTime.setText(time.display());
        	}
        });
        timer.start();
    }
		
	}