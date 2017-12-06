/**
 * StatObject is a class that allows a monster's stat to include an associated
 * value, as well as additional info, such as hit dice, lists of actions, ability score
 * modifiers, etc.
 * @author Ryan
 */
class StatObject {
	
}

class HitPoints {
	private int avgHitPoints;
	private int hitDie;
	private int numOfHitDice;
	private AbilityScores as;
	public HitPoints(AbilityScores as) {
		this.as = as;
		hitDie = 4;
		numOfHitDice = 1;
		setAvgHitPoints();
	}
	/**
	 * Calculates the average amount of hit points a monster will have, based on the hit die type, number
	 * of hit dice, and its Constitution modifier. This function is often used to automatically update the
	 * change in one of these variables to reflect the new proper average.
	 */
	private void setAvgHitPoints() {
		avgHitPoints = (int)(numOfHitDice * ((hitDie/2) + (0.5)) + (as.getMod("con") * numOfHitDice));
	}
	public int getAvgHitPoints() {
		return avgHitPoints;
	}
	public void setHitDie(int hitDie) {
		if (hitDie != 4 && hitDie != 6 && hitDie != 8 && hitDie != 10 && hitDie != 12 && hitDie != 20) {
			hitDie = 8;
		}
		this.hitDie = hitDie;
		setAvgHitPoints();
	}
	public int getHitDie() {
		return hitDie;
	}
	public void setNumOfHitDice(int numOfHitDice) {
		if (numOfHitDice < 1) {
			numOfHitDice = 1;
		}
		this.numOfHitDice = numOfHitDice;
	}
	public int getNumOfHitDice() {
		return numOfHitDice;
	}
	/*
	public void setAbilityScores(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) {
		if (asStr < 1) {
			as.setAbilityScore("str", 10);
		} else {
			as.setAbilityScore("str", asStr);
		}
		if (asDex < 1) {
			as.setAbilityScore("dex", 10);
		} else {
			as.setAbilityScore("dex", asDex);
		}
		if (asCon < 1) {
			as.setAbilityScore("con", 10);
		} else {
			as.setAbilityScore("con", asCon);
		}
		if (asInt < 1) {
			as.setAbilityScore("int", 10);
		} else {
			as.setAbilityScore("int", asInt);
		}
		if (asWis < 1) {
			as.setAbilityScore("wis", 10);
		} else {
			as.setAbilityScore("wis", asWis);
		}
		if (asCha < 1) {
			as.setAbilityScore("cha", 10);
		} else {
			as.setAbilityScore("cha", asCha);
		}
	}
	public AbilityScores getAbilityScores() {
		return as;
	}*/
}

class ArmorClass {
	private AbilityScores as;
	private int ac;
	private boolean natArmor; //If true, armor class will be determined by fixed number.
	private int natArmorVal;
	public ArmorClass(AbilityScores as) {
		this.as = as;
		natArmor = false;
		natArmorVal = 0;
		ac = 0;
		setArmorClass();
	}
	public void setArmorClass() {
		ac = 10 + as.getMod("dex");
		if (hasNatArmor() == true) {
			ac = ac + natArmorVal;
		}
	}
	public int getArmorClass() {
		return ac;
	}
	public void setNatArmor(boolean natArmor) {
		this.natArmor = natArmor;
		setArmorClass();
	}
	public boolean hasNatArmor() {
		return natArmor;
	}
	public void setNatArmorVal(int natArmorVal) {
		this.natArmorVal = natArmorVal;
	}
	public int getNatArmorVal() {
		return natArmorVal;
	}
}

class Abilities {
	private String[] abilities;
	public Abilities() {
		abilities = null;
	}
	public Abilities(String[] abilities) {
		setAbilities(abilities);
	}
	public void setAbilities(String[] abilities) {
		this.abilities = abilities;
	}
	public String[] getAbilities() {
		return abilities;
	}
	@Override
	public String toString() {
		if (abilities.equals(null)) {
			return "None";
		} else {
			String result = "";
			for (int i = 0; i < abilities.length - 1; i++) {
				result = result + String.format("%s, ", abilities[i]);
			}
			result = result + abilities[abilities.length-1];
			return result;
		}
	}
}

