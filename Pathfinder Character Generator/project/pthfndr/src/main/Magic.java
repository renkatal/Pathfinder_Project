package pthfndr.src.main;

public interface Magic {
	public interface School {
		public final int ABJURATION = 0, CONJURATION = 1, DIVINATION = 2, ENCHANTMENT = 3, ILLUSION = 4, NECROMANCY = 5, TRANSMUTATION = 6, UNIVERSAL = 7;
		public interface Subschool {
			public final int NONE = 0;
			//conjuration subschools
			public final int CALLING = 1, CREATION = 2, HEALING = 3, SUMMONING = 4, TELEPORTATION = 5;
			//divination subschool
			public final int SCRYING = 1;
			//enchantment subschools
			public final int CHARM = 1, COMPULSION = 2;
			//illusion subschools
			public final int FIGMENT = 1, GLAMER = 2, PATTERN = 3, PHANTASM = 4, SHADOW = 5;
			//transmutation subschool
			public final int POLYMORPH = 1;
		}
	}
	public interface Desciptor {
		public final byte ACID = 0, AIR = 1, CHAOTIC = 2, COLD = 3, DARKNESS = 4, DEATH = 5, EARTH = 6, ELECTRICITY = 7, EVIL = 8, FEAR = 9, FIRE = 10, FORCE = 11, GOOD = 12, LANGUAGE_DEPENDENT = 13, LAWFUL = 14, LIGHT = 15, MIND_AFFECTING = 16, SONIC = 17, WATER = 18;
	}

}
