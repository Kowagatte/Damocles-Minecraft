package ca.damocles.Runes;

public class LifeSteal extends Rune{

	public LifeSteal(int id) {
		super(id);
	}
	
	public int getMaxLevel() {
		return 2;
	}
	
	public Runes getRune() {
		return Runes.LIFESTEAL;
	}

	public String getName() {
		return "LifeSteal";
	}

}
