package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.entity.EntityDamageByBlockEvent;
import org.bukkit.event.entity.EntityDamageByEntityEvent;
import org.bukkit.event.entity.EntityDamageEvent;

/**
 * REMOVED DAMAGE CAUSES:
 *  - STARVATION
 *  - LIGHTNING
 *  - THORNS
 */
public class DamageListener implements Listener{
	
	@EventHandler
	public void onEntityDamaged(EntityDamageEvent event) {
		event.setCancelled(true);
		switch(event.getCause()) {
			case CRAMMING:
				break;
			case BLOCK_EXPLOSION:
				break;
			case DROWNING:
				break;
			case DRYOUT:
				break;
			case FALL:
				break;
			case FIRE:
				break;
			case FIRE_TICK:
				break;
			case FLY_INTO_WALL:
				break;
			case VOID:
				break;
			case WITHER:
				break;
			case SUFFOCATION:
				break;
			case POISON:
				break;
			default:
				break;
		}
	}
	
	@EventHandler
	public void onEntityDamageEntity(EntityDamageByEntityEvent event) {
		event.setCancelled(true);
		switch(event.getCause()) {
			case ENTITY_ATTACK:
				break;
			case ENTITY_EXPLOSION:
				break;
			case ENTITY_SWEEP_ATTACK:
				break;
			case FALLING_BLOCK:
				break;
			case MAGIC:
				break;
			case PROJECTILE:
				break;
			default:
				break;
		}
	}
	
	@EventHandler
	public void onBlockDamageEntity(EntityDamageByBlockEvent event) {
		event.setCancelled(true);
		switch(event.getCause()) {
			case CONTACT:
				break;
			case HOT_FLOOR:
				break;
			case LAVA:
				break;
			default:
				break;
		}
	}
	
}
