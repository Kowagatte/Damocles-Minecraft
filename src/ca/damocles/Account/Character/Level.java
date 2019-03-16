package ca.damocles.Account.Character;

public class Level {
	
	private int level;
	private int nextLevel;
	private Nature nature;
	
	public Level(int level, Nature nature) {
		this.level = level;
		this.nextLevel = level + 1;
		this.nature = nature;
	}
	
	public int experienceToNextLevel() {
		switch(nature) {
			case ERRATIC:
				if(level <= 50) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (100 - nextLevel) ) / 50);
				}else if((level > 50) && (level <= 68)) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (150 - nextLevel) ) / 100);
				}else if((level > 68) && (level <= 98)) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * ( ( 1911 - (10 * nextLevel) ) / 3 ) ) / 500);
				}else {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (160 - nextLevel) ) / 100);
				}
			case FAST:
				return (int) Math.floor((4 * (nextLevel * nextLevel * nextLevel)) / 5);
			case MEDIUM_FAST:
				return (int) Math.floor((nextLevel * nextLevel * nextLevel));
			case MEDIUM_SLOW:
				return (int) Math.floor(((6/5) * (nextLevel * nextLevel * nextLevel)) - (15 * (nextLevel * nextLevel)) + (100 * nextLevel) - 140);
			case SLOW:
				return (int) Math.floor((5 * (nextLevel * nextLevel * nextLevel)) / 4);
			case FLUCTUATING:
				if(level <= 15) {
					return (int) Math.floor((nextLevel * nextLevel * nextLevel) * ( ( ( (nextLevel + 1) / 3 ) + 24 ) / 50 ));
				}else if((level > 15) && (level < 36)) {
					return (int) Math.floor((nextLevel * nextLevel * nextLevel) * ( (nextLevel + 14) / 50 ));
				}else {
					return (int) Math.floor((nextLevel * nextLevel * nextLevel) * ( ( (nextLevel / 2) + 32 ) / 50 ));
				}
		}
		return 0;
	}
}
