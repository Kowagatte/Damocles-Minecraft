package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class Volley extends Rune{

	public Volley(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public Runes getRune() {
		return Runes.VOLLEY;
	}

	@Override
	public String getName() {
		return "Volley";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.BOW};
	}
	
}
