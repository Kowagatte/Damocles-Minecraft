package ca.damocles.Spells;

public enum Spell {
	
	NONE("Error", 0, new SpellEffect[] {}),
	ARACANE_MISSILE("Arcane Missile", 1, new SpellEffect[] {new SpellEffect(1, -1.0, CastType.TARGET, TargetType.TARGET_ENEMY, Infliction.DELAYED, Effect.ARCANE_MISSILE, false)});
	
	String name;
	double cost;
	SpellEffect[] effect;
	Spell(String name, double cost, SpellEffect[] effect){
		this.name = name;
		this.cost = cost;
		this.effect = effect;
	}
	
	public String getName() {
		return name;
	}
	
	public double getCost() {
		return cost;
	}
	
	public SpellEffect[] getSpellEffect() {
		return effect;
	}
	
}
