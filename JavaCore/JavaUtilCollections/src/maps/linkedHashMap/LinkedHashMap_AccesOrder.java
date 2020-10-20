package maps.linkedHashMap;


import java.util.LinkedHashMap;

public class LinkedHashMap_AccesOrder {
	public static void main(String[] args) {
		LinkedHashMap<String, String> map1 = new LinkedHashMap<>(20, 0.75F, false);
		LinkedHashMap<String, String> map2 = new LinkedHashMap<>(20, 0.75F, true);
		
		map1.put("1", "1");
		map1.put("2", "2");
		map1.put("3", "3");
		map1.put("4", "4");
		map1.put("5", "5");
		
		map2.put("1", "1");
		map2.put("2", "2");
		map2.put("3", "3");
		map2.put("4", "4");
		map2.put("5", "5");

		System.out.println("Map1 has argument accesOrder = false");
		System.out.println("Map2 has argument accesOrder = true");
		
		System.out.println("\nMap1 before getting element with key = 3:\n" + map1);
		System.out.println("Map2 before getting element with key = 3:\n" + map2);
		
		map1.get("3");
		map2.get("3");
		
		System.out.println("\nMap1 after getting element with key = 3:\n" + map1);
		System.out.println("Map2 after getting element with key = 3:\n" + map2);
	}
}