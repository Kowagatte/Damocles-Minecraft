package ca.damocles.Account.Character.Property;

public class IntProperty extends Property{

	private Integer value;
	
	public IntProperty(PropertyType type, Integer value) {
		super(type);
		this.value = value;
	}

	@Override
	public Integer getValue() {
		return value;
	}
	
	@Override
	public void setValue(Object value) {
		if(value instanceof Integer) {
			this.value = (Integer)value;
		}
	}

}
