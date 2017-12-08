import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.PrintWriter;
import java.util.Random;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import org.json.simple.JSONObject;

@SuppressWarnings("unchecked")
/**
 * This class is the controller for the program. It will be called upon for creating and
 * randomizing monsters, as well as handling saving and loading monsters.
 * @author Ryan Meeker
 */
public class DungeonMasterController {
	private MonsterFrame frm;
	private Monster monster;
	private Random rnd;
	private int choice;
	private JFileChooser jfc;
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
	public void randomizeMonster(Monster monster) {
		this.monster = monster;
		if (monster.getType().equals("Dragon Turtle")) {
			randomizeStats(22,25,10,20,10,12,12);
		} else if (monster.getType().equals("Grick")) {
			randomizeStats(6,14,14,11,3,14,5);
		} else if (monster.getType().equals("Kobold")) {
			randomizeStats(2,7,15,9,8,7,8);
		}
	}
	private void randomizeStats(int avgHitPoints, int avgStr, int avgDex, int avgCon, int avgInt, int avgWis, int avgCha) {
		int lowHitPoints = avgHitPoints - 2;
		int lowStr = avgStr - 2;
		int lowDex = avgDex - 2;
		int lowCon = avgCon - 2;
		int lowInt = avgInt - 2;
		int lowWis = avgWis - 2;
		int lowCha = avgCha - 2;
		monster.hitPoints.setNumOfHitDice(lowHitPoints + rnd.nextInt(5)); 
		monster.abilityScores.setAbilityScore("str", lowStr + rnd.nextInt(5));
		monster.abilityScores.setAbilityScore("dex", lowDex + rnd.nextInt(5));
		monster.abilityScores.setAbilityScore("con", lowCon + rnd.nextInt(5));
		monster.abilityScores.setAbilityScore("int", lowInt + rnd.nextInt(5));
		monster.abilityScores.setAbilityScore("wis", lowWis + rnd.nextInt(5));
		monster.abilityScores.setAbilityScore("cha", lowCha + rnd.nextInt(5));
	}
	public void saveMonsterToBinary(Monster monster) {
		jfc = new JFileChooser();
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectOutputStream oos = new ObjectOutputStream(new BufferedOutputStream(new FileOutputStream(jfc.getSelectedFile())));
				oos.writeObject(monster);
				oos.close();
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "The monster could not be saved to the file.");
				ex.printStackTrace();
			}
		}
	}
	public Monster loadMonsterFromBinary() {
		jfc = new JFileChooser();
		if (jfc.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
			try {
				ObjectInputStream ois = new ObjectInputStream(new BufferedInputStream(new FileInputStream(jfc.getSelectedFile())));
				monster = (Monster)ois.readObject();
				monster.setPicture();
				ois.close();
				return monster;
			} catch (Exception ex) {
				JOptionPane.showMessageDialog(null, "There was an issue while reading the file.");
				ex.printStackTrace();
				return createAnyMonster();
			}
		}
		return monster;
	}
	public void saveMonsterToJSON(Monster monster) {
		jfc = new JFileChooser();
		if (jfc.showSaveDialog(null) == JFileChooser.APPROVE_OPTION) {
			JSONObject jobj = new JSONObject();
			jobj.put("type", monster.getType());
			jobj.put("armorClass", monster.armorClass.getArmorClass());
			jobj.put("hitPoints", monster.hitPoints.getAvgHitPoints());
			jobj.put("speed", monster.getSpeed());
			jobj.put("abilityScores", monster.abilityScores.toString());
			jobj.put("senses", monster.senses.toString());
			jobj.put("languages", monster.languages.toString());
			jobj.put("challengeRating", monster.getChallengeRating());
			jobj.put("abilities", monster.abilities.toString());
			jobj.put("actions", monster.actions.toString());
			jobj.put("picFileName", monster.getPicFileName());
			try {
				PrintWriter pw = new PrintWriter(new BufferedWriter(new FileWriter(jfc.getSelectedFile())));
				pw.println(jobj.toJSONString());
				pw.close();
			} catch (Exception ex) {
				System.out.println("The file could not be saved. All hope is lost");
				ex.printStackTrace();
			}
		}
	}
}