package pthfndr.src.main;
//Creature class 
public class Creature {

	private String creatureName;
	private int type;
	private boolean[] subtypes = new boolean[Subtype.maxNumber()];
	private int size;
	private int[] alignment = new int[2];
	private boolean[] sense;
	private int[] aura;
	private int hitDie;
	private int numHitDie;
	private int maxHP;
	private int currentHP;
	private int nonleathalDmg;
	private int[] savingThrow = new int[3];
	private int[] defensiveAbilities;
	private int[] resistances;
	private boolean[] weaknesses;
	private int[] speed;
	private int speedMod;
	//private weapon[] melee; // weapon class
	//private weapon[] ranged;//
	private int space;
	private int reach;
	private boolean[] breath;
	private int[] specialAttacks;
	private int[] spellLikeAblilites;
	private int[] spellsKnownPrepared;
	private int[] stats = new int[6];
	private int[] tempStatAjustment = {0,0,0,0,0,0};
	private int baseAttack;
	private int combatManuverBonus;
	private int combatManuverDefense;
	private int[] feats;
	private int[] classSkills;
	private byte[][][] skill;
	private int skillPoints;
	private int[][] racialSkillMod;
	private boolean[] Language;
	private int[] specialQualities;
	private boolean[] environment;
	private Item[] inventory;
	private Special[] specialAbilities;
	private boolean conditions;
	private int[] anatomy;
	private Item[] slot;
	private int armorProficiency = Armor.ArmorType.NONE; // sets creatures initial armor proficentcy to none
	private Armor armor = Armor.None(this);
	private Sheild sheild;
	private Item[] hand;
	
	class Slot {
	public static final int HEAD = 0, HEADBAND = 1, EYES = 2, SHOULDERS = 3, NECK = 4, CHEST = 5, ARMOR = 6, BELT = 7, WRISTS = 8, HANDS = 9, RING1 = 10, RING2 = 11;
	}
	class Hand {
		public static final int OFF = 0, MAIN = 1;
	}
	class Anatomy {
		public static final int HEAD = 0, BODY = 1, LEG = 2, FOOT = 3, ARM = 4, HAND = 5, WING = 6, PAW = 7, TENTICLE = 8, EYE = 9, MOUTH = 10, EAR = 11, FIN = 12, HOOF = 13, BEAK = 14, NOSE = 15, CLAW = 16;
	}
	class SavingThrow {
		public static final int FORTITUDE = 0, REFLEX = 1, WILL = 2;
	}
	public Creature()
	{
		this.creatureName = "Human";
		this.type = Type.HUMANOID;
		new Subtype(this, Subtype.HUMAN);
	}

	// creature name getters and setters
	public void setCreatureName(String name)
	{ this.creatureName = name; }
	public String getCreatureName()
	{ return this.creatureName; }

	// type getters and setters
	public void setType(int type)
	{ this.type = type; }
	public int getType()
	{ return type; }
	public String getTypeName()
	{ return Name.type[type];}

	//subtype getters and setters
	public void setSubtype(int subtype)
	{
			this.subtypes[subtype] = true;
	}
	public void removeAllSubtypes()
	{
		for (int i = 0; i < subtypes.length; i++)
		{ this.subtypes[i] = false; }
	}
	public boolean getSubtypeValue(int subtype)
	{
		return subtypes[subtype];
	}

	//size getters and setters
	public int getSize()
	{return size;}
	public void setSize(int size)
	{this.size = size;}
	
	// stats getters and setters
	public int getStat(int stat)
	{
		return this.stats[stat] + this.tempStatAjustment[stat];
	}
	public int getUnajustedStat(int stat)
	{
		return this.stats[stat];
	}
	public int[] getUnajustedStats() {
		return stats;
	}
	public int[] getStats() {
		int[] tell = {0,0,0,0,0,0};
		for (int i = 0; i < 6; i ++)
			tell[i] = stats[i] + tempStatAjustment[i];
		return tell ;
	}
	public void setStats(int[] stats) {
		this.stats = stats;
	}
	public void setStat(int Stat, int newValue)
	{
		this.stats[Stat] = newValue;
	}
	public void stats10()
	{
		for (int i = 0; i < 6; i++)
			this.stats[i] = 10;
	}
	public void statsRandom()
	{
		for (int i = 0; i < 6; i++)
			this.stats[i] = Roll.PCstatRoll();
	}
	public void setTempStatAdjusment(int stat, int adjustment)
	{
		this.tempStatAjustment[stat] = adjustment;
	}
	public int getTempStatAdjustment(int stat)
	{
		return tempStatAjustment[stat];
	}
	
