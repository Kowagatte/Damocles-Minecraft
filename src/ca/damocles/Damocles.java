package ca.damocles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.World;
import ca.damocles.Account.Account;
import ca.damocles.FileIO.ConfigFile;
import ca.damocles.FileIO.DamoclesDirectory;

public class Damocles {
	
	public static DamoclesDirectory directories = new DamoclesDirectory();
	public static List<Account> accounts = new ArrayList<>();
	
	public static Account getAccount(UUID uuid) {
		for (Account account : accounts) {
			if(account.getUUID() == uuid)
				return account;
		}
		return null;
	}
	
	public static World getDefaultWorld() {
		String worldName = "";
		File file = new File(directories.SERVER, "server.properties");
		try {
			Scanner in = new Scanner(new FileReader(file));
			while(in.hasNext()) {
				String string = in.next();
				if(string.contains("level-name")) {
					string = string.replace("level-name=", "");
					worldName = string;
				}
			}
			in.close();
		} catch (FileNotFoundException e) { e.printStackTrace(); }
		return Bukkit.getServer().getWorld(worldName);
	}
	
	public static List<String> getAllUserNames() {
		List<String> names = new ArrayList<>();
		for(File folder : directories.listAllSubDirectories(directories.PLAYERS)) {
			for(File player : directories.listAllFilesInDirectory(folder)) {
				if(!player.getName().contains("info"))
					names.add(new ConfigFile(player.getParentFile(), player.getName()).config.getString("username"));
			}
		}
		return names;
	}
	
}
