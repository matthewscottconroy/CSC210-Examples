import java.io.BufferedWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;

public class HexFileConverter {
    public static void main(String[] args) {
        // Verify exactly one argument is provided.
        if (args.length != 1) {
            System.err.println("Usage: java HexFileConverter <input-file>");
            return;
        }

        String inputFileName = args[0];
        List<String> hexLines;

        // Read all lines from the input file.
        try {
            hexLines = Files.readAllLines(Paths.get(inputFileName));
        } catch (IOException e) {
            System.err.println("Error: Could not read input file: " + e.getMessage());
            return;
        }

        // Prepare an array to hold the converted decimal values.
        int[] decimalValues = new int[hexLines.size()];

        // Process each line.
        for (int i = 0; i < hexLines.size(); i++) {
            String hexString = hexLines.get(i).trim();

            // Check for an empty line (treat it as an error).
            if (hexString.isEmpty()) {
                System.err.println("Error: Found an empty line, which is not a valid hexadecimal value.");
                return;
            }

            try {
                // Remove a leading "0x" or "0X" if present.
                if (hexString.startsWith("0x") || hexString.startsWith("0X")) {
                    hexString = hexString.substring(2);
                }
                // Convert from hexadecimal (base 16) to decimal.
                decimalValues[i] = Integer.parseInt(hexString, 16);
            } catch (NumberFormatException e) {
                System.err.println("Error: '" + hexLines.get(i) + "' is not a valid hexadecimal value.");
                return;
            }
        }

        // Write the decimal numbers to the output file "output.txt".
        String outputFileName = "output.txt";
        try (BufferedWriter writer = Files.newBufferedWriter(Paths.get(outputFileName))) {
            for (int value : decimalValues) {
                writer.write(Integer.toString(value));
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Error writing to output file: " + e.getMessage());
        }
    }
}

