package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.enchantments.Enchantment;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import ca.damocles.Items.Types.Rune;

public class RuneFactory {
	
	Rune rune;
	
	public RuneFactory(ca.damocles.Runes.Rune rune) {
		ItemStack item = new ItemStack(Material.SHEARS, 1);
		ItemMeta meta = item.getItemMeta();
		((Damageable)meta).setDamage(4);
		meta.addEnchant(Enchantment.DURABILITY, 3, true);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		this.rune = new Rune(item);
		this.rune.setRuneType(rune);
	}
	
	public RuneFactory setLevel(int level) {
		rune.setLevel(level);
		return this;
	}
	
	public RuneFactory setSuccess(int success) {
		rune.setSuccess(success);
		return this;
	}

	public RuneFactory setDestroy(int destroy) {
		rune.setDestroy(destroy);
		return this;
	}
	
	public ItemStack build() {
		return rune.finish();
	}
	
}
