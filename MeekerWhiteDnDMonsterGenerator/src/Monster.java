/*
 * Monsters to add:
 * Dragon Turtle
 * Grick
 * Kobold
 */

/**
 * This is an generic Monster class. All specific monsters will extend from this class.
 * @author Ryan Meeker
 */
public class Monster {
	/*
	 * Each monster will have AC, HP (and hit dice), speed,
	 * STR, DEX, CON, INT, WIS, CHA,
	 * Senses, Languages, Challenge rating,
	 * Abilities, and Actions
	 */
	// as = ability score
	private int armorClass;
	private int hitPoints;
	private int speed;
	private AbilityScores as;
	private boolean natArmor; //If true, armor class will be determined by fixed number.
	public Monster() {
		natArmor = false;
		as = new AbilityScores();
		armorClass = 10 + as.getMod("dex");
		speed = 0;
	}
}
