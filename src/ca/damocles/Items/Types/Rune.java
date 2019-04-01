package ca.damocles.Items.Types;

import org.bukkit.NamespacedKey;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.tags.ItemTagType;

import ca.damocles.Cardinal;
import ca.damocles.Items.Item;
import ca.damocles.Items.ItemType;

public class Rune extends Item{

	public Rune(ItemStack itemstack) {
		super(itemstack);
		setItemType(ItemType.RUNE);
	}
	
	public void setRuneType(ca.damocles.Runes.Rune rune) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "rune");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.STRING, rune.toString());
	}
	
	public ca.damocles.Runes.Rune getRune(){
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "rune");
		return ca.damocles.Runes.Rune.valueOf((meta.getCustomTagContainer().hasCustomTag(nameKey, ItemTagType.STRING)) ? meta.getCustomTagContainer().getCustomTag(nameKey, ItemTagType.STRING) : "BLANK");
	}
	
	public void setLevel(int level) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "level");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.INTEGER, level);
	}
	
	public int getLevel(){
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "level");
		return (meta.getCustomTagContainer().hasCustomTag(nameKey, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(nameKey, ItemTagType.INTEGER) : 0;
	}
	
	public void setSuccess(int success) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "success");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.INTEGER, success);
	}
	
	public int getSuccess(){
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "success");
		return (meta.getCustomTagContainer().hasCustomTag(nameKey, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(nameKey, ItemTagType.INTEGER) : 0;
	}
	
	public void setDestroy(int destroy) {
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "destroy");
		meta.getCustomTagContainer().setCustomTag(nameKey, ItemTagType.INTEGER, destroy);
	}
	
	public int getDestroy(){
		NamespacedKey nameKey = new NamespacedKey(Cardinal.getInstance(), "destroy");
		return (meta.getCustomTagContainer().hasCustomTag(nameKey, ItemTagType.INTEGER)) ? meta.getCustomTagContainer().getCustomTag(nameKey, ItemTagType.INTEGER) : 0;
	}
	
	@Override
	public ItemStack finish() {
		updateLore();
		item.setItemMeta(meta);
		return item;
	}

}
