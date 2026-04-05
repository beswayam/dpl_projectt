package utilities;

import java.io.File;

public class Ssearch36Search {

	private static String[][] MATRIX_MAP = {
		{"BLOSUM45", "BL45"},
		{"BLOSUM50", "BL50"},
		{"BLOSUM62", "BL62"},
		{"BLOSUM80", "BL80"},
		{"BLOSUM90", "BL90"},
		{"PAM30", "P30"},
		{"PAM70", "P70"},
		{"PAM250", "P250"}
	};

	private Ssearch36Search() {
	}

	private static String getMatrixFlag(String displayName) {
		for (String[] entry : MATRIX_MAP) {
			if (entry[0].equals(displayName)) {
				return entry[1];
			}
		}
		return "BL62";
	}

	public static int run(File queryFile, File dbFile,
			String evalue, String maxSeqs,
			String matrix, String outputPath) throws Exception {

		String osName = System.getProperty("os.name").toLowerCase();
		String exeName = osName.contains("win") ? "ssearch36.exe" : "ssearch36";

		// Build absolute path so it works regardless of Eclipse working directory
		File ssearchExeFile = new File("tools" + File.separator + exeName).getAbsoluteFile();
		if (!ssearchExeFile.exists()) {
			throw new IllegalStateException("SSEARCH36 executable not found: " + ssearchExeFile.getPath());
		}

		String matrixFlag = getMatrixFlag(matrix);

		ProcessBuilder pb = new ProcessBuilder(
			ssearchExeFile.getPath(),
			"-s", matrixFlag,
			"-E", evalue,
			"-b", maxSeqs,
			"-d", maxSeqs,
			queryFile.getAbsolutePath(),
			dbFile.getAbsolutePath()
		);

		pb.redirectErrorStream(true);
		pb.redirectOutput(new File(outputPath));

		Process p = pb.start();
		return p.waitFor();
	}
}
