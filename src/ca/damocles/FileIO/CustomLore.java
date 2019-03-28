package ca.damocles.FileIO;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import ca.damocles.Damocles;
import ca.damocles.Items.ItemType;

public class CustomLore {
	
	private File file;
	private List<String> lines;
	
	public CustomLore(ItemType type) {
		lines = new ArrayList<>();
		File file = new File(Damocles.directories.ITEMLORES, type.toString()+".txt");
		if(!file.exists()) {
			Damocles.directories.exportResource(type.toString()+".txt", Damocles.directories.ITEMLORES);
		}
		this.file = new File(Damocles.directories.ITEMLORES, type.toString()+".txt");
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
	
	public List<String> getLore(){
		return lines;
	}
	
}
