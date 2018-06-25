package pthfndr.src.main;

import java.util.ArrayList;

import pthfndr.src.main.Special.ExtraordinaryAblitiy.*;
import pthfndr.src.main.Special.SpellLikeAblitiy.*;
import pthfndr.src.main.Special.SupernaturalAbilitiy.*;
import pthfndr.src.main.Type.Damage;

public class Special implements Magic.Desciptor{
	
	private String name;

	public Special(String name) {
		this.setName(name);
	}
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public static boolean check(Creature creature, Special special)
	{
		boolean check = false;
		for (int i = 0; i < creature.specialLength(); i++)
		{
			if (creature.getSpecial(i).equals(special))
			{
				check = true;
			}
		}
		return check;
	}
	
	public boolean equals(Special x)
	{
		if(this.getName() == x.getName())
			return true;
		return false;
	}
	
	public static class MagicArmor extends Special {
		private boolean armor;
		private boolean sheild;
		private int bonusCost;
		private double gpCost;
		private int auraStrenght;
		private int auraSchool;
		private Spell spell;
		public MagicArmor(String name) {
			super(name);
		}
		
		public void setArmor(boolean value) {
			this.armor = value;
		}
		public boolean isArmor() {
			return this.armor;
		}
		
		public boolean isSheild() {
			return sheild;
		}
		public void setSheild(boolean sheild) {
			this.sheild = sheild;
		}

		public int getBonusCost() {
			return bonusCost;
		}
		public void setBonusCost(int bonusCost) {
			this.bonusCost = bonusCost;
		}

		public double getGpCost() {
			return gpCost;
		}
		public void setGpCost(double gpCost) {
			this.gpCost = gpCost;
		}
		
		public void setAura(int strenght, int school) {
			this.auraStrenght = strenght;
			this.auraSchool = school;
		}
		public int[] getAura() {
			int[] aura = {this.auraStrenght,this.auraSchool};
			return aura;
		}
		
		
		public boolean isAplicable(Item item) {
			return true;
		}

		public static class Animated extends MagicArmor {
			public Animated() {
				super("Animated");
				this.setArmor(false);
				this.setSheild(true);
				this.setBonusCost(2);
				this.setAura(Aura.Strenght.STRONG, Magic.School.TRANSMUTATION);
			}
			
			public boolean isAplicable(Item item) {
				if (item.equals(Sheild.List.sheildTower) || item.getClass() == new Armor().getClass()) 
					return false;
				return true;
			}
		}
		
		public static class ArrowCatching extends MagicArmor {
			public ArrowCatching() {
				super("Arrow Catching");
				this.setArmor(false);
				this.setSheild(true);
			}
		}
		
		
	}
	
	public static class ExtraordinaryAblitiy extends Special {
		public ExtraordinaryAblitiy(String name) {
			super(name);
		}
		
		
		//Barbarian Class Extraordinary Ablilties
		public static class FastMovement extends ExtraordinaryAblitiy {
			public FastMovement() {
				super("Fast Movement");
			}
			
			public static int bonus(Creature creature) {
				if ( Special.check(creature, List.fastMovement) && creature.checkArmorType(Armor.ArmorType.HEAVY) == false && Encumbrance.check(creature) < Encumbrance.HEAVY ) {
					return 10;
				}
				
				return 0;
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new FastMovement());
			}
		}
		
		public static class RagePowers extends ExtraordinaryAblitiy {
			
			private ArrayList<ExtraordinaryAblitiy> ragePowers = new ArrayList<>();
			
			public RagePowers() {
				super("Rage Powers");
			}
			
			public ArrayList<ExtraordinaryAblitiy> getRagePowers() {
				return this.ragePowers;
			}
			public void setRagePowers(ArrayList<ExtraordinaryAblitiy> ragePowers) {
				this.ragePowers = ragePowers;
			}
			public void addRagePower(ExtraordinaryAblitiy ragePower) {
				this.ragePowers.add(ragePower);
			}
			public static boolean check(Creature creature, ExtraordinaryAblitiy ragePower) {
				boolean value = false;
				if (creature.getSpecialAbilities().contains(Special.List.ragePowers)) {
					RagePowers mod = (RagePowers) creature.getSpecial(creature.getSpecialAbilities().indexOf(Special.List.ragePowers));
					value = mod.getRagePowers().contains(ragePower);
				}
					return value;
			}
			
			public static class RagePower extends ExtraordinaryAblitiy {
				Special prereq;
				int levelreq;
				boolean oncePerRage;
				boolean oncePerDay;
				
				public RagePower() {
					super("Rage Power");
				}
				
