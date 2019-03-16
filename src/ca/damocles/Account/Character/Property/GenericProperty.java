package ca.damocles.Account.Character.Property;

public class GenericProperty extends Property{

	private Object value;
	
	public GenericProperty(PropertyType type, Object value) {
		super(type);
		this.value = value;
	}

	@Override
	public Object getValue() {
		return value;
	}
	
	public void setValue(Object value) {
		this.value = value;
	}

}
