package ca.damocles.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.bukkit.ChatColor;

import ca.damocles.Damocles;
import ca.damocles.Items.Item;
import ca.damocles.Items.Interfaces.Durable;
import ca.damocles.Items.Interfaces.Inscribable;
import ca.damocles.Items.Types.Armor;
import ca.damocles.Items.Types.Sword;
import ca.damocles.Runes.Rune;
import ca.damocles.utils.RomanNumeral;

public class CustomLore {
	
	private File file;
	private Item item;
	private List<String> lines;
	
	public CustomLore(Item item) {
		this.item = item;
		lines = new ArrayList<>();
		File file = new File(Damocles.directories.ITEMLORES, item.getItemType().toString()+".txt");
		if(!file.exists()) {
			Damocles.directories.exportResource(item.getItemType().toString()+".txt", Damocles.directories.ITEMLORES);
		}
		this.file = new File(Damocles.directories.ITEMLORES, item.getItemType().toString()+".txt");
		readFromFile();
	}
	
	private void readFromFile() {
		BufferedReader br = null;
        try {
            br = new BufferedReader(new FileReader(file));
            String line;
            while ((line = br.readLine()) != null) {
                lines.add(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                if (br != null) {
                    br.close();
                }
            } catch (IOException ex) {
                ex.printStackTrace();
            }
        }
	}
	
	public boolean containsTag(String string, String tag) {
		if(string.contains("${"+tag+"}")) {
			return true;
		}
		return false;
	}
	
	public void replaceTagWithLines(String tag, String... newString) {
		List<String> end = new ArrayList<>();
		boolean found = false;
		for(String s : lines) {
			if(!containsTag(s, tag)) {
				if(!found) {
					end.add(s);
				}else {
					end.add(s);
				}
			}else {
				String built = s.replace("${"+tag+"}", "`");
				String[] dissect = built.split("`");
				for(String newLine : newString) {
					if(dissect.length == 2) {
						end.add(dissect[0] + newLine + dissect[1]);
					}else if(dissect.length == 1){
						end.add(dissect[0] + newLine);
					}else {
						end.add(newLine);
					}
				}
				found = true;
			}
		}
		lines = end;
	}
	
	public void replaceTagWithLines(String tag, List<String> newString) {
		List<String> end = new ArrayList<>();
		boolean found = false;
		for(String s : lines) {
			if(!containsTag(s, tag)) {
				if(!found) {
					end.add(s);
				}else {
					end.add(s);
				}
			}else {
				String built = s.replace("${"+tag+"}", "`");
				String[] dissect = built.split("`");
				for(String newLine : newString) {
					if(dissect.length == 2) {
						end.add(dissect[0] + newLine + dissect[1]);
					}else if(dissect.length == 1){
						end.add(dissect[0] + newLine);
					}else {
						end.add(newLine);
					}
				}
				found = true;
			}
		}
		lines = end;
	}
	
	public void replaceTagInLine(String tag, Object value) {
		for(int i = 0; i < lines.size(); i++) {
			if(containsTag(lines.get(i), tag)) {
				String edit = lines.get(i);
				edit = edit.replace("${"+tag+"}", value.toString());
				lines.set(i, edit);
			}
		}
	}
	
	private void removeTagLine(String tag) {
		List<String> newLines = new ArrayList<>();
		for(String s : lines) {
			if(!containsTag(s, tag)) {
				newLines.add(s);
			}
		}
		lines = newLines;
	}
	
	private void addRunes() {
		if(item instanceof Inscribable) {
			List<String> runeLore = new ArrayList<>();
			Map<Rune, Integer> runes = ((Inscribable)item).getAppliedRunes();
			runes.forEach((k, v)->{
				runeLore.add(k.getName()+" "+new RomanNumeral(v).getNumeral());
			});
			replaceTagWithLines("runes", runeLore);
		}
	}
	
	private void translateColorCodes() {
		List<String> newLore = new ArrayList<>();
		for(String s : lines) {
			String edit = ChatColor.translateAlternateColorCodes('&', s);
			newLore.add(edit);
		}
		lines = newLore;
	}
	
	public List<String> getLore(){
		if(item instanceof Sword) {
			replaceTagInLine("damage", ((Sword)item).getDamage());
			replaceTagInLine("attackSpeed", ((Sword)item).getAttackSpeed());
		}
		if(item instanceof Inscribable) {
			addRunes();
			replaceTagInLine("slots", ((Inscribable)item).getAvailableSlots());
			if(((Inscribable)item).isProtected()) {
				replaceTagWithLines("protected", "PROTECTED");
			}else {
				removeTagLine("protected");
			}
		}
		if(item instanceof Durable) {
			replaceTagInLine("maxDurability", ((Durable)item).getMaxDurability());
			replaceTagInLine("durability", ((Durable)item).getDurability());
		}
		if(item instanceof Armor) {
			replaceTagInLine("armor", ((Armor)item).getArmor());
			replaceTagInLine("toughness", ((Armor)item).getToughness());
		}
		if(item instanceof ca.damocles.Items.Types.Rune) {
			replaceTagInLine("rune", ((ca.damocles.Items.Types.Rune)item).getRune().getName());
			replaceTagInLine("level", new RomanNumeral( ((ca.damocles.Items.Types.Rune)item).getLevel()).getNumeral());
			replaceTagInLine("success", ((ca.damocles.Items.Types.Rune)item).getSuccess());
			replaceTagInLine("destroy", ((ca.damocles.Items.Types.Rune)item).getDestroy());
		}
		translateColorCodes();
		return lines;
	}
	
}
