package by.pva.springio.configuration.annotationbasedconfiguration.autowired.optionalautowiring;

public class Dependency3{
	
	private String name = "Dependency3";

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
