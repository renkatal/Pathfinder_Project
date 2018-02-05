package pthfndr.src.main;
public class Encumbrance {
public static final int LIGHT = 0, MEDIUM = 1, HEAVY = 2, OVER = 3;
public int check(Creature creature){
	double weight  = creature.getInventoryWeight();
	int maxLoad = maxLoad(creature);
	if (weight > maxLoad)
		return OVER;
	else if ( weight > 2/3*(maxLoad))
		return HEAVY;
	else if ( weight > 1/3*(maxLoad))
		return MEDIUM;
	return LIGHT;
}
public void setSpeed(Creature creature)
{
	int speed = creature.getSpeed();
	if (speed == 30 && check(creature) > LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false && creature.getSpeedMod() == 0)
	{
		creature.setSpeedMod(-10);
	}
	else if (speed == 20 && check(creature) > LIGHT && creature.getSubtypeValue(Subtype.DWARF) == false && creature.getSpeedMod() == 0)
	{
		creature.setSpeedMod(-5);
	}
}
public int maxDex(Creature creature)
{
	if (check(creature) == HEAVY)
	{
		return Math.min(creature.dexBonus(), 1);
	}
	else if (check(creature) == MEDIUM)
	{
		return Math.min(creature.dexBonus(), 3);
	}
	return creature.dexBonus();
	
}
public boolean lift(Creature creature, Item item)
{
	double weight = item.getWeight();
	int maxLoad = maxLoad(creature);
	if (weight + creature.getInventoryWeight() < 2* maxLoad)
	{ 
		creature.pickUpItem(item);
		return true;
	}
	return false;
	
}
public boolean drag(Creature creature, Item item)
{
	double weight = item.getWeight();
	int maxLoad = maxLoad(creature);
	if (weight < 5 * maxLoad)
	{ return true; }
	return false;
}
public static int maxLoad(Creature creature){
	int x = creature.getStat(Stat.STR);
	int sum = 0;
	int multi = 0;
	while (x >= 30)
	{ x = x - 10; multi +=1; }
	for (int i = 0; i < x; i ++)
		{
			sum += 10; // 10
			if (i >= 10)
				sum += 5; //15
			if (i >= 12)
				sum += 5; //20
			if (i >= 13)
				sum += 5; //25
			if (i >= 15) 	
				sum += 5; // 30
			if (i >= 17) 	
				sum += 10; //40
			if (i >= 18) 	
				sum += 10; // 50
			if (i >= 20) 	
				sum += 10; // 60
			if (i >= 22) 	
				sum += 20; // 80
			if (i >= 23 ) 	
				sum += 20; // 100
			if (i >= 25 ) 	
				sum += 20; // 120
			if (i >= 27 ) 	
				sum += 40; // 160
			if (i >= 28 )
				sum += 40; // 200
			
			
		}
	while (multi > 0){
		sum = sum * 4;
		multi --;
	}
		return sum;
}		 
			
public static void main(String[] args)
{
	
}
}