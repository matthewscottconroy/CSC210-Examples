import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.math.BigInteger;

public class HexFileConverterGUI extends JFrame {

    private JButton chooseInputButton;
    private JButton convertAndSaveButton;
    private JLabel inputFileLabel;
    private JTextArea statusArea;
    private File inputFile;

    public HexFileConverterGUI() {
        super("Hex File Converter");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 300);
        setLocationRelativeTo(null); // Center on screen

        // Create components
        chooseInputButton = new JButton("Choose Input File");
        convertAndSaveButton = new JButton("Convert and Save Output File");
        inputFileLabel = new JLabel("No input file selected");
        statusArea = new JTextArea(8, 50);
        statusArea.setEditable(false);
        statusArea.setBorder(BorderFactory.createTitledBorder("Status"));

        // Set layout and add components
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new FlowLayout());
        topPanel.add(chooseInputButton);
        topPanel.add(inputFileLabel);

        JPanel centerPanel = new JPanel();
        centerPanel.add(convertAndSaveButton);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout());
        mainPanel.add(topPanel, BorderLayout.NORTH);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        mainPanel.add(new JScrollPane(statusArea), BorderLayout.SOUTH);

        add(mainPanel);

        // Action listener for choosing the input file
        chooseInputButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                chooseInputFile();
            }
        });

        // Action listener for converting and saving the output file
        convertAndSaveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (inputFile == null) {
                    JOptionPane.showMessageDialog(HexFileConverterGUI.this,
                            "Please choose an input file first.",
                            "No Input File",
                            JOptionPane.ERROR_MESSAGE);
                } else {
                    chooseOutputAndConvert();
                }
            }
        });

        setVisible(true);
    }

    /**
     * Opens a file chooser to let the user select an input file.
     */
    private void chooseInputFile() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showOpenDialog(HexFileConverterGUI.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            inputFile = fileChooser.getSelectedFile();
            inputFileLabel.setText("Input: " + inputFile.getName());
            statusArea.append("Selected input file: " + inputFile.getAbsolutePath() + "\n");
        }
    }

    /**
     * Opens a file chooser to let the user choose an output file and then converts the input file.
     */
    private void chooseOutputAndConvert() {
        JFileChooser fileChooser = new JFileChooser();
        int result = fileChooser.showSaveDialog(HexFileConverterGUI.this);
        if (result == JFileChooser.APPROVE_OPTION) {
            File outputFile = fileChooser.getSelectedFile();
            statusArea.append("Output file selected: " + outputFile.getAbsolutePath() + "\n");
            convertFile(inputFile, outputFile);
        }
    }

    /**
     * Reads the input file line by line, converts each hex value to decimal (if valid),
     * and writes the results to the output file.
     */
    private void convertFile(File inputFile, File outputFile) {
        try (
            BufferedReader reader = new BufferedReader(new FileReader(inputFile));
            BufferedWriter writer = new BufferedWriter(new FileWriter(outputFile))
        ) {
            String line;
            int lineNumber = 1;
            while ((line = reader.readLine()) != null) {
                String hex = line.trim();
                if (hex.isEmpty()) {
                    // Optionally skip or write an empty line
                    writer.newLine();
                } else {
                    String outputLine;
                    try {
                        BigInteger decimalValue = new BigInteger(hex, 16);
                        outputLine = hex + " -> " + decimalValue.toString();
                    } catch (NumberFormatException ex) {
                        outputLine = hex + " -> Invalid hex input";
                    }
                    writer.write(outputLine);
                    writer.newLine();
                    statusArea.append("Processed line " + lineNumber + "\n");
                }
                lineNumber++;
            }
            statusArea.append("Conversion complete! Output saved to " + outputFile.getAbsolutePath() + "\n");
        } catch (IOException ex) {
            JOptionPane.showMessageDialog(HexFileConverterGUI.this,
                    "Error processing files: " + ex.getMessage(),
                    "File Error",
                    JOptionPane.ERROR_MESSAGE);
            statusArea.append("Error: " + ex.getMessage() + "\n");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new HexFileConverterGUI());
    }
}

