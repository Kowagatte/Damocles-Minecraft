package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import ca.damocles.Items.Types.Sword;
import ca.damocles.Runes.Rune;

public class SwordFactory {
	
	Sword sword;
	
	public SwordFactory(int customModelID) {
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta meta = item.getItemMeta();
		((Damageable)meta).setDamage(customModelID);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		sword = new Sword(item);
	}
	
	public SwordFactory setUpDurability(int maxdurability) {
		sword.setDurability(maxdurability);
		return this;
	}
	
	public SwordFactory setDamage(int damage) {
		sword.setDamage(damage);
		return this;
	}
	
	public SwordFactory setAttackSpeed(double attackSpeed) {
		sword.setAttackSpeed(attackSpeed);
		return this;
	}
	
	public SwordFactory addRune(Rune rune, int level) {
		sword.addRune(rune, level);
		return this;
	}
	
	public ItemStack build() {
		return sword.finish();
	}
	
}
