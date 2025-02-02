public class HexManual {

    public static void main(String[] args) {
        // Check that exactly one argument is provided
        if (args.length != 1) {
            System.err.println("Usage: java HexManual <hexNumber>");
            return;
        }

        String hexString = args[0];

        // Validate that the string is a proper hexadecimal
        if (!hexString.matches("^[0-9A-Fa-f]+$")) {
            System.err.println("Error: '" + hexString + "' is not a valid hexadecimal number.");
            return;
        }

        // Convert the hex string to decimal (as a string)
        String decimalResult = convertHexToDecimalString(hexString);
        
        // Print out the decimal equivalent
        System.out.println("Decimal value: " + decimalResult);
    }

    /**
     * Converts a hexadecimal string to a decimal string manually.
     * @param hex the input hexadecimal string (validated to contain only [0-9A-Fa-f])
     * @return the decimal representation as a string
     */
    private static String convertHexToDecimalString(String hex) {
        String decimal = "0";  // start with zero in decimal

        // Process each character in the hex string
        for (int i = 0; i < hex.length(); i++) {
            // Current hex character
            char c = hex.charAt(i);
            // Convert the single hex digit to an integer value (0..15)
            int digitValue = hexCharToInt(c);

            // Multiply the current decimal result by 16
            decimal = multiplyDecimalStringBy16(decimal);

            // Add the current digitValue
            decimal = addIntToDecimalString(decimal, digitValue);
        }
        return decimal;
    }

    /**
     * Converts a single hex character [0-9A-Fa-f] to its integer value [0..15].
     */
    private static int hexCharToInt(char c) {
        if (c >= '0' && c <= '9') {
            return c - '0';
        } else if (c >= 'A' && c <= 'F') {
            return 10 + (c - 'A');
        } else {
            return 10 + (c - 'a');
        }
    }

    /**
     * Multiplies a decimal number (in string form) by 16, returning the result as a string.
     */
    private static String multiplyDecimalStringBy16(String decimal) {
        // We can do: decimal * 16 = decimal * (10 + 6)
        // But more directly, to multiply by 16 in decimal:
        //    1) parse each digit from right to left
        //    2) multiply it by 16
        //    3) add carry
        //    4) build the new string

        int carry = 0;
        StringBuilder sb = new StringBuilder();

        // Process the decimal string from right to left
        for (int i = decimal.length() - 1; i >= 0; i--) {
            int d = decimal.charAt(i) - '0';  // convert char digit to int
            int product = d * 16 + carry;
            carry = product / 10;            // carry
            int digit = product % 10;        // remainder

            sb.append(digit);
        }

        // If there's remaining carry
        while (carry > 0) {
            sb.append(carry % 10);
            carry /= 10;
        }

        // reverse to get the correct order
        return sb.reverse().toString();
    }

    /**
     * Adds an integer (0..15) to a decimal string, returning the new decimal string.
     */
    private static String addIntToDecimalString(String decimal, int toAdd) {
        // We'll add `toAdd` to the decimal string from right to left.
        int carry = toAdd;
        StringBuilder sb = new StringBuilder();

        for (int i = decimal.length() - 1; i >= 0; i--) {
            int d = decimal.charAt(i) - '0';
            int sum = d + carry;
            carry = sum / 10;
            int digit = sum % 10;
            sb.append(digit);
        }

        // If there's any leftover carry, append it as well
        while (carry > 0) {
            sb.append(carry % 10);
            carry /= 10;
        }

        return sb.reverse().toString();
    }

}

