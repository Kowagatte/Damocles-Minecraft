package ca.damocles.Events;

import java.util.Arrays;
import java.util.Random;

import org.bukkit.Material;
import org.bukkit.event.Event.Result;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Items.Item;
import ca.damocles.Items.ItemWrapper;
import ca.damocles.Items.Interfaces.Inscribable;
import ca.damocles.Items.Types.ProtectionRune;
import ca.damocles.Items.Types.Rune;

public class ApplyRune implements Listener{
	
	@EventHandler
	public void onItemClick(InventoryClickEvent event) {
		ItemStack itemClickedOn = event.getCurrentItem();
		ItemStack itemInCursor = event.getCursor();
		if (itemClickedOn != null && itemInCursor != null && itemClickedOn.getType() != Material.AIR && itemInCursor.getType() != Material.AIR) {
			if((new ItemWrapper(itemInCursor).get() instanceof Rune) && new ItemWrapper(itemClickedOn).get() instanceof Inscribable) {
				Rune rune = (Rune)new ItemWrapper(itemInCursor).get();
				Inscribable inscribable = (Inscribable)new ItemWrapper(itemClickedOn).get();
				if(Arrays.asList(rune.getRune().getCompatibleTypes()).contains(((Item)inscribable).getItemType())) {
					if(!inscribable.getAppliedRunes().containsKey(rune.getRune())) {
						if(inscribable.getAvailableSlots() >= 1) {
							event.setResult(Result.DENY);
							event.setCancelled(true);
							
							int success = new Random().nextInt(101);
							int destroy = new Random().nextInt(101);
							
							if(success <= rune.getSuccess()) {
								inscribable.addRune(rune.getRune(), rune.getLevel());
								((Item)inscribable).finish();
							}else {
								if(destroy <= rune.getDestroy()) {
									if(inscribable.isProtected()) {
										inscribable.setProtected(false);
										((Item)inscribable).finish();
									}else {
										event.setCurrentItem(null);
									}
								}
							}
							
							inscribable.setSlots(inscribable.getAvailableSlots()-1);
							((Item)inscribable).finish();
							return;
						}
					}
				}
			}else if((new ItemWrapper(itemInCursor).get() instanceof ProtectionRune) && new ItemWrapper(itemClickedOn).get() instanceof Inscribable) {
				Inscribable inscribable = (Inscribable)new ItemWrapper(itemClickedOn).get();
				
				if(inscribable.isProtected()) {
					return;
				}else{
					inscribable.setProtected(true);
					((Item)inscribable).finish();
					event.setCancelled(true);
					return;
				}
				
			}
		}
	}
	
}
