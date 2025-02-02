import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.math.BigInteger;

public class HexConverterGUIList extends JFrame {

    private JTextArea inputTextArea;
    private JTextArea outputTextArea;
    private JButton convertButton;

    public HexConverterGUIList() {
        super("Hex to Decimal Converter (Multiple Inputs)");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null); // Center the window

        // Create a text area for hex input (multiple lines)
        inputTextArea = new JTextArea(8, 40);
        inputTextArea.setBorder(BorderFactory.createTitledBorder("Enter Hex Values (one per line)"));
        
        // Create a text area for decimal output (multiple lines)
        outputTextArea = new JTextArea(8, 40);
        outputTextArea.setEditable(false);
        outputTextArea.setBorder(BorderFactory.createTitledBorder("Decimal Outputs"));

        // Create a Convert button
        convertButton = new JButton("Convert");
        convertButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                convertHexInputs();
            }
        });

        // Layout: use a panel with a vertical BoxLayout
        JPanel panel = new JPanel();
        panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

        // Add components with some spacing
        panel.add(new JScrollPane(inputTextArea));
        panel.add(Box.createVerticalStrut(10));
        panel.add(convertButton);
        panel.add(Box.createVerticalStrut(10));
        panel.add(new JScrollPane(outputTextArea));

        add(panel);
        setVisible(true);
    }

    /**
     * Converts each line of hex input to its decimal equivalent.
     * If a line is not a valid hex value, an error message is shown.
     */
    private void convertHexInputs() {
        String input = inputTextArea.getText();
        // Split the input text by newlines (handle both Windows and Unix line breaks)
        String[] lines = input.split("\\r?\\n");
        StringBuilder output = new StringBuilder();

        for (String line : lines) {
            String hex = line.trim();
            // Skip empty lines
            if (hex.isEmpty()) {
                continue;
            }
            try {
                // Convert the hex string to a decimal BigInteger
                BigInteger decimalValue = new BigInteger(hex, 16);
                output.append(hex)
                      .append(" -> ")
                      .append(decimalValue.toString())
                      .append("\n");
            } catch (NumberFormatException ex) {
                output.append(hex)
                      .append(" -> Invalid hex input")
                      .append("\n");
            }
        }
        outputTextArea.setText(output.toString());
    }

    public static void main(String[] args) {
        // Ensure GUI is created on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new HexConverterGUIList();
            }
        });
    }
}

