package ca.damocles.Events;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerQuitEvent;

import ca.damocles.Damocles;
import ca.damocles.Account.Account;

public class PlayerLeave implements Listener{

	@EventHandler
	public void onPlayerLeave(PlayerQuitEvent event) {
		Account account = Damocles.getAccount(event.getPlayer().getUniqueId());
		account.logoutCharacter();
		account.saveInfo();
		Damocles.accounts.remove(account);
	}
	
}
