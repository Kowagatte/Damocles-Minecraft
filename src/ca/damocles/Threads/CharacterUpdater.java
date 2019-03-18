package ca.damocles.Threads;

import org.bukkit.attribute.Attribute;
import org.bukkit.entity.Player;

import ca.damocles.Account.Character.Character;
import ca.damocles.Account.Character.Property.PropertyType;

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
		int count = 0;
		long lastTime = System.nanoTime();
		double nanoSecondsPerTicks = 1000000000 / 20;
		double delta = 0;
		while(active) {
			long now = System.nanoTime();
			delta += (now - lastTime) / nanoSecondsPerTicks;
			lastTime = now;
			if(delta >= 1) {
				count++;
				
				//MAXHEALTH & OVERFLOW UPDATES
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue((double)character.getProperty(PropertyType.BASE_MAX_HEALTH).getValue());
				character.getProperty(PropertyType.MAX_HEALTH).setValue(player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				if((double)character.getProperty(PropertyType.HEALTH).getValue() > (double)character.getProperty(PropertyType.MAX_HEALTH).getValue()) {
					character.getProperty(PropertyType.HEALTH).setValue((double)character.getProperty(PropertyType.MAX_HEALTH).getValue());
				}
				
				//EXP UPDATES
				player.setLevel((int)character.getProperty(PropertyType.LEVEL).getValue());
				double expRatio = (double)((int)character.getProperty(PropertyType.EXPERIENCE).getValue() / character.getExperienceToNextLevel());
				player.setExp((float)expRatio);
				
				//HEALTH UPDATE
				if(!player.isDead())
					player.setHealth((double)character.getProperty(PropertyType.HEALTH).getValue());
				
				//MANA UPDATE
				player.setFoodLevel((int)((double)character.getProperty(PropertyType.MANA).getValue()));
				
				//REGEN
				if(count == 20) {
					count = 0;
					character.heal(1);
				}
				delta--;
			}
		}
	}
	
}
