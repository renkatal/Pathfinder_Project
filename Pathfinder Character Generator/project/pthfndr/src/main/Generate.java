package pthfndr.src.main;
import java.util.ArrayList;
import java.util.Scanner;

import pthfndr.src.main.Spell.Aim;
import pthfndr.src.main.Spell.Aim.Effect;
import pthfndr.src.main.Spell.Component;
import pthfndr.src.main.Spell.Duration.SavingThrow;
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
		int[] level = {-1,-1,-1,-1,-1};
		Component components;
		int castingTime = 0;
		Range range = null;
		Aim aim;
		Spell.Duration duration = null;
		SavingThrow save = new SavingThrow();
		boolean spellResistance = false;
		
		//Name
		System.out.println("Name:");
		name = input.nextLine();
		//School, subschool, description
		System.out.println("School: ");
		numericList(Name.Magic.school, 1, 3);
		school[0] = (byte) intTryCatch(0, 7);
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
			school[1] = (byte) intTryCatch(0,Name.Magic.subschool.conjuaration.length-1);
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
			school[1] = (byte) intTryCatch(0,Name.Magic.subschool.divination.length-1);
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
			school[1] = (byte) intTryCatch(0,Name.Magic.subschool.enchantment.length-1);
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
			school[1] = (byte) intTryCatch(0,Name.Magic.subschool.illusion.length-1);
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
			school[1] = (byte) intTryCatch(0,Name.Magic.subschool.transmuation.length-1);
			break;
		default:
			break;
		}
		System.out.println("Descriptor y/n:");
		String x = input.nextLine();
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y') {
			boolean flag = true;
			while(flag) {
				numericList(Name.Magic.descriptor,1,3);
				int desc = intTryCatch(0, Name.Magic.descriptor.length-1);
				descriptor[desc] = true;
				System.out.println("Additonal Descriptor y/n:");
				x = input.nextLine();
				if (x.charAt(0) != 'y' || x.charAt(0) != 'Y') {
					flag = false;
				}
			}
		}
		boolean casterTypeFlag = false;
		do {
		System.out.println("Caster Type:");
		String[] casterNames = {Name.Class.PC[Class.PC.BARD],Name.Class.PC[Class.PC.CLERIC],Name.Class.PC[Class.PC.DRUID],Name.Class.PC[Class.PC.PALADIN],Name.Class.PC[Class.PC.RANGER],Name.Class.PC[Class.PC.WIZARD] + "/" + Name.Class.PC[Class.PC.SCORCERER]};
		numericList(casterNames,1,3);
		int casterType = intTryCatch(0, 5);
		System.out.println("Spell level:");
		int spellLevel = intTryCatch(0,9);
		level[casterType] = spellLevel;
		System.out.println("Additonal Caster Type? T/F");
		boolean check = boolTryCatch();
		casterTypeFlag = check;
		} while (casterTypeFlag);
		//Casting time
		System.out.println("Casting Time:\n 1 - Standard Action\n 2 - Full Round \n 3 - Minutes\n 4 - Hours");
		int castCheck = input.nextInt();
		switch (castCheck) {
		case 1:
			castingTime = Time.STANDARD_ACTION;
			break;
		case 2:
			System.out.println("  Number of Rounds:");
			castingTime = Time.FULL_ROUND * intTryCatch(0);
			break;
		case 3:
			System.out.println("  Number of Minutes:");
			castingTime = Time.MINUTE * intTryCatch(0);
			input.nextLine();
			break;
		case 4:
			System.out.println("  Number of Hours:");
			castingTime = Time.HOUR * intTryCatch(0);
			break;
		default:
			break;
		}
		boolean verbal;
		boolean somatic;
		ArrayList<Item> material = new ArrayList<>();
		Item focus = null;
		boolean devineFocus;
		System.out.println("Components :\n Verbal :");
		verbal = boolTryCatch();
		System.out.println(" Somatic :");
		somatic = boolTryCatch();
		System.out.println(" Material y/n:");
		x = input.nextLine();
		boolean flag = false;
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y')
			flag = true;
		while (flag) {
			material.add(itemCreator(true));
			System.out.println(" Additonal material y/n:");
			x = input.nextLine();
			if (x.charAt(0) == 'n' || x.charAt(0) == 'N') {
				flag = false;
			}
		}
		System.out.println(" Focus y/n:");
		x = input.nextLine();
		if (x.charAt(0) == 'y' || x.charAt(0) == 'Y')
			focus = itemCreator(true);
		System.out.println(" Devine Focus:");
		devineFocus = boolTryCatch();
		
		System.out.println("Range:\n 0 - Personal\n 1 - Touch\n 2 - Close\n 3 - Medium\n 4 - Long\n 5 - Unlimited 6 - in Feet");
		int rangeTest = intTryCatch(0,6);
		if(rangeTest < 6) {
			range = new Range(rangeTest);
		}
		else{
			System.out.println("  Range in feet:");
			int rangeInFeet = intTryCatch(0);
			
			range = new Range(rangeInFeet);	
		}
		System.out.println("Aim :\n Effect\n  0 - General\n  1 - Ray\n  2 - Spread");
		Spell.Aim.Effect effect = new Effect();
		int effectTest = intTryCatch(0, 2);
		switch(effectTest) {
		case 0:
			//might have to add more to this part later 6-24-18
			break;
		case 1:
			effect = new Spell.Aim.Effect.Ray();
			break;
		case 2:
			System.out.println("   Radius:");
			int radius = intTryCatch(0);
			System.out.println("   Height:");
			int height = intTryCatch(0);
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
				System.out.println("  0 - Cone, 1 - Cylinder, 2 - Line, 3 - Sphere");
				int shape = intTryCatch(0,3);
				System.out.println("  Affects living creatures:");
				boolean livingCreatures = boolTryCatch();
				System.out.println("  Affects all creatures:");
				boolean creatures = boolTryCatch();
				System.out.println("  Affects Objects:");
				boolean objects = boolTryCatch();
				System.out.println("  is shapable:");
				boolean shapable = boolTryCatch();
				area = new Spell.Aim.Area(bes, shape, creatures, livingCreatures, objects, shapable);
			}
		}
		
		aim = new Aim(effect, area);
		components = new Component(verbal, somatic, material, focus, devineFocus);
		
		System.out.println("Saving throw T/F");
		boolean saveFlag = boolTryCatch();
		boolean[] fort = new boolean[4] , ref = new boolean[4], will = new boolean[4];
		boolean harmless = false;
		if(saveFlag) {
			System.out.println(" Fort T/F");
			boolean fortFlag = boolTryCatch();
			if(fortFlag)
				fort = inputSaveTypes();
			System.out.println(" Ref T/F");
			boolean refFlag = boolTryCatch();
			if(refFlag)
				ref = inputSaveTypes();
			System.out.println(" Will T/F");
			boolean willFlag = boolTryCatch();
			if(willFlag)
				will = inputSaveTypes();
			save = new SavingThrow(new boolean[][] {fort,ref,will}, harmless); 
		}
		System.out.println("  Harmless T/F");
		save.setHarmless(boolTryCatch());
		System.out.println("Spell Resistance T/F");
		spellResistance = boolTryCatch();
		
		
		Spell inputSpell = new Spell(name, school, descriptor, level, components, castingTime, range, aim, duration, save, spellResistance);
		return inputSpell;
	}
	
	public static boolean[] inputSaveTypes() {
		boolean[] value = {false,false,false,false};
		// Negates = 0, Partial = 1, Half = 2, Disbelief = 3;
		System.out.println("    Negates T/F");
		value[0] = boolTryCatch();
		System.out.println("    Partial T/F");
		value[1] = boolTryCatch();
		System.out.println("    Half T/F");
		value[2] = boolTryCatch();
		System.out.println("    Disbelief T/F");
		value[3] = boolTryCatch();
		return value;
	}
	
	//Input verification Functions
	public static boolean boolTryCatch() {
		boolean value = false;
		try {
			value = stringToBool();
		} catch (Exception e) {
			System.out.println("Invalid Entry - Please try again:");
			value = boolTryCatch();
			
		}
		return value;
	}
	
	public static boolean stringToBool() {
		String in = input.nextLine();
		if(in.charAt(0) == 't' || in.charAt(0) == 'T' || in.charAt(0) == 'y' || in.charAt(0) == 'Y') {
			return true;
		}
		return false;
		
	}
	
	public static int intTryCatch(int min, int max) {
		boolean flag = true;
		int value = min;
		do {
			try {
				value = input.nextInt();
				input.nextLine();
				if (value < min || value > max) {
					System.out.println("Invalid Entry - Please try again:");
					intTryCatch(min, max);
				}
				else { flag = false; }
			} catch (Exception e) {
				System.out.println("Invalid Entry - Please try again:");
			}
		} while(flag);
		return value;
	}
	
	public static int intTryCatch(int min) {
		boolean flag = true;
		int value = min;
		do {
			try {
				value = input.nextInt();
				input.nextLine();
				if (value < min ) {
					System.out.println("Invalid Entry - Please try again:");
				} else { flag = false;}
			} catch (Exception e) {
				System.out.println("Invalid Entry - Please try again:");
			}
		} while(flag);
		return value;
	}

	//Spell code list generator
	public static void spellCodeGenration() {
		ArrayList<Spell> spellList = new ArrayList<>();
		String value = "";
		boolean flag = true;
		while(flag) {
			spellList.add(spellCreator());
			System.out.println("Next Spell : Y/N");
			String check = input.nextLine();
			if (check.charAt(0) == 'n' || check.charAt(0) == 'N') {
				flag = false;
				value += "\n\n";
			}
		}
		ArrayList<Item> componentList = new ArrayList<>();		
		ArrayList<Item> focusList = new ArrayList<>();
		for( int i = 0; i < spellList.size(); i++) {
			value += spellList.get(i).toCode() + "\n\n";
			componentList.addAll(0, spellList.get(i).getComponents().getMaterial());
			focusList.add(spellList.get(i).getComponents().getFocus());
		}
		if(componentList.get(0) != null) {
			value += "~~~ Spell Material Components ~~~\n\n";
			for( int i = 0; i < componentList.size(); i++) {
				value += Item.SpellComponent.componentCode(componentList.get(i)) + "\n";
			}
			value += "\n";
		}
		if(focusList.get(0) != null) {
			value += "~~~ Spell Focus Components ~~~\n\n";
			for( int i = 0; i < focusList.size(); i++) {
				value += Item.SpellComponent.componentCode(focusList.get(i)) + "\n";
			}
			value += "\n";
		}
		System.out.println(value);
	}
	
	//Array string generators
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
	
	public static int[] arrayListToIntArray(ArrayList<Byte> input) {
		int[] value = new int [input.size()];
		for(int i = 0; i < value.length; i++) {
			value[i] = input.get(i);
		}
		return value;
		
	}
	//Armor Code generation method
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
	
	//Armor code list generator
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
	
	//Numeric list generator - from string
	public static void numericList(String[] list, int spacesIn, int itemsPerLine) {
		int count = 0;
		for(int i = 0; i < list.length; i++) {
			if(spacesIn > 0 && count == 0) {
				for(int j = 0; j < spacesIn; j++) {
					System.out.print(" ");
				}
			}
			System.out.print( i + " - " + list[i]);
			count++;
			if (count == itemsPerLine || i == list.length -1) {
				System.out.println("");
				count = 0;
			}else {
				System.out.print(",  ");
			}
		}
	}
	

	public static void main(String[] args) {
	 Test.creature.addSpecial(new Special.ExtraordinaryAblitiy.SpellResistance(20));
	 Test.spell.setSpellResistance(true);
	 boolean flag = true;
	 int count = 1;
	 while(flag) {
		flag = Test.spell.spellResistanceCheck(Test.wizard, Class.PC.WIZARD, Test.creature);
		System.out.println(flag + " " +count/*Special.check(Test.creature, Special.List.spellResistance));*/);
		count ++;
		}
	 //spellCodeGenration();
	}

}
