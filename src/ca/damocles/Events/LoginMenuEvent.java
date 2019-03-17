package ca.damocles.Events;

import org.bukkit.Bukkit;
import org.bukkit.Material;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.inventory.InventoryClickEvent;
import org.bukkit.event.inventory.InventoryCloseEvent;
import org.bukkit.inventory.ItemStack;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import ca.damocles.Cardinal;
import ca.damocles.Damocles;
import ca.damocles.Account.Account;
import ca.damocles.Account.Account.AccountStatus;
import ca.damocles.Menus.LoginMenu;
import net.wesjd.anvilgui.AnvilGUI;

public class LoginMenuEvent implements Listener{
	
	public void sendAnvilMenu(Plugin plugin, Player holder, String message) {
		Account account = Damocles.getAccount(holder.getUniqueId());
		new AnvilGUI(Cardinal.getInstance(), holder, message, (player, reply) -> {
			String reg = "[a-zA-Z1-9_]{4,16}";
			if(reply.matches(reg)) {
				for(String s : Damocles.getAllUserNames()) {
					if(reply.equalsIgnoreCase(s.toLowerCase())) {
						account.setStatus(AccountStatus.CREATING_CHARACTER);
						player.closeInventory();
						sendAnvilMenu(plugin, player, "Username Taken");
						return "Username Taken";
					}
				}
				int i = account.createNewCharacter();
				account.characters.get(i).setUsername(reply);
				account.characters.get(i).config.saveToFile();
				return null;
			}
			account.setStatus(AccountStatus.CREATING_CHARACTER);
			player.closeInventory();
			sendAnvilMenu(plugin, player, "Invalid Characters");
			return "Invalid characters";
		});
		account.setStatus(AccountStatus.SELECTING_CHARACTER);
	}
	
	@SuppressWarnings("deprecation")
	@EventHandler
	public void onLoginMenuClick(InventoryClickEvent event) {
		Account account = Damocles.getAccount(event.getWhoClicked().getUniqueId());
		LoginMenu menu = new LoginMenu(account);
		if(event.getInventory().getTitle().equals(menu.getInventory().getTitle())) {
			if(account.getStatus() == AccountStatus.SELECTING_CHARACTER) {
				if(event.getView().getTopInventory().contains(event.getCurrentItem())) {
					event.setCancelled(true);
					ItemStack clicked = event.getCurrentItem();
					
					if(clicked.getType() == Material.EMERALD_BLOCK) {
						int slot = event.getSlot();
						account.loginCharacter(menu.characterItems.get(slot).getCharacter().getID());
						event.getWhoClicked().closeInventory();
					}
					
					if(clicked.getType() == Material.QUARTZ_BLOCK) {
						if(event.getWhoClicked() instanceof Player) {
							account.setStatus(AccountStatus.CREATING_CHARACTER);
							event.getWhoClicked().closeInventory();
							sendAnvilMenu(Cardinal.getInstance(), (Player)event.getWhoClicked(), "Select Username");
						}
					}
					
					if(clicked.getType() ==  Material.REDSTONE_BLOCK)
						if(event.getWhoClicked() instanceof Player)
							((Player)event.getWhoClicked()).kickPlayer("You have exited Damocles!");			
				}
			}
		}
	}
	
	@EventHandler
	public void onLoginMenuClose(InventoryCloseEvent event) {
		if(Bukkit.getOnlinePlayers().contains(event.getPlayer())) {
			Account account = Damocles.getAccount(event.getPlayer().getUniqueId());
			LoginMenu menu = new LoginMenu(account);
			if(account.getStatus() == AccountStatus.SELECTING_CHARACTER) {
				new BukkitRunnable() {
					public void run() {
						event.getPlayer().openInventory(menu.getInventory());
					}
				}.runTaskLaterAsynchronously(Cardinal.getInstance(), 1);
			}
		}
	}
	
}
