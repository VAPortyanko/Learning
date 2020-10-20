package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;

public class BinarySearch_method {
	   public static void main(String args[]) {
	   
	      // create arraylist       
	      ArrayList<String> arlst = new ArrayList<String>();

	      // populate the list
	      arlst.add("A");
	      arlst.add("B");
	      arlst.add("C");
	      arlst.add("D");
	      

	      // search the list for key 'C'   
	      // List must be ordered.
	      int index = Collections.binarySearch(arlst, "C");     

	      System.out.println("'C' is available at index: " + index);
	   }    
	}