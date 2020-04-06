package by.pva.functionalProgramming.methodReference;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class MethodReference_03 {
	public static void main(String[] args) {
        List<User2> users = new ArrayList<>();
        users.add(new User2("Nick", "Boll"));
        users.add(new User2("Jan", "Nicky"));
        users.add(new User2("Bot", "Smart"));
 
        users.sort(Comparator.comparing(User2::getName));
        users.forEach(System.out::println);
	}
}
	
class User2 {
    private String name, surname;
 
    public User2(String name, String surname) {
        this.name = name;
        this.surname = surname;
    }
 
    public String getName() {
        return name;
    }
 
    public String getSurname() {
        return surname;
    }
 
    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                '}';
    }
}