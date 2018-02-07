package pthfndr.src.main;

public class Name {
	public static final String[] stat = {"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};
	public static final String[] statAbbrv = {"STR","DEX","CON","INT","WIS","CHA"};
	public static final String[] race = {"Human", "Dwarf", "Elf", "Gnome", "Half-Elf", "Half-Orc", "Halfling"};
	
	public static final String[] material = {"Glass", "Paper", "Cloth", "Rope", "Ice", "Leather", "Hide", "Wood", "Stone", "Iron", "Steel", "Mithral", "Adamantine"};
	public static final String[] type = {"Aberration", "Animal", "CONSTRUCT", "Dragon", "Fey", "Humanoid", "Magical Beast", "Monsterous Humanoid", "Ooze", "Outsider", "Plant", "Undead", "Vermin"};
	static class Alignment {
		public static final String lawful = "Lawful", neutral = "Neutal", chaotic = "Chaotic", good = "Good", evil = "Evil";
	}
	static class Class {
		public static final String[] PC = { "Barbarian", "Bard",  "Cleric", "Druid", "Fighter", "Monk","Paladin", "Ranger", "Rogue","Wizard","Sorcerer"};
		public static final String[] NPC = { "Adept", "Aristocrat", "Commoner", "Expert", "Warrior" };
	}
	public static void main(String[] args) {
	
		for (int i = 0; i < 4; i ++)
		System.out.print(Roll.random(race) + " " + Roll.random(Class.PC) + "\n");

	}

}
