package ca.damocles.IO;

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
	
	public void exportResource(String resourceName) {
		File file = new File(DAMOCLES, resourceName);
		if(file.exists())
			return;
		try{
			InputStream stream = Damocles.class.getResourceAsStream(resourceName);
			Files.copy(stream, file.toPath());
			stream.close();
		}catch(NullPointerException | IOException e) {
			e.printStackTrace();
		}
		return;
	}
	
}
