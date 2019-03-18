package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;

public class DamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		event.setCancelled(true);
	}
	
	@EventHandler
	public void onBlockDamageEntity(EntityDamageByBlockEvent event) {
		event.setCancelled(true);
	}
	
}
