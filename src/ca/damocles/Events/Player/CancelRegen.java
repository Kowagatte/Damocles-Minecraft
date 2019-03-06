package ca.damocles.Events.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityRegainHealthEvent;
import org.bukkit.event.entity.EntityRegainHealthEvent.RegainReason;

public class CancelRegen implements Listener{
	
	@EventHandler
	public void playerRegainEvent(EntityRegainHealthEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getRegainReason() == RegainReason.SATIATED) {
				event.setCancelled(true);
			}
		}
	}
	
}
