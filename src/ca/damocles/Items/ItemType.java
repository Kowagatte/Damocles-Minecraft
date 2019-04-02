package ca.damocles.Items;

import org.bukkit.inventory.EquipmentSlot;

public enum ItemType {
	MINECRAFT_ITEM(null, false),
	HELMET(EquipmentSlot.HEAD, true),
	CHESTPLATE(EquipmentSlot.CHEST, true),
	LEGS(EquipmentSlot.LEGS, true),
	BOOTS(EquipmentSlot.FEET, true),
	RING(null, true),
	BOW(EquipmentSlot.HAND, true),
	SWORD(EquipmentSlot.HAND, true),
	RUNE(null, false),
	PROTECTION_RUNE(null, false);
	
	private EquipmentSlot slot;
	private boolean inscribable;
	ItemType(EquipmentSlot slot, boolean inscribable){
		this.slot = slot;
		this.inscribable = inscribable;
	}
	public EquipmentSlot getSlot() {
		return slot;
	}
	public boolean isInscribable() {
		return inscribable;
	}
}
