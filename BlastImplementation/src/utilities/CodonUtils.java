package utilities;

import java.util.HashMap;
import java.util.Map;

/**
 * Utility class for working with codons and amino acids.
 * 
 * <p>
 * Provides functionality to:
 * <ul>
 * <li>Translate DNA codons into amino acids</li>
 * <li>Retrieve molecular weights of amino acids</li>
 * <li>Validate codons</li>
 * </ul>
 * The codon table and amino acid masses are based on standard biological data.
 */
public class CodonUtils {

	private Map<String, Character> codonTable = new HashMap<>();
	private Map<Character, Double> aminoAcidTable = new HashMap<>();

	/**
	 * Constructs a CodonUtils object.
	 */
	public CodonUtils() {
		// --- codon table ---
		codonTable.put("TTT", 'F');
		codonTable.put("TTC", 'F');
		codonTable.put("TTA", 'L');
		codonTable.put("TTG", 'L');
		codonTable.put("CTT", 'L');
		codonTable.put("CTC", 'L');
		codonTable.put("CTA", 'L');
		codonTable.put("CTG", 'L');
		codonTable.put("ATT", 'I');
		codonTable.put("ATC", 'I');
		codonTable.put("ATA", 'I');
		codonTable.put("ATG", 'M');
		codonTable.put("GTT", 'V');
		codonTable.put("GTC", 'V');
		codonTable.put("GTA", 'V');
		codonTable.put("GTG", 'V');

		codonTable.put("TCT", 'S');
		codonTable.put("TCC", 'S');
		codonTable.put("TCA", 'S');
		codonTable.put("TCG", 'S');
		codonTable.put("CCT", 'P');
		codonTable.put("CCC", 'P');
		codonTable.put("CCA", 'P');
		codonTable.put("CCG", 'P');
		codonTable.put("ACT", 'T');
		codonTable.put("ACC", 'T');
		codonTable.put("ACA", 'T');
		codonTable.put("ACG", 'T');
		codonTable.put("GCT", 'A');
		codonTable.put("GCC", 'A');
		codonTable.put("GCA", 'A');
		codonTable.put("GCG", 'A');

		codonTable.put("TAT", 'Y');
		codonTable.put("TAC", 'Y');
		codonTable.put("TAA", '*');
		codonTable.put("TAG", '*');
		codonTable.put("CAT", 'H');
		codonTable.put("CAC", 'H');
		codonTable.put("CAA", 'Q');
		codonTable.put("CAG", 'Q');
		codonTable.put("AAT", 'N');
		codonTable.put("AAC", 'N');
		codonTable.put("AAA", 'K');
		codonTable.put("AAG", 'K');
		codonTable.put("GAT", 'D');
		codonTable.put("GAC", 'D');
		codonTable.put("GAA", 'E');
		codonTable.put("GAG", 'E');

		codonTable.put("TGT", 'C');
		codonTable.put("TGC", 'C');
		codonTable.put("TGA", '*');
		codonTable.put("TGG", 'W');
		codonTable.put("CGT", 'R');
		codonTable.put("CGC", 'R');
		codonTable.put("CGA", 'R');
		codonTable.put("CGG", 'R');
		codonTable.put("AGT", 'S');
		codonTable.put("AGC", 'S');
		codonTable.put("AGA", 'R');
		codonTable.put("AGG", 'R');
		codonTable.put("GGT", 'G');
		codonTable.put("GGC", 'G');
		codonTable.put("GGA", 'G');
		codonTable.put("GGG", 'G');

		// --- amino acid molecular weights (Dalton) ---
		aminoAcidTable.put('A', 89.09);
		aminoAcidTable.put('R', 174.20);
		aminoAcidTable.put('N', 132.12);
		aminoAcidTable.put('D', 133.10);
		aminoAcidTable.put('C', 121.15);
		aminoAcidTable.put('Q', 146.15);
		aminoAcidTable.put('E', 147.13);
		aminoAcidTable.put('G', 75.07);
		aminoAcidTable.put('H', 155.16);
		aminoAcidTable.put('I', 131.17);
		aminoAcidTable.put('L', 131.17);
		aminoAcidTable.put('K', 146.19);
		aminoAcidTable.put('M', 149.21);
		aminoAcidTable.put('F', 165.19);
		aminoAcidTable.put('P', 115.13);
		aminoAcidTable.put('S', 105.09);
		aminoAcidTable.put('T', 119.12);
		aminoAcidTable.put('W', 204.23);
		aminoAcidTable.put('Y', 181.19);
		aminoAcidTable.put('V', 117.15);
		aminoAcidTable.put('*', 0.0); // Stop codon
	}

	/**
	 * Returns the molecular weight of a given amino acid.
	 * 
	 * @param aminoAcid the amino acid character (e.g. "A", "C", "D")
	 * @return the molecular weight in Dalton
	 * @throws IllegalArgumentException if an invalid amino acid is entered
	 */
	public double baseWeight(char aminoAcid) {
		double molarWeight;

		if (aminoAcidTable.get(aminoAcid) != null) {
			molarWeight = aminoAcidTable.get(aminoAcid);

		} else {
			throw new IllegalArgumentException("Invalid base used:\"" + aminoAcid + "\"-remaining sentence");
		}
		return molarWeight;
	}

	/**
	 * Translates a DNA codon into its corresponding amino acid.
	 * 
	 * @param codon a three-letter DNA codon (e.g. "ATG")
	 * @return the corresponding amino acid character
	 * @throws IllegalArgumentException if the codon is not found
	 */
	public char getBase(String codon) {
		char aminoAcid;
		if (codonTable.get(codon) != null) {
			aminoAcid = codonTable.get(codon);
		} else {
			throw new IllegalArgumentException("Invalid codon used:  \"" + codon + "\"");
		}

		return aminoAcid;
	}

	/**
	 * Checks whether a given codon is valid according to the codon table.
	 * 
	 * @param codon a three-letter DNA codon
	 * @return true if the codon exists in the codon table, false otherwise
	 */
	public boolean checkCodon(String codon) {
		boolean valid;
		if (codonTable.containsKey(codon)) {
			valid = true;
		} else {
			valid = false;
		}

		return valid;
	}
}
