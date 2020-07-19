package maps.treeMap;

import java.util.Comparator;
import java.util.TreeSet;

public class TreeMap_Contains_method {
	public static void main(String[] args) {
		
		System.out.println("Create TreeSet instance with custom comparator (Comparing by UserName).");
		
		TreeSet<User> set1 = new TreeSet<>(new Comparator<User>(){
			@Override
			public int compare(User o1, User o2) {
				if (o1.getUsername().equals(o2.getUsername()))
					return 0;
				else
					return -1;
			}
		});	
		
		System.out.println("Create two new users with similar names (user1(\"Vasia\") and user2(\"Vasia\")).");
		// Create two new users with similar names.
		User user1 = new User();
		user1.setUsername("Vasia");
		User user2 = new User();
		user2.setUsername("Vasia");
		
		// Add first one to the TreeSet.
		System.out.println("Add \"user1\" to the TreeSet.");
		set1.add(user1);
		
		// Check if each users contains in the TreeSet
		System.out.println("Check if each users contains in the TreeSet");
		System.out.println("User1 is contained in the TreeSet: " + set1.contains(user1));
		System.out.println("User2 is contained in the TreeSet: " + set1.contains(user2));
		
		//********************************************************************************************//
		System.out.println("\nCreate new TreeSet without comparator");
		TreeSet<User> set2 = new TreeSet<>();
		System.out.println("Add user1 to it");
		set2.add(user1);
		// Check if each users contains in the TreeSet(without custom comparator)
		System.out.println("Check if each users contains in the TreeSet");
		System.out.println("User1 is contained in the TreeSet: " + set2.contains(user1)); // A custom class without a comparator must implements the comparable interface.
		System.out.println("User2 is contained in the TreeSet: " + set2.contains(user2));
		
		
		
	}
}

final class User {

	private String username;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}
}

