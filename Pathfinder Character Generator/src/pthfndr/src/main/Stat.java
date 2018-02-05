package pthfndr.src.main;

public class Stat {
	
	
	public static final int STR = 0, DEX = 1, CON = 2, INT = 3, WIS = 4, CHA = 5; 
	public static int bonus(int stat) // returns the bonus associated with a stat
	{
		int bonus;
		 bonus = (stat > 10) ? ((stat - 10) / 2) : ((stat - 11) / 2);
		
		return bonus;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
			for (int i = 3; i <= 18; i++)
				System.out.println(bonus(i));
	}

}