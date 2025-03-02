import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Random;

public class GameOfLife {
    private static final int GRID_SIZE = 10; // Grid size (10x10)
    private static final Color ALIVE_COLOR = Color.BLACK;
    private static final Color DEAD_COLOR = Color.WHITE;
    
    private JFrame frame;
    private JPanel gridPanel;
    private JButton[][] buttons;
    private JButton stepButton, playButton;
    private JTextField speedInput;
    private Timer timer;
    
    private boolean[][] grid;
    
    public GameOfLife() {
        frame = new JFrame("Conway's Game of Life");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setLayout(new BorderLayout());

        grid = new boolean[GRID_SIZE][GRID_SIZE]; // Initialize the game grid

        // Create the grid panel
        gridPanel = new JPanel(new GridLayout(GRID_SIZE, GRID_SIZE));
        buttons = new JButton[GRID_SIZE][GRID_SIZE];

        // Populate the grid with buttons
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                buttons[row][col] = new JButton();
                buttons[row][col].setBackground(DEAD_COLOR);
                buttons[row][col].setOpaque(true);
                buttons[row][col].setBorderPainted(true);

                final int r = row, c = col;
                buttons[row][col].addActionListener(e -> toggleCell(r, c));

                gridPanel.add(buttons[row][col]);
            }
        }

        frame.add(gridPanel, BorderLayout.CENTER);

        // Control Panel with Step, Play, and Speed Input
        JPanel controlPanel = new JPanel();

        stepButton = new JButton("Step");
        stepButton.addActionListener(e -> advanceGeneration());
        controlPanel.add(stepButton);

        playButton = new JButton("Play");
        playButton.addActionListener(e -> togglePlay());
        controlPanel.add(playButton);

        speedInput = new JTextField("500", 5); // Default speed: 500ms per step
        controlPanel.add(new JLabel("Speed (ms):"));
        controlPanel.add(speedInput);

        frame.add(controlPanel, BorderLayout.SOUTH);

        frame.setSize(500, 500);
        frame.setVisible(true);
    }

    private void toggleCell(int row, int col) {
        grid[row][col] = !grid[row][col];
        buttons[row][col].setBackground(grid[row][col] ? ALIVE_COLOR : DEAD_COLOR);
    }

    private void advanceGeneration() {
        boolean[][] newGrid = new boolean[GRID_SIZE][GRID_SIZE];

        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                int neighbors = countAliveNeighbors(row, col);
                
                if (grid[row][col]) {
                    // Live cell stays alive if it has 2 or 3 neighbors
                    newGrid[row][col] = (neighbors == 2 || neighbors == 3);
                } else {
                    // Dead cell becomes alive if it has exactly 3 neighbors
                    newGrid[row][col] = (neighbors == 3);
                }
            }
        }

        // Update the grid and UI
        grid = newGrid;
        updateUI();
    }

    private int countAliveNeighbors(int row, int col) {
        int count = 0;
        int[] directions = {-1, 0, 1};

        for (int dr : directions) {
            for (int dc : directions) {
                if (dr == 0 && dc == 0) continue; // Skip the cell itself
                int newRow = row + dr;
                int newCol = col + dc;

                if (newRow >= 0 && newRow < GRID_SIZE && newCol >= 0 && newCol < GRID_SIZE) {
                    if (grid[newRow][newCol]) count++;
                }
            }
        }
        return count;
    }

    private void updateUI() {
        for (int row = 0; row < GRID_SIZE; row++) {
            for (int col = 0; col < GRID_SIZE; col++) {
                buttons[row][col].setBackground(grid[row][col] ? ALIVE_COLOR : DEAD_COLOR);
            }
        }
    }

    private void togglePlay() {
        if (timer == null || !timer.isRunning()) {
            int speed;
            try {
                speed = Integer.parseInt(speedInput.getText());
            } catch (NumberFormatException e) {
                speed = 500; // Default to 500ms if input is invalid
            }

            timer = new Timer(speed, e -> advanceGeneration());
            timer.start();
            playButton.setText("Stop");
        } else {
            timer.stop();
            playButton.setText("Play");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(GameOfLife::new);
    }
}

