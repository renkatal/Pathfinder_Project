package pthfndr.src.main;

public class Armor extends Item {

	private static double thicness;
	
	public Armor() {
		
	}
	
	public Armor(String name, int type, int ACbonus, int maxDex, int checkPenalty, int spellFalure, double weight, double cost, int material, int creatureSize, int creatureType, boolean masterwork)
	{
		
		super(name,creatureSize-1,material,cost,weight,thicness); // Item constructor
		double[] sizeCostMod = {1/2,1/2,1/2,1,1,2,4,8,16};  //cost mod for creature size
		double[] sizeWeightMod = {1/10,1/10,1/10,1/2,1,2,5,8,12}; // weight mod for creature size
		this.setCost((int)(cost * sizeCostMod[creatureSize]));
		this.creatureType = creatureType;
		if (creatureType != Type.HUMANOID) // if creature is not humanoid set cost * 2
			this.setCost(this.getCost() * 2);
		this.type = type;
		this.armorClassBonus = ACbonus;
		this.maxDexBonus = maxDex;
		this.armorCheckPenelty = checkPenalty;
		this.arcaneSpellFalureChance = spellFalure;
		this.creatureSize = creatureSize;
		this.setWeight(weight * sizeWeightMod[creatureSize]); //sets armor weight 
		this.setMasterwork(masterwork);
		if (masterwork == true)
		{
			this.setCost(getCost() + 150);
			if (this.armorCheckPenelty > 0)
			this.armorCheckPenelty -= 1;
		}
	}
	interface ArmorType 
	{
		public static final int NONE = -1, LIGHT = 0, MEDIUM = 1, HEAVY = 2;
		public static String name(int type) {
			String value = "";
			switch (type) {
			case NONE:
				value = "None";
				break;
			case LIGHT:
				value = "Light";
				break;
			case MEDIUM:
				value = "Medium";
				break;
			case HEAVY:
				value = "Heavy";
				break;
			default:
				break;
			}
			return value;
		}
	}
	private int type;
	private int armorClassBonus;
	private int maxDexBonus;
	private int armorCheckPenelty;
	private int arcaneSpellFalureChance;
	private int creatureSize; // the size of creature that the item is intended for
	private int creatureType; // the creature body type that the armor is for
	private void speed(Creature creature)
	{
		int speed = creature.getSpeed();
		if (speed == 30 && type > ArmorType.LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false )
		{
			creature.setSpeedMod(-10);
		}
		else if (speed == 20 && type > ArmorType.LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false )
		{
			creature.setSpeedMod(-5);
		}
	}
	
	public void setType(int type) {
		this.type = type;
	}
	public int getType() {
		return this.type;
	}
	
	public int getArmorClassBonus() {
		if (this.creatureSize <= Size.TINY)
			return armorClassBonus/2;
		return armorClassBonus;
	}
	public int getMaxDex() {
		return this.maxDexBonus;
	}
	public void setArcaneSpellFalure(int chance) {
		this.arcaneSpellFalureChance = chance;
	}
	public int getArcaneSpellFailure() {
		return this.arcaneSpellFalureChance;
	}
	public int getArmorCheckPenalty() {
		return this.armorCheckPenelty;
	}
	public void setCreatureSize(int size) {
		this.creatureSize = size;	
	}
	public int getCreatureSize() {
		return this.creatureSize;
	}
	public void setCreatureType(int creatureType) {
		this.creatureType = creatureType;
	}
	public int getCreatureType() {
		return this.creatureType;
	}

	public void equip(Armor armor, Creature creature) {
		if (this.creatureSize == creature.getSize()) {
			creature.donArmor(armor);
			armor.speed(creature);
		}
		else 
			System.out.println("This armor will not fit you.");
	}
	
	public String toCode() {
		String c = ", ";
		return "Armor " + Generate.codeName(this.getName()) + " = new Armor(\"" + this.getName() + "\"" + c + "ArmorType." + Generate.finalIntName(ArmorType.name(this.getType())) + c + this.getArmorClassBonus() + c 
				+ this.getMaxDex() + c + this.getArmorCheckPenalty() + c + this.getArcaneSpellFailure() + c + this.getWeight() + c + this.getCost() + c + "Material." + Generate.finalIntName(Name.material[this.getMaterial()]) + c 
				+ "Size." + Generate.finalIntName(Size.name[this.getCreatureSize()]) + c  + "Type." + Generate.finalIntName(Name.type[this.getCreatureType()]) + c + this.isMasterwork() + ");"; 
	}
	
	public static Armor None(Creature creature)
	{
		Armor None = new Armor("None", ArmorType.NONE, 0, -1, 0, 0, 0, 0, Material.NONE, creature.getSize(), creature.getType(), false);
		return None;
	}
	
