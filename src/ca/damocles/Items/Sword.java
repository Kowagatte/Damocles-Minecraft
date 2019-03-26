package ca.damocles.Items;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.craftbukkit.v1_13_R2.inventory.CraftItemStack;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Runes.Rune;
import net.minecraft.server.v1_13_R2.NBTTagCompound;
import net.minecraft.server.v1_13_R2.NBTTagInt;
import net.minecraft.server.v1_13_R2.NBTTagList;
import net.minecraft.server.v1_13_R2.NBTTagString;

public class Sword extends Item implements Inscribable{

	private Map<Rune, Integer> runes;
	
	public Sword(ItemStack itemstack) {
		super(itemstack);
		runes = getAppliedRunes();
	}
	
	public void setDamage() {
		
	}

	@Override
	public Map<Rune, Integer> getAppliedRunes() {
		Map<Rune, Integer> runeMap = new HashMap<>();
		NBTTagList runes = (NBTTagList) mainCompound.get("Runes");
		if(runes != null) {
			runes.forEach((tag) ->{
				NBTTagCompound comp = (NBTTagCompound) tag;
				runeMap.put(Rune.valueOf(comp.getString("name")), comp.getInt("level"));
			});
		}
		return runeMap;
	}
	
	@Override
	public void addRune(Rune rune, int level) {
		runes.put(rune, level);
	}
	
	@Override
	public void setRunes() {
		NBTTagList nbtruneList = new NBTTagList();
		runes.forEach((k, v) ->{
			NBTTagCompound newTag = new NBTTagCompound();
			newTag.set("name", new NBTTagString(k.toString()));
			newTag.set("level", new NBTTagInt(v));
			nbtruneList.add(newTag);
		});
		mainCompound.set("Runes", nbtruneList);
	}

	@Override
	public int getAvailableSlots() {
		return mainCompound.getInt("rune_slots");
	}
	
	@Override
	public ItemStack build() {
		return CraftItemStack.asBukkitCopy(nmsStack);
	}

}
