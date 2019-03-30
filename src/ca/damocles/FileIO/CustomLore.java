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
import ca.damocles.Items.ItemType;
import ca.damocles.Items.Interfaces.Inscribable;
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
	
	public List<Character> getColorCodes(String line) {
		List<Character> colorCodes = new ArrayList<>();
		for(int i = 0; i < line.length(); i++) {
			char c = line.charAt(i);
			if(c == '\u00a7') {
				colorCodes.add(line.charAt(i+1));
			}
		}
		return colorCodes;
	}
	
	public void replaceTagWithLines(String tag, String... newString) {
		List<Character> colorCodes = new ArrayList<>();
		List<String> before = new ArrayList<>(), after = new ArrayList<>(), end = new ArrayList<>();
		boolean found = false;
		for(String s : lines) {
			if(!containsTag(s, tag)) {
				if(!found) {
					before.add(s);
				}else {
					after.add(s);
				}
			}else {
				colorCodes = getColorCodes(s);
				found = true;
			}
		}
		end.addAll(before);
		for(String newLine : newString) {
			String editedLine = "";
			for(Character c : colorCodes) {
				editedLine = editedLine+'\u00a7'+c;
			}
			editedLine = editedLine+newLine;
			end.add(editedLine);
		}
		end.addAll(after);
		lines = end;
	}
	
	public void replaceTagWithLines(String tag, List<String> newString) {
		List<Character> colorCodes = new ArrayList<>();
		List<String> before = new ArrayList<>(), after = new ArrayList<>(), end = new ArrayList<>();
		boolean found = false;
		for(String s : lines) {
			if(!containsTag(s, tag)) {
				if(!found) {
					before.add(s);
				}else {
					after.add(s);
				}
			}else {
				colorCodes = getColorCodes(s);
				found = true;
			}
		}
		end.addAll(before);
		for(String newLine : newString) {
			String editedLine = "";
			for(Character c : colorCodes) {
				editedLine = editedLine+'\u00a7'+c;
			}
			editedLine = editedLine+newLine;
			end.add(editedLine);
		}
		end.addAll(after);
		lines = end;
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
	
	public List<String> getLore(){
		addRunes();
		return lines;
	}
	
}
