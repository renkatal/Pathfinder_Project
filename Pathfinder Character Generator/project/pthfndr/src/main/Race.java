package pthfndr.src.main;

public interface Race {
	//"Human", "Dwarf", "Elf", "Gnome", "Half-Elf", "Half-Orc", "Halfling"
	public final int HUMAN = 0, DWARF = 1, ELF = 2, GNOME = 3, HALF_ELF = 4, HALF_ORC = 5, HALFLING = 6;
	public final int[][] BONUS = {{0,0,0,0,0,0},{0,0,2,0,2,-2},{0,2,-2,2,0,0},{-2,0,2,0,0,2},{0,0,0,0,0,0},{0,0,0,0,0,0},{-2,2,0,0,0,2}};
	public void addBonus(int race);
	public void characterRaceSize(int race);
	public void raceSpeed(int race);
}

