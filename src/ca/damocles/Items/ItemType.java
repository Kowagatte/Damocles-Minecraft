package ca.damocles.Items;

import org.bukkit.inventory.EquipmentSlot;

public enum ItemType {
	MINECRAFT_ITEM(null),
	HELMET(EquipmentSlot.HEAD),
	CHESTPLATE(EquipmentSlot.CHEST),
	LEGS(EquipmentSlot.LEGS),
	BOOTS(EquipmentSlot.FEET),
	RING(null),
	BOW(EquipmentSlot.HAND),
	SWORD(EquipmentSlot.HAND);
	
	private EquipmentSlot slot;
	ItemType(EquipmentSlot slot){
		this.slot = slot;
	}
	public EquipmentSlot getSlot() {
		return slot;
	}
	
}
