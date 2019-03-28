package ca.damocles.Account.Character;

public class Level {
	
	private double level;
	private double nextLevel;
	private Nature nature;
	
	public Level(int level, Nature nature) {
		this.level = level;
		this.nextLevel = level + 1.0;
		this.nature = nature;
	}
	
	public int experienceToNextLevel() {
		switch(nature) {
			case ERRATIC:
				if(level <= 50) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (100.0 - nextLevel) ) / 50.0);
				}else if((level > 50) && (level <= 68)) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (150.0 - nextLevel) ) / 100.0);
				}else if((level > 68) && (level <= 98)) {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * ( ( 1911.0 - (10.0 * nextLevel) ) / 3.0 ) ) / 500.0);
				}else {
					return (int) Math.floor(( (nextLevel * nextLevel * nextLevel) * (160.0 - nextLevel) ) / 100.0);
				}
			case FAST:
				return (int) Math.floor((4 * (nextLevel * nextLevel * nextLevel)) / 5.0);
			case MEDIUM_FAST:
				return (int) Math.floor((nextLevel * nextLevel * nextLevel));
			case MEDIUM_SLOW:
				return (int) Math.floor(((6/5) * (nextLevel * nextLevel * nextLevel)) - (15.0 * (nextLevel * nextLevel)) + (100.0 * nextLevel) - 140.0);
			case SLOW:
				return (int) Math.floor((5 * (nextLevel * nextLevel * nextLevel)) / 4.0);
			case FLUCTUATING:
				if(level <= 15) {
					return (int) Math.floor( (nextLevel * nextLevel * nextLevel) * ((((nextLevel+1)/3.0)+24.0)/50.0) );
				}else if((level > 15) && (level < 36)) {
					return (int) Math.floor((nextLevel * nextLevel * nextLevel) * ( (nextLevel + 14) / 50.0 ));
				}else {
					return (int) Math.floor((nextLevel * nextLevel * nextLevel) * ( ( (nextLevel / 2.0) + 32.0 ) / 50.0 ));
				}
		}
		return 0;
	}
}
