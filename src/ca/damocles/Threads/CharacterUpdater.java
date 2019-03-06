package ca.damocles.Threads;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import ca.damocles.Account.Character.Character;

public class CharacterUpdater extends Thread{
	
	private boolean active;
	private Player player;
	private Character character;
	
	public CharacterUpdater(Player player, Character character) {
		this.player = player;
		this.character = character;
		this.active = true;
		start();
	}
	
	public boolean isActive() {
		return active;
	}
	
	public void setActive(boolean active) {
		this.active = active;
	}
	
	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double nanoSecondsPerTicks = 1000000000 / 20;
		double delta = 0;
		while(active) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecondsPerTicks;
			lastTime = now;
			if(delta >= 1) {
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(character.getMaxHealth());
				
				player.setLevel(character.getLevel());
				
				
				if(!player.isDead())
					player.setHealth(character.getHealth());
				
				delta--;
			}
		}
	}
	
}
