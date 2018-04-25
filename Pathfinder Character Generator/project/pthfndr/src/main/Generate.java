package pthfndr.src.main;
import java.util.ArrayList;
import java.util.Scanner;

public class Generate {
 
	public static Scanner input = new Scanner(System.in);
	
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
	
	public static String codeName(String fix) {
		String value = "";
		char[] hold = new char[fix.length()];
		for (int i = 0; i < hold.length; i ++) {
				hold[i] = fix.charAt(i);
		}
		hold[0] = java.lang.Character.toLowerCase(hold[0]);
		for (int i = 0; i < hold.length; i ++) {
			if (hold[i] == ' ')
				i++;
			value += hold[i];
		}
		return value;
	}
	
	public static String finalIntName(String fix) {
		String value = "";
		char[] hold = new char[fix.length()];
		for (int i = 0; i < hold.length; i ++) {
				hold[i] = java.lang.Character.toUpperCase(fix.charAt(i));
		}
		for (int i = 0; i < hold.length; i ++) {
			if (hold[i] == ' ') {
				value += '_';
				i++;
			}
			value += hold[i];
		}
		return value;
	}
	
	public static String armorCreator() {
		String name;
		int type, ACbonus, maxDex, checkPenalty, spellFalure, material = 0, creatureSize, creatureType;
		double weight, cost;
		boolean masterwork = false;
		System.out.println("Armor Type: \n 0 - Light\n 1 - Medium\n 2 - Heavy");
		type = input.nextInt();
		input.nextLine();
		System.out.println("Armor Name: ");
		name = input.nextLine();
		System.out.println("Cost in gold: ");
		cost = input.nextDouble();
		System.out.println("Armor Class Bonus: ");
		ACbonus = input.nextInt();
		System.out.println("Max Dex: ");
		maxDex = input.nextInt();
		System.out.println("Check Penalty: ");
		checkPenalty = input.nextInt();
		System.out.println("Spell Failure %: ");
		spellFalure = input.nextInt();
		System.out.println("Weight: ");
		weight = input.nextDouble();
		System.out.println("Material: \n Cloth - 0\n Leather - 1\n Hide - 2\n Steel - 3");
		int mat = input.nextInt();
		boolean flag = true;
		while(flag) {
			switch (mat) {
			case 0:
				material = Material.CLOTH;
				flag = false;
				break;
			case 1:
				material = Material.LEATHER;
				flag = false;
				break;
			case 2:
				material = Material.HIDE;
				flag = false;
				break;
			case 3:
				material = Material.STEEL;
				flag = false;
				break;
			default:
				System.out.println("INVALID INPUT");
				System.out.println("Material: Cloth - 0, Leather - 1, Hide - 2, Steel - 3");
				mat = input.nextInt();
				break;
			}
		}
		creatureSize = Size.MEDIUM;
		creatureType = Type.HUMANOID;
		
		Armor inputArmor = new Armor(name, type, ACbonus, maxDex, checkPenalty, spellFalure, weight, cost, material, creatureSize, creatureType, masterwork);
		return inputArmor.toCode();
	}
	public static void armorHelper() {
		ArrayList<String> hold = new ArrayList<>();
		boolean flag = true;
		while (flag) {
			hold.add(armorCreator());
			System.out.println("Next Armor? yes = 0 / n = 1 :");
			int test = input.nextInt();
			if(test != 0) {
				flag = false;
			}
		}
		if (!flag)
			for (int i = 0; i < hold.size(); i ++) {
				System.out.println(hold.get(i));
			}
	}
	
	public static interface Test {
		Creature bob = new Creature();
	}
	
	public static void main(String[] args) {
		armorHelper();
	}

}
