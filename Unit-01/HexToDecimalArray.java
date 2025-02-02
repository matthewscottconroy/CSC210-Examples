public class HexToDecimalArray {
    public static void main(String[] args) {
        // If no arguments are provided, print an error message to stderr.
        if (args.length == 0) {
            System.err.println("Error: No hexadecimal values provided.");
            return;
        }

        // Create an array to hold the decimal conversions.
        int[] decimalValues = new int[args.length];

        // Process each command-line argument.
        for (int i = 0; i < args.length; i++) {
            String hexInput = args[i];
            try {
                // Optionally remove a leading "0x" or "0X" if present.
                if (hexInput.startsWith("0x") || hexInput.startsWith("0X")) {
                    hexInput = hexInput.substring(2);
                }
                // Parse the hex string into an integer using base 16.
                decimalValues[i] = Integer.parseInt(hexInput, 16);
            } catch (NumberFormatException e) {
                // If parsing fails, print an error message to stderr and exit.
                System.err.println("Error: '" + args[i] + "' is not a valid hexadecimal value.");
                return;
            }
        }

        // Print the array of decimal values to stdout.
        for (int value : decimalValues) {
            System.out.println(value);
        }
    }
}

