import java.util.ArrayList;

public class Tile {

	public Tile(ArrayList<Creature> c, int water, int temperature, int nutrients) {
		this.water = water;
		this.temperature = temperature;
		this.nutrients = nutrients;
		this.creatures = c;
	}
	
	public void takeTurn() {
		for(Creature c : creatures) {
			c.takeTurn(this);
		}
	}
	
	private ArrayList<Creature> creatures;
	private int water;
	private int temperature;
	private int nutrients;
}
