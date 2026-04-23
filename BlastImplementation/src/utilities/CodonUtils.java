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

	private static final Map<String, Character> CODON_TABLE = new HashMap<>();

	private static final Map<Character, Double> AMINOACID_TABLE = new HashMap<>();

	static {
		// --- codon table ---
		CODON_TABLE.put("TTT", 'F');
		CODON_TABLE.put("TTC", 'F');
		CODON_TABLE.put("TTA", 'L');
		CODON_TABLE.put("TTG", 'L');
		CODON_TABLE.put("CTT", 'L');
		CODON_TABLE.put("CTC", 'L');
		CODON_TABLE.put("CTA", 'L');
		CODON_TABLE.put("CTG", 'L');
		CODON_TABLE.put("ATT", 'I');
		CODON_TABLE.put("ATC", 'I');
		CODON_TABLE.put("ATA", 'I');
		CODON_TABLE.put("ATG", 'M');
		CODON_TABLE.put("GTT", 'V');
		CODON_TABLE.put("GTC", 'V');
		CODON_TABLE.put("GTA", 'V');
		CODON_TABLE.put("GTG", 'V');

		CODON_TABLE.put("TCT", 'S');
		CODON_TABLE.put("TCC", 'S');
		CODON_TABLE.put("TCA", 'S');
		CODON_TABLE.put("TCG", 'S');
		CODON_TABLE.put("CCT", 'P');
		CODON_TABLE.put("CCC", 'P');
		CODON_TABLE.put("CCA", 'P');
		CODON_TABLE.put("CCG", 'P');
		CODON_TABLE.put("ACT", 'T');
		CODON_TABLE.put("ACC", 'T');
		CODON_TABLE.put("ACA", 'T');
		CODON_TABLE.put("ACG", 'T');
		CODON_TABLE.put("GCT", 'A');
		CODON_TABLE.put("GCC", 'A');
		CODON_TABLE.put("GCA", 'A');
		CODON_TABLE.put("GCG", 'A');

		CODON_TABLE.put("TAT", 'Y');
		CODON_TABLE.put("TAC", 'Y');
		CODON_TABLE.put("TAA", '*');
		CODON_TABLE.put("TAG", '*');
		CODON_TABLE.put("CAT", 'H');
		CODON_TABLE.put("CAC", 'H');
		CODON_TABLE.put("CAA", 'Q');
		CODON_TABLE.put("CAG", 'Q');
		CODON_TABLE.put("AAT", 'N');
		CODON_TABLE.put("AAC", 'N');
		CODON_TABLE.put("AAA", 'K');
		CODON_TABLE.put("AAG", 'K');
		CODON_TABLE.put("GAT", 'D');
		CODON_TABLE.put("GAC", 'D');
		CODON_TABLE.put("GAA", 'E');
		CODON_TABLE.put("GAG", 'E');

		CODON_TABLE.put("TGT", 'C');
		CODON_TABLE.put("TGC", 'C');
		CODON_TABLE.put("TGA", '*');
		CODON_TABLE.put("TGG", 'W');
		CODON_TABLE.put("CGT", 'R');
		CODON_TABLE.put("CGC", 'R');
		CODON_TABLE.put("CGA", 'R');
		CODON_TABLE.put("CGG", 'R');
		CODON_TABLE.put("AGT", 'S');
		CODON_TABLE.put("AGC", 'S');
		CODON_TABLE.put("AGA", 'R');
		CODON_TABLE.put("AGG", 'R');
		CODON_TABLE.put("GGT", 'G');
		CODON_TABLE.put("GGC", 'G');
		CODON_TABLE.put("GGA", 'G');
		CODON_TABLE.put("GGG", 'G');

		// --- amino acid molecular weights (Dalton) ---
		AMINOACID_TABLE.put('A', 89.09);
		AMINOACID_TABLE.put('R', 174.20);
		AMINOACID_TABLE.put('N', 132.12);
		AMINOACID_TABLE.put('D', 133.10);
		AMINOACID_TABLE.put('C', 121.15);
		AMINOACID_TABLE.put('Q', 146.15);
		AMINOACID_TABLE.put('E', 147.13);
		AMINOACID_TABLE.put('G', 75.07);
		AMINOACID_TABLE.put('H', 155.16);
		AMINOACID_TABLE.put('I', 131.17);
		AMINOACID_TABLE.put('L', 131.17);
		AMINOACID_TABLE.put('K', 146.19);
		AMINOACID_TABLE.put('M', 149.21);
		AMINOACID_TABLE.put('F', 165.19);
		AMINOACID_TABLE.put('P', 115.13);
		AMINOACID_TABLE.put('S', 105.09);
		AMINOACID_TABLE.put('T', 119.12);
		AMINOACID_TABLE.put('W', 204.23);
		AMINOACID_TABLE.put('Y', 181.19);
		AMINOACID_TABLE.put('V', 117.15);
		AMINOACID_TABLE.put('*', 0.0); // Stop codon
	}

	/**
	 * Constructs a CodonUtils object.
	 */
	public CodonUtils() {
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

		if (AMINOACID_TABLE.get(aminoAcid) != null) {
			molarWeight = AMINOACID_TABLE.get(aminoAcid);

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
		if (CODON_TABLE.get(codon) != null) {
			aminoAcid = CODON_TABLE.get(codon);
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
		if (CODON_TABLE.containsKey(codon)) {
			valid = true;
		} else {
			valid = false;
		}

		return valid;
	}
}
