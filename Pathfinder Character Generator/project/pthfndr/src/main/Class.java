package pthfndr.src.main;

public class Class {
	private String name;
	private int classID = -1;
	class PC {
		public final int BARBARIAN = 0, BARD = 1, CLERIC = 2, DRUID = 3, FIGHTER = 4, MONK = 5, PALADIN = 6, RANGER = 7, ROGUE = 8, WIZARD = 9, SCORCERER = 10;
		public final int[] hitDie = {12,8,8,8,10,8,10,10,8,6,6};
	
		public class Barbarian extends Class {
			
		}
		public class Bard extends Class {
			
		}
		public class Cleric extends Class {
			
		}
		public class Druid extends Class {
			
		}
		public class Fighter extends Class {
			
		}
		public class Monk extends Class {
			
		}
		public class Paladin extends Class {
			
		}
		public class Ranger extends Class {
			
		}
		public class Rogue extends Class {
			
		}
		public class Wizard extends Class {
			
		}
		public class Sorcerer extends Class {
			
		}
	}
	class NPC {
		public final int ADEPT = 0, ARISTOCRAT = 1, COMMONER = 2, EXPERT = 3, WARRIOR = 4;
		public final int[] hitDie = {6,8,6,8,10};
		
		public class Adept extends Class {
			
		}
		public class Aristocrat extends Class {
			
		}
		public class Commoner extends Class {
			
		}
		public class Expert extends Class {
			
		}
		public class Warrior extends Class {
			
		}
	}
}
