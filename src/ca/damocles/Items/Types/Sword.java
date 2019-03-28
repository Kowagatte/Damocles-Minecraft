package ca.damocles.Items.Types;

import java.util.UUID;

import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import ca.damocles.Items.Equipment;
import ca.damocles.Items.ItemType;

public class Sword extends Equipment{
	
	public Sword(ItemStack itemstack) {
		super(itemstack);
		setItemType(ItemType.SWORD);
	}

	public void setAttackSpeed(double attackSpeed) {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_SPEED) != null) {
				meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_SPEED);
			}
		}
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_SPEED, new AttributeModifier(UUID.randomUUID(), "generic.attackSpeed", attackSpeed, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		return;
	}
	
	public void setDamage(int amount) {
		if(meta.hasAttributeModifiers()) {
			if(meta.getAttributeModifiers(Attribute.GENERIC_ATTACK_DAMAGE) != null) {
				meta.removeAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE);
			}
		}
		meta.addAttributeModifier(Attribute.GENERIC_ATTACK_DAMAGE, new AttributeModifier(UUID.randomUUID(), "generic.attackDamage", amount, AttributeModifier.Operation.ADD_NUMBER, EquipmentSlot.HAND));
		return;
	}
	
}
