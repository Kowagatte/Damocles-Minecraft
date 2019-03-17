package ca.damocles.FileIO;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.Location;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import org.bukkit.event.inventory.InventoryType;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Damocles;

public class ConfigFile {
	
	private boolean newFile;
	private File file;
	private FileConfiguration config;
	
	public ConfigFile(File path, String name) {
    	newFile = false;
    	file = path.isDirectory() ? new File(path, name) : new File(Damocles.directories.DAMOCLES, name);
        if (!file.exists()) {
            try {
            	newFile = true;
            	file.createNewFile();
            } catch (IOException e) { e.printStackTrace(); }
        }
        config = YamlConfiguration.loadConfiguration(file);
	}
	
	public boolean isInt(String path) {
		return config.isInt(path);
	}
	
	public boolean isDouble(String path) {
		return config.isDouble(path);
	}
	
	public int getInt(String path) {
		return config.getInt(path);
	}
	
	public double getDouble(String path) {
		return config.getDouble(path);
	}
	
	public String getString(String path) {
		return config.getString(path);
	}
	
	public Inventory getInventory(String path) {
		List<Map<?, ?>> inventories = config.getMapList(path);
		@SuppressWarnings("unchecked")
		HashMap<Integer, ItemStack> hashInventory = (HashMap<Integer, ItemStack>) inventories.get(0);
		Inventory inv = Bukkit.createInventory(null, InventoryType.PLAYER);
		for(Integer i : hashInventory.keySet()){
			ItemStack item = hashInventory.get(i);
			inv.setItem(i, item);
		}
		return inv;
	}
	
	public void setInventory(String path, Inventory inventory) {
		List<Map<?, ?>> inventories = config.getMapList(path);
		HashMap<Integer, ItemStack> hashInventory = new HashMap<Integer, ItemStack>();
		for(int i = 0; i < inventory.getSize(); i++){
			ItemStack item = inventory.getItem(i);
			if(item != null)
				hashInventory.put(i, item);
			
		}
		inventories.add(hashInventory);
		config.set("inventory", inventories);
	}
	
	public Location getLocation(String path) {
		String string = config.getString(path);
		String[] s = string.split("/");
		return new Location(Bukkit.getServer().getWorld(s[0]), Double.valueOf(s[1]), Double.valueOf(s[2]), Double.valueOf(s[3]), Float.valueOf(s[4]), Float.valueOf(s[5]));
	}
	
	public void setLocation(String path, Location location) {
		config.set("location", location.getWorld().getName()+"/"+location.getX()+"/"+location.getY()+"/"+location.getZ()+"/"+location.getYaw()+"/"+location.getPitch());
	}
	
	public Object get(String path) {
		return config.get(path);
	}
	
	public void set(String path, Object value) {
		config.set(path, value);
	}
	
	public File getFile() {
		return file;
	}
	
	public boolean isNewFile() {
		return newFile;
	}
	
	public void save() {
		try {
			config.save(file);
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	public void delete() {
		file.delete();
	}
	
}
