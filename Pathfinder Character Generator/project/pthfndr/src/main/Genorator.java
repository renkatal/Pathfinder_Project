package pthfndr.src.main;

public class Genorator {
 
	public static String fixForName(String fix) {
		String value = "\"";
		char[] hold = new char[fix.length()];
		for (int i = 0; i < hold.length; i ++) {
			hold[i] = fix.charAt(i);
		}
		for (int i = 0; i < hold.length; i ++) {
			if(testUpper(hold[i])) { 
				if(i == 0 || hold[i-1] == ' ') {
					value += hold[i];
				}
				else
				{
					value += java.lang.Character.toLowerCase(hold[i]);
				}
			}
			else if (hold[i] == ',')
			{
				value += "\", \"";
			}
			else {
			}
		}
		value += "\"";
		return value;
	}
	public static boolean testUpper(char x) {
		if(x >= 'A' && x <= 'Z')
			return true;
		else
			return false;
	}
	
	public static void main(String[] args) {
		System.out.println(fixForName("BLEED = 0, BLINDED = 1, BROKEN = 2, CONFUSED = 3, COWERING = 4, DAZED = 5, DAZZLED = 6, DEAD = 7, DEAFENED = 8, DISABLED = 9, DYING = 10, ENERGY_DRAINED = 11, ENTANGLED = 12, EXHAUSTED = 13, FASCINATED = 14, FATIGUED = 15, FLAT_FOOTED = 16, FRIGHTENED = 17, GRAPPLED = 18, HELPLESS = 19, INCORPOREAL = 20, INVISIBLE = 21, NAUSEATED = 22, PANICKED = 23, PARALYZED = 24, PETRIFIED = 25, PINNED = 26, PRONE = 27, SHAKEN = 28, SICKENED = 29, STABLE = 30, STAGGERED = 31, STUNNED = 32, UNCONSCIOUS = 33, DESTROYED = 34"));
	}

}
