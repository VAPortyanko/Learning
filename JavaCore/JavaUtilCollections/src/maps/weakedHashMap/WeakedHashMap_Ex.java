package maps.weakedHashMap;

import java.util.Map;
import java.util.WeakHashMap;

public class WeakedHashMap_Ex {
	public static void main(String[] args) throws InterruptedException {
		Map<Object, String> map = new WeakHashMap<Object, String>(); 
		
		String obj = new String("A"); // создаём объект 
		
		map.put(obj, "object"); // кладём его в мапу 
		
		System.out.println(map.size()); // в мапе один элемент 
		
		obj = null; // чистим ссылку 
		
		System.gc(); // играемся со сборщиком мусора 
		
		System.runFinalization(); // or Thread.sleep(5000);
		
		System.out.println(map.size()); // мапа должна стать пустой 
	}
}
