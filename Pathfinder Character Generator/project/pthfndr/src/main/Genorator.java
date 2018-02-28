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
		System.out.println(fixForName("ACID = 0, AIR = 1, CHAOTIC = 2, COLD = 3, DARKNESS = 4, DEATH = 5, EARTH = 6, ELECTRICITY = 7, EVIL = 8, FEAR = 9, FIRE = 10, FORCE = 11, GOOD = 12, LANGUAGE_DEPENDENT = 13, LAWFUL = 14, LIGHT = 15, MIND_AFFECTING = 16, SONIC = 17, WATER = 18"));
	}

}
