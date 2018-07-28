package pthfndr.src.main;

import java.util.ArrayList;

public class Item implements Size,Material,Condition {
	public Item() {}
	public Item(String name, int Size, int Material, double cost, double weight, double thickness)
	{
		this.setName(name);
		this.setItemSize(Size);
		this.setThickness(thickness);
		this.setMaterial(Material);
		this.setCost(cost);
		this.setWeight(weight);
		this.currentHP = this.maxHP;
		this.setArmorClass();
	}
	public Item( String name, double cost) {
		//Spell component constructor
		this.setName(name);
		this.setCost(cost);
	}
	private String name;
	private int armorClass; // items own armor class
	private int currentHP; // current hit points of the item
	private int maxHP; // maximum hit points of the item, detemined by its material
	private int hardness; // items resistance to damage
	private double cost; // cost in gold pieces
	private static double weight; // weight of item in pounds
	private int condition = -1; //good , broken, destroyed, detemined by current hit points.
	private int itemSize; //for item size NOT character size
	private int material; // item material, determines hardness and hitpoints
	private double thickness; // items thickness in inces for use to determine hitpoints
	private boolean held; // if the item is being held by a creature
	private int age = 0; // how old the item is in years
	private int dexMod = -5; // dex mod of an unattended item
	private int accumulatedDamage; // holds accumulated damage for the aging fucntion
	private boolean stackable;
	private boolean massable;
	private boolean tradeGood;
	private boolean masterwork;
	private int slot;
	
	public static class Container extends Item {

		public Container(String name, int Size, int Material, double cost, double weight, double thickness, double capacity) {
			super(name, Size, Material, cost, weight, thickness);
			this.setCapacity(capacity);
			this.setMassable(false);
			this.setTrade(false);
			this.setStackable(false);
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
		public double getWeight() {
			double value = weight;
			if (contents.get(0) != null)
			{
				for(int i = 0; i < contents.size(); i++) {
					weight += contents.get(i).getWeight();
				}
			}
			return value;
		}
		
		//static containers
		public static final Container backpack = new Container("Backpack", Size.TINY, LEATHER, 2.0, 2, 1, 40.0);
		
	}
	
	public class Stack extends Item {

		public Stack(Item item, int count) {
			super();
			this.setStackable(false);
			this.setMassable(false);
			this.setTrade(item.isTradeGood());
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
			this.setType(type);
			this.setQuantity(quantity);
		}
		private Item type;
		private double quantity;
		
		public void setType(Item type){
			this.type = type;
		}
		public Item getType() {
			return this.type;
		}
		public void setQuantity(double quantity) {
			this.quantity = quantity;
		}
		public double getQuantity() {
			return this.quantity;
		}

		public Mass pile(Item item) {
			if(item.isMassable()) {
				return new Mass(item,item.getWeight());
			}
			return null;
		}
		public Mass splitMass(Mass mass, double quantity) {
			mass.reduceMass(quantity);
			return new Mass(mass.getType(), quantity);
		}
		public void reduceMass(double quantity) {
			if (this.getQuantity() >= quantity) {
				this.setQuantity(this.getQuantity() - quantity);
			}
		}
		public void increaseMass(double quantity) {
			this.setQuantity(this.getQuantity() + quantity);
		}
		public double getWeight() {
			return this.quantity * this.type.getWeight();
		}
		
	}
	
	public static class Coin extends Item {
		public Coin(int material) {
			super(Name.material[material] + " Coin",FINE,material,value(material),0.02,Material.coinThickness(material));
			this.setTrade(true);
			this.setStackable(true);
			this.setMassable(false);
			
		}
		public static double value(int material) {
			switch (material) {
			case COPPER:
				return 0.01;
			case SILVER:
				return 0.1;
			case GOLD:
				return 1.0;
			case PLATINUM:
				return 10.0;
			default:
				break;
			}
			return 0.0;
		}
		public static final Coin copper = new Coin(COPPER);
		public static final Coin silver = new Coin(SILVER);
		public static final Coin gold = new Coin(GOLD);
		public static final Coin platnium = new Coin(PLATINUM);

		
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
		this.armorClass = 10 + Size.modifier(itemSize) + this.dexMod ;
	}
	
	//Hit Point getters and setters
	public int getCurrentHP() {
		return currentHP;
	}
	public void damageItem(int damage) {
		this.currentHP -= Math.max((damage - this.hardness),0);
		if (this.currentHP < 0) {
			this.currentHP = 0;
		}
		this.setCondition();
	}
	public void repairItem(int repair) {
		if (this.currentHP != this.maxHP)
		this.currentHP += repair;
		if (this.currentHP > this.maxHP) 
			this.currentHP = this.maxHP;
		this.setCondition();
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
	public void setWeight(double value) {
		weight = value;
	}

	//Condition getters and setters
	public int getCondition() {
		return condition;
	}
	public void setCondition() {
		if(this.getCurrentHP() >= 0.5 * this.getMaxHP()) {
			this.removeCondition(BROKEN);
		}
		else if(this.getCurrentHP() < 0.5 * this.getCurrentHP()) {
			this.addCondition(BROKEN);
		}
		else if(this.currentHP == 0) {
			this.addCondition(DESTROYED);
		}
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
	
	//trade good methods
	public void setTrade(boolean value) {
		this.tradeGood = value;
	}
	public boolean isTradeGood() {
		return tradeGood;
	}
	
	public void setMasterwork(boolean masterwork) {
		this.masterwork = masterwork;
	}
	public boolean isMasterwork() {
		return masterwork;
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
	
	public void setSlot(int slot) {
		this.slot = slot;
	}
	public int getSlot() {
		return this.slot;
	}
	
	public interface SpellComponent {
		public static String componentCode(Item item) {
			String value = "Item " + Generate.codeName(item.name) + " = new Item(\"" + item.getName() + "\", " + item.getCost() + ");";
			return value;
		}
		
		Item batGuano = new Item("Bat Guano", 0.0);
		Item rubarbLeaf = new Item("Rubarb Leaf", 0.0);
		Item addersStomach = new Item("Adder's Stomach", 0.0);
		Item powderedPeas = new Item("Powdered Peas", 0.0);
		Item animalHoof = new Item("Animal Hoof", 0.0);
		
		public interface Focus {
			Item dart = new Item("Dart", 0.0);
		}
	}


	public static void main(String[] args) {
	
		

		System.out.println(Coin.copper.getArmorClass());
		System.out.println(Coin.silver.getThickness());
		System.out.println(Coin.gold.getThickness());
		System.out.println(Coin.platnium.getThickness());

		
		
		//System.out.println(Item.testAgeing(test));
		// TODO Auto-generated method stub

	}
	@Override
	public void addCondition(int condition) {
		this.condition = condition;
		
	}
	@Override
	public void removeCondition(int condition) {
		if (this.condition == condition) {
			this.condition = -1;
		}
		
	}

}
