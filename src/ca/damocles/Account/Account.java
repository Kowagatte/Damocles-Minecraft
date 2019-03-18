package ca.damocles.Account;

import java.io.File;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import ca.damocles.Damocles;
import ca.damocles.Account.Character.Character;
import ca.damocles.FileIO.ConfigFile;

public class Account {
	
	private File directory;
	private ConfigFile config;
	
	private AccountStatus status;
	private Priviledge priviledge;
	private UUID uuid;
	
	private int maxNumberOfCharacters;
	public Map<Integer, Character> characters;
	private int loadedCharacter;
	
	public Account(UUID uuid) {
		this.loadedCharacter = -1;
		this.uuid = uuid;
		this.status = AccountStatus.SELECTING_CHARACTER;
		directory = new File(Damocles.directories.PLAYERS, uuid.toString());
		exists();
		loadCharacters();
	}
	
	public UUID getUUID() {
		return uuid;
	}
	
	public AccountStatus getStatus() {
		return status;
	}
	
	public void setStatus(AccountStatus status) {
		this.status = status;
	}
	
	public int getMaxNumberOfCharacters() {
		return maxNumberOfCharacters;
	}
	
	public boolean isInCharacter() {
		if(loadedCharacter != -1) {
			return true;
		}
		return false;
	}
	
	public void logoutCharacter() {
		if(isInCharacter()) {
			characters.get(loadedCharacter).logout();
			characters.remove(loadedCharacter);
			loadedCharacter = -1;
		}
	}
	
	public void loginCharacter(int id) {
		if(!isInCharacter()) {
			this.loadedCharacter = id;
			this.status = AccountStatus.LOGGED_IN;
			characters.get(id).login();
		}
	}
	
	public Character getLoadedCharacter() {
		return characters.get(loadedCharacter);
	}
	
	public int createNewCharacter() {
		int goodID = -1;
		for(int i : characters.keySet()) {
			if(i > goodID) {
				goodID = i;
			}
		}
		goodID++;
		characters.put(goodID, new Character(uuid, goodID));
		return goodID;
	}
	

	public void purgeCharacter(int id) {
		Character character = characters.get(id);
		if(character != null) {
			characters.remove(id);
			new ConfigFile(directory, id+".yml").delete();
		}
	}
	
	public void loadCharacters() {
		characters = new HashMap<>();
		for(File file : Damocles.directories.listAllFilesInDirectory(directory)) {
			if(!file.getName().contains("info")) {
				int id = Integer.valueOf(file.getName().replace(".yml", ""));
				characters.put(id, new Character(uuid, id));
			}
		}
	}
	
	public Map<Integer, Character> getCharacters(){
		return characters;
	}
	
	private void exists() {
		boolean exists = directory.exists();
		if(!exists) {
			directory.mkdir();
		}
		config = new ConfigFile(directory, "info.yml");
		if(!exists) {
			priviledge = Priviledge.PLAYER;
			maxNumberOfCharacters = 3;
			saveInfo();
		}
		loadInfo();
	}
	
	private void loadInfo() {
		priviledge = Priviledge.valueOf(config.getString("priviledge"));
		maxNumberOfCharacters = config.getInt("max_number_of_characters");
	}
	
	public void saveInfo() {
		config.set("priviledge", priviledge.toString());
		config.set("max_number_of_characters", maxNumberOfCharacters);
		config.save();
	}
	
	public enum AccountStatus {
		SELECTING_CHARACTER,
		CREATING_CHARACTER,
		LOGGED_IN;
	}
	
	public enum Priviledge {
		ADMINISTRATOR((byte) 0),
		DEVELOPER((byte) 1),
		PLAYER((byte) 2);
		
		private byte id;
		Priviledge(byte id){ this.id = id; }
		
		public byte getID() { return id; }
		
		public Priviledge valueOf(byte id) {
			for(Priviledge e : Priviledge.values()) {
				if(e.getID() == id)
					return e;
			}
			return PLAYER;
		}
	}
	
}
