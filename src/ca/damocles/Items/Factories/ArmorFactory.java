package ca.damocles.Items.Factories;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.Damageable;
import org.bukkit.inventory.meta.ItemMeta;

import ca.damocles.Items.ItemType;
import ca.damocles.Items.Types.Armor;
import ca.damocles.Runes.Rune;

public class ArmorFactory {
	
	Armor armor;
	
	public ArmorFactory(int customModelID, ItemType type) {
		ItemStack item = null;
		switch(type) {
			case BOOTS:
				item = new ItemStack(Material.DIAMOND_BOOTS, 1);
				break;
			case CHESTPLATE:
				item = new ItemStack(Material.DIAMOND_CHESTPLATE, 1);
				break;
			case HELMET:
				item = new ItemStack(Material.DIAMOND_HELMET, 1);
				break;
			case LEGS:
				item = new ItemStack(Material.DIAMOND_LEGGINGS, 1);
				break;
			default:
				break;
		}
		ItemMeta meta = item.getItemMeta();
		((Damageable)meta).setDamage(customModelID);
		meta.addItemFlags(ItemFlag.HIDE_ATTRIBUTES, ItemFlag.HIDE_UNBREAKABLE, ItemFlag.HIDE_ENCHANTS);
		meta.setUnbreakable(true);
		item.setItemMeta(meta);
		armor = new Armor(item, type);
	}
	
	public ArmorFactory setMaxDurability(int maxDurability) {
		armor.setMaxDurability(maxDurability);
		return this;
	}
	
	public ArmorFactory setDurability(int durability) {
		armor.setDurability(durability);
		return this;
	}
	
	public ArmorFactory setArmor(double value) {
		armor.setArmor(value);
		return this;
	}
	
	public ArmorFactory setToughness(double value) {
		armor.setToughness(value);
		return this;
	}
	
	public ArmorFactory addRune(Rune rune, int level) {
		armor.addRune(rune, level);
		return this;
	}
	
	public ItemStack build() {
		return armor.finish();
	}

}
