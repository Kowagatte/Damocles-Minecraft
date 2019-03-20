package ca.damocles.Damage;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.Player;

import ca.damocles.Account.Character.Character;
import ca.damocles.Damocles;
import ca.damocles.Account.Account;

public class DamageInstance {
	
	private Damageable entity;
	private Entity damager;
	private double damage;
	private DDamageCause cause;
	private DamageType type;
	
	public DamageInstance(Damageable entity, Entity damager, double rawDamage, DDamageCause cause, DamageType type) {
		this.entity = entity;
		this.damager = damager;
		this.damage = rawDamage;
		this.cause = cause;
		this.type = type;
	}
	
	public double calculateArmor() {
		return 0.0;
	}
	
	public void damage() {
		if(entity == null)
			return;
		
		if(entity instanceof Player) {
			
			Account account = Damocles.getAccount(((Player)entity).getUniqueId());
			
			if(account.isInCharacter()) {
				
				Character character = account.getLoadedCharacter();
				
				
				
			}
			
			return;
			
		}else {
			
			
			
		}
	}
	
	private boolean wasProvoked() {
		if(damager == null)
			return false;
		return true;
	}
	
}
