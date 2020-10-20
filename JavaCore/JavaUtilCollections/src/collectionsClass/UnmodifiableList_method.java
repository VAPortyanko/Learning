package collectionsClass;

import java.util.*;

public class UnmodifiableList_method {
   public static void main(String[] args) {
      
      // create array list
      List<Character> list = new ArrayList<Character>();

      // populate the list
      list.add('X');
      list.add('Y');

      System.out.println("Initial list: "+ list);

      // make the list unmodifiable
      List<Character> immutablelist = Collections.unmodifiableList(list);

      // try to modify the list
      immutablelist.add('Z');      
   }
}