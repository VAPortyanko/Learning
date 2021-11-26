package by.pva.springio.configuration.annotationbasedconfiguration.autowired.arraysandcollections;

import org.springframework.core.annotation.Order;

//Your target beans can implement the org.springframework.core.Ordered interface 
//or use the @Order or standard @Priority annotation if you want items in the array 
//or list to be sorted in a specific order. Otherwise, their order follows 
//the registration order of the corresponding target bean definitions in the container.
@Order(value = 1)
public class Dependency3 implements Dependency{
	
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
