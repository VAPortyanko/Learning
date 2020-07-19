package test;

import java.util.Map;
import static java.util.Map.Entry.comparingByKey;
import static java.util.stream.Collectors.*;
import java.util.HashMap;
import java.util.LinkedHashMap;

public class Test {

	public static void main(String[] args) {

		Map<String, String> map = new HashMap<>();
        map.put("Russia", "Moscow");
        map.put("Turkey", "Ankara");
        map.put("England", "London");
        map.put("Australia", "Canberra");

        LinkedHashMap<String, String> collect = map
                .entrySet()
                .stream()
                .sorted(comparingByKey())
                .collect(toMap(Map.Entry::getKey, Map.Entry::getValue, (e1, e2) -> e2, LinkedHashMap::new));
        
        System.out.println(collect);
	} 
}
