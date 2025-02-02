import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class HexConverterGUI extends JFrame {

    private JTextField hexInputField;
    private JButton convertButton;
    private JLabel resultLabel;

    public HexConverterGUI() {
        super("Hex to Decimal Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 120);
        setLocationRelativeTo(null); // Center on screen

        // Create components
        JLabel promptLabel = new JLabel("Enter Hex Value:");
        hexInputField = new JTextField(20);
        convertButton = new JButton("Convert");
        resultLabel = new JLabel("Decimal value will appear here");

        // Attach an ActionListener to the button
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertHexToDecimal();
            }
        });

        // Layout the components
        JPanel inputPanel = new JPanel();
        inputPanel.add(promptLabel);
        inputPanel.add(hexInputField);

        JPanel buttonPanel = new JPanel();
        buttonPanel.add(convertButton);

        JPanel resultPanel = new JPanel();
        resultPanel.add(resultLabel);

        // Add panels to the frame
        getContentPane().setLayout(new BorderLayout());
        getContentPane().add(inputPanel, BorderLayout.NORTH);
        getContentPane().add(buttonPanel, BorderLayout.CENTER);
        getContentPane().add(resultPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    /**
     * Attempts to convert the text in the input field from hex to decimal.
     * Displays the result in resultLabel, or an error message if invalid.
     */
    private void convertHexToDecimal() {
        String hexText = hexInputField.getText().trim();

        // Quick validation: check if empty
        if (hexText.isEmpty()) {
            resultLabel.setText("Error: Please enter a hexadecimal number.");
            return;
        }

        // Attempt to convert using BigInteger
        try {
            BigInteger decimalValue = new BigInteger(hexText, 16);
            resultLabel.setText("Decimal value: " + decimalValue.toString());
        } catch (NumberFormatException ex) {
            // Not a valid hexadecimal
            resultLabel.setText("Error: '" + hexText + "' is not a valid hex number.");
        }
    }

    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HexConverterGUI();
            }
        });
    }
}