	public static interface Humanoid {
		public static interface Medium {
			// Light Armor
			Armor padded = new Armor("Padded", ArmorType.LIGHT, 1, 8, 0, 5, 10.0, 5.0, Material.CLOTH, Size.MEDIUM, Type.HUMANOID, false);
			Armor leather = new Armor("Leather", ArmorType.LIGHT, 2, 6, 0, 10, 15.0, 10.0, Material.LEATHER, Size.MEDIUM, Type.HUMANOID, false);
			Armor studdedLeather = new Armor("Studded Leather", ArmorType.LIGHT, 3, 5, 1, 15, 20.0, 25.0, Material.LEATHER, Size.MEDIUM, Type.HUMANOID, false);
			Armor chainShirt = new Armor("Chain Shirt", ArmorType.LIGHT, 4, 4, 2, 20, 25.0, 100.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			//Medium Armor
			Armor hide = new Armor("Hide", ArmorType.MEDIUM, 4, 4, 3, 20, 25.0, 15.0, Material.HIDE, Size.MEDIUM, Type.HUMANOID, false);
			Armor scaleMail = new Armor("Scale Mail", ArmorType.MEDIUM, 5, 3, 4, 25, 30.0, 50.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			Armor chainmail = new Armor("Chainmail", ArmorType.MEDIUM, 6, 2, 5, 30, 40.0, 150.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			Armor breastplate = new Armor("Breastplate", ArmorType.MEDIUM, 6, 3, 4, 25, 30.0, 200.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			//Heavy Armor
			Armor splintMail = new Armor("Splint Mail", ArmorType.HEAVY, 7, 0, 7, 40, 45.0, 200.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			Armor bandedMail = new Armor("Banded Mail", ArmorType.HEAVY, 7, 1, 6, 35, 35.0, 250.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			Armor halfPlate = new Armor("Half-plate", ArmorType.HEAVY, 8, 0, 7, 40, 50.0, 600.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			Armor fullPlate = new Armor("Full Plate", ArmorType.HEAVY, 9, 1, 6, 35, 50.0, 1500.0, Material.STEEL, Size.MEDIUM, Type.HUMANOID, false);
			
		}
		public static interface Small {
			// Light Armor
			Armor padded = new Armor("Padded", ArmorType.LIGHT, 1, 8, 0, 5, 10.0, 5.0, Material.CLOTH, Size.SMALL, Type.HUMANOID, false);
			Armor leather = new Armor("Leather", ArmorType.LIGHT, 2, 6, 0, 10, 15.0, 10.0, Material.LEATHER, Size.SMALL, Type.HUMANOID, false);
			Armor studdedLeather = new Armor("Studded Leather", ArmorType.LIGHT, 3, 5, 1, 15, 20.0, 25.0, Material.LEATHER, Size.SMALL, Type.HUMANOID, false);
			Armor chainShirt = new Armor("Chain Shirt", ArmorType.LIGHT, 4, 4, 2, 20, 25.0, 100.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			//Medium Armor
			Armor hide = new Armor("Hide", ArmorType.MEDIUM, 4, 4, 3, 20, 25.0, 15.0, Material.HIDE, Size.SMALL, Type.HUMANOID, false);
			Armor scaleMail = new Armor("Scale Mail", ArmorType.MEDIUM, 5, 3, 4, 25, 30.0, 50.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			Armor chainmail = new Armor("Chainmail", ArmorType.MEDIUM, 6, 2, 5, 30, 40.0, 150.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			Armor breastplate = new Armor("Breastplate", ArmorType.MEDIUM, 6, 3, 4, 25, 30.0, 200.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			//Heavy Armor
			Armor splintMail = new Armor("Splint Mail", ArmorType.HEAVY, 7, 0, 7, 40, 45.0, 200.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			Armor bandedMail = new Armor("Banded Mail", ArmorType.HEAVY, 7, 1, 6, 35, 35.0, 250.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			Armor halfPlate = new Armor("Half-plate", ArmorType.HEAVY, 8, 0, 7, 40, 50.0, 600.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			Armor fullPlate = new Armor("Full Plate", ArmorType.HEAVY, 9, 1, 6, 35, 50.0, 1500.0, Material.STEEL, Size.SMALL, Type.HUMANOID, false);
			
		}
	}
	//Armor Leather = new Armor("Leather",ArmorType.LIGHT,2,6,0,10,15,10,Material.LEATHER,Size.MEDIUM,Type.HUMANOID,false);
	
	public static void main(String[] args) {
		System.out.println();
	}
	
}