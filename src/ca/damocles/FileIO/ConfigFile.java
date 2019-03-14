package ca.damocles.FileIO;

import java.io.File;
import java.io.IOException;

import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;
import ca.damocles.Damocles;

public class ConfigFile {
	
	private boolean newFile;
	private File file;
	public FileConfiguration config;
	
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
