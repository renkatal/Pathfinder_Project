package pthfndr.src.main;

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
		
		public static FastMovement fastMovement = new FastMovement();
		
		public static class FastMovement extends ExtraordinayAblitiy {
			public FastMovement() {
				super("Fast Movement");
			}
			
			public static int bonus(Creature creature) {
				if ( Special.check(creature, fastMovement) && creature.checkArmorType(Armor.ArmorType.HEAVY) == false && Encumbrance.check(creature) < Encumbrance.HEAVY ) {
					return 10;
				}
				
				return 0;
			}
			
			
			
		}
		public static class Regeneration extends ExtraordinayAblitiy {
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
