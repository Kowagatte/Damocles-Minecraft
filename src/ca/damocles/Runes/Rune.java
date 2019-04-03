package ca.damocles.Runes;

import ca.damocles.Items.ItemType;

public enum Rune {
	
	BLANK(0, "ERROR", new ItemType[] {}),
	LIFESTEAL(3, "LifeSteal", new ItemType[] {ItemType.SWORD}),
	SPEED(3, "Speed", new ItemType[] {ItemType.BOOTS}),
	VOLLEY(4, "Volley", new ItemType[] {ItemType.BOW}),
	SNARE(2, "Snare", new ItemType[] {ItemType.BOW}),
	BLOODTHIRST(3, "BloodThirst", new ItemType[] {ItemType.SWORD}),
	PROTECTION(4, "Protection", new ItemType[] {ItemType.BOOTS, ItemType.LEGS, ItemType.CHESTPLATE, ItemType.HELMET}),
	SHARPENED(5, "Sharpened", new ItemType[] {ItemType.SWORD}),
	FLAME_ASPECT(1, "Flame Aspect", new ItemType[] {ItemType.BOW, ItemType.SWORD});
	
	private int maxLevel;
	private String name;
	private ItemType[] compatibleTypes;
	Rune(int maxLevel, String name, ItemType[] compatibleTypes){
		this.maxLevel = maxLevel;
		this.name = name;
		this.compatibleTypes = compatibleTypes;
	}
	
	public int getMaxLevel() {
		return maxLevel;
	}
	
	public String getName() {
		return name;
	}
	
	public ItemType[] getCompatibleTypes() {
		return compatibleTypes;
	}
	
}
