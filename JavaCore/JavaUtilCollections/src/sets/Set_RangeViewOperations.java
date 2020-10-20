// https://docs.oracle.com/javase/tutorial/collections/interfaces/sorted-set.html
package sets;

import java.util.HashMap;

public class Set_RangeViewOperations {
	public static void main(String[] args) {
		HashMap<String, String> hs = new HashMap<>();
		String element1 = "1";
		
		hs.put("1", "1");
		hs.keySet().remove(element1);
		
		// The set supports element removal, which removes the corresponding mapping from the map, via the
                //
		// 1) Iterator.remove,
                // 2) Set.remove,
                // 3) removeAll, 
                // 4) retainAll, 
                // 5) clear.
                //
		// It does not support the 
		//
                // 1) add 
                // 2) addAll.
		
		System.out.println(hs);
	}
}