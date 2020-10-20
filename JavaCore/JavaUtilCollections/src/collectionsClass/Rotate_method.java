package collectionsClass;

import java.util.*;

public class Rotate_method {
   public static void main(String[] args) {
      
      // create array list object
      List<Integer> numbers = new ArrayList<Integer>();

      // populate the list
      for (int i = 0; i < 15; i++) {
         numbers.add(i);
      }

      System.out.println("Before : " + numbers);

      // rotate the list at distance 4
      Collections.rotate(numbers, 4);

      System.out.println("After : " + numbers);
   }
}
