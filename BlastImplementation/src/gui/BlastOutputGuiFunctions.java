package gui;

import java.awt.GraphicsConfiguration;
import java.awt.HeadlessException;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class BlastOutputGuiFunctions extends JFrame {
	private static final long serialVersionUID = 1L;
	ArrayList<String[]> hits;
	ArrayList<JLabel> labelList = new ArrayList<JLabel>();
	
	protected ArrayList<String[]> readBlastTsv(File file) {
	    ArrayList<String[]> hits = new ArrayList<String[]>();
		try (Scanner myReader = new Scanner(file)) {
			String fileheader = myReader.nextLine();
	        while (myReader.hasNextLine()) {
	        	String data = myReader.nextLine();
	        	String[] dataArray = data.split("\t");
	        	hits.add(dataArray);
	        }
	    } catch (FileNotFoundException e) {
			    JOptionPane.showMessageDialog(this,
			    		"Temporary BLAST output tsv "+file+" not found",
		                "File Error",
		                JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		}
		return hits;
	}
	
	protected void parseHit(String[] hitdata) {
		String hitnum = hitdata[0];
		String id = hitdata[1];
		String description = hitdata[2];
		String match_seq = hitdata[3];
		String eval = hitdata[4];
		String bitscore = hitdata[5];
		String identity = hitdata[6];
		String query_seq = hitdata[7];
		String query_start = hitdata[8];
		String query_end = hitdata[9];
		String match_start = hitdata[10];
		String match_end = hitdata[11];
		String matches = findMatches(query_seq, match_seq);
		labelList.get(0).setText(id);
		labelList.get(1).setText(description);
		labelList.get(2).setText("<html>"+query_seq.replaceAll(" ","\u00a0")+"<br>"+matches+"<br>"+match_seq.replaceAll(" ","\u00a0")+"</html>");
		labelList.get(3).setText("<html>("+query_start+":"+query_end+")<br><br>("+match_start+":"+match_end+")</html>");
		labelList.get(4).setText(eval);
		labelList.get(5).setText(bitscore);
		labelList.get(6).setText(identity + "%");

	}

	private String findMatches(String seq1, String seq2) {
		int seqlen = seq1.length();
		String matches = "";
		for(int i = 0; i < seqlen; i++){
			if(seq1.charAt(i)==seq2.charAt(i)) {
				matches+="|";
			} else {
				matches+="&nbsp";
			}
		}
		return matches;
	}
	
}