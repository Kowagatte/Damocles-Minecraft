package ca.damocles.Items.Types;

import org.bukkit.ChatColor;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Items.Item;
import ca.damocles.Items.ItemType;

public class ProtectionRune extends Item{

	public ProtectionRune(ItemStack itemstack) {
		super(itemstack);
		setItemType(ItemType.PROTECTION_RUNE);
	}

	@Override
	public ItemStack finish() {
		meta.setDisplayName(ChatColor.BOLD + "Protection" + ChatColor.RESET + "" + ChatColor.GRAY+" Rune");
		updateLore();
		item.setItemMeta(meta);
		return item;
	}

}
