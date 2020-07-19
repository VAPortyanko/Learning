package maps.identityHashMap;

import java.util.HashMap;
import java.util.IdentityHashMap;
import java.util.Map;

public class IdentityHashMap_ex {
	public static void main(String[] args) {

		// Creating HashMap and IdentityHashMap objects 
        Map<String, String> hm = new HashMap<>(); 
        Map<String, String> ihm = new IdentityHashMap<>(); 
  
        // Putting key and value in HashMap and IdentityHashMap Object 
        hm.put("hmkey","hmvalue"); 
        hm.put(new String("hmkey"),"hmvalue1");  
        ihm.put("ihmkey","ihmvalue");  
        ihm.put(new String("ihmkey"),"ihmvalue1");  
          
        // Print Size of HashMap and WeakHashMap Object 
        //hm.size() will print 1 since it compares the objects logically 
        // and both the keys are same 
        System.out.println("Size of HashMap--" + hm.size()+ " content:" + hm); 
          
        //ihm.size() will print 2 since it compares the objects by reference 
        System.out.println("Size of IdentityHashMap--" + ihm.size() + " content:" + ihm); 
	}
}
