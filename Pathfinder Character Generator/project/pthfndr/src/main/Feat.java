package pthfndr.src.main;
import java.util.ArrayList;

public class Feat {
	private String name;
	private String description;
	private ArrayList<Feat> featPrerequisite;
	private int[] statPrerequisite;
	private Benefit benefit;

	public class Benefit 
	{
		Creature creature;
		public Benefit(Creature creature)
		{
			this.creature = creature;
		}
		public class skillsPlus2
		{
			skillsPlus2(byte skill1, byte skill2)
			{
				if (creature.getSkillRank(skill1) == 10)
					
				creature.setSkillBonus(skill1,(byte) 2);
				creature.setSkillBonus(skill2,(byte) 2);
			}
			
		}
		public class armorProficentcy
		{
			armorProficentcy(int armorType)
			{
				creature.setArmorProficiency(armorType);
			}
		}
	}
	public void setName(String name)
	{
		this.name = name;
	}
	public String getName()
	{
		return name;
	}
	public void setDescription(String description)
	{
		this.description = description;
	}
	public String getDescription()
	{
		return description;
	}
	public void setFeatPrerequisite(Feat feat)
	{
		featPrerequisite.add(feat);
	}
	public void setStatPrereq(int stat, int prereq)
	{
		this.statPrerequisite[stat] = prereq;
	}
	public Benefit getBenefit()
	{
		return this.benefit;
	}
	public void setBenefit(Benefit benefit)
	{
		this.benefit = benefit;
	}
	
	

}
