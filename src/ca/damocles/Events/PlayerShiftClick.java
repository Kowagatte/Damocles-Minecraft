package ca.damocles.Events;

import java.util.Arrays;

import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.block.Action;
import org.bukkit.event.player.PlayerInteractEvent;

import ca.damocles.Menus.Book.Book;

public class PlayerShiftClick implements Listener{
	
	@EventHandler
	public void onPlayerClick(PlayerInteractEvent event) {
		if(event.getAction() == Action.RIGHT_CLICK_BLOCK) {
			if(event.getPlayer().isSneaking()) {
				Book book = new Book("Info", "GOD");
				book.addPage(Arrays.asList("Test"));
				book.openBook(event.getPlayer());
			}
		}
	}
	
}
