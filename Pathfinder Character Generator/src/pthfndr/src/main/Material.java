package pthfndr.src.main;
public class Material {
	public static final int NONE = -1, GLASS = 0, PAPER = 1, CLOTH = 2, ROPE = 3, ICE = 4, LEATHER = 5, HIDE = 6, WOOD = 7, STONE = 8, IRON = 9, STEEL = 10, MITHRAL = 11, ADAMANTINE = 12;
	public static int hardness(int material)
	{
		int hard = 0;
		switch(material)
		{
		case GLASS:
			hard = 1; break;
		case PAPER: case CLOTH: case ROPE: case ICE:
			hard = 0; break;
		case LEATHER: case HIDE:
			hard = 2; break;
		case WOOD:
			hard = 5; break;
		case STONE:
			hard = 8; break;
		case IRON: case STEEL:
			hard = 10; break;
		case MITHRAL:
			hard = 15; break;
		case ADAMANTINE:
			hard = 20; break;
		}
		return hard;
	}
	public static int hitPoints(int material, double thickness)
	{
		int hp = 0;
		switch(material)
		{
		case GLASS:
			hp = 1; break;
		case PAPER: case CLOTH: case ROPE:
			hp = 2; break;
		case ICE:
			hp = 3; break;
		case LEATHER: case HIDE:
			hp = 5; break;
		case WOOD:
			hp = 10; break;
		case STONE:
			hp = 15; break;
		case IRON: case STEEL: case MITHRAL:
			hp = 30; break;
		case ADAMANTINE:
			hp = 40; break;
		}
		if((int)(hp * thickness) < 1)
			return 1;
		return (int)(hp * thickness);
	}
}