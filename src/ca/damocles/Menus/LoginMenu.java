package ca.damocles.Menus;

import java.util.HashMap;
import java.util.Map;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.Material;
import org.bukkit.inventory.Inventory;
import org.bukkit.inventory.ItemStack;

import ca.damocles.Account.Account;
import ca.damocles.Account.Character.Character;
import ca.damocles.Account.Character.Character.Attribute;

public class LoginMenu extends Menu{
	
	private Inventory inventory;
	private Account account;
	private String username;
	public Map<Integer, CharacterItem> characterItems;
	
	private ItemStack newCharacter = this.createItem(ChatColor.BOLD + "" + ChatColor.DARK_GREEN + "CREATE NEW CHARACTER", ChatColor.GRAY+ "Click here to create a new Character", Material.QUARTZ_BLOCK);
	
	
	public LoginMenu(Account account) {
		this.account = account;
		this.username = Bukkit.getPlayer(account.getUUID()).getName();
		characterItems = new HashMap<>();
		int location = 0;
		for(Character character : account.getCharacters().values()) {
			characterItems.put(location, new CharacterItem(this, character));
			location++;
		}
		populateMenu();
	}
	
	public void populateMenu() {
		inventory = Bukkit.createInventory(null, getMenuSize(), getName());
		int location = 0;
		for(CharacterItem item : characterItems.values()) {
			inventory.setItem(location, item.getItemStack());
			location++;
		}
		if(account.getCharacters().size() < account.getMaxNumberOfCharacters()) {
			inventory.setItem(location, newCharacter);
			location++;
		}
		for(int i = 0; i < numberOfEmptySlots(); i++) {
			inventory.setItem(location, this.emptyItem);
			location++;
		}
		inventory.setItem(getMenuSize() - 1, this.createItem(ChatColor.BOLD + "" + ChatColor.DARK_RED + "LOGOUT", Material.REDSTONE_BLOCK));
	}
	
	public String getName() {
		return ChatColor.DARK_GRAY + username + "'s Characters";
	}
	
	public int numberOfEmptySlots() {
		double n = (account.getCharacters().size() < account.getMaxNumberOfCharacters()) ? 1 : 0;
		return (int) (getMenuSize() - (account.getCharacters().size() + 1 + n));
	}
	
	public int getMenuSize() {
		double n = (account.getCharacters().size() < account.getMaxNumberOfCharacters()) ? 1 : 0;
		double numOfChars = account.getCharacters().size() + 1 + n;
		int rows = (int)Math.ceil(numOfChars / 9.0);
		return rows * 9;
	}
	
	public Inventory getInventory() {
		return inventory;
	}
	
	public class CharacterItem {
		private Menu menu;
		private Character character;
		private ItemStack item;
		
		public CharacterItem(Menu menu, Character character) {
			this.menu = menu;
			this.character = character;
			update();
		}
		
		public void update() {
			item = menu.createItem(character.getUsername(), new String[] {
					"",
					ChatColor.GRAY + "Level: " + (int)character.getAttributeValue(Attribute.LEVEL),
					ChatColor.GRAY+ "Exp: "+ (int)character.getAttributeValue(Attribute.EXPERIENCE) +"/"+ character.getExperienceToNextLevel(),
					ChatColor.GRAY+ "Ability Points: ",
					"",
					ChatColor.GRAY+ "Strength: "+" Agility: ",
					ChatColor.GRAY+ "Hitpoints: "+" Intelligence: ",
					"",
					ChatColor.GRAY+"Max Health: "+((int)character.getAttributeValue(Attribute.MAX_HEALTH)),
					ChatColor.GRAY+"Max Mana: "+((int)character.getAttributeValue(Attribute.MAX_MANA)),
					ChatColor.GRAY+"Speed: "+character.getAttributeValue(Attribute.SPEED),
					"",
					}, Material.EMERALD_BLOCK);
		}
		
		public ItemStack getItemStack() {
			return item;
		}
		
		public Character getCharacter() {
			return character;
		}
		
		
	}
	
}
