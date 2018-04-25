package pthfndr.src.main;

import java.util.ArrayList;

import pthfndr.src.main.Class.PC.*;
import pthfndr.src.main.Special.ExtraordinaryAblitiy.DamageReduction;

public class Class implements Special.List{
	private String name;
	private int classID = -1;
	private ArrayList<Special> special;
	private int level;
	
	// variable for equals() referencing
	public static Barbarian barbarian = new Barbarian();
	public static Bard bard = new Bard();
	
	public void setName(int classID, boolean PC) {
		if (PC)
			this.name = Name.Class.PC[classID];
		else
			this.name = Name.Class.NPC[classID];
	}
	public String getName() {
		return this.name;
	}
	public void setClassID(int classID) {
		this.classID = classID;
	}
	public int getClassID() {
		return this.classID;
	}
	public void setSpecial(ArrayList<Special> special) {
		this.special = special;
	}
	public ArrayList<Special> getSpecial() {
		return this.special;
	}
	public Special returnFromPointOfIndex(Special special) {
		return this.getSpecial().get(this.getSpecial().indexOf(special));
	}
	
	public void replaceSpecial(Special newSpecial, Special oldSpecial) { // replaces a specail ability with another one , used to rplace specials with better versions of themselves ie damage reduction, or replace class abilities entirely in the case of class variants
		this.special.remove(oldSpecial);
		this.special.add(newSpecial);
			
	}
	public void addSpecial(Special special) {
		if (!this.special.contains(special)) // checks to see if special is already on the list;
			this.special.add(special);
	}
	public void setLevel(int level) {
		this.level = level;
	}
	public int getLevel() {
		return this.level;
	}
	public void setClassPC(int classID) {
		this.setClassID(classID);
		this.setName(classID, true);
	}
	public void setClassNPC(int classID) {
		this.setClassID(classID);
		this.setName(classID, false);
	}
	
	static class PC {
		public static final int BARBARIAN = 0, BARD = 1, CLERIC = 2, DRUID = 3, FIGHTER = 4, MONK = 5, PALADIN = 6, RANGER = 7, ROGUE = 8, WIZARD = 9, SCORCERER = 10;
		public final int[] hitDie = {12,8,8,8,10,8,10,10,8,6,6};
		public static class Barbarian extends Class {
			
			public Barbarian() {
				super();
				this.setName(BARBARIAN,true);
			}
			
			public Barbarian(int level) {
				super();
				this.setClassPC(BARBARIAN);
			}
			
			
		}
		public static class Bard extends Class {

			public Bard() {
				super();
				this.setName(BARD,true);
				
			}
			
			public Bard(int level) {
				super();
				this.setClassPC(BARD);
			}
			
		}
		public static class Cleric extends Class {

			public Cleric() {
				super();
				this.setName(CLERIC,true);
				
			}
			
			public Cleric(int level) {
				super();
				this.setClassPC(CLERIC);
			}
			
		}
		public static class Druid extends Class {

			public Druid() {
				super();
				this.setName(DRUID,true);
				
			}
		}
		public static class Fighter extends Class {

			public Fighter() {
				super();
				this.setName(FIGHTER,true);
				
			}
		}
		public static class Monk extends Class {

			public Monk() {
				super();
				this.setName(MONK,true);
				
			}
		}
		public static class Paladin extends Class {

			public Paladin() {
				super();
				this.setName(PALADIN,true);
				
			}
		}
		public static class Ranger extends Class {
			
			public Ranger() {
				super();
				this.setName(RANGER,true);
			}
		}
		public static class Rogue extends Class {
			
			public Rogue() {
				super();
				this.setName(ROGUE,true);
			}
		}
		public static class Wizard extends Class {
			
			public Wizard() {
				super();
				this.setName(WIZARD,true);
			}
		}
		public static class Sorcerer extends Class {
			
			public Sorcerer() {
				super();
				this.setName(SCORCERER,true);
			}
		}
		
	}
	static class NPC {
		
		public static final int ADEPT = 0, ARISTOCRAT = 1, COMMONER = 2, EXPERT = 3, WARRIOR = 4;
		public final int[] hitDie = {6,8,6,8,10};
		
		public static class Adept extends Class {
			
			public Adept() {
				super();
				this.setName(ADEPT,false);
			}
			
			public Adept(int level) {
				super();
				this.setClassNPC(ADEPT);
			}
			
		}
		public class Aristocrat extends Class {
			
			public Aristocrat() {
				super();
				this.setName(ARISTOCRAT, false);
			}
		}
		public class Commoner extends Class {
			
			public Commoner() {
				super();
				this.setName(COMMONER, false);
			}
			
		}
		public class Expert extends Class {
			
			public Expert() {
				super();
				this.setName(EXPERT, false);
			}
		}
		public class Warrior extends Class {
			
			public Warrior() {
				super();
				this.setName(WARRIOR, false);
			}
		}
	}
	
	public void setClassAbilities(int level, int classID, boolean isPC) {
		// method for setting all class abilities according to level 
		
		if (!isPC) {
			
			switch (classID) {
			case NPC.ADEPT:
				if (level >= 2) {
					this.addSpecial(summonFamiliar);
				}
					
				break;

			default:
				break;
			}
			
		}
		if (isPC) {
			switch (classID) {
			case PC.BARBARIAN:
				switch (level) {
				case 20:
					this.addSpecial(mightyRage);
					if (this.getSpecial().contains(damageReduction)) {
						this.replaceSpecial(new DamageReduction(5,Type.Damage.NONE),damageReduction);
						
					}
					this.addSpecial(new Special.ExtraordinaryAblitiy.DamageReduction(5,Type.Damage.NONE));
						
				}
			
			}
		}
	}
	
	public boolean equals(Class x) {
		if(this.getName() == x.getName()) {
			return true;
		}
		return false;
	}
}