				public RagePower(String name,Special prereq, int levelreq, boolean oncePerRage, boolean oncePerDay) {
					super(name);
					this.setPrereq(prereq);
					this.setLevelreq(levelreq);
					this.setOncePerDay(oncePerDay);
					this.setOncePerRage(oncePerRage);
				}
				
				public Special getPrereq() {
					return prereq;
				}
				public void setPrereq(Special prereq) {
					this.prereq = prereq;
				}
				
				public int getLevelreq() {
					return levelreq;
				}
				public void setLevelreq(int levelreq) {
					this.levelreq = levelreq;
				}
				
				public boolean isOncePerRage() {
					return oncePerRage;
				}
				public void setOncePerRage(boolean oncePerRage) {
					this.oncePerRage = oncePerRage;
				}
				
				public boolean isOncePerDay() {
					return oncePerDay;
				}
				public void setOncePerDay(boolean oncePerDay) {
					this.oncePerDay = oncePerDay;
				}
				
			}
			
			public static interface List {
				RagePower animalFury = new RagePower("Animal Fury", null, 0, false, false);
				RagePower clearMind = new RagePower("Clear Mind", null, 8, true, false);
				RagePower fearlessRage = new RagePower("Fearless Rage",null, 12, false, false);
				RagePower gaurdedStance = new RagePower("Guarded Stance", null, 0, false, false);
				RagePower increasedDamageReduction = new RagePower("Increased Damage Reduction", null, 8, false, false);
				RagePower internalFortitude = new RagePower("Internal Fortitude", null, 8, false, false);
				RagePower intimidatingGlare = new RagePower("Intimidading Glare", null, 0, false, false);
				RagePower knockback = new RagePower("Knockback", null, 0, false, false);
				RagePower lowLightVision = new RagePower("Low-Light Vision", null, 0, false, false);
				RagePower mightySwing = new RagePower("Mighty Swing", null, 12, true, false);
				RagePower momentOfClarity = new RagePower("Moment of Clarity", null, 0, true, false);
				RagePower nightVision = new RagePower("Night Vision", lowLightVision, 0, false, false);
				RagePower noEscape = new RagePower("No Escape", null, 0, true, false);
				RagePower powerfulBlow = new RagePower("Powerful Blow", null, 0, true, false);
				RagePower quickReflexes = new RagePower("Quick Reflexes", null, 0, false, false);
				RagePower ragingClimber = new RagePower("Raging Climber", null, 0, false, false);
				RagePower ragingLeaper = new RagePower("Raging Leaper", null, 0, false, false);
				RagePower ragingSwimmer = new RagePower("Raging Swimmer", null, 0, false, false);
				RagePower renewedVigor = new RagePower("Renewed Vigor", null, 4, false, true);
				RagePower rollingDodge = new RagePower("Rolling Dodge", null, 0, false, false);
				RagePower rousedAnger = new RagePower("Roused Anger", null, 0, false, false);
				RagePower scent = new RagePower("Scent", null, 0, false, false);
				RagePower strengthSurge = new RagePower("Strength Surge", null, 0, true, false);
				RagePower superstition = new RagePower("Superstition", null, 0, false, false);
				RagePower supriseAccuracy = new RagePower("Suprise Accuracy", null, 0, true, false);
				RagePower swiftFoot = new RagePower("Swift Foot", null, 0, false, false);
				RagePower terrifyingHowl = new RagePower("Terrifying Howl", intimidatingGlare, 8, false, false);
				RagePower unexpectedStrike = new RagePower("UnexpectedStrike", null, 8, true, false);
				
			}
		}
		
