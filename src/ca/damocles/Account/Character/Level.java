package ca.damocles.Account.Character;

public class Level {
	
	private int level;
	private Nature nature;
	
	public Level(int level, Nature nature) {
		this.level = level;
		this.nature = nature;
	}
	
	public int experienceToNextLevel() {
		switch(nature) {
			case ERRATIC:
				if(level <= 50) {
					return ( (level * level * level) * (100 - level) ) / 50;
				}else if((level > 50) && (level <= 68)) {
					return ( (level * level * level) * (150 - level) ) / 100;
				}else if((level > 68) && (level <= 98)) {
					return ( (level * level * level) * ( ( 1911 - (10 * level) ) / 3 ) ) / 500;
				}else {
					return ( (level * level * level) * (160 - level) ) / 100;
				}
			case FAST:
				return (4 * (level * level * level)) / 5;
			case MEDIUM_FAST:
				return (level * level * level);
			case MEDIUM_SLOW:
				return ((6/5) * (level * level * level)) - (15 * (level * level)) + (100 * level) - 140;
			case SLOW:
				return (5 * (level * level * level)) / 4;
			case FLUCTUATING:
				if(level <= 15) {
					return (level * level * level) * ( ( ( (level + 1) / 3 ) + 24 ) / 50 );
				}else if((level > 15) && (level < 36)) {
					return (level * level * level) * ( (level + 14) / 50 );
				}else {
					return (level * level * level) * ( ( (level / 2) + 32 ) / 50 );
				}
		}
		return 0;
	}
}
