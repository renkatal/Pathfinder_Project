package pthfndr.src.main;

import java.util.ArrayList;

public class Character extends Creature implements Race {

	private int race;
	private String name;
	private ArrayList<Class> ClassList;
	private int totalLevel;
	private boolean alive;
	private int height;
	private int weight;
	private String eyeColor;
	private String hairColor;
	private int gender;
	private Deity deity;
	private String homeland = "None";
	
	
	public Character ()
	{
		
	}
	public Character (String name, int[] stats, int maxHP)
	{
		this.name = name;
		this.setStats(stats);
		
	}
	
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isAlive() {
		return alive;
	}
	public void setAlive(boolean alive) {
		this.alive = alive;
	}
	
	public void getCharInfo()
	{
		
		
	}
	//race getters and setters
	public void setRace(int race)
	{ this.race = race; }
	public int getRace()
	{ return this.race;}

	

	public Deity getDeity() {
		return deity;
	}
	public void setDeity(Deity deity) {
		this.deity = deity;
	}
	public String getHomeland() {
		return homeland;
	}
	public void setHomeland(String homeland) {
		this.homeland = homeland;
	}
	public static void main(String[] args) {
		System.out.print(Name.stat[Stat.STR]);
		
	}
	public void addBonus(int race) {
		for(int i = 0; i < this.getStats().length; i++)
		{
			this.setStat(i, this.getStat(i) + Race.BONUS[race][i]);
		}
		
	}
	@Override
	public void characterRaceSize(int race) {
		if(race == GNOME || race == HALFLING)
		{
			this.setSize(SMALL);
		}
		else
		{ 
			this.setSize(MEDIUM);
		}
		
	}
	@Override
	public void raceSpeed(int race) {
		// TODO Auto-generated method stub
		
	}
	
	

}