	// Skill rank getters and setters and methods
	public void incrimentSkillRank(byte skill) // increments a skill rank in a skill
	{
		if ( skillPoints > 0 )
			this.skill[Skill.RANK][skill][0] ++;
	}
	public void incrimentSkillRank(byte skill, byte subskill) // incriments a skill rank in a skill(subskill)
	{
		if ( skillPoints > 0)
			this.skill[Skill.RANK][skill][subskill] ++;
	}
	public void levelSkill(byte skill, byte level)
	{
		for (int i = 0; i < level; i++)
		incrimentSkillRank(skill);
	}
	public void levelSkill(byte skill,byte subskill, byte level)
	{
		for (int i = 0; i < level; i++)
		incrimentSkillRank(skill,subskill);
	}
	public int getSkillRank(byte skill)
	{
		return this.skill[Skill.RANK][skill][0];
	}
	public int getSkillRank(byte skill, byte subSkill)
	{
		return this.skill[Skill.RANK][skill][subSkill];
	}
	public int getSkillBonus(byte skill)
	{
		return this.skill[Skill.BONUS][skill][0];
	}
	public int getSkillBonus(byte skill, byte subskill)
	{
		return this.skill[Skill.BONUS][skill][subskill];
	}
	public void setSkillBonus(byte skill, byte amount)
	{
		this.skill[Skill.BONUS][skill][0] = amount;
	}
	public void setSkillBonus(byte skill, byte subskill, byte amount)
	{
		this.skill[Skill.BONUS][skill][subskill] = amount;
	}
	public void setSkillRaceBonus(byte skill, byte amount)
	{
		this.skill[Skill.RACE_BONUS][skill][0] = amount;
	}
	public void setSkillRaceBonus(byte skill, byte subskill, byte amount)
	{
		this.skill[Skill.RACE_BONUS][skill][subskill] = amount;
	}
	public int getSkillRaceBonus(byte skill, byte subskill)
	{
		return (int) this.skill[Skill.RACE_BONUS][skill][subskill];
	}
	
	//alignment getters and setters
	public String getAligment()
	{
		String order = "", moral = "";
		if (alignment[0] > 100 && alignment[0] <=200 && alignment[1] > 100 && alignment[1] <=200)
		{	return "True Nutral";}
		if (alignment[0] <= 100)
				order = Name.Alignment.chaotic;
			else if(alignment[0] <= 200)
				order = Name.Alignment.neutral;
			else if (alignment[0] <= 300)
				order = Name.Alignment.lawful;
			if (alignment[1] <= 100)
				moral = Name.Alignment.evil;
			else if (alignment[1] <= 200) 
				moral = Name.Alignment.neutral;
			else if (alignment[1] <= 300) 
				moral = Name.Alignment.good;
			return order + " "  + moral;
		}
	public void shiftAligment(int type,int shift) // shift character alignment on type axis by shift points
		{
			this.alignment[type] += shift;
		}
	public void setAligment(String order, String moral) // hard sets aligment value in the middle of their respecitve values
		{
			if( order == "Lawful" || order == "lawful")
			{ this.alignment[0] = 250;}
			else if (order == "Chaotic" || order == "chaotic")
			{ this.alignment[0] = 50;}
			else
			{ this.alignment[0] = 150;}
			
			if( order == "Good" || moral == "good")
			{ this.alignment[1] = 250;}
			else if (order == "Evil" || order == "evil")
			{ this.alignment[1] = 50;}
			else
			{ this.alignment[1] = 150;}
		}

	//sense getters and setters
	public boolean getSense(int sense)
	{ return this.sense[sense]; }
	public void setSense(int sense, boolean x)
	{ this.sense[sense] = x;}

	//aura getters and setters
	public void setAura(int aura, int strenght)
	{
		this.aura[aura] = strenght;
	}
	public int getAuraStrenght(int aura)
	{
		return this.aura[aura];
	}
	
	//Hit dice and hit points getters and setters
	public int getNumHitDie()
	{
		return this.numHitDie;
	}
	public void setNumHitDie(int num)
	{ this.numHitDie = num; }
	public void incrimentNumHitDie(int num)
	{ this.numHitDie += num; }
	public int getHitDie()
	{ return this.hitDie; }
	public void setHitDie(int dieSize)
	{ this.hitDie = dieSize; }
	public int getCurrentHP() {
		return currentHP;
	}
	public void setCurrentHP(int currentHP) {
		this.currentHP = (currentHP > this.maxHP) ? this.maxHP : currentHP;
	}
	
	//armor class getters and setters
	public int armorClass()
	{
		return 10 + this.armor.getArmorClassBonus() + this.sheild.getArmorClassBonus() + dexBonus() + Size.modifier(size) + defensiveAbilities[Defense.NATURAL_ARMOR] + defensiveAbilities[Defense.DEFLECTION] + defensiveAbilities[Defense.DODGE];
	}
	public int flatFootedAC()
	{
		return armorClass() - dexBonus() - defensiveAbilities[Defense.DODGE];
	}
	public int tochAC()
	{
		return armorClass() - this.armor.getArmorClassBonus() - this.sheild.getArmorClassBonus() - this.defensiveAbilities[Defense.NATURAL_ARMOR];
	}

