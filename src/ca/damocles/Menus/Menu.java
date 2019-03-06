package ca.damocles.Menus;

import java.util.Arrays;
import java.util.List;

import org.bukkit.Material;
import org.bukkit.inventory.ItemFlag;
import org.bukkit.inventory.ItemStack;
import org.bukkit.inventory.meta.ItemMeta;

public class Menu {
	
	public ItemStack emptyItem = createItem(" ", Material.WHITE_STAINED_GLASS_PANE);
	
	public ItemStack createItem(String name, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		i.setItemMeta(im);
		return i;
	}
	
	public ItemStack createItem(String name, String lore, Material m) {
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();
		im.setDisplayName(name);
		im.setLore(Arrays.asList(new String[] { lore }));
		i.setItemMeta(im);
		return i;
	}
	
	public ItemStack createItem(String name, String[] lore, Material m){
		ItemStack i = new ItemStack(m);
		ItemMeta im = i.getItemMeta();				
		im.setDisplayName(name);
		im.setLore(Arrays.asList(lore));
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(im);
		return i;
	}
	
	public ItemStack createItem(String name, List<String> lore, Material m, int amount){
		ItemStack i = new ItemStack(m, amount);
		ItemMeta im = i.getItemMeta();				
		im.setDisplayName(name);
		im.setLore(lore);
		im.addItemFlags(ItemFlag.HIDE_ATTRIBUTES);
		i.setItemMeta(im);
		return i;
	}
	
}
