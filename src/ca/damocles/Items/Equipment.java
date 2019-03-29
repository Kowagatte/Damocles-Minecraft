package ca.damocles.Items;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;
import ca.damocles.Items.Interfaces.Durable;
import ca.damocles.Items.Interfaces.Inscribable;
import ca.damocles.Items.Interfaces.Nameable;
import ca.damocles.Runes.Rune;

public class Equipment extends Item implements Inscribable, Durable, Nameable{

	public Equipment(ItemStack itemstack) {
		super(itemstack);
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
		runeMap.put(rune, level);
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
	public String getName() {
		NamespacedKey name = new NamespacedKey(Cardinal.getInstance(), "name");
		return (meta.getCustomTagContainer().hasCustomTag(name, ItemTagType.STRING)) ? meta.getCustomTagContainer().getCustomTag(name, ItemTagType.STRING) : "Unnamed Equipment";
	}

	@Override
	public void setName(String name) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "name");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.STRING, name);
		return;
	}
	
	public void updateName() {
		meta.setDisplayName(getName());
	}
	
	@Override
	public ItemStack finish() {
		updateLore();
		updateName();
		item.setItemMeta(meta);
		return item;
	}

}