/**
 * This class is dedicated to holding a full set of ability scores for a monster, as
 * well as its corresponding modifiers.
 * @author Ryan
 */
class AbilityScores {
	private int asStr, asDex, asCon, asInt, asWis, asCha;
	private int modStr, modDex, modCon, modInt, modWis, modCha;
	/**
	 * Default constructor sets all ability scores to 10, with a corresponding modifier
	 * of 0 for each.
	 */
	public AbilityScores() {
		setAbilityScore("str",10);
		setAbilityScore("dex",10);
		setAbilityScore("con",10);
		setAbilityScore("int",10);
		setAbilityScore("wis",10);
		setAbilityScore("cha",10);
	}
	/**
	 * This constructor takes in a full set of ability score values and initializes to them.
	 * The order should be str, dex, con, int, wis, and cha.
	 * @param asStr
	 * @param asDex
	 * @param asCon
	 * @param asInt
	 * @param asWis
	 * @param asCha
	 */
	public AbilityScores(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) {
		setAbilityScore("str",asStr);
		setAbilityScore("dex",asDex);
		setAbilityScore("con",asCon);
		setAbilityScore("int",asInt);
		setAbilityScore("wis",asWis);
		setAbilityScore("cha",asCha);
	}
	
	/**
	 * This function allows an individual ability score to be set. When this is done, the
	 * ability score's modifier is automagically recalculated.
	 * @param as Ability score to be changed. Acceptable values are str, dex, con, int, wis, cha
	 * @param num Ability score value. Acceptable range is 1-30. Any other value will be set to 10.
	 */
	public void setAbilityScore(String as, int num) {
		if (!(num >= 1) && !(num <= 30)) {
			num = 10;
		}
		if (as.equals("str")) {
			asStr = num;
			setMod("str",num);
		} else if (as.equals("dex")) {
			asDex = num;
			setMod("dex",num);
		} else if (as.equals("con")) {
			asCon = num;
			setMod("con",num);
		} else if (as.equals("int")) {
			asInt = num;
			setMod("int",num);
		} else if (as.equals("wis")) {
			asWis = num;
			setMod("wis",num);
		} else if (as.equals("cha")) {
			asCha = num;
			setMod("cha",num);
		}
	}
	/**
	 * This function returns a specified ability score.
	 * @param as Ability score to be returned. Acceptable values are str, dex, con, int, wis, cha
	 * @return ability score value
	 */
	public int getAbilityScore(String as) {
		if (as.equals("str")) {
			return asStr;
		} else if (as.equals("dex")) {
			return asDex;
		} else if (as.equals("con")) {
			return asCon;
		} else if (as.equals("int")) {
			return asInt;
		} else if (as.equals("wis")) {
			return asWis;
		} else if (as.equals("cha")) {
			return asCha;
		} else {
			return 0;
		}
	}
	
