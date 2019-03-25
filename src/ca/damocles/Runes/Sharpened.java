package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class Sharpened extends Rune{

	public Sharpened(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 5;
	}

	@Override
	public Runes getRune() {
		return Runes.SHARPENED;
	}

	@Override
	public String getName() {
		return "Sharpened";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.SWORD};
	}

}
