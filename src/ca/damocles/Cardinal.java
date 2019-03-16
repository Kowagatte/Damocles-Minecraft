package ca.damocles;

import java.io.File;
import java.io.IOException;
import org.bukkit.Bukkit;
import org.bukkit.plugin.InvalidDescriptionException;
import org.bukkit.plugin.InvalidPluginException;
import org.bukkit.plugin.Plugin;
import org.bukkit.plugin.UnknownDependencyException;
import org.bukkit.plugin.java.JavaPlugin;
import org.bukkit.scheduler.BukkitRunnable;

import com.quickprogrammingtips.ASCIIArtGenerator;
import com.quickprogrammingtips.ASCIIArtGenerator.ASCIIArtFont;

import ca.damocles.Events.LoginMenuEvent;
import ca.damocles.Events.PlayerJoin;
import ca.damocles.Events.PlayerLeave;
import ca.damocles.Events.PlayerShiftClick;
import ca.damocles.Events.Player.CancelRegen;
import ca.damocles.Events.Player.CancelStarvation;
import ca.damocles.FileIO.ZipUtils;

public class Cardinal extends JavaPlugin{
	
	private static Plugin pluginInstance;
	public static Plugin getInstance() { return pluginInstance; }
	
	public void onEnable() {
		pluginInstance = this;
		registerConfig();
		registerEvents();
		if(isFirstTime())
			firstTimeSetUp();
		enableMessage();
		buildDependencies();
	}
	
	public void onDisable() {
		pluginInstance = null;
	}
	
	public void registerEvents() {
		Bukkit.getPluginManager().registerEvents(new PlayerJoin(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerLeave(), this);
		Bukkit.getPluginManager().registerEvents(new LoginMenuEvent(), this);
		Bukkit.getPluginManager().registerEvents(new PlayerShiftClick(), this);
		Bukkit.getPluginManager().registerEvents(new CancelRegen(), this);
		Bukkit.getPluginManager().registerEvents(new CancelStarvation(), this);
	}
	
	public void registerConfig(){
		getConfig().options().copyDefaults(true);
		saveConfig();
		Damocles.directories.exportResource("/CHARACTER_DEFAULTS.yml");
	}
	
	public boolean isFirstTime() {
		for(File file : Damocles.directories.listAllFilesInDirectory(Damocles.directories.DAMOCLES)) {
			if(file.getName().contains("DO_NOT_DELETE")) {
				return false;
			}
		}
		return true;
	}
	
	public void firstTimeSetUp() {
		File file = new File(Damocles.directories.DAMOCLES, "DO_NOT_DELETE.txt");
		try {
			file.createNewFile();
			Damocles.directories.CLAN.mkdir();
			Damocles.directories.PLAYERS.mkdir();
		} catch (IOException e) { e.printStackTrace(); }
	}
	
	/**
	 * Builds all dependencies Damocles relies on.
	 * @throws UnknownDependencyException
	 * @throws InvalidPluginException
	 * @throws InvalidDescriptionException
	 */
	public void buildDependencies() {
		if(!checkIfDependenciesInstalled()) {
		    try {
		    	ZipUtils.unzip(Cardinal.class.getClassLoader().getResourceAsStream("dependencies.zip"), Damocles.directories.PLUGIN);
			    Bukkit.dispatchCommand(Bukkit.getConsoleSender(), "reload");
			} catch (IOException | UnknownDependencyException e) { e.printStackTrace(); }
		}
	}
	
	/**
	 * Checks if Damocles is missing any dependencies.
	 * @return false if any dependencies are missing.
	 */
	public boolean checkIfDependenciesInstalled() {
		if((Bukkit.getPluginManager().getPlugin("MultiLineAPI") == null)
				&& (Bukkit.getPluginManager().getPlugin("ProtocolLib") == null)
				&& (Bukkit.getPluginManager().getPlugin("ActionBarAPI") == null)
				&& (Bukkit.getPluginManager().getPlugin("PacketEntityAPI") == null)) {
			return false;
		}
		return true;
	}
	
	public void enableMessage() {
		new BukkitRunnable(){
			public void run(){
				ASCIIArtGenerator artGen = new ASCIIArtGenerator();

				System.out.println("-----------------------------------------------------------------------------------------------");
				try {
					artGen.printTextArt("Damocles", ASCIIArtGenerator.ART_SIZE_MEDIUM, ASCIIArtFont.ART_FONT_MONO,"@");
				} catch (Exception e) { e.printStackTrace(); }
				System.out.println("-----------------------------------------------------------------------------------------------");
				
				System.out.println("For first time setup please read the README.txt file for detailed usage.");
				System.out.println(" ");
				System.out.println("Currently using Spigot Version: " + Bukkit.getBukkitVersion());
				System.out.println(" ");
				System.out.println("-=- Currently Enabled Plugins -=-");
				
				for(Plugin plugin : Bukkit.getPluginManager().getPlugins()){
					System.out.println("\t- " + plugin.getDescription().getName() + " : " + plugin.getDescription().getVersion());
				}
				
				System.out.println("-----------------------------------------------------------------------------------------------");
				
				this.cancel();
			}
		}.runTaskLaterAsynchronously(this, 20);
	}
	
}
