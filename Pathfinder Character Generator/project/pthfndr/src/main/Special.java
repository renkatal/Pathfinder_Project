package pthfndr.src.main;

import pthfndr.src.main.Magic.Desciptor;

public class Special implements Magic.Desciptor{
	private String name;
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Special(String name) {
		this.setName(name);
	}
	public static class ExtraordinayAblitiy extends Special {
		public ExtraordinayAblitiy(String name) {
			super(name);
		}
		public static class Regeneration extends Special {
			public Regeneration(byte rate) {
				super("Regeneration");
				this.setRate(rate);
			}
			public Regeneration() {
				super("Regeneration");
			}
			private byte rate;
			public byte getRate() {
				return rate;
			}
			public void setRate(byte rate) {
				this.rate = rate;
			}
			public static Special regeneration = new Regeneration();
		}
	}
	
	public class SpellLikeAblitiy extends Special {

		public SpellLikeAblitiy(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
		public class EnergyBurst extends Special {
			public EnergyBurst(byte type){
				super("");
				if(type == FIRE)
				{           }
			}
		}
		
	}
	
	public class SupernaturalAbilitiy extends Special {

		public SupernaturalAbilitiy(String name) {
			super(name);
			// TODO Auto-generated constructor stub
		}
		
	}
	public static boolean check(Creature creature, Special special)
	{
		boolean check = false;
		for (int i = 0; i < creature.specialLength(); i++)
		{
			if (creature.getSpecial(i).equals(special))
			{
				check = true;
			}
		}
		return check;
	}
	
	public boolean equals(Special x)
	{
		if(this.getName() == x.getName())
			return true;
		return false;
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
