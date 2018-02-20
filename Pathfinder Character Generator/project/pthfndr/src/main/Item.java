package pthfndr.src.main;

import java.util.ArrayList;

public class Item implements Size {
	public interface Condition 
	{
		public final int PERFECT = 0, GOOD = 1, FAIR = 2, POOR = 3, BROKEN = 5, DESTROYED = 6;
	}
	public Item()
	{
		
	}
	public Item(String name, int Size, int Material, double cost, double weight, double thickness)
	{
		this.setName(name);
		this.setItemSize(Size);
		this.setThickness(thickness);
		this.setMaterial(Material);
		this.setCost(cost);
		this.setWeight(weight);
		this.currentHP = this.maxHP;
	}
	private String name;
	private int armorClass; // items own armor class
	private int currentHP; // current hit points of the item
	private int maxHP; // maximum hit points of the item, detemined by its material
	private int hardness; // items resistance to damage
	private double cost; // cost in gold pieces
	private double weight; // weight of item in pounds
	private int condition; //good , broken, destroyed, detemined by current hit points.
	private int itemSize; //for item size NOT character size
	private int material; // item material, determines hardness and hitpoints
	private double thickness; // items thickness in inces for use to determine hitpoints
	private boolean held; // if the item is being held by a creature
	private int age = 0; // how old the item is in years
	private int dexMod = -5; // dex mod of an unattended item
	private int accumulatedDamage; // holds accumulated damage for the aging fucntion
	private boolean stackable;
	private boolean massable;
	
	public class Container extends Item {

		public Container(String name, int Size, int Material, double cost, double weight, double thickness, double capacity) {
			super(name, Size, Material, cost, weight, thickness);
			this.setCapacity(capacity);
		}
		
		private ArrayList<Item> contents = new ArrayList<>();
		private double capacity;
		
		public double getCapacity() {
			return this.capacity;
		}
		public void setCapacity(double capacity) {
			this.capacity = capacity;
		}
		
		public ArrayList<Item> getContents() {
			return this.contents;
		}
		public void setContents(ArrayList<Item> contents) {
			this.contents = contents;
		}
		public void storeItem(Item item) {
			this.contents.add(item);
		}
		public void removeItem(Item item) {
			for (int i = 0; i < this.contents.size(); i ++)
				if (item.equals(this.contents.get(i)))
				{
					this.contents.remove(i);
					return;
				}
		}
		
	}
	
	public class Stack extends Item {

		public Stack(Item item, int count) {
			super();
			this.setType(item);
			this.setCount(count);
		}
		
		private Item type;
		private int count;
		
		public void setType(Item type){
			this.type = type;
		}
		public Item getType() {
			return this.type;
		}
		
		public void setCount(int count) {
			this.count = count;
		}
		public int getCount() {
			return this.count;
		}
		
		public double getWeight() {
			return this.getType().getWeight() * count;
		}
		
		//Adding and removing methods
		public void addItem(Item item) {
			if(item.equals(type)) {
				this.count ++;
			}
		}
		public void addItem(Stack stack) {
			if(stack.equals(type)) {
				this.count += stack.getCount();
			}
		}
		public Item removeItem(Item item) {
			if(item.equals(type)) {
				this.count --;
			}
			return type;
		}
		public Stack splitStack(int count) {
			Stack split = new Stack(this.type,count);
			this.count -= count;
			return split;
		}
		
	}
	
	public class Mass extends Item {
		
		public Mass(Item type, double quantity) {
			
		}
		private Item type;
		private double quantity;
		
		public void setType(Item type){
			this.type = type;
		}
		public Item getType() {
			return this.type;
		}
		
	}
	
	//name getters and setter
	public void setName(String name) {
		this.name = name;
	}
	public String getName() {
		return name;
	}
	
	//Armor Class getters and setters
	public int getArmorClass() {
		return armorClass;
	}
	public void setArmorClass() {
		this.armorClass = 8 + Size.modifier(itemSize) - this.dexMod ;
	}
	
