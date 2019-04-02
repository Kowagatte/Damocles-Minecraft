package ca.damocles.Commands;

import java.util.ArrayList;
import java.util.List;

import org.bukkit.ChatColor;
import org.bukkit.command.Command;
import org.bukkit.command.CommandExecutor;
import org.bukkit.command.CommandSender;
import org.bukkit.entity.Player;

import ca.damocles.Damocles;
import ca.damocles.Items.Factories.ProtectionRuneFactory;
import ca.damocles.Items.Factories.RuneFactory;
import ca.damocles.Runes.Rune;

public class RuneCommand implements CommandExecutor{

	@Override
	public boolean onCommand(CommandSender sender, Command cmd, String label, String[] args) {
		
		if(!(sender instanceof Player)) {
			sender.sendMessage("Only players can use the /rune command.");
		}
		
		Player player = (Player)sender;
		
		if(cmd.getName().equalsIgnoreCase("rune")) {
			if(args.length == 4) {
				try {
					player.getInventory().addItem(new RuneFactory(Rune.valueOf(args[0].toUpperCase()))
							.setLevel(Integer.valueOf(args[1]))
							.setSuccess(Integer.valueOf(args[2]))
							.setDestroy(Integer.valueOf(args[3]))
							.build());
					return true;
				}catch(NumberFormatException e) {
					Damocles.sendCenteredMessage(player, ChatColor.BOLD+"Invalid Arguments, use /rune for help.");
					return true;
				}
			}else if(args.length == 1){
				if(args[0].equalsIgnoreCase("protection")) {
					player.getInventory().addItem(new ProtectionRuneFactory()
							.build());
					return true;
				}else if(args[0].equalsIgnoreCase("list")){
					List<String> runeNames = new ArrayList<>();
					for(Rune rune : Rune.values()) {
						if(rune != Rune.BLANK) {
							runeNames.add(rune.getName());
						}
					}
					String s = String.join(", ", runeNames);
					Damocles.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					Damocles.sendCenteredMessage(player, ChatColor.BOLD + "Rune List");
					Damocles.sendCenteredMessage(player, ChatColor.YELLOW + s);
					Damocles.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
					return true;
				}else {
					Damocles.sendCenteredMessage(player, ChatColor.BOLD+"No such subcommand, use /rune for help.");
					return true;
				}
			}else {
				Damocles.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				Damocles.sendCenteredMessage(player, ChatColor.BOLD + "Rune Help");
				Damocles.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune list "+ChatColor.GOLD+": for a list of Runes.");
				Damocles.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune <Enchantment> <level> <Success Rate> <Destroy Rate>");
				Damocles.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune slot <Amount> "+ChatColor.GOLD+": gives a Slot Rune.");
				Damocles.sendCenteredMessage(player, ChatColor.YELLOW + "/Rune protection "+ChatColor.GOLD+": gives a Protection Rune.");
				Damocles.sendCenteredMessage(player, ChatColor.GREEN+"----------------------------------------------------");
				return true;
			}
		}
		return true;
	}
}
