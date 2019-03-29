package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import ca.damocles.Items.TextureID;
import ca.damocles.Items.Factories.SwordFactory;
import ca.damocles.Runes.Rune;

public class PlayerShiftClick implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.HAND) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(event.getPlayer().isSneaking()) {
					
					
					event.getPlayer().getInventory().addItem(
							new SwordFactory(TextureID.BRONZE_SWORD.getID())
							.setUpDurability(100)
							.setDamage(7)
							.setAttackSpeed(-2.4)
							.addRune(Rune.SHARPENED, 4)
							.build());
				
				
				
				}
			}
		}
	}
	
}
