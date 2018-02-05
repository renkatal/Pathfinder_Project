package pthfndr.src.main;

public class Feat {
	private String name;
	private String description;
	private Feat[] featPrerequisite;
	private int[] statPrerequisite;
	private int prereqCount = 0;
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
			skillsPlus2(int skill1, int skill2)
			{
				if (creature.getSkillRank(skill1) == 10)
					
				creature.setSkillBonus(skill1, 2);
				creature.setSkillBonus(skill2, 2);
			}
			
		}
		public class armorProficentcy
		{
			armorProficentcy(int armorType)
			{
				creature.
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
		featPrerequisite[prereqCount] = feat;
		prereqCount ++;
	}
	public void setBenefit(Benefit benefit)
	{
		this.benefit = benefit;
	}
	
	

}