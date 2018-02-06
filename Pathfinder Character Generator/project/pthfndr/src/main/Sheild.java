package pthfndr.src.main;
public class Sheild extends Item{
	private static double thicness;
	public Sheild(String name, int type, int ACbonus, int maxDex, int checkPenalty, int spellFalure, int weight, int cost, int material, int creatureSize, int creatureType, boolean masterwork)
	{
		
		super(name,creatureSize-2,material,cost,weight,thicness); // Item constructor
		double[] sizeCostMod = {1/2,1/2,1/2,1,1,2,4,8,16};  //cost mod for creature size
		double[] sizeWeightMod = {1/10,1/10,1/10,1/2,1,2,5,8,12}; // weight mod for creature size
		this.setCost((int)(cost * sizeCostMod[creatureSize]));
		if (creatureType != Type.HUMANOID) // if creature is not humanoid set cost * 2
			this.setCost(this.getCost() * 2);
		this.name = name;
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
	class SheildType 
	{
		public static final int LIGHT = 0, MEDIUM = 1, HEAVY = 3;
	}
	private String name = "";
	private int type;
	private int armorClassBonus;
	private int maxDexBonus;
	private int armorCheckPenelty;
	private int arcaneSpellFalureChance;
	private int creatureSize; // the size of creature that the item is intended for
	private void speed(Creature creature)  // need to fix for shild !!!!!!!!!!!!!!!!!
	{
		int speed = creature.getSpeed();
		if (speed == 30 && type != SheildType.LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false )
		{
			creature.setSpeedMod(-10);
		}
		else if (speed == 20 && type != SheildType.LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false )
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
		this.setItemSize(size - 1);
	}

	public void equip(Sheild sheild, Creature creature, int appendage)
	{
		if (creature.checkHolding(appendage) == false)
		{
			creature.holdItem(appendage, sheild);
			creature.setShield(sheild);
			sheild.speed(creature);
		}
	}

}
