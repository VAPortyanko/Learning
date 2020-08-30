package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Copy_method {
	public static void main(String args[]) {

		List<String> srclst = new ArrayList<String>(5);
		ArrayList<String> destlst = new ArrayList<String>(10);

		srclst.add("Java");
		srclst.add("is");
		srclst.add("best");

		// Collections.copy(destlst, srclst); // OutOfBoundException.
		// See https://stackoverflow.com/questions/689370/how-to-copy-java-collections-list for details.

		destlst.add("C++");
		destlst.add("is");
		destlst.add("older");

		Collections.copy(destlst, srclst);

		System.out.println("Value of source list: " + srclst);
		System.out.println("Value of destination list: " + destlst);
	}
}