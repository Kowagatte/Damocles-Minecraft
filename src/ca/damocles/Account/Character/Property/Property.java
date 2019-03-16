package ca.damocles.Account.Character.Property;

public abstract class Property {
	
	private PropertyType type;
	
	public Property(PropertyType type) {
		this.type = type;
	}
	
	public PropertyType getType() {
		return type;
	}
	
	public String getName() {
		return type.toString();
	}
	
	public abstract Object getValue();
	
	public abstract void setValue(Object value);
	
}
