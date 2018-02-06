package pthfndr.src.main;

public class Race {
	//"Human", "Dwarf", "Elf", "Gnome", "Half-Elf", "Half-Orc", "Halfling"
	public final int HUMAN = 0, DWARF = 1, ELF = 2, GNOME = 3, HALF_ELF = 4, HALF_ORC = 5, HALFLING = 6;
	public final int[][] raceBonus = {{0,0,0,0,0,0},{0,0,2,0,2,-2},{0,2,-2,2,0,0},{-2,0,2,0,0,2},{0,0,0,0,0,0},{0,0,0,0,0,0},{-2,2,0,0,0,2}};
	
	// race constructor
	Race (Character modify, int race)
	{
		modify.setRace(race);
		for(int i = 0; i < 6; i++)
		{
			modify.setStat(i,addBonus(modify,race,i));
		}
		if (race == HUMAN || race == HALF_ELF || race == HALF_ORC)
		{
			int[] hold = humanBonus(modify.getMostImportantStat());
			for(int i = 0; i < 6; i++)
			{
				modify.setStat(i,modify.getStat(i) + hold[i]);
			}
		}
		characterRaceSize(modify, race);
		
	}
	
	public int addBonus(Character modify, int race, int stat)
	{
		return modify.getStat(stat) + raceBonus[race][stat];
	}

	public static int[] humanBonus(int x) {
		int[] temp = {0,0,0,0,0,0};
		temp[x] +=2;
		return temp;
	}
	public void characterRaceSize(Character character,int race)
	{
		if (race == GNOME || race == HALFLING)
		{
			character.setSize(Size.SMALL);
		}
		else 
		{
			character.setSize(Size.MEDIUM);
		}
	}
	public void raceSpeed(Character character, int race)
	{
		
	}
	public static void main(String[] args) {
		
	}
	
}
