package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class Protection extends Rune{

	public Protection(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 4;
	}

	@Override
	public Runes getRune() {
		return Runes.PROTECTION;
	}

	@Override
	public String getName() {
		return "Protection";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.BOOTS, ItemType.LEGS, ItemType.CHESTPLATE, ItemType.HELMET};
	}

}
