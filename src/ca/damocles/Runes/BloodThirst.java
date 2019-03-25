package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class BloodThirst extends Rune{

	public BloodThirst(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public Runes getRune() {
		return Runes.BLOODTHIRST;
	}

	@Override
	public String getName() {
		return "BloodThirst";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.SWORD};
	}

}
