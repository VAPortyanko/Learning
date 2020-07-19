package comparing;

import java.util.Comparator;
import java.util.Map;
import java.util.TreeMap;

public class Comparator_Ex2 {
	public static void main(String[] args) {
		TreeMap<String, Integer> tm = new TreeMap<String, Integer>(new MyComp());
		tm.put("Vasia Pupkin", 1);
		tm.put("Dmitrii Mendeleev", 2);
		tm.put("Isaak Niutan", 3);
		tm.put("Albert Enshtain", 4);
		tm.put("Nikola Tesla", 5);
		tm.put("Anton Tesla", 6);
		
		System.out.println("Data sorted by name and surname:");
		
		for (Map.Entry<String, Integer> a: tm.entrySet())
			System.out.println(a.getKey() + " " + a.getValue());
	}
}

class MyComp implements Comparator<String>{

	public int compare(String o1, String o2) {
		int i, j, k;
		String aStr = o1, bStr = o2;
		
		// Search for the index from which the surname begins.
		i = aStr.lastIndexOf(' ');
		j = bStr.lastIndexOf(' '); 
		
		k = aStr.substring(i).compareTo(bStr.substring(j));
		
		if (k == 0)
			return aStr.compareTo(bStr);
		else
			return k;
	}
}