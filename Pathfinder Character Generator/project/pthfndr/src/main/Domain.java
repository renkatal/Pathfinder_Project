package pthfndr.src.main;

import java.util.ArrayList;

public class Domain {
	private String name;
	private ArrayList<Special> powers = new ArrayList<>();
	private Spell[] domainSpells = new Spell[9];
	
	public String getName() {
		return this.name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public void setPower(Special power) {
		this.powers.add(power);
	}
	public ArrayList<Special> getPowers() {
		return this.powers;
	}
	
	public Spell[] getDomainSpells() {
		return this.domainSpells;
	}
	public Spell getDomainSpell(int level) {
		return this.domainSpells[level];
	}
	public void setDomainSpell(Spell spell, int level) {
		this.domainSpells[level] = spell;
	}

}
