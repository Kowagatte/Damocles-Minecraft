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
				player.getAttribute(Attribute.GENERIC_MAX_HEALTH).setBaseValue(character.getAttributeValue(ca.damocles.Account.Character.Attribute.BASE_MAX_HEALTH));
				character.setAttributeValue(ca.damocles.Account.Character.Attribute.MAX_HEALTH, player.getAttribute(Attribute.GENERIC_MAX_HEALTH).getValue());
				if(character.getAttributeValue(ca.damocles.Account.Character.Attribute.HEALTH) > character.getAttributeValue(ca.damocles.Account.Character.Attribute.MAX_HEALTH)) {
					character.setAttributeValue(ca.damocles.Account.Character.Attribute.HEALTH, character.getAttributeValue(ca.damocles.Account.Character.Attribute.MAX_HEALTH));
				}
				
				//EXP UPDATES
				player.setLevel((int)character.getAttributeValue(ca.damocles.Account.Character.Attribute.LEVEL));
				double expRatio = character.getAttributeValue(ca.damocles.Account.Character.Attribute.EXPERIENCE) / character.getExperienceToNextLevel();
				player.setExp(0.0f);
				player.setExp((float)(expRatio * (double)player.getExpToLevel()));
				
				//HEALTH UPDATE
				if(!player.isDead())
					player.setHealth(character.getAttributeValue(ca.damocles.Account.Character.Attribute.HEALTH));
				
				//MANA UPDATE
				player.setFoodLevel((int)character.getAttributeValue(ca.damocles.Account.Character.Attribute.MANA));
				
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
