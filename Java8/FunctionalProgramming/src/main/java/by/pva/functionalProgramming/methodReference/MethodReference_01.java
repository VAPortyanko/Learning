package by.pva.functionalProgramming.methodReference;

public class MethodReference_01 {
	public static void main(String[] args) {
		UserFactory userFactory = User::new;
		User user = userFactory.create("John", "Snow");
		System.out.println(user);
	}
}
	
class User {
    String name, surname;
 
    User(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }

	@Override
	public String toString() {
		return "User [name=" + name + ", surname=" + surname + "]";
	}
    
}

@FunctionalInterface
interface UserFactory {
    User create(String name, String surname);
}