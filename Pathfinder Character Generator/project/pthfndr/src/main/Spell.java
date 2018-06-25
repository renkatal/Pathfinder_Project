package pthfndr.src.main;

import java.util.ArrayList;
import java.util.Arrays;

import pthfndr.src.main.Name.Magic.subschool;


public class Spell implements Alignment, Type, Magic, Magic.School, Time {
	
	private String name;
	private byte[] school = new byte[2];
	private boolean[] descriptor = new boolean[19];
	private byte level;
	private Component components;
	private int castingTime;
	private Range range;
	private Aim aim;
	
	public Spell(String name, byte[] school,boolean[] discriptor, byte level, Component components, int castingTime, Range range, Aim aim) {
		
	}
	public Spell() {
		//test spell constructor
		this.name = "Test Spell";
		this.level = 1;
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
	
	public void setLevel(byte level) {
		this.level = level;
	}
	public byte getLevel() {
		return level;
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
			if (this.getMaterial() != null) {
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
			}
			return value;
		}
		
		public String toCode() {
			String c = ", ";
		//	Component(boolean verbal, boolean somatic, ArrayList<Item> material, Item focus, boolean devineFocus) {
			return "new Component(" + this.isVerbal() +	c + this.isSomatic() + c + materialCode() + c +  this.getFocus() + c + this.isDevineFocus()  + ")";
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
			String c = ", ";
			String value = "Range(";
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
					value = "new Spread(" + x.getRadius() + ", " + x.getHeight() + ")";
				}
				else if (this.getClass() == Ray.class) {
					value = "new Ray()";
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
	
	public static interface List {
		
	}
	
	public String toCode() {
		//public Spell(String name, byte[] school,boolean[] discriptor, byte level, Component components, int castingTime, Range range, Aim aim) {
			
		String c = ", ";
		return "Spell " + Generate.codeName(this.getName()) + " = new Spell(\"" + this.getName() + "\"" + c + Generate.arrayToString(this.getSchool()) + c + this.getLevel() + c + this.getComponents().toCode() + c + this.getCastingTime() + c + this.getRange().toCode() + c + this.getAim().toCode()+ ");"; 
	
	}
	
	public static void main(String args[]) {
		Spell test = new Spell();
		test.setName("Pool of Bones");
		test.setSchool(Magic.School.UNIVERSAL, Magic.School.Subschool.NONE);
		System.out.println(test.toCode());
	}
	
}
