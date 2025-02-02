public class Creature {
    private String name;
    private int health;

    // Constructor
    public Creature(String name, int health) {
        this.name = name;
        this.health = health;
    }

    // Method for the creature to speak
    public void speak() {
        System.out.println("Hello, my name is " + name +
                           " and I have " + health + " health!");
    }

    // Method for the creature to take damage
    public void takeDamage(int damage) {
        health -= damage;
        if (health < 0) {
            health = 0; // Ensure health doesn't go below zero
        }
        System.out.println(name + " took " + damage + " damage. Remaining health: " + health);
    }

    // Method for the creature to regain health by eating
    public void eat(int healthGained) {
        health += healthGained;
        System.out.println(name + " ate and gained " + healthGained + " health. Current health: " + health);
    }

    // Check if the creature is still alive
    public boolean isAlive() {
        return health > 0;
    }

    // Getters (optional, for external access if needed)
    public String getName() {
        return name;
    }

    public int getHealth() {
        return health;
    }

    // A demonstration in the main method
    public static void main(String[] args) {
        // Create two creatures
        Creature dragon = new Creature("Fire Drake", 100);
        Creature slime = new Creature("Green Slime", 20);

        // Let them speak
        dragon.speak();
        slime.speak();

        // Cause some damage
        slime.takeDamage(5);
        dragon.takeDamage(120);

        // Have the creatures eat to regain health
        dragon.eat(50);
        slime.eat(10);

        // Check if they are alive
        System.out.println(dragon.getName() + " alive? " + dragon.isAlive() + " health: " + dragon.getHealth() );
        System.out.println(slime.getName() + " alive? " + slime.isAlive() + " health: " + slime.getHealth());
    }
}

