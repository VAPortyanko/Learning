package collectionsClass;

import java.util.*;

public class UnmodifiableList_method {
   public static void main(String[] args) {
      
	   List<Character> list = new ArrayList<Character>();

      list.add('X');
      list.add('Y');

      System.out.println("Initial list: "+ list);

      List<Character> immutablelist = Collections.unmodifiableList(list);

       immutablelist.add('Z');      
   }
}