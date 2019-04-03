package ca.damocles.Spells;

public class SpellEffect {
	
	private double value, duration;
	private CastType cast;
	private TargetType target;
	private Infliction infliction;
	private Effect effect;
	private boolean heal;
	
	public SpellEffect(double value, double duration, CastType cast, TargetType target, Infliction infliction, Effect effect, boolean heal) {
		this.value = value;
		this.duration = duration;
		this.cast = cast;
		this.target = target;
		this.infliction = infliction;
		this.effect = effect;
		this.heal = heal;
	}
	
	public double getValue() {
		return value;
	}
	
	public double getDuration() {
		return duration;
	}
	
	public TargetType getTargetType() {
		return target;
	}
	
	public CastType getCastType() {
		return cast;
	}
	
	public Infliction getInfliction() {
		return infliction;
	}
	
	public Effect getEffect() {
		return effect;
	}
	
	public boolean isHeal() {
		return heal;
	}
	
}
