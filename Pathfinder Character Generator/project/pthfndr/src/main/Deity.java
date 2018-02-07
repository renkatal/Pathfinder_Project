package pthfndr.src.main;
import java.util.ArrayList;



public class Deity implements Alignment{

	private String name;
	private int alignment[] = new int[2];
	
	private ArrayList<Domain> domains = new ArrayList<>();
	private Weapon favoredWeapon;
	
	public String getName()
	{
		return name;
	}
	public void setName(String name)
	{
		this.name = name;
	}
	
	public int[] getAlignment() {
		return alignment;
	}
	public void setAlignment(int order, int moral ) {
		this.alignment[ORDER] = order;
		this.alignment[MORAL] = moral;
		
	}
	
	public ArrayList<Domain> getDomains() {
		return domains;
	}
	public void addDomains(Domain domain) {
		this.domains.add(domain);
	}
	public Weapon getFavoredWeapon() {
		return favoredWeapon;
	}
	public void setFavoredWeapon(Weapon favoredWeapon) {
		this.favoredWeapon = favoredWeapon;
	}
}
