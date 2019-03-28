package ca.damocles.FileIO;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.List;

import ca.damocles.Cardinal;
import ca.damocles.Damocles;

public class DamoclesDirectory {
	
	public File DAMOCLES = Cardinal.getInstance().getDataFolder();
	public File PLUGIN = DAMOCLES.getParentFile();
	public File SERVER = PLUGIN.getParentFile();
	public File CLAN = new File(DAMOCLES, "clan");
	public File PLAYERS = new File(DAMOCLES, "players");
	public File ITEM = new File(DAMOCLES, "item");
	public File ITEMLORES = new File(ITEM, "lores");
	
	public List<File> listAllFilesInDirectory(File file){
		List<File> files = new ArrayList<>();
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				if(subFile.isFile()) {
					files.add(subFile);
				}
			}
		}
		return files;
	}
	
	public List<File> listAllSubDirectories(File file) {
		List<File> files = new ArrayList<>();
		if(file.isDirectory()) {
			for(File subFile : file.listFiles()) {
				if(subFile.isDirectory()) {
					files.add(subFile);
				}
			}
		}
		return files;
	}
	
	public File exportResource(String resourceName, File targetDirectory) {
		File file = new File(targetDirectory, resourceName);
		if(file.exists())
			return file;
		try{
			InputStream stream = Damocles.class.getResourceAsStream("/"+resourceName);
			Files.copy(stream, file.toPath());
			stream.close();
		}catch(NullPointerException | IOException e) { e.printStackTrace(); }
		return file;
	}
	
}