	public void setAbilityScores(int asStr, int asDex, int asCon, int asInt, int asWis, int asCha) {
		setAbilityScore("str",asStr);
		setAbilityScore("dex",asDex);
		setAbilityScore("con",asCon);
		setAbilityScore("int",asInt);
		setAbilityScore("wis",asWis);
		setAbilityScore("cha",asCha);
	}
	/**
	 * This private function will convert the specified ability score into its
	 * proper modifier. 
	 * @param as Ability score to be converted. Acceptable values are str, dex, con, int, wis, cha
	 * @param num Ability score value. This will already be in the acceptable 1-30 range.
	 */
	private void setMod(String as, int num) {
		int mod = 0;
		if (num == 1) {
			mod = -5;
		} else if (num == 2 || num == 3) {
			mod = -4;
		} else if (num == 4 || num == 5) {
			mod = -3;
		} else if (num == 6 || num == 7) {
			mod = -2;
		} else if (num == 8 || num == 9) {
			mod = -1;
		} else if (num == 10 || num == 11) {
			mod = 0;
		} else if (num == 12 || num == 13) {
			mod = 1;
		} else if (num == 14 || num == 15) {
			mod = 2;
		} else if (num == 16 || num == 17) {
			mod = 3;
		} else if (num == 18 || num == 19) {
			mod = 4;
		} else if (num == 20 || num == 21) {
			mod = 5;
		} else if (num == 22 || num == 23) {
			mod = 6;
		} else if (num == 24 || num == 25) {
			mod = 7;
		} else if (num == 26 || num == 27) {
			mod = 8;
		} else if (num == 28 || num == 29) {
			mod = 9;
		} else if (num == 30) {
			mod = 10;
		}
		if (as.equals("str")) {
			modStr = mod;
		} else if (as.equals("dex")) {
			modDex = mod;
		} else if (as.equals("con")) {
			modCon = mod;
		} else if (as.equals("int")) {
			modInt = mod;
		} else if (as.equals("wis")) {
			modWis = mod;
		} else if (as.equals("cha")) {
			modCha = mod;
		}
	}
	/**
	 * This function returns a specified ability score's modifier.
	 * @param as Ability score modifier to be returned. Acceptable values are str, dex, con, int, wis, cha
	 * @return ability score modifier
	 */
	public int getMod(String as) {
		if (as.equals("str")) {
			return modStr;
		} else if (as.equals("dex")) {
			return modDex;
		} else if (as.equals("con")) {
			return modCon;
		} else if (as.equals("int")) {
			return modInt;
		} else if (as.equals("wis")) {
			return modWis;
		} else if (as.equals("cha")) {
			return modCha;
		} else {
			return 0;
		}
	}
}

class Actions {
	private String[] actions;
	public Actions() {
		actions = null;
	}
	public Actions(String[] actions) {
		setActions(actions);
	}
	public void setActions(String[] actions) {
		this.actions = actions;
	}
	public String[] getActions() {
		return actions;
	}
	@Override
	public String toString() {
		if (actions.equals(null)) {
			return "None";
		} else {
			String result = "";
			for (int i = 0; i < actions.length; i++) {
				result = result + String.format("%s\n\n", actions[i]);
			}
			return result;
		}
	}
}

class Languages {
	private String[] langs;
	public Languages() {
		langs = new String[1];
		langs[0] = "None";
	}
	public Languages(String[] langs) {
		this.langs = new String[langs.length];
		setLanguages(langs);
	}
	public void setLanguages(String[] langs) {
		if (langs[0].equals("None")) {
			this.langs[0] = "None";
		} else {
			this.langs = langs;
		}
	}
	public String[] getLanguages() {
		return langs;
	}
	@Override
	public String toString() {
		if (langs[0].equals("None")) {
			return "None";
		} else {
			String result = "";
			for (int i = 0; i < langs.length - 1; i++) {
				result = result + String.format("%s, ", langs[i]);
			}
			result = result + langs[langs.length-1];
			return result;
		}
	}
}

class Senses {
	private AbilityScores as;
	private int passivePerception;
	private String[] senses;
	public Senses(AbilityScores as) {
		this.as = as;
		senses = null;
		setPassivePerception();
	}
	public Senses(AbilityScores as, String[] senses) {
		this.as = as;
		setSenses(senses);
		setPassivePerception();
	}
	public void setPassivePerception() {
		passivePerception = 10 + as.getMod("wis");
	}
	public int getPassivePerception() {
		return passivePerception;
	}
	public void setSenses(String[] senses) {
		this.senses = senses;
	}
	public String[] getSenses() {
		return senses;
	}
	@Override
	public String toString() {
		if (senses.equals(null)) {
			return "None";
		} else {
			String result = "";
			for (int i = 0; i < senses.length - 1; i++) {
				result = result + String.format("%s, ", senses[i]);
			}
			result = result + senses[senses.length-1];
			return result;
		}
	}
}