package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class Snare extends Rune{

	public Snare(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public Runes getRune() {
		return Runes.SNARE;
	}

	@Override
	public String getName() {
		return "Snare";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.BOW};
	}

}
