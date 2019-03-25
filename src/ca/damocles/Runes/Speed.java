package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class Speed extends Rune{

	public Speed(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 3;
	}

	@Override
	public Runes getRune() {
		return Runes.SPEED;
	}

	@Override
	public String getName() {
		return "Speed";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.BOOTS};
	}

}
