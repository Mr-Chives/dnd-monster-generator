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
	public ArmorClass armorClass;
	public HitPoints hitPoints;
	private int speed;
	public AbilityScores abilityScores;
	private Senses senses;
	private String[] languages;
	public Monster() {
		abilityScores = new AbilityScores();
		armorClass = new ArmorClass(abilityScores);
		armorClass.setNatArmor(false);
		hitPoints = new HitPoints(abilityScores);
		speed = 30;
		senses = new Senses(abilityScores);
		languages = null;
	}
	public Monster(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) {
		abilityScores = new AbilityScores(asStr, asDex, asCon, asInt, asWis, asCha);
		armorClass = new ArmorClass(abilityScores);
		armorClass.setNatArmor(false);
		hitPoints = new HitPoints(abilityScores);
		speed = 30;
		senses = new Senses(abilityScores);
		languages = null;
	}
	public void setSpeed(int speed) {
		if (speed < 0) {
			speed = 0;
		}
		this.speed = speed;
	}
	public int getSpeed() {
		return speed;
	}
	public void setLanguages(String[] languages) {
		this.languages = languages;
	}
	public String[] getLanguages() {
		return languages;
	}
}