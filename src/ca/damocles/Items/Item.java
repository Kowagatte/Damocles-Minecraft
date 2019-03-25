package ca.damocles.Items;

import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagDouble;
import net.minecraft.server.v1_13_R2.NBTTagInt;
import net.minecraft.server.v1_13_R2.NBTTagString;

public class Item {
	
	public net.minecraft.server.v1_13_R2.ItemStack nmsStack;
	
	public Item(ItemStack itemstack) {
		nmsStack = CraftItemStack.asNMSCopy(itemstack);
	}
	
	public net.minecraft.server.v1_13_R2.ItemStack getNMSStack(){
		return nmsStack;
	}
	
	public NBTTagCompound getCompound() {
		return (getNMSStack().hasTag()) ? getNMSStack().getTag() : new NBTTagCompound();
	}
	
	public ItemType getItemType() {
		if(getCompound().hasKey("type")) {
			return ItemType.valueOf(getCompound().getString("type"));
		}else {
			return ItemType.MINECRAFT_ITEM;
		}
	}
	
	public void set(String key, Object value) {
		NBTTagCompound tag = getCompound();
		if(value instanceof Integer) {
			tag.set(key, new NBTTagInt((int)value));
		}else if(value instanceof Double) {
			tag.set(key, new NBTTagDouble((double)value));
		}else if(value instanceof String) {
			tag.set(key, new NBTTagString((String)value));
		}
		nmsStack.setTag(tag);
	}
	
	public ItemStack build() {
		return CraftItemStack.asBukkitCopy(getNMSStack());
	}
	
}
