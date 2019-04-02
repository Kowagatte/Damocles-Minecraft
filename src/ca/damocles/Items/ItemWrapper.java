package ca.damocles.Items;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;
import ca.damocles.Items.Types.Armor;
import ca.damocles.Items.Types.ProtectionRune;
import ca.damocles.Items.Types.Rune;
import ca.damocles.Items.Types.Sword;

public class ItemWrapper {
	
	ItemStack item;
	
	public ItemWrapper(ItemStack item) {
		this.item = item;
	}
	
	private ItemType getItemType() {
		NamespacedKey itemKey = new NamespacedKey(Cardinal.getInstance(), "item");
		if(item.hasItemMeta()) {
			if(item.getItemMeta().getCustomTagContainer().hasCustomTag(itemKey, ItemTagType.STRING)) {
				return ItemType.valueOf(item.getItemMeta().getCustomTagContainer().getCustomTag(itemKey, ItemTagType.STRING));
			}else {
				return ItemType.MINECRAFT_ITEM;
			}
		}else {
			return ItemType.MINECRAFT_ITEM;
		}
	}
	
	public Item get() {
		switch(getItemType()) {
			case BOOTS:
				return new Armor(item, getItemType());
			case BOW:
				break;
			case CHESTPLATE:
				return new Armor(item, getItemType());
			case HELMET:
				return new Armor(item, getItemType());
			case LEGS:
				return new Armor(item, getItemType());
			case MINECRAFT_ITEM:
				break;
			case PROTECTION_RUNE:
				return new ProtectionRune(item);
			case RING:
				break;
			case RUNE:
				return new Rune(item);
			case SWORD:
				return new Sword(item);
			default:
				break;
		}
		return null;
	}
	
}
