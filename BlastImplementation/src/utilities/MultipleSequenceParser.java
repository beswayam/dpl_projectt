package utilities;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.util.ArrayList;

import javax.swing.JOptionPane;

public class MultipleSequenceParser {
public static void main(String[] args)  {
	
	String sequences=">JOE\nAEEKSGYSFVHCKVTGTGGGIYLGRAWMSH\n>unknown_pecter\r\n"
			+ "RVIIKMAPGEYKEKVTIDRNKPFI";
	ArrayList<Sequence> parsedSeqList= parseMultipleSeqs(sequences);
	System.out.println(parsedSeqList.get(1).getSequence());


	File sequenceFile= new File("pectinesterases.fasta");
	ArrayList<Sequence> parsedSeqListFromFile= parseMultipleSeqs(sequenceFile);
	System.out.println(parsedSeqListFromFile.get(1).getSequence());
	
	}
public static ArrayList<Sequence> parseMultipleSeqs(File sequencefile){
	String sequences = "";
	try {
		sequences = Files.readString(sequencefile.toPath());
	} catch (IOException e) {
		JOptionPane.showMessageDialog(new JOptionPane(),
		"Failed to find input fasta file",
		"input Error", JOptionPane.ERROR_MESSAGE);
		e.printStackTrace();
	}
	return parseMultipleSeqs(sequences);
	
}

public static ArrayList<Sequence> parseMultipleSeqs(String sequences){
	String[] lines = sequences.split("\\r?\\n");
	ArrayList<Sequence> seqList = new ArrayList<Sequence>();
	String oneSeq = "";
	for(int i=0;i<lines.length;i++) {
		if(lines[i].startsWith(">") && oneSeq != "") {
			seqList.add(new Sequence(oneSeq));
			oneSeq = "";
		}
		oneSeq += lines[i] + "\n";
	}
	seqList.add(new Sequence(oneSeq));
	
	return seqList;
}
}
