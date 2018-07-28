package pthfndr.src.main;

public interface Stat {
	
	
	public static final int STR = 0, DEX = 1, CON = 2, INT = 3, WIS = 4, CHA = 5; 
	public static int bonus(int stat) // returns the bonus associated with a stat
	{
		int bonus;
		if(stat == -1) {
			return 0;
		}
		bonus = (stat > 10) ? ((stat - 10) / 2) : ((stat - 11) / 2);
		
		return bonus;
	}
	public static int casterStatBonus(Creature creature, int casterType) {
		switch(casterType) {
			case Class.PC.WIZARD:
				return bonus(creature.getStat(INT));
			case Class.PC.BARD: case Class.PC.PALADIN: case Class.PC.SCORCERER:
				return bonus(creature.getStat(CHA));
			default:
				return bonus(creature.getStat(WIS));
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			for (int i = 3; i <= 18; i++)
				System.out.println(i + ": " + bonus(i));
	}

}
