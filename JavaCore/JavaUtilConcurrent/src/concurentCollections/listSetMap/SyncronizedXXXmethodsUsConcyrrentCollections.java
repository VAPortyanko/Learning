//https://www.quora.com/What-is-the-difference-between-synchronize-and-concurrent-collection-in-Java
package concurentCollections.listSetMap;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SyncronizedXXXmethodsUsConcyrrentCollections {
	public static void main(String[] args) {
		Map<String, String>syncMap = Collections.synchronizedMap(new HashMap<String, String>());
		syncMap.put(null, null);   // May allow null  keys and null values based on the original collection class being passed inside it.
		System.out.println(syncMap);
		
		Map<String, String>syncMap2 = new ConcurrentHashMap<>();
		syncMap2.put(null, null);  // NullPointerException. (doesn't allow null keys and null values).
		System.out.println(syncMap2);
	}
}

