package pthfndr.src.main;
public class Skill {
	public static final byte	ACROBATICS = 0, APPRAISE = 1, BLUFF = 2, // int values of skills for use in arrays
						CLIMB = 3, CRAFT = 4, DIPLOMACY = 5, 
						DISABLE_DEVICE = 6, DISGUISE = 7, ESCAPE_ARTIST = 8, 
						FLY = 9, HANDLE_ANIMAL = 10, HEAL = 11, 
						INTIMIDATE = 12, KNOWLEDGE = 13, LINGUISTICS = 14,
						PERCEPTION = 15, PREFORM = 16, PROFFESSION = 17,
						RIDE = 18, SENSE_MOTIVE = 19, SLEIGHT_OF_HAND = 20,
						SPELLCRAFT = 21, STEALTH = 22, SURVIVAL = 23,
						SWIM = 24, USE_MAGIC_DEVICE = 25;
	public static final int	RANK = 0, BONUS = 1, RACE_BONUS = 2;
	public int check(int skill, Creature creature) // regular skill check
	{ return Stat.bonus(stat(skill)) + creature.getSkillRank(skill);} // need to add class skill bonus and racial modifyers, and feat bonus.
	public int check(int skill, int subSkill, Creature creature) // skill(subskill) check
	{ return Stat.bonus(stat(skill)) + creature.getSkillRank(skill,subSkill);} // ditto
	
	public static boolean untrained(int skill, Class classPC)
	{
		return false;
	};
	public int stat(int skill) // returns the stat array position value associated with a skill, for use in other methods.
	{
		if ( 0 >= skill && skill <= 25)
		{
			if (skill == CLIMB || skill == SWIM)
			{ return Stat.STR; }
			else if (skill == ACROBATICS || skill == DISABLE_DEVICE || skill == ESCAPE_ARTIST || skill == FLY || skill == RIDE || skill == SLEIGHT_OF_HAND || skill == STEALTH)
			{ return Stat.DEX;}
			else if ( skill == APPRAISE || skill == CRAFT || skill == KNOWLEDGE || skill == LINGUISTICS || skill == SPELLCRAFT)
			{ return Stat.INT;}
			else if (skill == HEAL || skill == PERCEPTION || skill == PROFFESSION || skill == SENSE_MOTIVE || skill == SURVIVAL)
			{ return Stat.WIS;}
			else
			{return Stat.CHA;}
		}
		else
			Junk.error();
			return -1;
	}
	public static class craft
	{
		public static final int  alchemy = 0, armor = 1, baskets = 2, books= 3, bows= 4, calligraphy= 5, carpentry = 6, cloth = 7, clothing = 8, glass = 9, jewelry = 10, leather = 11, locks = 12, paintings = 13, pottery = 14, sculptures = 15, ships = 16, shoes = 17, stonemasonry = 18, traps = 19, weapons = 20;
	}
	public static class knowledge
	{
		public static final int arcana = 0, dungeoneering = 1, engineering = 2, geography = 3, history = 4, local = 5, nature = 6, nobility = 7, planes = 8, religion = 9;
	}
	public static class profession
	{
		public static final int architect = 0, baker = 1, barrister = 2, brewer = 3, butcher = 4, clerk = 5, cook = 6, courtesan = 7, driver = 8, engineer = 9, farmer = 10, fisherman = 11, gambler = 12, gardener  = 13, herbalist = 14, innkeeper = 15, librarian = 16, merchant = 17, midwife = 18, miller = 19, miner = 20, porter = 21, sailor = 22, scribe = 23, shepherd = 24, stable_master = 25, soldier = 26, tanner = 27, trapper = 28, woodcutter = 29;
	}
	public static void main(String[] args) {
		System.out.print("");

	}

}