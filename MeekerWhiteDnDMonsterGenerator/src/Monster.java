import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

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
public abstract class Monster implements Serializable {
	/*
	 * Each monster will have AC, HP (and hit dice), speed,
	 * STR, DEX, CON, INT, WIS, CHA,
	 * Senses, Languages, Challenge rating,
	 * Abilities, Actions, and a picture
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
	public transient BufferedImage picture;
	public String picFileName;
	public Monster() throws IOException {
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
		picFileName = null;
		picture = null;
		setPicture();
	}
	public Monster(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) throws IOException {
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
		picFileName = null;
		picture = null;
		setPicture();
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
	public void setPicture() throws IOException {
		if (picFileName == null) {
			picture = ImageIO.read(new File(".\\beholder.jpg"));
		}
		else {
			picture = ImageIO.read(new File(picFileName));
		}
	}
	public abstract BufferedImage getPicture();
	public String getPicFileName() {
		return picFileName;
	};
	public abstract String getType();
}

class DragonTurtle extends Monster {
	public DragonTurtle() throws IOException {
		super(25,10,20,10,12,12);
		super.picFileName = ".\\dragonturtle.jpg";
		super.setPicture();
		super.hitPoints.setHitDie(20);
		super.hitPoints.setNumOfHitDice(22);
		super.armorClass.setNatArmorVal(10);
		super.armorClass.setNatArmor(true);
		super.setSpeed(20);
		String[] senses = new String[1];
		senses[0] = "Darkvision";
		String[] langs = new String[2];
		langs[0] = "Aquan";
		langs[1] = "Draconic";
		String[] abilities = new String[1];
		abilities[0] = "Amphibious";
		String[] actions = new String[5];
		actions[0] = "Multiattack. The dragon turtle makes three attacks: one with its bite and two with its claws. It can make one tail attack in place of its two claw attacks.";
		actions[1] = "Bite. Melee Weapon Attack: +13 to hit, reach 15 ft., one target. Hit: 26 (3d12 + 7) piercing damage.";
		actions[2] = "Claw. Melee Weapon Attack: +13 to hit, reach 10 ft., one target. Hit: 16 (2d8 + 7) slashing damage.";
		actions[3] = "Tail. Melee Weapon Attack: +13 to hit, reach 15 ft., one target. Hit: 26 (3d12 + 7) bludgeoning damage. If the target is a creature, it must succeed on a DC 20 Strength saving throw or be pushed up to 10 feet away from the dragon turtle and knocked prone.";
		actions[4] = "Steam Breath (Recharge 5-6). The dragon turtle exhales scalding steam in a 60-foot cone. Each creature in that area must make a DC 18 Constitution saving throw, taking 52 (15d6) fire damage on a failed save, or half as much damage on a successful one. Being underwater doesn't grant resistance against this damage.";
		
		super.senses.setSenses(senses);
		super.languages.setLanguages(langs);
		super.setChallengeRating("17");
		super.abilities.setAbilities(abilities);
		super.actions.setActions(actions);
	}
	public BufferedImage getPicture() {
		return picture;
	}
	public String getType() {
		return "Dragon Turtle";
	}
}

class Grick extends Monster {
	public Grick() throws IOException {
		super(14,14,11,3,14,5);
		super.picFileName = ".\\grick.jpg";
		super.setPicture();
		super.armorClass.setNatArmorVal(2);
		super.armorClass.setNatArmor(true);
		super.hitPoints.setHitDie(8);
		super.hitPoints.setNumOfHitDice(6);
		super.setSpeed(30);
		String[] senses = new String[1];
		senses[0] = "Darkvision";
		String[] langs = new String[1];
		langs[0] = "None";
		String[] abilities = new String[1];
		abilities[0] = "Stone Camouflage";
		String[] actions = new String[3];
		actions[0] = "Multiattack. The grick makes one attack with its tentacles. If that attack hits, the grick can make one beak attack against the same target.";
		actions[1] = "Tentacles. Melee Weapon Attack: +4 to hit, reach 5 ft., one target. Hit: 9 (2d6 + 2) slashing damage.";
		actions[2] = "Beak. Melee Weapon Attack: +4 to hit, reach 5 ft., one target. Hit: 5 (1d6 + 2) piercing damage.";
		
		super.senses.setSenses(senses);
		super.languages.setLanguages(langs);
		super.setChallengeRating("2");
		super.abilities.setAbilities(abilities);
		super.actions.setActions(actions);
	}
	public BufferedImage getPicture() {
		return picture;
	}
	public String getType() {
		return "Grick";
	}
}

class Kobold extends Monster {
	public Kobold() throws IOException {
		super(7,15,9,8,7,8);
		super.picFileName = ".\\kobold.jpg";
		super.setPicture();
		super.hitPoints.setHitDie(6);
		super.hitPoints.setNumOfHitDice(2);
		super.setSpeed(30);
		String[] senses = new String[1];
		senses[0] = "Darkvision";
		String[] langs = new String[2];
		langs[0] = "Common";
		langs[1] = "Draconic";
		String[] abilities = new String[2];
		abilities[0] = "Sunlight Sensitivity";
		abilities[1] = "Pack Tactics";
		String[] actions = new String[2];
		actions[0] = "Dagger. Melee Weapon Attack: +4 to hit, reach 5 ft., one target. Hit: 4 (1d4 + 2) piercing damage.";
		actions[1] = "Sling. Ranged Weapon Attack: +4 to hit, range 30/120 ft., one target. Hit: 4 (1d4 + 2) bludgeoning damage.";
		
		super.senses.setSenses(senses);
		super.languages.setLanguages(langs);
		super.setChallengeRating("1/8");
		super.abilities.setAbilities(abilities);
		super.actions.setActions(actions);
	}
	public BufferedImage getPicture() {
		return picture;
	}
	public String getType() {
		return "Kobold";
	}
}