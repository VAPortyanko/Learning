package collectionsClass;

import java.util.*;

public class Reverse_method {
   public static void main(String[] args) {
      
      // create array list object
      ArrayList<String> arrlst = new ArrayList<String>();

      // populate the list
      arrlst.add("A");
      arrlst.add("B");
      arrlst.add("C");
      arrlst.add("D");
      arrlst.add("E");

      System.out.println("The initial list is :"+arrlst);

      // reverse the list
      Collections.reverse(arrlst);

      System.out.println("The Reverse List is :"+arrlst);
   }
}
