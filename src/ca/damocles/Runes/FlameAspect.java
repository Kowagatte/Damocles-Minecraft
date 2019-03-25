package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public class FlameAspect extends Rune{

	public FlameAspect(int id) {
		super(id);
	}

	@Override
	public int getMaxLevel() {
		return 2;
	}

	@Override
	public Runes getRune() {
		return Runes.FLAME_ASPECT;
	}

	@Override
	public String getName() {
		return "Flame Aspect";
	}

	@Override
	public ItemType[] getApplicableItems() {
		return new ItemType[] {ItemType.SWORD, ItemType.BOW};
	}

}
