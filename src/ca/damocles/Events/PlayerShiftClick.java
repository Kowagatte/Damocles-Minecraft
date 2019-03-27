package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ca.damocles.Items.Factories.SwordFactory;

public class PlayerShiftClick implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().isSneaking()) {
				event.getPlayer().getInventory().addItem(
						new SwordFactory(1)
						.setUpDurability(100)
						.setAttackSpeed(-2.4)
						.setDamage(7)
						.build());
			}
		}
	}
	
}
