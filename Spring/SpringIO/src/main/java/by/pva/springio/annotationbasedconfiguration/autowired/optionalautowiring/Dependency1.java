package by.pva.springio.annotationbasedconfiguration.autowired.optionalautowiring;

public class Dependency1{
	
	private String name = "Dependency1";

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	@Override
	public String toString() {
		return this.name;
	}
	
}
