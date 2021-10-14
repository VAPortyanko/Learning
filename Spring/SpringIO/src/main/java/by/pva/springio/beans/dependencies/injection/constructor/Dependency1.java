package by.pva.springio.beans.dependencies.injection.constructor;

public class Dependency1 {

	private String name;
	private int age;

	public Dependency1(String name, int age) {
		this.name = name;
		this.age = age;
	}
	
	public String getName() {
		return name;
	}
	
	public int getAge() {
		return age;
	}

	@Override
	public String toString() {
		return "Name: " + name + ", age: " + age;
	}
	
}
