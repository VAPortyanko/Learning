// https://beginnersbook.com/2013/12/how-to-synchronize-hashmap-in-java-with-example/
package collectionsClass;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.Iterator;
public class SynchronizedMap_Ex {
    public static void main(String args[]) {
    	
         HashMap<Integer, String> hmap= new HashMap<Integer, String>();
         hmap.put(2, "Anio");
         hmap.put(44, "Ajit");
         hmap.put(1, "Brad");
         hmap.put(4, "Sachin");
         hmap.put(88, "XYZ");

         Map<Integer, String> map= Collections.synchronizedMap(hmap);
         Set<Entry<Integer, String>> set = map.entrySet();
         synchronized(map){                                          // Important - synchronized by "map" object.
             Iterator<Entry<Integer, String>> i = set.iterator();
             // Display elements
             while(i.hasNext()) {
                Entry<Integer, String> me = i.next();
                System.out.print(me.getKey() + ": ");
                System.out.println(me.getValue());
             }
         }
    }
}