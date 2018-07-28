package pthfndr.src.main;

import java.util.ArrayList;
import java.util.Arrays;

import pthfndr.src.main.Spell.Aim.Effect;
import pthfndr.src.main.Spell.Duration.SavingThrow;


public class Spell implements Alignment, Type, Magic, Magic.School, Time, Constants {
	
	private String name;
	private byte[] school = new byte[2];
	private boolean[] descriptor = new boolean[19];
	private ArrayList<Byte> level = new ArrayList<>();
	private Component components;
	private int castingTime;
	private Range range;
	private Aim aim;
	private Duration duration;
	private SavingThrow save;
	private boolean spellResistance;
	
	public Spell(String name, byte[] school,boolean[] descriptor, int[] level, Component components, int castingTime, Range range, Aim aim, Duration duration,SavingThrow save, boolean spellResistance) {
		this.name = name;
		this.school = school;
		this.descriptor = descriptor;
		for(int i = 0; i < level.length; i++) {
			this.level.add((byte) level[i]);
		}
		this.setComponents(components);
		this.castingTime = castingTime;
		this.range = range;
		this.aim = aim;
		this.duration = duration;
		this.save = save;
		this.spellResistance = spellResistance;
	}
	public Spell() {
		//test spell constructor
		this.name = "Test Spell";
		for (int i = 0; i < 6; i++)
			this.level.add((byte) -1);
		this.components = new Component(true, false, new ArrayList<Item>(Arrays.asList(Item.SpellComponent.batGuano, Item.SpellComponent.addersStomach)), null, false);
		this.castingTime = STANDARD_ACTION;
		this.range = new Range(Range.PERSONAL);
		this.aim = new Aim(null, null);
	};
	
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return this.name;
	}
	
	public void setSchool(int school, int subschool) {
		this.school[0] = (byte) school;
		this.school[1] = (byte) subschool;
	}
	public byte[] getSchool() {
		return this.school;
	}
	
	public void setDescriptor(int descriptor) {
		this.descriptor[descriptor] = true;
	}
	public void removeDescriptor(int descriptor) {
		this.descriptor[descriptor] = false;
	}
	public boolean isDescriptor(int descriptor)  {
		return this.descriptor[descriptor];
	}
	
	public void setLevel(byte level, int casterType) {
		switch (casterType) {
		case Class.PC.BARD:
			this.level.add(0, level);
			break;
		case Class.PC.CLERIC:
			this.level.add(1, level);
			break;
		case Class.PC.DRUID:
			this.level.add(2, level);
			break;
		case Class.PC.PALADIN:
			this.level.add(3, level);
			break;
		case Class.PC.RANGER:
			this.level.add(4, level);
			break;
		case Class.PC.SCORCERER:
		case Class.PC.WIZARD:
			this.level.add(5, level);
			break;

		default:
			break;
		}
	}
	public byte getSpellLevel(int casterType) {
		switch (casterType) {
		case Class.PC.BARD:
			return this.level.get(0);
		case Class.PC.CLERIC:
			return this.level.get(1);
		case Class.PC.DRUID:
			return this.level.get(2);
		case Class.PC.PALADIN:
			return this.level.get(3);
		case Class.PC.RANGER:
			return this.level.get(4);
		case Class.PC.SCORCERER:
		case Class.PC.WIZARD:
			return this.level.get(5);
		default:
			break;
		}
		return -1;
	}
	
	public byte getCasterLevel(int casterType, Character caster) {
		switch (casterType) {
		case Class.PC.BARD:
			return caster.getClassLevel(Class.bard);
		case Class.PC.CLERIC:
			return caster.getClassLevel(Class.cleric);
		case Class.PC.DRUID:
			return caster.getClassLevel(Class.druid);
		case Class.PC.PALADIN:
			return caster.getClassLevel(Class.paladin);
		case Class.PC.RANGER:
			return caster.getClassLevel(Class.ranger);
		case Class.PC.SCORCERER:
			return caster.getClassLevel(Class.sorcerer);
		case Class.PC.WIZARD:
			return caster.getClassLevel(Class.wizard);
		default:
			break;
		}
		return -1;
	}
	
	public ArrayList<Byte> getlevel() {
		return this.level;
	}
	
	public void setComponents(Component components) {
		this.components = components;
	}
	public Component getComponents() { 
		return this.components;
	}
	
	public void setCastingTime(int time) {
		this.castingTime = time;
	}
	public int getCastingTime() {
		return this.castingTime;
	}
	
	public void setRange(Range range) {
		this.range = range;
	}
	public Range getRange() {
		return this.range;
	}
	
	public void setAim(Aim aim) {
		this.aim = aim;
	}
	public Aim getAim() {
		return this.aim;
	}
	
	public void setDuration(Duration duration) {
		this.duration = duration;
	}
	public Duration.Timed getDuration() {
		return (Duration.Timed) this.duration;
	}
	
	public void setSave(SavingThrow save) {
		this.save = save;
	}
	public SavingThrow getSave() {
		return this.save;
	}
	
	public void setSpellResistance(boolean spellResistance) {
		this.spellResistance = spellResistance;
	}
	public boolean getSpellResistance() {
		return this.spellResistance;
	}
	public boolean spellResistanceCheck(Creature caster,int casterType, Creature target) {
		if (!this.getSpellResistance())
			return false;
		else if (Special.check(target, Special.List.spellResistance)) {
			if (Roll.d(20) + getCasterLevel(casterType, (Character) caster) >=  target.getSR().getValue()) {
				return false;
			}
		}
		return true;
	}
	
	public static class Component {
		private boolean verbal;
		private boolean somatic;
		private ArrayList<Item> material = new ArrayList<>();
		private Item focus;
		private boolean devineFocus;
		
		public Component(boolean verbal, boolean somatic, ArrayList<Item> material, Item focus, boolean devineFocus) {
			this.verbal = verbal;
			this.somatic = somatic;
			this.material = material;
			this.focus = focus;
			this.devineFocus = devineFocus;
		}
		
		public boolean isVerbal() {
			return verbal;
		}
		public boolean isSomatic() {
			return somatic;
		}
		public ArrayList<Item> getMaterial() {
			return material;
		}
		public Item getFocus() {
			return focus;
		}
		public boolean isDevineFocus() {
			return devineFocus;
		}
		public String materialCode() {
			String value = "null";
			if (!this.getMaterial().isEmpty()) {
				value = "new ArrayList<Item>(Arrays.asList(";
				for (int i = 0; i < this.getMaterial().size(); i ++) {
					value += "Item.SpellComponent." + Generate.codeName(this.getMaterial().get(i).getName());
					if (i < this.getMaterial().size() - 1) {
						value += ", ";
					}
					else {
						value += ")";
					}
				}
				value += ")";
			}
			return value;
		}
		
		public String focusCode() {
			String value = "null";
			if (this.getFocus() != null) {
				value = "Item.SpellComponent.Focus." + Generate.codeName(this.getFocus().getName());
			}
			return value;
		}
		
		public String toCode() {
			String c = ", ";
		//	Component(boolean verbal, boolean somatic, ArrayList<Item> material, Item focus, boolean devineFocus) {
			return "new Component(" + this.isVerbal() +	c + this.isSomatic() + c + materialCode() + c + this.focusCode() + c + this.isDevineFocus() + ")";
		}
		
	}
	
	public static class Range {
		public static final byte PERSONAL = 0, TOUCH = 1, CLOSE = 2, MEDIUM = 3, LONG = 4, UNLIMITED = 5;
		private boolean personal;
		private boolean touch;
		private boolean close;
		private boolean medium;
		private boolean longRange;
		private boolean unlimited;
		private int inFeet = 0;
		
		public Range(int range) {
			switch (range) {
			case PERSONAL:
				this.personal = true;
				break;
			case TOUCH:
				this.touch = true;
				break;
			case CLOSE:
				this.close = true;
				break;
			case MEDIUM:
				this.medium = true;
				break;
			case LONG:
				this.longRange = true;
				break;
			case UNLIMITED:
				this.unlimited = true;
				break;
			default:
				this.inFeet = range;
				break;
			}
			
		}

		public boolean isPersonal() {
			return personal;
		}

		public boolean isTouch() {
			return touch;
		}

		public boolean isClose() {
			return close;
		}

		public boolean isMedium() {
			return medium;
		}

		public boolean isLongRange() {
			return longRange;
		}

		public boolean isUnlimited() {
			return unlimited;
		}

		public int getInFeet() {
			return inFeet;
		}
		
		String toCode() {
			String value = "new Range(";
			if (this.inFeet != 0) {
				value += inFeet;
			}
			else {
				if(close) {
					value += CLOSE;
				}
				else if(longRange) {
					value += LONG;
				}
				else if(medium) {
					value += MEDIUM;
				}
				else if(personal) {
					value += PERSONAL;
				}
				else if(touch) {
					value += TOUCH;
				}
				else if(unlimited) {
					value += UNLIMITED;
				}
			}
			return value + ")";
		}
				
	}
	
	public static class Aim {
		private ArrayList<Object> targets = new ArrayList<>();
		private Effect effect;
		private Area area;
		
		public Aim (Effect effect, Area area) {
			this.setEffect(effect);
			this.setArea(area);
		}
		
		public void addTarget(Creature creature) {
			this.targets.add(creature);
		}
		public void addTarget(Item item) {
			this.targets.add(item);
		}
		public void setTargets(ArrayList<Object> targets) {
			for (int i = 0; i < targets.size(); i++) {
				if(targets.get(i).getClass() == new Item().getClass()) {
					this.addTarget((Item) targets.get(i));
				}
				else if(targets.get(i).getClass() == new Creature().getClass()) {
					this.addTarget((Creature) targets.get(i));
				}
			}
		}
		public void removeTarget(Creature creature) {
			targets.remove(creature);
		}
		public void removeTarget(Item item) {
			targets.remove(item);
		}
		
		public void setEffect(Effect effect) {
			this.effect = effect;
		}
		public Effect getEffect() {
			return this.effect;
		}
		public void setArea(Area area) {
			this.area = area;
		}
		public Area getArea() {
			return this.area;
		}
		
		public static class Effect {
			
			public static class Ray extends Effect {
				public Ray() {
					super();
					//Might need something here;
				}
			}
			public static class Spread extends Effect {
				private int radius;
				private int height;
				
				public Spread(int radius, int height) {
					this.radius = radius;
					this.height = height;
				}
				
				public int getRadius() {
					return this.radius;
				}
				public int getHeight() {
					return this.height;
				}
				
			}
			
			public Effect() {}
			
			public String toCode() {
				
				String value = "";
				if( this.getClass() == Spread.class) {
					Spread x = (Spread) this;
					value = "new Effect.Spread(" + x.getRadius() + ", " + x.getHeight() + ")";
				}
				else if (this.getClass() == Ray.class) {
					value = "new Effect.Ray()";
				}
				else {
					value = "new Effect()";
				}
				return value;
			}
		}
		
		public static class Area {
			public final byte BURST = 0, EMINATION = 1, SPREAD = 2;
			public final byte CONE = 0, CYLINDER = 1, LINE = 2, SPHERE = 3;
			private int BES;
			private int shape;
			private boolean creatures, livingCreatures, objects;
			private boolean shapeable;
			
			public Area(int bes, int shape, boolean creatures, boolean livingCreatures, boolean objects, boolean shapable) {
				this.setBES(bes);
				this.setShape(shape);
				this.setAffects(creatures, livingCreatures, objects);
				this.setShapable(shapable);
			}
			public Area() {};
			
			public void setBES(int bes) {
				this.BES = bes;
			}
			public int getBES() {
				return this.BES;
			}
			public void setShape(int shape) {
				this.shape = shape;
			}
			public int getShape() {
				return this.shape;
			}
			public void setAffects(boolean creatures, boolean livingCreatures, boolean objects) {
				this.creatures = creatures;
				this.livingCreatures = livingCreatures;
				this.objects = objects;
			}
			public boolean[] getAffects() {
				boolean[] value = {this.creatures,this.livingCreatures,this.objects};
				return value;
			}
			public void setShapable(boolean shapable) {
				this.shapeable = shapable;
			}
			public boolean isShapable() {
				return this.shapeable;
			}
			
			public String toCode() {
				String c = ", ";
				String value = "null";
				if( this != null) {
					value = "new Area(" + this.getBES() + c + this.getShape() + c + this.creatures + c + this.objects + c + this.shapeable + ")";
					//public Area(int bes, int shape, boolean creatures, boolean livingCreatures, boolean objects, boolean shapable)
				}
				return value;
			}
		}
		
		public String toCode() {
			String c = ", ";
			
			String value = "new Aim(";
			if (this.effect != null)
				value += this.effect.toCode() + c;
			else 
				value += "null" + c;
			if (this.area != null)
				value += this.area.toCode() + ")";
			else
				value += "null" + ")";
			//public Aim (Effect effect, Area area)
			return value;
			
		}
		
	}
	
	public static class Duration {
		private boolean dismisable;
		private boolean concentration;
		private boolean instantaneous;
		private boolean permanent;
				
		public Duration(boolean dismisable, boolean concentration, boolean instantaneous, boolean permanent) {
			this.setDismisable(dismisable);
			this.setConcentration(concentration);
			this.setInstantaneous(instantaneous);
			this.setPermanent(permanent);
		}
		
		public boolean isDismisable() {
			return dismisable;
		}
		public void setDismisable(boolean dismisable) {
			this.dismisable = dismisable;
		}

		public boolean isConcentration() {
			return concentration;
		}
		public void setConcentration(boolean concentration) {
			this.concentration = concentration;
		}

		public boolean isInstantaneous() {
			return instantaneous;
		}
		public void setInstantaneous(boolean instantaneous) {
			this.instantaneous = instantaneous;
		}

		public boolean isPermanent() {
			return permanent;
		}
		public void setPermanent(boolean permanent) {
			this.permanent = permanent;
		}
		
		public String toCode() {
			return "new Duration(" + this.dismisable + c + this.concentration + c + this.instantaneous + c + this.permanent + ")";
		}

		public static class Timed extends Duration {
			private int timeUnit; //Base time unit 
			private int timeMultiplier; // Multiplier of base time unit
			private int staticDuration; //Static duration of the spell;
			private int[] variableDuration = new int[2]; // variable duration  
			private int unitPerLevels; //how many levels per additional time unit
			
			public Timed(boolean dismisable, int timeMultiplier, int timeUnit, int saticDuration, int[] variableDuration, int unitPerLevels) {
				super(dismisable,false,false,false);
				this.timeMultiplier = timeMultiplier;
				this.timeUnit = timeUnit;
				this.staticDuration = saticDuration;
				this.variableDuration = variableDuration;
				this.unitPerLevels = unitPerLevels;
			}

			public int getTimeUnit() {
				return timeUnit;
			}

			public void setTimeUnit(int timeUnit) {
				this.timeUnit = timeUnit;
			}

			public int getTimeMultiplier() {
				return timeMultiplier;
			}

			public void setTimeMultiplier(int timeMultiplier) {
				this.timeMultiplier = timeMultiplier;
			}

			public int getStaticDuration() {
				return staticDuration;
			}

			public void setStaticDuration(int staticDuration) {
				this.staticDuration = staticDuration;
			}

			public int[] getVariableDuration() {
				return variableDuration;
			}

			public void setVariableDuration(int[] variableDuration) {
				this.variableDuration = variableDuration;
			}

			public int getUnitPerLevels() {
				return unitPerLevels;
			}

			public void setUnitPerLevels(int unitPerLevels) {
				this.unitPerLevels = unitPerLevels;
			}
			
			public int getTotalDuration(Creature caster, int casterLevel) {
				int baseTime = this.getTimeUnit() * this.timeMultiplier;
				return this.staticDuration * baseTime + Roll.numDsize(this.getVariableDuration()[0],this.getVariableDuration()[1]) * baseTime + this.getUnitPerLevels();
			}
			
			public String toCode() {
				return "new Timed(" + this.isDismisable() + c + this.timeMultiplier + c + this.timeUnit + c + this.staticDuration + c + Generate.arrayToString(variableDuration) + c + this.unitPerLevels + ")";
			}	
		}
		
		public static class SavingThrow {
			public final int FORT = 0, REF = 1, WILL = 2;
			public final int Negates = 0, Partial = 1, Half = 2, Disbelief = 3;
			private boolean harmless;
			private boolean[][] save;
			
			public SavingThrow(boolean[][] save, boolean harmless) {
				this.save = save;
				this.harmless = harmless;
			}
			public SavingThrow() {
			}
			
			public int getSaveDc(Character caster, int casterType, Spell spellCast) {
				return 10 + spellCast.getSpellLevel(casterType) + Stat.casterStatBonus(caster, casterType);
			}
			
			public boolean[][] getSave() {
				return this.save;
			}
			public boolean[] getSave(int saveType) {
				return save[saveType];
			}
			
			public boolean isHarmless() {
				return this.harmless;
			}
			public void setHarmless(boolean harmless) {
				this.harmless = harmless;
			}
		}
		
	}
	public static interface List {
		Spell acidArrow = new Spell("Acid Arrow", new byte[]{1, 2}, new boolean[]{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new int[] {-1,-1,-1,-1,2}, new Component(true, true, new ArrayList<Item>(Arrays.asList(Item.SpellComponent.rubarbLeaf, Item.SpellComponent.addersStomach)), Item.SpellComponent.Focus.dart, false), 3, new Range(4), new Aim(new Effect(), null), new Duration.Timed(false, 1, Time.FULL_ROUND, 1, null, 3),new SavingThrow(),false);
		Spell acidFog = new Spell("Acid Fog", new byte[]{1, 2}, new boolean[]{true, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false, false}, new int[] {-1,-1,-1,-1,6}, new Component(true, true, new ArrayList<Item>(Arrays.asList(Item.SpellComponent.powderedPeas, Item.SpellComponent.animalHoof)), null, false), 3, new Range(3), new Aim(new Effect.Spread(20, 20), null),new Duration.Timed(false, 1, Time.FULL_ROUND, 0, null, 1), new SavingThrow(), false);

		//                  Spell(String name, byte[] school,boolean[] descriptor, byte level, Component components, int castingTime, Range range, Aim aim) {

	}
	
	public String toCode() {
		//public Spell(String name, byte[] school,boolean[] discriptor, byte level, Component components, int castingTime, Range range, Aim aim) {
			
		String c = ", ";
		return "Spell " + Generate.codeName(this.getName()) + " = new Spell(\"" + this.getName() + "\"" + c + "new byte[]" + Generate.arrayToString(this.getSchool()) + c + "new boolean[]" + Generate.arrayToString(descriptor) + c + Generate.arrayToString(Generate.arrayListToIntArray(this.getlevel())) + c + this.getComponents().toCode() + c + this.getCastingTime() + c + this.getRange().toCode() + c + this.getAim().toCode()+ ");"; 
	}
	
	public static void main(String args[]) {
		Spell test = new Spell();
		test.setName("Pool of Bones");
		test.setSchool(Magic.School.UNIVERSAL, Magic.School.Subschool.NONE);
		test.level.set(5, (byte) 3);
		System.out.println(Generate.arrayToString(Generate.arrayListToIntArray(test.level)));
	}
	
}
