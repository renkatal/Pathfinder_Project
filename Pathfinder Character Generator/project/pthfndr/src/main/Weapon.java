package pthfndr.src.main;

public class Weapon extends Item {
	private static double thickness;
	
	public Weapon(String name, int Size, int Material, int cost, int weight) {
		super(name, Size, Material, cost, weight, thickness);
		// TODO Auto-generated constructor stub
	}
	private int[] damage = new int[2];
	private int critical;
	private int range;
	private boolean[] type; 
	
	public int[] getDamage() {
		return this.damage;
	}
	public void setDamage(int num, int size) {
		this.damage = new int[] {num,size};
	}
	public int getCritical() {
		return this.critical;
	}
	public void setCritical(int critical) {
		this.critical = critical;
	}
	public int getRange() {
		return this.range;
	}
	public void setRange(int range) {
		this.range = range;
	}
	public boolean isType(int type) {
		return this.type[type];
	}
	public void setType(int type) {
		this.type[type] = true;
	}
		
	//{{0,0},{1,1},{1,2},{1,3},{1,4},{1,6},{1,8},{1,10},{1,12},{2,4},{2,6},{2,8},{2,10},{3,6},{3,8},{4,8}};
	
	

}
