package ca.damocles.FileIO;

import java.io.File;

import ca.damocles.Damocles;
import ca.damocles.Account.Character.Character;
import ca.damocles.Account.Character.Nature;
import ca.damocles.Account.Character.Property.DoubleProperty;
import ca.damocles.Account.Character.Property.GenericProperty;
import ca.damocles.Account.Character.Property.IntProperty;
import ca.damocles.Account.Character.Property.Property;
import ca.damocles.Account.Character.Property.PropertyType;
import ca.damocles.Account.Character.Stat.Stat;
import ca.damocles.Account.Character.Stat.StatInstance;

public class CharacterConfigFile extends ConfigFile{
	
	private Character character;

	public CharacterConfigFile(File path, String name, Character character) {
		super(path, name);
		this.character = character;
	}
	
	public void loadCharacter() {
		loadProperties(this);
		for(Stat stat : Stat.values()) {
			character.getStat(stat).setValue(getInt("Stat."+stat.toString()));
		}
		character.setUsername(getString("username"));
		character.setNature(Nature.valueOf(getString("nature")));
		character.setLocation(getLocation("location"));
		character.setInventory(getInventory("inventory"));
	}
	
	public void saveToFile() {
		for(Property property : character.getProperties()) {
			set(property.getName(), property.getValue());
		}
		for(StatInstance stat : character.getStats()) {
			set("Stat."+stat.getStat().toString(), stat.getValue());
		}
		set("username", character.getUsername());
		set("nature", character.getNature().toString());
		setInventory("inventory", character.getInventory());
		setLocation("inventory", character.getLocation());
		save();
	}
	
	public void getDefaultValues() {
		ConfigFile defaults = new ConfigFile(Damocles.directories.DAMOCLES, "CHARACTER_DEFAULTS.yml");
		loadProperties(defaults);
		for(Stat stat : Stat.values()) {
			String path = "Stat."+stat.toString();
			if(defaults.get(path) != null) {
				if(defaults.isInt(path)) {
					character.addStat(new StatInstance(stat, defaults.getInt(path)));
				}
			}
		}
	}
	
	public void loadProperties(ConfigFile arg) {
		for(PropertyType type : PropertyType.values()) {
			String path = type.toString();
			if(arg.get(path) != null) {
				if(arg.isInt(path)) {
					character.addProperty(new IntProperty(type, new Integer(arg.getInt(path))));
				}else if(arg.isDouble(path)) {
					character.addProperty(new DoubleProperty(type, new Double(arg.getDouble(path))));
				}else {
					character.addProperty(new GenericProperty(type, arg.get(path)));
				}
			}
		}
	}

}
