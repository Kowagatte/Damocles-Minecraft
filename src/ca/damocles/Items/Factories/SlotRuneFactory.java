package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import ca.damocles.Items.Types.SlotRune;

public class SlotRuneFactory {
	
	SlotRune rune;
	
	public SlotRuneFactory() {
		ItemStack item = new ItemStack(Material.SHEARS, 1);
		ItemMeta meta = item.getItemMeta();
		((Damageable)meta).setDamage(5);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		this.rune = new SlotRune(item);
	}
	
	public SlotRuneFactory setSlots(int slots) {
		rune.setSlots(slots);
		return this;
	}
	
	public ItemStack build() {
		return rune.finish();
	}
}
