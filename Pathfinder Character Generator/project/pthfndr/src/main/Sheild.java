package pthfndr.src.main;

import java.util.ArrayList;

public class Sheild extends Item {
	private static double thicness;
	public Sheild(String name, int type, int ACbonus, int maxDex, int checkPenalty, int spellFalure, double weight, double cost, int material, int creatureSize, boolean masterwork)
	{
		
		super(name,creatureSize-2,material,cost,weight,thicness); // Item constructor
		double[] sizeCostMod = {1/2,1/2,1/2,1,1,2,4,8,16};  //cost mod for creature size
		double[] sizeWeightMod = {1/10,1/10,1/10,1/2,1,2,5,8,12}; // weight mod for creature size
		this.setCost((int)(cost * sizeCostMod[creatureSize]));
		this.setName(name);
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
	public static interface SheildType {
		public static final int LIGHT = 0, HEAVY = 1;
	}
	private String name = "";
	private int type;
	private int armorClassBonus;
	private int maxDexBonus;
	private int armorCheckPenelty;
	private int arcaneSpellFalureChance;
	private int creatureSize; // the size of creature that the item is intended for
	private int magic;
	private ArrayList<Special> abilities;
	public String getName() { 
		String value = "";
		if(this.isMasterwork() && magic < 1)
			value += "Masterwork ";
		value += this.name;
		return value;
	}
	public void setName(String name) {
		this.name = name;
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
	public int getSheildType() {
		return this.type;
	}
	public int getArmorCheckPenalty() {
		return this.armorCheckPenelty;
	}
	public int getCreatureSize() {
		return this.creatureSize;
	}
	
	public ArrayList<Special> getAbilities() {
		return this.abilities;
	}
	public void setAbilites(ArrayList<Special> abilities) {
		this.abilities = abilities;
	}
	public void addAbility(Special ability) {
		this.abilities.add(ability);
	}
	
	public static Sheild masterwork(Sheild sheild) {
		return new Sheild(sheild.getName(), sheild.getSheildType(), sheild.getArmorClassBonus(), sheild.getMaxDex(), sheild.getArmorCheckPenalty(), sheild.getArcaneSpellFailure(), sheild.getWeight(), sheild.getCost(), sheild.getMaterial(), sheild.getCreatureSize(), true);
	}
	public static Sheild magic(Sheild sheild, int magic, ArrayList<Special> abilities) {
		Sheild value = masterwork(sheild);
		
		return value;
	}
	public static Sheild changeSize(Sheild sheild, int size) {
		Sheild hold = sheild;
		hold.setCreatureSize(size);
		if(size > Size.FINE + 1) {
			hold.setItemSize(size-2);
		}
		else {
			hold.setItemSize(Size.FINE);
		}
		return hold;
	}
	
	public void equip(Sheild sheild, Creature creature, int appendage)
	{
		if (creature.checkHolding(appendage) == false)
		{
			creature.holdItem(appendage, sheild);
			creature.setSheild(sheild);
		}
	}
	
	public boolean equals(Sheild sheild) {
		if (this.getName() == sheild.getName() || this.getName() == Sheild.masterwork(sheild).getName())
			return true;
		return false;
	}
	
	public static interface List {
		Sheild buckler = new Sheild("Buckler", SheildType.LIGHT, 1, -1, 1, 5, 5, 15, Material.STEEL, Size.MEDIUM, false);
		Sheild sheildLightWooden = new Sheild("Light Wooden Sheild", SheildType.LIGHT, 1, -1, 1, 5, 5, 3, Material.WOOD, Size.MEDIUM, false);
		Sheild sheildLightSteel = new Sheild("Light Steel Sheild", SheildType.LIGHT, 1, -1, 1, 5, 6, 9, Material.STEEL, Size.MEDIUM, false);
		Sheild sheildHeavyWood = new Sheild("Heavy Wood Sheild", SheildType.HEAVY, 2, -1, 2, 15, 10, 7, Material.WOOD, Size.MEDIUM, false);
		Sheild sheildHeavySteel = new Sheild("Heavy Steel Sheild", SheildType.HEAVY, 2, -1, 2, 15, 15, 20, Material.STEEL, Size.MEDIUM, false);
		Sheild sheildTower = new Sheild("Tower Sheild", SheildType.HEAVY, 4, 2, 10, 50, 45, 30, Material.WOOD, Size.MEDIUM, false);
	}	

}
