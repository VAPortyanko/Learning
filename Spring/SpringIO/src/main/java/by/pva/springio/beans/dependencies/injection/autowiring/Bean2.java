package by.pva.springio.beans.dependencies.injection.autowiring;

public class Bean2 {
	
	private String name;
	private Bean4 bean4;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bean4 getBean4() {
		return bean4;
	}
	public void setBean4(Bean4 bean4) {
		this.bean4 = bean4;
	}
	
}
