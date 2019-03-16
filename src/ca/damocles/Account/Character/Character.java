package ca.damocles.Account.Character;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Damocles;
import ca.damocles.FileIO.ConfigFile;
import ca.damocles.Threads.CharacterUpdater;

public class Character {
	
	private final int id;
	public ConfigFile config;
	 
	private UUID uuid;
	private CharacterUpdater updater;
	
	private Map<Attribute, Double> attributes = new HashMap<Attribute, Double>();
	private Nature nature = Nature.getRandomNature();
	private String username = "UNKNOWN";
	private List<StatInstance> stats = new ArrayList<>();
	private Inventory inventory = Bukkit.getServer().createInventory(null, InventoryType.PLAYER);;
	private Location location = Damocles.getDefaultWorld().getSpawnLocation();
	
	public Character(UUID uuid, int id, ConfigFile config) {
		this.uuid = uuid;
		this.config = config;
		this.id = id;
		for(Stat stat : Stat.values()) {
			stats.add(new StatInstance(stat, 0));
		}
		if(config.isNewFile()) {
			getDefaultValues();
			saveToFile();
		}
		loadFromFile();
	}
	
	public void login() {
		this.updater = new CharacterUpdater(Bukkit.getPlayer(uuid), this);
	}
	
	public void logout() {
		updater.setActive(false);
		if(!updater.isInterrupted())
			updater.interrupt();
		saveToFile();
	}
	
	public int getID() {
		return id;
	}
	
	public StatInstance getStat(Stat stat) {
		for(StatInstance instance : stats) {
			if(instance.getStat() == stat)
				return instance;
		}
		return null;
	}
	
	public void heal(int amount) {
		double difference = getAttributeValue(Attribute.MAX_HEALTH) - getAttributeValue(Attribute.HEALTH);
		if(difference >= amount) {
			setAttributeValue(Attribute.HEALTH, getAttributeValue(Attribute.HEALTH) + amount);
		}else {
			setAttributeValue(Attribute.HEALTH, getAttributeValue(Attribute.MAX_HEALTH));
		}
	}
	
	public double getAttributeValue(Attribute attribute) {
		return attributes.get(attribute);
	}
	
	public void setAttributeValue(Attribute attribute, double value) {
		if(attributes.containsKey(attribute)) {
			attributes.replace(attribute, value);
			return;
		}
		attributes.put(attribute, value);
		return;
	}
	
	public Nature getNature() {
		return nature;
	}
	
	public void setNature(Nature nature) {
		this.nature = nature;
	}
	
	public double getExperienceToNextLevel() {
		return new Level((int)getAttributeValue(Attribute.LEVEL), getNature()).experienceToNextLevel();
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
	
	public void loadFromFile() {
		for(Attribute attribute : Attribute.values()) {
			setAttributeValue(attribute, config.config.getDouble(attribute.toString()));
		}
		for(Stat stat : Stat.values()) {
			getStat(stat).setValue(config.config.getInt("Stat."+stat.toString()));
		}
		setUsername(config.config.getString("username"));
		setNature(Nature.valueOf(config.config.getString("nature")));
		setLocation(getSavedLocation());
		setInventory(getSavedInventory());
	}
	
	public void saveToFile() {
		for(Attribute attribute : Attribute.values()) {
			config.config.set(attribute.toString(), getAttributeValue(attribute));
		}
		for(Stat stat : Stat.values()) {
			config.config.set("Stat."+stat.toString(), getStat(stat).getValue());
		}
		config.config.set("username", getUsername());
		config.config.set("nature", getNature().toString());
		config.save();
		setSavedInventory(getInventory());
		setSavedLocation(getLocation());
	}
	
	public void getDefaultValues() {
		ConfigFile file = new ConfigFile(Damocles.directories.DAMOCLES, "CHARACTER_DEFAULTS.yml");
		for(Attribute attribute : Attribute.values()) {
			setAttributeValue(attribute, file.config.getDouble(attribute.toString()));
		}
		for(Stat stat : Stat.values()) {
			getStat(stat).setValue(file.config.getInt("Stat."+stat.toString()));
		}
	}
	
	public Location getSavedLocation() {
		String string = config.config.getString("location");
		String[] s = string.split("/");
		return new Location(Bukkit.getServer().getWorld(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]), Double.valueOf(s[3]), Float.valueOf(s[4]), Float.valueOf(s[5]));
	}
	
	public void setSavedLocation(Location location) {
		config.config.set("location", location.getWorld().getName()+"/"+location.getX()+"/"+location.getY()+"/"+location.getZ()+"/"+location.getYaw()+"/"+location.getPitch());
		config.save();
	}
	
	public Inventory getSavedInventory(){
		List<Map<?, ?>> inventories = config.config.getMapList("inventory");
		@SuppressWarnings("unchecked")
		HashMap<Integer, ItemStack> hashInventory = (HashMap<Integer, ItemStack>) inventories.get(0);
		Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
		for(Integer i : hashInventory.keySet()){
			ItemStack item = hashInventory.get(i);
			inv.setItem(i, item);
		}
		return inv;
	}
	
	public void setSavedInventory(Inventory inventory){
		List<Map<?, ?>> inventories = config.config.getMapList("inventory");
		HashMap<Integer, ItemStack> hashInventory = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < inventory.getSize(); i++){
			ItemStack item = inventory.getItem(i);
			if(item != null)
				hashInventory.put(i, item);
			
		}
		inventories.add(hashInventory);
		config.config.set("inventory", inventories);
		config.save();
	}
	
}
