package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

public class PlayerShiftClick implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.HAND) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(event.getPlayer().isSneaking()) {
					//Do nothing.
				}
			}
		}
	}
	
}
