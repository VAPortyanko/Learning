package by.pva.springio.beans.dependencies.injection.constructor;

public class Dependency2 {

	private String firstName;
	private String lastName;

	public Dependency2(String firstName, String lastName) {
		this.firstName = firstName;
		this.lastName = lastName;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public String getLastNAme() {
		return lastName;
	}

	@Override
	public String toString() {
		return "First name: " + firstName + ", lastName: " + lastName;
	}
	
}
