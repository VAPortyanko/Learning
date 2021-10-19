package by.pva.springio.beans.dependencies.injection.autowiring;

public class Bean1 {
	
	private String name;
	private Bean3 bean3;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Bean3 getBean3() {
		return bean3;
	}
	public void setBean3(Bean3 bean3) {
		this.bean3 = bean3;
	}
	
}
