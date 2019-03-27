package ca.damocles.Items;

public enum TextureID {
	DEFAULT_DIAMOND_SWORD(0),
	BAT(1),
	HAMMER(2),
	BRONZE_SWORD(3),
	STEEL_RAPIER(4),
	STEEL_BROADSWORD(5),
	STEEL_CUTLASS(6),
	PLATINUM_SCIMITAR(7),
	EXCALIBUR(8);
	
	private int id;
	TextureID(int id){ this.id = id; }
	public int getID() { return id; }
}
