package ca.damocles.Runes;

public abstract class Rune {
	
	int id;
	
	public Rune(int id) {
		this.id = id;
	}
	
	public int getID() {
		return id;
	}
	
	public abstract int getMaxLevel();
	
	public abstract Runes getRune();
	
	public abstract String getName();
	
	public enum Runes {
		LIFESTEAL, SPEED, VOLLEY,
		SNARE, BLOODTHIRST, PROTECTION,
		SHARPENED, FLAME_ASPECT;
	}
	
}
