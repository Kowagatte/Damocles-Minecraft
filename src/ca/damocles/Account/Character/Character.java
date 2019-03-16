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
import ca.damocles.Account.Character.Property.DoubleProperty;
import ca.damocles.Account.Character.Property.GenericProperty;
import ca.damocles.Account.Character.Property.IntProperty;
import ca.damocles.Account.Character.Property.Property;
import ca.damocles.Account.Character.Property.PropertyType;
import ca.damocles.Account.Character.Stat.Stat;
import ca.damocles.Account.Character.Stat.StatInstance;
import ca.damocles.FileIO.ConfigFile;
import ca.damocles.Threads.CharacterUpdater;

public class Character {
	
	private final int id;
	public ConfigFile config;
	 
	private UUID uuid;
	private CharacterUpdater updater;
	
	private Nature nature = Nature.getRandomNature();
	private String username = "UNKNOWN";
	private List<StatInstance> stats = new ArrayList<>();
	private List<Property> properties = new ArrayList<>();
	private Inventory inventory = Bukkit.getServer().createInventory(null, InventoryType.PLAYER);;
	private Location location = Damocles.getDefaultWorld().getSpawnLocation();
	
	public Character(UUID uuid, int id, ConfigFile config) {
		this.uuid = uuid;
		this.config = config;
		this.id = id;
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
		double difference = (double)getProperty(PropertyType.MAX_HEALTH).getValue() - (double)getProperty(PropertyType.HEALTH).getValue();
		if(difference >= amount) {
			getProperty(PropertyType.HEALTH).setValue((double)getProperty(PropertyType.HEALTH).getValue() + amount);
		}else {
			getProperty(PropertyType.HEALTH).setValue((double)getProperty(PropertyType.MAX_HEALTH).getValue());
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
	
	public void loadFromFile() {
		loadProperties(config);
		for(Stat stat : Stat.values()) {
			getStat(stat).setValue(config.config.getInt("Stat."+stat.toString()));
		}
		setUsername(config.config.getString("username"));
		setNature(Nature.valueOf(config.config.getString("nature")));
		setLocation(getSavedLocation());
		setInventory(getSavedInventory());
	}
	
	public void saveToFile() {
		for(Property property : properties) {
			config.config.set(property.getName(), property.getValue());
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
		loadProperties(file);
		for(Stat stat : Stat.values()) {
			String path = "Stat."+stat.toString();
			if(file.config.get(path) != null) {
				if(file.config.isInt(path)) {
					stats.add(new StatInstance(stat, file.config.getInt(path)));
				}
			}
		}
	}
	
	public void loadProperties(ConfigFile file) {
		for(PropertyType type : PropertyType.values()) {
			String path = type.toString();
			if(file.config.get(path) != null) {
				if(file.config.isInt(path)) {
					addProperty(new IntProperty(type, new Integer(file.config.getInt(path))));
				}else if(file.config.isDouble(path)) {
					addProperty(new DoubleProperty(type, new Double(file.config.getDouble(path))));
				}else {
					addProperty(new GenericProperty(type, file.config.get(path)));
				}
			}
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
