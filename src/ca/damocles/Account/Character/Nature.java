package ca.damocles.Account.Character;

import java.util.Random;

public enum Nature {
	ERRATIC(0),
	FAST(1),
	MEDIUM_FAST(2),
	MEDIUM_SLOW(3),
	SLOW(4),
	FLUCTUATING(5);
	
	int id;
	Nature(int id){ this.id = id; }
	public int getID() { return id; }
	
	public static Nature valueOf(int id) {
		for(Nature nature : Nature.values()) {
			if(nature.getID() == id)
				return nature;
		}
		return null;
	}
	
	public static Nature getRandomNature() {
		return Nature.valueOf(new Random().nextInt(6));
	}
}
