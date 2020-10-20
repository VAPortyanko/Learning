package collectionsClass;

import java.util.*;

public class RemoveAll_method {
   public static void main(String args[]) {
      
      // create an array of string objs
      String init[] = { "One", "Two", "Three", "One", "Two", "Three" };

      // create two lists
      List<String> list1 = new ArrayList<String>(Arrays.asList(init));
      List<String> list2 = new ArrayList<String>(Arrays.asList(init));

      // remove from list1
      list1.remove("One");
      System.out.println("List1 value: "+list1);

      // remove from list2 using singleton
      list2.removeAll(Collections.singleton("One")); // boolean java.util.List.removeAll(Collection<?> c)	   
      System.out.println("The SingletonList is :"+list2);
   }
}