	//Hit Point getters and setters
	public int getCurrentHP() {
		return currentHP;
	}
	public void damageItem(int damage) {
		this.currentHP -= Math.max((damage - this.hardness),0);
		if (this.currentHP < 0)
			this.currentHP = 0;
	}
	public void repairItem(int repair) {
		if (this.currentHP != this.maxHP)
		this.currentHP += repair;
		if (this.currentHP > this.maxHP)
			this.currentHP = this.maxHP;
	}
	public int getMaxHP() {
		return maxHP;
	}
	public void setMaxHP(int hp) {
		this.maxHP = hp;
	}
	
	//Hardness getters and setters
	public int getHardness() {
		return hardness;
	}
	public void setHardness(int hardness) {
		this.hardness = hardness;
	}

	//Cost getters and setters
	public double getCost() {
		return cost;
	}
	public void setCost(double cost) {
		this.cost = cost;
	}

	//Weight getters and setters
	public double getWeight() {
		return weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}

	//Condition getters and setters
	public int getCondition() {
		return condition;
	}
	public void setCondition(int condition) {
		this.condition = condition;
	}

	//Size getters and setters
	public int getItemSize() {
		return itemSize;
	}
	public void setItemSize(int itemSize) {
		this.itemSize = itemSize;
		if (this.itemSize < Size.FINE)
		{this.itemSize = Size.FINE;}
		else if (this.itemSize > Size.COLOSSAL)
		{this.itemSize = Size.COLOSSAL;}
	}
	
	//Material getters and setters
	public int getMaterial() {
		return material; 
	}
	public void setMaterial(int material) { 
		this.material = material;
		this.hardness = Material.hardness(material);
		this.setMaxHP(Material.hitPoints(material, thickness));
	}
	
	//Thickness getters and setters
	public double getThickness()
	{ return thickness;	}
	
	public void setThickness(double thickness)
	{ this.thickness = thickness;}
	
	//boolean value getters and setters
	public void setStackable(boolean value) {
		this.stackable = value;
	}
	public boolean isStackable() {
		return this.stackable;
	}
	public void setMassable(boolean value) {
		this.massable = value;
	}
	public boolean isMassable() {
		return massable;
	}
	
	public void held(Creature creature)
	{
		if (this.held = false)
		{
			this.held = true;
			this.dexMod = Stat.bonus(creature.getStat(Stat.DEX));
			this.armorClass += 2;
			
		}
	}
	public void dropped()
	{
		if(this.held = true)
		{
			this.held = false;
			this.dexMod = -5;
			this.armorClass -= 2;
		}
	}
	public boolean getHeld()
	{
		return held;
	}
	public int getAge()
	{
		return age;
	}
	public void setAge(int age)
	{
		ageItem(this,age);
	}
	public boolean ageTest()
	{
		int roll = Roll.minnimum(5 + Material.hardness(material), 1);
		int test = Roll.d(roll);
		if (test == roll)
			return true;
		return false;
	}
	
	public void ageItem(Item item, int years)
	{
		int ageNow = item.age;
		while(item.age < ageNow + years)
		{ 	
			
			item.age ++;
			if (ageTest() == true)
			{
				
				item.accumulatedDamage += 1;
				if (item.accumulatedDamage > item.hardness && item.currentHP > 0)
					{
						damageItem(accumulatedDamage);
						item.accumulatedDamage = 0;
					}
			}
			System.out.println("Age : " + item.age + " HP : " + item.currentHP);
			
		}

			
			
			
	}
	public static int testAgeing(Item item)
	{
		while(item.currentHP > 0 && item.age < 10000000)
			item.ageItem(item,1);
		return item.age;
	}


	public static void main(String[] args) {
		Item test = new Item("test",Size.DIMINUTIVE,Material.IRON,30,20,1);
	
		while (test.currentHP > 0)
		test.ageItem(test, 1);
		
		
		
		//System.out.println(Item.testAgeing(test));
		// TODO Auto-generated method stub

	}

}
