package utilities;
import uk.ac.ebi.uniprot.dataservice.client.Client;
import uk.ac.ebi.uniprot.dataservice.client.ServiceFactory;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.*;
import uk.ac.ebi.uniprot.dataservice.client.alignment.blast.input.DatabaseOption;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import javax.swing.JOptionPane;

import java.io.File;
import java.io.FileWriter;   
import java.io.IOException;  

public class BlastpSearch {
	public static BlastResult<UniProtHit> runUniprotBlast(String sequence) {
	    ServiceFactory serviceFactoryInstance = Client.getServiceFactoryInstance();
	    UniProtBlastService uniProtBlastService = serviceFactoryInstance.getUniProtBlastService();
	    BlastInput input = new BlastInput.Builder(DatabaseOption.SWISSPROT, sequence).build();	    
	    CompletableFuture<BlastResult<UniProtHit>> resultFuture = uniProtBlastService.runBlast(input);

	    try {
	            BlastResult<UniProtHit> blastResult = resultFuture.get();
	            return blastResult;
	            

	        } catch (ExecutionException e) {
			    JOptionPane.showMessageDialog(new JOptionPane(), 
			    		"BLAST aborted, please check your internet connection", 
		                "BLAST Error", 
		                JOptionPane.ERROR_MESSAGE);
			    e.printStackTrace();
	        } catch (InterruptedException e) {
			    JOptionPane.showMessageDialog(new JOptionPane(), 
			    		"BLAST interrupted", 
		                "BLAST Error", 
		                JOptionPane.ERROR_MESSAGE);
	        	e.printStackTrace();
	        }
		return null; }
	    
	
	public static void writeUniprotBlastOutput(BlastResult<UniProtHit> blastResult,float mineval,int maxseq, File file) {
        try {
    	      FileWriter myWriter = new FileWriter(file);
    	      myWriter.write("hit_num\tuniprot_ID\tdescription\tsequence\te-value\tbit-score\tidentity\tquery_seq\tquery_start\tquery_end\tmatch_start\tmatch_end\n");
    	      myWriter.close();  
    	    } catch (IOException e) {
			    JOptionPane.showMessageDialog(new JOptionPane(), 
			    		"failed to write output to file", 
		                "output Error", 
		                JOptionPane.ERROR_MESSAGE);
    	      e.printStackTrace();}
      int numseq = 0;
      for (UniProtHit hit : blastResult.hits()) {
    	  UniProtBlastSummary summary = hit.getSummary();
    	  Alignment alignment = summary.getAlignments().get(0);
    	  double eval=alignment.getExpectation();
    	  if(eval < mineval) {
    		  if(numseq!=maxseq) {
    			  String accession = hit.getEntry().getPrimaryUniProtAccession().getValue();
    			  String description = summary.getDescription();
    			  String sequence = alignment.getMatchSeq();
    			  float bitscore = alignment.getBitScore();
    			  float identity = alignment.getIdentity();
    			  String query_seq = alignment.getQuerySeq();
    			  int query_start = alignment.getStartQuerySeq();
    			  int query_end = alignment.getEndQuerySeq();		
    			  int match_start = alignment.getStartMatchSeq();
    			  int match_end = alignment.getEndMatchSeq();
    			  numseq++;
    			  try {
		    	      FileWriter myWriter = new FileWriter(file,true);
		    	      myWriter.append(  numseq + "\t" + accession + "\t" + description + "\t" + sequence + 
		    	      "\t" + eval + "\t" + bitscore + "\t" + identity + "\t" + query_seq + "\t" + query_start + "\t" + query_end + "\t" + 
		    	      match_start + "\t" + match_end + "\n");
		    	      myWriter.close();  
		    	    } catch (IOException e) {
					    JOptionPane.showMessageDialog(new JOptionPane(), 
					    		"failed to write output to file", 
				                "output Error", 
				                JOptionPane.ERROR_MESSAGE);
		    	      e.printStackTrace();
    	    }}}
      }
	
}
}