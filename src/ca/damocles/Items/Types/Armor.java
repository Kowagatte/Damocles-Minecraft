package ca.damocles.Items.Types;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Items.Equipment;
import ca.damocles.Items.ItemType;

public class Armor extends Equipment{

	EquipmentSlot slot;
	
	public Armor(ItemStack itemstack, ItemType type) {
		super(itemstack);
		slot = type.getSlot();
		setItemType(type);
	}
	
	public void setArmor(double armor) {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null) {
				meta.removeAttributeModifier(Attribute.GENERIC_ARMOR);
			}
		}
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR, new AttributeModifier(UUID.randomUUID(), "generic.armor", armor, AttributeModifier.Operation.ADD_NUMBER, slot));
		return;
	}
	
	public double getArmor() {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR) != null) {
				for(AttributeModifier modifier : meta.getAttributeModifiers(Attribute.GENERIC_ARMOR)) {
					return modifier.getAmount();
				}
			}
		}
		return 0.0;
	}
	
	public void setToughness(double toughness) {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
				meta.removeAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS);
			}
		}
		meta.addAttributeModifier(Attribute.GENERIC_ARMOR_TOUGHNESS, new AttributeModifier(UUID.randomUUID(), "generic.armorToughness", toughness, AttributeModifier.Operation.ADD_NUMBER, slot));
		return;
	}
	
	public double getToughness() {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ARMOR_TOUGHNESS) != null) {
				for(AttributeModifier modifier : meta.getAttributeModifiers(Attribute.GENERIC_ARMOR_TOUGHNESS)) {
					return modifier.getAmount();
				}
			}
		}
		return 0.0;
	}
}
