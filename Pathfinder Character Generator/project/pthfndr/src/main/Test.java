package pthfndr.src.main;

public interface Test {
	//the test interface is for creating test versions of various classes to ensure that methods work correctly.
	
	public static Creature creature = new Creature();
	public static Character wizard = new Character(new Class.PC.Wizard(),5);
	
	public static Spell spell = new Spell();
}
