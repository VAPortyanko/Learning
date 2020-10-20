package collectionsClass;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class NCopies_method {
	   public static void main(String[] args) {
	      
	      // create a list with n copies // Immutable
	      List<String> list = Collections.nCopies(5, "tuitorial Point");

	      // create an iterator
	      Iterator<String> itr = list.iterator();

	      System.out.println("Values are :");
	      while (itr.hasNext()) {
	         System.out.println(itr.next());
	      }
	   }      
	}