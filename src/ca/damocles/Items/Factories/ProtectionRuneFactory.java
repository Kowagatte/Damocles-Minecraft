package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import ca.damocles.Items.Types.ProtectionRune;

public class ProtectionRuneFactory {
	
	ProtectionRune rune;
	
	public ProtectionRuneFactory() {
		ItemStack item = new ItemStack(Material.SHEARS, 1);
		ItemMeta meta = item.getItemMeta();
		((Damageable)meta).setDamage(3);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		this.rune = new ProtectionRune(item);
	}
	
	public ItemStack build() {
		return rune.finish();
	}
	
}
