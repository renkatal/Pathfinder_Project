public class Character extends Creature {

	private int race;
	private String name;
	private int[] classType;
	private int totalLevel;
	private int[] classLevel;
	private boolean alive;
	private int[] importantStats;
	private int height;
	private int weight;
	private String eyeColor;
	private String hairColor;
	private int gender;
	private String deity = "None";
	private String homeland = "None";
	
	
	public Character ()
	{
		this.race = 0;
		this.name = "Generic Character";
	}
	public Character (String name, int strength, int dexterity, int constitution, int intelligence , int wisdom ,int charisma, int currentHP, int maxHP, boolean alive)
	{
		
		
		this.name = name;
		this.setStat(Stat.CON,constitution);
		this.setStat(Stat.INT,intelligence);
	
		
	}
	
	
	public int getStatImportance(int stat)
	{ 
		return importantStats[stat];
	}
	public int getMostImportantStat()
	{
		int tell = 0;
		for (int i =0; i < 6; i++)
		{
			if (this.importantStats[i] == 1)
			{
				tell = i;
			}
		}
		return tell;
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

	

	public static void main(String[] args) {
		System.out.print(Name.stat[Stat.STR]);
		
	}
	
	

}
