package pthfndr.src.main;


public interface Type {
	public static final int ABERRATION = 0, ANIMAL = 1, CONSTRUCT = 2, DRAGON = 3, FEY = 4, HUMANOID = 5, MAGICAL_BEAST = 6, MONSTEROUS_HUMANOID = 7, OOZE = 8, OUTSIDER = 9, PLANT = 10, UNDEAD =11, VERMIN = 12;

	public interface Damage {
		public static final int NONE = -1, PIERCING = 0, BLUDGEONING = 1, SLASHING = 2, ADAMANTINE = 3, SILVER = 4, COLD_IRON = 5, MAGIC = 6, EPIC = 7, GOOD = 8, EVIL = 9, CHAOS = 10, LAW = 11;
	}
	
	public interface Energy {
		public static final int POSITIVE = 0, NEGTIVE = 1;
	}
	
	public static Special[] abberrationTraits = new Special[] {new Special.ExtraordinaryAblitiy.DarkVision(60)};
	
	public static boolean[][] breathEatSleep = {{},{true,true,true},{false,false,false},{true,true,true},{/*fey*/},{true,true,true},{/**/},{/**/},{/**/},{/**/},{/**/},{/**/},{/**/}};

}
