package collectionsClass;

import java.util.Collections;
import java.util.LinkedList;

public class Max_method {
	   public static void main(String args[]) { 
	      
	      LinkedList<Integer> list = new LinkedList<Integer>();

	      list.add(-18);  
	      list.add(40);  
	      list.add(-45);  
	      list.add(12); 

	      // comparing using natural ordering
	      System.out.println("Max val: " + Collections.max(list,null));          
	   }  
	}
