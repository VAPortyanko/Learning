package test;

import java.util.HashMap;
import java.util.Map;

public class MondayCollectionTst {

	public static void main(String[] args) {
		Map<String, String> map = new HashMap<>();
		map.put("1", "1");
		map.put("2", "2");
		map.put("3", "3");
		map.put("4", "4");
		map.put("5", "5");
		
		System.out.println(map);
		
		map.values().remove("3");
		
		System.out.println(map);
	}
}
