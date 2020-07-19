package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch_method {
	   public static void main(String args[]) {
	   
	      ArrayList<String> arlst = new ArrayList<String>();

	      arlst.add("B");
	      arlst.add("D");
	      arlst.add("A");
	      arlst.add("C");

	      // search the list for key 'C'   
	      // List must be ordered.
	      Collections.sort(arlst);
	      int index = Collections.binarySearch(arlst, "C");     

	      System.out.println("'C' is available at index: " + index);
	   }    
	}