package by.pva.servletapi.jsp.el;

import java.util.Map;

public class Dog{

	private String breed;
	private String name;
	
	Map<String, Dog> parents;
	
	public String getBreed() {
		return breed;
	}

	public void setBreed(String breed) {
		this.breed = breed;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Map<String, Dog> getParents() {
		return parents;
	}

	public void setParents(Map<String, Dog> parents) {
		this.parents = parents;
	}
	
}
