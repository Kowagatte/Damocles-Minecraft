package ca.damocles.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;

public abstract class Item {
	
	public ItemStack item;
	public ItemMeta meta;
	
	public Item(ItemStack itemstack) {
		item = itemstack;
		meta = item.getItemMeta();
	}
	
	public ItemType getItemType() {
		NamespacedKey itemKey = new NamespacedKey(Cardinal.getInstance(), "item");
		if(meta.getCustomTagContainer().hasCustomTag(itemKey, ItemTagType.STRING)) {
			return ItemType.valueOf(meta.getCustomTagContainer().getCustomTag(itemKey, ItemTagType.STRING));
		}else {
			return ItemType.MINECRAFT_ITEM;
		}
	}
	
	public abstract ItemStack finish();
	
}
