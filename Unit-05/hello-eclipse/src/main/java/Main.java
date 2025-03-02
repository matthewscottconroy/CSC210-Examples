import java.util.ArrayList;

public class Main {

	public static void main(String[] args) {
		System.out.println("Program started.");
		Bird b = new Bird("Big Bird", 100);
		Plant p = new Tree("Deku Tree", 500);
		
		ArrayList<Creature> population = new ArrayList<Creature>();
		population.add(b);
		population.add(p);
		
		Tile t = new Tile(population, 100, 100, 100);
		t.takeTurn();
		
		System.out.println("Program completed.");
	}
	
}
