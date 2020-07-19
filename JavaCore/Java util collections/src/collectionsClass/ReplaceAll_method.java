package collectionsClass;

import java.util.*;

public class ReplaceAll_method {
   public static void main(String[] args) {
      
      Vector<String> vector = new Vector<String>();

      vector.add("1");
      vector.add("two");
      vector.add("1");

      System.out.println("Initial values are :"+vector);

      Collections.replaceAll(vector, "1", "one");

      System.out.println("Value after replace :"+ vector);
   }
} 
