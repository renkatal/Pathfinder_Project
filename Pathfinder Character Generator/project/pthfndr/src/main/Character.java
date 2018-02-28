package pthfndr.src.main;

import java.util.ArrayList;

public class Character extends Creature implements Race {

	private int race;
	private String name;
	private ArrayList<Class> ClassList;
	private double height;
	private double weight;
	private String eyeColor;
	private String hairColor;
	private byte gender;
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
	
	//name getters and setters
    public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	//class methods
	public void addClass(Class x) {
		this.ClassList.add(x);
	}

	//race getters and setters
	public void setRace(int race) { 
		this.race = race;
		this.characterRaceSize(race);
		this.raceSpeed(race);
	}
	public int getRace()
	{ return this.race;}

	//physical description getters and setters
	public double getWeight() {
		return this.weight;
	}
	public void setWeight(double weight) {
		this.weight = weight;
	}
	public double getHeight() {
		return this.height;
	}
	public void setHeight(double height) {
		this.height = height;
	}
	public String getEyeColor() {
		return this.eyeColor;
	}
	public void setEyeColor(String eyeColor) {
		this.eyeColor = eyeColor;
	}
	public String getHairColor() {
		return this.hairColor;
	}
	public void setHairColor(String hairColor) {
		this.hairColor = hairColor;
	}
	public byte getGender() {
		return this.gender;
	}
	public void setGender(byte gender) {
		this.gender = gender;
	}
	
	//personal information getters and setters
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
		if(race == Race.GNOME || race == Race.HALFLING)
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
		if (race == DWARF || race == Race.GNOME || race == Race.HALFLING)
			this.setSpeed(20);
		else
			this.setSpeed(30);
	}
	
	

}
