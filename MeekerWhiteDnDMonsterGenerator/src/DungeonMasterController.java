import java.util.Random;

/**
 * This class is the controller for the program. It will be called upon for creating and
 * randomizing monsters, as well as handling saving and loading monsters.
 * @author Ryan Meeker
 */
public class DungeonMasterController {
	private MonsterFrame frm;
	private Random rnd;
	private int choice;
	public DungeonMasterController(MonsterFrame frm) {
		this.frm = frm;
		rnd = new Random();
	}
	public Monster createAnyMonster() {
		choice = rnd.nextInt(3);
		try {
			if (choice == 0) {
				return new DragonTurtle();
			} else if (choice == 1) {
				return new Grick();
			} else {
				return new Kobold();
			}
		} catch (Exception ex) {
			System.out.println("Something went wrong reading the monster's image. Make sure no files are missing.");
			ex.printStackTrace();
			return null;
		}
	}
}
