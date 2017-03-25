
class Subtype {
	public static final int AIR = 0, ANGEL = 1, AQUATIC = 2, ARCHON = 3, AUGMENTED = 4, 
			AZATA = 5, CHAOTIC = 6, COLD = 7, DEMON = 8, DEVIL = 9, 
			DWARF = 10, EARTH = 11, ELIMENTAL = 12, ELF = 13, EVIL = 14,
			EXTRAPLANAR = 15, FIRE = 16, GIANT = 17, GNOME = 18, GOBLINOID = 19,
			GOOD = 20, HALFLING = 21, HUMAN = 22, INCORPOREAL = 23, LAWFUL = 24,
			NATIVE = 25, ORC = 26, REPTILIAN = 27, SHAPECHANGER = 28, SWARM = 29,
			WATER = 30, ONI = 31, KYTON = 32, GNOLL = 33, DERRO = 34;
	Subtype(Creature originalCreature, int subtype)
	{
		originalCreature.setSubtype(subtype);
	}
	Subtype(Creature originalCreature, int subtype1, int subtype2)
	{
		originalCreature.setSubtype(subtype1);
		originalCreature.setSubtype(subtype2);
	}
	Subtype(Creature originalCreature, int subtype1, int subtype2, int subtype3)
	{
		originalCreature.setSubtype(subtype1);
		originalCreature.setSubtype(subtype2);
		originalCreature.setSubtype(subtype3); 
	}
	Subtype(Creature originalCreature, int subtype1, int subtype2, int subtype3, int subtype4)
	{
		originalCreature.setSubtype(subtype1);
		originalCreature.setSubtype(subtype2);
		originalCreature.setSubtype(subtype3); 
		originalCreature.setSubtype(subtype4); 
	}
	public static int maxNumber()
	{
		return 35;
	}

}
