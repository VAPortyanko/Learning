package collectionsClass;

import java.util.*;

public class ReplaceAll_method {
   public static void main(String[] args) {
      
      // create vector
      Vector<String> vector = new Vector<String>();

      // populate the vector
      vector.add("R");
      vector.add("B");
      vector.add("R");

      System.out.println("Initial values are :"+vector);

      // replace 'R' with 'Replace All'
      Collections.replaceAll(vector, "R", "Replace All");

      System.out.println("Value after replace :"+ vector);
   }
} 
