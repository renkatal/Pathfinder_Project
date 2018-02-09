package pthfndr.src.main;

import java.util.ArrayList;

public class Spell implements Alignment, Type, Magic, Time {
	
	private String name;
	private byte[] school = new byte[2];
	private boolean[] descriptor = new boolean[19];
	private byte level;
	private Component components;
	private int castingTime;
	private Range range;
	private Aim aim;
	
	public class Component {
		private boolean verbal;
		private boolean somatic;
		private ArrayList<Item> material = new ArrayList<>();
		private Item focus;
		private boolean devineFocus;
	}
	
	public class Range {
		public static final byte PERSONAL = 0, TOUCH = 1, CLOSE = 2, MEDIUM = 3, LONG = 4, UNLIMITED = 5;
		private boolean personal;
		private boolean touch;
		private boolean close;
		private boolean medium;
		private boolean longRange;
		private boolean unlimited;
		private int inFeet;
	}
	
	public class Aim {
		private ArrayList<Object> targets = new ArrayList<>();
		private Effect effect;
		
		public class Effect {
			
			public class Ray extends Effect {
				
			}
			public class Spread extends Effect {
				private int radius;
				private int height;
				
			}
		}
	}

}
