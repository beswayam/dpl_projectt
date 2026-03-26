package gui;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.GridBagLayout;
import javax.swing.JButton;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MainInterface extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MainInterface frame = new MainInterface();
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
	public MainInterface() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 592, 429);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		GridBagLayout gbl_contentPane = new GridBagLayout();
		gbl_contentPane.columnWidths = new int[]{44, 101, 0, 0};
		gbl_contentPane.rowHeights = new int[]{0, 0, 39, 0, 0};
		gbl_contentPane.columnWeights = new double[]{0.0, 0.0, 0.0, Double.MIN_VALUE};
		gbl_contentPane.rowWeights = new double[]{0.0, 0.0, 0.0, 0.0, Double.MIN_VALUE};
		contentPane.setLayout(gbl_contentPane);
		
		JLabel lblPageHeader = new JLabel("Welcome! Please Pick a tool to use:");
		lblPageHeader.setFont(new Font("Tahoma", Font.BOLD, 18));
		GridBagConstraints gbc_lblPageHeader = new GridBagConstraints();
		gbc_lblPageHeader.insets = new Insets(0, 0, 5, 0);
		gbc_lblPageHeader.gridx = 2;
		gbc_lblPageHeader.gridy = 1;
		contentPane.add(lblPageHeader, gbc_lblPageHeader);
		
		JButton btnBlastInterface = new JButton("BLASTP");
		btnBlastInterface.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				BlastInterface blastp = new BlastInterface();
				blastp.setLocationRelativeTo(null);
			    blastp.setVisible(true);
			}
		});
		btnBlastInterface.setBackground(Color.WHITE);
		GridBagConstraints gbc_btnBlastInterface = new GridBagConstraints();
		gbc_btnBlastInterface.fill = GridBagConstraints.BOTH;
		gbc_btnBlastInterface.insets = new Insets(0, 0, 0, 5);
		gbc_btnBlastInterface.gridx = 1;
		gbc_btnBlastInterface.gridy = 3;
		contentPane.add(btnBlastInterface, gbc_btnBlastInterface);

	}

}
