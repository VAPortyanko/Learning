package maps.weakedHashMap;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakedHashMap_Ex {
	public static void main(String[] args) throws InterruptedException {
		
		Map<Object, String> map = new WeakHashMap<Object, String>(); 
		String obj = new String("A"); 

		map.put(obj, "object"); 
		
		System.out.println(map.size()); 
		
		obj = null;
		
		System.gc(); 
		System.runFinalization(); // or Thread.sleep(5000);
		
		System.out.println(map.size()); 
	}
}
