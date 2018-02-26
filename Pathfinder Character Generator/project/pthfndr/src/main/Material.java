package pthfndr.src.main;

public interface Material {
	public static final int NONE = -1, GLASS = 0, PAPER = 1, CLOTH = 2, ROPE = 3, ICE = 4, LEATHER = 5, HIDE = 6, WOOD = 7, STONE = 8, IRON = 9, STEEL = 10, MITHRAL = 11, ADAMANTINE = 12, COPPER = 13, SILVER = 14, GOLD = 15, PLATINUM = 16;
	public static int hardness(int material)
	{
		int hard = 0;
		switch(material)
		{
		case PAPER: case CLOTH: case ROPE: case ICE:
			hard = 0; break;
		case GLASS:
			hard = 1; break;
		case LEATHER: case HIDE:
			hard = 2; break;
		case GOLD:
			hard = 3; break;
		case SILVER:
			hard = 4; break;
		case WOOD:
			hard = 5; break;
		case COPPER:
			hard = 6; break;
		case STONE: case PLATINUM:
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
		case COPPER: case SILVER: case GOLD: case PLATINUM:
			hp = 10; break;
		case IRON: case STEEL: case MITHRAL:
			hp = 30; break;
		case ADAMANTINE:
			hp = 40; break;
		}
		if((int)(hp * thickness) < 1)
			return 1;
		return (int)(hp * thickness);
	}
	public static double cubicInchesPerPound(int material) {
		switch(material)
		{
		case PLATINUM:
			return 1.29;
		case GOLD:
			return 1.44;
		case SILVER:
			return 2.64;
		case COPPER:
			return 3.09;
		case IRON:
			return 3.57;
		case GLASS:
			return 11.11;
		}
		return 0.0;
	}
	public static double surfaceAreaCircle(double diamiter) {
		double x = 0.5 * diamiter;
		return Math.PI * x * x;
	}
	public static double coinThickness(int material) {
		double surface = surfaceAreaCircle(1.0);
		double coinVolume = cubicInchesPerPound(material) / 50;
		return coinVolume / surface;
	}
	
}
