
public class Armor extends Item {
	private static double thicness;
	public Armor(String name, int type, int ACbonus, int maxDex, int checkPenalty, int spellFalure, int weight, int cost, int material, int creatureSize, int creatureType, boolean masterwork)
	{
		
		super(name,creatureSize-1,material,cost,weight,thicness); // Item constructor
		double[] sizeCostMod = {1/2,1/2,1/2,1,1,2,4,8,16};  //cost mod for creature size
		double[] sizeWeightMod = {1/10,1/10,1/10,1/2,1,2,5,8,12}; // weight mod for creature size
		this.setCost((int)(cost * sizeCostMod[creatureSize]));
		if (creatureType != Type.HUMANOID) // if creature is not humanoid set cost * 2
			this.setCost(this.getCost() * 2);
		this.type = type;
		this.armorClassBonus = ACbonus;
		this.maxDexBonus = maxDex;
		this.armorCheckPenelty = checkPenalty;
		this.arcaneSpellFalureChance = spellFalure;
		this.creatureSize = creatureSize;
		this.setWeight(weight * sizeWeightMod[creatureSize]); //sets armor weight 
		if (masterwork == true)
		{
			this.setCost(getCost() + 150);
			if (this.armorCheckPenelty > 0)
			this.armorCheckPenelty -= 1;
		}
	}
	class ArmorType 
	{
		public static final int NONE = -1, LIGHT = 0, MEDIUM = 1, HEAVY = 3;
	}
	private String name = "";
	private int type;
	private int armorClassBonus;
	private int maxDexBonus;
	private int armorCheckPenelty;
	private int arcaneSpellFalureChance;
	private int creatureSize; // the size of creature that the item is intended for
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
	private boolean masterwork;
	public String getName()
	{
		return this.name;
	}
	public void setName(String name)
	{
		this.name = "";
		if (masterwork == true)
			this.name += "Masterwork ";
		this.name += name;
	}
	public int getArmorClassBonus()
	{
		if (this.creatureSize <= Size.TINY)
			return armorClassBonus/2;
		return armorClassBonus;
	}
	public int getMaxDex()
	{
		return this.maxDexBonus;
	}
	public void setArcaneSpellFalure(int chance)
	{
		this.arcaneSpellFalureChance = chance;
	}
	public int getArcaneSpellFailure()
	{
		return this.arcaneSpellFalureChance;
	}
	public void setCreatureSize(int size)
	{
		this.creatureSize = size;	
	}

	public void equip(Armor armor, Creature creature)
	{
		if (this.creatureSize == creature.getSize())
		{
			creature.donArmor(armor);
			armor.speed(creature);
		}
		else 
			System.out.println("This armor will not fit you.");
	}
	public static Armor None(Creature creature)
	{
		Armor None = new Armor("None", ArmorType.NONE, 0, -1, 0, 0, 0, 0, Material.NONE, creature.getSize(), creature.getType(), false);
		return None;
	}
	Armor Padded = new Armor("Padded",ArmorType.LIGHT,1,8,0,5,10,5,Material.CLOTH,Size.MEDIUM,Type.HUMANOID,false);
	//Armor Leather = new Armor("Leather",ArmorType.LIGHT,2,6,0,10,15,10,Material.LEATHER,Size.MEDIUM,Type.HUMANOID,false);
	
}