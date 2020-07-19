package collectionsClass;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Fill_method {
	   public static void main(String args[]) {

	      List<String> arrlist = new ArrayList<String>();

	      arrlist.add("A");
	      arrlist.add("B");
	      arrlist.add("C");

	      System.out.println("List elements before fill: " + arrlist);

	      Collections.fill(arrlist,"Element");

	      System.out.println("List elements after fill: " + arrlist);    
	   }
	}