package ca.damocles.Items;

public interface Durable {
	
	public int getMaxDurability();
	
	public int getDurability();
	
	public void setDurability(int durability);
	
	public void removeDurability(int durability);

}
