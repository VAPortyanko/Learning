package lists;

import java.util.ArrayList;
import java.util.Arrays;

public class List_Initialization {

	@SuppressWarnings("serial")
	public static void main(String[] args) {
		// First way
		ArrayList<String> list1 = new ArrayList<String>(Arrays.asList("Pratap", "Peter", "Harsh"));
		System.out.println("Elements are:" + list1);
		
		// Second way
		ArrayList<String> cities = new ArrayList<String>(){{
		   add("Delhi");
		   add("Agra");
		   add("Chennai");
		}};
		System.out.println("Content of Array list cities:" + cities);
		
		// Third way
		ArrayList<String> books = new ArrayList<String>();
		books.add("Java Book1");
		books.add("Java Book2");
		books.add("Java Book3");
		System.out.println("Books stored in array list are: "+books);
	}
}
