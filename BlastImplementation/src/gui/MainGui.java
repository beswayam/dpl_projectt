package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
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
import java.awt.event.ActionEvent;

import javax.swing.JFileChooser;

import java.awt.SystemColor;
import utilities.Sequence;


public class MainGui extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	public File inputFile;
	public Sequence inputSeq;

	/**
	 * Launch the application.
	 */
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

	/**
	 * Create the frame.
	 */
	public MainGui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 769, 559);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
//		contentPane.setBackground(new Color(15, 17, 26));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{25, 413, 169, 0};
		gbl_contentPane.rowHeights = new int[]{10, 100, 50, 50, 0, 0, 0};
		gbl_contentPane.columnWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
				
				// BLASTP button 
				JButton btnBlastInterface = new JButton("BLASTP");
				//		btnBlastInterface.setBackground(new Color(28, 33, 52));
				//        btnBlastInterface.setForeground(new Color(60, 210, 140));
						btnBlastInterface.setFocusPainted(false);
						btnBlastInterface.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent arg0) {
								BlastGui blastp = new BlastGui();
								blastp.setLocationRelativeTo(null);
							    blastp.setVisible(true);
							}
						});
						
						
						// Header of the main interface
						JLabel lblPageHeader = new JLabel("Welcome! Please Pick a tool to use:");
						lblPageHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
						//		lblPageHeader.setForeground(new Color(140, 180, 255));
								GridBagConstraints gbc_lblPageHeader = new GridBagConstraints();
								gbc_lblPageHeader.insets = new Insets(0, 0, 5, 5);
								gbc_lblPageHeader.gridx = 1;
								gbc_lblPageHeader.gridy = 1;
								contentPane.add(lblPageHeader, gbc_lblPageHeader);
						btnBlastInterface.setBackground(Color.WHITE);
						GridBagConstraints gbc_btnBlastInterface = new GridBagConstraints();
						gbc_btnBlastInterface.insets = new Insets(0, 0, 5, 5);
						gbc_btnBlastInterface.fill = GridBagConstraints.BOTH;
						gbc_btnBlastInterface.gridx = 1;
						gbc_btnBlastInterface.gridy = 2;
						contentPane.add(btnBlastInterface, gbc_btnBlastInterface);
						
						
						// input statistics button start
						JButton btnInputStatistics = new JButton("File statistics");
						btnInputStatistics.setBackground(Color.WHITE);
						btnInputStatistics.setFocusPainted(false);
						btnInputStatistics.addActionListener(new ActionListener() {
							public void actionPerformed(ActionEvent e) {
								// open file explorer
								JFileChooser fileChooser = new JFileChooser();
								
								// only shows files with .fasta extension in the file chooser
								FileNameExtensionFilter fasta_filter = new FileNameExtensionFilter(
										"FASTA files (*.fasta, *.fa, *.fna)", 
										"fasta", "fa", "fna"); 
								
								FileNameExtensionFilter txt_filter = new FileNameExtensionFilter(
										"Text files (*.txt)", 
										"txt");
								
								fileChooser.setDialogTitle("Select Query FASTA File");
								fileChooser.addChoosableFileFilter(fasta_filter);
								fileChooser.addChoosableFileFilter(txt_filter);				
								fileChooser.setFileFilter(fasta_filter); //Applies extension filter;
								
								// choose file in file explorer
								int file = fileChooser.showOpenDialog(MainGui.this);
								
								// if the file is a valid file, pass the file to the FileStatistics GUI
								if (file == JFileChooser.APPROVE_OPTION) {
									inputFile = fileChooser.getSelectedFile();
									inputSeq = new Sequence(inputFile);
									
									// pass file to second GUI
								    StatisticsGui stats = new StatisticsGui(inputFile);
								    stats.setLocationRelativeTo(null);
								    stats.setVisible(true);
								}
							}
						});
						// input statistics button end
						
						btnInputStatistics.setForeground(SystemColor.infoText);
						GridBagConstraints gbc_btnInputStatistics = new GridBagConstraints();
						gbc_btnInputStatistics.fill = GridBagConstraints.BOTH;
						gbc_btnInputStatistics.insets = new Insets(0, 0, 5, 5);
						gbc_btnInputStatistics.gridx = 1;
						gbc_btnInputStatistics.gridy = 3;
						contentPane.add(btnInputStatistics, gbc_btnInputStatistics);

	}

}
