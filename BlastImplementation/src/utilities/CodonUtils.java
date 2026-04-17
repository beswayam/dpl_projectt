package utilities;

import java.util.HashMap;
import java.util.Map;

public class CodonUtils {

	private static final Map<String, Character> CODON_TABLE = new HashMap<>();
	private static final Map<Character, Double> AMINO_MASS = new HashMap<>();

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
		AMINO_MASS.put('A', 89.09);
		AMINO_MASS.put('R', 174.20);
		AMINO_MASS.put('N', 132.12);
		AMINO_MASS.put('D', 133.10);
		AMINO_MASS.put('C', 121.15);
		AMINO_MASS.put('Q', 146.15);
		AMINO_MASS.put('E', 147.13);
		AMINO_MASS.put('G', 75.07);
		AMINO_MASS.put('H', 155.16);
		AMINO_MASS.put('I', 131.17);
		AMINO_MASS.put('L', 131.17);
		AMINO_MASS.put('K', 146.19);
		AMINO_MASS.put('M', 149.21);
		AMINO_MASS.put('F', 165.19);
		AMINO_MASS.put('P', 115.13);
		AMINO_MASS.put('S', 105.09);
		AMINO_MASS.put('T', 119.12);
		AMINO_MASS.put('W', 204.23);
		AMINO_MASS.put('Y', 181.19);
		AMINO_MASS.put('V', 117.15);
		AMINO_MASS.put('*', 0.0); // Stop codon
	}

	public CodonUtils() {
	}

	public double baseWeight(char amino_acid) {
		double molar_weight;

		if (AMINO_MASS.get(amino_acid) != null) {
			molar_weight = AMINO_MASS.get(amino_acid);

		} else {
			throw new IllegalArgumentException("Invalid base used:\"" + amino_acid + "\"-remaining sentence");
		}
		return molar_weight;
	}

	public char getBase(String codon) {
		char amino_acid;
		if (CODON_TABLE.get(codon) != null) {
			amino_acid = CODON_TABLE.get(codon);
		} else {
			throw new IllegalArgumentException("Invalid codon used:  \"" + codon + "\"");
		}

		return amino_acid;
	}

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
