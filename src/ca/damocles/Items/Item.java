package ca.damocles.Items;

import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import net.minecraft.server.v1_13_R2.NBTTagCompound;

public class Item {
	
	public ItemStack item;
	public net.minecraft.server.v1_13_R2.ItemStack nmsStack;
	public NBTTagCompound mainCompound;
	
	public Item(ItemStack itemstack) {
		item = itemstack;
		nmsStack = CraftItemStack.asNMSCopy(itemstack);
		mainCompound = (nmsStack.hasTag()) ? nmsStack.getTag() : new NBTTagCompound();
	}
	
	public ItemType getItemType() {
		if(mainCompound.hasKey("type")) {
			return ItemType.valueOf(mainCompound.getString("type"));
		}else {
			return ItemType.MINECRAFT_ITEM;
		}
	}
	
	public ItemStack build() {
		return CraftItemStack.asBukkitCopy(nmsStack);
	}
	
}
