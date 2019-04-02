package ca.damocles;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.UUID;

import org.bukkit.Bukkit;
import org.bukkit.ChatColor;
import org.bukkit.World;
import org.bukkit.entity.Player;

import ca.damocles.Account.Account;
import ca.damocles.FileIO.ConfigFile;
import ca.damocles.FileIO.DamoclesDirectory;
import ca.damocles.utils.DefaultFontInfo;

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
					names.add(new ConfigFile(player.getParentFile(), player.getName()).getString("username"));
			}
		}
		return names;
	}
	
	private final static int CENTER_PX = 154;

	public static void sendCenteredMessage(Player player, String message){
	        if(message == null || message.equals("")) player.sendMessage("");
	                message = ChatColor.translateAlternateColorCodes('&', message);
	               
	                int messagePxSize = 0;
	                boolean previousCode = false;
	                boolean isBold = false;
	               
	                for(char c : message.toCharArray()){
	                        if(c == '§'){
	                                previousCode = true;
	                                continue;
	                        }else if(previousCode == true){
	                                previousCode = false;
	                                if(c == 'l' || c == 'L'){
	                                        isBold = true;
	                                        continue;
	                                }else isBold = false;
	                        }else{
	                                DefaultFontInfo dFI = DefaultFontInfo.getDefaultFontInfo(c);
	                                messagePxSize += isBold ? dFI.getBoldLength() : dFI.getLength();
	                                messagePxSize++;
	                        }
	                }
	               
	                int halvedMessageSize = messagePxSize / 2;
	                int toCompensate = CENTER_PX - halvedMessageSize;
	                int spaceLength = DefaultFontInfo.SPACE.getLength() + 1;
	                int compensated = 0;
	                StringBuilder sb = new StringBuilder();
	                while(compensated < toCompensate){
	                        sb.append(" ");
	                        compensated += spaceLength;
	                }
	                player.sendMessage(sb.toString() + message);
	        }
	
}
