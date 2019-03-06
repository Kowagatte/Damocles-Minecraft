package ca.damocles.utils;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import ca.damocles.Cardinal;

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
	
}
