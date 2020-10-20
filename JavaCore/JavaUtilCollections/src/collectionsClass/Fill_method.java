package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fill_method {
	   public static void main(String args[]) {

	      // create array list object       
	      List<String> arrlist = new ArrayList<String>();

	      // populate the list
	      arrlist.add("A");
	      arrlist.add("B");
	      arrlist.add("C");

	      System.out.println("List elements before fill: "+arrlist);

	      // fill the list with 'TP'
	      Collections.fill(arrlist,"TP");

	      System.out.println("List elements after fill: "+arrlist);    
	   }
	}