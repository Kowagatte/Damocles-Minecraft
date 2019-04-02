package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;
import org.bukkit.inventory.EquipmentSlot;

import ca.damocles.Items.TextureID;
import ca.damocles.Items.Factories.ProtectionRuneFactory;
import ca.damocles.Items.Factories.RuneFactory;
import ca.damocles.Items.Factories.SwordFactory;
import ca.damocles.Runes.Rune;

public class PlayerShiftClick implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if(event.getHand() == EquipmentSlot.HAND) {
			if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
				if(event.getPlayer().isSneaking()) {
					
					
					event.getPlayer().getInventory().addItem(
							new SwordFactory(TextureID.EXCALIBUR.getID())
							.setMaxDurability(100)
							.setDurability(100)
							.setDamage(7)
							.setAttackSpeed(-2.4)
							.setSlots(3)
							.build());
					
					event.getPlayer().getInventory().addItem(
							new RuneFactory(Rune.SHARPENED)
							.setLevel(4)
							.setSuccess(83)
							.setDestroy(26)
							.build());
					
					event.getPlayer().getInventory().addItem(
							new ProtectionRuneFactory()
							.build());
				
				}
			}
		}
	}
	
}
