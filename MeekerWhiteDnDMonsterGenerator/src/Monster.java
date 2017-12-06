import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;

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
public abstract class Monster {
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
	public Senses senses;
	public Languages languages;
	private String challengeRating;
	public Abilities abilities;
	public Actions actions;
	private BufferedImage picture;
	public Monster() {
		abilityScores = new AbilityScores();
		armorClass = new ArmorClass(abilityScores);
		armorClass.setNatArmor(false);
		hitPoints = new HitPoints(abilityScores);
		speed = 30;
		senses = new Senses(abilityScores);
		languages = new Languages();
		challengeRating = "1/4";
		abilities = new Abilities();
		actions = new Actions();
		picture = null;
	}
	public Monster(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) {
		abilityScores = new AbilityScores(asStr, asDex, asCon, asInt, asWis, asCha);
		armorClass = new ArmorClass(abilityScores);
		armorClass.setNatArmor(false);
		hitPoints = new HitPoints(abilityScores);
		speed = 30;
		senses = new Senses(abilityScores);
		languages = new Languages();
		challengeRating = "1/4";
		abilities = new Abilities();
		actions = new Actions();
		picture = null;
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
	public void setChallengeRating(String challengeRating) {
		this.challengeRating = challengeRating;
	}
	public String getChallengeRating() {
		return challengeRating;
	}
	public abstract BufferedImage getPicture();
	public abstract String getType();
}

class DragonTurtle extends Monster {
	private BufferedImage picture;
	private int natArmorVal;
	public DragonTurtle() throws IOException {
		super(25,10,20,10,12,12);
		picture = ImageIO.read(new File(".\\dragonturtle.jpg"));
		natArmorVal = 10;
		super.armorClass.setNatArmorVal(natArmorVal);
		super.armorClass.setNatArmor(true);
		super.setSpeed(20);
		String[] senses = new String[1];
		senses[0] = "Darkvision";
		String[] langs = new String[2];
		langs[0] = "Aquan";
		langs[1] = "Draconic";
		String[] abilities = new String[1];
		abilities[0] = "Amphibious";
		
		super.senses.setSenses(senses);
		super.languages.setLanguages(langs);
		super.setChallengeRating("17");
		super.abilities.setAbilities(abilities);
	}
	public BufferedImage getPicture() {
		return picture;
	}
	public String getType() {
		return "Dragon Turtle";
	}
}