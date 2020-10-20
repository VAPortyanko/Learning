package collectionsClass;

import java.util.*;

public class Shuffle_method {
   public static void main(String args[]) {
      
      // create array list object       
      List<String> arrlist = new ArrayList<String>();

      // populate the list
      arrlist.add("A");
      arrlist.add("B");
      arrlist.add("C");  

      System.out.println("Initial collection: "+arrlist);

      // shuffle the list
      Collections.shuffle(arrlist);

      System.out.println("Final collection after shuffle: "+arrlist);
   }    
} 