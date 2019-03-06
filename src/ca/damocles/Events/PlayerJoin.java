package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.scheduler.BukkitRunnable;

import ca.damocles.Cardinal;
import ca.damocles.Damocles;
import ca.damocles.Account.Account;
import ca.damocles.Menus.LoginMenu;

public class PlayerJoin implements Listener{
	
	@EventHandler
	public void onPlayerJoin(PlayerJoinEvent event) {
		Damocles.accounts.add(new Account(event.getPlayer().getUniqueId()));
		new BukkitRunnable() {
			@Override
			public void run() {
				event.getPlayer().openInventory(new LoginMenu(Damocles.getAccount(event.getPlayer().getUniqueId())).getInventory());
			}
		}.runTaskLaterAsynchronously(Cardinal.getInstance(), 1);
	}
	
}
