package ca.damocles.Account.Character.Property;

public class DoubleProperty extends Property{

	private Double value;
	
	public DoubleProperty(PropertyType type, Double value) {
		super(type);
		this.value = value;
	}

	@Override
	public Double getValue() {
		return value;
	}
	
	@Override
	public void setValue(Object value) {
		if(value instanceof Double) {
			this.value = (Double)value;
		}
	}

}
