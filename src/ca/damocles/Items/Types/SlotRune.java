package ca.damocles.Items.Types;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;
import ca.damocles.Items.Item;
import ca.damocles.Items.ItemType;
import net.md_5.bungee.api.ChatColor;

public class SlotRune extends Item{

	public SlotRune(ItemStack itemstack) {
		super(itemstack);
		setItemType(ItemType.SLOT_RUNE);
	}
	
	public int getSlots() {
		NamespacedKey runesKey = new NamespacedKey(Cardinal.getInstance(), "rune_slots");
		return (meta.getCustomTagContainer().hasCustomTag(runesKey, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(runesKey, ItemTagType.INTEGER) : 0;
	}
	
	public void setSlots(int slots) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "rune_slots");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.INTEGER, slots);
		return;
	}

	@Override
	public ItemStack finish() {
		updateLore();
		meta.setDisplayName(ChatColor.GRAY+"Slot Rune");
		item.setItemMeta(meta);
		return item;
	}

}