		public static class UncannyDodge extends ExtraordinaryAblitiy {
			public UncannyDodge() {
				super("Uncanny Dodge");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, Special.List.uncannyDodge);
			}
		}
		
		public static class TrapSense extends ExtraordinaryAblitiy {
			public TrapSense() {
				super("Trap Sense");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, Special.List.trapSense);
			}
		}
		
		public static class ImprovedUncannyDodge extends ExtraordinaryAblitiy {
			public ImprovedUncannyDodge() {
				super("Improved Uncanny Dodge");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new ImprovedUncannyDodge());
			}
		}
		
		public static class GreaterRage extends ExtraordinaryAblitiy {
			public GreaterRage() {
				super("Greater Rage");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new GreaterRage());
			} 
			
		}
		
		public static class IndominableWill extends ExtraordinaryAblitiy {
			public IndominableWill() {
				super("Indominable Will");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new IndominableWill());
			} 
			
		}
		
		public static class TirelessRage extends ExtraordinaryAblitiy {
			public TirelessRage() {
				super("Tireless Rage");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new TirelessRage());
			} 
			
		}
	
		public static class MightyRage extends ExtraordinaryAblitiy {
			public MightyRage() {
				super("Mighty Rage");
			}
			
			public static boolean check(Creature creature) {
				return Special.check(creature, new MightyRage());
			} 
		}
		
		
		//general Extraordinary Abilities
		public static class DamageReduction extends ExtraordinaryAblitiy {
			
			private int value;
			private boolean[] type = new boolean[12];
			private boolean and  = false;
			
			public DamageReduction() {
				super("Damage Reduction");
			}
			public DamageReduction(int value, int type) {
				super("Damage Reduction");
				this.setValue(value);
				this.setType(type);
				
			}
			public DamageReduction(int value, int type1, int type2, boolean and) {
				super("Damage Reduction");
				this.setValue(value);
				this.setType(type1);
				this.setType(type2);
				this.setAnd(and);
			}
			
			public int getValue() {
				return value;
			}
			public void setValue(int value) {
				this.value = value;
			}
			public boolean getType(int type) {
				return this.type[type];
			}
			public void setType(int type) {
				if (type != Damage.NONE)
					this.type[type] = true;
			}
			public void setAnd(boolean value) {
				this.and = value;
			}
			public boolean isAnd() {
				return this.and;
			}
			
			public String toStatBlock() {
				String retvalue = "DR " + this.value + "/";
				boolean flag = false;
				for(int i = 0; i < type.length; i++) {
					if (type[i] == true) {
						if (flag) {
							retvalue += (and == true ? " and " : " or ");
						}
						flag = true;
						retvalue += Name.returnLowercase(Name.DRTypes[i]);
					}
				}
				if (!flag)
					retvalue += "-";
				return retvalue;
			}
		}
				
		public static class Regeneration extends ExtraordinaryAblitiy {
			public Regeneration(byte rate) {
				super("Regeneration");
				this.setRate(rate);
			}
			public Regeneration() {
				super("Regeneration");
			}
			private byte rate;
			public byte getRate() {
				return rate;
			}
			public void setRate(byte rate) {
				this.rate = rate;
			}
			public static Special regeneration = new Regeneration();
		}
	
	}
	
	public static class SpellLikeAblitiy extends Special {
		public SpellLikeAblitiy(String name) {
			super(name);
		}
		
		public class EnergyBurst extends SpellLikeAblitiy {
			
			public int type;
			
			public EnergyBurst(byte type){
				super("");
				this.type = type;
				switch (type) {
				case FIRE:
					this.setName("Fire Bolt");
					break;
				case ELECTRICITY:
					this.setName("Lightning Arc");
					break;
				case ACID:
					this.setName("Acid Dart");
					break;
				case COLD:
					this.setName("Icicle");
					break;
				default:
					break;
				}
			}
			
			public int getType() {
				return this.type;
			}
		}
		
		public static class SummonFamiliar extends SpellLikeAblitiy {

			Creature familiar;
			public SummonFamiliar() {
				super("Summon Familiar");
			}
			
			
		}
		
	}
	
	public static class SupernaturalAbilitiy extends Special {

		public SupernaturalAbilitiy(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public static class ChannelEnergy extends SupernaturalAbilitiy {
			
			public ChannelEnergy() {
				super("Channel Energy");
			}
		}
	}
	
	public interface List {
		// for comparing with equals(Special) function
		//Extaordinary Abilities
		public static FastMovement fastMovement = new FastMovement();
		public static DamageReduction damageReduction = new DamageReduction();
		public static MightyRage mightyRage = new MightyRage();
		public static RagePowers ragePowers = new RagePowers();
		public static UncannyDodge uncannyDodge = new UncannyDodge();
		public static TrapSense trapSense = new TrapSense();
		//Spell-like Abilities
		public static SummonFamiliar summonFamiliar = new SummonFamiliar();
		//Supernatural Abilities
		public static ChannelEnergy channelEnergy = new ChannelEnergy();
		
		
	}

	public static void main(String[] args) {
		Creature bob = Generate.Test.bob;
		bob.addSpecial(Special.List.ragePowers);
		RagePowers hold = (RagePowers) bob.getSpecialAbilities().get(bob.getSpecialAbilities().indexOf(Special.List.ragePowers));
		hold.addRagePower(RagePowers.List.noEscape);
		hold.addRagePower(RagePowers.List.animalFury);
		RagePowers.check(bob, RagePowers.List.animalFury);
		System.out.println(UncannyDodge.check(bob) + "\n" + RagePowers.check(bob, RagePowers.List.animalFury) + "\n" + RagePowers.check(bob, RagePowers.List.noEscape));
		System.out.println(ImprovedUncannyDodge.check(bob));
		DamageReduction test = new DamageReduction(10, Damage.ADAMANTINE, Damage.MAGIC, true);
		System.out.println(test.toStatBlock());

	}

}
