package ca.damocles.Account.Character;

public class StatInstance {
	
	private Stat stat;
	private int value;
	
	public StatInstance(Stat stat, int value) {
		this.stat = stat;
		this.value = value;
	}
	
	public int getValue() {
		return value;
	}
	
	public void setValue(int value) {
		this.value = value;
	}
	
	public Stat getStat() {
		return stat;
	}
	
}
