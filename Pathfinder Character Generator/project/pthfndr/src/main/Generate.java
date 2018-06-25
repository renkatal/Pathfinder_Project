package pthfndr.src.main;
import java.util.ArrayList;
import java.util.Scanner;

import pthfndr.src.main.Spell.Aim;
import pthfndr.src.main.Spell.Aim.Effect;
import pthfndr.src.main.Spell.Component;
import pthfndr.src.main.Spell.Range;

public class Generate implements Magic {
 
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
			if (!java.lang.Character.isAlphabetic(hold[i])) {
				if (hold[i] == ' ' && java.lang.Character.isLowerCase(hold[i+1])) {
					hold[i+1] = java.lang.Character.toUpperCase(hold[i+1]);
				}
				i++;
			}
				
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
	
	public static Item itemCreator(boolean basic) {
		Item value = new Item();
		if (basic) {
			System.out.println("Name:");
			value.setName(input.nextLine());
			System.out.println("Cost:");
			value.setCost(input.nextDouble());
			input.nextLine();
		}
		return value;
	}
	
	public static Spell spellCreator() {
		String name; 
		byte[] school = {0,0};
		boolean[] descriptor = new boolean[18]; 
		byte level; 
		Component components;
		int castingTime = 0;
		Range range = null;
		Aim aim;
		System.out.println("Name:");
		name = input.nextLine();
		System.out.println("School: \n ABJURATION = 0, CONJURATION = 1, DIVINATION = 2, ENCHANTMENT = 3\n ILLUSION = 4, NECROMANCY = 5, TRANSMUTATION = 6, UNIVERSAL = 7");
		school[0] = input.nextByte();
		input.nextLine();
		switch(school[0]) {
		case School.CONJURATION:
			System.out.println("Subschool:");
			for(int i = 0; i < Name.Magic.subschool.conjuaration.length; i++) {
				System.out.print(" " + i + " - ");
				if (i == 0) {
					System.out.println("None");
				}
				else {
					System.out.println(Name.Magic.subschool.conjuaration[i]);
				}
			}
			school[1] = input.nextByte();
			break;
		case School.DIVINATION:
			System.out.println("Subschool:");
			for(int i = 0; i < Name.Magic.subschool.divination.length; i++) {
				System.out.print(" " + i + " - ");
				if (i == 0) {
					System.out.println("None");
				}
				else {
					System.out.println(Name.Magic.subschool.divination[i]);
				}
			}
			school[1] = input.nextByte();
			break;
		case School.ENCHANTMENT:
			System.out.println("Subschool:");
			for(int i = 0; i < Name.Magic.subschool.enchantment.length; i++) {
				System.out.print(" " + i + " - ");
				if (i == 0) {
					System.out.println("None");
				}
				else {
					System.out.println(Name.Magic.subschool.enchantment[i]);
				}
			}
			school[1] = input.nextByte();
			break;
		case School.ILLUSION:
			System.out.println("Subschool:");
			for(int i = 0; i < Name.Magic.subschool.illusion.length; i++) {
				System.out.print(" " + i + " - ");
				if (i == 0) {
					System.out.println("None");
				}
				else {
					System.out.println(Name.Magic.subschool.illusion[i]);
				}
			}
			school[1] = input.nextByte();
			break;
		case School.TRANSMUTATION:
			System.out.println("Subschool:");
			for(int i = 0; i < Name.Magic.subschool.transmuation.length; i++) {
				System.out.print(" " + i + " - ");
				if (i == 0) {
					System.out.println("None");
				}
				else {
					System.out.println(Name.Magic.subschool.transmuation[i]);
				}
			}
			school[1] = input.nextByte();
			break;
		default:
			break;
		}
		input.nextLine();
		System.out.println("Descriptor y/n:");
		String x = input.nextLine();
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y') {
			numericList(Name.Magic.descriptor);
			boolean flag = true;
			while(flag) {
				int desc = input.nextInt();
				input.nextLine();
				descriptor[desc] = true;
				System.out.println("Additonal Descriptor y/n:");
				x = input.nextLine();
				if (x.charAt(0) != 'y' || x.charAt(0) != 'Y') {
					flag = false;
				}
			}
		}
		System.out.println("Level :");
		level = input.nextByte();
		input.nextLine();
		boolean verbal;
		boolean somatic;
		ArrayList<Item> material = new ArrayList<>();
		Item focus = null;
		boolean devineFocus;
		System.out.println("Components :\n Verbal :");
		verbal = input.nextBoolean();
		input.nextLine();
		System.out.println(" Somatic :");
		somatic = input.nextBoolean();
		input.nextLine();
		System.out.println(" Material y/n:");
		x = input.nextLine();
		boolean flag = false;
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y')
			flag = true;
		while (flag) {
			material.add(itemCreator(true));
			System.out.println(" Additonal material y/n:");
			x = input.nextLine();
			if (x.charAt(0) != 'y' || x.charAt(0) != 'Y') {
				flag = false;
			}
		}
		System.out.println(" Focus y/n:");
		x = input.nextLine();
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y')
			focus = itemCreator(true);
		System.out.println(" Devine Focus:");
		devineFocus = input.nextBoolean();
		input.nextLine();
		System.out.println("Casting Time:\n 1 - Standard Action\n 2 - Full Round \n 3 - Minutes\n 4 - Hours");
		int castCheck = input.nextInt();
		input.nextLine();
		switch (castCheck) {
		case 1:
			castingTime = Time.STANDARD_ACTION;
			break;
		case 2:
			System.out.println("  Number of Rounds:");
			castingTime = Time.FULL_ROUND * input.nextInt();
			input.nextLine();
			break;
		case 3:
			System.out.println("  Number of Minutes:");
			castingTime = Time.MINUTE * input.nextInt();
			input.nextLine();
			break;
		case 4:
			System.out.println("  Number of Hours:");
			castingTime = Time.HOUR * input.nextInt();
			input.nextLine();
			break;
		default:
			break;
		}
		System.out.println("Range:\n 0 - Personal\n 1 - Touch\n 2 - Close\n 3 - Medium\n 4 - Long\n 5 - Unlimited 6 - in Feet");
		int rangeTest = input.nextInt();
		input.nextLine();
		if(rangeTest < 6) {
			range = new Range(rangeTest);
		}
		else{
			System.out.println("  Range in feet:");
			int rangeInFeet = input.nextInt();
			input.nextLine();
			range = new Range(rangeInFeet);	
		}
		System.out.println("Aim :\n Effect\n  0 - General\n  1 - Ray\n  2 - Spread");
		Spell.Aim.Effect effect = new Effect();
		int effectTest = input.nextInt();
		input.nextLine();
		switch(effectTest) {
		case 0:
			//might have to add more to this part later 6-24-18
			break;
		case 1:
			effect = new Spell.Aim.Effect.Ray();
			break;
		case 2:
			System.out.println("   Radius:");
			int radius = input.nextInt();
			input.nextLine();
			System.out.println("   Height:");
			int height = input.nextInt();
			input.nextLine();
			effect = new Spell.Aim.Effect.Spread(radius, height);
			break;
		default:
			break;
		}
		Spell.Aim.Area area = null;
		if (!range.isPersonal() && !range.isTouch()) {
			System.out.println("Area of effect? true or false");
			boolean areaFlag = boolTryCatch();
			if(areaFlag){
				System.out.println(" Area\n  0 - Burst, 1 - Emmination, 2 - Spread");
				int bes = intTryCatch(0,2);
				input.nextLine();
				System.out.println("  0 - Cone, 1 - Cylinder, 2 - Line, 3 - Sphere");
				int shape = intTryCatch(0,3);
				input.nextLine();
				System.out.println("  Affects living creatures:");
				boolean livingCreatures = boolTryCatch();
				System.out.println("  Affects all creatures:");
				boolean creatures = boolTryCatch();
				System.out.println("  Affects Objects:");
				boolean objects = boolTryCatch();
				System.out.println("  is shapable:");
				boolean shapable = boolTryCatch();
				input.nextLine();
				area = new Spell.Aim.Area(bes, shape, creatures, livingCreatures, objects, shapable);
			}
		}
		aim = new Aim(effect, area);
		components = new Component(verbal, somatic, material, focus, devineFocus);
		Spell inputSpell = new Spell(name, school, descriptor, level, components, castingTime, range, aim);
		return inputSpell;
	}
	
	public static boolean boolTryCatch() {
		boolean flag = false;
		boolean value = false;
		do {
			try {
				value = input.nextBoolean(); 
			} catch (Exception e) {
				System.out.println("Invalid Entry - Please try again:");
				flag = true;
			}
		} while(flag);
		return value;
	}
	public static int intTryCatch(int min, int max) {
		boolean flag = false;
		int value = min;
		do {
			try {
				value = input.nextInt();
				if (value < min || value > max) {
					System.out.println("Invalid Entry - Please try again:");
					flag = true;
				}
			} catch (Exception e) {
				System.out.println("Invalid Entry - Please try again:");
				flag = true;
			}
		} while(flag);
		return value;
	}
	
	public static String spellCodeGenration() {
		ArrayList<Spell> spellList = new ArrayList<>();
		String value = "";
		boolean flag = true;
		while(flag) {
			spellList.add(spellCreator());
			System.out.println("Next Spell : Y\n");
			String check = input.nextLine();
			if (check.charAt(0) == 'n' || check.charAt(0) == 'N') {
				flag = false;
				value += "\n\n";
			}
		}
		ArrayList<Item> componentList = new ArrayList<>();		
		for( int i = 0; i < spellList.size(); i++) {
			value += spellList.get(i).toCode() + "\n";
			componentList.addAll(0, spellList.get(i).getComponents().getMaterial());
		}
		value += "~~~ Spell Components ~~~";
		for( int i = 0; i < componentList.size(); i++) {
			value += Item.SpellComponent.componentCode(componentList.get(i));
		}
		return value;
	}
	
	public static String arrayToString(boolean[] input) {
		String c = ", ";
		if (input == null)
			return "{}";
		String value = "{";
		for (int i = 0; i < input.length; i ++) {
			value += input[i];
			if (i != input.length - 1) {
				value += c;
			}
		}
		return value + "}";
	}
	public static String arrayToString(int[] input) {
		String c = ", ";
		if (input == null)
			return "{}";
		String value = "{";
		for (int i = 0; i < input.length; i ++) {
			value += input[i];
			if (i != input.length - 1) {
				value += c;
			}
		}
		return value + "}";
	}
	public static String arrayToString(short[] input) {
		String c = ", ";
		if (input == null)
			return "{}";
		String value = "{";
		for (int i = 0; i < input.length; i ++) {
			value += input[i];
			if (i != input.length - 1) {
				value += c;
			}
		}
		return value + "}";
	}
	public static String arrayToString(byte[] input) {
		String c = ", ";
		if (input == null)
			return "{}";
		String value = "{";
		for (int i = 0; i < input.length; i ++) {
			value += input[i];
			if (i != input.length - 1) {
				value += c;
			}
		}
		return value + "}";
	}
	public static String arrayToString(float[] input) {
		String c = ", ";
		if (input == null)
			return "{}";
		String value = "{";
		for (int i = 0; i < input.length; i ++) {
			value += input[i];
			if (i != input.length - 1) {
				value += c;
			}
		}
		return value + "}";
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
	
	public static void numericList(String[] list) {
		for(int i = 0; i < list.length; i++) {
			System.out.print(" " + i + " - ");
			System.out.println(list[i]);
		}
	}
	
	public static interface Test {
		Creature bob = new Creature();
		Sheild testSheild = Sheild.masterwork(Sheild.List.sheildLightWooden);
	}
	public static void main(String[] args) {
		spellCodeGenration();
	}

}
