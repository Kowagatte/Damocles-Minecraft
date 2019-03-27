package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

public class SwordFactory {
	
	public SwordFactory(int customModelID) {
		ItemStack item = new ItemStack(Material.DIAMOND_SWORD, 1);
		ItemMeta meta = item.getItemMeta();
		Damageable dmeta = (Damageable)meta;
		dmeta.setDamage(customModelID);
		item.setItemMeta((ItemMeta)dmeta);
		meta = item.getItemMeta();
		
	}
	
	public SwordFactory setUpDurability(int maxdurability) {
		
	}
	
	public SwordFactory setDamage(int damage) {
		
	}
	
	public SwordFactory setAttackSpeed(double attackSpeed) {
		
	}
	
	public SwordFactory addRune(Rune rune, int level) {
		
	}
	
	public ItemStack build() {
		
	}
	
}
