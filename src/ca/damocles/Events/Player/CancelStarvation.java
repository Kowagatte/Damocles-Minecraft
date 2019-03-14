package ca.damocles.Events.Player;

import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageEvent;
import org.bukkit.event.entity.EntityDamageEvent.DamageCause;

public class CancelStarvation implements Listener{
	
	@EventHandler
	public void onStarve(EntityDamageEvent event) {
		if(event.getEntity() instanceof Player) {
			if(event.getCause() == DamageCause.STARVATION) {
				event.setCancelled(true);
			}
		}
	}
	
}
