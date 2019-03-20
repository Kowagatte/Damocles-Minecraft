package ca.damocles.Damage;

public enum DamageType {
	
	LETHAL(true, false, false),
	PHYSICAL(false, false, false),
	MAGICAL(false, false, true),
	ANCIENT(true, false, true),
	TRUE(true, true, true);
	
	boolean armor;
	boolean enchantment;
	boolean spell;
	
	DamageType(boolean ignoresArmor, boolean ignoresEnchantments, boolean ignoresSpells){
		armor = ignoresArmor;
		enchantment = ignoresEnchantments;
		spell = ignoresSpells;
	}
	
	public boolean doesIgnoreArmor() {
		return armor;
	}
	
	public boolean doesIgnoreEnchantments() {
		return enchantment;
	}
	
	public boolean doesIgnoreSpells() {
		return spell;
	}
	
}
