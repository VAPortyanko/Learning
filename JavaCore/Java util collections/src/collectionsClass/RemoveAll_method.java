package collectionsClass;

import java.util.*;

public class RemoveAll_method {
   public static void main(String args[]) {
      
      String init[] = { "One", "Two", "Three", "One", "Two", "Three" };

      List<String> list1 = new ArrayList<String>(Arrays.asList(init));
      List<String> list2 = new ArrayList<String>(Arrays.asList(init));

      // remove from list1
      list1.remove("One");
      System.out.println("The list1 is : "+list1);

      // remove from list2 using singleton
      list2.removeAll(Collections.singleton("One")); // boolean java.util.List.removeAll(Collection<?> c)	   
      System.out.println("The list2 is :"+list2);
   }
}