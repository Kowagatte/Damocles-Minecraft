package ca.damocles.Items.Types;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.NamespacedKey;
import org.bukkit.attribute.Attribute;
import org.bukkit.attribute.AttributeModifier;
import org.bukkit.inventory.EquipmentSlot;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;
import ca.damocles.Items.Durable;
import ca.damocles.Items.Inscribable;
import ca.damocles.Items.Item;
import ca.damocles.Runes.Rune;

public class Sword extends Item implements Inscribable, Durable{

	public Sword(ItemStack itemstack) {
		super(itemstack);
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

	@Override
	public Map<Rune, Integer> getAppliedRunes() {
		Map<Rune, Integer> runeMap = new HashMap<>();
		NamespacedKey runesKey = new NamespacedKey(Cardinal.getInstance(), "runes");
		if(meta.getCustomTagContainer().hasCustomTag(runesKey, ItemTagType.STRING)) {
			String rawRunes = meta.getCustomTagContainer().getCustomTag(runesKey, ItemTagType.STRING);
			rawRunes = rawRunes.trim().replace("{", "").replace("}", "");
			String[] rawRuneArray = rawRunes.split(",");
			for(String element : rawRuneArray) {
				String[] splitElement = element.split(":");
				runeMap.put(Rune.valueOf(splitElement[0]), Integer.valueOf(splitElement[1]));
			}
		}
		return runeMap;
	}
	
	@Override
	public void addRune(Rune rune, int level) {
		NamespacedKey runesKey = new NamespacedKey(Cardinal.getInstance(), "runes");
		Map<Rune, Integer> runeMap = getAppliedRunes();
		List<String> runeElements = new ArrayList<>();
		for(Rune k : runeMap.keySet()) {
			runeElements.add(k.toString() + ":" + runeMap.get(k));
		}
		String inside = String.join(",", runeElements);
		meta.getCustomTagContainer().setCustomTag(runesKey, ItemTagType.STRING, "{"+inside+"}");
		return;
	}

	@Override
	public int getAvailableSlots() {
		NamespacedKey runesKey = new NamespacedKey(Cardinal.getInstance(), "rune_slots");
		return (meta.getCustomTagContainer().hasCustomTag(runesKey, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(runesKey, ItemTagType.INTEGER) : 0;
	}

	@Override
	public int getMaxDurability() {
		NamespacedKey maxDura = new NamespacedKey(Cardinal.getInstance(), "max_durability");
		return (meta.getCustomTagContainer().hasCustomTag(maxDura, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(maxDura, ItemTagType.INTEGER) : 0;
	}
	
	public void setMaxDurability(int maxDurability) {
		NamespacedKey maxDura = new NamespacedKey(Cardinal.getInstance(), "max_durability");
		meta.getCustomTagContainer().setCustomTag(maxDura, ItemTagType.INTEGER, maxDurability);
		return;
	}

	@Override
	public int getDurability() {
		NamespacedKey dura = new NamespacedKey(Cardinal.getInstance(), "durability");
		return (meta.getCustomTagContainer().hasCustomTag(dura, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(dura, ItemTagType.INTEGER) : 0;
	}

	@Override
	public void setDurability(int durability) {
		NamespacedKey dura = new NamespacedKey(Cardinal.getInstance(), "durability");
		meta.getCustomTagContainer().setCustomTag(dura, ItemTagType.INTEGER, durability);
		return;
	}

	@Override
	public void removeDurability(int durability) {
		setDurability(getDurability() - durability);
		return;
	}

	@Override
	public ItemStack finish() {
		item.setItemMeta(meta);
		return item;
	}

}
