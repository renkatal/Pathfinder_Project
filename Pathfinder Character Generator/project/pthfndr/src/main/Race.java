package pthfndr.src.main;

public interface Race {
	//Basic Races
	public final int HUMAN = 0, DWARF = 1, ELF = 2, GNOME = 3, HALF_ELF = 4, HALF_ORC = 5, HALFLING = 6;
	//Non-basic Races
	public final int AASIMAR = 7;
	public final int[][] BONUS = {{0,0,0,0,0,0},{0,0,2,0,2,-2},{0,2,-2,2,0,0},{-2,0,2,0,0,2},{0,0,0,0,0,0},{0,0,0,0,0,0},{-2,2,0,0,0,2},{0,0,0,0,2,2}};
	
	public void addBonus(int race);
	public void characterRaceSize(int race);
	public void raceSpeed(int race);
	
}

