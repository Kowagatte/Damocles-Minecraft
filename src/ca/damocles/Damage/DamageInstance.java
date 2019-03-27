package ca.damocles.Damage;

import org.bukkit.entity.Damageable;
import org.bukkit.entity.Entity;
import org.bukkit.entity.LivingEntity;
import org.bukkit.entity.Player;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Account.Character.Character;
import ca.damocles.Account.Character.Property.PropertyType;
import ca.damocles.Damocles;
import ca.damocles.Account.Account;

public class DamageInstance {
	
	private double rawDamage;
	private DDamageCause cause;
	private DamageType type;
	
	private Damageable entity;
	private ItemStack[] entityArmorContents;
	private ItemStack offHand;
	
	private Entity damager;
	private ItemStack damagerWeapon, damagerOffHand;
	
	private boolean wasProvoked;
	private boolean isPlayer;
	
	private double finalDamage = 0.0;
	
	private DamageInstance(Builder builder) {
		rawDamage = builder.rawDamage;
		cause = builder.cause;
		type = builder.type;
		entity = builder.entity;
		entityArmorContents = builder.entityArmorContents;
		offHand = builder.offHand;
		damager = builder.damager;
		damagerWeapon = builder.damagerWeapon;
		damagerOffHand = builder.damagerOffHand;
		wasProvoked = wasProvoked();
		isPlayer = isPlayer();
	}
	
	public double damageAfterArmor() {
		//TODO Armor Protection Calculation.
		return finalDamage;
	}
	
	public double damageAfterEnchantments(){
		//TODO Enchantment Protection Calculation
		return rawDamage;
	}
	
	public double damageAfterSpells() {
		//TODO Spell Protection Calculation
		return rawDamage;
	}
	
	public void finalDamageCalculation() {
		finalDamage = rawDamage;
		switch(type) {
			case LETHAL:
				finalDamage = damageAfterSpells();
				finalDamage = damageAfterEnchantments();
				break;
			case PHYSICAL:
				finalDamage = damageAfterArmor();
				finalDamage = damageAfterSpells();
				finalDamage = damageAfterEnchantments();
				break;
			case MAGICAL:
				finalDamage = damageAfterArmor();
				finalDamage = damageAfterEnchantments();
				break;
			case ANCIENT:
				finalDamage = damageAfterEnchantments();
				break;
			default:
				break;
		}
		return;
	}
	
	public void inflict() {
		if(isPlayer) {
			Account account = Damocles.getAccount(((Player)entity).getUniqueId());
			if(account.isInCharacter()) {
				account.getLoadedCharacter().damage(finalDamage);
				((Player)entity).damage(0.1);
			}
			return;
		}else {
			entity.setHealth(entity.getHealth() - finalDamage);
			return;
		}
	}
	
	public void damage() {
		if(entity == null)
			return;
		
		if(entity instanceof LivingEntity) {
			
			if(wasProvoked) {
				//TRIGGER PROVOKER EVENTS
			}
			
			//TRIGGER PROVOKED EVENTS
			
			
			finalDamageCalculation();
			inflict();
			
		}else {
			inflict();
		}
		
	}
	
	private boolean isPlayer() {
		if(entity instanceof Player) {
			return true;
		}
		return false;
	}
	
	private boolean wasProvoked() {
		if(damager == null)
			return false;
		return true;
	}
	
	public static class Builder {
		
		private double rawDamage = 0.0;
		private DDamageCause cause = null;
		private DamageType type = null;
		
		private Damageable entity = null;
		private ItemStack[] entityArmorContents = {};
		private ItemStack offHand = null;
		
		private Entity damager = null;
		private ItemStack damagerWeapon = null, damagerOffHand = null;
		
		public Builder(double rawDamage, DDamageCause cause, DamageType type) {
			this.rawDamage = rawDamage;
			this.cause = cause;
			this.type = type;
		}
		
		public Builder setDamagedEntity(Damageable entity, ItemStack[] armorContents, ItemStack offHand) {
			this.entity = entity;
			this.entityArmorContents = armorContents;
			this.offHand = offHand;
			return this;
		}
		
		public Builder setDamagingEntity(Entity damager, ItemStack damagerWeapon, ItemStack damagerOffHand) {
			this.damager = damager;
			this.damagerWeapon = damagerWeapon;
			this.damagerOffHand = damagerOffHand;
			return this;
		}
		
		public DamageInstance build() {
			return new DamageInstance(this);
		}
		
	}
	
}
