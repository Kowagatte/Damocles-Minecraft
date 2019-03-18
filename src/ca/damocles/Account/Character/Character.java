package ca.damocles.Account.Character;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import ca.damocles.Damocles;
import ca.damocles.Account.Character.Property.Property;
import ca.damocles.Account.Character.Property.PropertyType;
import ca.damocles.Account.Character.Stat.Stat;
import ca.damocles.Account.Character.Stat.StatInstance;
import ca.damocles.FileIO.CharacterConfigFile;
import ca.damocles.Threads.CharacterUpdater;

public class Character {
	
	private final int id;
	public CharacterConfigFile config;
	 
	private UUID uuid;
	private CharacterUpdater updater;
	
	private Nature nature = Nature.getRandomNature();
	private String username = "UNKNOWN";
	private List<StatInstance> stats = new ArrayList<>();
	private List<Property> properties = new ArrayList<>();
	private Inventory inventory = Bukkit.getServer().createInventory(null, InventoryType.PLAYER);;
	private Location location = Damocles.getDefaultWorld().getSpawnLocation();
	
	public Character(UUID uuid, int id) {
		this.uuid = uuid;
		this.config = new CharacterConfigFile(new File(Damocles.directories.PLAYERS, uuid.toString()), id+".yml", this);
		this.id = id;
		if(config.isNewFile()) {
			this.config.getDefaultValues();
			this.config.saveToFile();
		}
		this.config.loadCharacter();
	}
	
	public void login() {
		this.updater = new CharacterUpdater(Bukkit.getPlayer(uuid), this);
	}
	
	public void logout() {
		updater.setActive(false);
		if(!updater.isInterrupted())
			updater.interrupt();
		config.saveToFile();
	}
	
	public int getID() {
		return id;
	}
	
	public List<Property> getProperties(){
		return properties;
	}
	
	public List<StatInstance> getStats(){
		return stats;
	}
	
	public StatInstance getStat(Stat stat) {
		for(StatInstance instance : stats) {
			if(instance.getStat() == stat)
				return instance;
		}
		return null;
	}
	
	public void heal(double amount) {
		double difference = (double)getProperty(PropertyType.MAX_HEALTH).getValue() - (double)getProperty(PropertyType.HEALTH).getValue();
		if(difference >= amount) {
			getProperty(PropertyType.HEALTH).setValue((double)getProperty(PropertyType.HEALTH).getValue() + amount);
		}else {
			getProperty(PropertyType.HEALTH).setValue((double)getProperty(PropertyType.MAX_HEALTH).getValue());
		}
	}
	
	public void regenMana(double amount) {
		double difference = (double)getProperty(PropertyType.MAX_MANA).getValue() - (double)getProperty(PropertyType.MANA).getValue();
		if(difference >= amount) {
			getProperty(PropertyType.MANA).setValue((double)getProperty(PropertyType.MANA).getValue() + amount);
		}else {
			getProperty(PropertyType.MANA).setValue((double)getProperty(PropertyType.MAX_MANA).getValue());
		}
	}
	
	public Property getProperty(PropertyType type) {
		for(Property property : properties) {
			if(property.getType() == type) {
				return property;
			}
		}
		return null;
	}
	
	public void addProperty(Property prop) {
		for(Property property : properties) {
			if(property.getType() == prop.getType()) {
				properties.set(properties.indexOf(property), prop);
				return;
			}
		}
		properties.add(prop);
		return;
	}
	
	public void addStat(StatInstance add) {
		for(StatInstance stat : stats) {
			if(stat.getStat() == add.getStat()) {
				stats.set(stats.indexOf(stat), add);
				return;
			}
		}
		stats.add(add);
		return;
	}
	
	public Nature getNature() {
		return nature;
	}
	
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	public void addExperience(int experience) {
		int neededExperience = (int)getProperty(PropertyType.EXPERIENCE).getValue() + getExperienceToNextLevel();
		int projectedExperience = (int) ((int)getProperty(PropertyType.EXPERIENCE).getValue() + experience);
		if(projectedExperience >= neededExperience) {
			levelUp();
		}
		getProperty(PropertyType.EXPERIENCE).setValue(projectedExperience);
	}
	
	public void levelUp() {
		getProperty(PropertyType.LEVEL).setValue((int)getProperty(PropertyType.LEVEL).getValue() + 1);
		//TODO LEVELUP
	}
	
	public int getExperienceToNextLevel() {
		return new Level((int)getProperty(PropertyType.LEVEL).getValue(), getNature()).experienceToNextLevel();
	}
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public void setInventory(Inventory inventory) {
		this.inventory = inventory;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
}
