import java.math.BigInteger;

public class Hex {
    public static void main(String[] args) {
        // Check that exactly one argument is provided
        if (args.length != 1) {
            System.err.println("Usage: java Hex <hexNumber>");
            return;
        }

        String hexString = args[0];
        
        // Validate that the string is a proper hexadecimal
        // We can use a regular expression: ^[0-9A-Fa-f]+$ 
        // (some hex strings can start with 0x, but for this example we'll assume just raw hex)
        if (!hexString.matches("^[0-9A-Fa-f]+$")) {
            System.err.println("Error: '" + hexString + "' is not a valid hexadecimal number.");
            return;
        }

        try {
            // Convert the hexadecimal string to a BigInteger
            BigInteger decimalValue = new BigInteger(hexString, 16);
            // Print out the decimal equivalent
            System.out.println("Decimal value: " + decimalValue.toString(10));
        } catch (NumberFormatException e) {
            System.err.println("Error: '" + hexString + "' is not a valid hexadecimal number.");
        }
    }
}

