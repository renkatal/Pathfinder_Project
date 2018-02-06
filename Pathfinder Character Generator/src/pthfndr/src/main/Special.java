package pthfndr.src.main;
public class Special {
	class Attack
	{
		public void AbilityDamage(int stat,Creature target, int damage)
		{
			target.setTempStatAdjusment(stat, Roll.d(damage));
		}
		public void AbilityDrain(int stat,Creature target, int damage)
		{
			target.setTempStatAdjusment(stat, Roll.d(damage));
		}
		public void BreathWeapon(int range, int shape, int damageType)
		{
			
		}
	}
	static class Quality
	{
		
		public static Special Regeneration;
		public void Amphibious(Creature creature)
		{
			creature.breathAir(true);
			creature.breathWater(true);
		}
		public void Blindsense(int range)
		{
			
		}
		public void Blindsight(int range)
		{
			
		}
		public static class Regeneration
		{
			Regeneration(int rate, Creature creature) {
			}
			
		}
		
		
	}
	
	public static boolean check(Creature creature, Special special)
	{
		boolean check = false;
		for (int i = 0; i < creature.specialLength(); i++)
		{
			if (creature.getSpecial(i).equals(special))
			{
				check = true;
			}
		}
		return check;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
