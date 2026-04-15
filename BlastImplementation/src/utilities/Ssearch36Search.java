package utilities;

import java.io.File;

public final class Ssearch36Search {

    // maps the GUI names to the short matrix flags
    private static final String[][] MATRIX_MAP = {
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
        String matrixFlag = "BL62";
        for (String[] mapping : MATRIX_MAP) {
            if (mapping[0].equals(displayName)) {
                return mapping[1];
            }
        }
        return matrixFlag;
    }

    /**
     * Runs the local ssearch36 executable with the selected options.
     */
    public static int run(File queryFile, File dbFile,
            String evalue, String maxSeqs,
            String matrix, String outputPath) throws Exception {
    	System.out.println(queryFile.getPath());
        String osName = System.getProperty("os.name");
        String exeName = osName != null && osName.toLowerCase().contains("win") ? "ssearch36.exe" : "ssearch36";
        String ssearchExe = "tools" + File.separator + exeName;
        String matrixFlag = getMatrixFlag(matrix);
        ProcessBuilder pb = new ProcessBuilder(
                ssearchExe,
                "-s", matrixFlag,
                "-E", evalue,
                "-b", maxSeqs,
                "-d", maxSeqs,
                queryFile.getPath(),
                dbFile.getPath());
        pb.redirectErrorStream(true);
        pb.redirectOutput(new File(outputPath));
        Process p = pb.start();
        return p.waitFor();
    }
}