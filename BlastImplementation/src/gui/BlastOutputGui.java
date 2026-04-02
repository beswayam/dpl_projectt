package gui;

import java.awt.EventQueue;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

public class BlastOutputGui extends JFrame{
	
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;

	/**
	 * Launch the application.
	 */

	/**
	 * Create the frame.
	 * @param filename 
	 */
	
	public BlastOutputGui(String filename) {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		
	//set up windowlistener
		WindowListener taskStarterWindowListener = new WindowListener() {
	        @Override
	        public void windowOpened(WindowEvent e) {
	        	ArrayList<String[]> hits=readBlastTsv(filename);
	        	System.out.println(hits.get(4)[1]);
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
		
	            
		

	
	


	private ArrayList<String[]> readBlastTsv(String filename) {
	    ArrayList<String[]> hits = new ArrayList<String[]>();
	    File file = new File(filename);
		try (Scanner myReader = new Scanner(file)) {
			String header = myReader.nextLine();
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
	
		


