package ca.damocles.Account.Character;

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
import ca.damocles.Threads.CharacterUpdater;
import ca.damocles.utils.ConfigFile;

public class Character {
	
	private final int id;
	ConfigFile config;
	 
	private UUID uuid;
	private CharacterUpdater updater;
	
	private double health;
	private double maxHealth;
	private double mana;
	private double maxMana;
	private double speed;
	private Nature nature;
	private String username;
	private int souls;
	private int level;
	private int experience;
	private Inventory inventory;
	private Location location;
	
	public Character(UUID uuid, int id, ConfigFile config) {
		this.uuid = uuid;
		this.config = config;
		if(config.isNewFile()) {
			this.id = id;
			health = 6;
			maxHealth = 6;
			mana = 0;
			maxMana = 0;
			speed = 0.2;
			username = "UNKNOWN";
			nature = Nature.getRandomNature();
			souls = 3;
			level = 1;
			experience = 0;
			location = Damocles.getDefaultWorld().getSpawnLocation();
			inventory = Bukkit.getServer().createInventory(null, InventoryType.PLAYER);
			saveToFile();
		}else {
			this.id = id;
			loadFromFile();
		}
	}
	
	public void login() {
		this.updater = new CharacterUpdater(Bukkit.getPlayer(uuid), this);
	}
	
	public void logout() {
		updater.setActive(false);
		if(!updater.isInterrupted())
			updater.interrupt();
	}
	
	public Nature getNature() {
		return nature;
	}
	
	public int getExperienceToNextLevel() {
		return new Level(getLevel(), getNature()).experienceToNextLevel();
	}
	
	public int getID() {
		return id;
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
	
	public double getMana() {
		return mana;
	}

	public void setMana(double mana) {
		this.mana = mana;
	}

	public double getHealth() {
		return health;
	}

	public void setHealth(double health) {
		this.health = health;
	}

	public double getMaxHealth() {
		return maxHealth;
	}

	public void setMaxHealth(double maxHealth) {
		this.maxHealth = maxHealth;
	}

	public double getMaxMana() {
		return maxMana;
	}

	public void setMaxMana(double maxMana) {
		this.maxMana = maxMana;
	}
	
	public double getSpeed() {
		return speed;
	}
	
	public void setSpeed(double speed) {
		this.speed = speed;
	}
	
	public String getUsername() {
		return username;
	}
	
	public void setUsername(String username) {
		this.username = username;
	}
	
	public int getSouls() {
		return souls;
	}
	
	public void setSouls(int souls) {
		this.souls = souls;
	}
	
	public void addSouls(int souls) {
		this.souls = getSouls() + souls;
	}
	
	public void removeSouls(int souls) {
		if(getSouls() >= 1) {
			this.souls = getSouls() - souls;
		}
	}
	
	public int getLevel() {
		return level;
	}
	
	public void setLevel(int level) {
		this.level = level;
	}
	
	public void addLevel(int level) {
		this.level = getLevel() + level;
	}
	
	public int getExperience() {
		return experience;
	}
	
	public void setExperience(int exp) {
		this.experience = exp;
	}
	
	public void addExperience(int exp) {
		this.experience = getExperience() + exp;
	}
	
	public void loadFromFile() {
		health = config.config.getDouble("health");
		maxHealth = config.config.getDouble("maxHealth");
		mana = config.config.getDouble("mana");
		maxMana = config.config.getDouble("maxMana");
		speed = config.config.getDouble("speed");
		username = config.config.getString("username");
		nature = Nature.valueOf(config.config.getString("nature"));
		souls = config.config.getInt("souls");
		level = config.config.getInt("level");
		experience = config.config.getInt("experience");
		location = getSavedLocation();
		inventory = getSavedInventory();
	}
	
	public Location getSavedLocation() {
		String string = config.config.getString("location");
		String[] s = string.split("/");
		return new Location(Bukkit.getServer().getWorld(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]), Double.valueOf(s[3]), Float.valueOf(s[4]), Float.valueOf(s[5]));
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
	
	public void saveToFile() {
		config.config.set("health", health);
		config.config.set("maxHealth", maxHealth);
		config.config.set("mana", mana);
		config.config.set("maxMana", maxMana);
		config.config.set("speed", speed);
		config.config.set("nature", nature.toString());
		config.config.set("username", username);
		config.config.set("souls", souls);
		config.config.set("level", level);
		config.config.set("experience", experience);
		config.save();
		setSavedInventory(inventory);
		setSavedLocation(location);
	}
	
	public void setSavedLocation(Location location) {
		config.config.set("location", location.getWorld().getName()+"/"+location.getX()+"/"+location.getY()+"/"+location.getZ()+"/"+location.getYaw()+"/"+location.getPitch());
		config.save();
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