	//hit points getters and setters and methods related to them
	public int statBonusHP(int num)
	{	if (this.type == Type.UNDEAD) 
			return Stat.bonus(this.stats[Stat.CHA]) * num;
		return Stat.bonus(this.stats[Stat.CON]) * num;}
	private int hitPointCalc()
	{	int hold = Roll.d(this.hitDie) + statBonusHP(1);
		if (hold < 1)
			return 1;
		return hold;
	}
	private int rollHitPoints(int num)
	{
		int sum = 0;
		for( int i = 0; i <= num; i++)
		{
			sum += hitPointCalc();
		}
		return sum;
			
	}
	public int getMaxHP() 
	{	return maxHP; }
	public void setMaxHP(int maxHP) 
	{
		if ( maxHP <= (this.hitDie * this.numHitDie) + statBonusHP(this.numHitDie))
			{this.maxHP = maxHP;}
		else { maximumMaxHP(); }
	}
	public void randomMaxHP()
	{ 	this.maxHP = rollHitPoints(this.numHitDie);}
	public void standardMaxHP()
	{	this.maxHP = this.hitDie + Stat.bonus(this.stats[Stat.CON]) + rollHitPoints(this.numHitDie - 1) ;}
	public void maximumMaxHP()
	{	this.maxHP = this.hitDie * this.numHitDie + statBonusHP(this.numHitDie);}
	public int getNonleathelDMG()
	{
		return nonleathalDmg;
	}
	public void setNonLeathalDMG(int damage)
	{
		if  (Special.check(this, Special.Quality.Regeneration) == false && this.nonleathalDmg == this.maxHP)
		{	this.currentHP -= damage;}
		else
		{	this.nonleathalDmg += damage;}
	}
	
	//saving throw getters and setters
	public int getSavingThrow(int type)
	{
		return savingThrow[type];
	}
	public void setSavingThrow(int type, int value)
	{
		this.savingThrow[type] = value;
	}
	
	//resistances getters and setters
	public int getResistance(int type)
	{
		return resistances[type];
	}
	public void setResistance(int type, int value)
	{
		this.resistances[type] = value;
	}
	
	//weakness getters and setters
	public boolean getWeakness(int type)
	{
		return weaknesses[type];
	}
	public void setWeakness(int type, boolean x)
	{
		this.weaknesses[type] = x;
	}
	
	//Special methods
	public int specialLength()
	{
		return specialAbilities.length;
	}
	public void newSpecial(Special special)
	{
		this.specialAbilities[specialLength()] = special;
	}
	public Special getSpecial(int x)
	{
		return specialAbilities[x];
	}
	
	//breath getters and setters
	public boolean getBreath(int type)
	{
		return this.breath[type];
	}
	public void breathAir(boolean x)
	{
		this.breath[0] = x;
	}
	public void breathWater(boolean x)
	{
		this.breath[1] = x;
	}
	
	//speed getters and setters
	public void setSpeed(int speed)
	{
		this.speed[0] = speed;
	}
	public void setSwimSpeed(int speed)
	{
		this.speed[1] = speed;
	}
	public void setBurrowSpeed(int speed)
	{ 	this.speed[2] = speed;}
	public void setFlySpeed (int speed, int manuvarability)
	{
		this.speed[3] = speed;
		this.speed[4] = manuvarability;
	}
	public int getSpeed()
	{
		return Roll.minnimum(this.speed[0] + this.speedMod,5);
	}
	public void setSpeedMod(int mod)
	{
		this.speedMod = mod;
	}
	public int getSpeedMod()
	{
		return speedMod;
	}
	
	//armor getters and setters
	public Armor getArmor()
	{
		return this.armor;
	}
	public void donArmor(Armor armor)
	{
		this.armor = armor;
	}
	public int dexBonus()
	{
		if (armor.getMaxDex() != -1 || armor.getMaxDex() < Stat.bonus(stats[Stat.DEX]))
			return Stat.bonus(stats[Stat.DEX]);
		return armor.getMaxDex();
		
	}
	
	//intventory Methods
	public int getInventroySize()
	{
		return inventory.length;
	}
	public double getInventoryWeight()
	{
		int weight = 0;
		for ( int i = 0; i < getInventroySize(); i++)
		{
			if (inventory[i] != null)
			weight += inventory[i].getWeight();
		}
		return weight;
	}
	public void pickUpItem(Item item)
	{
		item.held(this);
		this.inventory[inventory.length] = item;
		
	}
	public void dropItem(Item item)
	{
		for (int i = 0; i < inventory.length; i++)
		{
			if (item.equals(inventory[i]))
			{
				item.dropped();
				inventory[i] = null;
				return;
			}
			
		}
	}
	public boolean checkHand(int hand) // check to see if there is an item in the hand that would prevent its use;
	{
		if (this.hand[hand] == null)
		{
			return false;
		}
		return true;
	}
	public void equipHand(int hand, Item item)
	{
		this.hand[hand] = item;
	}

	public static void main(String[] args) {
		

	}
	
}