package pthfndr.src.main;

public interface Name {
	public static final String[] stat = {"Strength","Dexterity","Constitution","Intelligence","Wisdom","Charisma"};
	public static final String[] statAbbrv = {"STR","DEX","CON","INT","WIS","CHA"};
	public static final String[] race = {"Human", "Dwarf", "Elf", "Gnome", "Half-Elf", "Half-Orc", "Halfling"};
	public static final String[] material = {"Glass", "Paper", "Cloth", "Rope", "Ice", "Leather", "Hide", "Wood", "Stone", "Iron", "Steel", "Mithral", "Adamantine","Copper","Silver", "Gold", "Platinum"};
	public static final String[] type = {"Aberration", "Animal", "Construct", "Dragon", "Fey", "Humanoid", "Magical Beast", "Monsterous Humanoid", "Ooze", "Outsider", "Plant", "Undead", "Vermin"};
	public static final String[] condition = {"Bleed", "Blinded", "Broken", "Confused", "Cowering", "Dazed", "Dazzled", "Dead", "Deafened", "Disabled", "Dying", "Energy Drained", "Entangled", "Exhausted", "Fascinated", "Fatigued", "Flat-footed", "Frightened", "Grappled", "Helpless", "Incorporeal", "Invisible", "Nauseated", "Panicked", "Paralyzed", "Petrified", "Pinned", "Prone", "Shaken", "Sickened", "Stable", "Staggered", "Stunned", "Unconscious", "Destroyed"};
	public static final String[] aura = {};
	public static final String[] DRTypes =  {"Piercing", "Bludgeoning", "Slashing", "Adamantine", "Silver", "Coldiron", "Magic", "Epic", "Good", "Evil", "Chaos", "Law"};
	
	static class Alignment {
		public static final String lawful = "Lawful", neutral = "Neutal", chaotic = "Chaotic", good = "Good", evil = "Evil";
	}
	static class Class {
		public static final String[] PC = { "Barbarian", "Bard",  "Cleric", "Druid", "Fighter", "Monk","Paladin", "Ranger", "Rogue","Wizard","Sorcerer"};
		public static final String[] NPC = { "Adept", "Aristocrat", "Commoner", "Expert", "Warrior" };
	}
	static class Magic {
		public static final String[] school = {"Abjuration", "Conjuration", "Divination", "Enchantment", "Illusion", "Necromancy", "Transmutation", "Universal"};
		static class subscool {
			public static final String[] conjuaration = {"", "Calling", "Creation", "Healing", "Summoning", "Teleportation"};
			public static final String[] divination = {"", "Scrying"};
			public static final String[] enchantment = {"", "Charm", "Compulsion"};
			public static final String[] illusion = {"", "Figment", "Glamer", "Pattern", "Phantasm", "Shadow"};
			public static final String[] transmuation = {"", "Polymorph"};
		}
		public static final String[] descriptor = {"Acid", "Air", "Chaotic", "Cold", "Darkness", "Death", "Earth", "Electricity", "Evil", "Fear", "Fire", "Force", "Good", "Language Dependent", "Lawful", "Light", "Mind-Affecting", "Sonic", "Water"};
	}
	
	public static String returnLowercase(String fix) {
		String value = "";
		char[] hold = new char[fix.length()];
		for (int i = 0; i < hold.length; i ++) {
			hold[i] = fix.charAt(i);
		}
		hold[0] = java.lang.Character.toLowerCase(hold[0]);
		for (int i = 0; i < hold.length; i ++) {
			value += hold[i];
		}
		return value;
	}
	
	static void main(String[] args) {
	
		for (int i = 0; i < 4; i ++)
		System.out.print(Roll.random(race) + " " + Roll.random(Class.PC) + "\n");
		System.out.print(returnLowercase("Test"));
		

	}

}
