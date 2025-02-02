import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CreatureCSVReader {
    
    // Define the Creature class
    static class Creature {
        private String name;
        private int health;
        
        public Creature(String name, int health) {
            this.name = name;
            this.health = health;
        }
        
        public void speak() {
            System.out.println("Hello, my name is " + name +
                               " and I have " + health + " health!");
        }
    }

    public static void main(String[] args) {
        
        // Check that a filename was provided
        if (args.length < 1) {
            System.out.println("Usage: java CreatureCSVReader <filename.csv>");
            System.exit(1);
        }
        
        String filename = args[0];
        
        // Use a List to read in all creatures dynamically
        List<Creature> creatureList = new ArrayList<>();
        
        // Read the CSV file line by line
        try (Scanner scanner = new Scanner(new File(filename))) {
            while (scanner.hasNextLine()) {
                String line = scanner.nextLine().trim();
                
                // Skip blank lines
                if (line.isEmpty()) {
                    continue;
                }
                
                // Split on comma
                String[] parts = line.split(",");
                if (parts.length != 2) {
                    // If the line doesn't have exactly 2 parts, skip or handle error
                    System.out.println("Skipping malformed line: " + line);
                    continue;
                }
                
                // Parse name and health
                String name = parts[0].trim();
                int health;
                try {
                    health = Integer.parseInt(parts[1].trim());
                } catch (NumberFormatException e) {
                    System.out.println("Skipping line with invalid health value: " + line);
                    continue;
                }
                
                // Create a new Creature and add it to the list
                creatureList.add(new Creature(name, health));
            }
        } catch (FileNotFoundException e) {
            System.err.println("File not found: " + filename);
            System.exit(2);
        }
        
        // Convert the List to an array
        Creature[] creatures = creatureList.toArray(new Creature[0]);
        
        // Print out each creature
        System.out.println("Creatures in the array:");
        for (Creature c : creatures) {
            c.speak();
        }
    }
}

