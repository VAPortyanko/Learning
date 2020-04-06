package by.pva.functionalProgramming.lambda;

import java.util.Comparator;
import java.util.function.Function;

public class ComparatorFactory {

	public Comparator<Person> getComparator(Function<Person, Integer> func) {

		return (o1, o2) -> func.apply(o1).compareTo(func.apply(o2));
		
	};
	
	public static void main(String[] args) {
		
		ComparatorFactory cFactory = new ComparatorFactory();
		Comparator<Person> compareByAge = cFactory.getComparator(person -> person.getAge());
		Comparator<Person> compareByName = cFactory.getComparator(person -> person.getName().length());
		
		Person person1 = new Person(18, "David");
		Person person2 = new Person(26, "George");
		
		System.out.println("by age " + compareByAge.compare(person1, person2));
		System.out.println("by name " + compareByName.compare(person1, person2));
		
		person1 = new Person(18, "Egor");
		person2 = new Person(18, "Dima");
		
		System.out.println("by age " + compareByAge.compare(person1, person2));
		System.out.println("by name " + compareByName.compare(person1, person2));
		
		person1 = new Person(33, "Denis");
		person2 = new Person(26, "Max");
		
		System.out.println("by age " + compareByAge.compare(person1, person2));
		System.out.println("by name " + compareByName.compare(person1, person2));
	}
}

class Person{
	
	private int age;
	private String name;
	
	Person(int age, String name){
		this.age = age;
		this.name = name;
	}
	
	int getAge () {
		return age;
	}
	
	String getName () {
		return name;
	}
}