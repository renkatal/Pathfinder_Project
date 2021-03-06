package pthfndr.src.main;

public interface Condition {
	public static final int BLEED = 0, BLINDED = 1, BROKEN = 2, CONFUSED = 3, COWERING = 4, DAZED = 5, DAZZLED = 6, DEAD = 7, DEAFENED = 8, DISABLED = 9, DYING = 10, ENERGY_DRAINED = 11, ENTANGLED = 12, EXHAUSTED = 13, FASCINATED = 14, FATIGUED = 15, FLAT_FOOTED = 16, FRIGHTENED = 17, GRAPPLED = 18, HELPLESS = 19, INCORPOREAL = 20, INVISIBLE = 21, NAUSEATED = 22, PANICKED = 23, PARALYZED = 24, PETRIFIED = 25, PINNED = 26, PRONE = 27, SHAKEN = 28, SICKENED = 29, STABLE = 30, STAGGERED = 31, STUNNED = 32, UNCONSCIOUS = 33, DESTROYED = 34;
	
	public void addCondition(int condition);
	public void removeCondition(int condition);
}
